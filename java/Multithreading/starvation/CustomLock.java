import java.util.*;


class CustomLock {


    class Lock {

        boolean isLocked = false;

        public synchronized void lock() throws Exception {
            while(isLocked)
                this.wait();
            isLocked = true;
        }

        public synchronized void unlock() {
            this.notify();
            isLocked = false;
        }

    }


    class SharedObject {
        Lock lock = new Lock();
        public void sharedMethod() throws Exception{
            System.out.println("Start " + Thread.currentThread().getName());
            lock.lock();

            System.out.println("Hey1 " + Thread.currentThread().getName());
            System.out.println("Hey2 " + Thread.currentThread().getName());

            lock.unlock();
            System.out.println("End " + Thread.currentThread().getName());

        }
    }


    public static void main(String... args)  throws Exception{
        new CustomLock().run();
    }

    private void run()  throws Exception{
        
        SharedObject sobj = new SharedObject();
        Thread thread1 = new Thread(() -> {
            try {
                sobj.sharedMethod();
            } catch(Exception e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                sobj.sharedMethod();
            } catch(Exception e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();

    }


 }