package s03.thread_synchronization_utilities.s04.exam;

import util.ThreadUtil;

import java.util.Random;
import java.util.concurrent.Phaser;

class Student implements Runnable {

    private final Phaser phaser;
    private final Random random = new Random();

    Student(Phaser phaser) {
        this.phaser = phaser;
        this.phaser.register();
    }

    @Override
    public void run() {
        System.out.printf("%s arrived to the exam\n", Thread.currentThread().getName());
        phaser.arriveAndAwaitAdvance();

        doTask1();
        System.out.printf("%s finished task 1\n", Thread.currentThread().getName());
        phaser.arriveAndAwaitAdvance();

        doTask2();
        System.out.printf("%s finished task 2\n", Thread.currentThread().getName());
        phaser.arriveAndAwaitAdvance();

        doTask3();
        System.out.printf("%s finished task 3\n", Thread.currentThread().getName());
        phaser.arriveAndAwaitAdvance();
    }

    private void doTask1() {
        ThreadUtil.sleepSeconds(getDelay());
    }

    private void doTask2() {
        ThreadUtil.sleepSeconds(getDelay());
    }

    private void doTask3() {
        ThreadUtil.sleepSeconds(getDelay());
    }

    private int getDelay() {
        return random.nextInt(10) + 1;
    }

}
