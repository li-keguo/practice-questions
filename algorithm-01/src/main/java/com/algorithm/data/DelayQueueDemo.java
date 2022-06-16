package com.algorithm.data;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * DelayQueueDemo.
 *
 * @author <a href='mailto:likeguo@apache.com'> likeguo </a>
 */
public class DelayQueueDemo {
    
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<MyDelayed> delayQueue = new DelayQueue<>();
        delayQueue.put(new MyDelayed(500, "A"));
        delayQueue.put(new MyDelayed(1000, "B"));
        delayQueue.put(new MyDelayed(500, "C"));
        delayQueue.put(new MyDelayed(5000, "D"));
        Thread.sleep(4000);
        System.out.println(delayQueue.poll().getName());
        System.out.println(delayQueue.poll().getName());
        System.out.println(delayQueue.poll().getName());
        System.out.println(delayQueue.poll().getName());
        
    }
    
    private static class MyDelayed implements Delayed {
        private final long start;
        private final long delay;
        private final String name;
        
        private MyDelayed(long delay, String name) {
            this.start = System.currentTimeMillis();
            this.delay = delay;
            this.name = name;
        }
        
        /**
         * get name.
         */
        public String getName() {
            return name;
        }
        
        @Override
        public long getDelay(TimeUnit unit) {
            return start + delay - System.currentTimeMillis();
        }
        
        public long getDelay() {
            return delay;
        }
        
        @Override
        public int compareTo(Delayed o) {
            if (o instanceof MyDelayed) {
                return (int) (delay - ((MyDelayed) o).getDelay());
            }
            long d = (getDelay(TimeUnit.NANOSECONDS) -
                    o.getDelay(TimeUnit.NANOSECONDS));
            return (d == 0) ? 0 : ((d < 0) ? -1 : 1);
        }
    }
}
