package com.example.demo;

/**
 * @author huangrongchao on 2018/5/1.
 * @version 1.0
 */
public class Choice {

    Strategy strategy;

    public Choice(Strategy strategy) {
        this.strategy = strategy;
    }
    public Object validate(){
        return strategy.execute();
    }
}
