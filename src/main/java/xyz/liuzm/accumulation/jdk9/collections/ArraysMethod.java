package xyz.liuzm.accumulation.jdk9.collections;

import java.util.Arrays;

/**
 * 用来学习 JDK 9 中的数组的新方法
 */
public class ArraysMethod {

    /**
     * 用来测试 ARRAYS 中的新方法
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        int[] a = {1, 2, -7, 6, 8};
        int[] b = {1, 9, 7, 1, 2};

        System.out.println(Arrays.equals(a, 0, 1, b, 3, 4));
        System.out.println(Arrays.equals(a, 1, 2, b, 3, 4));

        System.out.println( Arrays.compare(a, 0, 1, b, 3, 4) );

        System.out.println( Arrays.compare(a, b) );

        System.out.println( Arrays.compareUnsigned(a, 2, 2, b, 2, 2) );

        System.out.println( Arrays.mismatch(a, b) );
    }

}
