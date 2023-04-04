package org.ashe.kappa.reflection;

@SuppressWarnings("unused")
public class Cat {

    private final String name;
    private Integer age;

    public Cat(String name, Integer age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void meow(){
        System.out.println("Meow~Meow~");
    }

    private void heyThisIsPrivate(){
        System.out.println("How did you call this?");
    }

    public static void thisIsAPublicStaticMethod(){
        System.out.println("I'm public and static!");
    }

    private static void thisIsAPrivateStaticMethod(){
        System.out.println("Hey, I'm private and static");
    }
}
