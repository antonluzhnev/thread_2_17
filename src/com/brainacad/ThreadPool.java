package com.brainacad;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {

    private static ExecutorService executorService = Executors.newSingleThreadExecutor();
    private static ExecutorService executorService1 = Executors.newFixedThreadPool(4);

    public static void main(String[] args) {

        for (int i = 0; i < 10 ; i++) {
            final int val=i;
            executorService1.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("start " + val);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("end " + val);
                }
            });
        }

        executorService1.shutdown();// остановка экзекутора



    }
}
