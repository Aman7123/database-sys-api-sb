package com.aaronrenner.spring.repositories;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.aaronrenner.spring.models.ColumnNameMapper;
import com.aaronrenner.spring.models.CustomMapper;

@Component
public class DatabaseRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public DatabaseRepository() {}

	// GET all databases
	public List<Map<String, String>> getAllDatabases() {
		// Setup variables
		String DB_QUERY = "SELECT * FROM INFORMATION_SCHEMA.SCHEMATA";
		List<String> DB_FIELDS = getInfoSchemaColumnNames("SCHEMATA");
		// Build our line parser
		CustomMapper mapUtil = new CustomMapper(DB_FIELDS);
		// Make MySQL call and parse
		List<Map<String, String>> databases = jdbcTemplate.query(DB_QUERY, mapUtil);
		// Return parsed product
		return databases;
	}
	
	// GET all tables in a database
	public List<Map<String, String>> getAllTablesInDB(String dbName) {
		// Setup variables
		String DB_QUERY = String.format("SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = '%s' AND TABLE_TYPE = 'BASE TABLE'", dbName);
		List<String> DB_FIELDS = getInfoSchemaColumnNames("TABLES");
		// Build our line parser
		CustomMapper mapUtil = new CustomMapper(DB_FIELDS);
		// Make MySQL call and parse
		List<Map<String, String>> databases = jdbcTemplate.query(DB_QUERY, mapUtil);
		// Return parsed product
		return databases;
	}
	
	// GET all tables in a database
		public List<Map<String, String>> getAllEntriedInTable(String dbName, String tblName) {
			// Setup variables
			String DB_QUERY = String.format("SELECT * FROM `%s`.`%s`", dbName, tblName);
			List<String> DB_FIELDS = getColumnNames(dbName, tblName);
			// Build our line parser
			CustomMapper mapUtil = new CustomMapper(DB_FIELDS);
			// Make MySQL call and parse
			List<Map<String, String>> databases = jdbcTemplate.query(DB_QUERY, mapUtil);
			// Return parsed product
			return databases;
		}
	
	/**
	 * Private used to obtain column names in the INFORMATION_SCHEMA table
	 * @return
	 */
	private List<String> getInfoSchemaColumnNames(String tblName) {
		// Setup variables
		String DB_QUERY = String.format("SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '%s' AND TABLE_SCHEMA = 'information_schema'", tblName);
		// Make MySQL call and parse
		List<String> databases = jdbcTemplate.query(DB_QUERY, new ColumnNameMapper());
		// Return parsed product
		return databases;
	}
	
	/**
	 * Private used to obtain column names in the INFORMATION_SCHEMA table
	 * @return
	 */
	private List<String> getColumnNames(String dbName, String tblName) {
		// Setup variables
		String DB_QUERY = String.format("SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '%s' AND TABLE_SCHEMA = '%s'", tblName, dbName);
		// Make MySQL call and parse
		List<String> databases = jdbcTemplate.query(DB_QUERY, new ColumnNameMapper());
		// Return parsed product
		return databases;
	}
}
