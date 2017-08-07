package com.example.demo.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Greeting;

@RestController
public class GreetingController {

	private static Long nextId;

	private static Map<Long, Greeting> greetingsMap;

	private static Greeting save(Greeting greeting) {
		if (greetingsMap == null) {
			greetingsMap = new HashMap<>();
			nextId = 1L;
		}
		// If update
		if (greeting.getId() != null) {
			Greeting oldGreeting = greetingsMap.get(greeting.getId());
			if (oldGreeting == null) {
				return null;
			}
			greetingsMap.remove(greeting.getId());
			greetingsMap.put(greeting.getId(), greeting);
			return greeting;
		}

		// If create
		greeting.setId(nextId);
		greetingsMap.put(nextId, greeting);
		nextId++;
		return greeting;
	}

	private static boolean delete(Long id) {
		Greeting oldGreeting = greetingsMap.get(id);
		if (oldGreeting == null) {
			return false;
		}
		greetingsMap.remove(id);
		return true;
	}

	static {
		Greeting g1 = new Greeting();
		g1.setText("Hello World!");
		save(g1);

		Greeting g2 = new Greeting();
		g2.setText("Hola Mundo!");
		save(g2);
	}

	@RequestMapping(value = "/greetings", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Greeting>> getGreetings() {
		Collection<Greeting> greetings = greetingsMap.values();
		System.out.println(greetings);
		return new ResponseEntity<Collection<Greeting>>(greetings, HttpStatus.OK);
	}

	@RequestMapping(value = "/greetings/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> getGreeting(@PathVariable("id") Long id) {
		Greeting greeting = greetingsMap.get(id);
		if (greeting == null) {
			return new ResponseEntity<Greeting>(HttpStatus.NOT_FOUND);
		}
		System.out.println(greeting);
		return new ResponseEntity<Greeting>(greeting, HttpStatus.OK);
	}

	@RequestMapping(value = "/greetings", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> createGreeting(@RequestBody Greeting greeting) {
		Greeting savedGreeting = save(greeting);
		System.out.println(savedGreeting);
		return new ResponseEntity<Greeting>(savedGreeting, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/greetings", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> updateGreeting(@RequestBody Greeting greeting) {
		Greeting updatedGreeting = save(greeting);
		if (updatedGreeting == null) {
			return new ResponseEntity<Greeting>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		System.out.println(updatedGreeting);
		return new ResponseEntity<Greeting>(updatedGreeting, HttpStatus.OK);
	}

	@RequestMapping(value = "/greetings/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> deleteGreeting(@PathVariable("id") Long id, @RequestBody Greeting greeting) {
		boolean deleted = delete(id);
		if (!deleted) {
			return new ResponseEntity<Greeting>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		System.out.println(greeting);
		return new ResponseEntity<Greeting>(greeting, HttpStatus.OK);
	}
}
