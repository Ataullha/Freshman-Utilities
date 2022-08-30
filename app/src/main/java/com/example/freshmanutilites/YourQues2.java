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

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class YourQues2 extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference AllQues,UserQues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_ques2);
        recyclerView = findViewById(R.id.deep_fav_ques2);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);

        recyclerView.setLayoutManager(linearLayoutManager);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentuserId = user.getUid();
        AllQues= database.getReference("All Questions Residential");
        UserQues= database.getReference("User Questions Residential").child(currentuserId);
        FirebaseRecyclerOptions<QuestionsMember2> options =
                new FirebaseRecyclerOptions.Builder<QuestionsMember2>().setQuery(UserQues, QuestionsMember2.class).build();
        FirebaseRecyclerAdapter<QuestionsMember2, ViewHolder_Question> firebaseRecyclerAdapter =
               new FirebaseRecyclerAdapter<QuestionsMember2, ViewHolder_Question>(options) {
                   @Override
                   protected void onBindViewHolder(@NonNull ViewHolder_Question holder, int position, @NonNull QuestionsMember2 model) {
                       FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                       String currentuserId = user.getUid();
                       final String postkey = getRef(position).getKey();

                       holder.setitemyourques2(getApplication(), model.getName(), model.getUrl(), model.getUserid(), model.getKey(), model.getQues(), model.getTime());
                    //for reply
                       String ques = getItem(position).getQues();
                       String name = getItem(position).getName();
                       String url = getItem(position).getUrl();

                       String userid = getItem(position).getUserid();


                       holder.reply.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {
                               Intent intent = new Intent(YourQues2.this,ReplyActivity2.class);
                               intent.putExtra("userid",userid);
                               intent.putExtra("que",ques);
                               intent.putExtra("postkey",postkey);
                               startActivity(intent);
                           }
                       });

                       // we will use this method for deleting questions
                       String time = getItem(position).getTime();
                       holder.delete.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {
                               shorao2(time);
                           }
                       });
                   }

                   @NonNull
                   @Override
                   public ViewHolder_Question onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.your_ques_items2, parent, false);
                       return new ViewHolder_Question(view);
                   }
               };



        recyclerView.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();


    }
    void shorao2(String time)
    {
        Query query = UserQues.orderByChild("time").equalTo(time);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    dataSnapshot.getRef().removeValue();
                    // Toast.makeText(getActivity(),"Deleted re",Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        Query queryforall = AllQues.orderByChild("time").equalTo(time);
        queryforall.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    dataSnapshot.getRef().removeValue();
                    // Toast.makeText(getActivity(),"Deleted re",Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}