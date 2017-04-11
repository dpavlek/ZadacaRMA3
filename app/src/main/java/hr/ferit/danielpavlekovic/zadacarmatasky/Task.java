package hr.ferit.danielpavlekovic.zadacarmatasky;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by danielpavlekovic on 10.04.2017..
 */

class Task {

    private String Name, Category,Priority;
    private Date DateTimeCreated, DueDate;

    private SimpleDateFormat DueFormat = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat CreatedFormat = new SimpleDateFormat("yyMMddHHmmss");

    Task(String Name){
        DateTimeCreated = new Date();
        CreatedFormat.format(DateTimeCreated);
        this.Name=Name;
    }

    Task(String created, String name, String duedate, String category, String priority){
        this.Name=name;
        this.Category = category;
        this.Priority = priority;
        try {
            DateTimeCreated = CreatedFormat.parse(created);
            DueDate = DueFormat.parse(duedate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    Task(){
        this.DateTimeCreated = new Date();
        this.CreatedFormat.format(DateTimeCreated);
    }

    public void setDueDate(String dueDate) {
        try {
            DueDate = DueFormat.parse(dueDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void setCategory(String category) {
        Category = category;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPriority(String priority) {
        Priority = priority;
    }

    public String getDateTimeCreated() {
        return CreatedFormat.format(DateTimeCreated);
    }

    public String getDueDate() {
        return DueFormat.format(DueDate);
    }

    public String getPriority() {
        return Priority;
    }

    public String getCategory() {
        return Category;
    }

    public String getName() {
        return Name;
    }
}
