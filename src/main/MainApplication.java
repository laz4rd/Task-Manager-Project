package main;

import model.Task;
import service.TaskManager;

public class MainApplication {
  public static void main(String[] args) {
    TaskManager manager = new TaskManager();

    manager.addTask("Finish Java Project", "Complete CLI app", "HIGH");
    manager.addTask("Study", "Revise collections", "MEDIUM");

    manager.viewTasks();

    manager.markTaskComplete(1);

    manager.viewTasks();

    manager.deleteTask(2);

    manager.viewTasks();
  }
}
