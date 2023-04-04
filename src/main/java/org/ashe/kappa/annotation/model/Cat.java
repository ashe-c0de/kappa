package org.ashe.kappa.annotation.model;

@VeryImportant
public class Cat {

    @ImportantString
    public String name; // for invoke, must not be private
    @SuppressWarnings("unused")
    private Integer age;
    @VeryImportant
    public Cat(String name) {
        this.name = name;
    }

    @RunImmediately(times = 3)
    @SuppressWarnings("unused")
    public void meow() {
        System.out.println("Meow!");
    }
    @SuppressWarnings("unused")
    public void eat(){
        System.out.println("Munch");
    }
}
