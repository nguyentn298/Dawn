package com.dante.learn.core.thread.ThreadPoolTaskExecutor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.dante.config.ApplicationContext;
import com.dante.config.ProfileType;
import com.dante.test.TestApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { TestApplicationContext.class })
@ActiveProfiles(ProfileType.TEST)
public class TestMinAndMax {
	AnnotationConfigApplicationContext applicationContext;

	public static void main(String[] args) {
		TestMinAndMax test = new TestMinAndMax();
		test.myCounter();
	}

	public void myCounter() {
		applicationContext = new AnnotationConfigApplicationContext(TestApplicationContext.class);
		ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) applicationContext.getBean("taskExecutor");
		
		for(int i = 1; i < 20; i++) {
			
			try{
				taskExecutor.execute(new Counter("Thread " + i)); 
			} catch(TaskRejectedException e) {
				System.out.println("Reject: " + i + " --> " + e);
			}
			
		}
		
		for(;;) {
			// Same while(true)
			int count = taskExecutor.getActiveCount();
			int queue = taskExecutor.getThreadPoolExecutor().getQueue().size();
			System.out.println("Active Threads : " + count + " - " + "Queue size: " + queue);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (count == 0) {
				taskExecutor.shutdown();
				break;
			}
		}
	}
	
	@Test
	public void checkBean() {
		applicationContext = new AnnotationConfigApplicationContext(TestApplicationContext.class);
		String[] myBeans = applicationContext.getBeanDefinitionNames();
		for (String string : myBeans) {
			System.out.println("My bean: " + string);
		}
		
		((AbstractApplicationContext)applicationContext).close();
	}
}

class Counter implements Runnable {
	String name;

	Counter(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		System.out.println(name + " is running (Before)");

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(name + " is running (After)");
	};
}
