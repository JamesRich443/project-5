package com.Codepath.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.service.autofill.SaveCallback;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.text.ParseException;

public class MainActivity extends AppCompatActivity {
    public static final String TAG="MainActivity";
    public static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE=42;
    private EditText etDescription;
    private Button btnCaptureImage;
    private ImageView ivPostImage;
    private Button btnSubmit;
    private File photoFile;
    private String photoFileName = "photo.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etDescription= findViewById(R.id.etDescription);
        btnCaptureImage=findViewById(R.id. btnCaptureImage);
        ivPostImage=findViewById(R.id. ivPostImage);
        btnSubmit= findViewById(R.id.btnSubmit );

        btnCaptureImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchCamera(){
            }
        }

            private void launchCamera() {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // Create a File reference for future access
                photoFile = getPhotoFileUri(photoFileName);

                // wrap File object into a content provider
                // required for API >= 24
                // See https://guides.codepath.com/android/Sharing-Content-with-Intents#sharing-files-with-api-24-or-higher
                Uri fileProvider = FileProvider.getUriForFile(MainActivity.this, "com.codepath.fileprovider", photoFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider);

                // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
                // So as long as the result is not null, it's safe to use the intent.
                if (intent.resolveActivity(getPackageManager()) != null) {
                    // Start the image capture intent to take photo
                    startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
                }
            }
            @Override
            protected void onActivityResult(requestCode, int resultCode, @Nullable Intent data);{
                @Override
                public void onActivityResult(int requestCode, int resultCode, Intent data) {
                    if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
                        if (resultCode == RESULT_OK) {
                            // by this point we have the camera photo on disk
                            Bitmap takenImage = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
                            // RESIZE BITMAP, see section below
                            // Load the taken image into a preview
                            ImageView ivPreview = (ImageView) findViewById(R.id.ivPostImage);
                            ivPostImage.setImageBitmap(takenImage);
                        } else { // Result was a failure
                            Toast.makeText(this, "Picture wasn't taken!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                super.onActivityResult(requestCode,resultCode,data);
            }
            public File getPhotoFileUri(String fileName) {
                // Get safe storage directory for photos
                // Use `getExternalFilesDir` on Context to access package-specific directories.
                // This way, we don't need to request external read/write runtime permissions.
                File mediaStorageDir = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), APP_TAG);

                // Create the storage directory if it does not exist
                if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()){
                    Log.d(TAG, "failed to create directory");
                }

                // Return the file target for the photo based on filename
               return new  File = new File(mediaStorageDir.getPath() + File.separator + fileName);

                return file;
            }
            }
            });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String description=etDescription.getText().toString();
                if(description.isEmpty()){
                    Toast.makeText(MainActivity.this,"Description",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(photoFile==null||ivPostImage.getDrawable()==null){
                    Toast.makeText(MainActivity.this,"no image",Toast.LENGTH_SHORT).show()
                }
            ParseUser currentUser=ParseUser.getCurrentUser();
                savePost(description,currentUser);


        });
    private void savePost(String description,ParseUser currentUser  ){
        Post post=new Post();
        post.setDescription(description);
        post.setImage(new ParseFile(photoFile));
        post.setUser(currentUser);
        post.saveInBackround(new SaveCallback()){
            @Override
                    public void done(ParseException e){
                if(e!=null){
                    Log.e(TAG,"post was saved", e)
                    Toast.makeText(MainActivity.this, "error while saving", Toast.LENGTH_SHORT).show();
                }
                Log.i(TAG,"post was successful");
                etDescription.setText("");
                ivPostImage.setImageResource(0);
                return;
            }
        }
    }

    }
    queryPosts();
    private void queryPosts(){
        ParseQuery<TodoItem>query= ParseQuery.getQuery(TodoItem.class);
        query.findInBackround(new FindCallback<Post>());
            @Override
                    public void done(List<Post>posts,ParseException e){
                if(e != null){
                    Log.e(TAG ,"Issue with getting posts",e);
                    return;
                }
                for(Post post: posts){
                    Log.i(TAG, "Post:"+ post.getDescription());
                }
    }
}}

    private void launchCamera() {
    }