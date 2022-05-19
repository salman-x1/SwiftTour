package com.example.swifttour;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTriprecord extends AppCompatActivity {


    EditText imageurl, imagedescription;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_triprecord);

        imageurl=findViewById(R.id.imageurl);
        imagedescription=findViewById(R.id.imagedescription);
        save=findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 {
                    String imgurl = imageurl.getText().toString();
                    String imgdes = imagedescription.getText().toString();
                    if (imgurl.isEmpty() || imgdes.isEmpty())
                    {
                        Toast.makeText(AddTriprecord.this, "Image URL is Empty", Toast.LENGTH_LONG).show();
                    }
                    else
                    {

                    }

                }
            }
        });

    }
}