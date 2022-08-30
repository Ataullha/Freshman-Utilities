package com.example.freshmanutilites;

import androidx.appcompat.app.AppCompatActivity;

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
import java.util.Locale;

public class Questions4 extends AppCompatActivity {

    EditText et;
    Button bt;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference ref;
    DatabaseReference AllQues,UserQues;
    QuestionsMember4 member;
    String name,url,uid,batch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions4);

        et = findViewById(R.id.question_et4);
        bt = findViewById(R.id.button_submit4);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentuserId = user.getUid();
        ref= db.collection("User").document(currentuserId);
        AllQues= database.getReference("All Questions Others");
        UserQues= database.getReference("User Questions Others").child(currentuserId);
        member = new QuestionsMember4();
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ques = et.getText().toString();

                SimpleDateFormat curDate = new SimpleDateFormat("dd-MMMM-yy HH:mm:ss");
                String time = curDate.format(new Date());


                if(ques != null && !ques.trim().isEmpty())
                {
                    member.setQues(ques);
                    member.setName(name);
                    member.setUrl(url);
                    member.setUserid(uid);
                    member.setTime(time);
                    member.setCategory(batch);


                    String id =  UserQues.push().getKey();
                    UserQues.child(id).setValue(member);

                    // saving the key for reply
                    String child  = AllQues.push().getKey();
                    member.setKey(id);

                    AllQues.child(child).setValue(member);
                    Toast.makeText(Questions4.this, "Submit", Toast.LENGTH_SHORT).show();
                    finish();

                }
                else
                {
                    Toast.makeText(Questions4.this, "Hoilo na toh", Toast.LENGTH_SHORT).show();
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
                batch = task.getResult().getString("Batch");

            }
            else
            {

            }

        });
    }
}