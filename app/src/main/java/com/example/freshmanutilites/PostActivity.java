package com.example.freshmanutilites;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class PostActivity extends AppCompatActivity {

    ImageView imageView;
    ProgressBar progressBar;
    Button Upload_BTN, Choose_BTN;
    private Uri selectedUri;
    private static final int PICK_FILE = 1;
    UploadTask uploadTask;
    EditText description_ET;
    VideoView videoView;
    String url,name;
    StorageReference storageReference;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference db1,db2,db3;

    MediaController mediaController;
    String type;
    PostMember postMember;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        postMember = new PostMember();
        mediaController = new MediaController(this);

        progressBar = findViewById(R.id.post_file_pb);
        imageView = findViewById(R.id.post_image);
        videoView = findViewById(R.id.post_video);
        Choose_BTN = findViewById(R.id.choose_file_btn);
        Upload_BTN = findViewById(R.id.upload_file_btn);
        description_ET = findViewById(R.id.post_caption);


        storageReference = FirebaseStorage.getInstance().getReference("User Posts");  //``````````````````````


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentuid = user.getUid().toString();

        db1 = database.getReference("All Images").child(currentuid);
        db2 = database.getReference("All Videos").child(currentuid);
        db3 = database.getReference("All Posts");



        Upload_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadPost();
            }
        });

        Choose_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              ChooseFile();
            }
        });



    }

    private void ChooseFile() {
//        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI); // for video we write that line
//        intent.setType("image/* video/*");
//       // intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(intent,PICK_FILE);


            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_MIME_TYPES, new String[] {"image/*", "video/*"});
            startActivityForResult(intent, PICK_FILE);

    }

    private String getFileExt(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType((contentResolver.getType(uri)));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_FILE || resultCode == RESULT_OK || data!=null || data.getData()!=null){

            selectedUri = data.getData(); // line of crash ? loc ;

            if(selectedUri.toString().contains("video")){
                videoView.setMediaController(mediaController);
                videoView.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.INVISIBLE);
                videoView.setVideoURI(selectedUri);
                videoView.start();
                type="vv";
            } else if(selectedUri.toString().contains("image")){
                Picasso.get().load(selectedUri).into(imageView);
                imageView.setVisibility(View.VISIBLE);
                videoView.setVisibility(View.INVISIBLE);
                type="iv";
            } else {
                Toast.makeText(getApplicationContext(), "No Valid File Selected!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void UploadPost() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentuid = user.getUid().toString();

        String description = description_ET.getText().toString();



        SimpleDateFormat curDate = new SimpleDateFormat("dd-MMMM-yy HH:mm:ss");
        String time = curDate.format(new Date());

        if (TextUtils.isEmpty(description) || selectedUri!= null){

            // copied from create profile ATA

            progressBar.setVisibility(View.VISIBLE);
            final StorageReference reference = storageReference.child(System.currentTimeMillis()+"."+getFileExt(selectedUri));
            uploadTask = reference.putFile(selectedUri);
            Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    // Toast.makeText(CreateProfileActivity.this, "Here I am", Toast.LENGTH_SHORT).show();
                    return reference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {

                    if(task.isSuccessful()){
                        Uri downloadUri = task.getResult();
                        // next day we will start from // UPDATE WILL comment it out// /// retrive data
                            if(type.equals("iv")){
                            postMember.setDescription(description);
                            postMember.setName(name);
                            postMember.setPostUri(downloadUri.toString()); // uri to string
                            postMember.setTime(time);
                            postMember.setUid(currentuid);
                            postMember.setUrl(url);
                            postMember.setType("iv");

                            // for image
                            String id = db1.push().getKey();
                            db1.child(id).setValue(postMember);

                            // for both
                            String id1 = db3.push().getKey();
                            db3.child(id1).setValue(postMember);

                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(getApplicationContext(), "Post Uploaded", Toast.LENGTH_SHORT).show();

                        } else if (type.equals("vv")){

                            postMember.setDescription(description);
                            postMember.setName(name);
                            postMember.setPostUri(downloadUri.toString()); // uri to string
                            postMember.setTime(time);
                            postMember.setUid(currentuid);
                            postMember.setUrl(url);
                            postMember.setType("vv");

                            // for video
                            String id3 = db2.push().getKey();
                            db2.child(id3).setValue(postMember);

                            // for both
                            String id4 = db3.push().getKey();
                            db3.child(id4).setValue(postMember);


                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(getApplicationContext(), "Post Uploaded", Toast.LENGTH_SHORT).show();


                        } else{
                            Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_SHORT).show();
                        }

                        //*/


                    }
                }
            }).addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    finish();
                }
            });
            // ends here
        }
    }


    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String currentuid = user.getUid().toString();

    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    DocumentReference ref = firebaseFirestore.collection("User").document(currentuid);


    @Override
    protected void onStart() {
        super.onStart();


        // from there to here -- questions SOUMIK
        ref.get().addOnCompleteListener(task ->
        {
            if(task.getResult().exists())
            {
                name = task.getResult().getString("name");
                url = task.getResult().getString("Image URL");

            }
            else
            {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }

        });


    }



}
