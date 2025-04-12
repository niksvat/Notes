import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.*;

public class Solution {
    
    static Random rand = new Random();

    public static void main(String... args) throws InterruptedException {
        int max = 1_000_000_00;

        int[] arr = new int[max];
        for(int i=0;i<max;i++) {
            arr[i] = rand.nextInt(max - i);
        }
        int[] arrClone = arr.clone();

        // Thread.sleep(10000);
       
        
        Instant start = Instant.now();
        new Solution().sort(arr);
        Instant finish = Instant.now();
        long timeElapsedParallel = Duration.between(start, finish).toMillis();

        start = Instant.now();
        Arrays.sort(arrClone);
        finish = Instant.now();
        long timeElapsedSequential = Duration.between(start, finish).toMillis();

        System.out.println(timeElapsedParallel);
        System.out.println(timeElapsedSequential);



        // System.out.println(Arrays.toString(arr));
        // System.out.println(Arrays.toString(arrClone));
    }


    public void sort(int[] arr) {

        MergeAction task = new MergeAction(arr);
        ForkJoinPool fJoinPool = ForkJoinPool.commonPool();
        fJoinPool.invoke(task);
    

    }

    class MergeAction extends RecursiveAction {

        int[] arr;

        public MergeAction(int[] arr) {
            this.arr = arr;
        }

        @Override
        protected void compute() {

            int size = arr.length;
            if(size<1000) {
                Arrays.sort(arr);
                return;
            }
            int mid = size/2; //2

            int[] left = new int[mid]; //1
            int[] right = new int[size-mid]; //2

            System.arraycopy(arr, 0, left, 0, mid);
            System.arraycopy(arr, mid, right, 0, size-mid);

            var leftTask = new MergeAction(left);
            var rightTask = new MergeAction(right);

            // ----------
            leftTask.fork();
            rightTask.fork();

            leftTask.join();
            rightTask.join();

            // ----------

            // invokeAll(leftTask, rightTask);

            // ----------

            // leftTask.fork();
            // rightTask.compute();

            // leftTask.join();


            merge(left, right);

        }
        
        private void merge(int[] left, int[] right) {

            int l = 0;
            int r = 0;

            int i = 0;
        
            
            while(l<left.length && r<right.length) {
                if(left[l]<right[r]) arr[i++] = left[l++];
                else arr[i++] = right[r++];
            }

            while(l<left.length) arr[i++] = left[l++];
            while(r<right.length) arr[i++] = right[r++];
        }

    }


}
/*
 * 

                |
  left : 1 2 3 4
  right: 5 6 7 8 9
         |

  * * * * # * * * *
 

 * 
 */
