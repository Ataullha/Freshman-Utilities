package com.example.freshmanutilites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class VarsityActivity extends AppCompatActivity implements View.OnClickListener{


    FloatingActionButton fp;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference ref;
    ImageView iv;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference ;
    RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentuserId = user.getUid();

      recyclerView= findViewById(R.id.rv_varsity);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        databaseReference = database.getReference("All Questions Varsity");



       setContentView(R.layout.activity_varsity);

       iv = findViewById(R.id.varsity_image);
        fp= findViewById(R.id.floatingActionButton2);
        ref = db.collection("User").document(currentuserId);

        fp.setOnClickListener(this);
        iv.setOnClickListener(this);

       FirebaseRecyclerOptions<QuestionsMember> options = new FirebaseRecyclerOptions.Builder<QuestionsMember>().setQuery(databaseReference,QuestionsMember.class).build();

        FirebaseRecyclerAdapter<QuestionsMember,ViewHolder_Question> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<QuestionsMember, ViewHolder_Question>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder_Question holder, int position, @NonNull QuestionsMember model) {

                holder.setitem(VarsityActivity.this,model.getName(),model.getUrl(),model.getUserid(),model.getKey(),model.getQues(),model.getTime());
            }

            @NonNull
            @Override
            public ViewHolder_Question onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quest_utem,parent,false);

                return  new ViewHolder_Question(view);
            }
        };

        firebaseRecyclerAdapter.startListening();
        recyclerView.setAdapter(firebaseRecyclerAdapter);



    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.floatingActionButton2)
        {
            Intent intent = new Intent(getApplicationContext(), Questions.class);
            startActivity(intent);
        }

    }

    @Override
    protected void onStart() {

        super.onStart();

        ref.get().addOnCompleteListener((task) -> {
            if(task.getResult().exists())
            {
                //String url = task.getResult().getString(("Image URL"));

              //  Picasso.get().load(url).fit().into(iv);


            }
            else

            {
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
            }
        });
    }
}