package com.example.freshmanutilites;

import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freshmanutilites.databinding.ActivityYourQuesBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class YourQues extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference AllQues,UserQues;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_ques);
        recyclerView = findViewById(R.id.deep_fav_ques);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);

        recyclerView.setLayoutManager(linearLayoutManager);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentuserId = user.getUid();
        AllQues= database.getReference("All Questions Varsity");
        UserQues= database.getReference("User Questions Varsity").child(currentuserId);

      //  databaseReference = database.getReference("User Questions Varsity").child(currentuserId);
        FirebaseRecyclerOptions<QuestionsMember> options =
                new FirebaseRecyclerOptions.Builder<QuestionsMember>().setQuery(UserQues, QuestionsMember.class).build();
        FirebaseRecyclerAdapter<QuestionsMember, ViewHolder_Question> firebaseRecyclerAdapter =
               new FirebaseRecyclerAdapter<QuestionsMember,ViewHolder_Question>(options) {
                   protected void onBindViewHolder(@NonNull ViewHolder_Question holder, int position, @NonNull QuestionsMember model) {

                       FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                       String currentuserId = user.getUid();
                       final String postkey = getRef(position).getKey();

                       holder.setitemyourques(getApplication(), model.getName(), model.getUrl(), model.getUserid(), model.getKey(), model.getQues(), model.getTime());

                       String ques = getItem(position).getQues();
                       String name = getItem(position).getName();
                       String url = getItem(position).getUrl();
                       //String time = getItem(position).getTime();
                       String userid = getItem(position).getUserid();
                       // we will use this method for deleting questions
                       String time = getItem(position).getTime();
                       holder.delete.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {
                               shorao(time);
                           }
                       });


                       holder.reply.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {
                               Intent intent = new Intent(YourQues.this,ReplyActivity.class);
                               intent.putExtra("userid",userid);
                               intent.putExtra("que",ques);
                               intent.putExtra("postkey",postkey);
                               startActivity(intent);
                           }
                       });





                   }

                   @NonNull
                   @Override
                   public ViewHolder_Question onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.your_ques_items, parent, false);
                       return new ViewHolder_Question(view);

                   }
               };




        recyclerView.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();


    }
    void shorao(String time)
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