package com.aaronrenner.spring.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.aaronrenner.spring.MySQLApplication;

public class CustomMapper implements RowMapper<Map<String, String>> {
	
	private List<String> keys;
	private static final Logger LOGGER = LoggerFactory.getLogger(MySQLApplication.class);
	
	public CustomMapper(List<String> fields) {
		this.keys = fields;
	}

	@Override
	public Map<String, String> mapRow(ResultSet rs, int rowNum) throws SQLException {
		// Build a new map to contain the K/V pairs in this row
		Map<String, String> data = new HashMap<>();
		// Loop through the keys we expect to see
		for(String s : keys) {
			// Key default is a string null
			String dataForField = null;
			try {
				// Actually get the data from the DB result
				dataForField = rs.getString(s);
			} catch (Exception e) {
				LOGGER.error(String.format("column %s not found", s));
			}
			// Add a single K/V to the overall response
			data.put(s, dataForField);
		}
		// Return all K/V pairs for this row
		return data;
	}

}
