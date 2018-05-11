package xyz.liuzm.accumulation.concurrent;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDemo {

    static class Pair {
        private Integer first;
        private Integer second;
        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        public Integer getFirst() {
            return first;
        }

        public Integer getSecond() {
            return second;
        }

        @Override
        public String toString() {
            return String.valueOf(this.first + this.second);
        }
    }

    public static void main(String[] args) {
        Pair p = new Pair(10, 12);
        AtomicReference<Pair> demo = new AtomicReference<>(p);
        demo.compareAndSet(p, new Pair(15, 15));
        System.out.println(demo.get());
    }

}
