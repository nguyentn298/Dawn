package com.dante.learn.core.thread2;

import java.util.concurrent.ThreadPoolExecutor;

public class PoolUtil {

	public static void showPoolDetails (ThreadPoolExecutor executor, String tasksDetails) {
        StringBuilder builder = new StringBuilder();
        builder.append("Executor: ")
               .append(executor.getClass())
               .append("\ncore pool size: ")
               .append(executor.getCorePoolSize())
               .append("\nTask submitted: ")
               .append(executor.getTaskCount())
               .append("\nQueue: ")
               .append(executor.getQueue().getClass())
               .append("\nTasks details: ")
               .append(tasksDetails)
               .append("\n");
        System.out.println(builder);
    }
}
