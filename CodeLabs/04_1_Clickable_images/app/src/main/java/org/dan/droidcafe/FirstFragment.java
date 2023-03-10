package org.dan.droidcafe;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.dan.droidcafe.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    public static final String EXTRA_MESSAGE = "ordered";
    public static String ORDER_MSG = "ORDER RECEIVED : ";
    private FragmentFirstBinding binding;
    private ImageView donut;
    private ImageView ice_cream;
    private ImageView froyo;
    public FloatingActionButton shoppingCartBtn;
    private String orderedItem = null;
    private Button toAlert;
    private Button toDatePick;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        onClickFunc(view);

        shoppingCartBtn = view.findViewById(R.id.shoppingCartBtn);
        shoppingCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), OrderActivity.class);
                intent.putExtra(EXTRA_MESSAGE, ORDER_MSG);
                startActivity(intent);
            }
        });

        Button toAlert = view.findViewById(R.id.toAlert);
        toAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_content_main);
                NavController navController =  navHostFragment.getNavController();
                navController.navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        binding.toDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_content_main);
                NavController controller = navHostFragment.getNavController();
                controller.navigate(R.id.action_FirstFragment_to_DatePicker);
            }
        });

    }

    public void onClickFunc(View view) {
        donut = view.findViewById(R.id.donut);
        donut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayToast("DONUT!");
                orderedItem = "Donut";
                ORDER_MSG += "DONUT";
            }
        });

        ice_cream = view.findViewById(R.id.ice_cream);
        ice_cream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayToast("ICE CREAM!");
                orderedItem = "Ice Cream";
                ORDER_MSG += "Ice Cream";
            }
        });

        froyo = view.findViewById(R.id.froyo);
        froyo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayToast("FROYO! ");
                orderedItem = "Frozen Yogurt";
                ORDER_MSG += "Frozen Yogurt";
            }
        });


        toDatePick = view.findViewById(R.id.toDatePicker);
        toDatePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //SWITCH FRAG
            }
        });
    }

    public void displayToast(String message) {
        Toast.makeText(getContext(), message,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}