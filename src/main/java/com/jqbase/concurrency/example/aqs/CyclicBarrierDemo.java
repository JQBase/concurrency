package com.jqbase.concurrency.example.aqs;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * <p>Description:</p>
 *
 * @author JQ on 2018/11/15 21:07
 */
@Slf4j
public class CyclicBarrierDemo {
    private final static Integer THREADNUM = 9;

    private final static CyclicBarrier barrier = new CyclicBarrier(3,()->{
        log.info("barrier restarted...");
    });

    public static void main(String[] args) throws InterruptedException {

        ExecutorService pool = getFixPool(3);

        for (int i = 0; i < THREADNUM; i++) {
            final int num = i+1;
            pool.execute(()->{
                try {
                    task(Thread.currentThread().getName() +"-"+num);
                } catch (Exception e) {
                    log.error("exception"+e);
                }

            });

        }
        System.out.println("finish....");
        pool.shutdown();
    }

    public static ExecutorService getFixPool(Integer num) {

        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("concurrency-threads-%d").build();
        return new ThreadPoolExecutor(num, num,
                1000, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(100),threadFactory,new ThreadPoolExecutor.AbortPolicy());
    }

    public static void task(String name) throws InterruptedException {
        Thread.sleep(100);
        log.info("{} is ready...",name);
        try {
            barrier.await(200,TimeUnit.MILLISECONDS);
        } catch (BrokenBarrierException | TimeoutException e) {
            e.printStackTrace();
        }
        log.info("{} is ok...",name);
    }

}
