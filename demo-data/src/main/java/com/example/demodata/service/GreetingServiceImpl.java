package com.example.demodata.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demodata.model.Greeting;
import com.example.demodata.repository.GreetingRepository;

@Service
public class GreetingServiceImpl implements GreetingService {

	@Autowired
	private GreetingRepository greetingRepository;

	@Override
	public Collection<Greeting> findAll() {
		return greetingRepository.findAll();
	}

	@Override
	public Greeting findOne(Long id) {
		return greetingRepository.findOne(id);
	}

	@Override
	public Greeting create(Greeting greeting) {
		if (greeting.getId() != null) {
			return null;
		}
		return greetingRepository.save(greeting);
	}

	@Override
	public Greeting update(Greeting greeting) {
		Greeting oldGreeting = greetingRepository.findOne(greeting.getId());
		if (oldGreeting == null) {
			return null;
		}
		return greetingRepository.save(greeting);
	}

	@Override
	public void delete(Long id) {
		greetingRepository.delete(id);
	}

}
