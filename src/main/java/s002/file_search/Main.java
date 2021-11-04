package s002.file_search;

import java.io.File;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;

public class Main {

    // todo: automatic directory generation with multiple files inside. automatic directory clean up. class design.
    public static void main(String[] args) {
        File file = new File("/home/alex/git/learn-concurrency-in-java/src/main/java/s002/file_search");

        searchFilesLinear(file, "dir1.txt").ifPresentOrElse(
                path -> System.out.println("Found file by path: " + path),
                () -> System.out.println("File not found")
        );
        searchFilesLinear(file, "dir2.txt").ifPresentOrElse(
                path -> System.out.println("Found file by path: " + path),
                () -> System.out.println("File not found")
        );
        searchFilesLinear(file, "dir3.txt").ifPresentOrElse(
                path -> System.out.println("Found file by path: " + path),
                () -> System.out.println("File not found")
        );
        searchFilesLinear(file, "missing.txt").ifPresentOrElse(
                path -> System.out.println("Found file by path: " + path),
                () -> System.out.println("File not found")
        );

        Queue<File> directories = Arrays.stream(Objects.requireNonNull(file.listFiles()))
                .filter(File::isDirectory)
                .collect(toCollection(ConcurrentLinkedQueue::new));

//        int availableProcessors = Runtime.getRuntime().availableProcessors();
        int availableProcessors = 2;

        List<FileSearchTask> tasks = IntStream.range(0, availableProcessors)
                .mapToObj(i -> new FileSearchTask("dir3.txt", directories))
                .collect(toList());

        List<Thread> threads = tasks.stream()
                .map(Thread::new)
                .collect(toList());

        threads.forEach(Thread::start);

        boolean done = false;
        int finishedCount = 0;
        while (!done) {
            for (int i = 0; i < availableProcessors; ++i) {
                FileSearchTask fileSearchTask = tasks.get(i);
                Thread thread = threads.get(i);
                if (thread.getState() == Thread.State.TERMINATED) {
                    finishedCount++;
                    if (fileSearchTask.getFoundFilePath().isPresent()) {
                        done = true;
                    }
                }
                if (finishedCount == availableProcessors) {
                    done = true;
                }
            }
        }
        if (finishedCount != availableProcessors) {
            threads.forEach(Thread::interrupt);
        }

        tasks.stream()
                .map(FileSearchTask::getFoundFilePath)
                .filter(Optional::isPresent)
                .findAny()
                .map(Optional::get)
                .ifPresentOrElse(
                        path -> System.out.println("Found path: " + path),
                        () -> System.out.println("Path not found")
                );
    }

    private static class FileSearchTask implements Runnable {

        String filename;
        Queue<File> directories;
        String foundFilePath;

        public FileSearchTask(String filename, Queue<File> directories) {
            this.filename = filename;
            this.directories = directories;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " is running");
            try {
                while (directories.size() > 0) {
                    File file = directories.poll();
                    // imitate long work to see if it is interrupted
                    Thread.sleep((long)(Math.random() * 1000 + 500));
                    foundFilePath = file == null ? null : processDirectory(file, filename);
                }
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrupted");
            }
            System.out.println(Thread.currentThread().getName() + " finished");
        }

        private String processDirectory(File file, String filename) throws InterruptedException {
            File[] files = file.listFiles();
            if (files == null || files.length == 0) {
                return null;
            }
            for (File f : files) {
                if (f.isDirectory()) {
                    String path = processDirectory(f, filename);
                    if (Thread.currentThread().isInterrupted()) {
                        throw new InterruptedException();
                    }
                    if (path != null) {
                        return path;
                    }
                } else {
                    if (f.getName().equals(filename)) {
                        return f.getAbsolutePath();
                    }
                    if (Thread.currentThread().isInterrupted()) {
                        throw new InterruptedException();
                    }
                }
            }
            return null;
        }

        public Optional<String> getFoundFilePath() {
            return Optional.ofNullable(foundFilePath);
        }

    }

    private static Optional<String> searchFilesLinear(File file, String s) {
        File[] content = file.listFiles();
        if (content == null || content.length == 0) {
            return Optional.empty();
        }
        for (File f : content) {
            if (f.isDirectory()) {
                Optional<String> optionalS = searchFilesLinear(f, s);
                if (optionalS.isPresent()) {
                    return optionalS;
                }
            } else {
                if (f.getName().equals(s)) {
                    return Optional.of(f.getAbsolutePath());
                }
            }
        }
        return Optional.empty();
    }

}
