package main;

import java.util.Scanner;
import service.TaskManager;
import util.Logger;

public class MainApplication {

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    TaskManager manager = new TaskManager();

    int choice;

    while (true) {

      // Menu
      Logger.section("TERMINAL TASK MANAGER");

      System.out.println("▶ [1] Add Task");
      System.out.println("  [2] View Tasks");
      System.out.println("  [3] Mark Task Complete");
      System.out.println("  [4] Delete Task");
      System.out.println("  [5] Search Task");
      System.out.println("  [6] Exit");
      System.out.println("  [7] View Task Details (TESTING)");

      System.out.print("\nSelect option → ");

      choice = sc.nextInt();
      sc.nextLine(); // clear buffer

      switch (choice) {

        case 1:
          Logger.info("Enter title:");
          String title = sc.nextLine();

          Logger.info("Enter description:");
          String description = sc.nextLine();

          Logger.info("Enter priority (HIGH/MEDIUM/LOW):");
          String priority = sc.nextLine();

          Logger.type("Processing...", 25);
          manager.addTask(title, description, priority);
          Logger.success("Task added successfully");
          System.out.println();
          Logger.info("Press Enter to continue...");
          sc.nextLine();
          break;

        case 2:
          Logger.type("Fetching tasks...", 25);
          manager.viewTasks();
          System.out.println();
          Logger.info("Press Enter to continue...");
          sc.nextLine();
          break;

        case 3:
          Logger.info("Enter task ID to mark complete:");
          int completeId = sc.nextInt();
          sc.nextLine();
          Logger.type("Updating task...", 25);
          manager.markTaskComplete(completeId);
          System.out.println();
          Logger.info("Press Enter to continue...");
          sc.nextLine();
          break;

        case 4:
          Logger.info("Enter task ID to delete:");
          int deleteId = sc.nextInt();
          sc.nextLine();
          Logger.type("Deleting task...", 25);
          manager.deleteTask(deleteId);
          System.out.println();
          Logger.info("Press Enter to continue...");
          sc.nextLine();
          break;

        case 5:
          Logger.info("Enter keyword to search:");
          String keyword = sc.nextLine();
          Logger.type("Searching...", 25);
          manager.searchTask(keyword);
          System.out.println();
          Logger.info("Press Enter to continue...");
          sc.nextLine();
          break;

        case 6:
          Logger.type("Shutting down...", 25);
          Logger.sys("Exiting application...");
          sc.close();
          return;

        case 7:
          Logger.info("Enter task ID:");
          int detailId = sc.nextInt();
          sc.nextLine();

          Logger.type("Fetching details...", 25);
          manager.viewTaskDetails(detailId);

          System.out.println();
          Logger.info("Press Enter to continue...");
          sc.nextLine();
          break;

        default:
          Logger.error("Invalid choice. Try again.");
          System.out.println();
          Logger.info("Press Enter to continue...");
          sc.nextLine();
      }
    }
  }
}
