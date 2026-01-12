package dto;

import java.io.Serializable;

public class Task implements Serializable {

    private int id;
    private String category;
    private String task;
    private String priority;
    private String status;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getTask() { return task; }
    public void setTask(String task) { this.task = task; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
