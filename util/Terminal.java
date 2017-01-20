package util;

public abstract class Terminal {
    public static final String ANSI_CLS = "\u001b[2J";
    public static final String ANSI_HOME = "\u001b[H";
    public static final String CLEAR_TERMINAL = ANSI_CLS+ANSI_HOME;

    public static void clear() {
        System.out.print(CLEAR_TERMINAL);
    }
}
