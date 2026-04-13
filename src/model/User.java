package model;

import java.util.ArrayList;
import java.util.List;

public class User {

  private int userId;
  private String username;
  private String password;
  private String fullName;
  private String email;
  private boolean active;
  private final String role;
  private List<Task> assignedTasks;

  public User(int userId, String username, String password, String fullName, String email) {
    this.userId = userId;
    this.username = username;
    this.password = password;
    this.fullName = fullName;
    this.email = email;
    this.active = true;
    this.role = "USER";
    this.assignedTasks = new ArrayList<>();
  }

  public User(int userId, String username, String password, String fullName, String email, String role) {
    this.userId = userId;
    this.username = username;
    this.password = password;
    this.fullName = fullName;
    this.email = email;
    this.active = true;
    this.role = role;
    this.assignedTasks = new ArrayList<>();
  }

  public int getUserId() {
    return userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public String getRole() {
    return role;
  }

  public String getPassword() {
    return password;
  }

  public boolean validatePassword(String inputPassword) {
    return this.password.equals(inputPassword);
  }

  public void changePassword(String oldPassword, String newPassword) {
    if (validatePassword(oldPassword)) {
      this.password = newPassword;
    }
  }

  public void addTask(Task task) {
    assignedTasks.add(task);
  }

  public void removeTask(Task task) {
    assignedTasks.remove(task);
  }

  public List<Task> getAssignedTasks() {
    return new ArrayList<>(assignedTasks);
  }

  public boolean hasPermission(String permission) {
    return "USER".equals(role) && ("VIEW_TASK".equals(permission) || "CREATE_TASK".equals(permission) || "EDIT_OWN_TASK".equals(permission));
  }

  @Override
  public String toString() {
    return "[" + userId + "] " + fullName + " (" + username + ") - " + role + (active ? "" : " [INACTIVE]");
  }
}
