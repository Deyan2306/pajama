package dark.cat.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * A utility class for managing threads and scheduling tasks in a centralized manner.
 * Provides a fixed-size thread pool and a scheduled thread pool for asynchronous and periodic task execution.
 *
 * <p>This class uses static methods to handle thread-related operations, making it easily accessible
 * throughout the application. It ensures thread safety and provides mechanisms for graceful shutdown.</p>
 *
 * <h3>Features:</h3>
 * <ul>
 *   <li>Asynchronous task execution using a fixed-size thread pool.</li>
 *   <li>Periodic task scheduling using a scheduled thread pool.</li>
 *   <li>Graceful shutdown of all threads.</li>
 *   <li>Automatic exception handling in submitted tasks.</li>
 * </ul>
 *
 * @author Deyan Sirakov
 * @version 1.0
 * @since 1.0
 */
public class ThreadManagerPool {

    // Default pool size based on the number of available CPU cores
    private static final int DEFAULT_THREAD_MANAGER_POOL_SIZE = Runtime.getRuntime().availableProcessors();

    // Fixed-size thread pool for executing asynchronous tasks
    private static final ExecutorService threadPool = Executors.newFixedThreadPool(DEFAULT_THREAD_MANAGER_POOL_SIZE);

    // Scheduled thread pool for periodic task scheduling
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

    /**
     * Submits a task for asynchronous execution in the fixed-size thread pool.
     *
     * @param task the {@link Runnable} task to execute
     */
    public void runAsync(Runnable task) {
        threadPool.submit(wrapTask(task));
    }

    /**
     * Schedules a task to run at a fixed rate using the scheduled thread pool.
     *
     * @param task the {@link Runnable} task to execute
     * @param initialDelay the initial delay before the task is first executed
     * @param period the time period between successive executions of the task
     * @param timeUnit the {@link TimeUnit} of the delay and period
     */
    public void scheduleAtFixedRate(Runnable task, long initialDelay, long period, TimeUnit timeUnit) {
        scheduler.scheduleAtFixedRate(wrapTask(task), initialDelay, period, timeUnit);
    }

    /**
     * Shuts down all threads gracefully.
     *
     * <p>Attempts to stop all actively executing tasks, halts the processing of waiting tasks,
     * and releases resources associated with the thread pools. If threads do not terminate within
     * the timeout, they are forcibly shut down.</p>
     */
    public static void shutdown() {
        try {
            threadPool.shutdown();
            scheduler.shutdown();
            if (!threadPool.awaitTermination(30, TimeUnit.SECONDS)) {
                threadPool.shutdownNow();
            }
            if (!scheduler.awaitTermination(30, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            threadPool.shutdownNow();
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Wraps a task to handle uncaught exceptions during execution.
     *
     * <p>This method ensures that any exception thrown by the task does not terminate the thread pool.
     * Exceptions are logged using {@link PajamaLogger}.</p>
     *
     * @param task the {@link Runnable} task to wrap
     * @return a wrapped {@link Runnable} with exception handling
     */
    private static Runnable wrapTask(Runnable task) {
        return () -> {
            try {
                task.run();
            } catch (Exception e) {
                PajamaLogger.error("Uncaught exception in thread: " + e.getMessage());
                e.printStackTrace();
            }
        };
    }

    /**
     * Creates a new thread and executes the provided task asynchronously.
     *
     * @param threadConsumer the thread callback to execute asynchronously
     */
    public void createNewThread(Consumer<Thread> threadConsumer) {
        Thread thread = new Thread(() -> {
            threadConsumer.accept(Thread.currentThread());
        });
        threadPool.submit(thread);
    }
}
