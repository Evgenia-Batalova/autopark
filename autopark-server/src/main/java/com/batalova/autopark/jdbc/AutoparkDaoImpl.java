package com.batalova.autopark.jdbc;

import com.batalova.autopark.dto.AutoDto;
import org.springframework.jdbc.core.JdbcTemplate;

public class AutoparkDaoImpl implements AutoparkDao {

    private final JdbcTemplate jdbcTemplate;

    public AutoparkDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addAuto(AutoDto autoDto) {
        String request = "INSERT INTO auto (num, color, mark, personnel_id) values (?, ?, ?, ?)";

        jdbcTemplate
                .update(request, autoDto.getNumber(), autoDto.getColor(), autoDto.getMark(), autoDto.getPersonnelId());
    }
}
