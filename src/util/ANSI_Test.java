package util;

public class ANSI_Test {

    public static void main(String[] args) {

        // Reset
        final String RESET = "\u001B[0m";

        // Colors
        final String RED = "\u001B[91m";
        final String GREEN = "\u001B[92m";
        final String YELLOW = "\u001B[93m";
        final String BLUE = "\u001B[94m";
        final String PURPLE = "\u001B[95m";
        final String CYAN = "\u001B[96m";
        final String WHITE = "\u001B[97m";

        System.out.println("\n===== ANSI COLOR TEST =====\n");

        System.out.println(RED + "[RED] This is red text" + RESET);
        System.out.println(GREEN + "[GREEN] This is green text" + RESET);
        System.out.println(YELLOW + "[YELLOW] This is yellow text" + RESET);
        System.out.println(BLUE + "[BLUE] This is blue text" + RESET);
        System.out.println(PURPLE + "[PURPLE] This is purple text" + RESET);
        System.out.println(CYAN + "[CYAN] This is cyan text" + RESET);
        System.out.println(WHITE + "[WHITE] This is white text" + RESET);

        System.out.println("\n===== BACKGROUND TEST =====\n");

        System.out.println("\u001B[41m" + "[BG RED]" + RESET);
        System.out.println("\u001B[42m" + "[BG GREEN]" + RESET);
        System.out.println("\u001B[43m" + "[BG YELLOW]" + RESET);
        System.out.println("\u001B[44m" + "[BG BLUE]" + RESET);
        System.out.println("\u001B[45m" + "[BG PURPLE]" + RESET);
        System.out.println("\u001B[46m" + "[BG CYAN]" + RESET);
        System.out.println("\u001B[47m" + "[BG WHITE]" + RESET);

        System.out.println("\n===== STYLE TEST =====\n");

        System.out.println("\u001B[1m[BOLD] Bold text" + RESET);
        System.out.println("\u001B[3m[ITALIC] Italic text" + RESET);
        System.out.println("\u001B[4m[UNDERLINE] Underlined text" + RESET);

        System.out.println("\n===== DONE =====\n");
    }
}