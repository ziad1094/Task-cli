package com.example;

public class Main {
    public static void main(String[] args){
        Command c;

        if (args.length < 1) {
            String helpMessage = """
                Welcome to Task Tracker!
                
                Commands list:
                
                task-cli --------------------------------------> list all the commands.
                task-cli add <task-name> ----------------------> creates a new task with the given name.
                task-cli update <task-id> <new-task-name> -----> changes the name of the specified task with the given name.
                task-cli delete <task-id> ---------------------> deletes the specified task if it exists.
                task-cli mark-in-progress <task-id> -----------> changes the status of the specified task to in-progress.
                task-cli mark-done <task-id> ------------------> changes the status of the specified task to done.
                task-cli list ---------------------------------> lists all tasks.
                task-cli list done ----------------------------> lists tasks with status done.
                task-cli list in-progress ---------------------> lists all tasks with status in-progress.
                task-cli list todo ----------------------------> lists all tasks with status todo.
                """;
            System.out.println(helpMessage);
        }
        else{
            switch (args[0].toLowerCase()) {
                case "add":
                    if(!validateArgs(args, 2, "add")) break;
                    c = new AddCommand(args[1]);
                    c.execute();
                    break;
                case "update":
                    if(!validateArgs(args, 3, "update")) break;
                    c = new UpdateCommand(parseTaskId(args[1]), args[2]);
                    c.execute();
                    break;
                case "delete":
                    if(!validateArgs(args, 2, "delete")) break;
                    c = new DeleteCommand(parseTaskId(args[1]));
                    c.execute();
                    break;
                case "mark-in-progress": 
                    if(!validateArgs(args, 2, "mark-in-progress"));
                    c = new ChangeTaskStatusCommand(parseTaskId(args[1]), "in-progress");
                    c.execute();
                    break;
                case "mark-done":
                    if(!validateArgs(args, 2, "mark-done")) break;
                    c = new ChangeTaskStatusCommand(parseTaskId(args[1]), "done");
                    c.execute();
                    break;
                case "list":
                    if(args.length == 1){
                        c = new ListTasksCommand();
                    }
                    else{
                        if(!args[1].equals("todo") && !args[1].equals("in-progress") && !args[1].equals("done")){
                            System.out.println("Incomplete or invalid list command!");
                            break;
                        }
                        c = new ListTasksCommand(args[1]);
                    }
                    c.execute();
                    break;
                default:
                    System.out.println("Invalid command!");
                    break;
            }
        }
    }

    public static  boolean validateArgs(String[] args, int expectedLength, String commandName){
        if(args.length != expectedLength){
            System.out.println("Incomplete or invalid "+ commandName + " command!");
            return false;
        }
        return true;
    }

    public static int parseTaskId(String arg){
        try {
            return Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            System.out.println("Invalid task id! must be a number");
            return -1;
        }
    }
}