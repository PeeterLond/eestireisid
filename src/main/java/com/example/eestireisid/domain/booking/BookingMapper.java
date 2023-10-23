package com.example.eestireisid.domain.booking;

import com.example.eestireisid.business.dto.BookingDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookingMapper {


    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    Booking toBooking(BookingDto bookingDto);

}