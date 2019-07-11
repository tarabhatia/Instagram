package com.example.instagram;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.instagram.model.Post;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.File;

public class PostActivity extends AppCompatActivity {

    private EditText caption;
    private ImageView image;
    private Button submitButton;
    private File photoFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        image = findViewById(R.id.ivPreview);
        caption = findViewById(R.id.etCaption);
        submitButton = findViewById(R.id.btnSubmit);

       final String path = getIntent().getStringExtra("picture");
        // set image in post activity
        // by this point we have the camera photo on disk
        Bitmap takenImage = BitmapFactory.decodeFile(path);
        photoFile = new File(path);
        // RESIZE BITMAP, see section below
        // Load the taken image into a preview
        image.setImageBitmap(takenImage);
        // set caption in post activity
        final EditText caption = findViewById(R.id.etCaption);
        // caption.setText();




        //image.setImageBitmap(HomeActivity.takenImage);
        //caption.setText();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String descrip = caption.getText().toString();
                ParseUser user = ParseUser.getCurrentUser();
                savePost(descrip, user, photoFile);

            }

        });
    }

    private void savePost(String descrip, ParseUser user, File photoFile) {
        Post post = new Post();
        post.setDescription(descrip);
        post.setUser(user);
        post.setImage(new ParseFile(photoFile));
        post.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e!=null) {
                    Log.d("PostActivity", "Error");
                    e.printStackTrace();
                    return;
                }
                Log.d("PostActivity", "Success");
                finish();
            }
        });
    }
}
