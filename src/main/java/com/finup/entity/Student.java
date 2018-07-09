package com.finup.entity;

import java.util.List;
import lombok.Data;

/**
 * @author huangrongchao on 2018/4/27.
 * @version 1.0
 */
@Data
public class Student {

    public Student() {
    }

    public Student(int age, int grade, String name) {
        this.age = age;
        this.grade = grade;
        this.name = name;
    }

    public Student(int age, int grade, String name, List<Integer> elems) {
        this.age = age;
        this.grade = grade;
        this.name = name;
        this.elems = elems;
    }

    private int age;
    private int grade;
    private String name;
    private List<Integer> elems;
}
