package s002.file_search;

import java.io.File;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        File file = new File("/home/alex/git/learn-concurrency-in-java/src/main/java/s002/file_search");

        searchFiles(file, "dir1.txt").ifPresentOrElse(
                path -> System.out.println("Found file by path: " + path),
                () -> System.out.println("File not found")
        );

        searchFiles(file, "dir2.txt").ifPresentOrElse(
                path -> System.out.println("Found file by path: " + path),
                () -> System.out.println("File not found")
        );

        searchFiles(file, "dir3.txt").ifPresentOrElse(
                path -> System.out.println("Found file by path: " + path),
                () -> System.out.println("File not found")
        );

        searchFiles(file, "missing.txt").ifPresentOrElse(
                path -> System.out.println("Found file by path: " + path),
                () -> System.out.println("File not found")
        );
    }

    private static Optional<String> searchFiles(File file, String s) {
        File[] content = file.listFiles();
        if (content == null || content.length == 0) {
            return Optional.empty();
        }
        for (File f : content) {
            if (f.isDirectory()) {
                Optional<String> optionalS = searchFiles(f, s);
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
