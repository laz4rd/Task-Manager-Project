(Detailed description of project. It should include answers to following questions)

1) Motivation to take the proposed project

The primary motivation behind developing this Terminal Task Manager was to explore and implement modern logging systems and ANSI color codes in a Java CLI environment. While traditional console applications rely on plain text output, we wanted to experiment with creating visually appealing terminal interfaces using ANSI escape codes for colors, text formatting, and cursor control. This project provided an opportunity to build a custom Logger utility (util/Logger.java) that demonstrates:

- Color-coded log messages (success in green, errors in red, warnings in yellow, info in blue)
- Visual UI elements like bordered boxes for task display
- Screen clearing and section headers using escape sequences
- Typing animation effects for enhanced user experience
- Unicode symbols (✔, ✖, ⚠, ℹ, ⚙) for intuitive iconography

Beyond the visual aspects, the project serves as a comprehensive demonstration of core Java concepts learned during the semester, including Object-Oriented Programming, Collections Framework, Exception Handling, and String manipulation. The goal was to create something functional yet visually distinct from typical student projects.

2) Working of project

The Terminal Task Manager operates as a command-line application with user authentication and role-based access control:

Authentication Flow:
- Users can log in with predefined credentials (user1/pass123, user2/pass456, admin/admin123)
- New accounts can be created during runtime
- Three failed login attempts lock the user out

User Roles:
- Regular Users: Can create, view, search, and mark their own tasks as complete
- Admin Users: Extended permissions including deleting any task, managing users, and accessing system settings

Task Management:
- Tasks are stored in an ArrayList with auto-incrementing IDs
- Each task contains: ID, title, description, priority (HIGH/MEDIUM/LOW), and completion status
- Tasks can be viewed in styled boxes using the Logger utility
- Search functionality allows finding tasks by keyword in titles

The application runs in a continuous loop, displaying menus and processing user input until the exit option is selected. All user interactions are enhanced with visual feedback through the custom Logger class.

3) Advantages & disadvantages of project

Advantages:
- Clean modular architecture with packages (model, service, exception, main, util)
- Custom Logger class provides professional-looking terminal output with colors and formatting
- Role-based access control demonstrates inheritance (AdminUser extends User)
- Comprehensive exception handling with custom exceptions (TaskNotFoundException, InvalidTaskException)
- Uses multiple Java Collections: ArrayList for tasks, LinkedList concept for history
- Iterator pattern used for task traversal and deletion
- StringBuffer-like operations in Logger for building formatted output
- Self-contained application requiring no external dependencies beyond Java JDK
- Visual feedback improves user experience compared to plain console apps

Disadvantages:
- No persistent storage - all data is lost when the application exits
- In-memory user storage with plaintext passwords (not secure for production)
- Limited to terminal environments that support ANSI escape codes
- No multi-threading implementation for concurrent operations
- User interface is text-based only - no GUI option available
- Task assignments are tracked but not fully integrated with the task lifecycle
- Admin user management features are basic (activate/deactivate only)

4) Technologies used for developing the project

Core Technologies:
- Java Development Kit (JDK) 8+ - Core programming language and runtime
- Object-Oriented Programming (OOP) - Classes, Objects, Encapsulation, Inheritance
- Java Collections Framework - ArrayList, List, Iterator
- Exception Handling - try-catch blocks, custom exceptions
- String Handling - String manipulation, StringBuilder concepts
- ANSI Escape Codes - Terminal color and formatting control

Development Tools:
- Standard Java compiler (javac)
- Command-line terminal for execution
- Git for version control

Package Structure:
- model package: Task, User, AdminUser classes
- service package: TaskManager (business logic)
- exception package: Custom exception classes
- util package: Logger (ANSI color handling and UI)
- main package: MainApplication (entry point)

Key Features Implemented:
- ANSI color codes for terminal styling
- Unicode symbols for visual indicators
- Screen clearing escape sequences
- Box-drawing characters for UI elements
- Role-based permission system
