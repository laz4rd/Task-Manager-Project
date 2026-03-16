# Terminal Task Manager (Java CLI)

A command-line based **Task Manager application built in Java** that allows users to organize, search, update, and manage tasks directly from the terminal.  
This project was developed as a **semester-end project** to demonstrate core concepts of **Object-Oriented Programming, Collections, String Handling, and Exception Handling in Java**.

The application provides a lightweight productivity tool that runs entirely in the terminal and manages tasks dynamically using Java data structures.

---

## Overview

The Terminal Task Manager allows users to:

- Create and manage tasks
- Search tasks by keyword
- Mark tasks as completed
- Delete tasks
- View all existing tasks
- Interact with a continuous command-line interface

The program demonstrates how **Java OOP principles and collections can be used to build a functional CLI application**.

---

## Features

### Task Creation

Users can create tasks with details such as:

- Task title
- Description
- Priority level
- Completion status

### Task Viewing

Displays all stored tasks in a structured list format.

### Task Searching

Allows searching tasks using keywords.

### Task Completion

Users can mark tasks as completed.

### Task Deletion

Tasks can be removed from the task list.

### Command Loop

The application runs continuously until the user chooses to exit.

---

## Concepts Demonstrated

This project intentionally covers topics from the Java syllabus.

### Object-Oriented Programming

- Classes and Objects
- Methods
- Constructors
- Encapsulation

### Inheritance

User roles can be extended using inheritance.

Example concept:

User
└── AdminUser

### Packages

The project is organized into packages for maintainability.

Example structure:

taskmanager
│
├── model
├── service
├── exception
└── main

### Exception Handling

The application demonstrates:

- try
- catch
- finally
- custom exceptions

Example use cases include handling:

- invalid input
- missing tasks
- incorrect commands

### String Handling

The project uses multiple Java string operations such as:

- String comparison
- Searching within strings
- Case conversion
- String formatting

### StringBuffer

`StringBuffer` is used for building formatted output strings dynamically.

### Collections Framework

The project uses several Java collections:

| Collection | Usage                   |
| ---------- | ----------------------- |
| ArrayList  | Stores tasks            |
| LinkedList | Maintains task history  |
| Set        | Stores unique task tags |

### Iterator

Collections are traversed using Java **Iterators** to display task information.

---

## Project Structure

Terminal-Task-Manager
│
├── src
│ ├── model
│ │ ├── Task
│ │ ├── User
│ │ └── AdminUser
│ │
│ ├── service
│ │ └── TaskManager
│ │
│ ├── exception
│ │ ├── TaskNotFoundException
│ │ └── InvalidTaskException
│ │
│ └── main
│ └── MainApplication
│
└── README.md

---

## Example CLI Interface

===== TERMINAL TASK MANAGER ===== 1. Add Task 2. View Tasks 3. Search Task 4. Mark Task Complete 5. Delete Task 6. Exit

Enter your choice:

Example session:

Add Task
Task title: Finish Java Project
Priority: High

Task added successfully.

View Tasks
[1] Finish Java Project - Pending

---

## How It Works

1. The program starts and displays a CLI menu.
2. The user selects an action.
3. The system performs operations on the task collection.
4. Results are displayed in the terminal.
5. The program returns to the main menu until the user exits.

---

## Learning Outcomes

By building this project, the following Java programming concepts are demonstrated:

- Designing modular programs using **classes and objects**
- Implementing **inheritance**
- Organizing code using **packages**
- Handling errors with **exceptions**
- Managing dynamic data with **Java collections**
- Performing **string manipulation**
- Iterating through collections using **iterators**

---

## Requirements

- Java JDK 8 or higher
- Command line / terminal environment
- Java compiler

---

## Future Improvements

Possible enhancements include:

- File-based task persistence
- Task deadlines and reminders
- Task categories and tagging
- User authentication
- Advanced command-based CLI interface

---

## Author

**Brijraj Singh Bhati**

Computer Science Student  
NMIMS Mumbai

---

## License

This project is created for **educational purposes** as part of Java Programming Semester-end submission.
