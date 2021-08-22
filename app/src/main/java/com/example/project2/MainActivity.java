package com.example.project2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final int CAMERA_ACTION_CODE=1888;
    ImageView imageView;
    Button takePhoto;
    ActivityResultLauncher <Intent>activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.iv_display);
        takePhoto = findViewById(R.id.btn_cature);


        activityResultLauncher= registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                     if(result.getResultCode()==RESULT_OK && result.getData()!=null) {
                         Bitmap bitmap = (Bitmap) result.getData().getExtras().get("data");
                         imageView.setImageBitmap(bitmap);
                     }
            }
        });


        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null) {
                //    startActivityForResult(intent,CAMERA_ACTION_CODE);
                    activityResultLauncher.launch(intent);
                } else {
                    Toast.makeText(MainActivity.this, "There is no app that support this action",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    
}

