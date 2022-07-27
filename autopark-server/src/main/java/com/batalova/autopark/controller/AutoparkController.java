package com.batalova.autopark.controller;

import com.batalova.autopark.dto.AutoDto;
import com.batalova.autopark.dto.JournalDto;
import com.batalova.autopark.dto.PersonnelDto;
import com.batalova.autopark.dto.RouteDto;
import com.batalova.autopark.services.AutoparkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AutoparkController {

    private final AutoparkService autoparkService;

    public AutoparkController(AutoparkService autoparkService) {
        this.autoparkService = autoparkService;
    }

    @PostMapping(path = "/add-auto")
    public ResponseEntity<Integer> addAuto(
            @RequestParam(name = "personnel_id")
            int personnel_id,
            @RequestParam(name = "color")
            String color,
            @RequestParam(name = "num")
            String num,
            @RequestParam(name = "mark")
            String mark
    )
    {
        AutoDto newAuto = new AutoDto(Optional.empty(), personnel_id, color, num, mark);

        int id = autoparkService.addAuto(newAuto);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PostMapping(path = "/add-personnel")
    public ResponseEntity<Integer> addPersonnel(
            @RequestParam(name = "first_name")
            String first_name,
            @RequestParam(name = "last_name")
            String last_name,
            @RequestParam(name = "father_name")
            String father_name
    )
    {
        PersonnelDto newPersonnel = new PersonnelDto(Optional.empty(), first_name, last_name, father_name);

        int id = autoparkService.addPersonnel(newPersonnel);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PostMapping(path = "/add-route")
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
    public ResponseEntity<Void> deletePersonnel(
            @PathVariable(name = "id") int id
    )
    {
        autoparkService.deletePersonnel(id);

        return new ResponseEntity<>(HttpStatus.OK);
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
            @RequestParam(name = "num")
            String num
    )
    {
        List<AutoDto> autosByNumber = autoparkService.findAutoByNumber(num);

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
            @RequestParam(name = "first_name")
            String first_name
    )
    {
        List<PersonnelDto> findFirstName = autoparkService.findPersonnelByFirstName(first_name);

        return new ResponseEntity<>(findFirstName, HttpStatus.OK);
    }

    @GetMapping(value = "/find-personnel-by-last-name")
    public ResponseEntity<List<PersonnelDto>> findPersonnelByLastName(
            @RequestParam(name = "last_name")
            String last_name
    )
    {
        List<PersonnelDto> personnelByLastName = autoparkService.findPersonnelByLastName(last_name);

        return new ResponseEntity<>(personnelByLastName, HttpStatus.OK);
    }

    @GetMapping(value = "/find-personnel-by-father-name")
    public ResponseEntity<List<PersonnelDto>> findPersonnelByFatherName(
            @RequestParam(name = "father_name")
            String father_name
    )
    {
        List<PersonnelDto> personnelByFatherName = autoparkService.findPersonnelByFatherName(father_name);

        return new ResponseEntity<>(personnelByFatherName, HttpStatus.OK);
    }

    @GetMapping(value = "/find-route-by-name")
    public ResponseEntity<List<RouteDto>> findRouteByName(
            @RequestParam(name = "name")
            String name
    )
    {
        List<RouteDto> routeByName = autoparkService.findRouteByName(name);

        return new ResponseEntity<>(routeByName, HttpStatus.OK);
    }

    @GetMapping(value = "/find-unfinished-route-by-auto")
    public ResponseEntity<List<JournalDto>> findUnfinishedRouteByAuto(
            @RequestParam(name = "num")
            String num
    )
    {
        List<JournalDto> routeByAuto = autoparkService.findUnfinishedRouteByAuto(num);

        return new ResponseEntity<>(routeByAuto, HttpStatus.OK);
    }

    @GetMapping(value = "/find-unfinished-route-by-auto-id")
    public ResponseEntity<List<JournalDto>> findUnfinishedRouteByAutoId(
            @RequestParam(name = "auto_id")
            int auto_id
    )
    {
        List<JournalDto> routeByAutoId = autoparkService.findUnfinishedRouteByAutoId(auto_id);

        return new ResponseEntity<>(routeByAutoId, HttpStatus.OK);
    }

    @GetMapping(value = "/is-route-finished")
    public ResponseEntity<Boolean> isRouteFinished(
            @RequestParam(name = "route_id")
            int route_id
    )
    {
        Boolean isFinished = autoparkService.isRouteFinished(route_id);

        return new ResponseEntity<>(isFinished, HttpStatus.OK);
    }

    @GetMapping(value = "/finish-route-by-number")
    public ResponseEntity<Void> finishRouteByNumber(
            @RequestParam(name = "num")
            String num
    )
    {
        autoparkService.finishRouteByAutoNumber(num);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/start-route-by-auto-number-and-route-name")
    public ResponseEntity<Integer> startRouteByAutoNumberAndRouteName(
            @RequestParam(name = "num")
            String num,
            @RequestParam(name = "name")
            String name
    )
    {
        int startByAutoNumberAndRouteName = autoparkService.startRouteByAutoNumberAndRouteName(num, name);

        return new ResponseEntity<>(startByAutoNumberAndRouteName, HttpStatus.OK);
    }

    @PostMapping(value = "/update-auto-color")
    public ResponseEntity<Void> updateAutoColor(
            @RequestParam(name = "color")
            String color,
            @RequestParam(name = "num")
            String num
    )
    {
        autoparkService.updateAutoColor(color, num);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/update-auto-number")
    public ResponseEntity<List<AutoDto>> updateAutoNumber(
            @RequestParam(name = "old_number")
            String old_number,
            @RequestParam(name = "new_number")
            String new_number
    )
    {
        return new ResponseEntity<>(autoparkService.updateAutoNumber(old_number, new_number), HttpStatus.OK);
    }

    @GetMapping(value = "/find-personnel-by-full-name")
    public ResponseEntity<List<PersonnelDto>> findPersonnelByFullName(
            @RequestParam(name = "first_name")
            String first_name,
            @RequestParam(name = "last_name")
            String last_name,
            @RequestParam(name = "father_name")
            String father_name
    )
    {
        List<PersonnelDto> personnelByFullName = autoparkService.findPersonnelByFullName(first_name, last_name, father_name);

        return new ResponseEntity<>(personnelByFullName, HttpStatus.OK);
    }

    @PostMapping(value = "/update-personnel-first-name")
    public ResponseEntity<List<PersonnelDto>> updatePersonnelFirstName(
            @RequestParam(name = "old_first_name")
            String old_first_name,
            @RequestParam(name = "old_last_name")
            String old_last_name,
            @RequestParam(name = "old_father_name")
            String old_father_name,
            @RequestParam(name = "new_first_name")
            String new_first_name
    )
    {
        return new ResponseEntity<>(autoparkService.updatePersonnelFirstName(old_first_name, old_last_name, old_father_name, new_first_name), HttpStatus.OK);
    }

    @PostMapping(value = "/update-personnel-last-name")
    public ResponseEntity<List<PersonnelDto>> updatePersonnelLastName(
            @RequestParam(name = "old_first_name")
            String old_first_name,
            @RequestParam(name = "old_last_name")
            String old_last_name,
            @RequestParam(name = "old_father_name")
            String old_father_name,
            @RequestParam(name = "new_last_name")
            String new_last_name
    )
    {
        return new ResponseEntity<>(autoparkService.updatePersonnelLastName(old_first_name, old_last_name, old_father_name, new_last_name), HttpStatus.OK);
    }

    @PostMapping(value = "/update-personnel-father-name")
    public ResponseEntity<List<PersonnelDto>> updatePersonnelFatherName(
            @RequestParam(name = "old_first_name")
            String old_first_name,
            @RequestParam(name = "old_last_name")
            String old_last_name,
            @RequestParam(name = "old_father_name")
            String old_father_name,
            @RequestParam(name = "new_father_name")
            String new_father_name
    )
    {
        return new ResponseEntity<>(autoparkService.updatePersonnelFatherName(old_first_name, old_last_name, old_father_name, new_father_name), HttpStatus.OK);
    }

    @GetMapping(value = "/show-all-auto")
    public ResponseEntity<List<AutoDto>> showAllAuto()
    {
        List<AutoDto> autoDtoList = autoparkService.showAllAuto();

        return new ResponseEntity<>(autoDtoList, HttpStatus.OK);
    }

    @GetMapping(value = "/show-all-personnel")
    public ResponseEntity<List<PersonnelDto>> showAllPersonnel()
    {
        List<PersonnelDto> personnelDtoList = autoparkService.showAllPersonnel();

        return new ResponseEntity<>(personnelDtoList, HttpStatus.OK);
    }

    @GetMapping(value = "/show-all-route")
    public ResponseEntity<List<RouteDto>> showAllRoute()
    {
        List<RouteDto> routeDtoList = autoparkService.showAllRoute();

        return new ResponseEntity<>(routeDtoList, HttpStatus.OK);
    }

}
