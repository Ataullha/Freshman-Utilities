package com.example.freshmanutilites;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;

public class UpdateProfileActivity extends AppCompatActivity {

    Spinner spinnerblood;
    String[] bloodgrps;

    static String dept,bat,blo;

    Spinner spinnerdept;
    String depts[] = {"","ANP", "ARC", "BAN", "BMB", "BNG", "CEP", "CEE", "CHE", "CSE", "ECO", "EEE", "ENG", "FET", "FES", "GEB", "GEE", "IPE", "MAT", "MEE", "OCG", "PAD", "PGE", "PHY", "PSS", "SCW", "SOC", "STA"};

    Spinner spinnerbatch;
    static boolean selectedBatch = false;

    private static final int PICK_IMAGE = 1;
    EditText update_profile_name_et, update_profile_department_et, update_profile_batch_et, update_profile_mobile_no_et, update_profile_blood_group_et, update_profile_fb_profile_link_et;
    ImageView update_profile_image_iv;
    ProgressBar update_profile_progress_pb;
    //
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    //
    DatabaseReference reference;
    //
    DocumentReference documentReference;
    StorageReference storageReference;
    UploadTask uploadTask;
    Uri imageUri;
    Uri downloadUri;
    boolean imageIsSelected = false;
    //
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Button update_profile_update_profile_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentUserId = user.getUid();
        documentReference = db.collection("User").document(currentUserId);

        update_profile_batch_et = findViewById(R.id.update_profile_batch_et);
        update_profile_department_et = findViewById(R.id.update_profile_department_et);
        update_profile_name_et = findViewById(R.id.update_profile_name_et);
        update_profile_mobile_no_et = findViewById(R.id.update_profile_mobile_no_et);
        update_profile_fb_profile_link_et = findViewById(R.id.update_profile_facebook_link_et);
        update_profile_blood_group_et = findViewById(R.id.update_profile_blood_group_et);
        update_profile_image_iv = findViewById(R.id.update_profile_image);
        update_profile_progress_pb = findViewById(R.id.update_profile_progress_bar);
        update_profile_update_profile_btn = findViewById(R.id.update_profile_update_button);

        spinnerbatch = findViewById(R.id.update_profile_batchgrp_spinner);
        ArrayAdapter<String> arrayAdapterbatch = new ArrayAdapter<>(this,R.layout.spineercustomlayout,R.id.customSpinnerTextViewId,batches());
        spinnerbatch.setAdapter(arrayAdapterbatch);

        spinnerdept = findViewById(R.id.update_profile_deptgrp_spinner);
        ArrayAdapter<String> arrayAdapterdept = new ArrayAdapter<>(this,R.layout.spineercustomlayout,R.id.customSpinnerTextViewId,depts);
        spinnerdept.setAdapter(arrayAdapterdept);

        bloodgrps = getResources().getStringArray(R.array.bloodGrpU);
        spinnerblood = findViewById(R.id.update_profile_bldgrp_spinner);

        ArrayAdapter<String> arrayAdapterblood = new ArrayAdapter<>(this,R.layout.spineercustomlayout,R.id.customSpinnerTextViewId,bloodgrps);
        spinnerblood.setAdapter(arrayAdapterblood);

        update_profile_image_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(getApplicationContext(),EditImageActivity.class);
              startActivity(intent);
            }
        });

        update_profile_update_profile_btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick (View v){
        updateProfile();
    }
    });


}
/**
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try{
            if(requestCode == PICK_IMAGE || resultCode == RESULT_OK || data!=null || data.getData()!=null){
                imageUri = data.getData();
                Picasso.get().load(imageUri).fit().centerCrop().into(update_profile_image_iv);
                imageIsSelected = true;
                // Toast.makeText(CreateProfileActivity.this, "IMAGE SHOULD LOAD .. .", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e ){
            Toast.makeText(getApplicationContext(), "Error "+e, Toast.LENGTH_SHORT).show();
        }
    }
'
    privalte String getFileExt(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        imeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType((contentResolver.getType(uri)));
    }
**/
    private void updateProfile() {
        String name = update_profile_name_et.getText().toString();
        String prevdept = update_profile_department_et.getText().toString();
        String dept = spinnerdept.getSelectedItem().toString();
        if (dept.trim().isEmpty()){
            dept = prevdept;
        }
         String batchprev = update_profile_batch_et.getText().toString();
        String batch = spinnerbatch.getSelectedItem().toString();
        ///Log.e("BATCH PREV",batchprev);
        ///Log.e("BATCH",batch);
        if (batch.trim().isEmpty()){
            batch = batchprev;
        }
        String mobile_no = update_profile_mobile_no_et.getText().toString();
        String link = update_profile_fb_profile_link_et.getText().toString();
        String prevblood = update_profile_blood_group_et.getText().toString();
        String blood = spinnerblood.getSelectedItem().toString();
        if (blood.trim().isEmpty()){
            blood = prevblood;
        }
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentUserId = user.getUid();

      /*  ////////////////////////////////
if(imageIsSelected) {
    final StorageReference reference = storageReference.child(System.currentTimeMillis() + "." + getFileExt(imageUri));
    uploadTask = reference.putFile(imageUri);
    Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
        @Override
        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
            return reference.getDownloadUrl();
        }
    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
        @Override
        public void onComplete(@NonNull Task<Uri> task) {

            if (task.isSuccessful()) {
                if (!(imageUri.toString().isEmpty())) {
                            Uri downloadUri = task.getResult();
                }

            }
        }
    }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            Toast.makeText(getApplicationContext(), "Failed!", Toast.LENGTH_SHORT).show();
        }
    });
}
    ///////////////////////////////*/


        final DocumentReference sDoc = db.collection("User").document(currentUserId);
        String finalBatch = batch;
        String finalDept = dept;
        String finalBlood = blood;
        db.runTransaction(new Transaction.Function<Void>() {
            @Override
            public Void apply(Transaction transaction) throws FirebaseFirestoreException {
                transaction.update(sDoc, "name",name);
                transaction.update(sDoc, "Dept", finalDept);
                transaction.update(sDoc, "Batch", finalBatch);
                transaction.update(sDoc,"Mobile No",mobile_no);
                transaction.update(sDoc, "Blood Group", finalBlood);
                transaction.update(sDoc, "Facebook Profile Link",link);
                //transaction.update(sDoc, "Image URL", downloadUri.toString());



                return null;
            }
        }).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
//                Handler handler = new Handler();
//                handler.postDelayed(new Runnable() {
//                    @Override
//
//                    public void run() {
                Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
                        //Intent intent = new Intent(getApplicationContext(), Fragment2.class);
                       // Intent intent = new Intent(getApplicationContext(), LoadingScreenActivity.class);
                        Intent intent = new Intent(getApplicationContext(), SplashScreenActivity.class);
                        startActivity(intent);
//                    }
//                },2000);
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UpdateProfileActivity.this, "Failed To Update to Profile", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private ArrayList batches( ){

        ArrayList batch = new ArrayList();
        int yearInt =  Calendar.getInstance().get(Calendar.YEAR);
        //year = yearInt+"";
        batch.add("");
        int j = 1;
        for(int i = yearInt-5;i<=yearInt+5;i++){
            batch.add(i+"");
            j++;
        }

        return batch;
    }

    @Override
    protected void onStart() {
        super.onStart();
        //String currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentUserId = user.getUid();
        documentReference = db.collection("User").document(currentUserId);



        documentReference.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.getResult().exists()){
                            String name = task.getResult().getString("name");
                            String dept = task.getResult().getString("Dept");
                            String batch = task.getResult().getString("Batch");
                            String mobile_no = task.getResult().getString("Mobile No");
                            String link = task.getResult().getString("Facebook Profile Link");
                            String iurl = task.getResult().getString("Image URL");
                            String blood = task.getResult().getString("Blood Group");

                            Picasso.get().load(iurl).centerCrop().fit().into(update_profile_image_iv);

                            update_profile_name_et.setText(name);
                            update_profile_department_et.setText(dept);
                            update_profile_batch_et.setText(batch);
                            update_profile_mobile_no_et.setText(mobile_no);
                            update_profile_fb_profile_link_et.setText(link);
                            update_profile_blood_group_et.setText(blood);



                        } else {
                            Intent intent = new Intent(UpdateProfileActivity.this, CreateProfileActivity.class);
                            startActivity(intent);
                        }
                    }
                });


    }
}