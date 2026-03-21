package model;

public class Task {

  // variables
  private int _id;
  private String _titleString;
  private String _descriptionString;
  private String _priorityString;
  private boolean _completeBoolean;

  // constructor
  public Task(int _id, String _titleString, String _descriptionString, String _priorityString) {
    this._id = _id;
    this._titleString = _titleString;
    this._descriptionString = _descriptionString;
    this._priorityString = _priorityString;
  }

  // getters
  public int getId() {
    return _id;
  }

  public String getTitle() {
    return _titleString;
  }

  public String getDescription() {
    return _priorityString;
  }

  public boolean isCompleted() {
    return _completeBoolean;
  }

  // Mark task as completed
  public void markComplete() {
    this._completeBoolean = true;
  }

  // Convert task to readable string (VERY IMPORTANT)
  @Override
  public String toString() {
    String status = _completeBoolean ? "Completed" : "Pending";

    return "[" + _id + "] " + _titleString +
        " | Priority: " + _priorityString +
        " | Status: " + status;
  }
}
