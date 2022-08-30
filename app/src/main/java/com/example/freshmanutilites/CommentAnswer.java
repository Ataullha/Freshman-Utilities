package com.example.freshmanutilites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CommentAnswer extends AppCompatActivity {

    String name,url,time,uid,ques,postkey;
    EditText et;
    Button bt;
    PostMember member ;
    FirebaseDatabase database= FirebaseDatabase.getInstance();
    DatabaseReference AllQues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_answer);


        member = new PostMember() ;
        et = findViewById(R.id.comment2_er_edittext);
        bt = findViewById(R.id.comment2_submit);
        Bundle bundle = getIntent().getExtras();
        if(bundle!= null)
        {
            uid = bundle.getString("uid");

            postkey = bundle.getString("postkey");

        }
        else

        {
            Toast.makeText(this, "Error in CommentAnswer", Toast.LENGTH_SHORT).show();
        }



    }
}