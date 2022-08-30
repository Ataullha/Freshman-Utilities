package com.example.freshmanutilites;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
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

public class Fragment6 extends Fragment implements  View.OnClickListener{

    FloatingActionButton fb;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference ref;
    ImageView iv;
    FirebaseDatabase database2 = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference2 ;
    RecyclerView recyclerView2;
    QuestionsMember member2;
    DatabaseReference favref ; //favref for checking by clicking image
    DatabaseReference fav_list_ref; //for all saved fav question
    Boolean favchecker = false ;
    ImageButton ib;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_6,container,false);




        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentuserId = user.getUid();
        iv = getActivity().findViewById(R.id.residential_image);
        fb= getActivity().findViewById(R.id.floatingActionButton3);
        ref = db.collection("User").document(currentuserId);
        fb.setOnClickListener(this);
        iv.setOnClickListener(this);
        ib = getActivity().findViewById(R.id.f7button_res);
        ib.setOnClickListener(this);


        recyclerView2 =getActivity().findViewById(R.id.rv_f6);
        recyclerView2.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getActivity());
        linearLayoutManager2.setReverseLayout(true);
        linearLayoutManager2.setStackFromEnd(true);
        recyclerView2.setLayoutManager(linearLayoutManager2);
        databaseReference2= database2.getReference("All Questions Residential");
        member2= new QuestionsMember();

        favref = database2.getReference("favourites residential"); //for checking wheather question is saved or not
        fav_list_ref = database2.getReference("favourites residential list").child(currentuserId); //for saviing question


        FirebaseRecyclerOptions<QuestionsMember2> options2 = new FirebaseRecyclerOptions.Builder<QuestionsMember2>().setQuery(databaseReference2,QuestionsMember2.class).build();
        FirebaseRecyclerAdapter<QuestionsMember2,ViewHolder_Question> firebaseRecyclerAdapter2= new FirebaseRecyclerAdapter<QuestionsMember2, ViewHolder_Question>(options2) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder_Question holder, int position, @NonNull QuestionsMember2 model) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String currentuserId = user.getUid();
                final  String postkey= getRef(position).getKey();
                holder.setitem2(getActivity(),model.getName(),model.getUrl(),model.getUserid(),model.getKey(),model.getQues(),model.getTime(), model.getCategory());

                String ques= getItem(position).getQues();
                String name = getItem(position).getName();
                String url = getItem(position).getUrl();
                String time = getItem(position).getTime();
                String userid = getItem(position).getUserid();

                holder.reply1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(),ReplyActivity2.class);
                        intent.putExtra("userid",userid);
                        intent.putExtra("que",ques);
                        intent.putExtra("postkey",postkey);
                        startActivity(intent);
                    }
                });
                holder.favChecker2(postkey);


                holder.ib2.setOnClickListener(new View.OnClickListener() {
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
                                        shorao2(time);
                                        favchecker=false;
                                    }
                                    else
                                    {
                                        favref.child(postkey).child(currentuserId).setValue(true);
                                        member2.setName(name);
                                        member2.setTime(time);
                                        member2.setUserid(userid);
                                        member2.setUrl(url);
                                        member2.setQues(ques);

                                        // inside fav_list_ref all ques will be saved

                                        fav_list_ref.child(postkey).setValue(member2);

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
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ques_item2,parent,false);
                return  new ViewHolder_Question(view);
            }
        };
        recyclerView2.setAdapter(firebaseRecyclerAdapter2);
        firebaseRecyclerAdapter2.startListening();
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.floatingActionButton3)
        {
           Intent intent = new Intent(getActivity(),Questions2.class);
            startActivity(intent);
        }
        else if (v.getId()==R.id.residential_image)
        {
            Bottomsheet2 bottomsheet = new Bottomsheet2();
           bottomsheet.show(getFragmentManager(),"bottom");

        }
        else if (v.getId() == R.id.f7button_res) {
            dekhaoRes();
        }

    }

    private void dekhaoRes() {

        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.res_selection);
        TextView find = dialog.findViewById(R.id.textview_of_find);
        TextView rent = dialog.findViewById(R.id.textview_of_rent);
        TextView sell = dialog.findViewById(R.id.textview_of_sell);
        TextView buy = dialog.findViewById(R.id.textview_of_buy);


        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = "find";
                Toast.makeText(getActivity(), "FIND is selected", Toast.LENGTH_SHORT).show();
                sortques(category);
                dialog.cancel();

            }




        });


        rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = "rent";
                Toast.makeText(getActivity(), "RENT is selected", Toast.LENGTH_SHORT).show();
                sortques(category);
                dialog.cancel();

            }


        });


        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = "sell";
                Toast.makeText(getActivity(), "SELL is selected", Toast.LENGTH_SHORT).show();
                sortques(category);
                dialog.cancel();

            }


        });

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = "buy";
                Toast.makeText(getActivity(), "BUY is selected", Toast.LENGTH_SHORT).show();
                sortques(category);
                dialog.cancel();

            }


        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.Theme_Design_BottomSheetDialog;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    private void sortques(String category) {
        //to sort according to topic
        Query query = databaseReference2.orderByChild("category").startAt(category).endAt(category + "\uf0ff");

        FirebaseRecyclerOptions<QuestionsMember2> options2 = new FirebaseRecyclerOptions.Builder<QuestionsMember2>().setQuery(query,QuestionsMember2.class).build();
        FirebaseRecyclerAdapter<QuestionsMember2,ViewHolder_Question> firebaseRecyclerAdapter2= new FirebaseRecyclerAdapter<QuestionsMember2, ViewHolder_Question>(options2) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder_Question holder, int position, @NonNull QuestionsMember2 model) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String currentuserId = user.getUid();
                final  String postkey= getRef(position).getKey();
                holder.setitem2(getActivity(),model.getName(),model.getUrl(),model.getUserid(),model.getKey(),model.getQues(),model.getTime(),model.getCategory());

                String ques= getItem(position).getQues();
                String name = getItem(position).getName();
                String url = getItem(position).getUrl();
                String time = getItem(position).getTime();
                String userid = getItem(position).getUserid();

                holder.reply1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(),ReplyActivity2.class);
                        intent.putExtra("userid",userid);
                        intent.putExtra("que",ques);
                        intent.putExtra("postkey",postkey);
                        startActivity(intent);
                    }
                });
                holder.favChecker2(postkey);


                holder.ib2.setOnClickListener(new View.OnClickListener() {
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
                                        shorao2(time);
                                        favchecker=false;
                                    }
                                    else
                                    {
                                        favref.child(postkey).child(currentuserId).setValue(true);
                                        member2.setName(name);
                                        member2.setTime(time);
                                        member2.setUserid(userid);
                                        member2.setUrl(url);
                                        member2.setQues(ques);

                                        // inside fav_list_ref all ques will be saved

                                        fav_list_ref.child(postkey).setValue(member2);

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
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ques_item2,parent,false);
                return  new ViewHolder_Question(view);
            }
        };
        recyclerView2.setAdapter(firebaseRecyclerAdapter2);
        firebaseRecyclerAdapter2.startListening();


    }

    void shorao2(String time)
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
    public void onStart() {
        super.onStart();


        super.onStart();

        ref.get().addOnCompleteListener((task) -> {
            if(task.getResult().exists())
            {
                String url = task.getResult().getString(("Image URL"));

                Picasso.get().load(url).resize(400,400).centerCrop().into(iv);


            }
            else

            {
                //Toast.makeText(getActivity(),"Error",Toast.LENGTH_LONG).show();
            }
        });
    }
}