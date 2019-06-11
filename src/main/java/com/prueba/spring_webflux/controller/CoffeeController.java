package com.prueba.spring_webflux.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.spring_webflux.entity.Coffee;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@RestController
@Slf4j
public class CoffeeController {

	@Autowired
	private MessageSource messageSource;

	private final ReactiveRedisOperations<String, Coffee> coffeeOps;

	CoffeeController(ReactiveRedisOperations<String, Coffee> coffeeOps) {
		this.coffeeOps = coffeeOps;
	}

	@GetMapping("/coffees")
    public Flux<Coffee> all() {
    	log.info("...@coffeeeess");
    	//ESTOS SON LOS IDS, YO NECESITARIA LOS OBJETOS POSTA PERO RE VA
    	coffeeOps.keys("*").subscribe(
    			  value -> System.out.println(value.toString()), 
    			  error -> error.printStackTrace(), 
    			  () -> System.out.println("completed without a value")
    			);	
    	
    	System.out.println(messageSource.getMessage("errormsg.name",
                null, Locale.GERMAN));
        return coffeeOps.keys("*")
                .flatMap(coffeeOps.opsForValue()::get);
    }
}