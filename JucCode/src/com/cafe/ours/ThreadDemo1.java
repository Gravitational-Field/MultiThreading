package com.cafe.ours;

/**
 * @ClassName ThreadDemo1
 * @Description: TODO
 * @Author Keen
 * @DATE 2021/4/14 20:47
 * @Version 1.0
 **/
public class ThreadDemo1 extends Thread{
    private Thread t;
    private String threadName;

    ThreadDemo1(String name) {
        threadName = name;
        System.out.println("Creating " + threadName);
    }

    @Override
    public void run() {
        System.out.println("Running " +  threadName );

        try {
            for (int i = 4; i > 0; i--) {
                System.out.println("Thread: " +  threadName + "," + i);
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread " +  threadName + " interrupted.");
        }

        System.out.println("Thread " +  threadName + " exiting.");
    }

    /*@Override
    public synchronized void start() {
        System.out.println("Starting " +  threadName );
        if (t == null) { //保证线程一定创建
            t = new Thread (this, threadName);
            t.start ();
        }
    }*/

    public static void main(String[] args) {
        ThreadDemo1 T1 = new ThreadDemo1("线程-1");
        T1.start();
        //T1.start();
        ThreadDemo1 T2 = new ThreadDemo1("线程-2");
        //T2.start();
        /*T1.run();
        T1.run();
        T2.run();*/
        /*T1.start();
        T2.start();*/


    }


}
