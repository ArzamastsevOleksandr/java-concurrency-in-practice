# learn-concurrency-in-java
## 1. Thread management
1. Create, run, set characteristics of a thread
   * If System.exit() is called from one of the running threads - all threads will terminate.
   * You can not override thread's id and status.
   * Only the call to the start() method launches a new execution thread.
2. Interrupt a thread
   * The Thread.interrupt() instance method will set the property of the thread to true. However, to stop thread execution the thread must handle the interruption signal on its own.
   * Thread.currentThread().isInterrupted() checks if the thread instance was interrupted.
   * Thread.interrupted() checks if the thread has been interrupted since it was last asked and resets the flag.
   * Sometimes the run() method might be complex (like having recursive calls). One can throw an InterruptedException and catch it in the run method to stop the recursive execution.
3. Sleep and resume a thread
   * There is a method yield() which tells the JVM that the thread can leave the CPU for other tasks. The JVM does not guarantee this behavior.
4. Wait for the finalization of the thread
5. Daemon thread
   * The JVM exits when the only threads running are all daemon threads. 
   * The setDaemon() method must be called before the start() or IllegalThreadStateException is thrown.
6. Process uncontrolled exceptions in a thread
   * If an uncaught exception is thrown from within a thread the JVM will look for the Thread.UncaughtExceptionHandler implementation. If none is found - the stacktrace is printed and the thread is terminated.
7. Thread local variables
   * FR: https://www.baeldung.com/java-threadlocal
   * FR: InheritableThreadLocal
8. Thread group
9. Thread factory
   * adv: Allows you to customize the thread parameters, collect statistics etc.
   * dis adv: One must make sure that threads are created via the thread factory to have a consistent codebase.
## 2. Basic thread synchronization
   * Critical section is a block of code that accesses the shared resource and can't be executed by more than one thread at the same time.
1. Synchronize a method
   * If one static and one instance methods are synchronized and modify the same data - 2 different threads may execute these methods and create a race condition.
   * Synchronized on the instance method implicitly uses "this" as the synchronization object.
   * Synchronized on the static method implicitly uses ".class" as the synchronization object.
   * Promote correctness over performance.
   * You can use recursive calls with synchronized methods, since the thread already has access to the synchronized methods of an object.
   * Limit the scope of the critical section.
   * Avoid calling I/O operations from the critical section as it may block other threads indefinitely.
2. Use conditions in synchronized code
3. Synchronize a block of code with a lock
   * A lock is a more powerful mechanism than a synchronized method/block of code. Where synchronized gives you control only over method/block of code, the lock allows you controlling more complex structures.
   * A lock has the tryLock() that returns false if lock was not acquired.
   * A lock supports fair/unfair modes via constructor parameter.
   * Ensure that the lock.unlock() is called in the finally statement.
   * A deadlock is a condition when two threads are waiting for the locks that will never be unlocked.
4. Synchronize data access with read/write locks
   * In cases when multiple read and few write operations are performed against a DS, a ReadWriteLock might improve concurrency performance.

# TODO (Implement):
   * An elevator simulation. The house has N floors and M elevators. People enter/leave the house using the elevator.
   * Producer-Consumer app. Producer sends data packages in order, consumer processes those in the same order. 
   * A shell script that creates a package with the Main class and main method
   * Find all prime numbers from a range of N..M
   * Use JMH to benchmark alternative solutions
   * Web Server with endpoints to get different stats from a file. A JMeter test to check the serial and concurrent versions.
   * Various deadlock simulations.

# TODO (Learn):
  * CountDownLatch best practice
  * ReadWriteLock
  * ReadWriteLock
  * Executors.newCachedThreadPool()
  * Blocking/Non-blocking data structures