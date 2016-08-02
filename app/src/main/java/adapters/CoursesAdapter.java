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
public class CoursesAdapter extends BaseAdapter {

    private Context mContext;

    public CoursesAdapter(Context context){
        this.mContext = context;
    }

    @Override
    public int getCount() {
       return StartScreen.mCourses.size();
    }

    @Override
    public Object getItem(int position) {
        return StartScreen.mCourses.get(position);
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.course_list_item, null);
            holder = new ViewHolder();
            holder.courseNameList = (TextView) convertView.findViewById(R.id.courseNameList);
            holder.professorList = (TextView) convertView.findViewById(R.id.professorList);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.courseNameList.setText(StartScreen.mCourses.get(position).getName());
        holder.professorList.setText(StartScreen.mCourses.get(position).getProfessor());
        return convertView;
    }

    private static class ViewHolder {
        TextView courseNameList;
        TextView professorList;
    }
}
