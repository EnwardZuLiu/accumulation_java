package xyz.liuzm.accumulation.jdk8.lambda.functional;

/**
 * @FunctionalInterface 只能标记在有一个抽象方法的接口上。
 * @param <T> 目标对象
 * @param <F> 来源对象
 */
@FunctionalInterface
public interface Converter<T, F> {

    /**
     * 定义一个任意的转化方法
     * @param from 任意对象
     * @return 任意任务
     */
    T converter(F from);

}
