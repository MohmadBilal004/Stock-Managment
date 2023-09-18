package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.ktx.FirebaseAuthKtxRegistrar;
import com.google.firebase.ktx.Firebase;

public class SignUpActvity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText signUpMail, signUpPassword;
    private Button signupButton;
    private TextView loginRedirectText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_actvity);

        auth = FirebaseAuth.getInstance();
        signUpMail= findViewById(R.id.txtEmail);
        signUpPassword = findViewById(R.id.txtPassword);
        signupButton = findViewById(R.id.btnSignUp);
        loginRedirectText = findViewById(R.id.txtLogin);


        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = signUpMail.getText().toString().trim();
                String pass = signUpPassword.getText().toString().trim();

                if(user.isEmpty()){
                    signUpMail.setError("Email cannot be Empty");
                }
                if(pass.isEmpty()){
                    signUpMail.setError("Password cannot be Empty");
                }else{
                    auth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(SignUpActvity.this, "Sign Up successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUpActvity.this,MainActivity.class));
                            }else{
                                Toast.makeText(SignUpActvity.this, "Sign Up Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActvity.this, LoginActivity.class));
            }
        });
    }
}