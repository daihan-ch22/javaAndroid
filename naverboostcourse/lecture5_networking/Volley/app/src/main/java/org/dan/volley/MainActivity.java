package org.dan.volley;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequest();
            }
        });

        if(AppHelper.requestQueue == null)
            AppHelper.requestQueue = Volley.newRequestQueue(getApplicationContext());


    }



    //Volley Request method
    public void sendRequest(){
        String url = "https://google.com";
        /**
         * Param:
         * 1st = HTTP Method type (GET,POST ...)
         * 2nd = Target URL
         * 3rd = Response.Listner<>(){ ... }        -> String으로 응답을 받겠다
         * 4th = Response.ErrorListner<>(){ ... }   -> 응답이 에러인 경우 어떻게 할지
         * */
        StringRequest request = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        println("response : " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println("error : " + error.getMessage());
                    }
                })
        //위에서 StringRequest로 요청할때 POST처럼 특정 파라미터를 같이 전달하고 싶다면 아래처럼
        // { } 안에 Request클래스의 getParams()를 오버라이딩
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;
            }
        };
        request.setShouldCache(false); //캐싱 false -> 이전 결과가 있더라도 새로 Request객체 만들기

        AppHelper.requestQueue.add(request); // 보통 요청을 보냈다는 메시지는 여기서 출력해준다.
        println("REQUEST SENT!");
    } // SEND REQUEST METHOD END //

    public void println(String data){
        textView.append(data + "\n");
    }
}