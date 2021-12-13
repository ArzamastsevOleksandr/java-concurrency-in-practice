package s05.fork_join_framework.s04;

import java.util.concurrent.ConcurrentLinkedDeque;

class TaskManager {

    final ConcurrentLinkedDeque<SearchNumberTask> tasks = new ConcurrentLinkedDeque<>();

    void add(SearchNumberTask task) {
        tasks.add(task);
    }

    void cancelTasks(SearchNumberTask task) {
        tasks.stream().filter(t -> t != task).forEach(t -> {
            t.cancel(true);
            t.logCancelMessage();
        });
    }

}
