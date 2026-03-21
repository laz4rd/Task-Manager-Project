package service;

import java.util.ArrayList;
import java.util.Iterator;
import model.Task;
import util.Logger;

public class TaskManager {

  private ArrayList<Task> taskList;
  private int nextId;

  // Constructor
  public TaskManager() {
    taskList = new ArrayList<>();
    nextId = 1;
  }

  // Add Task
  public void addTask(String title, String description, String priority) {
    Task task = new Task(nextId, title, description, priority);
    taskList.add(task);
    nextId++;
  }

  // View Tasks
  public void viewTasks() {
    if (taskList.isEmpty()) {
      Logger.warn("No tasks available.");
      return;
    }

    Logger.section("TASK LIST");
    for (Task task : taskList) {
      Logger.info(task.toString());
    }
  }

  // Delete Task
  public void deleteTask(int id) {
    Iterator<Task> iterator = taskList.iterator();

    while (iterator.hasNext()) {
      Task task = iterator.next();
      if (task.getId() == id) {
        iterator.remove();
        Logger.success("Task deleted.");
        return;
      }
    }

    Logger.error("Task not found.");
  }

  // Mark Task Complete
  public void markTaskComplete(int id) {
    for (Task task : taskList) {
      if (task.getId() == id) {
        task.markComplete();
        Logger.success("Task marked as completed.");
        return;
      }
    }

    Logger.error("Task not found.");
  }

  // Search Task
  public void searchTask(String keyword) {
    Logger.section("SEARCH RESULTS");
    boolean found = false;

    for (Task task : taskList) {
      if (task.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
        Logger.info(task.toString());
        found = true;
      }
    }

    if (!found) {
      Logger.warn("No matching tasks found.");
    }
  }
}
