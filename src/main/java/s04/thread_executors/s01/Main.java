package s04.thread_executors.s01;

import java.util.stream.IntStream;

class Main {

    public static void main(String[] args) {
        System.out.println("Main START");
        var server = new Server();
        IntStream.rangeClosed(1, Runtime.getRuntime().availableProcessors())
                .mapToObj(i -> new Task("Task " + i))
                .forEach(server::executeTask);

        server.stop();

        IntStream.range(1000, 1005)
                .mapToObj(i -> new Task("Task " + i))
                .forEach(server::executeTask);

        System.out.println("Main END");
    }

}
