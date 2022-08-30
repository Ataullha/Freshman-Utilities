package com.example.freshmanutilites;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
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

public class PostViewHolder extends RecyclerView.ViewHolder {

    ImageView imageViewprofilepic, imageViewpost;
    TextView name_TV, description_TV, likes_TV, comment_TV, time_TV, nameprfoile_TV;
    Button likeBTN, moreBTN, commentBTN;
    DatabaseReference likesref;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    int likescount;

    // creating a variable for exoplayerview.
    SimpleExoPlayerView exoPlayerView;

    // creating a variable for exoplayer
    SimpleExoPlayer exoPlayer;


    // url of video which we are loading.
    String videoURL = "https://media.geeksforgeeks.org/wp-content/uploads/20201217163353/Screenrecorder-2020-12-17-16-32-03-350.mp4";




    public PostViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void SetPost(FragmentActivity activity, String name, String url, String postUri, String time, String uid, String type, String description) {

        imageViewprofilepic = itemView.findViewById(R.id.user_post_profile_pic);
        imageViewpost = itemView.findViewById(R.id.iv_post_item);
        //comment_TV = itemView.findViewById(R.id.tv_comment);
        description_TV = itemView.findViewById(R.id.tv_description);

        likeBTN = itemView.findViewById(R.id.like_button_post);
        likes_TV = itemView.findViewById(R.id.tv_like);
        moreBTN = itemView.findViewById(R.id.more_button_post);
        time_TV = itemView.findViewById(R.id.tv_time_post);
        name_TV = itemView.findViewById(R.id.tv_name_post);
        commentBTN = itemView.findViewById(R.id.comment_button_post);

        exoPlayerView = itemView.findViewById(R.id.idExoPlayerVIew);



       if(type.equals("vv")) {
            imageViewpost.setVisibility(View.INVISIBLE);
            exoPlayerView.setVisibility(View.VISIBLE);
            description_TV.setText(description);
            time_TV.setText(calc(time));
            name_TV.setText(name);
            Picasso.get().load(url).fit().into(imageViewprofilepic);

            try {
                BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
                TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
                exoPlayer = ExoPlayerFactory.newSimpleInstance(activity, trackSelector);
                Uri videouri = Uri.parse(postUri);
                DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer_video");
                ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
                MediaSource mediaSource = new ExtractorMediaSource(videouri, dataSourceFactory, extractorsFactory, null, null);
                exoPlayerView.setPlayer(exoPlayer);
                exoPlayer.prepare(mediaSource);
                exoPlayer.setPlayWhenReady(false);
               // Toast.makeText(activity, videoURL, Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                //Toast.makeText(activity, "Exo Player is Not Working!", Toast.LENGTH_SHORT).show();
            }


        }else if (type.equals("iv")) {
            Picasso.get().load(url).fit().into(imageViewprofilepic);
            Picasso.get().load(postUri).into(imageViewpost);
            description_TV.setText(description);
            time_TV.setText(calc(time));
            name_TV.setText(name);
            exoPlayerView.setVisibility(View.INVISIBLE);
        }
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

    public void likeChecker(String postkey) {

        likeBTN = itemView.findViewById(R.id.like_button_post);

        likesref = database.getReference("post likes");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid =  user.getUid().toString();

        likesref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(postkey).hasChild(uid))
                {

                    likeBTN.setText("LIKED");
                    likeBTN.setTextColor((int) R.color.red);
                    likescount = (int) snapshot.child(postkey).getChildrenCount();
                    likes_TV.setText(likescount+" likes"); // 1+es/1-e
                } else {
                    likeBTN.setTextColor((int)R.color.black);
                    likeBTN.setText("LIKE");
                    likescount = (int) snapshot.child(postkey).getChildrenCount();
                    likes_TV.setText(likescount+" likes");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

}