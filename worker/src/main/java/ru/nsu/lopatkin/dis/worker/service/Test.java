package ru.nsu.lopatkin.dis.worker.service;

import org.apache.commons.math3.util.Combinations;
import org.paukov.combinatorics3.Generator;

public class Test {


    public void test() {

        Generator.combination("apple", "orange")
                .multi(3)
                .stream().skip()
                .forEach(System.out::println);
    }
}
