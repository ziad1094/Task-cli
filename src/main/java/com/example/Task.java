package com.example;

import java.time.LocalDateTime;

public class Task implements Comparable<Task>{

    private int id;
    private String description;
    private String status; //(todo, in-progress, done)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Task(int id){
        this.id = id;
        description = "";
        status = "todo";
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    public Task(int id, String desc){
        this.id = id;
        description = desc;
        status = "todo";
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    public Task(int id, String desc, String stat, LocalDateTime createdAt, LocalDateTime updatedAt){
        this.id = id;
        description = desc;
        status = stat;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    //--------------------geters----------------------
    public int getTaskId(){
        return this.id;
    }

    public String getDescription(){
        return this.description;
    }

    public String getStatus(){
        return this.status;
    }

    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }

    public LocalDateTime getUpdatedAt(){
        return this.updatedAt;
    }

    //------------------seters-------------------------

    public void setDescription(String desc){
        this.description = desc;
    }

    public void setStatusInProgress(){
        this.status = "in-progress";
    }

    public void setStatusDone(){
        this.status = "done";
    }

    public void setStatus(String status){
        this.status = status;
    }

    public void setUpdatedAt(LocalDateTime dateTime){
        this.updatedAt = dateTime;
    }

    public String createSpace(int length){
        StringBuilder s = new StringBuilder();

        for(int i=1; i<=length; i++){
            s.append(" ");
        }
        
        return s.toString();
    }

    public void displayTask(){
        System.out.println(this.id + 
                            "\t|| " + 
                            this.description + 
                            createSpace(37-this.description.length()) + "||" + 
                            this.status + 
                            createSpace(18-this.description.length()) + "|| " + 
                            this.createdAt + 
                            "\t|| " +
                            this.updatedAt);
    }

    @Override
    public int compareTo(Task task) {
        if(this.getTaskId() < task.getTaskId()) return -1;
        if(this.getTaskId() > task.getTaskId()) return 1;
        return 0;
    }
}
