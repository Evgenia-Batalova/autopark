package com.batalova.autopark.controller;

import com.batalova.autopark.dto.AutoDto;
import com.batalova.autopark.jdbc.AutoparkDao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AutoparkController {

    private final AutoparkDao autoparkDao;

    public AutoparkController(AutoparkDao autoparkDao) {
        this.autoparkDao = autoparkDao;
    }

    @GetMapping(path = "/add-auto")
    public void addAuto(
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

        autoparkDao.addAuto(newAuto);
    }


}
