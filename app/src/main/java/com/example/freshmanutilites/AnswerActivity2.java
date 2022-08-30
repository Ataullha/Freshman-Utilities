package com.example.freshmanutilites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class AnswerActivity2 extends AppCompatActivity {

    String name,url,time,uid,ques,postkey;
    EditText et;
    Button bt;
    AnswerMember member ;
    FirebaseDatabase database= FirebaseDatabase.getInstance();
    DatabaseReference AllQues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer2);
        member = new AnswerMember() ;
        et = findViewById(R.id.answer_er_edittext2);
        bt = findViewById(R.id.reply_submit2);
        Bundle bundle = getIntent().getExtras();
        if(bundle!= null)
        {
            uid = bundle.getString("uid");

            postkey = bundle.getString("postkey");

        }
        else

        {
            Toast.makeText(this, "Error in AnswerActivity1", Toast.LENGTH_SHORT).show();
        }
        AllQues= database.getReference("All Questions Residential").child(postkey).child("Answer");
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jomaAns2();
            }
        });

    }
    public void jomaAns2()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userid= user.getUid();
        String answer = et.getText().toString();
        if(answer != null && !answer.trim().isEmpty()) {

            SimpleDateFormat curDate = new SimpleDateFormat("dd-MMMM-yy HH:mm:ss");
            String time = curDate.format(new Date());

            member.setAns(answer);
            member.setTime(time);
            member.setName(name);
            member.setUid(userid);
            member.setUrl(url);
            // everytime we answer it will save
            String id = AllQues.push().getKey();
            AllQues.child(id).setValue(member);


            Toast.makeText(this, "Answer Submitted", Toast.LENGTH_SHORT).show();

            finish();






        }
        else {

            Toast.makeText(this,"Write Answer",Toast.LENGTH_LONG).show();
        }

    }
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userid= user.getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference ref= db.collection("User").document(userid);


        ref.get().addOnCompleteListener((task) -> {
            if(task.getResult().exists())
            {
                url = task.getResult().getString(("Image URL"));
                name = task.getResult().getString(("name"));
            }
            else

            {
                Toast.makeText(this,"Error in Answer Activity",Toast.LENGTH_LONG).show();
            }
        });


    }
}