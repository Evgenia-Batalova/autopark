package com.batalova.autopark.jdbc;

import com.batalova.autopark.dto.AutoDto;
import com.batalova.autopark.dto.JournalDto;
import com.batalova.autopark.dto.PersonnelDto;
import com.batalova.autopark.dto.RouteDto;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.util.List;

public class AutoparkDaoImpl implements AutoparkDao {

    private final JdbcTemplate jdbcTemplate;

    public AutoparkDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addAuto(AutoDto autoDto) {
        String request = "INSERT INTO auto (num, color, mark, personnel_id) VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement preparedStatement = connection.prepareStatement(request);
                    ArgumentPreparedStatementSetter argumentPreparedStatementSetter =
                            new ArgumentPreparedStatementSetter(new Object[]{
                                    autoDto.getNumber(),
                                    autoDto.getColor(),
                                    autoDto.getMark(),
                                    autoDto.getPersonnelId()
                    });
                    argumentPreparedStatementSetter.setValues(preparedStatement);
                    return preparedStatement;
                },
                keyHolder);

        return keyHolder.getKey().intValue();
    }


    @Override
    public int addPersonnel(PersonnelDto personnelDto) {
        String request = "INSERT INTO personnel (firstName, lastName, fatherName) VALUES (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement preparedStatement = connection.prepareStatement(request);
                    ArgumentPreparedStatementSetter argumentPreparedStatementSetter =
                            new ArgumentPreparedStatementSetter(new Object[]{
                                    personnelDto.getFirstName(),
                                    personnelDto.getLastName(),
                                    personnelDto.getFatherName()
                            });
                    argumentPreparedStatementSetter.setValues(preparedStatement);
                    return preparedStatement;
                },
                keyHolder);

        return keyHolder.getKey().intValue();

    }

    @Override
    public int addRoute(RouteDto routeDto) {
        String request = "INSERT INTO routes (name) VALUES (?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement preparedStatement = connection.prepareStatement(request);
                    ArgumentPreparedStatementSetter argumentPreparedStatementSetter =
                            new ArgumentPreparedStatementSetter(new Object[]{
                                    routeDto.getName()
                            });
                    argumentPreparedStatementSetter.setValues(preparedStatement);
                    return preparedStatement;
                },
                keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public int addJournal(JournalDto journalDto) {
        String request = "INSERT INTO journal (autoId, routeId, timeIn, timeOut) VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement preparedStatement = connection.prepareStatement(request);
                    ArgumentPreparedStatementSetter argumentPreparedStatementSetter =
                            new ArgumentPreparedStatementSetter(new Object[]{
                                    journalDto.getAutoId(),
                                    journalDto.getRouteId(),
                                    journalDto.getTimeIn(),
                                    journalDto.getTimeOut()
                            });
                    argumentPreparedStatementSetter.setValues(preparedStatement);
                    return preparedStatement;
                },
                keyHolder);

        return keyHolder.getKey().intValue();
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
    public List<PersonnelDto> deletePersonnel(int personnelId) {
        String request = "DELETE FROM personnel WHERE id = ?";

        return jdbcTemplate.query(
                request,
                DataClassRowMapper.newInstance(PersonnelDto.class),
                personnelId);
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
        return null;
    }

    @Override
    public List<AutoDto> findAutoByMark(String color) {
        return null;
    }

    @Override
    public List<AutoDto> findAutoByNumber(String number) {
        String request = "SELECT * FROM auto WHERE num = ?";

        return jdbcTemplate.query(
                request,
                DataClassRowMapper.newInstance(AutoDto.class),
                number);

    }

}
