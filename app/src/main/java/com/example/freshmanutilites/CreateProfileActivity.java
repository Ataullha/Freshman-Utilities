package com.example.freshmanutilites;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class CreateProfileActivity extends AppCompatActivity {

    private Button create_profile_save_profile_btn;
    EditText create_profile_name_et , create_profile_department_et , create_profile_batch_et , create_profile_mobile_no_et , create_profile_blood_group_et , create_profile_fb_profile_link_et;
    ImageView create_profile_image_iv;
    ProgressBar create_profile_progress_pb;
    Uri imageUri;
    UploadTask uploadTask;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    DocumentReference documentReference;
    StorageReference storageReference;
    private static final int PICK_IMAGE = 1;
    AllUserInfo allUserInfo;
    String currentUserId;

    //static String year;

    Spinner spinnerblood;
    String[] bloodgrps;

    Spinner spinnerdept;
    String depts[] = {"department","anp", "arc", "ban", "bmb", "bng", "cep", "cee", "che", "cse", "eco", "eee", "eng", "fet", "fes", "geb", "gee", "ipe", "mat", "mee", "ocg", "pad", "pge", "phy", "pss", "scw", "soc", "sta"};

    Spinner spinnerbatch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        allUserInfo = new AllUserInfo();
        create_profile_image_iv = findViewById(R.id.create_profile_image);
        create_profile_name_et = findViewById(R.id.crete_profile_name_et);
       // create_profile_department_et = findViewById(R.id.crete_profile_department_et);
        //
        //depts = getResources().getStringArray(R.array.bloodGrp);

        spinnerbatch = findViewById(R.id.create_profile_batchgrp_spinner);
        ArrayAdapter<String> arrayAdapterbatch = new ArrayAdapter<>(this,R.layout.spineercustomlayout,R.id.customSpinnerTextViewId,batches());
        spinnerbatch.setAdapter(arrayAdapterbatch);

        spinnerdept = findViewById(R.id.create_profile_deptgrp_spinner);

        ArrayAdapter<String> arrayAdapterdept = new ArrayAdapter<>(this,R.layout.spineercustomlayout,R.id.customSpinnerTextViewId,depts);
        spinnerdept.setAdapter(arrayAdapterdept);

        // calendar for just a test purpose
        // start

               // createDialogWithoutDateField().show();

        // end


        create_profile_mobile_no_et = findViewById(R.id.crete_profile_mobile_no_et);
        //create_profile_blood_group_et = findViewById(R.id.crete_profile_blood_group_et); // we are going to change it to a spinner
        //
        bloodgrps = getResources().getStringArray(R.array.bloodGrp);
        spinnerblood = findViewById(R.id.create_profile_bldgrp_spinner);

        ArrayAdapter<String> arrayAdapterblood = new ArrayAdapter<>(this,R.layout.spineercustomlayout,R.id.customSpinnerTextViewId,bloodgrps);
        spinnerblood.setAdapter(arrayAdapterblood);
        //
        create_profile_fb_profile_link_et = findViewById(R.id.crete_profile_facebook_link_et);
        create_profile_save_profile_btn = findViewById(R.id.create_profile_save_button);
        create_profile_progress_pb = findViewById(R.id.create_profile_progress_bar);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        currentUserId = currentUser.getUid();
        // offline fire store for current user
        documentReference = firebaseFirestore.collection("User").document(currentUserId);
        // Store Photo in firebase Profile Images Storage
        storageReference = FirebaseStorage.getInstance().getReference("Profile Images");
        // keep data in firebase realtime database with the name - "All User Info"
        databaseReference = firebaseDatabase.getReference("All Users Info");

        create_profile_save_profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadData();
            }
        });

        create_profile_image_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,PICK_IMAGE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try{
            if(requestCode == PICK_IMAGE || resultCode == RESULT_OK || data!=null || data.getData()!=null){
                imageUri = data.getData();
                Picasso.get().load(imageUri).fit().centerCrop().into(create_profile_image_iv);
               // Toast.makeText(CreateProfileActivity.this, "IMAGE SHOULD LOAD .. .", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e ){
            Toast.makeText(CreateProfileActivity.this, "Error "+e, Toast.LENGTH_SHORT).show();
        }
    }
    private DatePickerDialog createDialogWithoutDateField() {
        DatePickerDialog dpd = new DatePickerDialog(this, null, 2014, 1, 24);
        try {
            java.lang.reflect.Field[] datePickerDialogFields = dpd.getClass().getDeclaredFields();
            for (java.lang.reflect.Field datePickerDialogField : datePickerDialogFields) {
                if (datePickerDialogField.getName().equals("mDatePicker")) {
                    datePickerDialogField.setAccessible(true);
                    DatePicker datePicker = (DatePicker) datePickerDialogField.get(dpd);
                    java.lang.reflect.Field[] datePickerFields = datePickerDialogField.getType().getDeclaredFields();
                    for (java.lang.reflect.Field datePickerField : datePickerFields) {
                        Log.i("test", datePickerField.getName());
                        if ("mDaySpinner".equals(datePickerField.getName())) {
                            datePickerField.setAccessible(true);
                            Object dayPicker = datePickerField.get(datePicker);
                            ((View) dayPicker).setVisibility(View.GONE);
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
        }
        return dpd;
    }

    private ArrayList batches(){

        ArrayList batch = new ArrayList();
        int yearInt =  Calendar.getInstance().get(Calendar.YEAR);
        //year = yearInt+"";
        batch.add("Batch");
        int j = 1;
        for(int i = yearInt-5;i<=yearInt+5;i++){
            batch.add(i+"");
            j++;
        }

        return batch;
    }


    private String getFileExt(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType((contentResolver.getType(uri)));
    }

    private void uploadData() {
            String name = create_profile_name_et.getText().toString();
            //String dept = create_profile_department_et.getText().toString();
            String dept = spinnerdept.getSelectedItem().toString();
            //String batch = create_profile_batch_et.getText().toString();
            String batch =spinnerbatch.getSelectedItem().toString();
            String mobNo = create_profile_mobile_no_et.getText().toString();
            //String bloodGrp = create_profile_blood_group_et.getText().toString(); // convert to spinner so commented
            String bloodGrp = spinnerblood.getSelectedItem().toString();
            //Toast.makeText(getApplicationContext(), bloodGrp, Toast.LENGTH_SHORT).show();
            String linkwhat = create_profile_fb_profile_link_et.getText().toString();
            String link;
            // modification for ui purposes
            // ata

            if (linkwhat.contains("https://")){
                link = linkwhat;
            } else if (linkwhat.contains("www")){
                link = "https://"+linkwhat;
            } else if (linkwhat.contains(".com")){
                link = "https://www."+linkwhat;
            } else if(linkwhat.contains("fb.com/")||linkwhat.contains("facebook.com/")) {
                link = linkwhat;
            } else {
                link = "https://www.facebook.com/"+linkwhat;
            }

            //

            if(!(name.isEmpty()) && !(dept.isEmpty()) && !(batch.isEmpty()) && !(mobNo.isEmpty()) && !(bloodGrp.isEmpty()) && !(link.isEmpty()) ){
                create_profile_progress_pb.setVisibility(View.VISIBLE);
               // Toast.makeText(CreateProfileActivity.this, "Here", Toast.LENGTH_SHORT).show();
                final StorageReference reference = storageReference.child(System.currentTimeMillis()+"."+getFileExt(imageUri));
                uploadTask = reference.putFile(imageUri);
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

                            Map<String,String> profile = new HashMap<>();

                            profile.put("name",name);
                            profile.put("Dept",dept);
                            profile.put("Batch",batch);
                            profile.put("Mobile No",mobNo);
                            profile.put("Blood Group",bloodGrp);
                            profile.put("User Id",currentUserId);
                            profile.put("Facebook Profile Link",link);
                            profile.put("Image URL",downloadUri.toString());

                            allUserInfo.setName(name);
                            allUserInfo.setDept(dept);
                            allUserInfo.setBatch(batch);
                            allUserInfo.setMobNo(mobNo);
                            allUserInfo.setBloodGrp(bloodGrp);
                            allUserInfo.setUserId(currentUserId);
                            allUserInfo.setUrl(downloadUri.toString());

                            databaseReference.child(currentUserId).setValue(allUserInfo);

                            documentReference.set(profile)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            create_profile_progress_pb.setVisibility(View.INVISIBLE);
                                            Toast.makeText(CreateProfileActivity.this, "Profile Created", Toast.LENGTH_SHORT).show();

                                            Intent intent = new Intent(getApplicationContext(), SplashScreenActivity.class);
                                            startActivity(intent);

                                        }
                                    });
                        }
                    }
                });
            } else {
                Toast.makeText(CreateProfileActivity.this, "Please Fill All The Values Correctly!", Toast.LENGTH_SHORT).show();
            }
    }
}