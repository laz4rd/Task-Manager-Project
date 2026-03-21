package util;

public class Logger {

  /*
   * =============================================================================
   * ================================ ANSI Colors ================================
   * =============================================================================
   */
  private static final String RESET = "\u001B[0m";

  private static final String RED = "\u001B[91m";
  private static final String GREEN = "\u001B[92m";
  private static final String YELLOW = "\u001B[93m";
  private static final String BLUE = "\u001B[94m";
  private static final String PURPLE = "\u001B[95m";
  private static final String CYAN = "\u001B[96m";
  private static final String WHITE = "\u001B[97m";

  /*
   * ======================================================================================
   * ==================================== Core print method ===============================
   * ======================================================================================
   */

  private static void print(String tag, String color, String message) {
    String icon;
    switch (tag) {
      case "SUCCESS":
        icon = "✔";
        break;
      case "ERROR":
        icon = "✖";
        break;
      case "WARN":
        icon = "⚠";
        break;
      case "INFO":
        icon = "ℹ";
        break;
      case "SYS":
        icon = "⚙";
        break;
      default:
        icon = "•";
    }

    System.out.println(color + "[" + icon + " " + tag + "] " + RESET + message);
  }

  /*
   * ======================================================================================
   * ==================================== Log Types =======================================
   * ======================================================================================
   */

  public static void log(String message) {
    print("LOG", WHITE, message);
  }

  public static void info(String message) {
    print("INFO", BLUE, message);
  }

  public static void success(String message) {
    print("SUCCESS", GREEN, message);
  }

  public static void warn(String message) {
    print("WARN", YELLOW, message);
  }

  public static void error(String message) {
    print("ERROR", RED, message);
  }

  public static void sys(String message) {
    print("SYS", PURPLE, message);
  }

  /*
   * ======================================================================================
   * ==================================== Clear Screen ====================================
   * ======================================================================================
   */

  public static void clear() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  /*
   * ======================================================================================
   * ==================================== Section Header ==================================
   * ======================================================================================
   */

  public static void section(String title) {
    clear();

    int width = Math.max(42, title.length() + 10);

    String border = "═".repeat(width);
    String paddedTitle = " " + title + " ";

    int padding = (width - paddedTitle.length()) / 2;
    String centered = " ".repeat(padding) + paddedTitle + " ".repeat(padding);

    if (centered.length() < width) {
      centered += " ";
    }

    System.out.println();
    System.out.println(PURPLE + "╔" + border + "╗" + RESET);
    System.out.println(PURPLE + "║" + centered + "║" + RESET);
    System.out.println(PURPLE + "╚" + border + "╝" + RESET);
    System.out.println();
  }

  /*
   * ======================================================================================
   * ==================================== Divider =========================================
   * ======================================================================================
   */

  public static void divider() {
    System.out.println(CYAN + "────────────────────────────────────────────" + RESET);
  }

  /*
   * ======================================================================================
   * ==================================== Center Text ======================================
   * ======================================================================================
   */

  public static String center(String text, int width) {
    int padding = (width - text.length()) / 2;
    if (padding < 0)
      padding = 0;
    return " ".repeat(padding) + text;
  }

  private static String truncate(String text, int maxLength) {
    if (text == null) return "";
    if (text.length() <= maxLength) return text;
    return text.substring(0, maxLength - 3) + "..";
  }

  /*
   * ======================================================================================
   * ============================= Sequential / Typing Effect =============================
   * ======================================================================================
   */

  public static void type(String message, int delay) {
    for (char c : message.toCharArray()) {
      System.out.print(c);
      System.out.flush(); // ensures real-time output
      try {
        Thread.sleep(delay);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
    System.out.println();
  }

  public static void type(String message) {
    type(message, 25); // default speed
  }
  /*
   * ======================================================================================
   * ==================================== Task Box UI =====================================
   * ======================================================================================
   */

  public static void taskBox(int id, String title, String priority, boolean isCompleted) {

    title = truncate(title, 30);
    priority = truncate(priority, 10);

    String status = isCompleted ? "Completed" : "Pending";
    String statusColor = isCompleted ? GREEN : YELLOW;

    int width = 42;
    String border = "═".repeat(width);

    System.out.println(PURPLE + "╔" + border + "╗" + RESET);

    printLine("▶ Task #" + id, width, WHITE);
    printLine("  Title    : " + title, width, WHITE);
    printLine("  Priority : " + priority, width, WHITE);

    // Handle colored status separately (ANSI-safe padding)
    String rawStatus = "  Status   : " + status;
    int padding = width - rawStatus.length();
    if (padding < 0) padding = 0;

    System.out.println(PURPLE + "║" + WHITE +
        rawStatus.replace(status, statusColor + status + RESET + WHITE) +
        " ".repeat(padding) +
        PURPLE + "║" + RESET);

    System.out.println(PURPLE + "╚" + border + "╝" + RESET);
    System.out.println();
  }

  private static void printLine(String text, int width, String color) {
    int padding = width - text.length();
    if (padding < 0) padding = 0;

    System.out.println(PURPLE + "║" + color + text + " ".repeat(padding) + PURPLE + "║" + RESET);
  }
}
