package org.Astatine.r10.Util.Function;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class AsyncTaskRunner {
    private static class Holder {
        private static final AsyncTaskRunner INSTANCE = new AsyncTaskRunner();
    }

    public static AsyncTaskRunner getThreadPool() {
        return Holder.INSTANCE;
    }


    private ExecutorService executorService;
    private final ScheduledExecutorService scheduledExecutorService;

    private AsyncTaskRunner() {
        this.executorService = new ThreadPoolExecutor(
            2,
            4,
            100L,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>()
            );
        /*
//        https://engineerinsight.tistory.com/197
//        executorService = Executors.newFixedThreadPool(4);
        executorService =  new ThreadPoolExecutor(
            CORE_POOL_SIZE, //기본 thread core 1개 유지
            MAX_CORE_POOL_SIZE,  // 최대 쓰레딩 wait 갯수 4
            KEEP_ALIVE_TIME, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(QUEUE_CAPACITY)
        );
        */

        this.scheduledExecutorService = Executors.newScheduledThreadPool(4);
    }

    public void addTask(Runnable task) {
        this.executorService.submit(task);
    }

    public void executorServiceOff() {
        this.executorService.shutdown();
    }

    public void addSchedulingTaskMin(Runnable task, long delay, long interval) {
        this.scheduledExecutorService.scheduleWithFixedDelay(task, delay, interval, TimeUnit.MINUTES);
    }

    public synchronized void addSchedulingTaskSec(Runnable task, long delay, long interval) {
        this.scheduledExecutorService.scheduleWithFixedDelay(task, delay, interval, TimeUnit.SECONDS);
    }

    public void allServiceOff() {
//        executorService.shutdownNow();
        this.scheduledExecutorService.shutdownNow();
    }
}