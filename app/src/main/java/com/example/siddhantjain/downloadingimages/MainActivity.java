package com.example.siddhantjain.downloadingimages;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {
    ImageView downloadedImage;

    public void downloadImage(View view){
        ImageDownloader task = new ImageDownloader();
        Bitmap myImage;
        try {

            myImage = task.execute("https://qwizzy.s3.amazonaws.com/uploads/quiz_item/image/7033/1494364133-4-0013-2996/maverick_item.jpg").get();
            downloadedImage.setImageBitmap(myImage);

        } catch (Exception e) {

            e.printStackTrace();
        }
        Toast.makeText(this , "BUTTON TAPPED" ,Toast.LENGTH_SHORT ).show();
    }

    public class ImageDownloader extends AsyncTask<String , Void , Bitmap>{

        @Override
        protected Bitmap doInBackground(String... urls) {

            try {

                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);
                return myBitmap;

            } catch (Exception e) {

                e.printStackTrace();

            }
            return null;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        downloadedImage = (ImageView) findViewById(R.id.imageView);
    }
}
