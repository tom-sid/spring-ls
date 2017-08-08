package com.example.demodata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demodata.model.Greeting;

@Repository
public interface GreetingRepository extends JpaRepository<Greeting, Long> {

}
