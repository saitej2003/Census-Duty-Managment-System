package backend;

public class Main {

    // private Constructor to prevent Instantiation
    private Main() {
        throw new IllegalStateException("Utility class Main");
    }

    public static void run() {
        System.out.println("Hello World from Main!");
        PostgreSQLAccess.run();
    }
}
