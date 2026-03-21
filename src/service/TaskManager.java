package service;

import model.Task;
import java.util.ArrayList;
import java.util.Iterator;

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
      System.out.println("No tasks available.");
      return;
    }

    for (Task task : taskList) {
      System.out.println(task);
    }
  }

  // Delete Task
  public void deleteTask(int id) {
    Iterator<Task> iterator = taskList.iterator();

    while (iterator.hasNext()) {
      Task task = iterator.next();
      if (task.getId() == id) {
        iterator.remove();
        System.out.println("Task deleted.");
        return;
      }
    }

    System.out.println("Task not found.");
  }

  // Mark Task Complete
  public void markTaskComplete(int id) {
    for (Task task : taskList) {
      if (task.getId() == id) {
        task.markComplete();
        System.out.println("Task marked as completed.");
        return;
      }
    }

    System.out.println("Task not found.");
  }

  // Search Task
  public void searchTask(String keyword) {
    boolean found = false;

    for (Task task : taskList) {
      if (task.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
        System.out.println(task);
        found = true;
      }
    }

    if (!found) {
      System.out.println("No matching tasks found.");
    }
  }
}
