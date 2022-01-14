import java.util.Scanner;

public class Debugger {
    private static Debugger debuggerInstance = null;

    private boolean debuggingEnabled;

    private Debugger() {
        debuggingEnabled = false;
    }

    public static Debugger getInstance() {
        if (debuggerInstance == null) {
            debuggerInstance = new Debugger();
        }

        return debuggerInstance;
    }

    public void enable() {
        debuggingEnabled = true;
    }

    public boolean enabled() {
        return debuggingEnabled;
    }

    public int getIntegerFromConsole() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
