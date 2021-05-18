# 1、基础知识

## 1.1 Java多线程

**进程**

> - 分配资源的基本单位
> - 一个进程包含一个或多个线程

**线程**

> - 线程是调度的单位
> - 多线程编程能够使得程序高效利用CPU

### 1.1.1 线程的生命周期

线程是一个动态执行的过程，其生命周期是一个从创建到消亡的过程。

下图为一个线程的完整的生命周期：

![image-20210414200855644](img/image-20210414200855644.png)

**线程的五种状态：**

- **新建状态**

  ​		使用 **new** 关键字和 **Thread** 类或其子类建立一个线程对象后，该线程对象就处于新建状态。它保持这个状态直到程序 **start()** 这个线程。

- **就绪状态**

  ​		当线程对象调用了start()方法之后，该线程就进入就绪状态。就绪状态的线程处于就绪队列中，要**<font color='red'>等待JVM线程调度器</font>**的调度。

- **运行状态**

  ​		如果就绪状态的线程获取 CPU 资源，就可以执行 **run()**，此时线程便处于运行状态。处于运行状态的线程最为复杂，它可以变为**<font color='red'>阻塞状态、就绪状态和死亡状态。</font>**  

- **阻塞状态**

  ​		如果一个线程执行了sleep（睡眠）、suspend（挂起）等方法，失去所占用资源之后，该线程就从运行状态进入阻塞状态。在睡眠时间已到或获得设备资源后可以重新进入就绪状态。阻塞状态可以分为三种：

  - 等待阻塞：运行状态中的线程执行 wait() 方法，使线程进入到等待阻塞状态；
  - 同步阻塞：线程在获取 synchronized 同步锁失败(因为同步锁被其他线程占用)；
  - 其他阻塞：通过调用线程的 sleep() 或 join() 发出了 I/O 请求时，线程就会进入到阻塞状态。当sleep() 状态超时，join() 等待线程终止或超时，或者 I/O 处理完毕，线程重新转入就绪状态；

- **死亡状态**

  ​		一个运行状态的线程完成任务或者其他终止条件发生时，该线程就切换到终止状态。



### 1.1.2 线程优先级

> - 每一个Java线程都有一个优先级，有助于CPU确定线程的调度顺序；
> - 优先级为整数，从1到10，1最小，10最大；
> - 1：（Thread.MIN_PRIORITY ）10 ：（Thread.MAX_PRIORITY ）
> - 默认情况下，每一个线程都会分配一个优先级 NORM_PRIORITY（5）；
> - 高优先级会优先分配CPU资源，<font color='red'>但线程优先级不能保证线程执行的顺序</font>



### 1.1.3 创建线程的四种方式

- 通过继承Thread类
- 通过实现Runnable接口
- 通过Callable接口和Future创建线程
- 通过线程池获取线程



#### 1）通过继承Thread类



见代码：com.cafe.ours.ThreadDemo1.java

```java
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

    @Override
    public synchronized void start() {
        System.out.println("Starting " +  threadName );
        if (t == null) { //保证线程一定创建
            t = new Thread (this, threadName);
            t.start ();
        }
    }


    public static void main(String[] args) {
        ThreadDemo1 T1 = new ThreadDemo1("线程-1");
        T1.start();
        ThreadDemo1 T2 = new ThreadDemo1("线程-2");
        T2.start();
    }
}
```



![image-20210414213713157](img/image-20210414213713157.png)





```java
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
```









## 参考链接

1、[Java 多线程编程]([Java 多线程编程 | 菜鸟教程 (runoob.com)](https://www.runoob.com/java/java-multithreading.html))