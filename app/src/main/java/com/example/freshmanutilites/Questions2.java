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

public class Questions2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText et;
    Button bt;
    String[] title = {"Choose Topic","Find","Rent","Sell","Buy"};
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference ref;
    DatabaseReference AllQues,UserQues;
    QuestionsMember2 member;
    String name,url,privacy,uid,vicky;
    Spinner spinner;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions2);

        et = findViewById(R.id.question_et2);
        bt = findViewById(R.id.button_submit2);
        tv = findViewById(R.id.res_topic);
        spinner = findViewById(R.id.spinner_res);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,title);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentuserId = user.getUid();

        ref= db.collection("User").document(currentuserId);
        AllQues= database.getReference("All Questions Residential");
        UserQues= database.getReference("User Questions Residential").child(currentuserId);
        member = new QuestionsMember2();
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ques = et.getText().toString();

               /* Calendar cd= Calendar.getInstance();
                SimpleDateFormat curDate = new SimpleDateFormat("dd/MMMM/yy");
                final String savedate = curDate.format(cd.getTime());


                Calendar ct= Calendar.getInstance();
                SimpleDateFormat curTime = new SimpleDateFormat("HH:mm:ss");

                final String savetime = curTime.format(cd.getTime());


                String time ="Posted on "+ savedate + "\n At "+ savetime;*/

                SimpleDateFormat curDate = new SimpleDateFormat("dd-MMMM-yy HH:mm:ss");
                String time = curDate.format(new Date());

                //validation
                if(ques.length()==0||vicky.equals("Choose Topic"))
                {

                    Toast.makeText(Questions2.this, "Ask a valid question", Toast.LENGTH_SHORT).show();

                }
                else
                {
                   //setting all items to be stored in firebase
                    member.setQues(ques);
                    member.setName(name);
                    member.setUrl(url);
                    member.setUserid(uid);
                    member.setTime(time);
                    member.setCategory(vicky.toLowerCase());


                    String id =  UserQues.push().getKey();
                    UserQues.child(id).setValue(member);

                    // saving the key for reply
                    String child  = AllQues.push().getKey();
                    member.setKey(id);

                    AllQues.child(child).setValue(member);
                    Toast.makeText(Questions2.this, "Submit", Toast.LENGTH_SHORT).show();
                    //when we post question we go to previous activity
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

            }
            else
            {

            }

        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        vicky = parent.getSelectedItem().toString();
        tv.setText(vicky);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

        //when we won't select any topic
        Toast.makeText(this, "Select a topic", Toast.LENGTH_SHORT).show();
    }
}