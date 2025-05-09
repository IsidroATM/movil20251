package com.example.helloandroid;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.helloandroid.utils.PermissionUtils;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class FormContactActivity extends AppCompatActivity {

    Button avatarBtn;
    Button submitBtn;
    ImageView avatarImage;

    Uri avatarUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_form_contact);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == RESULT_OK) {
            // mostrar foto en imageview
            processPhotoFromCamera();
        }
    }

    private void openCamera() {
        if (PermissionUtils.checkCameraPermission(this)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            File file = createImageFile();
            avatarUri = FileProvider.getUriForFile(this, "com.example.helloandroid", file);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, avatarUri);

            startActivityForResult(intent, 1000);
        } else {
            PermissionUtils.requestCameraPermission(this);
        }

    }

    private File createImageFile() {
        String fileName = "avatar_" + System.currentTimeMillis() + ".png";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        return new File(storageDir, fileName);
    }

    private void processPhotoFromCamera() {
        avatarImage.setImageURI(avatarUri);
    }

    private void saveContact() {
//        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
//        StorageReference avatarRef = storageRef.child("avatars");
//
//        avatarRef.putFile(avatarUri)
//            .addOnSuccessListener(taskSnapshot -> {
//                avatarRef.getDownloadUrl().addOnSuccessListener(uri -> {
//                    String url = uri.toString();
//                    // obtener la url de la foto desde firebase
//                    // guradr contacto en database
//                });
//            });

        Toast.makeText(this, "Se guardo correctamente", Toast.LENGTH_LONG).show();
    }

    private void setupView() {
        avatarImage = findViewById(R.id.avatarImage);
        avatarBtn = findViewById(R.id.avatarBtn);
        submitBtn = findViewById(R.id.submitBtn);

        avatarBtn.setOnClickListener(v -> {
            openCamera();
        });

        submitBtn.setOnClickListener(v -> {
            saveContact();
        });
    }

}