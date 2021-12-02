package s02.basic_thread_synchronization.s02;

public class Main {

    public static void main(String[] args) {
        var eventStorage = new EventStorage();
        var consumer = new Thread(new Consumer(eventStorage));
        var producer = new Thread(new Producer(eventStorage));

        consumer.start();
        producer.start();
    }

}
