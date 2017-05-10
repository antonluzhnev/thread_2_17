package com.brainacad;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchSample {

    static CountDownLatch latch = new CountDownLatch(4);// начальное значение счетчика

    public static void main(String[] args) {

        for (int i = 0; i < 4 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Thread " + Thread.currentThread().getId());
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    latch.countDown();//уменьшаем счетчик
                }
            }).start();
        }

        System.out.println("Waiting....");
        try {
            latch.await();// ждем пока счетчик не станет равным 0
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finished");
    }

}
