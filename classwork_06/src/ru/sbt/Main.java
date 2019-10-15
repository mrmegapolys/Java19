package ru.sbt;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        Map<Integer, String> hashMapTimer = TimerProxy.timer(new HashMap<>());
        Map<Integer, String> treeMapTimer = TimerProxy.timer(new TreeMap<>());
        IPerson personTimer = TimerProxy.timer(new Person("Alex"));


        personTimer.sleep();

        hashMapTimer.put(1, "abc");
        hashMapTimer.put(12, "abcd");
        hashMapTimer.put(12, "abc");

        hashMapTimer.get(1);
        hashMapTimer.get(12);


        treeMapTimer.put(1, "abc");
        treeMapTimer.put(12, "abcd");
        treeMapTimer.put(12, "abc");

        treeMapTimer.get(1);
        treeMapTimer.get(12);





    }
}
