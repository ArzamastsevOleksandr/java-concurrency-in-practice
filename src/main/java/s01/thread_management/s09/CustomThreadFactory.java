package s01.thread_management.s09;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadFactory;

public class CustomThreadFactory implements ThreadFactory {

    private int counter;
    private final String baseName;
    private final List<String> stats = new ArrayList<>();

    public CustomThreadFactory(String baseName) {
        this.baseName = baseName;
    }

    @Override
    public Thread newThread(Runnable r) {
        var thread = new Thread(r, baseName + "-Thread_" + counter++);
        stats.add("Create thread " + thread.getId() + " with name " + thread.getName());
        return thread;
    }

    public String getStats() {
        return String.join("\n", stats);
    }

}
