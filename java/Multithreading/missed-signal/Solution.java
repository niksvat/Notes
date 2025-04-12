import java.util.*;

class Solution {

    class SharedObject {


    }

    public static void main(String... args) throws InterruptedException {
    //    new Solution().run1();
       new Solution().run2();
    }

    public void run1() throws InterruptedException {

        SharedObject obj = new SharedObject();

        Thread thread1 = new Thread(() -> {

            // try {
            //     Thread.sleep(1000);
            // } catch (InterruptedException e) {
            //     e.printStackTrace();
            // }
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

        Thread thread2 = new Thread(() -> {
            synchronized(obj) {
                    System.out.println("notify");
                    obj.notify();
                    System.out.println("release notify");
            }
        });

        thread2.start();
        thread1.start();

    }

    boolean notified = false;
    public void run2() throws InterruptedException {
        
        SharedObject obj = new SharedObject();
        
        Thread thread1 = new Thread(() -> {
            synchronized(obj) {
                try {
                    System.out.println("1 wait start");
                    
                    if(!notified) // replace this with while 
                        obj.wait();
                    
                    System.out.println("2 wait exit");
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized(obj) {
                System.out.println("2 notify entry");
                
                obj.notify();
                notified = true;

                System.out.println("2 notify exit");
            }
        });

        thread1.start();
        thread2.start();


    }
    
     
    
}