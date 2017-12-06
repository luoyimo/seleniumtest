package com.huqi;

public interface CovertInterface<F, T> {
    T convert(F from);
}
