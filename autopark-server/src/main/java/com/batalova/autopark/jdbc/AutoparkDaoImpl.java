package com.batalova.autopark.jdbc;

import com.batalova.autopark.dto.AutoDto;
import com.batalova.autopark.dto.JournalDto;
import com.batalova.autopark.dto.PersonnelDto;
import com.batalova.autopark.dto.RouteDto;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Timestamp;
import java.sql.Types;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

public class AutoparkDaoImpl implements AutoparkDao {

    private final JdbcTemplate jdbcTemplate;

    public AutoparkDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addAuto(AutoDto autoDto) {
        PreparedStatementCreatorFactory preparedStatementCreatorFactory = new PreparedStatementCreatorFactory(
                "INSERT INTO auto (num, color, mark, personnel_id) VALUES (?, ?, ?, ?)",
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER
        );

        preparedStatementCreatorFactory.setReturnGeneratedKeys(true);

        PreparedStatementCreator preparedStatementCreator = preparedStatementCreatorFactory.newPreparedStatementCreator(
                Arrays.asList(
                        autoDto.getNumber(),
                        autoDto.getColor(),
                        autoDto.getMark(),
                        autoDto.getPersonnelId()
                )
        );

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                preparedStatementCreator,
                keyHolder);

        return (int)keyHolder.getKeys().get("id");
    }


    @Override
    public int addPersonnel(PersonnelDto personnelDto) {
        String request = "INSERT INTO auto_personnel (first_name, last_name, father_name) VALUES (?, ?, ?)";
        PreparedStatementCreatorFactory preparedStatementCreatorFactory = new PreparedStatementCreatorFactory(
                request,
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR
        );

        preparedStatementCreatorFactory.setReturnGeneratedKeys(true);

        PreparedStatementCreator preparedStatementCreator = preparedStatementCreatorFactory.newPreparedStatementCreator(
                Arrays.asList(
                        personnelDto.getFirstName(),
                        personnelDto.getLastName(),
                        personnelDto.getFatherName()
                )
        );


        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                preparedStatementCreator,
                keyHolder);

        return (int)keyHolder.getKeys().get("id");

    }

    @Override
    public int addRoute(RouteDto routeDto) {
        String request = "INSERT INTO routes (name) VALUES (?)";

        PreparedStatementCreatorFactory preparedStatementCreatorFactory = new PreparedStatementCreatorFactory(
                request,
                Types.VARCHAR
        );

        preparedStatementCreatorFactory.setReturnGeneratedKeys(true);

        PreparedStatementCreator preparedStatementCreator = preparedStatementCreatorFactory.newPreparedStatementCreator(
                Arrays.asList(
                        routeDto.getName()
                )
        );


        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                preparedStatementCreator,
                keyHolder);

        return (int)keyHolder.getKeys().get("id");
    }

    private int addJournal(JournalDto journalDto) {
        String request = "INSERT INTO journal (auto_id, route_id, time_in, time_out) VALUES (?, ?, ?, ?)";

        PreparedStatementCreatorFactory preparedStatementCreatorFactory = new PreparedStatementCreatorFactory(
                request,
                Types.INTEGER, Types.INTEGER, Types.TIMESTAMP, Types.TIMESTAMP
        );

        preparedStatementCreatorFactory.setReturnGeneratedKeys(true);

        PreparedStatementCreator preparedStatementCreator = preparedStatementCreatorFactory.newPreparedStatementCreator(
                Arrays.asList(
                        journalDto.getAutoId(),
                        journalDto.getRouteId(),
                        Timestamp.from(journalDto.getTimeIn()),
                        journalDto.getTimeOut().orElseGet(() -> null)
                )
        );


        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                preparedStatementCreator,
                keyHolder);

        return (int)keyHolder.getKeys().get("id");
    }

    @Override
    public List<AutoDto> deleteAuto(int autoId) {
        String request = "DELETE FROM auto WHERE id = ?";

        return jdbcTemplate.query(
                request,
                DataClassRowMapper.newInstance(AutoDto.class),
                autoId);
    }

    @Override
    public void deletePersonnel(int personnelId) {

        String request = "DELETE FROM auto_personnel WHERE id = ?";
        Object[] arguments = new Object[] {personnelId};

        jdbcTemplate.update(
                request,
                arguments);
    }

    @Override
    public List<RouteDto> deleteRoute(int routeId) {
        String request = "DELETE FROM routes WHERE id = ?";

        return jdbcTemplate.query(
                request,
                DataClassRowMapper.newInstance(RouteDto.class),
                routeId);
    }

    @Override
    public List<JournalDto> deleteJournal(int journalId) {
        String request = "DELETE FROM journal WHERE id = ?";

        return jdbcTemplate.query(
                request,
                DataClassRowMapper.newInstance(JournalDto.class),
                journalId);
    }

    @Override
    public List<AutoDto> findAutoByColor(String color) {
        String request = "SELECT * FROM auto WHERE color = ?";

        return jdbcTemplate.query(
                request,
                DataClassRowMapper.newInstance(AutoDto.class),
                color);
    }

    @Override
    public List<AutoDto> findAutoByMark(String mark) {
        String request = "SELECT * FROM auto WHERE mark = ?";

        return jdbcTemplate.query(
                request,
                DataClassRowMapper.newInstance(AutoDto.class),
                mark);
    }

    @Override
    public List<AutoDto> findAutoByNumber(String number) {
        String request = "SELECT * FROM auto WHERE num = ?";

        return jdbcTemplate.query(
                request,
                DataClassRowMapper.newInstance(AutoDto.class),
                number);

    }

    @Override
    public List<PersonnelDto> findPersonnelByFirstName(String firstName) {
        String request = "SELECT * FROM auto_personnel WHERE first_name = ?";

        return jdbcTemplate.query(
                request,
                DataClassRowMapper.newInstance(PersonnelDto.class),
                firstName);
    }

    @Override
    public List<PersonnelDto> findPersonnelByLastName(String lastName) {
        String request = "SELECT * FROM auto_personnel WHERE last_name = ?";

        return jdbcTemplate.query(
                request,
                DataClassRowMapper.newInstance(PersonnelDto.class),
                lastName);
    }

    @Override
    public List<PersonnelDto> findPersonnelByFatherName(String fatherName) {
        String request = "SELECT * FROM auto_personnel WHERE father_name = ?";

        return jdbcTemplate.query(
                request,
                DataClassRowMapper.newInstance(PersonnelDto.class),
                fatherName);
    }

    @Override
    public List<JournalDto> findUnfinishedRouteByAuto(String autoNumber) {
        String request = "SELECT j.* FROM auto AS a JOIN journal AS j ON a.id = j.auto_id " +
                "WHERE a.num = ? AND j.time_out IS NULL";

        return jdbcTemplate.query(
                request,
                DataClassRowMapper.newInstance(JournalDto.class),
                autoNumber);
    }

    @Override
    public List<JournalDto> findUnfinishedRouteByAutoId(int autoId) {
        String request = "SELECT * FROM journal WHERE auto_id = ? AND time_out IS NULL";

        return jdbcTemplate.query(
                request,
                DataClassRowMapper.newInstance(JournalDto.class),
                autoId);
    }

    @Override
    public Boolean isRouteFinished(int routeId) {
        String request = "SELECT * FROM journal WHERE route_id = ? AND time_out IS NULL";

        return jdbcTemplate.query(
                request,
                DataClassRowMapper.newInstance(JournalDto.class),
                routeId).size() < 1;
    }

    @Override
    public int startRoute(JournalDto journalDto) {
        if (findUnfinishedRouteByAutoId(journalDto.getAutoId()).size() < 1) {
            return addJournal(journalDto);
        } else {
            throw new RuntimeException("Auto with id: " + journalDto.getAutoId() + " is already on the route!");
        }
    }

    @Override
    public void finishRoute(int id, Instant timeOut) {
        if (!isRouteFinished(id)) {
            String request = "UPDATE journal SET time_out = ? WHERE id = ?";
            jdbcTemplate.update(request, Timestamp.from(timeOut), id);
        } else {
            throw new RuntimeException("Route with id: " + id + " is already exists!");
        }
    }

    @Override
    public List<RouteDto> findRouteByName(String routeName) {
        String request = "SELECT * FROM routes WHERE name = ?";

        return jdbcTemplate.query(
                request,
                DataClassRowMapper.newInstance(RouteDto.class),
                routeName);
    }

    @Override
    public void updateAutoColor(String color, String number) {
        String request = "UPDATE auto SET color =? WHERE num = ?";

        jdbcTemplate.update(request,
                color,
                number);
    }

    @Override
    public List<AutoDto> updateAutoNumber(String number, int id) {
        String request = "UPDATE auto SET number = ? WHERE id = ?";

        return jdbcTemplate.query(
                request,
                DataClassRowMapper.newInstance(AutoDto.class),
                number,
                id);
    }

    @Override
    public List<PersonnelDto> findPersonnelByFullName(String firstName, String lastName, String fatherName) {
        String request = "SELECT * FROM auto_personnel WHERE first_name = ? AND last_name = ? AND father_name =?";

        return jdbcTemplate.query(
                request,
                DataClassRowMapper.newInstance(PersonnelDto.class),
                firstName,
                lastName,
                fatherName);
    }

    @Override
    public List<PersonnelDto> updatePersonnelFirstName(String firstName, String lastName, String fatherName) {
        String request = "UPDATE auto_personnel SET first_name = ? WHERE last_name = ? AND father_name =?";

        return jdbcTemplate.query(
                request,
                DataClassRowMapper.newInstance(PersonnelDto.class),
                firstName,
                lastName,
                fatherName);
    }

    @Override
    public List<PersonnelDto> updatePersonnelLastName(String lastName, String firstName, String fatherName) {
        String request = "UPDATE auto_personnel SET last_name = ? WHERE first_name = ? AND father_name =?";

        return jdbcTemplate.query(
                request,
                DataClassRowMapper.newInstance(PersonnelDto.class),
                lastName,
                firstName,
                fatherName);
    }

    @Override
    public List<PersonnelDto> updatePersonnelFatherName(String firstName, String lastName, String fatherName) {
        String request = "UPDATE auto_personnel SET father_name = ? WHERE first_name = ? AND last_name = ?";

        return jdbcTemplate.query(
                request,
                DataClassRowMapper.newInstance(PersonnelDto.class),
                fatherName,
                firstName,
                lastName);
    }

    @Override
    public List<AutoDto> showAllAuto() {
        String request = "SELECT * FROM auto";

        return jdbcTemplate.query(
                request,
                DataClassRowMapper.newInstance(AutoDto.class));
    }

    @Override
    public List<PersonnelDto> showAllPersonnel() {
        String request = "SELECT * FROM auto_personnel";

        return jdbcTemplate.query(
                request,
                DataClassRowMapper.newInstance(PersonnelDto.class));
    }

    @Override
    public List<RouteDto> showAllRoute() {
        String request = "SELECT * FROM routes";

        return jdbcTemplate.query(
                request,
                DataClassRowMapper.newInstance(RouteDto.class));
    }

}
