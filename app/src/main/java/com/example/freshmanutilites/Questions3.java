package com.example.freshmanutilites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Questions3 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText et;
    String[] title = {"Choose Dept","ANP", "ARC", "BAN", "BMB", "BNG", "CEP", "CEE", "CHE", "CSE", "ECO", "EEE", "ENG", "FET", "FES", "GEB", "GEE", "IPE", "MAT", "MEE", "OCG", "PAD", "PGE", "PHY", "PSS", "SCW", "SOC", "STA"};
    Button bt;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference ref;
    DatabaseReference AllQues,UserQues;
    QuestionsMember3 member;
    String name,url,uid,vicky,dept;
    Spinner spinner;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions3);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        String currentuserId = user.getUid();
        et = findViewById(R.id.question_et3);
        bt = findViewById(R.id.button_submit3);
        tv = findViewById(R.id.course);
        spinner = findViewById(R.id.spinner_course);


        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,title);
    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(arrayAdapter);
    spinner.setOnItemSelectedListener(this);

        ref= db.collection("User").document(currentuserId);
        AllQues= database.getReference("All Questions Academic");
        UserQues= database.getReference("User Questions Academic").child(currentuserId);
        member = new QuestionsMember3();
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ques = et.getText().toString();

                SimpleDateFormat curDate = new SimpleDateFormat("dd-MMMM-yy HH:mm:ss");
                String time = curDate.format(new Date());


                if(ques.length()==0||vicky.equals("Choose Course"))
                {
                    Toast.makeText(Questions3.this, "Select the course and ask question", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    member.setQues(ques);
                    member.setName(name);
                    member.setUrl(url);
                    member.setUserid(uid);
                    member.setTime(time);


                    member.setCategory(vicky.toLowerCase());
                    member.setDept(dept.toLowerCase());


                    String id =  UserQues.push().getKey();
                    UserQues.child(id).setValue(member);
                    // saving the key for reply
                    member.setKey(id);


                    String child  = AllQues.push().getKey();


                    AllQues.child(child).setValue(member);
                    Toast.makeText(Questions3.this, "Submit", Toast.LENGTH_SHORT).show();
                    finish();

                }


            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        ref.get().addOnCompleteListener(task ->
        {
            if(task.getResult().exists())
            {
                name = task.getResult().getString("name");
                url = task.getResult().getString("Image URL");

                uid = task.getResult().getString("User Id");
                dept = task.getResult().getString(("Dept")).toLowerCase();


            }
            else
            {

            }

        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //setting the text according to selected dept
        vicky = parent.getSelectedItem().toString();
        tv.setText(vicky);



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "Select a course", Toast.LENGTH_SHORT).show();
    }
}