package xyz.liuzm.accumulation.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 主要是用来测试 AtomicInteger 这个类
 */
public class AtomicIntegerDemo {

    /**
     * 定义一个原子变量，用来测试并发场景下是否正确
     */
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * 创建一个线程实现类
     */
    private static class Visitor extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                atomicInteger.incrementAndGet();
                /**
                 *         Java线程中的Thread.yield( )方法，译为线程让步。顾名思义，就是说当一个线程使用了这个方法之后，它就会把自己CPU执行的时间让掉，
                 *
                 * 让自己或者其它的线程运行，注意是让自己或者其他线程运行，并不是单纯的让给其他线程。
                 *
                 *         yield()的作用是让步。它能让当前线程由“运行状态”进入到“就绪状态”，从而让其它具有相同优先级的等待线程获取执行权；但是，并不能保
                 *
                 * 证在当前线程调用yield()之后，其它具有相同优先级的线程就一定能获得执行权；也有可能是当前线程又进入到“运行状态”继续运行！
                 *
                 *       举个例子：一帮朋友在排队上公交车，轮到Yield的时候，他突然说：我不想先上去了，咱们大家来竞赛上公交车。然后所有人就一块冲向公交车，
                 *
                 * 有可能是其他人先上车了，也有可能是Yield先上车了。
                 *
                 *      但是线程是有优先级的，优先级越高的人，就一定能第一个上车吗？这是不一定的，优先级高的人仅仅只是第一个上车的概率大了一点而已，
                 *
                 * 最终第一个上车的，也有可能是优先级最低的人。并且所谓的优先级执行，是在大量执行次数中才能体现出来的。
                 */
                Thread.yield();
            }
        }
    }

    /**
     * 测试AtomicInteger 类
     * @param args 命令行参数
     * @throws InterruptedException 线程中断异常
     */
    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            threads[i] = new Visitor();
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println(atomicInteger.get());
    }

}
