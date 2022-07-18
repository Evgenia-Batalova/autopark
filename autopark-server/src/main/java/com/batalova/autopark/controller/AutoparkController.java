package com.batalova.autopark.controller;

import com.batalova.autopark.dto.AutoDto;
import com.batalova.autopark.dto.JournalDto;
import com.batalova.autopark.dto.PersonnelDto;
import com.batalova.autopark.dto.RouteDto;
import com.batalova.autopark.jdbc.AutoparkDao;
import com.batalova.autopark.services.AutoparkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Optional;

@RestController
public class AutoparkController {

    private final AutoparkService autoparkService;

    public AutoparkController(AutoparkService autoparkService) {
        this.autoparkService = autoparkService;
    }

    @GetMapping(path = "/add-auto")
    public ResponseEntity<Integer> addAuto(
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

        int id = autoparkService.addAuto(newAuto);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping(path = "/add-personnel")
    public ResponseEntity<Integer> addPersonnel(
            @RequestParam(name = "first-name")
            String firstName,
            @RequestParam(name = "last-name")
            String lastName,
            @RequestParam(name = "father-name")
            String fatherName
    )
    {
        PersonnelDto newPersonnel = new PersonnelDto(Optional.empty(), firstName, lastName, fatherName);

        int id = autoparkService.addPersonnel(newPersonnel);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping(path = "/add-route")
    public ResponseEntity<Integer> addRoute(
            @RequestParam(name = "name")
            String name
    )
    {
        RouteDto newRoute = new RouteDto(Optional.empty(), name);

        int id = autoparkService.addRoute(newRoute);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping(path = "/add-journal")
    public ResponseEntity<Integer> addJournal(
            @RequestParam(name = "auto-id")
            int autoId,
            @RequestParam(name = "route-id")
            int routeId,
            @RequestParam(name = "time-in")
            Instant timeIn,
            @RequestParam(name = "time-out")
            Optional<Instant> timeOut
    )
    {
        JournalDto newJournal = new JournalDto(Optional.empty(), autoId, routeId, timeIn, timeOut);

        int id = autoparkService.addJournal(newJournal);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }


}
