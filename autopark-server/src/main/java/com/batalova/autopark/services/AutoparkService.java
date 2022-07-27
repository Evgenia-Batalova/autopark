package com.batalova.autopark.services;

import com.batalova.autopark.dto.AutoDto;
import com.batalova.autopark.dto.JournalDto;
import com.batalova.autopark.dto.PersonnelDto;
import com.batalova.autopark.dto.RouteDto;
import com.batalova.autopark.jdbc.AutoparkDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

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

    public int addPersonnel(PersonnelDto personnel) {
        List<PersonnelDto> fullName = findPersonnelByFullName(personnel.getFirstName(), personnel.getLastName(), personnel.getFatherName());
        if (fullName.size() < 1) {
            return autoparkDao.addPersonnel(personnel);
        } else {
            throw new RuntimeException("Personnel with this name is already exists");
        }
    }

    public int addRoute(RouteDto route) {
        List<RouteDto> newRoute = findRouteByName(route.getName());
        if (newRoute.size() < 1) {
            return autoparkDao.addRoute(route);
        } else {
            throw new RuntimeException("Route with this name is already exists");
        }
    }

    public List<AutoDto> deleteAuto(int autoId) {
        return autoparkDao.deleteAuto((autoId));
    }

    public void deletePersonnel(int personnelId) {
        autoparkDao.deletePersonnel(personnelId);
    }

    public List<RouteDto> deleteRoute(int routeId) {
        return autoparkDao.deleteRoute(routeId);
    }

    public List<JournalDto> deleteJournal(int journalId) {
        return autoparkDao.deleteJournal(journalId);
    }

    public List<AutoDto> findAutoByColor(String color) {
        return autoparkDao.findAutoByColor(color);
    }

    public List<AutoDto> findAutoByMark(String mark) {
        return autoparkDao.findAutoByMark(mark);
    }

    public List<AutoDto> findAutoByNumber(String number) {
        return autoparkDao.findAutoByNumber(number);
    }

    public List<PersonnelDto> findPersonnelByFirstName(String firstName) {
        return autoparkDao.findPersonnelByFirstName(firstName);
    }

    public List<PersonnelDto> findPersonnelByLastName(String lastName) {
        return autoparkDao.findPersonnelByLastName(lastName);
    }

    public List<PersonnelDto> findPersonnelByFatherName(String fatherName) {
        return autoparkDao.findPersonnelByFatherName(fatherName);
    }

    public List<JournalDto> findUnfinishedRouteByAuto(String autoNumber) {
        return autoparkDao.findUnfinishedRouteByAuto(autoNumber);
    }

    public List<JournalDto> findUnfinishedRouteByAutoId(int autoId) {
        return autoparkDao.findUnfinishedRouteByAutoId(autoId);
    }

    public Boolean isRouteFinished(int routeId) {
        return autoparkDao.isRouteFinished(routeId);
    }

    public int startRoute(JournalDto journalDto) {
        return autoparkDao.startRoute(journalDto);
    }

    private void finishRoute(int id) {
        Instant timeOut = Instant.now();
        autoparkDao.finishRoute(id, timeOut);
    }

    public List<RouteDto> findRouteByName(String routeName) {
        return autoparkDao.findRouteByName(routeName);
    }

    public int startRouteByAutoNumberAndRouteName(String autoNum, String routeName) {
        List<AutoDto> autoDtoList = autoparkDao.findAutoByNumber(autoNum);
        int autoId;
        int routeId;
        if (autoDtoList.size() >= 1) {
            autoId = autoDtoList.get(0).getId().get();
        } else {
            throw new RuntimeException("Auto with number " + autoNum + " is absent");
        }
        List<RouteDto> routeDtoList = autoparkDao.findRouteByName(routeName);
        if (routeDtoList.size() >= 1) {
            routeId = routeDtoList.get(0).getId().get();
        } else {
            throw new RuntimeException("Route with name " + routeName + " is absent");
        }
        return autoparkDao.startRoute(new JournalDto(Optional.empty(), autoId, routeId, Instant.now(), Optional.empty()));
    }

    public ResponseEntity<Void> finishRouteByAutoNumber(String autoNum) {
        List<AutoDto> autoDtoList = autoparkDao.findAutoByNumber(autoNum);
        int autoId;
        int routeId;
        if (autoDtoList.size() >= 1) {
            autoId = autoDtoList.get(0).getId().get();
        } else {
            throw new RuntimeException("Auto with number " + autoNum + " is absent");
        }
        List<JournalDto> journalDtoList = autoparkDao.findUnfinishedRouteByAutoId(autoId);
        if (journalDtoList.size() >= 1) {
            routeId = journalDtoList.get(0).getRouteId();
            finishRoute(routeId);
        } else {
            throw new RuntimeException("There are no active routes for auto with number " + autoNum);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public void updateAutoColor(String color, String num) {
        autoparkDao.updateAutoColor(color, num);
    }

    public List<AutoDto> updateAutoNumber(String oldNumber, String newNumber){
        List<AutoDto> oldAutoDto = findAutoByNumber(oldNumber);
        if (oldAutoDto.size() >= 1) {
            Integer id = oldAutoDto.get(0).getId().get();
            return autoparkDao.updateAutoNumber(newNumber, id);
        } else {
            throw new RuntimeException("There is no auto with this number " + oldNumber);
        }

    }

    public List<PersonnelDto> findPersonnelByFullName(String firstName, String lastName, String fatherName) {
        return autoparkDao.findPersonnelByFullName(firstName,lastName, fatherName);
    }

    public List<PersonnelDto> updatePersonnelFirstName(String oldFirstName, String oldLastName, String oldFatherName, String newFirstName) {
        List<PersonnelDto> oldFullName = findPersonnelByFullName(oldFirstName, oldLastName, oldFatherName);
        List<PersonnelDto> newFullName = findPersonnelByFullName(newFirstName, oldLastName, oldFatherName);
        if (oldFullName.size() >= 1) {
            if (newFullName.size() < 1) {
                return autoparkDao.updatePersonnelFirstName(newFirstName, oldLastName, oldFatherName);
            } else {
                throw new RuntimeException("Personnel with name " + newFirstName + oldLastName + oldFatherName + " is already exists");
            }
        } else {
            throw new RuntimeException("There is no personnel with this name");
        }
    }

    public List<PersonnelDto> updatePersonnelLastName(String oldFirstName, String oldLastName, String oldFatherName, String newLastName) {
        List<PersonnelDto> oldFullName = findPersonnelByFullName(oldFirstName, oldLastName, oldFatherName);
        List<PersonnelDto> newFullName = findPersonnelByFullName(oldFirstName, newLastName, oldFatherName);
        if (oldFullName.size() >= 1) {
            if (newFullName.size() < 1) {
                return autoparkDao.updatePersonnelLastName(newLastName, oldFirstName, oldFatherName);
            } else {
                throw new RuntimeException("Personnel with name " + oldFirstName + newLastName + oldFatherName + " is already exists");
            }
        } else {
            throw new RuntimeException("There is no personnel with this name");
        }
    }

    public List<PersonnelDto> updatePersonnelFatherName(String oldFirstName, String oldLastName, String oldFatherName, String newFatherName) {
        List<PersonnelDto> oldFullName = findPersonnelByFullName(oldFirstName, oldLastName, oldFatherName);
        List<PersonnelDto> newFullName = findPersonnelByFullName(oldFirstName, oldLastName, newFatherName);
        if (oldFullName.size() >= 1) {
            if (newFullName.size() < 1) {
                return autoparkDao.updatePersonnelFatherName(newFatherName, oldFirstName, oldLastName);
            } else {
                throw new RuntimeException("Personnel with name " + oldFirstName + oldLastName + newFatherName + " is already exists");
            }
        } else {
            throw new RuntimeException("There is no personnel with this name");
        }
    }

    public List<AutoDto> showAllAuto() {
        return autoparkDao.showAllAuto();
    }

    public List<PersonnelDto> showAllPersonnel() {
        return autoparkDao.showAllPersonnel();
    }

    public List<RouteDto> showAllRoute() {
        return autoparkDao.showAllRoute();
    }
}