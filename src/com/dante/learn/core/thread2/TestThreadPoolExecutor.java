package com.dante.learn.core.thread2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestThreadPoolExecutor {
	public static void main(String[] args) {
		MyRejectedExecutionHandler rejectionHandler = new MyRejectedExecutionHandler();
		ThreadFactory threadFactory = Executors.defaultThreadFactory();

		/**
		 * ThreadPoolExecutor extends AbstractExecutorService
		 * AbstractExecutorService implements ExecutorService
		 */
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 10,
				TimeUnit.SECONDS, 
				new ArrayBlockingQueue<Runnable>(3), // workQueue the queue to use for holding tasks before they are executed.
				threadFactory, 
				rejectionHandler);
		
		/**
		 * Run sub task to check information of ThreadPoolExecutor
		 */
		SubTask subTask = new SubTask(threadPoolExecutor, 3);
		Thread monitorTask = new Thread(subTask);
		monitorTask.start();

		/**
		 *	Create number thread of MainTask
		 */
		for (int i = 0; i < 10; i++) {
			MainTask mainTask = new MainTask("i = " + i);
			threadPoolExecutor.execute(mainTask);
		}

		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		threadPoolExecutor.shutdown();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		subTask.shutdown();

	}
}

/**
 *	Main Task
 */
class MainTask implements Runnable {
	 
    private String command;
    
    public MainTask(String s){
        this.command=s;
    }

    @Override
    public void run() {
        System.out.println("[MainTask] "+command + " Started -- "+ Thread.currentThread().getName());
        processCommand();
        System.out.println("[MainTask] "+command + " Finished -- "+ Thread.currentThread().getName());
    }

    private void processCommand() {
        try {
        	System.out.println("[MainTask] is working");
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString(){
        return this.command;
    }
}

/**
 *	Sub Task
 */
class SubTask implements Runnable {

	private ThreadPoolExecutor executor;
	private int seconds;
	private boolean run = true;

	public SubTask(ThreadPoolExecutor executor, int delay) {
		this.executor = executor;
		this.seconds = delay;
	}
	public void shutdown() {
		this.run = false;
	}
	@Override
	public void run() {
		while (run) {
			System.out.println(String.format(
					"[SubTask][PoolSize/CorePoolSize] [%d/%d] ActiveCount: %d, CompletedTaskCount: %d, TaskCount: %d, isShutdown: %s, isTerminated: %s",
					this.executor.getPoolSize(), 			// Current number of threads in the pool.
					this.executor.getCorePoolSize(),		// Core number of threads.
					this.executor.getActiveCount(),			// Approximate number of threads that are actively executing tasks.
					this.executor.getCompletedTaskCount(),	// Completed Task
					this.executor.getTaskCount(), 			// Total task (maximumPoolSize + workQueue of ThreadPoolExecutor)
					this.executor.isShutdown(),
					this.executor.isTerminated()));
			try {
				Thread.sleep(seconds * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}

/**
 * Thread Reject Controll
 */
class MyRejectedExecutionHandler implements RejectedExecutionHandler {

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		System.out.println(r.toString() + " is rejected");
	}

}
