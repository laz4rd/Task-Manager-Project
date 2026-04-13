package main;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import service.TaskManager;
import util.Logger;
import exception.InvalidTaskException;
import exception.TaskNotFoundException;
import model.User;
import model.AdminUser;
import model.Task;

public class MainApplication {

  private static User currentUser;
  private static List<User> users;
  private static TaskManager manager;
  private static Scanner sc;

  public static void main(String[] args) {
    sc = new Scanner(System.in);
    manager = new TaskManager();
    initializeUsers();

    while (true) {
      if (currentUser == null) {
        if (!showPreLoginMenu()) {
          break;
        }
      } else {
        showMenu();
        System.out.print("\nSelect option → ");

        int choice;
        try {
          choice = sc.nextInt();
          sc.nextLine();
        } catch (Exception e) {
          Logger.error("Invalid input. Please enter a number.");
          sc.nextLine();
          continue;
        }

        if (!handleMenuChoice(choice)) {
          break;
        }
      }
    }

    sc.close();
  }

  private static boolean showPreLoginMenu() {
    Logger.section("TASK MANAGER");
    System.out.println("▶ [1] Login");
    System.out.println("  [2] View Available Users");
    System.out.println("  [3] Create New Account");
    System.out.println("  [4] Exit");
    System.out.print("\nSelect option → ");

    int choice;
    try {
      choice = sc.nextInt();
      sc.nextLine();
    } catch (Exception e) {
      Logger.error("Invalid input. Please enter a number.");
      sc.nextLine();
      return true;
    }

    switch (choice) {
      case 1:
        if (login()) {
          Logger.success("Welcome, " + currentUser.getFullName() + "!");
          Logger.type("Loading dashboard...", 20);
        }
        break;
      case 2:
        showAvailableUsers();
        break;
      case 3:
        createNewUser();
        break;
      case 4:
        exit();
        return false;
      default:
        Logger.error("Invalid choice. Try again.");
        pause();
    }
    return true;
  }

  private static void showAvailableUsers() {
    Logger.section("AVAILABLE USERS (DEMO MODE - PASSWORDS VISIBLE)");
    System.out.println();

    for (User user : users) {
      if (user.isActive()) {
        String role = user instanceof AdminUser ? " [ADMIN]" : " [USER]";
        System.out.println("  • " + user.getUsername() + " / " + user.getPassword() + role);
      }
    }
    System.out.println();
    Logger.info("Total active users: " + users.stream().filter(User::isActive).count());
    pause();
  }

  private static void createNewUser() {
    Logger.section("CREATE NEW ACCOUNT");

    Logger.info("Enter username:");
    String username = sc.nextLine();

    for (User user : users) {
      if (user.getUsername().equalsIgnoreCase(username)) {
        Logger.error("Username already taken");
        pause();
        return;
      }
    }

    Logger.info("Enter password:");
    String password = sc.nextLine();

    int newId = users.size() + 1;
    User newUser = new User(newId, username, password, username, "no-email");
    users.add(newUser);

    Logger.success("Account created successfully!");
    Logger.info("You can now login with: " + username);
    pause();
  }

  private static void initializeUsers() {
    users = new ArrayList<>();
    users.add(new User(1, "user1", "pass123", "John Doe", "john@example.com"));
    users.add(new User(2, "user2", "pass456", "Jane Smith", "jane@example.com"));
    users.add(new AdminUser(3, "admin", "admin123", "System Admin", "admin@example.com"));
  }

  private static boolean login() {
    int attempts = 0;
    int maxAttempts = 3;

    while (attempts < maxAttempts) {
      System.out.print("Username: ");
      String username = sc.nextLine();

      System.out.print("Password: ");
      String password = sc.nextLine();

      for (User user : users) {
        if (user.getUsername().equals(username) && user.validatePassword(password)) {
          if (!user.isActive()) {
            Logger.error("Account is deactivated.");
            return false;
          }
          currentUser = user;
          return true;
        }
      }

      attempts++;
      Logger.error("Invalid credentials. Attempts remaining: " + (maxAttempts - attempts));
    }

    return false;
  }

  private static void showMenu() {
    Logger.clear();

    String role = currentUser.getRole();
    String header = role + " DASHBOARD";

    Logger.section(header);
    Logger.info("Logged in as: " + currentUser.getFullName() + " (" + role + ")");
    System.out.println();

    System.out.println("▶ [1] Add Task");
    System.out.println("  [2] View Tasks");
    System.out.println("  [3] Mark Task Complete");
    System.out.println("  [4] Delete Task");
    System.out.println("  [5] Search Task");
    System.out.println("  [6] View Task Details");
    System.out.println("  [7] View My Tasks");

    if (currentUser instanceof AdminUser) {
      System.out.println("  [8] Manage Users");
      System.out.println("  [9] System Settings");
      System.out.println("  [10] Logout");
      System.out.println("  [11] Exit");
    } else {
      System.out.println("  [8] Logout");
      System.out.println("  [9] Exit");
    }
  }

  private static boolean handleMenuChoice(int choice) {
    boolean isAdmin = currentUser instanceof AdminUser;

    if (isAdmin) {
      return handleAdminChoice(choice);
    } else {
      return handleUserChoice(choice);
    }
  }

  private static boolean handleUserChoice(int choice) {
    switch (choice) {
      case 1:
        addTask();
        break;
      case 2:
        viewTasks();
        break;
      case 3:
        markTaskComplete();
        break;
      case 4:
        deleteTask();
        break;
      case 5:
        searchTask();
        break;
      case 6:
        viewTaskDetails();
        break;
      case 7:
        viewMyTasks();
        break;
      case 8:
        logout();
        return true;
      case 9:
        exit();
        return false;
      default:
        Logger.error("Invalid choice. Try again.");
        pause();
    }
    return true;
  }

  private static boolean handleAdminChoice(int choice) {
    AdminUser admin = (AdminUser) currentUser;

    switch (choice) {
      case 1:
        addTask();
        break;
      case 2:
        viewTasks();
        break;
      case 3:
        if (admin.canEditAnyTask()) {
          markTaskComplete();
        } else {
          permissionDenied();
        }
        break;
      case 4:
        if (admin.canDeleteAnyTask()) {
          deleteTask();
        } else {
          permissionDenied();
        }
        break;
      case 5:
        searchTask();
        break;
      case 6:
        viewTaskDetails();
        break;
      case 7:
        viewMyTasks();
        break;
      case 8:
        if (admin.canManageUsers()) {
          manageUsers();
        } else {
          permissionDenied();
        }
        break;
      case 9:
        systemSettings();
        break;
      case 10:
        logout();
        return true;
      case 11:
        exit();
        return false;
      default:
        Logger.error("Invalid choice. Try again.");
        pause();
    }
    return true;
  }

  private static void addTask() {
    if (!currentUser.hasPermission("CREATE_TASK")) {
      permissionDenied();
      return;
    }

    Logger.info("Enter title:");
    String title = sc.nextLine();

    Logger.info("Enter description:");
    String description = sc.nextLine();

    Logger.info("Enter priority (HIGH/MEDIUM/LOW):");
    String priority = sc.nextLine();

    Logger.type("Processing...", 25);
    try {
      manager.addTask(title, description, priority);
      Logger.success("Task added successfully");
    } catch (InvalidTaskException e) {
      Logger.error(e.getMessage());
    }
    pause();
  }

  private static void viewTasks() {
    Logger.type("Fetching tasks...", 25);
    manager.viewTasks();
    pause();
  }

  private static void markTaskComplete() {
    Logger.info("Enter task ID to mark complete:");
    try {
      int completeId = sc.nextInt();
      sc.nextLine();
      Logger.type("Updating task...", 25);
      manager.markTaskComplete(completeId);
    } catch (TaskNotFoundException e) {
      Logger.error(e.getMessage());
    } catch (Exception e) {
      Logger.error("Invalid ID format");
      sc.nextLine();
    }
    pause();
  }

  private static void deleteTask() {
    Logger.info("Enter task ID to delete:");
    try {
      int deleteId = sc.nextInt();
      sc.nextLine();
      Logger.type("Deleting task...", 25);
      manager.deleteTask(deleteId);
    } catch (TaskNotFoundException e) {
      Logger.error(e.getMessage());
    } catch (Exception e) {
      Logger.error("Invalid ID format");
      sc.nextLine();
    }
    pause();
  }

  private static void searchTask() {
    Logger.info("Enter keyword to search:");
    String keyword = sc.nextLine();
    Logger.type("Searching...", 25);
    manager.searchTask(keyword);
    pause();
  }

  private static void viewTaskDetails() {
    Logger.info("Enter task ID:");
    try {
      int detailId = sc.nextInt();
      sc.nextLine();
      Logger.type("Fetching details...", 25);
      manager.viewTaskDetails(detailId);
    } catch (TaskNotFoundException e) {
      Logger.error(e.getMessage());
    } catch (Exception e) {
      Logger.error("Invalid ID format");
      sc.nextLine();
    }
    pause();
  }

  private static void viewMyTasks() {
    Logger.section("MY ASSIGNED TASKS");
    List<Task> myTasks = currentUser.getAssignedTasks();
    if (myTasks.isEmpty()) {
      Logger.warn("No tasks assigned to you.");
    } else {
      for (Task task : myTasks) {
        System.out.println(task);
      }
    }
    pause();
  }

  private static void manageUsers() {
    Logger.section("USER MANAGEMENT");
    System.out.println("[1] List all users");
    System.out.println("[2] Activate/Deactivate user");
    System.out.println("[3] Back");
    System.out.print("→ ");

    int choice = sc.nextInt();
    sc.nextLine();

    switch (choice) {
      case 1:
        listAllUsers();
        break;
      case 2:
        toggleUserStatus();
        break;
      case 3:
        return;
      default:
        Logger.error("Invalid choice");
    }
    pause();
  }

  private static void listAllUsers() {
    Logger.section("ALL USERS");
    for (User user : users) {
      System.out.println(user);
    }
  }

  private static void toggleUserStatus() {
    Logger.info("Enter user ID to toggle status:");
    int userId = sc.nextInt();
    sc.nextLine();

    for (User user : users) {
      if (user.getUserId() == userId) {
        if (user == currentUser) {
          Logger.error("Cannot deactivate yourself");
          return;
        }
        user.setActive(!user.isActive());
        Logger.success("User " + (user.isActive() ? "activated" : "deactivated"));
        return;
      }
    }
    Logger.error("User not found");
  }

  private static void viewAllTasksAdmin() {
    Logger.section("ALL TASKS (ADMIN VIEW)");
    manager.viewTasks();
  }

  private static void systemSettings() {
    Logger.section("SYSTEM SETTINGS");
    Logger.info("Admin: " + currentUser.getFullName());
    Logger.info("Permissions: " + ((AdminUser) currentUser).getSystemPermissions().size());
    pause();
  }

  private static void permissionDenied() {
    Logger.error("Permission denied. You don't have access to this feature.");
    pause();
  }

  private static void logout() {
    Logger.type("Logging out...", 25);
    Logger.info("Logged out successfully");
    currentUser = null;
    pause();
  }

  private static void exit() {
    Logger.type("Shutting down...", 25);
    Logger.sys("Exiting application...");
  }

  private static void pause() {
    System.out.println();
    Logger.info("Press Enter to continue...");
    sc.nextLine();
  }
}
