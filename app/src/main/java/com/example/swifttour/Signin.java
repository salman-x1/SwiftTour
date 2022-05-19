package com.example.swifttour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Signin extends AppCompatActivity {
    EditText email,password;
    Button login;
    TextView signup;
    FirebaseAuth firebaseAuth;
//    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        email= findViewById(R.id.email1);
        password=findViewById(R.id.password1);
        login=findViewById(R.id.login);
        signup=findViewById(R.id.signup);

        firebaseAuth=FirebaseAuth.getInstance();
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(Signin.this, Signup.class);
                startActivity(home);
                finish();

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nemail=email.getText().toString().trim();
                String npass=password.getText().toString();
                if (nemail.isEmpty()||npass.isEmpty())
                {
                    Toast.makeText(Signin.this, "Email or password can't be empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Login1(nemail,npass);
                }
            }
        });
    }

    private void Login1(String nemail, String npass) {

        firebaseAuth.signInWithEmailAndPassword(nemail, npass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                    Toast.makeText(Signin.this, "Successfully Login", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Signin.this, HomeActivity.class);
                    startActivity(intent);
                    finish();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Signin.this, "Login Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        if (firebaseUser!=null)
        {
            Intent intent=new Intent(this,HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }
}