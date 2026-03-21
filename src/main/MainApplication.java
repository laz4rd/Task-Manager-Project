package main;

import model.Task;

public class MainApplication {
  public static void main(String[] args) {
    Task t = new Task(1, "Finish Java Project", "Complete CLI app", "HIGH");
    System.out.println(t);
  }
}
