package com.huqi;

/**
 * @author huqi
 * @create 2017-12-04 10:16
 **/
import java.lang.reflect.Field;

/**
 * 一加一不等于二
 *
 * @author baifan
 * @version V1.0
 * @since 2017-11-30 22:01
 */
public class OnePlusOne {

    static {
        try {
            Class<?> cacheClazz = Class.forName("java.lang.Integer$IntegerCache");
            Field cacheField = cacheClazz.getDeclaredField("cache");
            cacheField.setAccessible(true);
            Integer[] cache = (Integer[]) cacheField.get(null);
            cache[2 + 128] = new Integer(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        testOne();
    }

    public static void testOne() {
        int one = 1;
        int two = one + one;
        System.out.printf("Two=%d", two);
    }

    public static void testOneFull() {
        int one = 1;
        int two = one + one;
        Object[] params = { Integer.valueOf(two) };
        System.out.printf("Two=%d", params);
    }

    public static void testTwo() {
        int one = 1;
        int two = one + one;
        System.out.println(two == 2);
        System.out.println(Integer.valueOf(two) == 2);
    }
}
