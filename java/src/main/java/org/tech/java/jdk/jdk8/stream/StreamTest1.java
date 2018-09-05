package org.tech.java.jdk.jdk8.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest1 {

    @Test
    public void test() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        list = list.stream().map(i -> i * i).collect(Collectors.toList());
        System.out.println(list);
    }

    @Test
    public void map() {
        User u1 = new User("yjp", 31, 88.5);
        User u2 = new User("yjp2", 32, 58.5);
        User u3 = new User("yjp3", 33, 98.5);
        List<User> list = Arrays.asList(u1, u2, u3);
        List<String> result = list.stream().map(u -> u.getName()).collect(Collectors.toList());
        System.out.println("名称："+result);
        DoubleSummaryStatistics sts = list.stream().mapToDouble(u -> u.getScore()).summaryStatistics();
        System.out.println("average："+sts.getAverage());
        System.out.println("max："+sts.getMax());
        System.out.println("min："+sts.getMin());
    }

    class User {
        private String name;
        private Integer age;
        private Double score;

        public User(String name, Integer age, Double score) {
            this.name = name;
            this.age = age;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Double getScore() {
            return score;
        }

        public void setScore(Double score) {
            this.score = score;
        }
    }
}


