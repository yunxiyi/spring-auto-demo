package com.example.demo;

import java.util.Optional;

/**
 * @author huangrongchao on 2018/5/1.
 * @version 1.0
 */
public class StrategyMain {
    public static void main(String[] args) {
        Choice choice = new Choice(StrategyFactory::strategyString);
        Choice choice1 = new Choice(StrategyFactory::strategy);
        System.out.println(choice.validate());
        System.out.println(choice1.validate());

        Optional<Integer> optional = Optional.ofNullable(null);
        System.out.println(optional.isPresent());
    }
}
