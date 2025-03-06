# Task-CLI

A simple command-line task tracker to manage your tasks efficiently.
Project idea belongs to [roadmap.sh](https://roadmap.sh/projects/task-tracker)

## Features

- Add, update, delete tasks
- Mark tasks as "in-progress" or "done"
- List tasks by status
- Simple and lightweight

## Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/ziad1094/task-cli.git
   ```
2. Navigate to the project directory:
   ```sh
   cd task-cli
   ```
3. Generate .jar file (i'm using Maven as a project manager):
   ```sh
   mvn clean package
   ```
4. Generate .exe file for the .jar file using a java wrapper application like Launch4j( lighweight and easy to use).

## Usage

1. Open the terminal and navigate to the folder containing task-cli.exe file.
2. Run the application using the command: 

```sh
task-cli <command> [arguments]
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

NB: you will find task-cli.exe file ready to use, if you want to avoid the hasle of installing the application.

## Contributing

If you'd like to improve Task-CLI, feel free to fork the repo and submit a pull request.

## License

This project is licensed under the MIT License.

