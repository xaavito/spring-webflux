package com.prueba.spring_webflux.entity;

//import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//@AllArgsConstructor
public class Coffee {
	private String id;
    private String name;
    
	public Coffee(String string, String name2) {
		this.id = string;
		this.name = name2;
	}

	public String toString() {
		return this.id + " " + this.name;
	}
	
}