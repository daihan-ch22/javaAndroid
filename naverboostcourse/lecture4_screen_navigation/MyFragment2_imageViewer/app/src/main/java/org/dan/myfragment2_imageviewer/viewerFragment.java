package org.dan.myfragment2_imageviewer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class viewerFragment extends Fragment {
    ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragmentviewer, container);

        imageView = (ImageView) rootView.findViewById(R.id.imageView);

        return rootView;
    }

    public void setImage(int index){
        if(index == 0){
            imageView.setImageResource(R.drawable.s1);
        } else if(index == 1){
            imageView.setImageResource(R.drawable.s2);
        }else if(index == 2){
            imageView.setImageResource(R.drawable.s3);
        }
    }

}
