package xyz.liuzm.accumulation.concurrent;

/**
 * 测试并确定 volatile 关键字并不能保证线程安全性
 */
public class VolatileInc implements Runnable {

    private static volatile int i = 0;

    @Override
    public void run() {
        for (int a = 0; a < 1000; a ++) {
            i++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int a = 0; a < 5; a++) {
            new Thread(new VolatileInc()).start();
        }

        for (int a = 0; a < 10000; a++) {
            i++;
        }
        Thread.sleep(2000l);
        System.out.println(i);

    }

}
