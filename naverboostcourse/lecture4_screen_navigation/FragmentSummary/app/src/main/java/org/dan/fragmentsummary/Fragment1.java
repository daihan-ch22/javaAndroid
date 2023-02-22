package org.dan.fragmentsummary;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment1 extends Fragment {

    FragmentCallBack callback;
    TextView textView;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof FragmentCallBack){
            callback = (FragmentCallBack) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(callback != null){
            callback = null;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment1, container, false);
        textView = (TextView) inflatedView.findViewById(R.id.textView);

        Button button = (Button) inflatedView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(callback != null){ // 프래그먼트가 액티비티에 올라간 상태
                    callback.onCommand("show", "data transferred from Fragment1");
                }
            }
        });

        return inflatedView;
    }
    public void onCommandFromActivity(String command, String data) {
        textView.setText(data);
    }


}
