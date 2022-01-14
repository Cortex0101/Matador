public class Debugger {
    private static Debugger debuggerInstance = null;

    private boolean debuggingEnabled;

    public void enable() {
        debuggingEnabled = true;
    }

    public boolean enabled() {
        return debuggingEnabled;
    }

    private Debugger() {
        debuggingEnabled = false;
    }

    public static Debugger getInstance() {
        if (debuggerInstance == null) {
            debuggerInstance = new Debugger();
        }

        return debuggerInstance;
    }
}
