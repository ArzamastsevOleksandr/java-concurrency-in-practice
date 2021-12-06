package s03.thread_synchronization_utilities.s05;

import java.util.List;
import java.util.concurrent.Exchanger;

class Main {

    public static void main(String[] args) {
        var exchanger = new Exchanger<List<String>>();
        new Thread(new Consumer(exchanger)).start();
        new Thread(new Producer(exchanger)).start();
    }

}
