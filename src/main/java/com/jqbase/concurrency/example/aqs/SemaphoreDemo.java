package com.jqbase.concurrency.example.aqs;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * <p>Description:</p>
 *
 * @author JQ on 2018/11/14 22:23
 */
@Slf4j
public class SemaphoreDemo {
    private final static Integer THREADNUM = 15;

    private final static Semaphore semphore = new Semaphore(3);

    public static void main(String[] args) throws InterruptedException {

        ExecutorService pool = getFixPool(2);

        for (int i = 0; i < THREADNUM; i++) {
            final int num = i+1;
            pool.execute(()->{
                try {
                    semphore.acquire();
                    task(Thread.currentThread().getName() +"-"+num);
                } catch (Exception e) {
                    log.error("exception"+e);
                } finally {
                    semphore.release();
                }
            });

        }
        pool.shutdown();
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
