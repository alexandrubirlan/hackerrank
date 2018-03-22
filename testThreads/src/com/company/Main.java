package com.company;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {


    public static void main(String[] args) throws InterruptedException {

//        DEADLOCK

//        Object syncObj1 = new Object();
//        Object syncObj2 = new Object();
//
//        MyThread threadThread1 = new MyThread(syncObj1, syncObj2);
//        threadThread1.start();
//
//        MyThread threadThread2 = new MyThread(syncObj2, syncObj1);
//        threadThread2.start();


//        Thread threadRunnable = new Thread(new MyRunable());
//        threadRunnable.start();


//        SYNC

        Object o = new Object();
        Resource resource = new Resource();
        Thread reader = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                synchronized (o) {
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                resource.getRes();

            }
        });

        Thread writer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (int i = 0; i < 1000; i++) {
                    synchronized (o) {
                        if (i > 500) {
                            o.notify();
                        }
                    }
                    resource.setRes(String.valueOf(i));
                }
            }
        });

        reader.start();
        writer.start();
    }
}


class Resource {
    private static String res;

    public Resource() {
    }

    public String getRes() {
//        synchronized (Resource.class) {
        System.out.println("Reading " + res);
        return res;
//        }
    }

    public void setRes(String res) {
//        synchronized(Resource.class) {
        System.out.println("Setting " + res);
        Resource.res = res;
//        }
    }

//    public String getResWait() {
//        synchronized (syncObj) {
//            System.out.println("Reading " + res);
//            syncObj.notify();
//            return res;
//        }
//    }
//
//    public void setResWait(String res) {
//        synchronized (syncObj) {
//            try {
//                syncObj.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("Setting " + res);
//            Resource.res = res;
//        }
//    }

    public static synchronized void m1() {

    }

    public synchronized void m2() {

    }

    public void m3() {
        synchronized (this) {

        }
    }

    public void m4() {
        synchronized (Resource.class) {

        }
    }
}

class MyThread extends Thread {

    private Object syncObj1;
    private Object syncObj2;

    private String resource;

    public MyThread(Object syncObj1, Object syncObj2) {
        this.syncObj1 = syncObj1;
        this.syncObj2 = syncObj2;
    }

    @Override
    public void run() {
        synchronized (syncObj1) {
            System.out.println(Thread.currentThread().getName() + " locked  " + syncObj1);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println(e);
            }
            synchronized (syncObj2) {
                System.out.println(Thread.currentThread().getName() + " locked  " + syncObj2);
            }
        }
    }

}

class MyRunable implements Runnable {

    @Override
    public void run() {
        while (true) {
            System.out.println("Doing stuff through runnable");
        }
    }
}
