package com.example.freshmanutilites;

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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

    // User have to register here using firebase email and password
public class RegisterActivity extends AppCompatActivity {
    // email , pass and confirm pass Edit Text
    private EditText register_email , register_password , register_confirm_password;
    // register(sign up button ) and the (register to login button)
    private Button register_btn , register_to_login_btn ;
    // progress bar for the UI
    private ProgressBar register_progress_bar;
    // checkbox for show the hidden password
    private CheckBox register_checkbox;
    // firebase authentication variable - from the firebase assistant and it's documentation
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // getInstance from the firebase assistant
        mAuth = FirebaseAuth.getInstance();
        // find the id's of all variables and widget use at the register activity and store at the proper variable

        register_email = findViewById(R.id.register_email_et);
        register_password = findViewById(R.id.register_password_et);
        register_confirm_password = findViewById(R.id.register_confirm_password_et);
        register_btn = findViewById(R.id.register_btn);
        register_to_login_btn = findViewById(R.id.register_to_login_button);
        register_progress_bar = findViewById(R.id.register_progress_bar);
        register_checkbox = findViewById(R.id.register_checkbox);

        // when clicked at the checkbox it will show the password

        register_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    // will show the password using setTransformationMethod
                    register_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    register_confirm_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    /// this two lines below will prevent the user hassle when clicked at the checkbox It will set at the end of the password edit text string length where you end your line and clicked at the show pass checkbox
                    register_password.setSelection(register_password.getText().length());
                    register_confirm_password.setSelection(register_confirm_password.getText().length());
                } else {
                    // the opposite works of the if block
                    register_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    register_confirm_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    register_password.setSelection(register_password.getText().length());
                    register_confirm_password.setSelection(register_confirm_password.getText().length());
                }
            }
        });

        // what will happen when we will click at the register button

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // email ,password and confirm password
                String email = register_email.getText().toString().trim();
                String password = register_password.getText().toString().trim();
                String confirm_password = register_confirm_password.getText().toString().trim();
                // regex - collected from the web to  match the email pattern
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";




                if (!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password) || !TextUtils.isEmpty(confirm_password)){
                    if(password.equals(confirm_password) && (email.matches(emailPattern) || email.contains("@student.sust.edu")) && password.length()>=8){
                        // if everything ok than the progress bar will available only for UI purposes
                        register_progress_bar.setVisibility(View.VISIBLE);
                        // it will create account using email and password
                        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // if task.isSuccessful() then it will work
                                if (task.isSuccessful()){
                                    // a simple confirming Toast
                                    Toast.makeText(RegisterActivity.this, "Account Created Successfully!", Toast.LENGTH_SHORT).show();
                                    // progress bar will vanish
                                    register_progress_bar.setVisibility(View.GONE);

                                    FirebaseUser user = mAuth.getCurrentUser();
                                    user.sendEmailVerification()
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(@NonNull Void unused) {
                                                    SendtoEmailVer();
                                                    //Toast.makeText(getApplicationContext(), "Email Verification Send", Toast.LENGTH_SHORT).show();
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(getApplicationContext(),"Failed To Send Verification Email Check Your Network and Email", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                    /// now we have to comment the below line because we are going to use email verification for our project
                                    ///SendtoMain(); // for at this stage of the project it will change
                                } else {
                                    // exception will be shown if occurs ..
                                    String error = task.getException().getMessage();
                                    // a simple  toast
                                    Toast.makeText(RegisterActivity.this, "Error : "+error, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                    if(!(password.equals(confirm_password))){
                        // Toast.makeText(RegisterActivity.this, "Password and Confirm Password Not Matched!", Toast.LENGTH_SHORT).show();
                        register_confirm_password.setError("Password and Confirm Password Not Matched!");
                        register_confirm_password.setSelection(register_confirm_password.getText().length());
                    }
                    if(password.length()<8) {
                        //Toast.makeText(RegisterActivity.this, "Password Must Be More Than 8 Character!", Toast.LENGTH_SHORT).show();
                        register_password.setError("Password Must Be More Than 8 Character!");
                        register_password.setSelection(register_password.getText().length());
                    }
                    if(!((email.matches(emailPattern) || email.contains("@student.sust.edu")))){
                        //Toast.makeText(RegisterActivity.this, "Enter A Valid Email Address!", Toast.LENGTH_SHORT).show();
                        register_email.setError("Enter A Valid Email Address!");
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Please Fill All The Values Correctly!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // it will send user from register panel to the login panel
        register_to_login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(RegisterActivity.this, "Button Clicked!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    // if task is successful we will send the user to the email verification activity
        private void SendtoEmailVer(){
        Intent intent = new Intent(RegisterActivity.this,EmailVerificationSentActivity.class);
        startActivity(intent);
        finish();
        }

    // for this stage of the application will change it later
    private void SendtoMain() {

        Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    // OnStart() - android activity lifecycle thing

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user!=null){
            SendtoMain();
            finish();
        }

    }
}