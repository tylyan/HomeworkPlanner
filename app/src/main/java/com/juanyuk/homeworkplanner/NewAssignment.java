package com.juanyuk.homeworkplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import models.Assignment;
import models.Course;

public class NewAssignment extends AppCompatActivity{

    private EditText assignmentNameEditText;
    private Spinner coursesSpinner;
    private EditText dateEditText;
    private Spinner prioritySpinner;
    private String courseString;
    private String priorityString;

    private String[] priorities = {"HIGH", "MED", "LOW"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_assignment);

        assignmentNameEditText = (EditText) findViewById(R.id.assignmentNameEditText);
        coursesSpinner = (Spinner) findViewById(R.id.coursesSpinner);
        dateEditText = (EditText) findViewById(R.id.dateEditText);
        prioritySpinner = (Spinner) findViewById(R.id.prioritySpinner);

        ArrayAdapter<Course> coursesAdapter = new ArrayAdapter<Course>(this, android.R.layout.simple_spinner_item, StartScreen.mCourses);
        coursesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        coursesSpinner.setAdapter(coursesAdapter);

        ArrayAdapter<String> priorityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, priorities);
        coursesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        prioritySpinner.setAdapter(priorityAdapter);

        coursesSpinner.setOnItemSelectedListener(new CourseSpinnerListener());
        prioritySpinner.setOnItemSelectedListener(new PrioritySpinnerListener());
    }

    public void addAssignment(View view){
        if (assignmentNameEditText.getText().toString().equals("") ||
                !dateValid() ||
                coursesSpinner.getSelectedItem() == null ||
                prioritySpinner.getSelectedItem() == null){
            Toast.makeText(NewAssignment.this, "Fields cannot be empty", Toast.LENGTH_LONG).show();
        }else{
            Assignment newAssignment = new Assignment(assignmentNameEditText.getText().toString(),
                    (Course) coursesSpinner.getSelectedItem(), parseDate(), parsePriority());
            ((Course) coursesSpinner.getSelectedItem()).addAssignment(newAssignment);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public Assignment.Priority parsePriority(){
        String selection = (String)prioritySpinner.getSelectedItem();
        switch (selection){
            case "HIGH":
                return Assignment.Priority.HIGH;
            case "MED":
                return Assignment.Priority.MED;
            case "LOW":
                return Assignment.Priority.LOW;
        }
        return Assignment.Priority.HIGH;
    }

    public boolean dateValid(){
        if (dateEditText.getText().toString().equals("")) return false;
        Date date = null;
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
        try{
            date = formatter.parse(dateEditText.getText().toString());
        }catch(ParseException e){
            return false;
        }
        return true;
    }

    public Date parseDate(){
        Date date = null;
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
        try{
            date = formatter.parse(dateEditText.getText().toString());
        }catch(ParseException e){
            return null;
        }
        return date;
    }

    private class CourseSpinnerListener implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            courseString = parent.getItemAtPosition(position).toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private class PrioritySpinnerListener implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            priorityString = parent.getItemAtPosition(position).toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}
