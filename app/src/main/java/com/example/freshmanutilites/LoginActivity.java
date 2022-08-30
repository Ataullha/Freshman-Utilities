package com.example.freshmanutilites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
    //this is the activity from which the work of the application will start .
public class LoginActivity extends AppCompatActivity {
    // email and password edit text
    private EditText login_email , login_password ;
    // login and login to register button if user not exist
    private Button login_btn , login_to_register_btn ;
    // only for UI purposes
    private ProgressBar login_progress_bar;
    // for show password option
    private CheckBox login_checkbox;
    // if user forgot his password then this will work ..
    private TextView login_forgot_password;
    // firebase authentication variable
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // get the id's to the variable declared above
        mAuth = FirebaseAuth.getInstance();
        login_email = findViewById(R.id.login_email_et);
        login_password = findViewById(R.id.login_password_et);
        login_btn = findViewById(R.id.login_btn);
        login_to_register_btn = findViewById(R.id.login_to_register_button);
        login_progress_bar = findViewById(R.id.login_progress_bar);
        login_checkbox = findViewById(R.id.login_checkbox);
        login_forgot_password = findViewById(R.id.login_forgot_password_tv);

        // will use for showing the password if checked
        login_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    login_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    login_password.setSelection(login_password.getText().length());
                } else {
                    login_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    login_password.setSelection(login_password.getText().length());
                }
            }
        });
        // login activity to register activity
        login_to_register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });


        // if email and password was previously registered at the firebase then user can login
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = login_email.getText().toString().trim();
                String password = login_password.getText().toString().trim();

                if (!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)){
                    // progress bar for UI process
                    login_progress_bar.setVisibility(View.VISIBLE);
                    // firebase sign in with email and password
                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                login_progress_bar.setVisibility(View.GONE);
                                SendtoMain();
                            } else {
                                String error = task.getException().getMessage();
                                Toast.makeText(LoginActivity.this, "Error : "+error, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                } else {
                    // A simple toast
                    Toast.makeText(LoginActivity.this, "Please Fill All The Values Correctly!", Toast.LENGTH_SHORT).show();
                }
            }
        });


            // if user forgot password

        login_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = login_email.getText().toString().trim();
                // if a valid email is entered
                if(login_email.getText().toString().isEmpty()){
                    login_email.setError("Enter A Valid Email Address First!");
                } else {
                    // Alert dialogue - this will build an  alert wih Yes and No if yes is clicked then set positive button will work and else then other buttton will work
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setTitle("Reset Password")
                            .setMessage("Are you sure to reset password?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() { // positive button listener ..
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mAuth.sendPasswordResetEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() { // if able to sent the email to user email
                                        @Override
                                        public void onSuccess(Void unused) {    // onSuccess method
                                            // a simple toast message
                                            Toast.makeText(LoginActivity.this, "Reset Password Email Sent!", Toast.LENGTH_SHORT).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() { // failure listener if failed to sent the email  ..
                                        @Override
                                        public void onFailure(@NonNull Exception e) { //OnFailure method
                                            // a simple toast message
                                            Toast.makeText(LoginActivity.this, "Error : " + e, Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() { // negative button listener ..
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                    builder.create(); // create that Alert
                    builder.show(); // show that alert
                }
            }
        });

    }

    // for at this stage of the project
    private void SendtoMain() {
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    // activity lifecycle related thing
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user!=null){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            // finish();
            // this method will finish the activity if completed ...
            finish();
        }
    }

}