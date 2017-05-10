package com.brainacad;


import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockSample {

    static ReentrantLock lock = new ReentrantLock( );

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();// блокирует переменную для второго потока, пока не будет анлок второй поток ничего делать не будет
                System.out.println("Thread1 locked");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread1 unlocked");
                lock.unlock();

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(lock.isLocked());
                lock.lock();
                System.out.println("Thread2 locked");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread2 unlocked");
                lock.unlock();
            }
        }).start();
    }
}
