package com.brainacad;


import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class ForkJoinSample {

    private static final int SEGMENT_SIZE = 10;
    private static final int TOTAL_POINTS = 500;
    private static int x0 = 1;


    public static void main(String[] args) {

      //  new ForkJoinPool().invoke(new Calc(0, TOTAL_POINTS));// передаем задачу
        Double sum = new ForkJoinPool().invoke(new CalcSum(0, TOTAL_POINTS));
        System.out.println("Sum = " + sum);

    }

    /*static class Calc extends RecursiveAction {// должны переопределите метод compute  разбивает задачи на подзадачи

        private final int start;
        private final int end;

        public Calc(int start, int end) {
            this.start = start;
            this.end = end;
        }

        private void calcInRange(int start, int end) {
            System.out.println(String.format("Calc in range: %d, %d", start, end));
            for (int i = start; i < end; i++) {
                double x = x0 + i * 0.01;
                calc(x, i);
            }
        }

        private double calc(double x, int n) {
            double sum = 0;
            for (int i = 0; i < n; i++) {
                sum += Math.log(x) * Math.sin(x * x) - Math.cos(x * x) * Math.pow(x, 5);
            }

            return sum;
        }

        @Override
        protected void compute() {
            if (end - start < SEGMENT_SIZE) {
                calcInRange(start, end);
            } else {
                int mid = (start + end) / 2;
                Calc calc1 = new Calc(start, mid);
                Calc calc2 = new Calc(mid + 1, end);

                invokeAll(calc1, calc2);// ждем пока выполнтся задача
            }
        }
    }*/

    static class CalcSum extends RecursiveTask<Double> {

        private final int start;
        private final int end;

        public CalcSum(int start, int end) {
            this.start = start;
            this.end = end;
        }

        private double calcInRange(int start, int end) {
            System.out.println(String.format("Calc in range: %d, %d, %d",Thread.currentThread().getId(), start, end));
            double sum = 0;
            for (int i = start; i < end; i++) {
                double x = x0 + i * 0.01;
                sum += calc(x, i);
            }

            return sum;
        }

        private double calc(double x, int n) {
            double sum = 0;
            for (int i = 0; i < n; i++) {
                sum += Math.log(x) * Math.sin(x * x) - Math.cos(x * x) * Math.pow(x, 5);
            }
            return sum;
        }

        @Override
        protected Double compute() {
            if (end - start < SEGMENT_SIZE) {
                return calcInRange(start, end);
            } else {
                int mid = (start + end) / 2;
                CalcSum calc1 = new CalcSum(start, mid);
                calc1.fork();

                CalcSum calc2 = new CalcSum(mid + 1, end);
                calc2.fork();

                Double join1 = calc1.join();// для того что бы получить результат. Ждет пока завершиться задача и возвращает результат
                Double join2 = calc2.join();
                return join1 + join2;
            }
        }
    }

}
