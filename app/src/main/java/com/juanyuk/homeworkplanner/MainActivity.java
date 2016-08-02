package com.juanyuk.homeworkplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.internal.view.menu.ActionMenuItemView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import adapters.AssignmentsAdapter;
import models.Assignment;
import models.Course;

public class MainActivity extends AppCompatActivity {

    private TextView allTextView;
    private TextView courseTextView;
    private TextView priorityTextView;
    private TextView pendingTextView;
    private ListView assignmentsListView;

    private ArrayList<Assignment> mAssignments;

    public static final int ALL = 0;
    public static final int COURSES = 1;
    public static final int PRIORITY = 2;
    public static final int PENDING = 3;
    public static final int DONE = 4;
    public static final String ASSIGNMENT = "ASSIGNMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        allTextView = (TextView) findViewById(R.id.all_label);
        courseTextView = (TextView) findViewById(R.id.course_label);
        priorityTextView = (TextView) findViewById(R.id.priority_label);
        pendingTextView = (TextView) findViewById(R.id.pending_label);
        assignmentsListView = (ListView) findViewById(R.id.assignmentsListView);

        populateList(ALL);
    }

    private ArrayList<Assignment> getAssignmentsAll(){
        ArrayList<Assignment> assignments = new ArrayList<Assignment>();
        for (Course course : StartScreen.mCourses){
            for (Assignment assignment : course.getAssignments()){
                assignments.add(assignment);
            }
        }
        Collections.sort(assignments);
        return assignments;
    }

    private ArrayList<Assignment> getAssignmentsCourse(){
        ArrayList<Assignment> assignments = new ArrayList<Assignment>();
        for (Course course : StartScreen.mCourses){
            for (Assignment assignment : course.getAssignments()){
                assignments.add(assignment);
            }
        }
        return assignments;
    }

    private ArrayList<Assignment> getAssignmentsPriority(){
        ArrayList<Assignment> assignments = new ArrayList<Assignment>();
        for (Course course : StartScreen.mCourses){
            for (Assignment assignment : course.getAssignments()){
                if (assignment.getPriority() == Assignment.Priority.HIGH)
                    assignments.add(assignment);
            }
        }
        for (Course course : StartScreen.mCourses){
            for (Assignment assignment : course.getAssignments()){
                if (assignment.getPriority() == Assignment.Priority.MED)
                    assignments.add(assignment);
            }
        }
        for (Course course : StartScreen.mCourses){
            for (Assignment assignment : course.getAssignments()){
                if (assignment.getPriority() == Assignment.Priority.LOW)
                    assignments.add(assignment);
            }
        }
        return assignments;
    }

    private ArrayList<Assignment> getAssignmentsPending(){
        ArrayList<Assignment> assignments = new ArrayList<Assignment>();
        for (Course course : StartScreen.mCourses){
            for (Assignment assignment : course.getAssignments()){
                if (!assignment.isCompleted())
                    assignments.add(assignment);
            }
        }
        return assignments;
    }

    private ArrayList<Assignment> getAssignmentsDone(){
        ArrayList<Assignment> assignments = new ArrayList<Assignment>();
        for (Course course : StartScreen.mCourses){
            for (Assignment assignment : course.getAssignments()){
                if (assignment.isCompleted())
                    assignments.add(assignment);
            }
        }
        return assignments;
    }

    private ArrayList<Assignment> getAssignments(int mode){
        switch (mode){
            case ALL:
                return getAssignmentsAll();
            case COURSES:
                return getAssignmentsCourse();
            case PRIORITY:
                return getAssignmentsPriority();
            case PENDING:
                return getAssignmentsPending();
            case DONE:
                return getAssignmentsDone();
            default:
                return getAssignmentsAll();
        }
    }

    public void populateList(int mode){
        //fetch assignments based on mode
        mAssignments = getAssignments(mode);
        //update ListView with assignments
        AssignmentsAdapter adapter = new AssignmentsAdapter(this, mAssignments);
        assignmentsListView.setAdapter(adapter);

        assignmentsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startAssignmentDetails(position);
            }
        });
    }

    public void filterAll(View view){
        changeColor(allTextView);
        populateList(ALL);
    }

    public void filterCourse(View view){
        changeColor(courseTextView);
        populateList(COURSES);
    }

    public void filterPriority(View view){
        changeColor(priorityTextView);
        populateList(PRIORITY);
    }

    public void filterPending(View view){
        changeColor(pendingTextView);
        populateList(PENDING);
    }

    public void changeColor(TextView view){
        allTextView.setTextColor(getResources().getColor(R.color.white));
        courseTextView.setTextColor(getResources().getColor(R.color.white));
        priorityTextView.setTextColor(getResources().getColor(R.color.white));
        pendingTextView.setTextColor(getResources().getColor(R.color.white));
        view.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
    }

    public void newAssignment(View view){
        // check for existence of courses first
        if (StartScreen.mCourses == null || StartScreen.mCourses.size() == 0){
            Toast.makeText(MainActivity.this, "Please add a course first", Toast.LENGTH_LONG).show();
        }else{
            Intent intent = new Intent(this, NewAssignment.class);
            startActivity(intent);
        }
    }

    private void startAssignmentDetails(int position) {
        Intent intent = new Intent(this, AssignmentDetail.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(ASSIGNMENT, mAssignments.get(position));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void startCourses(View view) {
        Intent intent = new Intent(this, Courses.class);
        startActivity(intent);
    }

    public void search(View view){
        Intent intent = new Intent(this, Search.class);
        startActivity(intent);
    }
}
