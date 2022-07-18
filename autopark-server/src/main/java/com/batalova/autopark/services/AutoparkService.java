package com.batalova.autopark.services;

import com.batalova.autopark.dto.AutoDto;
import com.batalova.autopark.dto.JournalDto;
import com.batalova.autopark.dto.PersonnelDto;
import com.batalova.autopark.dto.RouteDto;
import com.batalova.autopark.jdbc.AutoparkDao;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;

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
    public int addPersonnel(PersonnelDto personnel){
        return autoparkDao.addPersonnel(personnel);
    }

    public int addRoute(RouteDto route){
        return autoparkDao.addRoute(route);
    }

    public int addJournal(JournalDto journal){
        return autoparkDao.addJournal(journal);
    }

    public List<AutoDto> deleteAuto(int autoId){
        return autoparkDao.deleteAuto((autoId));
    }

    public List<PersonnelDto> deletePersonnel(int personnelId){
        return autoparkDao.deletePersonnel(personnelId);
    }

    public List<RouteDto> deleteRoute(int routeId){
        return autoparkDao.deleteRoute(routeId);
    }

    public List<JournalDto> deleteJournal(int journalId){
        return autoparkDao.deleteJournal(journalId);
    }

    public List<AutoDto> findAutoByColor(String color){
        return autoparkDao.findAutoByColor(color);
    }

    public List<AutoDto> findAutoByMark(String mark){
        return autoparkDao.findAutoByMark(mark);
    }

    public List<AutoDto> findAutoByNumber(String number){
        return autoparkDao.findAutoByNumber(number);
    }

    public List<PersonnelDto> findPersonnelByFirstName(String firstName){
        return autoparkDao.findPersonnelByFirstName(firstName);
    }

    public List<PersonnelDto> findPersonnelByLastName(String lastName){
        return autoparkDao.findPersonnelByLastName(lastName);
    }

    public List<PersonnelDto> findPersonnelByFatherName(String fatherName){
        return autoparkDao.findPersonnelByFatherName(fatherName);
    }

    public List<JournalDto> findUnfinishedRouteByAuto(String autoNumber){
        return autoparkDao.findUnfinishedRouteByAuto(autoNumber);
    }

    public List<JournalDto> findUnfinishedRouteByAutoId(int autoId){
        return autoparkDao.findUnfinishedRouteByAutoId(autoId);
    }

    public Boolean isRouteFinished(int routeId){
        return autoparkDao.isRouteFinished(routeId);
    }

    public int startRoute(JournalDto journalDto){
        return autoparkDao.startRoute(journalDto);
    }

    public void finishRoute(int id, Instant timeOut){
        autoparkDao.finishRoute(id, timeOut);
    }
}
