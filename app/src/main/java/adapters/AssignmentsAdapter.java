package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.juanyuk.homeworkplanner.MainActivity;
import com.juanyuk.homeworkplanner.R;
import com.juanyuk.homeworkplanner.StartScreen;

import java.util.ArrayList;
import java.util.Collections;

import models.Assignment;
import models.Course;

/**
 * Created by yuky on 5/4/2016.
 */
public class AssignmentsAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Assignment> mAssignments;

    public AssignmentsAdapter(Context context, ArrayList<Assignment> assignments){
        mAssignments = assignments;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        int sum = 0;
        for(Course course : StartScreen.mCourses){
            for (Assignment assignment : course.getAssignments()){
                sum++;
            }
        }
        return sum;
    }

    @Override
    public Object getItem(int position) {
        return mAssignments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            // brand new
            convertView = LayoutInflater.from(mContext).inflate(R.layout.assignment_list_item, null);
            holder = new ViewHolder();
            holder.assignmentNameList = (TextView) convertView.findViewById(R.id.assignmentNameList);
            holder.assignmentCourseList = (TextView) convertView.findViewById(R.id.assignmentCourseList);
            holder.assignmentPriorityList = (TextView) convertView.findViewById(R.id.assignmentPriorityList);
            holder.assignmentDueList = (TextView) convertView.findViewById(R.id.assignmentDueList);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        Assignment assignment = mAssignments.get(position);
        String priority = "";
        switch (assignment.getPriority()){
            case HIGH:
                priority = "High";
                break;
            case MED:
                priority = "Medium";
                break;
            case LOW:
                priority = "Low";
                break;
        }
        holder.assignmentNameList.setText(assignment.getName());
        holder.assignmentCourseList.setText(assignment.getCourse().getName());
        holder.assignmentPriorityList.setText(priority);
        holder.assignmentDueList.setText(assignment.getDueDateString());
        return convertView;
    }

    public Assignment getAssignment(int position){
        return mAssignments.get(position);
    }

    private static class ViewHolder {
        TextView assignmentNameList;
        TextView assignmentCourseList;
        TextView assignmentPriorityList;
        TextView assignmentDueList;
    }

}
