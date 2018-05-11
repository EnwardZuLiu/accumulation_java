package xyz.liuzm.accumulation.concurrent;

import java.util.concurrent.TimeUnit;

public class VolatileTest implements Runnable {

    private static boolean flag = false;

    @Override
    public void run() {
        while (!flag) {
            System.out.println("异步线程：" + System.currentTimeMillis());
        }
        System.out.println("异步线程执行完毕");
    }

    public void stop() {
        flag = true;
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileTest v = new VolatileTest();
        new Thread(v).start();
        TimeUnit.MILLISECONDS.sleep(100L);
        System.out.println("主线程开始：" + System.currentTimeMillis());
        v.stop();
        System.out.println("主步线程结束：" + System.currentTimeMillis());
        TimeUnit.MILLISECONDS.sleep(1000L);
    }
}
