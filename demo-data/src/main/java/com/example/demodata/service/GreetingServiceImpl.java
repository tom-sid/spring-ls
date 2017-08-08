package com.example.demodata.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.demodata.model.Greeting;
import com.example.demodata.repository.GreetingRepository;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class GreetingServiceImpl implements GreetingService {

	@Autowired
	private GreetingRepository greetingRepository;

	@Override
	public Collection<Greeting> findAll() {
		return greetingRepository.findAll();
	}

	@Override
	@Cacheable(value = "greetingsCache", key = "#id")
	public Greeting findOne(Long id) {
		return greetingRepository.findOne(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@CachePut(value = "greetingsCache", key = "#result.id")
	public Greeting create(Greeting greeting) {
		if (greeting.getId() != null) {
			return null;
		}
		Greeting savedGreeting = greetingRepository.save(greeting);

		// Illustrate Transaction rollback

		if (savedGreeting.getId() == 4L) {
			throw new RuntimeException();
		}

		return savedGreeting;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@CachePut(value = "greetingsCache", key = "#greeting.id")
	public Greeting update(Greeting greeting) {
		Greeting oldGreeting = greetingRepository.findOne(greeting.getId());
		if (oldGreeting == null) {
			return null;
		}
		return greetingRepository.save(greeting);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@CacheEvict(value = "greetingsCache", key = "#id")
	public void delete(Long id) {
		greetingRepository.delete(id);
	}

	@Override
	@CacheEvict(value = "greetingsCache", allEntries = true)
	public void evictCache() {
	}
}
