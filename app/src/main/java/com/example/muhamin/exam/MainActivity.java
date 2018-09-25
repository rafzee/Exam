package com.example.muhamin.exam;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private EditText name, level, credit, email, password, id;
    private RadioButton rBtn;
    private RadioGroup radio;
    private DatePicker datePicker;
    private CheckBox checkBox;
    private Button btn, btnView;
    private DatabaseReference ref;
    private FirebaseAuth mAuth;
    private StudentInfo student;
    private ProgressDialog progress;

    private String sName, sLevel, sCredit, sGender, sEmail, sPassword, sID;
    private Date sDOB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnView = findViewById(R.id.btnShowInfo);
        name = findViewById(R.id.name);
        id = findViewById(R.id.id);
        level = findViewById(R.id.level);
        credit = findViewById(R.id.credit);
        email =  findViewById(R.id.email);
        password = findViewById(R.id.password);
        radio = findViewById(R.id.rgroup);
        datePicker = findViewById(R.id.datePicker);
        checkBox = findViewById(R.id.checkBox);
        btn = findViewById(R.id.btnSave);
        progress = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkBox.isChecked()) {
                    Toast.makeText(MainActivity.this, "Please Check the Box", Toast.LENGTH_LONG).show();
                }
                else {
                    getInput();
                    createStudent();
                    createAccountAndSaveInfo();
                }
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ViewActivity.class);
                startActivity(i);
            }
        });
    }


    void getInput(){
        sName = name.getText().toString();
        sLevel = level.getText().toString();
        sEmail = email.getText().toString();
        sID = id.getText().toString();
        sPassword = password.getText().toString();
        sCredit = credit.getText().toString();
        sGender = radioSelected();
        getDate();
        Log.d("MBMR", sGender);
    }

    void getDate() {
        int d = datePicker.getDayOfMonth();
        int m = datePicker.getMonth();
        int y = datePicker.getYear();
        sDOB = new Date(y, m+1, d);
    }

    String radioSelected() {
        int selection = radio.getCheckedRadioButtonId();
        rBtn = findViewById(selection);
        return rBtn.getText().toString();
    }

    void createStudent(){
        student = new StudentInfo(sName, sLevel, sCredit, sGender, sID, sDOB);
        Log.d("MBMR", "Student created\n");
    }
    void createAccountAndSaveInfo() {
        progress.setMessage("Please Wait");
        progress.show();
        mAuth.createUserWithEmailAndPassword(sEmail, sPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("Logging in", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            ref = FirebaseDatabase.getInstance().getReference();
                            ref.child(user.getUid()).setValue(student);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Failed", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                        progress.dismiss();

                        // ...
                    }

                });
    }
}
