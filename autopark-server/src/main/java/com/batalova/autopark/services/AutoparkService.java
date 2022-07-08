package com.batalova.autopark.services;

import com.batalova.autopark.dto.AutoDto;
import com.batalova.autopark.jdbc.AutoparkDao;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

public class AutoparkService {
    private final AutoparkDao autoparkDao;

    public AutoparkService(AutoparkDao autoparkDao) {
        this.autoparkDao = autoparkDao;
    }

    public int addAuto(AutoDto auto) throws HttpStatusCodeException {

        if (autoparkDao.findAutoByNumber(auto.getNumber()).size() < 1) {
            return autoparkDao.addAuto(auto);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Car with this number (" + auto.getNumber() + ") is already exists");
        }

    }
}
