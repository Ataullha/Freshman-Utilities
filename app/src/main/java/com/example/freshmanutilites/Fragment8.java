package com.example.freshmanutilites;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class Fragment8 extends Fragment implements  View.OnClickListener{

    FloatingActionButton fb;
    FloatingActionButton fp;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference ref;
    ImageView iv;
    FirebaseDatabase database4 = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference4 ;
    RecyclerView recyclerView4;
    QuestionsMember member4;
    DatabaseReference favref ; //favref for checking by clicking image
    DatabaseReference fav_list_ref; //for all saved fav question
    Boolean favchecker = false ;
    String batch;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_8,container,false);




        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        iv = getActivity().findViewById(R.id.others_image);
        fb= getActivity().findViewById(R.id.floatingActionButton5);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentuserId = user.getUid();
        ref = db.collection("User").document(currentuserId);
        fb.setOnClickListener(this);
        iv.setOnClickListener(this);
        recyclerView4 =getActivity().findViewById(R.id.rv_f8);
        recyclerView4.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager4 = new LinearLayoutManager(getActivity());
        linearLayoutManager4.setReverseLayout(true);
        linearLayoutManager4.setStackFromEnd(true);
        recyclerView4.setLayoutManager(linearLayoutManager4);
        databaseReference4= database4.getReference("All Questions Others");
        member4= new QuestionsMember();
        favref = database4.getReference("favourites others"); //for checking wheather question is saved or not
        fav_list_ref = database4.getReference("favourites others list").child(currentuserId); //for saviing question

        ref.get().addOnCompleteListener((task) -> {
            if(task.getResult().exists())
            {


                batch = task.getResult().getString(("Batch"));

                sortques(batch);

            }
            else

            {
                Toast.makeText(getActivity(),"Error",Toast.LENGTH_LONG).show();
            }
        });




    }

    public void sortques(String category)
    {
        Query query = databaseReference4.orderByChild("category").startAt(category).endAt(category + "\uf0ff");
        FirebaseRecyclerOptions<QuestionsMember4> options4 = new FirebaseRecyclerOptions.Builder<QuestionsMember4>().setQuery(query,QuestionsMember4.class).build();
        FirebaseRecyclerAdapter<QuestionsMember4,ViewHolder_Question> firebaseRecyclerAdapter4 = new FirebaseRecyclerAdapter<QuestionsMember4, ViewHolder_Question>(options4) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder_Question holder, int position, @NonNull QuestionsMember4 model) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String currentuserId = user.getUid();
                final  String postkey= getRef(position).getKey();

                holder.setitem4(getActivity(),model.getName(),model.getUrl(),model.getUserid(),model.getKey(),model.getQues(),model.getTime(),model.getCategory());

                String ques= getItem(position).getQues();
                String name = getItem(position).getName();
                String url = getItem(position).getUrl();
                String time = getItem(position).getTime();
                String userid = getItem(position).getUserid();
                holder.favChecker4(postkey);

                holder.reply1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(getActivity(),ReplyActivity4.class);
                        intent.putExtra("userid",userid); // id of user who asked question
                        intent.putExtra("que",ques);
                        intent.putExtra("postkey",postkey);
                        startActivity(intent);
                    }
                });
                holder.ib4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        favchecker = true;
                        favref.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(favchecker.equals(true))
                                {
                                    if(snapshot.child(postkey).hasChild(currentuserId))
                                    {
                                        favref.child(postkey).child(currentuserId).removeValue();
                                        shorao4(time);
                                        favchecker=false;
                                    }
                                    else
                                    {
                                        favref.child(postkey).child(currentuserId).setValue(true);
                                        member4.setName(name);
                                        member4.setTime(time);
                                        member4.setUserid(userid);
                                        member4.setUrl(url);
                                        member4.setQues(ques);

                                        // inside fav_list_ref all ques will be saved

                                        fav_list_ref.child(postkey).setValue(member4);

                                        favchecker= false;
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
            public ViewHolder_Question onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ques_item4,parent,false);
                return  new ViewHolder_Question(view);
            }
        };
        recyclerView4.setAdapter(firebaseRecyclerAdapter4);
        firebaseRecyclerAdapter4.startListening();
    }

    void shorao4(String time)
    {
        Query query = fav_list_ref.orderByChild("time").equalTo(time);
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
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.floatingActionButton5)
        {
            Intent intent = new Intent(getActivity(),Questions4.class);
            startActivity(intent);
        }
        else if (v.getId()==R.id.others_image)
        {
            Bottomsheet4 bottomsheet = new Bottomsheet4();
             bottomsheet.show(getFragmentManager(),"bottom");

        }


    }

    @Override
    public void onStart() {
        super.onStart();

        ref.get().addOnCompleteListener((task) -> {
            if(task.getResult().exists())
            {
                String url = task.getResult().getString(("Image URL"));

                Picasso.get().load(url).resize(400,400).centerCrop().into(iv);


            }
            else

            {
                Toast.makeText(getActivity(),"Error",Toast.LENGTH_LONG).show();
            }
        });
    }
}