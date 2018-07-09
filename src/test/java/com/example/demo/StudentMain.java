package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.finup.entity.Student;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author huangrongchao on 2018/4/27.
 * @version 1.0
 */
public class StudentMain {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student(10, 20, "aaa", Arrays.asList(1, 2, 3)));
        students.add(new Student(12, 20, "bbb", Arrays.asList(1, 2, 3, 4)));
        students.add(new Student(4, 20, "ccc", Arrays.asList(7, 1, 2, 3)));

        Map<Boolean, List<Student>> collect = students.stream().collect(Collectors.partitioningBy(s -> s.getAge() > 10));
        System.out.println(JSON.toJSONString(collect));

        List<Student> collect1 = students.stream().collect(Collectors.toList());
        collect1.removeIf(student -> student.getAge() > 100);
        System.out.println(JSON.toJSONString(collect1));

        Map<String, Integer> collect2 = students.stream()
                .collect(Collectors.toMap(Student::getName, Student::getAge));
        System.out.println(JSON.toJSONString(collect2));

        long count = students.stream().map(Student::getElems).flatMap(x -> x.stream()).count();
        System.out.println(count);

        ArrayList<List<Integer>> map = map(students.stream(), Student::getElems);
        System.out.println(map);

        ArrayList<Student> filter = filter(students.stream(), student -> student.getAge() > 10);
        System.out.println(JSON.toJSONString(filter));

        students.forEach(student -> System.out.println(student.getElems()));

        int sum = students.parallelStream().mapToInt(Student::getAge).sum();
        System.out.println(sum);

        IntStream.range(2, 10).forEach(x -> System.out.println(x));

        List<String> longerNames = students.parallelStream()
                .filter(student -> student.getAge() > 10)
                .map(Student::getName)
                .collect(Collectors.toList());

        System.out.println(longerNames);

    }

    public static <T, R> ArrayList<R> map(Stream<T> source, Function<T, R> mapper) {
        return source.reduce(new ArrayList<R>(), (acc, value) -> {
            ArrayList<R> result = new ArrayList<>();
            result.addAll(acc);
            result.add(mapper.apply(value));
            return result;
        }, (left, right) -> {
            ArrayList<R> result = new ArrayList<>();
            result.addAll(left);
            result.addAll(right);
            return result;
        });
    }

    public static <T> ArrayList<T> filter(Stream<T> source, Predicate<T> predicate) {
        return source.reduce(new ArrayList<T>(), (acc, value) -> {
            ArrayList<T> result = new ArrayList<>();
            result.addAll(acc);
            if (predicate.test(value))
                result.add(value);
            return result;
        }, (left, right) -> {
            ArrayList<T> result = new ArrayList<>();
            result.addAll(left);
            result.addAll(right);
            return result;
        });
    }

}
