package com.zomato.model;

import java.util.List;

public class Person {

	private String 				name;
	private String 				gender;
	private Person				spouse;
	private Person				father;
	private Person				mother;
	private List<Person>		children;
	
	public Person() {
		super();
	}

	public Person(String name, String gender, Person spouse, Person father, Person mother, List<Person> children) {
		super();
		this.name = name;
		this.gender = gender;
		this.spouse = spouse;
		this.father = father;
		this.mother = mother;
		this.children = children;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Person getSpouse() {
		return spouse;
	}

	public void setSpouse(Person spouse) {
		this.spouse = spouse;
	}

	public Person getFather() {
		return father;
	}

	public void setFather(Person father) {
		this.father = father;
	}

	public Person getMother() {
		return mother;
	}

	public void setMother(Person mother) {
		this.mother = mother;
	}

	public List<Person> getChildren() {
		return children;
	}

	public void setChildren(List<Person> children) {
		this.children = children;
	}
}
