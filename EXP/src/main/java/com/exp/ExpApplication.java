package com.exp;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import com.exp.cassandra.Person;
import com.exp.cassandra.PersonKey;
import com.exp.cassandra.PersonRepository;

@SpringBootApplication
public class ExpApplication implements CommandLineRunner {
	
	@Autowired
	private PersonRepository personRepository;

	public static void main(String[] args) {
		SpringApplication.run(ExpApplication.class, args);
	}
	
	@Override
	  public void run(String... args) throws Exception {
	    final PersonKey key = new PersonKey("John", LocalDateTime.now(), UUID.randomUUID());
	    final Person p = new Person(key, "Doe", 1000);
	    personRepository.insert(p);

	    System.out.println("find by first name");
	    personRepository.findByKeyFirstName("John").forEach(System.out::println);

	    System.out.println("find by first name and date of birth greater than date");
	    personRepository
	        .findByKeyFirstNameAndKeyDateOfBirthGreaterThan("John", LocalDateTime.now().minusDays(1))
	        .forEach(System.out::println);

	    System.out.println("find by last name");
	    personRepository.findByLastName("Doe").forEach(System.out::println);
	  }

}
