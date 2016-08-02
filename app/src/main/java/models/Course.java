package models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by yuky on 4/28/2016.
 */

public class Course implements Parcelable, Serializable {
    private String mName;
    private String mProfessor;
    private ArrayList<Assignment> mAssignments;

    private Course(Parcel in) {
        mName = in.readString();
        mProfessor = in.readString();
        mAssignments = in.readArrayList(null);
    }

    public Course(String name, String professor){
        this.mName = name;
        this.mProfessor = professor;
        this.mAssignments = new ArrayList<Assignment>();
    }

    public static final Creator<Course> CREATOR = new Creator<Course>(){
        @Override
        public Course createFromParcel(Parcel source) {
            return new Course(source);
        }

        @Override
        public Course[] newArray(int size) {
            return new Course[size];
        }
    };

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getProfessor() {
        return mProfessor;
    }

    public void setProfessor(String professor) {
        mProfessor = professor;
    }

    public void addAssignment(Assignment assignment){
        if (mAssignments == null) return;
        mAssignments.add(assignment);
    }

    public ArrayList<Assignment> getAssignments() {
        if (mAssignments == null) mAssignments = new ArrayList<Assignment>();
        return mAssignments;
    }

    public int getNumAssignments(){
        if (mAssignments == null) return 0;
        return this.mAssignments.size();
    }

    public boolean equals(Object o){
        if (o == null || !(o instanceof Course)) return false;
        Course oc = (Course) o;
        return this.getName().equals(oc.getName());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(mProfessor);
        dest.writeList(mAssignments);
    }

    public String toString(){
        return this.getName();
    }
}
