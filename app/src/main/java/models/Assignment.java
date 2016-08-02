package models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by yuky on 4/28/2016.
 */
public class Assignment implements Comparable<Assignment>, Comparator<Assignment>, Serializable{

    public enum Priority {LOW, MED, HIGH};

    private String mName;
    private Course mCourse;
    private Date mDueDate;
    private String mDescription;
    private Priority mPriority;
    private boolean mCompleted;

    public Assignment(String name, Course course, Date dueDate, Priority priority){
        this.mName = name;
        this.mCourse = course;
        this.mDueDate = dueDate;
        this.mPriority = priority;
        this.mCompleted = false;
    }

    public void setCompleted(boolean completed) {
        mCompleted = completed;
    }

    public void setPriority(Priority priority) {
        mPriority = priority;
    }

    public void setDueDate(Date dueDate) {
        mDueDate = dueDate;
    }

    public void setCourse(Course course) {
        mCourse = course;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getName() {

        return mName;
    }

    public Course getCourse() {
        return mCourse;
    }

    public Date getDueDate() {
        return mDueDate;
    }

    public String getDueDateString(){
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy");
        return format.format(this.getDueDate()).toString();
    }

    public Priority getPriority() {
        return mPriority;
    }

    public String getPriorityString(){
        switch (mPriority){
            case HIGH:
                return "HIGH";
            case MED:
                return "MED";
            case LOW:
                return "LOW";
            default:
                return "HIGH";
        }
    }

    public boolean isCompleted() {
        return mCompleted;
    }

    public String getIsCompleted(){
        return isCompleted() ? "Yes" : "No";
    }

    public boolean equals(Object o){
        if (o == null || !(o instanceof Assignment))
            return false;
        Assignment oa = (Assignment) o;
        return this.getName().equals(oa.getName());
    }
    
    @Override
    public int compareTo(Assignment another) {
        return this.getDueDate().compareTo(another.getDueDate());
    }

    @Override
    public int compare(Assignment lhs, Assignment rhs) {
        return lhs.compareTo(rhs);
    }

    public String toString(){
        return this.getName();
    }
}
