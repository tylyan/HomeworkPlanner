package com.juanyuk.homeworkplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import adapters.AssignmentsAdapter;
import models.Course;

public class CourseDetail extends AppCompatActivity {

    private Course mCourse;
    private TextView professorTextView;
    private ListView cAssignmentsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        Bundle bundle = getIntent().getExtras();
        mCourse = StartScreen.mCourses.get(getIntent().getIntExtra(Courses.COURSE_INDEX, 0));

        professorTextView = (TextView) findViewById(R.id.professorTextView);
        cAssignmentsListView = (ListView) findViewById(R.id.cAssignmentsListView);

        cAssignmentsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startAssignmentDetails(position);
            }
        });
        populateList();
        professorTextView.setText("Professor: " + mCourse.getName());
    }

    public void populateList(){
        if (!(mCourse.getAssignments() == null || mCourse.getAssignments().size() == 0)) {
            AssignmentsAdapter adapter = new AssignmentsAdapter(this, mCourse.getAssignments());
            cAssignmentsListView.setAdapter(adapter);
        }
    }

    private void startAssignmentDetails(int position) {
        Intent intent = new Intent(this, AssignmentDetail.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(MainActivity.ASSIGNMENT, mCourse.getAssignments().get(position));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void edit(View view){
        Intent intent = new Intent(this, AddCourse.class);
        startActivity(intent);
    }
}
