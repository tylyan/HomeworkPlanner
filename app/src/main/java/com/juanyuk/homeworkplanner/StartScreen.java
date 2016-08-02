package com.juanyuk.homeworkplanner;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import models.Assignment;
import models.Course;

public class StartScreen extends AppCompatActivity {

    public static final String COURSE_DATA = "COURSE_DATA";
    public static ArrayList<Course> mCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        // load data here or inside, pass into intent, start main activity
        new Timer().schedule(new TimerTask(){

            @Override
            public void run() {
                startMain();

            }
        },5000);
    }

    private void startMain(){
        mCourses = new ArrayList<Course>();
        loadData();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void loadData(){
        Course sampleA = new Course("Algorithms", "Muthu");
        Course sampleB = new Course("Systems", "Sesh");
        Course sampleC = new Course("CompSec", "Vinod");
        Course sampleD = new Course("Expos", "West");
        mCourses.add(sampleA);
        mCourses.add(sampleB);
        mCourses.add(sampleC);
        mCourses.add(sampleD);
    }
    
    /*public void loadData(){
        try{
            FileInputStream fi = new FileInputStream("data.txt");
            ObjectInputStream in = new ObjectInputStream(fi);
            mCourses = (ArrayList<Course>) in.readObject();
            if (mCourses == null){
                mCourses = new ArrayList<Course>();
            }
            in.close();
            fi.close();
        }catch(FileNotFoundException e){
            mCourses = new ArrayList<Course>();
        }catch(IOException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }*/
}
