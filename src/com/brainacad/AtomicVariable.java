package com.brainacad;


import java.util.concurrent.atomic.AtomicInteger;

public class AtomicVariable {

    static AtomicInteger val = new AtomicInteger();

    public static void main(String[] args) {

        val.set(10);

        for (int i = 0; i < 5 ; i++) {
             new Thread(new Runnable() {
                 @Override
                 public void run() {
                     int v = val.getAndIncrement();
                     int v1 = val.get();
                     System.out.println("Prev: " + v);
                     System.out.println("Current: " + v1);
                 }
             }).start();
        }

    }

}
