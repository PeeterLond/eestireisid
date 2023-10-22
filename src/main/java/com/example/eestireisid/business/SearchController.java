package com.example.eestireisid.business;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    @Resource
    private SearchService searchService;

    @GetMapping("/search")
    public void searchSchedules() {
        searchService.searchSchedules();
    }


}
