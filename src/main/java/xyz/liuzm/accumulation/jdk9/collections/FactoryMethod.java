package xyz.liuzm.accumulation.jdk9.collections;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用来学习 JDK 9 的新版集合工厂方法
 */
public class FactoryMethod {

    /**
     * 用来测试
     * @param args
     */
    public static void main(String[] args) {
        List<String> list = List.of("A", "B", "C");
        System.out.println(list);

        Set<String> set = Set.of("A", "B", "C");
        System.out.println(set);

        Map<String, Integer> map = Map.of("A", 1, "B", 2, "C", 3);
        System.out.println(map);

        Map<String, Integer> mapEntry = Map.ofEntries(
                Map.entry("A", 1),
                Map.entry("B", 2),
                Map.entry("C", 3)
        );
        System.out.println(mapEntry);
    }

}
