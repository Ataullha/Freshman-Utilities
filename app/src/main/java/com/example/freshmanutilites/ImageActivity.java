package com.example.freshmanutilites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class ImageActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    Button btnEdit, btnDelete;
    DocumentReference reference;
    String url;
    public Boolean IMAGE_DELETED = false;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        btnDelete = findViewById(R.id.btn_del_iv);
        btnEdit = findViewById(R.id.btn_edit_iv);
        imageView = findViewById(R.id.iv_expand);
        textView = findViewById(R.id.tv_name_image);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentid = user.getUid();

        reference = db.collection("User").document(currentid);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditImageActivity.class);
                startActivity(intent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.getResult().exists()) {
                            String name = new StringBuilder().append(task.getResult().getString("name")).append(" Profile Picture").toString();
                            url = task.getResult().getString("Image URL");
                            if (!(url.isEmpty())) {


                                StorageReference ref = FirebaseStorage.getInstance().getReferenceFromUrl(url);
                                ref.delete()
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                String currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();

                                                final DocumentReference sDoc = db.collection("User").document(currentUserId);
                                                db.runTransaction(new Transaction.Function<Void>() {
                                                @Override
                                                public Void apply(Transaction transaction) throws FirebaseFirestoreException {

                                                transaction.update(sDoc, "Image URL","https://firebasestorage.googleapis.com/v0/b/freshmanutilites.appspot.com/o/Profile%20Images%2F1639992042826.jpg?alt=media&token=5cd40eec-0d1d-4028-911b-3ce7042c257b");
                                                return null;
                                                }
                                                });
                                                Toast.makeText(getApplicationContext(), "Profile Picture Deleted Successfully!An Default Image Is Set ", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(getApplicationContext(), SplashScreenActivity.class);
                                                startActivity(intent);
                                            }
                                        });
                            }
                        } else {
                            Toast.makeText(ImageActivity.this, "No Profile Picture!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.getResult().exists()) {
                    String name = new StringBuilder().append(task.getResult().getString("name")).append(" Profile Picture").toString();
                    url = task.getResult().getString("Image URL");
                    if (!(url.isEmpty())) {
                        Picasso.get().load(url).into(imageView);
                    } else {
                        //   Picasso.get().load(String.valueOf(getDrawable(R.drawable.ic_baseline_person_24))).centerCrop().fit().into(imageView);
                    }
                    textView.setText(name);
                } else {
                    Toast.makeText(ImageActivity.this, "No Profile Picture!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
