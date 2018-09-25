package com.example.muhamin.exam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {

    private ListView studentList;
    private ArrayList <StudentInfo> students;
    private DatabaseReference dref;
    private FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        database = FirebaseDatabase.getInstance();
        dref = database.getReference();
        studentList = findViewById(R.id.listItems);
        students = new ArrayList<>();
        getData();

    }

    private void getData() {
        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snap: dataSnapshot.getChildren()) {
                    StudentInfo s = snap.getValue(StudentInfo.class);
                    students.add(s);
                }
                ItemStudentAdapter sAdapter = new









            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        })
    }
}
