package com.auribises.gw2018a;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class JSONParserActivity extends AppCompatActivity {

    StringBuffer response = new StringBuffer();
    BookFetchTask task;

    ArrayList<Book> bookList = new ArrayList<>();

    ListView listView;
    ArrayAdapter<String> adapter;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsonparser);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");

        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);

        progressDialog.show();

        task = new BookFetchTask();
        task.execute(); // for Java Thread -> task.start();
    }


    /*class BookFetchTask extends Thread{
        public void run(){

        }
    }*/

    // AsyncTask is Thread in Android which runs parallely to the Activity
    class BookFetchTask extends AsyncTask{


        // We cannot write any code in doInBackground which has anything which uses UI
        @Override
        protected Object doInBackground(Object[] objects) {


            try{
                // Mention the URL to which you want to send Request
                URL url = new URL("http://www.json-generator.com/api/json/get/chQLxhBjaW?indent=2");

                // Send a Request to the Server
                URLConnection connection = url.openConnection();

                // Read the Response from the Server
                InputStream inputStream = connection.getInputStream();

                // To read response line by line in the form of text
                InputStreamReader reader = new InputStreamReader(inputStream);
                BufferedReader buffer = new BufferedReader(reader);

                String line = "";

                while((line = buffer.readLine()) != null){
                    response.append(line);
                }

                // Writing Toast here gives Exception
                // Toast.makeText(JSONParserActivity.this,"Respone is: "+response.toString(),Toast.LENGTH_LONG).show();

            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }


        // Will be executed after doInBackground is finished
        // We have a method where we can write the code to update any UI element
        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            Toast.makeText(JSONParserActivity.this,"Respone is: "+response.toString(),Toast.LENGTH_LONG).show();

            // Parsing the JSON to my Book Objects
            try{

                JSONObject jsonObject = new JSONObject(response.toString());
                JSONArray jsonArray = jsonObject.getJSONArray("bookstore");

                for(int i=0;i<jsonArray.length();i++){

                    JSONObject jObj = jsonArray.getJSONObject(i);

                    Book book = new Book();
                    book.price = jObj.getString("price");
                    book.name = jObj.getString("name");
                    book.author = jObj.getString("author");

                    bookList.add(book);

                }

                // Add Data to Adapter


                listView.setAdapter(adapter);

                progressDialog.dismiss();

            }catch (Exception e){
                e.printStackTrace();
            }


        }
    }
}
