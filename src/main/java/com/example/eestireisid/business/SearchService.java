package com.example.eestireisid.business;

import com.example.eestireisid.business.dto.BookingDto;
import com.example.eestireisid.business.dto.RouteDto;
import com.example.eestireisid.business.dto.ScheduleDto;
import com.example.eestireisid.domain.ApiService;
import com.example.eestireisid.domain.booking.Booking;
import com.example.eestireisid.domain.booking.BookingMapper;
import com.example.eestireisid.domain.booking.BookingService;
import com.example.eestireisid.domain.city.City;
import com.example.eestireisid.domain.city.CityService;
import com.example.eestireisid.domain.company.Company;
import com.example.eestireisid.domain.company.CompanyService;
import com.example.eestireisid.domain.route.RouteMapper;
import com.example.eestireisid.domain.routeschedule.RouteSchedule;
import com.example.eestireisid.domain.routeschedule.RouteScheduleService;
import com.example.eestireisid.domain.schedule.Schedule;
import com.example.eestireisid.domain.schedule.ScheduleMapper;
import com.example.eestireisid.domain.schedule.ScheduleService;
import com.example.eestireisid.domain.time.Time;
import com.example.eestireisid.domain.time.TimeService;
import com.example.eestireisid.domain.timetableroute.TimetableRoute;
import com.example.eestireisid.domain.route.Route;
import com.example.eestireisid.domain.route.RouteService;
import com.example.eestireisid.domain.timetable.Timetable;
import com.example.eestireisid.domain.timetable.TimetableService;
import com.example.eestireisid.domain.timetableroute.TimetableRouteService;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SearchService {

    @Resource
    private ApiService apiService;

    @Resource
    private TimetableService timetableService;

    @Resource
    private CityService cityService;

    @Resource
    private RouteService routeService;

    @Resource
    private TimetableRouteService timetableRouteService;

    @Resource
    private CompanyService companyService;

    @Resource
    private TimeService timeService;

    @Resource
    private ScheduleService scheduleService;

    @Resource
    private RouteScheduleService routeScheduleService;

    @Resource
    private BookingService bookingService;

    @Resource
    private ScheduleMapper scheduleMapper;

    @Resource
    private RouteMapper routeMapper;

    @Resource
    private BookingMapper bookingMapper;



    public RouteDto searchSchedules(String fromCity, String toCity) {
        handleApiCall();

        Route route = routeService.findRouteBy(fromCity, toCity);
        List<ScheduleDto> scheduleDtos = getScheduleDtos(route);

        RouteDto routeDto = routeMapper.toRouteDto(route);
        routeDto.setSchedules(scheduleDtos);

        return routeDto;
    }

    public void addBooking(BookingDto request) {
        Booking booking = bookingMapper.toBooking(request);
        Schedule schedule = scheduleService.getScheduleBy(request.getScheduleId());
        booking.setSchedule(schedule);
        bookingService.saveBooking(booking);

    }

    private List<ScheduleDto> getScheduleDtos(Route route) {
        List<RouteSchedule> routeSchedules = routeScheduleService.getAllRouteSchedulesBy(route.getId());
        ArrayList<Schedule> schedules = new ArrayList<>();
        for (RouteSchedule routeSchedule : routeSchedules) {
            schedules.add(routeSchedule.getSchedule());
        }
        List<ScheduleDto> scheduleDtos = scheduleMapper.toScheduleDtos(schedules);
        return scheduleDtos;
    }

    private void handleApiCall() {
        Mono<JsonNode> response = apiService.getTimetableFromApi();
        response.subscribe(result -> {
            handleApiResponse(result);
        });
    }

    private void handleApiResponse(JsonNode result) {
        JsonNode expiresNode = result.get("expires");
        Instant datetimeInstant = convertToInstant(expiresNode);
        Optional<Timetable> timetableOptional = timetableService.findTimetableBy(datetimeInstant);

        if (timetableOptional.isEmpty()) {
            handleDatabaseFill(result);
        }
    }

    private void handleDatabaseFill(JsonNode result) {
        JsonNode expiresNode = result.get("expires");
        Timetable timetable = createTimetable(expiresNode);

        JsonNode routes = result.get("routes");

        for (JsonNode route : routes) {
            Route newRoute = createRoute(route);

            createTimetableRoutes(timetable, newRoute);

            JsonNode schedules = route.get("schedule");

            for (JsonNode schedule : schedules) {
                Schedule newSchedule = createSchedule(schedule);
                createRouteSchedule(newRoute, newSchedule);
            }
        }
    }

    private void createRouteSchedule(Route newRoute, Schedule newSchedule) {
        RouteSchedule routeSchedule = new RouteSchedule();
        routeSchedule.setRoute(newRoute);
        routeSchedule.setSchedule(newSchedule);
        routeScheduleService.saveRouteSchedule(routeSchedule);
    }

    private Schedule createSchedule(JsonNode schedule) {
        Schedule newSchedule = new Schedule();
        newSchedule.setPrice((float) schedule.get("price").asDouble());

        String companyName = schedule.get("company").get("state").asText();
        newSchedule.setCompany(getCompany(companyName));

        JsonNode startTime = schedule.get("start");
        newSchedule.setStartTime(createTime(startTime));

        JsonNode endTime = schedule.get("end");
        newSchedule.setEndTime(createTime(endTime));

        scheduleService.saveSchedule(newSchedule);
        return newSchedule;
    }

    private Time createTime(JsonNode time) {
        Time newTime = new Time();
        newTime.setDateTime(convertToInstant(time));
        newTime.setTimezoneType(time.get("timezone_type").asInt());
        newTime.setTimezone(time.get("timezone").asText());
        timeService.save(newTime);
        return newTime;
    }

    private Company getCompany(String companyName) {
        Optional<Company> companyBy = companyService.findCompanyBy(companyName);
        Company company;
        if (companyBy.isEmpty()) {
            company = createCompany(companyName);
        } else {
            company = companyBy.get();
        }
        return company;
    }

    private Company createCompany(String companyName) {
        Company newCompany = new Company();
        newCompany.setName(companyName);
        companyService.saveCompany(newCompany);
        return newCompany;
    }

    private void createTimetableRoutes(Timetable timetable, Route newRoute) {
        TimetableRoute timetableRoute = new TimetableRoute();
        timetableRoute.setTimetable(timetable);
        timetableRoute.setRoute(newRoute);
        timetableRouteService.save(timetableRoute);
    }

    private Route createRoute(JsonNode route) {
        Route newRoute = new Route();

        String fromCity = route.get("from").get("name").asText();
        newRoute.setFromCity(getCity(fromCity));

        String toCity = route.get("to").get("name").asText();
        newRoute.setToCity(getCity(toCity));

        newRoute.setDistance(route.get("distance").asInt());
        newRoute.setStatus("A");
        routeService.saveRoute(newRoute);
        return newRoute;
    }

    private City getCity(String cityString) {
        Optional<City> fromCityBy = cityService.findCityBy(cityString);
        City city;
        if (fromCityBy.isEmpty()) {
            city = createCity(cityString);
        } else {
            city = fromCityBy.get();
        }
        return city;
    }

    private City createCity(String fromCityString) {
        City newCity = new City();
        newCity.setName(fromCityString);
        cityService.saveCity(newCity);
        return newCity;
    }

    private static Instant convertToInstant(JsonNode node) {
        String date = node.get("date").asText();
        return Timestamp.valueOf(date).toInstant();
    }

    private Timetable createTimetable(JsonNode expiresNode) {
        Timetable newTimetable = new Timetable();

        Instant datetimeInstant = convertToInstant(expiresNode);
        newTimetable.setExpireDate(datetimeInstant);
        newTimetable.setTimezoneType(expiresNode.get("timezone_type").asInt());
        newTimetable.setTimezone(expiresNode.get("timezone").asText());

        timetableService.saveTimetable(newTimetable);

        return newTimetable;
    }
}
