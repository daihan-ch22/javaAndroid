package org.dan.myfragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

//androidx.fragment.app   // 예전 os 동작 가능
//android.app             // 기본 프래그먼트 사용
public class MenuFragment extends Fragment {


    //fragment를 추가하는데는 2가지 방법이 있음.
    // 1. xml - java Inflation
    // 2. Java - new 연산자로 새로운 객체를 만들어 추가

    //방법 1
    //인플레이션을 수동으로 해줘야함 (콜백 함수 - setContent없다. 액티비티가 아니라서)
    //fragment는 실제 뷰는 아니고 부분화면을 담기위한 틀이라고 보면 됨
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView =(ViewGroup) inflater.inflate(R.layout.fragment_menu, container, false);

        return rootView;
    }
}
