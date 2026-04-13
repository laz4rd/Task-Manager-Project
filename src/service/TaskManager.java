package service;

import java.util.ArrayList;
import java.util.Iterator;
import model.Task;
import util.Logger;
import exception.InvalidTaskException;
import exception.TaskNotFoundException;

public class TaskManager {

  private ArrayList<Task> taskList;
  private int nextId;

  // Constructor
  public TaskManager() {
    taskList = new ArrayList<>();
    nextId = 1;
  }

  // Add Task
  public void addTask(String title, String description, String priority) throws InvalidTaskException {
    if (title == null || title.trim().isEmpty()) {
      throw new InvalidTaskException("Title cannot be empty");
    }
    if (description == null) {
      throw new InvalidTaskException("Description cannot be null");
    }
    if (priority == null || !priority.matches("(?i)HIGH|MEDIUM|LOW")) {
      throw new InvalidTaskException("Priority must be HIGH, MEDIUM, or LOW");
    }
    Task task = new Task(nextId, title, description, priority.toUpperCase());
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
      Logger.taskBox(
          task.getId(),
          task.getTitle(),
          task.getPriority(),
          task.isCompleted()
      );
    }
  }

  // Delete Task
  public void deleteTask(int id) throws TaskNotFoundException {
    Iterator<Task> iterator = taskList.iterator();

    while (iterator.hasNext()) {
      Task task = iterator.next();
      if (task.getId() == id) {
        iterator.remove();
        Logger.success("Task deleted.");
        return;
      }
    }

    throw new TaskNotFoundException("Task with ID " + id + " not found");
  }

  // Mark Task Complete
  public void markTaskComplete(int id) throws TaskNotFoundException {
    for (Task task : taskList) {
      if (task.getId() == id) {
        task.markComplete();
        Logger.success("Task marked as completed.");
        return;
      }
    }

    throw new TaskNotFoundException("Task with ID " + id + " not found");
  }

  // Search Task
  public void searchTask(String keyword) {
    Logger.section("SEARCH RESULTS");
    boolean found = false;

    for (Task task : taskList) {
      if (task.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
        Logger.taskBox(
            task.getId(),
            task.getTitle(),
            task.getPriority(),
            task.isCompleted()
        );
        found = true;
      }
    }

    if (!found) {
      Logger.warn("No matching tasks found.");
    }
  }

  public void viewTaskDetails(int id) throws TaskNotFoundException {
    for (Task task : taskList) {
      if (task.getId() == id) {

        Logger.section("TASK DETAILS");

        System.out.println("Title       : " + task.getTitle());
        System.out.println("Description : " + task.getDescription());
        System.out.println("Priority    : " + task.getPriority());
        System.out.println("Status      : " + (task.isCompleted() ? "Completed" : "Pending"));

        return;
      }
    }

    throw new TaskNotFoundException("Task with ID " + id + " not found");
  }
}
