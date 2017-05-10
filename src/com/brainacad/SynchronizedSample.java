package com.brainacad;


public class SynchronizedSample {


        public static Integer val = 0;
      final static Object lock = new Object();

        public static void main(String[] args) {

      //  new NonSynchronizedThread("NonSync1").start();
        // new NonSynchronizedThread("NonSync2").start();

            new SynchronizedThread("Sync1").start();
            new SynchronizedThread("Sync2").start();
        }


        private static synchronized void changeVal() {
            while (val < 5) {
                val++;
                System.out.println(String.format("%s: %d", Thread.currentThread().getName(), val));
            }
            val = 0;
        }

        static class SynchronizedThread extends Thread {
            public SynchronizedThread(String name) {
                super(name);
            }
            @Override
            public void run() {
               // synchronized (lock){//(lock)(SynchronizedThread.class) {// пока работает с этим классом никто не может туда обратиться
                    changeVal();
                //}
            }
        }

        static class NonSynchronizedThread extends Thread {
            public NonSynchronizedThread(String name) {
                super(name);
            }
            @Override
            public void run() {
                changeVal();
            }
        }

}


