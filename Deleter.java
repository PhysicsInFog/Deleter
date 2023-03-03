import java.io.File;

public class Deleter {
    static int found = 0;
    static int deleted = 0;
    static long start = System.currentTimeMillis();

    static void delete(File file) {
        if (file.isDirectory()) {
            try {
                for (File cfile : file.listFiles()) {
                    delete(cfile);
                }
            } catch (NullPointerException e) {
                System.out.println(String.format("[%d/%d/%d] Error %s", found, deleted, System.currentTimeMillis() - start, file.getPath()));
            }
        }

        if (file.delete()) {
            System.out.println(String.format("[%d/%d/%d] Deleted %s", found, deleted, System.currentTimeMillis() - start, file.getPath()));
            deleted++;
        } else {
            System.out.println(String.format("[%d/%d/%d] Unable to delete %s", found, deleted, System.currentTimeMillis() - start, file.getPath()));
        }
        found++;
    }

    public static void main(String[] args) {
        for (String arg : args) {
            delete(new File(arg));
        }
        System.out.println(String.format("\n%d found\n%d deleted\n%d ms running for", found, deleted, System.currentTimeMillis() - start));
    }
}
