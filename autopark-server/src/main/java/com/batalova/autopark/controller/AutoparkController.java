package com.batalova.autopark.controller;

import com.batalova.autopark.dto.AutoDto;
import com.batalova.autopark.dto.JournalDto;
import com.batalova.autopark.dto.PersonnelDto;
import com.batalova.autopark.dto.RouteDto;
import com.batalova.autopark.jdbc.AutoparkDao;
import com.batalova.autopark.services.AutoparkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
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

    @DeleteMapping(value = "/delete-route/{id}")
    public ResponseEntity<List<RouteDto>> deleteRoute(
            @PathVariable int id
    )
    {

        List<RouteDto> removedRoutes = autoparkService.deleteRoute(id);

        return new ResponseEntity<>(removedRoutes, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete-personnel/{id}")
    public ResponseEntity<List<PersonnelDto>> deletePersonnel(
            @PathVariable int id
    )
    {

        List<PersonnelDto> removedPersonnel = autoparkService.deletePersonnel(id);

        return new ResponseEntity<>(removedPersonnel, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete-auto/{id}")
    public ResponseEntity<List<AutoDto>> deleteAuto(
            @PathVariable int id
    )
    {

        List<AutoDto> removedAuto = autoparkService.deleteAuto(id);

        return new ResponseEntity<>(removedAuto, HttpStatus.OK);
    }

    @GetMapping(value = "/find-auto-by-color")
    public ResponseEntity<List<AutoDto>> findAutoByColor(
            @RequestParam(name = "color")
            String color
    )
    {
        List<AutoDto> autosByColor = autoparkService.findAutoByColor(color);

        return new ResponseEntity<>(autosByColor, HttpStatus.OK);
    }

    @GetMapping(value = "/find-auto-by-number")
    public ResponseEntity<List<AutoDto>> findAutoByNumber(
            @RequestParam(name = "number")
            String number
    )
    {
        List<AutoDto> autosByNumber = autoparkService.findAutoByNumber(number);

        return new ResponseEntity<>(autosByNumber, HttpStatus.OK);
    }

    @GetMapping(value = "/find-auto-by-mark")
    public ResponseEntity<List<AutoDto>> findAutoByMark(
            @RequestParam(name = "mark")
            String mark
    )
    {
        List<AutoDto> autosByMark = autoparkService.findAutoByMark(mark);

        return new ResponseEntity<>(autosByMark, HttpStatus.OK);
    }

    @GetMapping(value = "/find-personnel-by-first-name")
    public ResponseEntity<List<PersonnelDto>> findPersonnelByFirstName(
            @RequestParam(name = "firstName")
            String firstName
    )
    {
        List<PersonnelDto> findFirstName = autoparkService.findPersonnelByFirstName(firstName);

        return new ResponseEntity<>(findFirstName, HttpStatus.OK);
    }

    @GetMapping(value = "/find-personnel-by-last-name")
    public ResponseEntity<List<PersonnelDto>> findPersonnelByLastName(
            @RequestParam(name = "lastName")
            String lastName
    )
    {
        List<PersonnelDto> personnelByLastName = autoparkService.findPersonnelByLastName(lastName);

        return new ResponseEntity<>(personnelByLastName, HttpStatus.OK);
    }

    @GetMapping(value = "/find-personnel-by-father-name")
    public ResponseEntity<List<PersonnelDto>> findPersonnelByFatherName(
            @RequestParam(name = "fatherName")
            String fatherName
    )
    {
        List<PersonnelDto> personnelByFatherName = autoparkService.findPersonnelByFatherName(fatherName);

        return new ResponseEntity<>(personnelByFatherName, HttpStatus.OK);
    }

    @GetMapping(value = "/find-route-by-name")
    public ResponseEntity<List<RouteDto>> findRouteByName(
            @RequestParam(name = "routeName")
            String routeName
    )
    {
        List<RouteDto> routeByName = autoparkService.findRouteByName(routeName);

        return new ResponseEntity<>(routeByName, HttpStatus.OK);
    }

    @GetMapping(value = "/find-unfinished-route-by-auto")
    public ResponseEntity<List<JournalDto>> findUnfinishedRouteByAuto(
            @RequestParam(name = "autoNumber")
            String autoNumber
    )
    {
        List<JournalDto> routeByAuto = autoparkService.findUnfinishedRouteByAuto(autoNumber);

        return new ResponseEntity<>(routeByAuto, HttpStatus.OK);
    }

    @GetMapping(value = "/find-unfinished-route-by-auto-id")
    public ResponseEntity<List<JournalDto>> findUnfinishedRouteByAutoId(
            @RequestParam(name = "autoId")
            int autoId
    )
    {
        List<JournalDto> routeByAutoId = autoparkService.findUnfinishedRouteByAutoId(autoId);

        return new ResponseEntity<>(routeByAutoId, HttpStatus.OK);
    }

    @GetMapping(value = "/is-route-finished")
    public ResponseEntity<Boolean> isRouteFinished(
            @RequestParam(name = "routeId")
            int routeId
    )
    {
        Boolean isFinished = autoparkService.isRouteFinished(routeId);

        return new ResponseEntity<>(isFinished, HttpStatus.OK);
    }

    @GetMapping(value = "/start-route")
    public ResponseEntity<Integer> startRoute(
            @RequestParam(name = "journalDto")
            JournalDto journalDto
    )
    {
        int start = autoparkService.startRoute(journalDto);

        return new ResponseEntity<>(start, HttpStatus.OK);
    }

    @GetMapping(value = "/finish-route")
    public ResponseEntity<Void> finishRoute()
    {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "start-route-by-auto-number-and-route-name")
    public ResponseEntity<Integer> startRouteByAutoNumberAndRouteName(
            @RequestParam(name = "autoNum")
            String autoNum,
            @RequestParam(name = "routeName")
            String routeName
    )
    {
        int startByAutoNumberAndRouteName = autoparkService.startRouteByAutoNumberAndRouteName(autoNum, routeName);

        return new ResponseEntity<>(startByAutoNumberAndRouteName, HttpStatus.OK);
    }

}
