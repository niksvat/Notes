import java.util.*;

class Solution {

    class SharedObject {

        

    }

    public static void main(String... args) throws InterruptedException {
       new Solution().run();
    }

    public void run() throws InterruptedException {

        SharedObject obj = new SharedObject();

        Thread thread1 = new Thread(() -> {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized(obj) {
                try {
                    System.out.println("wait start 1");
                    obj.wait();
                    System.out.println("wait over 1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });

        Thread thread3 = new Thread(() -> {

            synchronized(obj) {
                try {
                    System.out.println("wait start 3");
                    obj.wait();
                    System.out.println("wait over 3");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });

        Thread thread2 = new Thread(() -> {
            synchronized(obj) {
                    System.out.println("notify");
                    obj.notifyAll();
                    System.out.println("release notify");
            }
        });

        thread1.start();
        thread3.start();
        Thread.sleep(1000);
        thread2.start();

    }
    
     
    
}