package xyz.liuzm.accumulation.jdk8.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 用来测试并行 stream
 */
public class Parallel {

    /**
     * 入口测试方法
     * @param args 命令行参数列表
     */
    public static void main(String[] args) {
        int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }


        // 串行测试
        long t0 = System.nanoTime();

        long count = values.stream().sorted().count();
        System.out.println(count);

        long t1 = System.nanoTime();

        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sequential sort took: %d ms", millis));


        // 并行测试
        long t2 = System.nanoTime();

        long count1 = values.parallelStream().sorted().count();
        System.out.println(count1);

        long t3 = System.nanoTime();

        long millis1 = TimeUnit.NANOSECONDS.toMillis(t3 - t2);
        System.out.println(String.format("parallel sort took: %d ms", millis1));
    }

}
