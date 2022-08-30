package com.example.freshmanutilites;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Fragment4 extends Fragment implements View.OnClickListener {

    Button createpostBTN;
    RecyclerView recyclerViewPost;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference,likeref,db1,db2,db3;
    Boolean likechecker = false;
    DocumentReference ref;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ImageView iv;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // inflate the xml fragment ...
        View view = inflater.inflate(R.layout.fragment4,container,false);
        return view;

    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        iv = getActivity().findViewById(R.id.f4img);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentuid = user.getUid();
        db1 = database.getReference("All Images").child(currentuid);
        db2 = database.getReference("All Videos").child(currentuid);
        db3 = database.getReference("All Posts");
        //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentuserId = user.getUid();
        ref = db.collection("User").document(currentuserId);

        createpostBTN = getActivity().findViewById(R.id.create_post_btn_f4);


        reference = database.getReference("All Posts");
        likeref = database.getReference("post likes");
        recyclerViewPost = getActivity().findViewById(R.id.rv_f4_post);
        recyclerViewPost.setHasFixedSize(true);
        // for reverse the layout for the
        LinearLayoutManager linearLayoutManagerPost = new LinearLayoutManager(getActivity());
        linearLayoutManagerPost.setReverseLayout(true); // for  reverser order the layout
        // LIFO ?
        linearLayoutManagerPost.setStackFromEnd(true);  // start from the end of the stack
        recyclerViewPost.setLayoutManager(linearLayoutManagerPost);

        createpostBTN.setOnClickListener(this);





    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.create_post_btn_f4:
                startActivity(new Intent(getActivity(),PostActivity.class));
                break;

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
        // firebase recycler option and post member class
        FirebaseRecyclerOptions<PostMember> options =
                new FirebaseRecyclerOptions.Builder<PostMember>()
                        .setQuery(reference,PostMember.class)
                        .build();
        FirebaseRecyclerAdapter<PostMember,PostViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<PostMember,PostViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull PostViewHolder holder, int position, @NonNull final PostMember model) {

                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        final String currentuserId = user.getUid();
                        final  String postkey= getRef(position).getKey();

                        holder.SetPost(getActivity(),model.getName(), model.getUrl(), model.getPostUri(),model.getTime()
                                ,model.getUid(),model.getType(),model.getDescription());

                        String name = getItem(position).getName();
                        String url = getItem(position).getPostUri();
                        String time = getItem(position).getTime();
                        String userid = getItem(position).getUid();
                        String typo = getItem(position).getType();

                        //Toast.makeText(getActivity(), "Hello World", Toast.LENGTH_SHORT).show();
                        holder.likeChecker(postkey);

                        holder.commentBTN.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(),CommentActivity.class);
                                intent.putExtra("userid",userid);
                                intent.putExtra("typo",typo);


                                intent.putExtra("postkey",postkey);
                                startActivity(intent);
                            }
                        });

                        holder.moreBTN.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dekhaoDialog(name,url,time,userid,typo);
                            }
                        });

                        holder.likeBTN.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                likechecker= true;
                                likeref.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if(likechecker.equals(true))
                                        {
                                            if(snapshot.child(postkey).hasChild(currentuserId))
                                            {
                                                likeref.child(postkey).child(currentuserId).removeValue();
                                                likechecker=false;
                                            }
                                            else
                                            {
                                                likeref.child(postkey).child(currentuserId).setValue(true);
                                                likechecker= false;
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
                    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        //Toast.makeText(getActivity(), "Hello World", Toast.LENGTH_SHORT).show();
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_layout,parent,false);
                        return  new PostViewHolder(view);

                    }
                };

        firebaseRecyclerAdapter.startListening();
        recyclerViewPost.setAdapter(firebaseRecyclerAdapter);



    }
    void dekhaoDialog(String name, String url, String time, String userid,String typo)
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentuserId = user.getUid();
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.post_options,null);
        TextView down = view.findViewById(R.id.download_post);
        TextView share = view.findViewById(R.id.share_post);
        TextView delete = view.findViewById(R.id.delete_post);
        TextView copy = view.findViewById(R.id.url_post);

        AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).setView(view).create();
        alertDialog.show();
        //checking whether the user is the post who posted this
        if(userid.equals(currentuserId))
        {
            delete.setVisibility(View.VISIBLE);
        }
        else
        {
            delete.setVisibility(View.GONE);
        }
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    Query query = db1.orderByChild("time").equalTo(time);
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


                Query query1 = db2.orderByChild("time").equalTo(time);
                query1.addListenerForSingleValueEvent(new ValueEventListener() {
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

                Query query2 = db3.orderByChild("time").equalTo(time);
                query2.addListenerForSingleValueEvent(new ValueEventListener() {
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

                //for deleting image/video from storage
                StorageReference reference = FirebaseStorage.getInstance().getReferenceFromUrl(url);
                reference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(@NonNull Void unused) {
                        Toast.makeText(getActivity(), "Deleted", Toast.LENGTH_SHORT).show();
                    }
                });
                alertDialog.dismiss();
            }

        });

        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //first we ask for permission

                PermissionListener permissionListener = new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {

                        //we will download
                        if(typo.equals("iv"))
                        {
                            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE| DownloadManager.Request.NETWORK_WIFI);
                            request.setTitle("Download");
                            request.setDescription("Downloading Photo..");
                            request.allowScanningByMediaScanner();
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, name + System.currentTimeMillis()+".jpg");
                            DownloadManager manager=  (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
                            manager.enqueue(request);
                            Toast.makeText(getActivity(),"Downloading",Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();



                        }
                        else
                        {
                            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE| DownloadManager.Request.NETWORK_WIFI);
                            request.setTitle("Download");
                            request.setDescription("Downloading Video..");
                            request.allowScanningByMediaScanner();
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, name + System.currentTimeMillis()+".mp4");
                            DownloadManager manager=  (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
                            manager.enqueue(request);
                            Toast.makeText(getActivity(),"Downloading",Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();

                        }

                    }

                    @Override
                    public void onPermissionDenied(List<String> deniedPermissions) {
                        Toast.makeText(getActivity(),"Permisson Denied",Toast.LENGTH_SHORT).show();


                    }
                };

                TedPermission.with(getActivity()).setPermissionListener(permissionListener).setPermissions(Manifest.permission.INTERNET, Manifest.permission.READ_EXTERNAL_STORAGE).check();


            }
        });



        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String textofshare= name + "\n" + "\n" +url ;
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                sendIntent.putExtra(Intent.EXTRA_TEXT, textofshare);
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent,"Share via"));
                alertDialog.dismiss();

            }
        });



        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager cp = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("String",url);
                cp.setPrimaryClip(clipData);
                clipData.getDescription();
                Toast.makeText(getActivity(),"Copied",Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });


    }
}
