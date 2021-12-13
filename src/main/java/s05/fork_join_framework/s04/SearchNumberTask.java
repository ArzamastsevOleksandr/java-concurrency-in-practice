package s05.fork_join_framework.s04;

import lombok.RequiredArgsConstructor;
import util.ThreadUtil;

import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

@RequiredArgsConstructor
class SearchNumberTask extends RecursiveTask<Integer> {

    final int[] source;
    final int start, end;
    final int target;
    final TaskManager taskManager;

    @Override
    protected Integer compute() {
        System.out.printf("Task [%d-%d] START\n", start, end);
        return end - start > 10 ? launchTasks() : findNumber();
    }

    private Integer launchTasks() {
        int mid = (start + end) / 2;

        var task1 = new SearchNumberTask(source, start, mid, target, taskManager);
        var task2 = new SearchNumberTask(source, mid, end, target, taskManager);

        taskManager.add(task1);
        taskManager.add(task2);

        task1.fork();
        task2.fork();

        int result = task1.join();
        if (result != -1) {
            return result;
        }
        return task2.join();
    }

    private Integer findNumber() {
        int index = IntStream.range(start, end)
                .peek(i -> ThreadUtil.sleepMillis(500))
                .filter(i -> source[i] == target)
                .peek(i -> {
                    System.out.printf("Found %d at index %d\n", target, i);
                    taskManager.cancelTasks(this);
                }).findFirst()
                .orElse(-1);
        ThreadUtil.sleepSeconds(1);
        return index;
    }

    void logCancelMessage() {
        System.out.printf("Canceled [%d-%d]\n", start, end);
    }

}
