package com.example;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.SortedSet;
import java.util.TreeSet;

public class DeleteCommand implements Command{

    private int id;

    public DeleteCommand(int id){
        this.id = id;
    }

    public void setId(int id){
        this.id = id;
    }

    @Override
    public void execute() {
        String jsonFileContent;
        String[] taskJsonArray;
        SortedSet<Task> tasks;
        Task[] taskArray;
        String newJsonString = "";
        boolean removed = false;

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
                removed = tasks.remove(new Task(id));
            }

            //transforming the set of tasks into array of tasks
            taskArray = new Task[tasks.size()];
            tasks.toArray(taskArray);

            //transforming the array of tasks to json
            newJsonString = ObjectMaper.tasksToJson(taskArray);
            Files.writeString(path, newJsonString);

            if(removed){
                System.out.println("Task deleted successfully (ID: " + id + ")");
            }
            else{
                System.out.println("No such task!");
            }
        }
        catch(Exception e){
            System.out.println("Unexcepted error occured\n");
        }
    }
    
}
