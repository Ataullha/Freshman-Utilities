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

import java.util.Locale;

public class Fragment7 extends Fragment implements View.OnClickListener {

    FloatingActionButton fb;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference ref;
    ImageView iv;
    FirebaseDatabase database3 = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference3;
    RecyclerView recyclerView3;
    QuestionsMember member3;
    DatabaseReference favref; //favref for checking by clicking image
    DatabaseReference fav_list_ref; //for all saved fav question
    Boolean favchecker = false;
    ImageButton ib;
    String dept;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_7, container, false);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        iv = getActivity().findViewById(R.id.academic_image);
        fb = getActivity().findViewById(R.id.floatingActionButton4);
      //  ib = getActivity().findViewById(R.id.f7button_vicky);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentuserId = user.getUid();

        ref = db.collection("User").document(currentuserId);
        fb.setOnClickListener(this);
        iv.setOnClickListener(this);
//        ib.setOnClickListener(this);

        recyclerView3 = getActivity().findViewById(R.id.rv_f7);
        recyclerView3.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(getActivity());
        linearLayoutManager3.setReverseLayout(true);
        linearLayoutManager3.setStackFromEnd(true);
        recyclerView3.setLayoutManager(linearLayoutManager3);

        databaseReference3 = database3.getReference("All Questions Academic");
        member3 = new QuestionsMember();
        favref = database3.getReference("favourites academic"); //for checking wheather question is saved or not
        fav_list_ref = database3.getReference("favourites academic list").child(currentuserId); //for saviing question

      //  Here we are calling the function which will show us dept wise questions
        ref.get().addOnCompleteListener((task) -> {
            if (task.getResult().exists()) {
                //String url = task.getResult().getString(("Image URL"));
                dept = task.getResult().getString(("Dept")).toLowerCase();

                sortques(dept);


            } else {
                //Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG).show();
            }
        });



     /*  FirebaseRecyclerOptions<QuestionsMember3> options3 = new FirebaseRecyclerOptions.Builder<QuestionsMember3>().setQuery(databaseReference3, QuestionsMember3.class).build();
        FirebaseRecyclerAdapter<QuestionsMember3, ViewHolder_Question> firebaseRecyclerAdapter3 = new FirebaseRecyclerAdapter<QuestionsMember3, ViewHolder_Question>(options3) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder_Question holder, int position, @NonNull QuestionsMember3 model) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String currentuserId = user.getUid();
                final String postkey = getRef(position).getKey();

                holder.setitem3(getActivity(), model.getName(), model.getUrl(), model.getUserid(), model.getKey(), model.getQues(), model.getTime(), model.getCategory());

                String ques = getItem(position).getQues();
                String name = getItem(position).getName();
                String url = getItem(position).getUrl();
                String time = getItem(position).getTime();
                String userid = getItem(position).getUserid();
                holder.reply1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), ReplyActivity3.class);
                        intent.putExtra("userid", userid);
                        intent.putExtra("que", ques);
                        intent.putExtra("postkey", postkey);
                        startActivity(intent);
                    }
                });
                holder.favChecker3(postkey);
                holder.ib3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        favchecker = true;
                        favref.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (favchecker.equals(true)) {
                                    if (snapshot.child(postkey).hasChild(currentuserId)) {
                                        favref.child(postkey).child(currentuserId).removeValue();
                                        shorao3(time);
                                        favchecker = false;
                                    } else {
                                        favref.child(postkey).child(currentuserId).setValue(true);
                                        member3.setName(name);
                                        member3.setTime(time);
                                        member3.setUserid(userid);
                                        member3.setUrl(url);
                                        member3.setQues(ques);

                                        // inside fav_list_ref all ques will be saved

                                        fav_list_ref.child(postkey).setValue(member3);

                                        favchecker = false;
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
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ques_item3, parent, false);
                return new ViewHolder_Question(view);
            }
        };

        recyclerView3.setAdapter(firebaseRecyclerAdapter3);
        firebaseRecyclerAdapter3.startListening();*/


    }

  /*  void shorao3(String time) {
        Query query = fav_list_ref.orderByChild("time").equalTo(time);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    dataSnapshot.getRef().removeValue();
                    // Toast.makeText(getActivity(),"Deleted re",Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });
    }*/

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.floatingActionButton4) {
            Intent intent = new Intent(getActivity(), Questions3.class);
            startActivity(intent);
        } else if (v.getId() == R.id.academic_image) {
            Bottomsheet3 bottomsheet = new Bottomsheet3();
            bottomsheet.show(getFragmentManager(), "bottom");

        }

    }

    /* we can use it if we add a spinner of dept
    private void dekhaoCourse() {
        Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.course_selection);


        TextView arc = dialog.findViewById(R.id.textview_of_ARC);
        TextView bmb = dialog.findViewById(R.id.textview_of_BMB);
        TextView cee = dialog.findViewById(R.id.textview_of_CEE);
        TextView cep = dialog.findViewById(R.id.textview_of_CEP);
        TextView chem = dialog.findViewById(R.id.textview_of_CHEM);
        TextView cse = dialog.findViewById(R.id.textview_of_CSE);
        TextView eee = dialog.findViewById(R.id.textview_of_EEE);
        TextView fes = dialog.findViewById(R.id.textview_of_FES);
        TextView fet = dialog.findViewById(R.id.textview_of_FET);
        TextView gee = dialog.findViewById(R.id.textview_of_GEE);
        TextView ipe = dialog.findViewById(R.id.textview_of_IPE);
        TextView me = dialog.findViewById(R.id.textview_of_ME);
        TextView mat = dialog.findViewById(R.id.textview_of_MAT);
        TextView ocg = dialog.findViewById(R.id.textview_of_OCG);
        TextView phy = dialog.findViewById(R.id.textview_of_PHY);
        TextView pme = dialog.findViewById(R.id.textview_of_PME);
        TextView stat = dialog.findViewById(R.id.textview_of_STAT);


        arc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = "arc";
                Toast.makeText(getActivity(), "ARC is selected", Toast.LENGTH_SHORT).show();
                sortques(category);
                dialog.cancel();

            }


        });
        bmb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = "bmb";
                Toast.makeText(getActivity(), "BMB is selected", Toast.LENGTH_SHORT).show();
                sortques(category);
                dialog.cancel();

            }

        });
        cee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = "cee";
                Toast.makeText(getActivity(), "CEE is selected", Toast.LENGTH_SHORT).show();
                sortques(category);
                dialog.cancel();

            }

        });
        cep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = "cep";
                Toast.makeText(getActivity(), "CEP is selected", Toast.LENGTH_SHORT).show();
                sortques(category);
                dialog.cancel();

            }

        });
        chem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = "chem";
                Toast.makeText(getActivity(), "CHEM is selected", Toast.LENGTH_SHORT).show();
                sortques(category);
                dialog.cancel();

            }

        });
        cse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = "cse";
                Toast.makeText(getActivity(), "CSE is selected", Toast.LENGTH_SHORT).show();
                sortques(category);
                dialog.cancel();

            }

        });
        eee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = "eee";
                Toast.makeText(getActivity(), "EEE is selected", Toast.LENGTH_SHORT).show();
                sortques(category);
                dialog.cancel();

            }

        });
        fes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = "fes";
                Toast.makeText(getActivity(), "FES is selected", Toast.LENGTH_SHORT).show();
                sortques(category);
                dialog.cancel();

            }

        });
        fet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = "fet";
                Toast.makeText(getActivity(), "FET is selected", Toast.LENGTH_SHORT).show();
                sortques(category);
                dialog.cancel();

            }

        });
        gee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = "gee";
                Toast.makeText(getActivity(), "GEE is selected", Toast.LENGTH_SHORT).show();
                sortques(category);
                dialog.cancel();

            }

        });

        ipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = "ipe";
                Toast.makeText(getActivity(), "IPE is selected", Toast.LENGTH_SHORT).show();
                sortques(category);
                dialog.cancel();

            }

        });
        me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = "me";
                Toast.makeText(getActivity(), "ME is selected", Toast.LENGTH_SHORT).show();
                sortques(category);
                dialog.cancel();

            }

        });
        mat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = "mat";
                Toast.makeText(getActivity(), "MAT is selected", Toast.LENGTH_SHORT).show();
                sortques(category);
                dialog.cancel();

            }

        });

        ocg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = "ocg";
                Toast.makeText(getActivity(), "OCG is selected", Toast.LENGTH_SHORT).show();
                sortques(category);
                dialog.cancel();

            }

        });
        phy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = "phy";
                Toast.makeText(getActivity(), "PHY is selected", Toast.LENGTH_SHORT).show();
                sortques(category);
                dialog.cancel();

            }

        });
        pme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = "pme";
                Toast.makeText(getActivity(), "PME is selected", Toast.LENGTH_SHORT).show();
                sortques(category);
                dialog.cancel();

            }

        });
        stat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = "stat";
                Toast.makeText(getActivity(), "STAT is selected", Toast.LENGTH_SHORT).show();
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
*/
    public void sortques(String category) {
        Query query = databaseReference3.orderByChild("category").startAt(category).endAt(category + "\uf0ff");



        FirebaseRecyclerOptions<QuestionsMember3> options3 = new FirebaseRecyclerOptions.Builder<QuestionsMember3>().setQuery(query, QuestionsMember3.class).build();
        FirebaseRecyclerAdapter<QuestionsMember3, ViewHolder_Question> firebaseRecyclerAdapter3 = new FirebaseRecyclerAdapter<QuestionsMember3, ViewHolder_Question>(options3) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder_Question holder, int position, @NonNull QuestionsMember3 model) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String currentuserId = user.getUid();
                final String postkey = getRef(position).getKey();

                holder.setitem3(getActivity(), model.getName(), model.getUrl(), model.getUserid(), model.getKey(), model.getQues(), model.getTime(), model.getCategory(),model.getDept());

                String ques = getItem(position).getQues();
                String name = getItem(position).getName();
                String url = getItem(position).getUrl();
                String time = getItem(position).getTime();
                String userid = getItem(position).getUserid();


                holder.reply1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), ReplyActivity3.class);
                        intent.putExtra("userid", userid);
                        intent.putExtra("que", ques);
                        intent.putExtra("postkey", postkey);
                        startActivity(intent);
                    }
                });
                holder.favChecker3(postkey);



                holder.ib3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        favchecker = true;
                        favref.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (favchecker.equals(true)) {
                                    if (snapshot.child(postkey).hasChild(currentuserId)) {
                                        favref.child(postkey).child(currentuserId).removeValue();
                                        shorao3(time);
                                        favchecker = false;
                                    } else {
                                        favref.child(postkey).child(currentuserId).setValue(true);
                                        member3.setName(name);
                                        member3.setTime(time);
                                        member3.setUserid(userid);
                                        member3.setUrl(url);
                                        member3.setQues(ques);

                                        // inside fav_list_ref all ques will be saved

                                        fav_list_ref.child(postkey).setValue(member3);

                                        favchecker = false;
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
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ques_item3, parent, false);
                return new ViewHolder_Question(view);
            }
        };

        recyclerView3.setAdapter(firebaseRecyclerAdapter3);
        firebaseRecyclerAdapter3.startListening();



    }
    public void shorao3(String time) {
        Query query = fav_list_ref.orderByChild("time").equalTo(time);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
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


        ref.get().addOnCompleteListener((task) -> {
            if (task.getResult().exists()) {
                String url = task.getResult().getString(("Image URL"));
                 dept = task.getResult().getString(("Dept")).toLowerCase();




                Picasso.get().load(url).resize(400, 400).centerCrop().into(iv);

                //sortques(dept);


            } else {
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG).show();
            }
        });
    }
}