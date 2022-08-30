package com.example.freshmanutilites;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class Fragment5 extends Fragment implements View.OnClickListener{



    FloatingActionButton fp;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference ref;
    ImageView iv;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference ;



    RecyclerView recyclerView;
    DatabaseReference favref ; //favref for checking by clicking image
    DatabaseReference fav_list_ref; //for all saved fav question
    Boolean favchecker = false ;
    QuestionsMember member;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // inflate the xml fragment ...
        View view = inflater.inflate(R.layout.fragment_5,container,false);
        return view;




    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentuserId = user.getUid();

        recyclerView =getActivity().findViewById(R.id.rv_f5);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        databaseReference= database.getReference("All Questions Varsity");
        member= new QuestionsMember();
        favref = database.getReference("favourites varsity"); //for checking wheather question is saved or not
        fav_list_ref = database.getReference("favourites varsity list").child(currentuserId); //for saviing question





















        iv = getActivity().findViewById(R.id.varsity_image);
        fp= getActivity().findViewById(R.id.floatingActionButton2);
        ref = db.collection("User").document(currentuserId);
        fp.setOnClickListener(this);
        iv.setOnClickListener(this);

        // retrive data

        FirebaseRecyclerOptions<QuestionsMember> options =
                new FirebaseRecyclerOptions.Builder<QuestionsMember>().setQuery(databaseReference,QuestionsMember.class).build();
        FirebaseRecyclerAdapter<QuestionsMember,ViewHolder_Question> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<QuestionsMember,ViewHolder_Question>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ViewHolder_Question holder, int position, @NonNull QuestionsMember model) {

                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        String currentuserId = user.getUid();
                        final  String postkey= getRef(position).getKey();

                        holder.setitem(getActivity(),model.getName(),model.getUrl(),model.getUserid(),model.getKey(),model.getQues(),model.getTime());

                        String ques= getItem(position).getQues();
                        String name = getItem(position).getName();
                        String url = getItem(position).getUrl();
                        String time = getItem(position).getTime();
                        String userid = getItem(position).getUserid();

                    holder.reply1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(),ReplyActivity.class);
                            intent.putExtra("userid",userid);
                            intent.putExtra("que",ques);
                            intent.putExtra("postkey",postkey);
                            startActivity(intent);
                        }
                    });

                        holder.favChecker(postkey);

                        holder.ib1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                favchecker= true;
                                favref.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if(favchecker.equals(true))
                                        {
                                            if(snapshot.child(postkey).hasChild(currentuserId))
                                            {
                                                favref.child(postkey).child(currentuserId).removeValue();
                                                shorao(time);
                                                favchecker=false;
                                            }
                                            else
                                            {
                                                favref.child(postkey).child(currentuserId).setValue(true);
                                                member.setName(name);
                                                member.setTime(time);
                                                member.setUserid(userid);
                                                member.setUrl(url);
                                                member.setQues(ques);

                                                // inside fav_list_ref all ques will be saved
                                                fav_list_ref.child(postkey).setValue(member);

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
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quest_utem,parent,false);
                        return  new ViewHolder_Question(view);

                    }
                };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
        firebaseRecyclerAdapter.startListening();













    }

    void shorao(String time)
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

        if(v.getId()==R.id.floatingActionButton2)
        {
            Intent intent = new Intent(getActivity(),Questions.class);
            startActivity(intent);
        }
        else if (v.getId()==R.id.varsity_image)
        {
            Bottomsheet bottomsheet = new Bottomsheet();
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

                Picasso.get().load(url).fit().into(iv);


            }
            else

            {
                Toast.makeText(getActivity(),"Error",Toast.LENGTH_LONG).show();
            }
        });
    }
}