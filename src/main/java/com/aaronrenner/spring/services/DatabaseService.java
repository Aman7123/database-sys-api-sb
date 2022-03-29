package com.aaronrenner.spring.services;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aaronrenner.spring.repositories.DatabaseRepository;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

@Service
public class DatabaseService {
	
	@Autowired
	DatabaseRepository dbRepository;
	
	// GET all databases
	public String getAllDatabases() {
		// Get an array of objects containing K/V pairs for rows
		List<Map<String, String>> dbList = dbRepository.getAllDatabases();
		// Return the raw JSON string
		return buildReturnItem(dbList).toJSONString();
	}
	
	// Get all tables in DB
	public String getAllTablesInDB(String dbName) {
		// Get an array of objects containing K/V pairs for rows
		List<Map<String, String>> dbList = dbRepository.getAllTablesInDB(dbName);
		// Return the raw JSON string
		return buildReturnItem(dbList).toJSONString();
	}
	
	// Get all tables in DB
	public String getAllEntriesInTable(String dbName, String tblName) {
		// Get an array of objects containing K/V pairs for rows
		List<Map<String, String>> dbList = dbRepository.getAllEntriedInTable(dbName, tblName);
		// Return the raw JSON string
		return buildReturnItem(dbList).toJSONString();
	}
	
	private JSONArray buildReturnItem(List<Map<String, String>> arrOfObj) {
		// Response is arr of obj
		JSONArray toReturn = new JSONArray();
		// Loop through each object in the base list
		arrOfObj.forEach(data -> {
			// Each Object needs a proper JSONObject
			JSONObject objEntry = new JSONObject();
			// Loop through K/V pairs in the map, known as "entry" items
			for(Entry<String, String> e : data.entrySet()) {
				// Proper JSON uses camelCase for keys, this will do for now
				String getKey   = e.getKey().toLowerCase();
				// Get value
				String getValue = e.getValue();
				// Add K/V to this object
				objEntry.put(getKey, getValue);
			}
			// Add the rows object to the array
			toReturn.add(objEntry);
		});
		return toReturn;
	}

}
