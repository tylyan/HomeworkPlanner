package com.juanyuk.homeworkplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import adapters.CoursesAdapter;
import models.Course;

public class Courses extends AppCompatActivity {

    public static final String COURSE_INDEX = "COURSE_INDEX";
    private ListView coursesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        coursesListView = (ListView) findViewById(R.id.coursesListView);
        CoursesAdapter adapter = new CoursesAdapter(this);
        coursesListView.setAdapter(adapter);
        coursesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startCourseDetails(position);
            }
        });
    }

    private void startCourseDetails(int index){
        Intent intent = new Intent(this, CourseDetail.class);
        intent.putExtra(COURSE_INDEX, index);
        startActivity(intent);
    }

    public void addCourse(View view){
        Intent intent = new Intent(this, AddCourse.class);
        startActivity(intent);
    }
}
