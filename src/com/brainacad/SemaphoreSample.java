package com.brainacad;


import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreSample {

    private static final Semaphore semaphore = new Semaphore(3);

    public static void main(String[] args) {

        //semaphore.release();// освободили семафор

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        long id = Thread.currentThread().getId();

                        System.out.println(id + " Waiting...");
                        semaphore.acquire();

                        System.out.println(id + " Running");
                        TimeUnit.SECONDS.sleep(3);// ждем три секунды


                        System.out.println(id + " Release");
                        semaphore.release();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

}
