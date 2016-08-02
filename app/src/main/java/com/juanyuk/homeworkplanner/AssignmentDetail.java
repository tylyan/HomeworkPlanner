package com.juanyuk.homeworkplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import models.Assignment;

public class AssignmentDetail extends AppCompatActivity {

    private Assignment mAssignment;

    private TextView assignmentNameLabel;
    private TextView courseNameLabel;
    private TextView dueDateLabel;
    private TextView priorityLabel;
    private TextView completedLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_detail);

        Bundle bundle = getIntent().getExtras();
        mAssignment = (Assignment)bundle.getSerializable(MainActivity.ASSIGNMENT);

        assignmentNameLabel = (TextView) findViewById(R.id.assignmentNameLabel);
        courseNameLabel = (TextView) findViewById(R.id.courseNameLabel);
        dueDateLabel = (TextView) findViewById(R.id.dueDateLabel);
        priorityLabel = (TextView) findViewById(R.id.priorityLabel);
        completedLabel = (TextView) findViewById(R.id.completedLabel);

        assignmentNameLabel.setText(mAssignment.getName());
        courseNameLabel.setText(mAssignment.getCourse().getName());
        dueDateLabel.setText(mAssignment.getDueDateString());
        priorityLabel.setText(mAssignment.getPriorityString());
        completedLabel.setText(mAssignment.getIsCompleted());
    }

    public void markCompleted(View view){
        if (mAssignment.isCompleted()){
            Toast.makeText(AssignmentDetail.this, "Already completed", Toast.LENGTH_LONG).show();
        }else {
            mAssignment.setCompleted(true);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void edit(View view){

    }
}
