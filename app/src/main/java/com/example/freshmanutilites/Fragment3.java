package com.example.freshmanutilites;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Fragment3 extends Fragment implements View.OnClickListener {


    private CardView contactcard, faqcard, mapcard, toolscard;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // inflate the xml fragment ...
        View view = inflater.inflate(R.layout.fragment3, container, false);
        return view;

    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        contactcard = getActivity().findViewById(R.id.cv_contacts);
        faqcard = getActivity().findViewById(R.id.cv_faq);
        mapcard = getActivity().findViewById(R.id.cv_map);
        toolscard = getActivity().findViewById(R.id.cv_tools);

        contactcard.setOnClickListener(this);
        faqcard.setOnClickListener(this);
        mapcard.setOnClickListener(this);
        toolscard.setOnClickListener(this);

    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cv_contacts:
                startActivity(new Intent(getActivity(), ContactSubSection.class));
                break;
            case R.id.cv_faq:
                startActivity(new Intent(getActivity(), FAQActivity.class));
                break;
            case R.id.cv_map:
                startActivity(new Intent(getActivity(), LocationActivity.class));
                break;
            case R.id.cv_tools:
                startActivity(new Intent(getActivity(), ToolsActivity.class));
                break;


        }


    }
}
