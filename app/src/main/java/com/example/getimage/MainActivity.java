package com.example.getimage;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {

    EditText urltext;
    ImageView finalImage;


    public void download(View view){
        finalImage=(ImageView)findViewById(R.id.downloadedImage);
        urltext=(EditText) findViewById(R.id.textUrl);
        Log.i("information", "download: ");
        GetURl task= new GetURl();
        Bitmap myMap;
        try {
            myMap=task.execute("https://www.google.com/imgres?imgurl=https%3A%2F%2Fc.ndtvimg.com%2F2021-10%2Fgjo8hnng_ms-dhoni-ipl_625x300_07_October_21.jpg&imgrefurl=https%3A%2F%2Fsports.ndtv.com%2Fipl-2021%2Fthere-are-a-lot-of-uncertainties-ms-dhoni-on-future-with-chennai-super-kings-2567110&tbnid=DPfycM-KgAiwBM&vet=12ahUKEwiyg8qBpqD2AhXqgGMGHSkXCxgQMygAegUIARDUAQ..i&docid=oF23NwR3RXBXbM&w=806&h=605&q=ms%20dhoni&ved=2ahUKEwiyg8qBpqD2AhXqgGMGHSkXCxgQMygAegUIARDUAQ").get();
            finalImage.setImageBitmap(myMap);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
class GetURl extends AsyncTask<String,Void, Bitmap>{

    @Override
    protected Bitmap doInBackground(String... urls) {

        try {
            URL url=new URL(urls[0]);
            HttpURLConnection urlConnection=(HttpURLConnection)url.openConnection();
            urlConnection.connect();
            InputStream inputStream=urlConnection.getInputStream();
            Bitmap myBitmap= BitmapFactory.decodeStream(inputStream);
            return myBitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
