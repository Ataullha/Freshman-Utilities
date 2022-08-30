package com.example.freshmanutilites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import javax.annotation.Nullable;

public class EditImageActivity extends AppCompatActivity {

    Button btn_edit_save, btn_edit_select;
    ImageView imageView;
    Uri imageUri;
    Uri downloadUri;
    private static final int PICK_IMAGE = 1;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference;
    DocumentReference documentReference;
    StorageReference storageReference;
    UploadTask uploadTask;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ProgressBar edit_img_save_pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_image);

        imageView = findViewById(R.id.edit_iv_expand);
        btn_edit_save = findViewById(R.id.btn_edit_save_iv);
        btn_edit_select = findViewById(R.id.btn_select_image_iv);
        edit_img_save_pb = findViewById(R.id.edit_image_save_progress_bar);

        storageReference = FirebaseStorage.getInstance().getReference("Profile Images");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentUserId = user.getUid();
        documentReference = db.collection("User").document(currentUserId);

        btn_edit_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, PICK_IMAGE);
            }
        });


        btn_edit_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                                downloadUri = task.getResult();
                            } else {
                                Intent intent = new Intent(getApplicationContext(),Fragment1.class);
                                startActivity(intent);
                                return;
                            }

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Failed!", Toast.LENGTH_SHORT).show();
                    }
                });
                Toast.makeText(getApplicationContext(), "Uploading and Saving Image", Toast.LENGTH_LONG*2).show();
                edit_img_save_pb.setVisibility(View.VISIBLE);
                final DocumentReference sDoc = db.collection("User").document(currentUserId);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        db.runTransaction(new Transaction.Function<Void>() {
                            @Override
                            public Void apply(Transaction transaction) throws FirebaseFirestoreException {
                                transaction.update(sDoc, "Image URL", downloadUri.toString());
                                return null;
                            }

                        }).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
                                //Intent intent = new Intent(getApplicationContext(), Fragment2.class);
                                Intent intent = new Intent(getApplicationContext(), SplashScreenActivity.class);
                                startActivity(intent);
                            }
                        });
                    }
                }, 8000);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == PICK_IMAGE || resultCode == RESULT_OK || data != null || data.getData() != null) {
                imageUri = data.getData();
                Picasso.get().load(imageUri).fit().centerCrop().into(imageView);
                //imageIsSelected = true;
                // Toast.makeText(CreateProfileActivity.this, "IMAGE SHOULD LOAD .. .", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error " + e, Toast.LENGTH_SHORT).show();
        }
    }
    private String getFileExt(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType((contentResolver.getType(uri)));
    }
}