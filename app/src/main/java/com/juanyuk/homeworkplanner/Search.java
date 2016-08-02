package com.juanyuk.homeworkplanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import adapters.AssignmentsAdapter;
import models.Assignment;
import models.Course;

public class Search extends AppCompatActivity {

    private EditText searchQueryEditText;
    private ListView searchResultListView;
    private ArrayList<Assignment> mAssignments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchQueryEditText = (EditText) findViewById(R.id.searchQueryEditText);
        searchResultListView = (ListView) findViewById(R.id.searchResultListView);

        searchResultListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startAssignmentDetails(position);
            }
        });
    }

    private void startAssignmentDetails(int position) {
        Intent intent = new Intent(this, AssignmentDetail.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(MainActivity.ASSIGNMENT, mAssignments.get(position));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void populateList(){
        AssignmentsAdapter adapter = new AssignmentsAdapter(this, mAssignments);
        searchResultListView.setAdapter(adapter);
    }
    public void search(View view){
        if (searchQueryEditText.getText().toString().equals("")){
            Toast.makeText(Search.this, "Fields cannot be empty", Toast.LENGTH_LONG).show();
        }else{
            mAssignments = new ArrayList<Assignment>();
            String searchQuery = searchQueryEditText.getText().toString();
            for (Course course : StartScreen.mCourses){
                for (Assignment assignment : course.getAssignments()){
                    if (assignment.getName().toLowerCase().contains(searchQuery.toLowerCase())){
                        mAssignments.add(assignment);
                    }
                }
            }
        }

        populateList();
    }
}
