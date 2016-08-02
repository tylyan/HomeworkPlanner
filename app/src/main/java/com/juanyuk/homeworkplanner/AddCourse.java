package com.juanyuk.homeworkplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import models.Course;

public class AddCourse extends AppCompatActivity {

    private EditText courseNameEditText;
    private EditText professorEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        courseNameEditText = (EditText) findViewById(R.id.courseNameEditText);
        professorEditText = (EditText) findViewById(R.id.professorEditText);
    }

    public void addCourse(View view){
        if((courseNameEditText.getText().toString().equals("")) || (professorEditText.getText().toString().equals(""))){
            Toast.makeText(AddCourse.this, "Fields cannot be empty", Toast.LENGTH_LONG).show();
        }else{
            Course newCourse = new Course(courseNameEditText.getText().toString(),
                    professorEditText.getText().toString());
            StartScreen.mCourses.add(newCourse);
            Intent intent = new Intent(this, Courses.class);
            startActivity(intent);
        }
    }
}
