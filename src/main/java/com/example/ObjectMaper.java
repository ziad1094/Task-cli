package com.example;

import java.time.LocalDateTime;

public class ObjectMaper {

    public static String taskToJson(Task task){

        try{
            StringBuilder jsonString = new StringBuilder();

            jsonString.append("\t\t{\n");
            jsonString.append("\t\t\t\"id\": " + task.getTaskId() + ",\n");
            jsonString.append("\t\t\t\"description\": \"" + task.getDescription() + "\",\n");
            jsonString.append("\t\t\t\"status\": \"" + task.getStatus() + "\",\n");
            jsonString.append("\t\t\t\"createdAt\": \"" + task.getCreatedAt() + "\",\n");
            jsonString.append("\t\t\t\"updatedAt\": \"" + task.getUpdatedAt() + "\"\n");
            jsonString.append("\t\t}");
    
            return jsonString.toString();
        }
        catch(Exception e){
            System.out.println("Operation failed!");
            return null;
        }
    }

    public static Task jsonToTask(String jsonString){
        try{
            jsonString = jsonString.replace("{", "").replace("}", "");
            String[] fields = jsonString.split(",");
            return new Task(Integer.parseInt(fields[0].split(":")[1].trim()), 
                            fields[1].split(":")[1].trim(), 
                            fields[2].split(":")[1].trim(), 
                            LocalDateTime.parse(fields[3].replaceFirst(":", "---").split("---")[1].trim()), 
                            LocalDateTime.parse(fields[4].replaceFirst(":", "---").split("---")[1].trim()));
        }
        catch(Exception e){
            System.out.println("Corrupted JSON file!\n");
            return null;
        }
    }

    public static String jsonTaskFileInit(){
        StringBuilder jsonStringInit = new StringBuilder();
        jsonStringInit.append("{\n");
        jsonStringInit.append("\t\"Task count\": 0,\n");
        jsonStringInit.append("\t\"Tasks\": []\n");
        jsonStringInit.append("}");

        return jsonStringInit.toString();
    }

    public static String tasksToJson(Task[] tasks){
        try {
            StringBuilder jsonString = new StringBuilder();
            jsonString.append("{\n");
            jsonString.append("\t\"Task count\": " + tasks.length + ",\n");
            jsonString.append("\t\"Tasks\": [\n");
            for(int i=0; i<tasks.length; i++){
                jsonString.append(taskToJson(tasks[i]));
                if(i != tasks.length-1){
                    jsonString.append(",\n");
                }
            }
            jsonString.append("\n\t]\n}");

            return jsonString.toString();
        } catch (Exception e) {
            System.out.println("Operation failed!");
            return null;
        }
    }

    public static String[] jsonToTasks(String jsonFileContent){

        try {
            jsonFileContent = jsonFileContent.replace("\n", "").
                                            replace("\t", "").
                                            replace("\"", "").
                                            replace("[", "").
                                            replace("]", "").
                                            trim().
                                            replaceFirst(",", "---");
        
        
            jsonFileContent = jsonFileContent.substring(0, jsonFileContent.lastIndexOf("}"));
            jsonFileContent = jsonFileContent.replaceFirst("\\{", "");

            String[] contentArray = jsonFileContent.split("---");
            String[] taskJsonArray = contentArray[1].replaceFirst(":","---").split("---")[1].trim().split("\\},\\{");


            if(taskJsonArray.length == 1 && taskJsonArray[0] == "") return null;
            return taskJsonArray;
        } catch (Exception e) {
            System.out.println("corrupted JSON file!\n");
            return null;
        }
        
    }
}