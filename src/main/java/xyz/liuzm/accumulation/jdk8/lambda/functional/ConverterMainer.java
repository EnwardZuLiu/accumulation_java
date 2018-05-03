package xyz.liuzm.accumulation.jdk8.lambda.functional;

/**
 * 测试方法
 */
public class ConverterMainer {

    /**
     * 测试入口方法
     * @param args 命令行参数列表
     */
    public static void main(String[] args) {
        // 使用 lambda 表达式来编写实现类，并调用实现类的方法操作即可。
        Converter<Integer, String> converter = (from -> Integer.valueOf(from));
        Integer i = converter.converter("123");
        System.out.println(i);

        // 实现原理就是，要求参数列表和返回值类型都是完全一样的，这样就可以使用关键字`::`
        Converter<Integer, String> converterString = Integer::valueOf;
        Integer j = converterString.converter("456");
        System.out.println(j);
    }

}
