package com.example.freshmanutilites;

import android.annotation.SuppressLint;
import android.app.Application;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AnswerViewholder extends RecyclerView.ViewHolder {

   ImageView iv;
   TextView name1,ans1,time1,uid,upvote,votes;
   int votesno;
   DatabaseReference ref;
   FirebaseDatabase database;

    public AnswerViewholder(@NonNull View itemView) {
        super(itemView);


    }
    private String calc(String time)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yy HH:mm:ss");

        try {
            long time1 = sdf.parse(time).getTime();
            long now = System.currentTimeMillis();
            CharSequence ago =
                    DateUtils.getRelativeTimeSpanString(time1, now, DateUtils.MINUTE_IN_MILLIS);

            return ago+"";
        } catch ( ParseException e) {
            e.printStackTrace();
        }
        return "";
    }
    public void setAnswer(Application application,String name, String ans, String uid,String time, String url)
    {
        iv = itemView.findViewById(R.id.image_of_answer);
        name1 = itemView.findViewById(R.id.name_of_answer);
        time1 = itemView.findViewById(R.id.time_of_answer);
        ans1 = itemView.findViewById(R.id.ans_from_answer);
        name1.setText(name);
        time1.setText(calc(time));
        ans1.setText(ans);
        Picasso.get().load(url).resize(400,400).centerCrop().into(iv);



    }

    public void setAnswer2(Application application,String name, String ans, String uid,String time, String url)
    {
        iv = itemView.findViewById(R.id.image_of_answer2);
        name1 = itemView.findViewById(R.id.name_of_answer2);
        time1 = itemView.findViewById(R.id.time_of_answer2);
        ans1 = itemView.findViewById(R.id.ans_from_answer2);
        name1.setText(name);
        time1.setText(calc(time));
        ans1.setText(ans);
        Picasso.get().load(url).resize(400,400).centerCrop().into(iv);



    }

    public void setAnswer3(Application application,String name, String ans, String uid,String time, String url)
    {
        iv = itemView.findViewById(R.id.image_of_answer3);
        name1 = itemView.findViewById(R.id.name_of_answer3);
        time1 = itemView.findViewById(R.id.time_of_answer3);
        ans1 = itemView.findViewById(R.id.ans_from_answer3);
        name1.setText(name);
        time1.setText(calc(time));
        ans1.setText(ans);
        Picasso.get().load(url).resize(400,400).centerCrop().into(iv);



    }

    public void setAnswer4(Application application,String name, String ans, String uid,String time, String url)
    {
        iv = itemView.findViewById(R.id.image_of_answer4);
        name1 = itemView.findViewById(R.id.name_of_answer4);
        time1 = itemView.findViewById(R.id.time_of_answer4);
        ans1 = itemView.findViewById(R.id.ans_from_answer4);
        name1.setText(name);
        time1.setText(calc(time));
        ans1.setText(ans);
        Picasso.get().load(url).resize(400,400).centerCrop().into(iv);



    }
    //for vote
    public  void  upvoteDekho(String postkey)
    {
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("votes") ;

        upvote = itemView.findViewById(R.id.vote_from_ans) ;
        votes = itemView.findViewById(R.id.voteno_from_ans);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentuid = user.getUid();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(postkey).hasChild(currentuid))
                {
                    upvote.setText("VOTED");
                   // upvote.setBackgroundColor(R.color.white);
                    votesno = (int) snapshot.child(postkey).getChildrenCount();
                    votes.setText(Integer.toString(votesno)+" VOTES");

                }else
                {

                    upvote.setText("UPVOTE");
                    votesno = (int) snapshot.child(postkey).getChildrenCount();
                    votes.setText(Integer.toString(votesno)+" VOTES");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public  void  upvoteDekho2(String postkey)
    {
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("votes") ;

        upvote = itemView.findViewById(R.id.vote_from_ans2) ;
        votes = itemView.findViewById(R.id.voteno_from_ans2);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentuid = user.getUid();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(postkey).hasChild(currentuid))
                {
                    upvote.setText("VOTED");
                    // upvote.setBackgroundColor(R.color.white);
                    votesno = (int) snapshot.child(postkey).getChildrenCount();
                    votes.setText(Integer.toString(votesno)+" VOTES");

                }else
                {

                    upvote.setText("UPVOTE");
                    votesno = (int) snapshot.child(postkey).getChildrenCount();
                    votes.setText(Integer.toString(votesno)+" VOTES");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public  void  upvoteDekho3(String postkey)
    {
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("votes") ;

        upvote = itemView.findViewById(R.id.vote_from_ans3) ;
        votes = itemView.findViewById(R.id.voteno_from_ans3);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentuid = user.getUid();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(postkey).hasChild(currentuid))
                {
                    upvote.setText("VOTED");
                    // upvote.setBackgroundColor(R.color.white);
                    votesno = (int) snapshot.child(postkey).getChildrenCount();
                    votes.setText(Integer.toString(votesno)+" VOTES");

                }else
                {

                    upvote.setText("UPVOTE");
                    votesno = (int) snapshot.child(postkey).getChildrenCount();
                    votes.setText(Integer.toString(votesno)+" VOTES");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public  void  upvoteDekho4(String postkey)
    {
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("votes") ;

        upvote = itemView.findViewById(R.id.vote_from_ans4) ;
        votes = itemView.findViewById(R.id.voteno_from_ans4);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentuid = user.getUid();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(postkey).hasChild(currentuid))
                {
                    upvote.setText("VOTED");
                    // upvote.setBackgroundColor(R.color.white);
                    votesno = (int) snapshot.child(postkey).getChildrenCount();
                    votes.setText(Integer.toString(votesno)+" VOTES");

                }else
                {

                    upvote.setText("UPVOTE");
                    votesno = (int) snapshot.child(postkey).getChildrenCount();
                    votes.setText(Integer.toString(votesno)+" VOTES");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}






























