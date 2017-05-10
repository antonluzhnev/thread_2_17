package com.brainacad;


import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class WaitNotifySample {

    private static ArrayList<Integer> list = new ArrayList<>();

    public static void main(String[] args) {

        Thread thread1 = new Thread1();
        Thread thread2 = new Thread2();

        thread1.start();
        thread2.start();

        try {
            thread1.join();// ждет завершение потока
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Finish");
    }

    static class Thread1 extends Thread {// ждет пока заполнится лист и выводит кол во элементов
        @Override
        public void run() {
            synchronized (list) {
                System.out.println("Waiting...");
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("List size: " + list.size());
            }
        }
    }

    static class Thread2 extends Thread { // формирует список заполняя его элементами
        @Override
        public void run() {
            for (int i = 0; i < 10; i++)
            {
                list.add((int) (Math.random() * 100));
                try {
                    TimeUnit.MILLISECONDS.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            synchronized (list) {
                list.notifyAll();
            }
        }
    }

}
