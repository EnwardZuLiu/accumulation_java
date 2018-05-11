package xyz.liuzm.accumulation.concurrent;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport 测试使用
 */
public class LockSupportDemo {

    /**
     * 测试 LockSupport
     * @param args 命令行参数
     * @throws InterruptedException 中断异常
     */
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread() {
            @Override
            public void run() {
                LockSupport.park(); // 让当前线程进入等待状态
                System.out.println("exit;");
            }
        };
        t.start();
        Thread.sleep(3000l);
        LockSupport.unpark(t); // 将指定线程恢复到可运行状态
    }

}
