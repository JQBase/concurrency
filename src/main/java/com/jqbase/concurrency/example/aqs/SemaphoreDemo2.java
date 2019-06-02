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
public class SemaphoreDemo2 {
    private final static Integer THREADNUM = 15;

    private final static Semaphore semphore = new Semaphore(3);

    public static void main(String[] args) throws InterruptedException {

//        ExecutorService pool = getFixPool(3);
        ExecutorService pool = Executors.newCachedThreadPool();

        for (int i = 0; i < THREADNUM; i++) {
            final int num = i+1;
            pool.execute(()->{
                try {
                    if (semphore.tryAcquire()){
                        task(Thread.currentThread().getName() +"-"+num);
                        semphore.release();
                    }

                } catch (Exception e) {
                    log.error("exception" + e);
                }
            });

        }
        pool.shutdown();
    }
    /**
     * 注意keepAliveTime的长度大小决定了线程获取不了‘许可’后存活时间，如果大于等于获取许可的时间，该线程最终还是可以或许到许可继续运行的
      * @param num
     * @return
     */
    public static ExecutorService getFixPool(Integer num) {

        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("concurrency-threads-%d").build();
        return new ThreadPoolExecutor(num, num,
                100, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(),threadFactory,new ThreadPoolExecutor.AbortPolicy());
    }

    public static void task(String name) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println(name);
    }


}
