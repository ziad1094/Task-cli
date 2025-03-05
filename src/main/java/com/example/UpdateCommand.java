package com.example;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.SortedSet;
import java.util.TreeSet;

public class UpdateCommand implements Command{

    private int taskId;
    private String description;

    public UpdateCommand(int id, String description){
        taskId = id;
        this.description = description;
    }

    public void setTaskId(int id){
        taskId = id;
    }

    public void setTaskDescription(String desc){
        this.description = desc;
    }

    @Override
    public void execute() {
        String jsonFileContent;
        String[] taskJsonArray;
        SortedSet<Task> tasks;
        Task[] taskArray;
        String newJsonString = "";
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

            //searching for the specific task and changing it's description
            for(Task task: taskArray){
                if(task.getTaskId() == taskId){
                    task.setDescription(description);
                    task.setUpdatedAt(LocalDateTime.now());
                    exists = true;
                } 
            }

            //transforming the array of tasks to json
            newJsonString = ObjectMaper.tasksToJson(taskArray);
            Files.writeString(path, newJsonString);

            if(exists){
                System.out.println("Task updated successfully (ID: " + taskId + ")");
            }
            else{
                System.out.println("No such task!");
            }

        }
        catch(Exception e){
            System.out.println("Unexpected error occured.");
        }
    }
}
