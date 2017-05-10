package com.brainacad;

public class Main {

    static int val =0;

    static class SimpleThread extends Thread{
        @Override
        public void run() {
           while (true) {
               val++;

               System.out.println(val);

               try {
                   sleep(200);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        }
    }

    public static void main(String[] args) {

        /*for (int i = 0; i < 5 ; i++) {
            MyThread myThread = new MyThread();
            myThread.setDaemon(true);
            myThread.start();

        } //для способа создания через класс


        Thread thread = new Thread(new MyRunnable());
        thread.setName("Test");
        thread.setPriority(10);//установка приоритетов от 1 до 10 еще зависит от системы. например в винде только 6 приоритетов
        thread.setDaemon(true);// по умолчанию фолс, если демон, то приложение не ждет окончания потока.

        thread.start();
        thread.interrupt();// прерать поток
        System.out.println(thread.getId());// получит Id
        System.out.println(thread.getName());// получит имя потока
        System.out.println(thread.getState());// возвращает состояние потока - слайд States of a Thread 2/2
        */

        new SimpleThread().start();
        new SimpleThread().start();
        new SimpleThread().start();
        
        System.out.println("dddd");

        System.out.println("Hello new NEWWWW");
    }
}
