package com.example.eestireisid.business;

import com.example.eestireisid.business.dto.BookingDto;
import com.example.eestireisid.business.dto.RouteDto;
import com.example.eestireisid.infrastructure.error.ApiError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
public class SearchController {

    @Resource
    private SearchService searchService;

    @GetMapping("/search")
    @Operation(summary = "Tagastab vastavalt valitud linnade sõidugraafikud.",
    description = "Meetodi käivitamisel saadetakse päring Novateri Api-le kust saadakse sõidugraafikud, mis pannakse" +
            "edasi rakenduse andmebaasidesse. Andmebaasist otsitakse vastavalt valitud lähtekoha ja sihtkoha linnade" +
            "järgi sõidugraafikud. Kui valitud linnade vahel buss ei sõida , siis visatakse viga errorCode-iga 111")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404",
                    description = "message: Sellist teekonda pole hetkel saadaval. errorCode: 111",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))})
    public RouteDto searchSchedules(@RequestParam String fromCity, @RequestParam String toCity) {
        return searchService.searchSchedules(fromCity, toCity);
    }

    @PostMapping("/booking")
    @Operation(summary = "Salvestab broneeringu sõidule",
    description = "Kontrollib kõigepealt kas ajakava on kehtiv, kui ei ole, siis vistakase viga errorCode-iga 222. " +
            "Kui on kehtiv , siis salvestab broneeringu vastavalt inimese ees-ja perekonnanimele.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "403",
                    description = "message: Ajagraafik on uuenenud, tee uus otsing. errorCode: 222",
                    content = @Content(schema = @Schema(implementation = ApiError.class)))})
    public void addBooking(@RequestBody BookingDto request) {
        searchService.addBooking(request);
    }




}
