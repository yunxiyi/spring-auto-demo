package com.example.demo;

import com.alibaba.fastjson.JSON;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author huangrongchao on 2018/4/27.
 * @version 1.0
 */
public class lamba {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("I", "love", "you");
        Optional<String> reduce = list.stream().reduce((s1, s2) -> s1.length() > s2.length() ? s1 : s2);
        System.out.println(reduce.get());
        String collect = list.stream().collect(Collectors.joining(","));
        System.out.println(collect);

        list = list.stream().filter(li -> li.length() != 1).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(list));

        Integer reduce1 = Stream.of(1, 2, 3).reduce(0, (ac, elem) -> ac + elem);
        System.out.println(reduce1);
    }
}
