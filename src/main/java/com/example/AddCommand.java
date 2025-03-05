package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.SortedSet;
import java.util.TreeSet;

public class AddCommand implements Command {

    private String taskName;

    public AddCommand(String name){
        taskName = name;
    }

    public void setTaskName(String name){
        taskName = name;
    }

    @Override
    public void execute(){
        String jsonFileContent;
        String[] taskJsonArray;
        SortedSet<Task> tasks;
        Task[] taskArray;
        Task newTask;
        String newJsonString;
        int newTaskId;

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

            newTaskId = 1;
            if(taskJsonArray != null){
                for(String taskElement: taskJsonArray){
                    //transforming the tasks in Task instances
                    tasks.add(ObjectMaper.jsonToTask(taskElement));
                }
                newTaskId = tasks.last().getTaskId()+1;
            }
            
            //adding the new given task
            newTask = new Task(newTaskId, taskName);
            tasks.add(newTask);

            //transforming the set of tasks into array of tasks
            taskArray = new Task[tasks.size()];
            tasks.toArray(taskArray);

            //transforming the array of tasks to json
            newJsonString = ObjectMaper.tasksToJson(taskArray);
            
            Files.writeString(path, newJsonString);
            System.out.println("Task added successfully (ID: " + newTask.getTaskId() + ")");
        }
        catch(IOException e){
            System.out.println("Operation failed! there might have been issues with opening/reading/writing the file");
        }
    }
}
