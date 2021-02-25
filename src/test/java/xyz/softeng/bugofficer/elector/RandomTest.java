package xyz.softeng.bugofficer.elector;

import lombok.Value;
import org.junit.jupiter.api.Test;

import java.util.*;

class RandomTest {
    @Value
    static class Chance implements Comparable<Chance> {
        private static int ID = 0;
        int priority;
        int id;

        public Chance(int priority) {
            this.priority = priority;
            id = ID++;
        }

        @Override
        public int compareTo(Chance o) {
            return Comparator.comparing(Chance::getPriority)
                    .compare(this, o);
        }
    }

    @Test
    void test() {
        List<Chance> list = new ArrayList<>();
//        Random random = new Random();
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 5; j++)
                list.add(new Chance(i));
        Collections.shuffle(list);
        PriorityQueue<Chance> queue = new PriorityQueue<>();
        list.forEach(queue::add);
        System.out.println(queue.poll());
    }
}
