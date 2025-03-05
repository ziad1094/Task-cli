# Task-CLI

A simple command-line task tracker to manage your tasks efficiently.

## Features

- Add, update, delete tasks
- Mark tasks as "in-progress" or "done"
- List tasks by status
- Simple and lightweight

## Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/yourusername/task-cli.git
   ```
2. Navigate to the project directory:
   ```sh
   cd task-cli
   ```
3. Compile the Java files (if needed):
   ```sh
   javac -d bin src/com/example/*.java
   ```

## Usage

Run the CLI tool using:

```sh
java -cp bin com.example.Main <command> [arguments]
```

### Available Commands:

- **Show help:**
  ```sh
  task-cli
  ```
- **Add a task:**
  ```sh
  task-cli add "Task Name"
  ```
- **Update a task:**
  ```sh
  task-cli update <task-id> "New Task Name"
  ```
- **Delete a task:**
  ```sh
  task-cli delete <task-id>
  ```
- **Mark task as in-progress:**
  ```sh
  task-cli mark-in-progress <task-id>
  ```
- **Mark task as done:**
  ```sh
  task-cli mark-done <task-id>
  ```
- **List all tasks:**
  ```sh
  task-cli list
  ```
- **List tasks by status:**
  ```sh
  task-cli list todo
  task-cli list in-progress
  task-cli list done
  ```

## Contributing

If you'd like to improve Task-CLI, feel free to fork the repo and submit a pull request.

## License

This project is licensed under the MIT License.

