package com.batalova.autopark.jdbc;

import com.batalova.autopark.dto.AutoDto;
import com.batalova.autopark.dto.JournalDto;
import com.batalova.autopark.dto.PersonnelDto;
import com.batalova.autopark.dto.RouteDto;

import java.time.Instant;
import java.util.List;


public interface AutoparkDao {

    int addAuto(AutoDto autoDto);

    int addPersonnel(PersonnelDto personnelDto);

    int addRoute(RouteDto routeDto);

    List<AutoDto> deleteAuto(int autoId);

    List<PersonnelDto> deletePersonnel(int personnelId);

    List<RouteDto> deleteRoute(int routeId);

    List<JournalDto> deleteJournal(int journalId);

    List<AutoDto> findAutoByColor(String color);

    List<AutoDto> findAutoByMark(String color);

    List<AutoDto> findAutoByNumber(String number);

    List<PersonnelDto> findPersonnelByFirstName(String firstName);

    List<PersonnelDto> findPersonnelByLastName(String lastName);

    List<PersonnelDto> findPersonnelByFatherName(String fatherName);

    List<JournalDto> findUnfinishedRouteByAuto(String autoNumber);

    List<JournalDto> findUnfinishedRouteByAutoId(int autoId);

    Boolean isRouteFinished(int routeId);
    int startRoute(JournalDto journalDto);

    void finishRoute(int id, Instant timeOut);

    List<RouteDto> findRouteByName(String routeName);

    List<AutoDto> updateAutoColor(String color, String number);

    List<AutoDto> updateAutoNumber(String number, int id);

    List<PersonnelDto> findPersonnelByFullName(String firstName, String lastName, String fatherName);

    List<PersonnelDto> updatePersonnelFirstName(String firstName, String lastName, String fatherName);

    List<PersonnelDto> updatePersonnelLastName(String lastName, String firstName, String fatherName);

    List<PersonnelDto> updatePersonnelFatherName(String fatherName, String firstName, String lastName);


}
