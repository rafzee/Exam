package com.example.muhamin.exam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Muhamin on 25-Sep-18.
 */

public class ItemStudentAdapter extends BaseAdapter {
    private Context activity;
    private ArrayList<StudentInfo> students;
    private LayoutInflater inflater;

    public ItemStudentAdapter() {
        students = new ArrayList<>();
        inflater = null;
    }

    public ItemStudentAdapter(Context activity, ArrayList<StudentInfo> students) {
        this.activity = activity;
        this.students = students;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private static class ViewHolder {
        private TextView name, id, level, credit;
    }

    private ViewHolder viewHolder = null;

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int i) {
        return students.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        final int pos = position;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.row_cell_student, null);
            viewHolder.name = view.findViewById(R.id.name1);
            viewHolder.id = view.findViewById(R.id.id1);
            viewHolder.level = view.findViewById(R.id.level1);
            viewHolder.credit = view.findViewById(R.id.credit1);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.name.setText(students.get(pos).getName());
        viewHolder.id.setText(students.get(pos).getsID());
        viewHolder.level.setText(students.get(pos).getLevel());
        viewHolder.credit.setText(students.get(pos).getCredit());
        return view;
    }
}
