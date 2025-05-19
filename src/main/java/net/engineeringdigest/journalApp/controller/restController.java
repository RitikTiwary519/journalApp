package net.engineeringdigest.journalApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class restController {
    @GetMapping("/health-checkup")
    public String getHealthCheckup()
    {
        return "Ok tested";
    }


}
