package com.example;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.SortedSet;
import java.util.TreeSet;

public class ListTasksCommand implements Command{
    
    private String status;

    public ListTasksCommand(){
        status = null;
    }

    public ListTasksCommand(String status){
        this.status = status;
    }

    @Override
    public void execute(){
        String jsonFileContent;
        String[] taskJsonArray;
        SortedSet<Task> tasks;
        Task[] taskArray;

        Path path = Path.of("Tasks.json", "");

        try {
            if(!Files.exists(path)){
                Files.createFile(path.toAbsolutePath());
                Files.writeString(path, ObjectMaper.jsonTaskFileInit());
            }

            //reading the content of the json file
            jsonFileContent = Files.readString(path);

            //extracting tasks from the string in the form of an array
            //the tasks in the array are just strings not yet Task instances
            taskJsonArray = ObjectMaper.jsonToTasks(jsonFileContent);
            
            tasks = new TreeSet<>();
            if(taskJsonArray != null){
                for(String taskElement: taskJsonArray){
                    //transforming the tasks in Task instances
                    tasks.add(ObjectMaper.jsonToTask(taskElement));
                }
            }

            //transforming the set of tasks into array of tasks
            taskArray = new Task[tasks.size()];
            tasks.toArray(taskArray);

            System.out.println("Id\t|| Description\t\t\t\t|| Status\t|| CreatedAt\t\t\t\t|| UpdatedAt\t\t\t\t\n");
            System.out.println("---------------------------------------------------------------------------------------------");
            for(Task task: tasks){
                if(status == null || task.getStatus().equals(status)){
                    task.displayTask();
                    System.out.println("\n");
                }
            }
        } catch (Exception e) {
            System.out.println("Unexpected error occured.");
        }
    }
}
