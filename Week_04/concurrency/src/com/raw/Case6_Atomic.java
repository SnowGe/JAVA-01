package com.raw;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Case6_Atomic {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        AtomicInteger result = new AtomicInteger();
        new Thread(() -> result.set(Demo.sum())).start();
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }
}
