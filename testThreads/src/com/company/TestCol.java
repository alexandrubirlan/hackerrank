package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TestCol {

    public static void main(String[] args) {


        Map<MyKey, String> myMap = new TreeMap<>((o1, o2) -> {
            if (o1 == null) return -1;
            if (o2 == null) return 1;
            return o1.getId() < o2.getId() ? -1 : 1;
        });

//        Map<MyKey, String> myMap = new TreeMap<>();


        myMap.put(new MyKey(2), "two");
        myMap.put(new MyKey(4), "four");
        myMap.put(new MyKey(3), "three");
        myMap.put(new MyKey(1), "one");

        for (Map.Entry<MyKey, String> entry : myMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }


        TestCol.execute(new TestIntf() {
            @Override
            public void doSMth(Integer i) {
                System.out.println(i + 2);
            }

            @Override
            public String doSMth1(String i, String s) {
                return i + s;
            }
        });

        TestCol.execute(i -> {
            System.out.println(i + 2);
        });


    }

    public static void execute(TestIntf intf) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);

        for (int i = 0; i < list.size(); i++) {
            intf.doSMth(i);
        }

    }

}

class MyKey {

    private int id;

    public MyKey(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}

interface TestIntf {

    void doSMth(Integer i);

    default String doSMth1(String i, String s) {
        return i + " " + s;
    }

}
