package com.example.freshmanutilites;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
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
import java.util.TimeZone;

public class ViewHolder_Question extends RecyclerView.ViewHolder
{
    ImageView iv;
    TextView time1,name1,ques1,reply1,reply,cat1,fv1;
    TextView delete;
    ImageButton ib1,ib2,ib3,ib4;
    DatabaseReference favref;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference favref2;
    FirebaseDatabase database2 = FirebaseDatabase.getInstance();
    DatabaseReference favref3;
    FirebaseDatabase database3 = FirebaseDatabase.getInstance();
    DatabaseReference favref4;
    FirebaseDatabase database4 = FirebaseDatabase.getInstance();



    public ViewHolder_Question(@NonNull View itemView)
    {
        super(itemView);
    }

    public void setitem(FragmentActivity activity, String name, String url, String userid, String key, String ques, String time)
    {
        iv= itemView.findViewById(R.id.questionutem_image);
        time1= itemView.findViewById(R.id.time_fromquestion_item);

        name1 = itemView.findViewById(R.id.name_fromquestion_item);

        ques1 = itemView.findViewById(R.id.ques_fromquestion_item);

        reply1= itemView.findViewById(R.id.reply_ques);


        Picasso.get().load(url).fit().into(iv);
        time1.setText(calc(time));
        name1.setText(name);
        ques1.setText(ques);


    }

    public void setitem2(FragmentActivity activity, String name, String url, String userid, String key, String ques, String time,String category)
    {
        iv= itemView.findViewById(R.id.questionutem_image2);
        time1= itemView.findViewById(R.id.time_fromquestion_item2);



        name1 = itemView.findViewById(R.id.name_fromquestion_item2);


        ques1 = itemView.findViewById(R.id.ques_fromquestion_item2);

        reply1= itemView.findViewById(R.id.reply_ques2);


        Picasso.get().load(url).fit().into(iv);

/*

 */

        /*SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yy HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            long time = sdf.parse(time).getTime();
            long now = System.currentTimeMillis();
            CharSequence ago =
                    DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS);
            ago += "" ;
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
        time1.setText(calc(time));
        name1.setText(name);
        ques1.setText(ques);


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


    public void setitem3(FragmentActivity activity, String name, String url, String userid, String key, String ques, String time,String category,String dept)
    {
        iv= itemView.findViewById(R.id.questionutem_image3);


        time1= itemView.findViewById(R.id.time_fromquestion_item3);

        name1 = itemView.findViewById(R.id.name_fromquestion_item3);

        ques1 = itemView.findViewById(R.id.ques_fromquestion_item3);

        reply1= itemView.findViewById(R.id.reply_ques3);
        cat1= itemView.findViewById(R.id.cat_fromquestion_item3);

        Picasso.get().load(url).fit().into(iv);

        time1.setText(calc(time));
        name1.setText(name);
        ques1.setText(ques);
        //String cat = category.toUpperCase();
        cat1.setText(dept);


    }

    public void setitem4(FragmentActivity activity, String name, String url, String userid, String key, String ques, String time,String category)
    {
        iv= itemView.findViewById(R.id.questionutem_image4);
        time1= itemView.findViewById(R.id.time_fromquestion_item4);

        name1 = itemView.findViewById(R.id.name_fromquestion_item4);

        ques1 = itemView.findViewById(R.id.ques_fromquestion_item4);

        reply1= itemView.findViewById(R.id.reply_ques4);

        Picasso.get().load(url).fit().into(iv);
        time1.setText(calc(time));
        name1.setText(name);
        ques1.setText(ques);


    }

    public void favChecker(String postkey) {

        favref = database.getReference("favourites varsity");
        ib1= itemView.findViewById(R.id.fav_varsity);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid =  user.getUid();
        favref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(postkey).hasChild(uid))
                {
                    ib1.setImageResource(R.drawable.ic_baseline_star_24);
                }
                else
                {
                    ib1.setImageResource(R.drawable.ic_baseline_star_border_24);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


    public void setitemfav(Application activity, String name, String url, String userid, String key, String ques, String time)
    {
        TextView timetext = itemView.findViewById(R.id.time_fromfav_ques);
        ImageView iv2 = itemView.findViewById(R.id.favquesitem_image);
        TextView nametext =  itemView.findViewById(R.id.name_fromfavques_item);
        TextView questext = itemView.findViewById(R.id.ques_fromfav_ques_item);
        reply = itemView.findViewById(R.id.reply_fav_ques);
        Picasso.get().load(url).fit().into(iv2);
        nametext.setText(name);
        timetext.setText(calc(time));
        questext.setText(ques);



    }

    public void setitemfav2(Application activity, String name, String url, String userid, String key, String ques, String time)
    {
        TextView timetext = itemView.findViewById(R.id.time_fromfav_ques2);
        ImageView iv2 = itemView.findViewById(R.id.favquesitem_image2);
        TextView nametext =  itemView.findViewById(R.id.name_fromfavques_item2);
        TextView questext = itemView.findViewById(R.id.ques_fromfav_ques_item2);
        reply = itemView.findViewById(R.id.reply_fav_ques2);
        nametext.setText(name);
        timetext.setText(calc(time));
        questext.setText(ques);
        Picasso.get().load(url).fit().into(iv2);




    }

    public void setitemfav3(Application activity, String name, String url, String userid, String key, String ques, String time)
    {
        TextView timetext = itemView.findViewById(R.id.time_fromfav_ques3);
        ImageView iv2 = itemView.findViewById(R.id.favquesitem_image3);
        TextView nametext =  itemView.findViewById(R.id.name_fromfavques_item3);
        TextView questext = itemView.findViewById(R.id.ques_fromfav_ques_item3);
        reply = itemView.findViewById(R.id.reply_fav_ques3);
        nametext.setText(name);
        timetext.setText(calc(time));
        questext.setText(ques);
        Picasso.get().load(url).fit().into(iv2);



    }

    public void setitemfav4(Application activity, String name, String url, String userid, String key, String ques, String time)
    {
        TextView timetext = itemView.findViewById(R.id.time_fromfav_ques4);
        ImageView iv2 = itemView.findViewById(R.id.favquesitem_image4);
        TextView nametext =  itemView.findViewById(R.id.name_fromfavques_item4);
        TextView questext = itemView.findViewById(R.id.ques_fromfav_ques_item4);
        reply = itemView.findViewById(R.id.reply_fav_ques4);
        nametext.setText(name);
        timetext.setText(calc(time));
        questext.setText(ques);
        Picasso.get().load(url).fit().into(iv2);



    }

    public void setitemyourques(Application activity, String name, String url, String userid, String key, String ques, String time)
    {
        TextView timetext = itemView.findViewById(R.id.time_fromdel_ques);
        ImageView iv2 = itemView.findViewById(R.id.delquesitem_image);
        TextView nametext =  itemView.findViewById(R.id.name_fromdelques_item);
        TextView questext = itemView.findViewById(R.id.ques_fromdel_ques_item);
        delete = itemView.findViewById(R.id.delete_your_ques);
        reply = itemView.findViewById(R.id.reply_your_ques);
        Picasso.get().load(url).fit().into(iv2);
        nametext.setText(name);
        timetext.setText(calc(time));
        questext.setText(ques);



    }
    public void setitemyourques2(Application activity, String name, String url, String userid, String key, String ques, String time)
    {
        TextView timetext = itemView.findViewById(R.id.time_fromdel_ques2);
        ImageView iv2 = itemView.findViewById(R.id.delquesitem_image2);
        TextView nametext =  itemView.findViewById(R.id.name_fromdelques_item2);
        TextView questext = itemView.findViewById(R.id.ques_fromdel_ques_item2);
        delete = itemView.findViewById(R.id.delete_your_ques2);
        nametext.setText(name);
        reply = itemView.findViewById(R.id.reply_your_ques2);
        timetext.setText(calc(time));
        questext.setText(ques);
        Picasso.get().load(url).fit().into(iv2);

    }

    public void setitemyourques3(Application activity, String name, String url, String userid, String key, String ques, String time)
    {
        TextView timetext = itemView.findViewById(R.id.time_fromdel_ques3);
        ImageView iv2 = itemView.findViewById(R.id.delquesitem_image3);
        TextView nametext =  itemView.findViewById(R.id.name_fromdelques_item3);
        TextView questext = itemView.findViewById(R.id.ques_fromdel_ques_item3);
        delete = itemView.findViewById(R.id.delete_your_ques3);
        reply = itemView.findViewById(R.id.reply_your_ques3);
        nametext.setText(name);
        timetext.setText(calc(time));
        questext.setText(ques);
        Picasso.get().load(url).fit().into(iv2);

    }

    public void setitemyourques4(Application activity, String name, String url, String userid, String key, String ques, String time)
    {
        TextView timetext = itemView.findViewById(R.id.time_fromdel_ques4);
        ImageView iv2 = itemView.findViewById(R.id.delquesitem_image4);
        TextView nametext =  itemView.findViewById(R.id.name_fromdelques_item4);
        TextView questext = itemView.findViewById(R.id.ques_fromdel_ques_item4);
        delete = itemView.findViewById(R.id.delete_your_ques4);
        reply = itemView.findViewById(R.id.reply_your_ques4);
        nametext.setText(name);

        timetext.setText(calc(time));
        questext.setText(ques);
        Picasso.get().load(url).fit().into(iv2);

    }


    public void favChecker2(String postkey) {

        favref = database.getReference("favourites residential");
        ib2= itemView.findViewById(R.id.fav_residential);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid =  user.getUid();
        favref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(postkey).hasChild(uid))
                {
                    ib2.setImageResource(R.drawable.ic_baseline_star2_24);
                }
                else
                {
                    ib2.setImageResource(R.drawable.ic_baseline_star_border2_24);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void favChecker3(String postkey) {
        favref = database.getReference("favourites academic");
        ib3= itemView.findViewById(R.id.fav_acedemic);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid =  user.getUid();
        favref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(postkey).hasChild(uid))
                {
                    ib3.setImageResource(R.drawable.ic_baseline_star3_24);
                }
                else
                {
                    ib3.setImageResource(R.drawable.ic_baseline_star_border3_24);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void favChecker4(String postkey) {
        favref = database.getReference("favourites others");
        ib4= itemView.findViewById(R.id.fav_others);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid =  user.getUid();
        favref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(postkey).hasChild(uid))
                {
                    ib4.setImageResource(R.drawable.ic_baseline_star4_24);
                }
                else
                {
                    ib4.setImageResource(R.drawable.ic_baseline_star_border4_24);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
