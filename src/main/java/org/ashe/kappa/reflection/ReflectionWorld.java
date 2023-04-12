package org.ashe.kappa.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * reflection
 * 对于任意一个类，都能够知道这个类的所有属性和方法
 * 对于任意一个对象，都能够调用它的任意一个方法
 * 反射存在效率问题
 */
@SuppressWarnings("all")
public class ReflectionWorld {

    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
        Cat ashe = new Cat("Ashe", 25);
        Field[] fields = ashe.getClass().getDeclaredFields();
        for (Field field : fields) {
//            System.out.println(field.getName());
            if(field.getName().equals("name")){
                field.setAccessible(true);
                field.set(ashe, "wong"); // you can do anything by reflection on instance
            }
        }
        System.out.println(ashe.getName());

        Method[] methods = ashe.getClass().getDeclaredMethods();
        for(Method method : methods) {
            if (method.getName().equals("heyThisIsPrivate")) {
                method.setAccessible(true);
                method.invoke(ashe);
            }
        }
    }
}
