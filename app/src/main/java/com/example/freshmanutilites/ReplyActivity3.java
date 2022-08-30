package com.example.freshmanutilites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class ReplyActivity3 extends AppCompatActivity {

    String uid,ques,post_key;
    TextView name1,ques1,reply1;
    RecyclerView rv;
    ImageView ivQues, ivUser;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference ref, ref2;
    FirebaseDatabase database= FirebaseDatabase.getInstance();
    DatabaseReference votesref,Allques;
    Boolean votecheck = false ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply3);

        rv = findViewById(R.id.rec_of_reply3);
        rv.setLayoutManager(new LinearLayoutManager(ReplyActivity3.this));

        name1 = findViewById(R.id.name_reply3);
        ques1 = findViewById(R.id.question_reply3);
        ivQues = findViewById(R.id.ques_user3);
        ivUser = findViewById(R.id.reply_user3);
        reply1 = findViewById(R.id.answer3);

        Bundle extra = getIntent().getExtras();
        if(extra!= null)
        {
            uid= extra.getString("userid");
            post_key = extra.getString("postkey");
            ques = extra.getString("que");
            votesref = database.getReference("votes");
            Allques = database.getReference("All Questions Academic").child(post_key).child("Answer");

        }
        else
        {
            Toast.makeText(this,"intent problem",Toast.LENGTH_LONG).show();
        }

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String cuid= user.getUid();

        ref = db.collection("User").document(uid);
        ref2= db.collection("User").document(cuid); //current user

        reply1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // when user clicks the reply button he will go to new activity
                Intent intent = new Intent(ReplyActivity3.this,AnswerActivity3.class);
                intent.putExtra("userid",uid);
                intent.putExtra("que",ques);
                intent.putExtra("postkey",post_key);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        ref.get().addOnCompleteListener((task) -> {
            if(task.getResult().exists())
            {
                String url = task.getResult().getString(("Image URL"));
                String name = task.getResult().getString(("name"));
                name1.setText(name);


                Picasso.get().load(url).resize(400,400).centerCrop().into(ivQues);
                ques1.setText(ques);


            }
            else

            {
                // Toast.makeText(this,"Error",Toast.LENGTH_LONG).show();
            }
        });

        // for replying
        ref2.get().addOnCompleteListener((task) -> {
            if(task.getResult().exists())
            {
                String url = task.getResult().getString(("Image URL"));


                Picasso.get().load(url).resize(400,400).centerCrop().into(ivUser);


            }
            else

            {
                //  Toast.makeText(this,"Error",Toast.LENGTH_LONG).show();
            }
        });

        FirebaseRecyclerOptions<AnswerMember3> options = new FirebaseRecyclerOptions.Builder<AnswerMember3>().setQuery(Allques,AnswerMember3.class).build();
        FirebaseRecyclerAdapter<AnswerMember3,AnswerViewholder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<AnswerMember3, AnswerViewholder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull AnswerViewholder holder, int position, @NonNull AnswerMember3 model) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        String currentuser = user.getUid();
                        String postkey = getRef(position).getKey();
                        holder.setAnswer3(getApplication(), model.getName(),model.getAns(),model.getUid(),model.getTime(),model.getUrl());
                        holder.upvoteDekho3(postkey);
                        holder.upvote.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                votecheck = true;
                                votesref.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if(votecheck.equals(true))
                                        {
                                            if(snapshot.child(postkey).hasChild(currentuser))
                                            {
                                                votesref.child(postkey).child(currentuser).removeValue();
                                                votecheck = false;

                                            }
                                            else
                                            {
                                                votesref.child(postkey).child(currentuser).setValue(true);
                                                votecheck = false;
                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
















                                });
                            }
                        });


                    }

                    @NonNull
                    @Override
                    public AnswerViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reply_layout3, parent, false);
                        return new AnswerViewholder(view);
                    }
                };



        rv.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();

    }
}