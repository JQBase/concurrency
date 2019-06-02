package com.jqbase.concurrency;

import com.jqbase.concurrency.annotations.NotRecommend;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ConcurrencyApplicationTests {

	private static Integer count = 0;

	@NotRecommend
	@Test
	public void concurrentAdd(){
		ExecutorService pool = Executors.newCachedThreadPool();
		CountDownLatch latch = new CountDownLatch(1000);
		Semaphore semaphore = new Semaphore(10);
		for (int i = 0; i < 1000; i++) {

			pool.execute(()->{
				try {
					semaphore.acquire();
					count++;
					semaphore.release();
				} catch (InterruptedException e) {
					log.error("exception:"+e);
				}
			});
			latch.countDown();
		}

		System.out.println("count:"+count);

	}

	@Test
	public void contextLoads() {

	}

}
