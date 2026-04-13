package model;

import java.util.ArrayList;
import java.util.List;

public class AdminUser extends User {

  private List<String> systemPermissions;
  private int managedUsersCount;

  public AdminUser(int userId, String username, String password, String fullName, String email) {
    super(userId, username, password, fullName, email, "ADMIN");
    this.systemPermissions = new ArrayList<>();
    this.managedUsersCount = 0;
    initializeAdminPermissions();
  }

  private void initializeAdminPermissions() {
    systemPermissions.add("MANAGE_USERS");
    systemPermissions.add("DELETE_ANY_TASK");
    systemPermissions.add("EDIT_ANY_TASK");
    systemPermissions.add("VIEW_ALL_TASKS");
    systemPermissions.add("SYSTEM_CONFIG");
    systemPermissions.add("VIEW_TASK");
    systemPermissions.add("CREATE_TASK");
    systemPermissions.add("EDIT_OWN_TASK");
  }

  @Override
  public boolean hasPermission(String permission) {
    return systemPermissions.contains(permission);
  }

  public boolean canManageUsers() {
    return hasPermission("MANAGE_USERS");
  }

  public boolean canDeleteAnyTask() {
    return hasPermission("DELETE_ANY_TASK");
  }

  public boolean canEditAnyTask() {
    return hasPermission("EDIT_ANY_TASK");
  }

  public boolean canViewAllTasks() {
    return hasPermission("VIEW_ALL_TASKS");
  }

  public void incrementManagedUsers() {
    managedUsersCount++;
  }

  public void decrementManagedUsers() {
    if (managedUsersCount > 0) {
      managedUsersCount--;
    }
  }

  public int getManagedUsersCount() {
    return managedUsersCount;
  }

  public List<String> getSystemPermissions() {
    return new ArrayList<>(systemPermissions);
  }

  public void addPermission(String permission) {
    if (!systemPermissions.contains(permission)) {
      systemPermissions.add(permission);
    }
  }

  public void removePermission(String permission) {
    systemPermissions.remove(permission);
  }

  @Override
  public String toString() {
    return super.toString() + " [Permissions: " + systemPermissions.size() + "]";
  }
}
