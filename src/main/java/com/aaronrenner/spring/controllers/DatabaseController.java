package com.aaronrenner.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.aaronrenner.spring.services.DatabaseService;

@RestController
public class DatabaseController {
	
	@Autowired
	DatabaseService dbService;

	private final String CONTENT_TYPE = "application/json";
	private final String BASE_PATH = "";
	private final String DATABASE_PATH = "/{databaseName}";
	private final String TABLE_PATH = "/{tableName}";
	
	@GetMapping(path = BASE_PATH, produces = CONTENT_TYPE)
	public String getAllDatabases() {
		return dbService.getAllDatabases();
	}
	
	@GetMapping(path = {DATABASE_PATH, DATABASE_PATH + TABLE_PATH}, produces = CONTENT_TYPE)
	public Object getAllTables(@PathVariable String databaseName, @PathVariable(required = false) String tableName) throws Exception {
		if(isForbidden(databaseName)) throw new Exception("Someone tried GET on a forbidden database");
		if(tableName == null) return dbService.getAllTablesInDB(databaseName);
		return dbService.getAllEntriesInTable(databaseName, tableName);
	}
	
	public boolean isForbidden(String databaseName) {
		String[] forbiddenTables = {"mysql", "sys", "information_schema", "keycloak", "sonar", "keys"};
		boolean isForbidden = false;
		for(String forbiddenWord : forbiddenTables) {
			if(forbiddenWord.equalsIgnoreCase(databaseName)) isForbidden = true;
		}
		return isForbidden;
	}
}
