package s03.thread_synchronization_utilities.s04.server;

class Main {

    public static void main(String[] args) {
        new Thread(new Server()).start();
    }

}
