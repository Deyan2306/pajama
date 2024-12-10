# ThreadManagerPool Documentation (v1.0.0)

The `ThreadManagerPool` utility class centralizes thread management and task scheduling in a Java application. It provides a fixed-size thread pool for asynchronous tasks and a scheduled thread pool for periodic executions. Additionally, it handles graceful thread shutdown and manages exceptions in tasks.

---

## Table of Contents
1. [Overview](#overview)
2. [Features](#features)
3. [Usage](#usage)
4. [Methods](#methods)
5. [Shutdown](#shutdown)
6. [Thread Safety](#thread-safety)
7. [Example Usage](#example-usage)
8. [Dependencies](#dependencies)

---

## Overview
`ThreadManagerPool` simplifies managing concurrent tasks in an application. By providing a shared pool of threads and automated exception handling, it reduces boilerplate code and ensures efficient resource usage.

---

## Features
- **Asynchronous Task Execution**: Run tasks in parallel using a fixed-size thread pool.
- **Periodic Task Scheduling**: Execute tasks at fixed intervals with a scheduled thread pool.
- **Graceful Shutdown**: Ensures all threads are terminated properly, even in case of application errors.
- **Exception Handling**: Prevents task exceptions from crashing the thread pool by logging errors and continuing execution.

---

## Usage
This class is designed to be used statically, offering centralized methods to manage threads and tasks.

---

## Methods

### `runAsync(Runnable task)`
Submits a task for asynchronous execution.

- **Parameters**:
    - `task`: The `Runnable` task to execute.

- **Description**: Uses the fixed-size thread pool to execute tasks without blocking the main thread.

---

### `scheduleAtFixedRate(Runnable task, long initialDelay, long period, TimeUnit timeUnit)`
Schedules a task to run periodically at a fixed rate.

- **Parameters**:
    - `task`: The `Runnable` task to execute.
    - `initialDelay`: The initial delay before the first execution.
    - `period`: The time period between successive executions.
    - `timeUnit`: The unit of time for `initialDelay` and `period`.

- **Description**: Uses the scheduled thread pool to execute tasks periodically.

---

### `shutdown()`
Gracefully shuts down all threads.

- **Description**:
    - Stops actively executing tasks.
    - Prevents waiting tasks from starting.
    - Releases resources associated with the thread pools.
    - If threads fail to terminate within 30 seconds, they are forcibly shut down.

---

### Private Method: `wrapTask(Runnable task)`
Wraps a task to catch and log exceptions during execution.

- **Parameters**:
    - `task`: The `Runnable` task to wrap.

- **Description**: Logs exceptions using `PajamaLogger` and ensures they do not terminate the thread pool.

---

## Shutdown
Call the `shutdown()` method during application termination to ensure all threads are stopped and resources are released.

---

## Thread Safety
`ThreadManagerPool` is designed to be thread-safe. Internally, it uses `ExecutorService` instances, which handle synchronization for concurrent task execution.

---

## Example Usage

### Asynchronous Task Execution
```java
ThreadManagerPool.runAsync(() -> {
    System.out.println("Running asynchronously on thread: " + Thread.currentThread().getName());
});
```

### Periodic Task Scheduling
```java
ThreadManagerPool.scheduleAtFixedRate(() -> {
    System.out.println("Periodic task running on thread: " + Thread.currentThread().getName());
}, 0, 10, TimeUnit.SECONDS);
```

### Graceful Shutdown
```java
ThreadManagerPool.shutdown();
```

---

## Dependencies
- **Java Concurrency**:
    - `ExecutorService`, `ScheduledExecutorService`, `Executors`, `TimeUnit`.
- **Logging**:
    - `PajamaLogger` for logging exceptions and status messages.

---

## Notes
- **Default Thread Pool Size**: The fixed-size thread pool size is determined by the number of available CPU cores.
- **Best Practices**: Always call `shutdown()` during application termination to avoid resource leaks or orphaned threads.