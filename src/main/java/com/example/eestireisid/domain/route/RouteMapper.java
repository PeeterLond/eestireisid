package com.example.eestireisid.domain.route;

import com.example.eestireisid.business.dto.RouteDto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RouteMapper {


    @Mapping(source = "fromCity.name", target = "fromCityName")
    @Mapping(source = "toCity.name", target = "toCityName")
    @Mapping(source = "distance", target = "distance")
    RouteDto toRouteDto(Route route);


}