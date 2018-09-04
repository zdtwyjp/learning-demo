package org.tech.java.jdk.jdk8.methodReference;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

public class MethodTest1 {

    /**
     * 引用静态方法
     */
    @Test
    public void test() {
        Person[] persons = new Person[1];
        Person p = new Person();
        p.name = "Test";
        p.emailAddress = "qq.com";
        p.gender = Person.Sex.FEMALE;
        p.birthday = new Date();
        persons[0] = p;
        // 使用匿名内部类
        Arrays.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.birthday.compareTo(o2.birthday);
            }
        });
        // 使用lambda表达式
        Arrays.sort(persons, (o1, o2) -> o1.birthday.compareTo(o2.birthday));

        // 使用Lambda表达式和类的静态方法
        Arrays.sort(persons, ((o1, o2) -> Person.compareByAge(o1, o2)));

        // 使用方法引用
        Arrays.sort(persons, Person::compareByAge);
    }

    /**
     * 引用对象的实例方法
     */
    @Test
    public void test2() {
        Person[] persons = new Person[1];
        Person p = new Person();
        p.name = "Test";
        p.emailAddress = "qq.com";
        p.gender = Person.Sex.FEMALE;
        p.birthday = new Date();
        persons[0] = p;

        ComparisonProvider provider = new ComparisonProvider();

        //使用lambda表达式
        //对象的实例方法
        Arrays.sort(persons, (a, b) -> provider.compareByAge(a, b));

        //使用方法引用
        //引用的是对象的实例方法
        Arrays.sort(persons, provider::compareByAge);
    }

    /**
     * 引用类型对象的实例方法
     */
    @Test
    public void test3() {
        String[] stringsArray = {"Hello","World"};

        //使用lambda表达式和类型对象的实例方法
        Arrays.sort(stringsArray,(s1,s2)->s1.compareToIgnoreCase(s2));

        //使用方法引用
        //引用的是类型对象的实例方法
        Arrays.sort(stringsArray, String::compareToIgnoreCase);
    }

    /**
     * 引用构造方法
     */
    @Test
    public void test4() {

    }

}

class ComparisonProvider {
    public int compareByName(Person a, Person b) {
        return a.getName().compareTo(b.getName());
    }

    public int compareByAge(Person a, Person b) {
        return a.getBirthday().compareTo(b.getBirthday());
    }
}

class Person {
    public enum Sex {
        MALE, FEMALE
    }

    String name;
    Date birthday;
    Sex gender;
    String emailAddress;

    public String getEmailAddress() {
        return emailAddress;
    }

    public Sex getGender() {
        return gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getName() {
        return name;
    }

    public static int compareByAge(Person a, Person b) {
        return a.birthday.compareTo(b.birthday);
    }

}
