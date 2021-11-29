# learn-concurrency-in-java
## Thread management
1. Create, run, set characteristics of a thread
   1. If System.exit() is called from one of the running threads - all threads will terminate.
   2. You can not override thread's id and status
   3. Only the call to the start() method launches a new execution thread.
2. 
# TODO (Implement):
* Find all prime numbers from a range of N..M
* Use JMH to benchmark alternative solutions
* Web Server with endpoints to get different stats from a file. A JMeter test to check the serial and concurrent versions.

# TODO (Learn):
* CountDownLatch best practice
* Executors.newCachedThreadPool()
* Blocking/Non-blocking data structures