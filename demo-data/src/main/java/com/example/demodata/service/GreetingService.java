package com.example.demodata.service;

import java.util.Collection;

import com.example.demodata.model.Greeting;

public interface GreetingService {
	Collection<Greeting> findAll();

	Greeting findOne(Long id);

	Greeting create(Greeting greeting);

	Greeting update(Greeting greeting);

	void delete(Long id);
}
