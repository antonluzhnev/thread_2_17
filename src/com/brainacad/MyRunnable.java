package com.brainacad;


public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Runnable " + Thread.currentThread().getName());
    }
}
