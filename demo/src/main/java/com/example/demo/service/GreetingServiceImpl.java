package com.example.demo.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

import com.example.demo.model.Greeting;

@Service
public class GreetingServiceImpl implements GreetingService {

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

	private static boolean remove(Long id) {
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

	@Override
	public Collection<Greeting> findAll() {
		return greetingsMap.values();
	}

	@Override
	public Greeting findOne(Long id) {
		return greetingsMap.get(id);
	}

	@Override
	public Greeting create(Greeting greeting) {
		return save(greeting);
	}

	@Override
	public Greeting update(Greeting greeting) {
		return save(greeting);
	}

	@Override
	public boolean delete(Long id) {
		return remove(id);
	}

}
