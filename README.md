# learn-concurrency-in-java
## Thread management
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
5. 



# TODO (Implement):
* Find all prime numbers from a range of N..M
* Use JMH to benchmark alternative solutions
* Web Server with endpoints to get different stats from a file. A JMeter test to check the serial and concurrent versions.

# TODO (Learn):
* CountDownLatch best practice
* Executors.newCachedThreadPool()
* Blocking/Non-blocking data structures