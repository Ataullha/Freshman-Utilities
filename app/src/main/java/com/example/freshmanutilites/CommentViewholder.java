package com.example.freshmanutilites;

import android.app.Application;
import android.text.format.DateUtils;
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

public class CommentViewholder extends RecyclerView.ViewHolder {
    ImageView iv;
    TextView name1,ans1,time1,uid,upvote,votes;
    int votesno;
    DatabaseReference ref;
    FirebaseDatabase database;
    public CommentViewholder(@NonNull View itemView) {
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
    public void setComment(Application application, String name, String ans, String uid, String time, String url)
    {
        iv = itemView.findViewById(R.id.image_of_comment);
        name1 = itemView.findViewById(R.id.name_of_comment);
        time1 = itemView.findViewById(R.id.time_of_comment);
        ans1 = itemView.findViewById(R.id.ans_from_comment);
        name1.setText(name);
        time1.setText(calc(time));
        ans1.setText(ans);
        Picasso.get().load(url).fit().into(iv);




    }

    public  void  likeDekho(String postkey)
    {
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("votes") ;

        upvote = itemView.findViewById(R.id.votes_from_cmnt) ;
        votes = itemView.findViewById(R.id.voteno_from_cmnt);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentuid = user.getUid();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(postkey).hasChild(currentuid))
                {
                    //when it is liked
                    upvote.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_thumb_up_24,0,0,0);

                   //it has a child which means it is liked
                    votesno = (int) snapshot.child(postkey).getChildrenCount();
                    votes.setText(Integer.toString(votesno)+" LIKES");

                }else
                {

                    upvote.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_thumb_up_off_alt_24,0,0,0);
                    votesno = (int) snapshot.child(postkey).getChildrenCount();
                    votes.setText(Integer.toString(votesno)+" LIKES");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
