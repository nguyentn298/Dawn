package com.dante.learn.core.thread2;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.dante.learn.core.thread.threadPool.WorkerThread;

/**
 * IMPORTANT: Unit test with ScheduleExectuorService has problem
 * because your unit test exits before the scheduler executes your Runnable.
 */
public class TestScheduledExecutorService {

	private ScheduledExecutorService service;
	MyTask myTask;
	long startTime;
	long endTime;
	
	public static void main(String[] args) throws InterruptedException {
		Runnable runnable = new Runnable() {
			public void run() {
				// task to run goes here
				System.out.println("Hello !!");
			}
		};

		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		service.scheduleAtFixedRate(runnable, 0, 3, TimeUnit.SECONDS);
		
	}
	
    @Before
    public void setUp() throws Exception {
    	startTime = System.currentTimeMillis()/1000;
    	System.out.println("Start setUp at:" + startTime);
    	service = Executors.newSingleThreadScheduledExecutor();
    	myTask = new MyTask("--> Initial Thread <--");
		System.out.println("Finished setUp");
    }

    @After
    public void tearDown() throws Exception {
    	System.out.println("Start tearDown");
//    	service.awaitTermination(10, TimeUnit.SECONDS);
    	
    	/**
    	 * Unit test will auto shutdown with ScheduledExecutorService
    	 * So, this loop will prevent from that
    	 */
    	while (!service.isTerminated()) {
    		Thread.sleep(2000);
    	}

    }
    
	/**
	 *	Repeat after every PERIOD unit (From start of task)
	 *	Example: After initial Delay, Task A start at 6:00 and finish at 7:00 with PERIOD Unit = 90' (Mean 6:00 --> 7:30)
	 *	It mean, after finishing Task A, Task B will B will wait to 7:30 (finish PERIOD unit) to Start new task.
	 *	Task B will start at 7:30
	 *	That is executions will commence after initialDelay and 
	 */
	@Test
	public void testScheduleAtFixedRate() throws InterruptedException {
		
		long initialDelay = 1; 		// After initialDelay, The first task will be started.
		long period = 4;			// After period, Next task will be started.

		service.scheduleAtFixedRate(myTask, initialDelay, period, TimeUnit.SECONDS);
		PoolUtil.showPoolDetails((ThreadPoolExecutor) service,
				"For each task" + " initial delay: 500 ms,"
						+ " delay period: 1000 ms,"
						+ " repeat policy: fixed-rate");

		/**
		 * If a task takes longer to execute than the period between its scheduled executions, 
		 * the next execution will start after the current execution finishes. 
		 * The scheduled task will not be executed by more than one thread at a time. 
		 */
//		TimeUnit.SECONDS.sleep(3);
//		Thread.sleep(6000); // sleep must be > 15250(initialDelay(5000) + period(10000))
//		service.shutdown();
//		service.awaitTermination(5, TimeUnit.SECONDS);
	}

	/**
	 *	Repeat after every DELAY unit (From END of task), different from scheduleAtFixedRate
	 *	Example: After initial Delay, Task A start at 6:00 and finish at 7:00 with DELAY Unit = 90' (Mean 7:00 --> 8:30)
	 *	It mean, after finishing Task A, Task B will B will wait about 90' (finish DELAY unit) to Start new task.
	 *	Task B will start at 8:30
	 */
	@Test
	public void testScheduleWithFixedDelay() throws InterruptedException {

        long initialDelay = 1; 	// After initialDelay, The first task will be started.
		long delay = 4;			// After initialDelay + delay + timeOfTask, Next task will be started
        service.scheduleWithFixedDelay(myTask, initialDelay, delay, TimeUnit.SECONDS);

        PoolUtil.showPoolDetails((ThreadPoolExecutor) service, "For each task" +
                            " initial delay: 500 ms," +
                            " delay period: 1000 ms," +
                            " repeat policy: fixed-delay");

        /**
         * In this method, however, the period is interpreted as the delay between the end of the previous execution, until the start of the next. 
         * The delay is thus between finished executions, not between the beginning of executions. 
         */
	}

	/**
	 *	worker is runnable or callable 
	 */
	@Test
	public void testSchedule()  throws InterruptedException {
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);

		System.out.println("Current Time = " + new Date()); // schedule to run after sometime
		for (int i = 0; i < 3; i++) {
			Thread.sleep(1000);
			WorkerThread worker = new WorkerThread("do heavy processing");
			scheduledThreadPool.schedule(worker, 10, TimeUnit.SECONDS);
		}

		// add some delay to let some threads spawn by scheduler
		Thread.sleep(30000);

		scheduledThreadPool.shutdown();
		while (!scheduledThreadPool.isTerminated()) {
			// wait for all tasks to finish
		}
		System.out.println("Finished all threads");
	}
}
class MyTask implements Runnable {
    private static final long start;
    private final String name;
    private long repeatCount;
    private long taskStart;

    static {
        start = System.currentTimeMillis();
    }

    public MyTask (String s) {
        this.name = s;
    }

    @Override
    public void run () {
    	System.out.println("Start Task");
        taskStart = System.currentTimeMillis();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        repeatCount++;
        printTaskInfo();
        System.out.println("Finish Task");
    }

    private void printTaskInfo () {
        StringBuilder builder = new StringBuilder(" ")
                            .append(name)
                            .append(" - Repeat count: ")
                            .append(repeatCount)
                            .append(" - Exec At: ")
                            .append(taskStart - start)
                            .append(" - Task duration: " + (System.currentTimeMillis() - taskStart));

        System.out.println(builder);
    }

    public String getName () {
        return name;
    }
}
