package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.util.CollectionUtils;

/**
 * @author huangrongchao on 2018/5/1.
 * @version 1.0
 */
public class Subset {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 4, 9);
        List<List<Integer>> subsets = subsets(list);
        System.out.println(subsets);
    }

    public static List<List<Integer>> subsets(List<Integer> list) {
        List<List<Integer>> sets = new ArrayList<>();
        if (CollectionUtils.isEmpty(list)) {
            sets.add(list);
            return sets;
        }
        List<Integer> rest = list.subList(1, list.size());
        List<List<Integer>> subans = subsets(rest);
        subans.forEach(sub -> {
            List<Integer> copy = new ArrayList<>(sub);
            copy.add(list.get(0));
            sets.add(copy);
        });
        sets.addAll(subans);
        sets.add(new ArrayList<>(list));
        return sets;
    }

}
