package com.example.freshmanutilites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EmailVerificationSentActivity extends AppCompatActivity {

    TextView resendEmailTV,msgTV,remsgTV;
    Button emailVarDoneBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verification_sent);

        resendEmailTV = findViewById(R.id.resend_email_TV);
        msgTV = findViewById(R.id.textViewMSG);
        remsgTV = findViewById(R.id.textViewRESENDMSG);
        emailVarDoneBTN = findViewById(R.id.done_email_Ver);

        emailVarDoneBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                user.reload().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            if (user.isEmailVerified()){
                                Toast.makeText(getApplicationContext(), "Email Verified ", Toast.LENGTH_SHORT).show();
                                SendtoMain();
                                finish();
                            }
                        }
                    }
                });

            }
        });


        resendEmailTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                if (!(user.isEmailVerified())){
                    user.sendEmailVerification()
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(@NonNull Void unused) {
                                    remsgTV.setVisibility(View.VISIBLE);
                                    msgTV.setVisibility(View.INVISIBLE);
                                    //Toast.makeText(getApplicationContext(), "Email Verification Send", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),"Failed To Send Verification Email Check Your Network and Email", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });

    }

    private void SendtoMain() {

        Intent intent = new Intent(getApplicationContext(),SplashScreenActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user.reload().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    if (user.isEmailVerified()) {
                        Toast.makeText(getApplicationContext(), "Email Verified ", Toast.LENGTH_SHORT).show();
                        SendtoMain();
                        finish();
                    }
                }
            }
        });
    }
}