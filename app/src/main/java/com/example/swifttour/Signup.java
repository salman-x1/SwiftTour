package com.example.swifttour;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class Signup extends AppCompatActivity {

    Button register;
    TextView gosign;
    FirebaseAuth firebaseAuth;
    DatabaseReference database;
    EditText names, addresss, emails, passwords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        firebaseAuth=FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance().getReference().child("swift-tour");
        register=findViewById(R.id.registers);
        gosign=findViewById(R.id.gologin);
        names=findViewById(R.id.names);
        addresss=findViewById(R.id.addresss);
        emails=findViewById(R.id.emails);
        passwords=findViewById(R.id.passwords);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (names.getText().toString().isEmpty() || addresss.getText().toString().isEmpty()|| emails.getText().toString().isEmpty()|| passwords.getText().toString().isEmpty())
                {
                    Toast.makeText(Signup.this, "Please Enter the required fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Reg();
                }

            }
        });

        gosign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Signup.this, Signin.class);
                startActivity(intent);
                finish();
            }
        });




    }
    private void Reg() {
        String name=names.getText().toString();
        String address=addresss.getText().toString();
        String email=emails.getText().toString().trim();
        String password=passwords.getText().toString().trim();
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(Signup.this, "Account Successfully Created", Toast.LENGTH_SHORT).show();
                String ui=firebaseAuth.getCurrentUser().getUid();
                PersonalInfo pf=new PersonalInfo(name, email, address);
                database.child(ui).setValue(pf);
                Intent intent=new Intent(Signup.this, HomeActivity.class);
                startActivity(intent);
                finish();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Signup.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}