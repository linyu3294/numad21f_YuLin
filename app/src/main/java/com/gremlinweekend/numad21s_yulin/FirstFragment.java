package com.gremlinweekend.numad21s_yulin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.gremlinweekend.numad21s_yulin.linkActivity.LinkActivity;

public class FirstFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_about).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
        view.findViewById(R.id.button_new_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewActivity();
            }
        });
        view.findViewById(R.id.button_link_collector).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) { openLinkActivity();}
        });
    }
    public void openNewActivity(){
        Intent intent = new Intent(FirstFragment.this.getActivity(), NewActivity.class);
        startActivity(intent);
    }
    public void openLinkActivity(){
        Intent intent = new Intent(FirstFragment.this.getActivity(), LinkActivity.class);
        startActivity(intent);
    }
}