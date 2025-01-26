package com.effective.chapter02.item01;

public class AnimalFactory {
    public static Animal getAnimal(String type){
        if(type.equalsIgnoreCase("cat")){
            return new Cat();
        }
        if(type.equalsIgnoreCase("dog")){
            return new Dog();
        }
        throw  new IllegalArgumentException(type + "없음");
    }

    public static void main(String[] args) {
        Animal cat = AnimalFactory.getAnimal("cat");
        cat.makeSound();
    }
}
