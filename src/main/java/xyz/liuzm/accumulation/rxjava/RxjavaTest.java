package xyz.liuzm.accumulation.rxjava;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * 测试 rxjava 的使用场景
 */
public class RxjavaTest {

    /**
     * 用来测试线程切换的方法
     */
    public static void test1() {
        Observable.just(blockMethod("A")).subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.newThread())
                .subscribe(s -> System.out.println(s));

        Observable.defer(() -> Observable.just(blockMethod("AA"))).subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.single())
                .subscribe(s -> {
                    System.out.println(Thread.currentThread().getName());
                    System.out.println(s);
                });
    }

    /**
     * 写一个费时操作
     * @param message 标示字符串
     * @return 合成后的字符串
     */
    private static String blockMethod(String message) {
        String result = "block:" + message;
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(1000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 用来测试 RXJAVA
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        System.out.println("C");
        test1();
        System.out.println("B");
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(2000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
