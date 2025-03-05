package com.example;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.SortedSet;
import java.util.TreeSet;

public class ChangeTaskStatusCommand implements Command{

    private int taskId;
    private String newStatus;

    public ChangeTaskStatusCommand(int id, String newStatus){
        taskId = id;
        this.newStatus = newStatus;
    }

    public void setTaskId(int id){
        taskId = id;
    }

    public void setNewStatus(String newStatus){
        this.newStatus = newStatus;
    }

    @Override
    public void execute() {
        String jsonFileContent;
        String[] taskJsonArray;
        SortedSet<Task> tasks;
        Task[] taskArray;
        String newJsonString = "";
        boolean operationDone = false;
        boolean exists = false;
        
        Path path = Path.of("Tasks.json", "");
        try{
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

            //searching for the specific task and changing it's status
            for(Task task: taskArray){
                if(task.getTaskId() == taskId){
                    if(task.getStatus().equals("todo") || (task.getStatus().equals("in-progress") && newStatus.equals("done"))){
                        task.setStatus(newStatus);
                        operationDone = true;
                    }
                    exists = true;
                } 
            }

            //transforming the array of tasks to json
            newJsonString = ObjectMaper.tasksToJson(taskArray);
            Files.writeString(path, newJsonString);

            if(!exists){
                System.out.println("No such task!");
            }
            else if(!operationDone){
                System.out.println("Cannot change the status of this task to the desired one!");
            }
            else{
                System.out.println("Task state changed successfully (ID: " + taskId + ")");
            }
        }
        catch(Exception e){
            System.out.println("Unexpected error occured.");
        }
    }
    
}
