package xyz.liuzm.accumulation.jdk8.lambda.constructor;


/**
 * 定义的实体类
 */
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Person {

    private String name;

    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * 定义一个接口，并使用 lambda 表达式实现
     */
    @FunctionalInterface
    interface PersonFactory{
        Person create(String name, int age);
    }

    /**
     * 入口方法，主要用来测试
     * @param args 命令行参数列表
     */
    public static void main(String[] args) {
        // 重点在于，构造方法和方法的参数列表和返回值的类型一定要保持一致，这样就可以直接使用
        PersonFactory personFactory = Person::new;
        Person p = personFactory.create("liu zu ming", 30);
        System.out.println(p);
    }

}
