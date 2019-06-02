package com.jqbase.concurrency.example.aqs;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**^
 * <p>Description:</p>
 *
 * @author JQ on 2018/11/13 22:57
 */
@Slf4j
public class CountDownLatchDemo {

    private final static Integer THREADNUM = 20;

    private final static CountDownLatch latch = new CountDownLatch(THREADNUM);

    public static void main(String[] args) throws InterruptedException {

        ExecutorService pool = getFixPool(2);

        for (int i = 0; i < THREADNUM; i++) {
            final int num = i+1;
            pool.execute(()->{
                try {

                    task(Thread.currentThread().getName() +"-"+num);
                } catch (Exception e) {
                    log.error("exception"+e);
                } finally {
                    latch.countDown();
                }

            });

        }
        latch.await();
        System.out.println("finish....");
    }

    public static ExecutorService getFixPool(Integer num) {

        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("concurrency-threads-%d").build();
        return new ThreadPoolExecutor(num, num,
                1000, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(),threadFactory,new ThreadPoolExecutor.AbortPolicy());
    }

    public static void task(String name) throws InterruptedException {
        Thread.sleep(100);
        System.out.println(name);
    }

}
