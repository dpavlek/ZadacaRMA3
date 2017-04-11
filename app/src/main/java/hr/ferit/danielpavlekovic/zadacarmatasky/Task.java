package hr.ferit.danielpavlekovic.zadacarmatasky;

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

    Task(){
        DateTimeCreated = new Date();
        CreatedFormat.format(DateTimeCreated);
    }

    public void setDueDate(Date dueDate) {
        DueDate = dueDate;
        DueFormat.format(DueDate);
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

    public Date getDateTimeCreated() {
        return DateTimeCreated;
    }

    public Date getDueDate() {
        return DueDate;
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
