# Project Structure

This document describes the folder organization of the **Terminal Task Manager** project and the purpose of each component.  
The project follows a modular structure to keep the code clean, readable, and maintainable.

---

# Root Directory
```
terminal-task-manager
│
├── src
├── README.md
├── PROJECT_STRUCTURE.md
└── .gitignore
```
### Files

**README.md**  
Contains the project overview, features, and instructions for running the program.

**PROJECT_STRUCTURE.md**  
Explains the internal architecture of the project.

**.gitignore**  
Prevents unnecessary system files and compiled Java files from being added to the repository.

---

# Source Code Directory

All Java source files are located inside the `src` folder.

```
src
│
├── main
├── model
├── service
└── exception
```

Each folder represents a logical component of the application.

---

# main

src/main
│
└── MainApplication

### Purpose

This package contains the **entry point of the program**.

### Responsibilities

- Starting the application
- Displaying the command line interface
- Handling user input
- Calling the appropriate methods in the service layer

The main loop of the CLI runs here.

---

# model

```
src/model
│
├── Task
├── User
└── AdminUser
```

### Purpose

This package defines the **core data objects** used by the system.

### Classes

**Task**

Represents a single task.

Possible attributes:

- Task ID
- Title
- Description
- Priority
- Completion status

This class also contains methods to update and display task information.

---

**User**

Represents a system user.

Attributes may include:

- Username
- Role

---

**AdminUser**

Extends the `User` class to demonstrate **inheritance**.

Admin users may have additional permissions such as:

- Deleting tasks
- Clearing the task list

---

# service

src/service
│
└── TaskManager

### Purpose

This package contains the **business logic of the application**.

### TaskManager

Responsible for managing the task collection.

Responsibilities include:

- Adding tasks
- Deleting tasks
- Searching tasks
- Marking tasks as completed
- Listing all tasks

The class internally manages collections such as:

- `ArrayList` for storing tasks
- `LinkedList` for history
- `Set` for unique tags

---

# exception

```
src/exception
│
├── TaskNotFoundException
└── InvalidTaskException
```

### Purpose

This package contains **custom exceptions** used in the application.

### TaskNotFoundException

Thrown when the user tries to access a task that does not exist.

---

### InvalidTaskException

Thrown when a task input is invalid.

Example cases:

- Empty task title
- Incorrect task ID

---

# Application Flow

The application operates using the following workflow:

1. The program starts from the `MainApplication`.
2. The CLI menu is displayed.
3. The user selects an option.
4. The `TaskManager` processes the request.
5. Tasks are created, updated, searched, or deleted.
6. Results are displayed to the user.
7. The program returns to the main menu until the user exits.

---

# Collections Used

| Collection | Purpose                  |
| ---------- | ------------------------ |
| ArrayList  | Stores tasks dynamically |
| LinkedList | Stores command history   |
| Set        | Stores unique task tags  |

---

# Key Concepts Demonstrated

This project demonstrates several Java programming concepts:

- Classes and Objects
- Methods and Constructors
- Inheritance
- Packages
- Exception Handling
- Custom Exceptions
- String Handling
- StringBuffer
- Java Collections Framework
- Iterators

---

# Future Expansion

The project structure allows easy addition of new features such as:

- Task persistence using files
- Task deadlines and reminders
- Task categories
- User authentication
- Advanced CLI commands

---

This modular architecture ensures the project remains **scalable, maintainable, and easy to understand** for both developers and reviewers.
