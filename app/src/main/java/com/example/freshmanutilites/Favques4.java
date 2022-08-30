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
import com.google.firebase.database.ValueEventListener;

public class Favques4 extends AppCompatActivity {
    RecyclerView recyclerView;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favques4);
        recyclerView = findViewById(R.id.deep_related4);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);

        recyclerView.setLayoutManager(linearLayoutManager);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentuserId = user.getUid();
        databaseReference = database.getReference("favourites others list").child(currentuserId);


        FirebaseRecyclerOptions<QuestionsMember4> options4 = new FirebaseRecyclerOptions.Builder<QuestionsMember4>().setQuery(databaseReference,QuestionsMember4.class).build();
        FirebaseRecyclerAdapter<QuestionsMember4,ViewHolder_Question> firebaseRecyclerAdapter4 = new FirebaseRecyclerAdapter<QuestionsMember4, ViewHolder_Question>(options4) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder_Question holder, int position, @NonNull QuestionsMember4 model) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String currentuserId = user.getUid();
                final  String postkey= getRef(position).getKey();

                holder.setitemfav4(getApplication(), model.getName(), model.getUrl(), model.getUserid(), model.getKey(), model.getQues(), model.getTime());

                String ques = getItem(position).getQues();
                String name = getItem(position).getName();
                String url = getItem(position).getUrl();
                String time = getItem(position).getTime();
                String userid = getItem(position).getUserid();


                holder.reply.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Favques4.this,ReplyActivity4.class);
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
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_ques_items4,parent,false);
                return  new ViewHolder_Question(view);
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter4);
        firebaseRecyclerAdapter4.startListening();




    }
}