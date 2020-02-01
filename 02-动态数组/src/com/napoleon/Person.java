package com.napoleon;

public class Person {

	private int age;
	private String name;
	
	public Person(int age, String name) {
		this.age = age;
		this.name = name;
	}
	@Override
	public String toString() {
		return "Person [age=" + age + ", name=" + name + "]";
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj == null) {
			return false;
		}
		if (obj instanceof Person) {
			Person person = (Person) obj;
			return this.age == person.age;
		}
		return false;
	}
}
