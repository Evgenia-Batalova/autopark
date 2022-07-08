package com.batalova.autopark.jdbc;

import com.batalova.autopark.dto.AutoDto;
import com.batalova.autopark.dto.JournalDto;
import com.batalova.autopark.dto.PersonnelDto;
import com.batalova.autopark.dto.RouteDto;

import java.util.List;


public interface AutoparkDao {

    int addAuto(AutoDto autoDto);

    int addPersonnel(PersonnelDto personnelDto);

    int addRoute(RouteDto routeDto);

    int addJournal(JournalDto journalDto);

    List<AutoDto> deleteAuto(int autoId);

    List<PersonnelDto> deletePersonnel(int personnelId);

    List<RouteDto> deleteRoute(int routeId);

    List<JournalDto> deleteJournal(int journalId);

    List<AutoDto> findAutoByColor(String color);

    List<AutoDto> findAutoByMark(String color);

    List<AutoDto> findAutoByNumber(String number);



}
