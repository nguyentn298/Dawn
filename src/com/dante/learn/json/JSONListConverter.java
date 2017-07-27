package com.dante.learn.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JSONListConverter {
	public static void main(String[] args) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		// Set pretty printing of json
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

		// Define map which will be converted to JSON
		List<Person> personList = getPersons();

		// 1. Convert List of Person objects to JSON
		String arrayToJson = objectMapper.writeValueAsString(personList);
		System.out.println("1. Convert List of person objects to JSON :");
		System.out.println(arrayToJson);

		// 2. Convert JSON to List of Person objects
		// Define Custom Type reference for List<Person> type
		TypeReference<List<Person>> mapType = new TypeReference<List<Person>>() {};
		List<Person> jsonToPersonList = objectMapper.readValue(arrayToJson,
				mapType);
		System.out.println("\n2. Convert JSON to List of person objects :");

		// Print list of person objects output using Java 8
		// jsonToPersonList.forEach(System.out::println);
		for (Person person : jsonToPersonList) {
			System.out.println(person);
		}
	}

	@SuppressWarnings("serial")
	public static List<Person> getPersons() {
		List<Person> personList = new ArrayList<Person>() {
			{
				add(new Person("Mike", "harvey", 34));
				add(new Person("Nick", "young", 75));
				add(new Person("Jack", "slater", 21));
				add(new Person("gary", "hudson", 55));
			}
		};

		return personList;
	}
}
