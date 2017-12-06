package com.huqi;

public interface FunctionInterface {
    double caclulate(int a);

    default double sqrt(int a) {

        return Math.sqrt(a);
    }
}
