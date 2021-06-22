package com.cafe.ours;

/**
 * @ClassName ThreadDemo2
 * @Description: 实现 Runnable 接口的类
 * @Author Keen
 * @DATE 2021/4/14 21:40
 * @Version 1.0
 **/
public class ThreadDemo2 implements Runnable{
    private Thread t;
    private String threadName;

    ThreadDemo2( String name) {
        threadName = name;
        System.out.println("Creating " +  threadName );
    }

    @Override
    public void run() {
        System.out.println("Running " +  threadName );
        try {
            for(int i = 4; i > 0; i--) {
                System.out.println("Thread: " + threadName + ", " + i);
                // 让线程睡眠一会
                Thread.sleep(50);
            }
        }catch (InterruptedException e) {
            System.out.println("Thread " +  threadName + " interrupted.");
        }
        System.out.println("Thread " +  threadName + " exiting.");
    }

    public void start () {
        System.out.println("Starting " +  threadName );
        if (t == null) {
            t = new Thread (this, threadName);
            t.start ();
        }
    }

    public static void main(String[] args) {
        ThreadDemo2 R1 = new ThreadDemo2( "Thread-1");
        R1.start();

        ThreadDemo2 R2 = new ThreadDemo2( "Thread-2");
        R2.start();
    }
}
