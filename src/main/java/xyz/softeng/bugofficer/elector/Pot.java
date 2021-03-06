package xyz.softeng.bugofficer.elector;

import xyz.softeng.bugofficer.dataaccess.person.Person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Pot {
    private final PriorityQueue<Person> queue;

    public Pot(Comparator<Person> comparator) {
        queue = new PriorityQueue<>(comparator);
    }

    public void addCandidates(List<Person> candidates) {
        queue.addAll(candidates);
    }

    public Person pick() {
        return pick(1).get(0);
    }

    public List<Person> pick(int count) {
        if (queue.size() <= count)
            return new ArrayList<>(queue);

        List<Person> result = new ArrayList<>();
        for (int i = 0; i < count; i++)
            result.add(queue.poll());
        return result;
    }
}
