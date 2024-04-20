package esercitazione4.bet.esercizio4_2;

public class Logging {

    public static enum Type {INFO, ERROR}

    private static String formatThread(Thread t) {
        if (t == null) return "";
        return "(" + t.getClass().getSimpleName() + "/" + t.getName() + ")";
    }

    private static String formatTag(String tag) {
        if (tag == null) return "";
        else return "[" + tag + "]";
    }

    static void printInfo(String message) {
        printInfo(null, null, message);
    }
    static<T extends Thread>  void printInfo(T t, String tag, String message) {
        System.out.println("INFO" + formatTag(tag) + " " + formatThread(t) + ": " + message);
    }

    static void printError(String message, Exception e) {
        printError(null, null, message, e);
    }

    static<T extends Thread> void printError(T t, String tag, String message, Exception e) {
        System.err.println("ERROR" + formatTag(tag) + " " + formatThread(t) + message + "\n JVM: " + e);
    }

    static void print(Type type, String message, String tag, Object obj, Exception e) {
        switch (type) {
            case INFO: {
                if (obj instanceof Thread) printInfo((Thread) obj, tag, message);
                else printInfo(message);
                break;
            }
            case ERROR:
                if (obj instanceof Thread) printError((Thread) obj,tag, message, e);
                else printError(message, e);
        }
    }
}
