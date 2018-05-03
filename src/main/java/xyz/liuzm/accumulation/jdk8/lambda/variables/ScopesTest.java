package xyz.liuzm.accumulation.jdk8.lambda.variables;

/**
 * 用来测试 lambda 表达式如何使用外部变量
 */
public class ScopesTest {

    private static int staticVar = 0;

    /**
     * 测试入口
     * @param args 命令行参数列表
     */
    public static void main(String[] args) {
        int a = 1;
        StringBuilder sb = new StringBuilder();
        Runnable runnable = () -> {
            System.out.println(a);
            sb.append(0);
//            sb = new StringBuilder();   // 不可以修改地址值
            staticVar++;  //全局静态变量可以修改值
        };

    }


}
