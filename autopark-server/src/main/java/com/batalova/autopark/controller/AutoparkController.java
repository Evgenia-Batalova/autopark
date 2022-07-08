package com.batalova.autopark.controller;

import com.batalova.autopark.dto.AutoDto;
import com.batalova.autopark.dto.RouteDto;
import com.batalova.autopark.jdbc.AutoparkDao;
import com.batalova.autopark.services.AutoparkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AutoparkController {

    private final AutoparkService autoparkService;

    public AutoparkController(AutoparkService autoparkService) {
        this.autoparkService = autoparkService;
    }

    @GetMapping(path = "/add-auto")
    public ResponseEntity<String> addAuto(
            @RequestParam(name = "person-id")
            int personId,
            @RequestParam(name = "color")
            String color,
            @RequestParam(name = "number")
            String number,
            @RequestParam(name = "mark")
            String mark
    )
    {
        AutoDto newAuto = new AutoDto(Optional.empty(), personId, color, number, mark);

        autoparkService.addAuto(newAuto);

        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

    @GetMapping(path = "/add-route")
    public ResponseEntity<String> addAuto(
            @RequestParam(name = "name")
            String name
    )
    {
        RouteDto newRoute = new RouteDto(Optional.empty(), name);

//        autoparkService.addRoute(newRoute);

        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }

}
