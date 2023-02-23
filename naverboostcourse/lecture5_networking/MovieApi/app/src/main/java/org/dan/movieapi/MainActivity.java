package org.dan.movieapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.dan.movieapi.data.MovieList;
import org.dan.movieapi.data.ResponseInfo;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestMovieList();
            }
        });

        //make requestqueue
        if(AppHelper.requestQueue == null){
            AppHelper.requestQueue = Volley.newRequestQueue(this);
        }
    }

    public void println(String data) {
        textView.append(data + "\n");
    }

    public void requestMovieList() {
        String url = "http://" + AppHelper.host + ":" + AppHelper.port + "/movie/readMovieList";
        url += "?" + "type=1";

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        println("RECEIVED : " + response);

                        processResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println("NOT RECEIVED : " + error);
                    }
                }
        );
        request.setShouldCache(false);
        AppHelper.requestQueue.add(request);
        println("Request for getting movie list has been sent.");
    }

    private void processResponse(String response){
        Gson gson = new Gson();
        ResponseInfo responseInfo = gson.fromJson(response, ResponseInfo.class);

        if(responseInfo.code == 200){
            MovieList list = gson.fromJson(response, MovieList.class);
            println("moviesize" + list.result.size());

            for(int i=0; i<list.result.size(); i++){
                println(i+"번째 : " + list.result.get(i).title + " / " + list.result.get(i).title_eng);
            }
        }
    }
}