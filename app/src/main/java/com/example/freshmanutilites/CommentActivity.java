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
import android.widget.Button;
import android.widget.EditText;
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

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommentActivity extends AppCompatActivity {
    String name,uid,ques,post_key,url,typo;
    TextView name1,ques1,reply1;
    EditText reply2;
    RecyclerView rv;
    ImageView ivQues, ivUser;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference ref, ref2;
    FirebaseDatabase database= FirebaseDatabase.getInstance();
    DatabaseReference votesref,Allques;
    Boolean votecheck = false ;
    Button bt;
    CommentMember member ;
    DatabaseReference AllPost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        rv = findViewById(R.id.rec_of_comment);
        rv.setLayoutManager(new LinearLayoutManager(CommentActivity.this));


        member = new CommentMember();




        ivUser = findViewById(R.id.comment_user);
        name1 = findViewById(R.id.comment_wala_name);
        bt = findViewById(R.id.comment_submit);
       // reply1 = findViewById(R.id.comment);
        reply2= findViewById(R.id.comment_er_edittext);

        Bundle extra = getIntent().getExtras();
        if(extra!= null)
        {
            uid= extra.getString("userid");
            post_key = extra.getString("postkey");

            typo = extra.getString("typo");
            votesref = database.getReference("votes");



        }
        else
        {
            Toast.makeText(this,"intent problem",Toast.LENGTH_LONG).show();
        }
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String cuid= user.getUid();
        ref = db.collection("User").document(uid); //post giver user
        ref2= db.collection("User").document(cuid); //current user

        //Here AllPost stands for all posts
        AllPost = database.getReference("All Posts").child(post_key).child("Answer");



        //when we click the submit button
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               jomaCmnt();
            }
        });




    }
    public void jomaCmnt() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userid = user.getUid();
        String answer = reply2.getText().toString();
        if (answer != null && !answer.trim().isEmpty()) {

            SimpleDateFormat curDate = new SimpleDateFormat("dd-MMMM-yy HH:mm:ss");
            String time = curDate.format(new Date());

            member.setAns(answer);
            member.setTime(time);
            member.setName(name);
            member.setUid(userid);
            member.setUrl(url);
            // everytime we answer it will save
            String id = AllPost.push().getKey();
            AllPost.child(id).setValue(member);
            reply2.setText("");


          // Toast.makeText(this, "You have added a new Comment", Toast.LENGTH_SHORT).show();

           /*Intent intent = new Intent(AnswerActivity1.this,ReplyActivity.class);
            startActivity(intent);

            finish();*/



        } else {

            Toast.makeText(this, "Write Answer", Toast.LENGTH_LONG).show();
        }
    }


        @Override
    protected void onStart() {
        super.onStart();
        //to retrieve user image
        ref2.get().addOnCompleteListener((task) -> {
            if(task.getResult().exists())
            {
                url = task.getResult().getString(("Image URL"));
                 name = task.getResult().getString(("name"));
                 name1.setText(name);


                Picasso.get().load(url).fit().into(ivUser);


            }
            else

            {
                //  Toast.makeText(this,"Error",Toast.LENGTH_LONG).show();
            }
        });


            FirebaseRecyclerOptions<CommentMember> options =
                    new FirebaseRecyclerOptions.Builder<CommentMember>().setQuery(AllPost,CommentMember.class).build();


            FirebaseRecyclerAdapter<CommentMember,CommentViewholder> firebaseRecyclerAdapter =
                    new FirebaseRecyclerAdapter<CommentMember, CommentViewholder>(options) {
                        @Override
                        protected void onBindViewHolder(@NonNull CommentViewholder holder, int position, @NonNull CommentMember model) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            String currentuser = user.getUid();
                            String postkey = getRef(position).getKey();
                            holder.setComment(getApplication(), model.getName(),model.getAns(),model.getUid(),model.getTime(),model.getUrl());
                            holder.likeDekho(postkey);
                            holder.upvote.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    //like checker
                                    votecheck = true;
                                    votesref.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if(votecheck.equals(true))
                                            {
                                                //removing a vote
                                                if(snapshot.child(postkey).hasChild(currentuser))
                                                {

                                                    votesref.child(postkey).child(currentuser).removeValue();
                                                    votecheck = false;

                                                }

                                                else
                                                {
                                                    //adding vote
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
                        public CommentViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_layout, parent, false);
                            return new CommentViewholder(view);
                        }
                    };
            rv.setAdapter(firebaseRecyclerAdapter);
            firebaseRecyclerAdapter.startListening();
    }

}