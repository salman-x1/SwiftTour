package com.example.swifttour;

import static android.app.Activity.RESULT_OK;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Fragment_Profile extends Fragment {

    Button logout, post;
    EditText caption;
    ImageView upimg;
    Uri uri2;
    StorageReference storageReference;
    DatabaseReference databaseReference;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.profile, container, false);
        logout=view.findViewById(R.id.logout);
        post=view.findViewById(R.id.post);
        caption=view.findViewById(R.id.caption);
        upimg=view.findViewById(R.id.upimg);

        storageReference= FirebaseStorage.getInstance().getReference("swift-tour");
        databaseReference= FirebaseDatabase.getInstance().getReference().child("swift-tour");

        upimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cap= caption.getText().toString();
                if (cap.isEmpty()|| uri2 == null)
                {
                    Toast.makeText(getActivity().getApplicationContext(), "Enter Location Name with image", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Upload();
                }
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();

                Intent intent=new Intent(getActivity().getApplicationContext(), Signin.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return view;
    }

    private void Upload() {
        final ProgressDialog dialog=new ProgressDialog(getView().getContext());
        dialog.setTitle("Uploading...");
        dialog.show();

//        progressBar.setVisibility(View.VISIBLE);
        StorageReference mountainImagesRef = storageReference.child(HomeActivity.uid).child(System.currentTimeMillis()+".png");
        mountainImagesRef.putFile(uri2).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                mountainImagesRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        UploadTrip uploadTrip=new UploadTrip(uri.toString(), caption.getText().toString());

                        databaseReference.child(HomeActivity.uid).child("Trips").push().setValue(uploadTrip);

                        dialog.dismiss();
                        Snackbar.make(getView(),"Successfully Posted",Snackbar.LENGTH_LONG).show();
                        caption.setText(null);
                        dialog.dismiss();
                        uri2=null;
                        upimg.setImageDrawable(getActivity().getApplicationContext().getResources().getDrawable(R.drawable.ic_baseline_image_24));
                    }
                });

            }
        }).addOnFailureListener(fail->
        {
            dialog.dismiss();
            Snackbar.make(getView(),"Uploading Fail..."+fail.getMessage(),Snackbar.LENGTH_LONG).show();
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>()
        {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double aDouble = (100.00 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                int percentageinint = (int) aDouble;

                dialog.setMessage("Uploading " + percentageinint + "%");
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case 1:
                if (resultCode==RESULT_OK)
                {
                    uri2=data.getData();
                    upimg.setImageURI(uri2);
                }
                break;


        }
    }
}
