package com.aaronrenner.spring.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class ColumnNameMapper implements RowMapper<String> {

	@Override
	public String mapRow(ResultSet rs, int rowNum) throws SQLException {
		String newColName = rs.getString("COLUMN_NAME");
		return newColName;
	}

}
