package com.example.freshmanutilites;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Fragment2 extends Fragment implements View.OnClickListener  {


    private CardView varsitycard, residentialcard, academiccard,otherscard ;
private TextView tv;
    @Nullable
    @Override


    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        // inflate the xml fragment ...
        View view = inflater.inflate(R.layout.fragment2,container,false);




        return view;




    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        varsitycard = getActivity().findViewById(R.id.cv1);
        residentialcard = getActivity().findViewById(R.id.cv2);
        academiccard = getActivity().findViewById(R.id.cv3);
        otherscard = getActivity().findViewById(R.id.cv4);
        varsitycard.setOnClickListener(this);
        residentialcard.setOnClickListener(this);
        academiccard.setOnClickListener(this);
        otherscard.setOnClickListener(this);



    }


    public  void onClick(View v)
    {


        if(v.getId()==R.id.cv1)
        {

            Fragment fragment = new Fragment5();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(((ViewGroup) getView().getParent()).getId(), fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();



        }
        else if (v.getId()==R.id.cv2)
            {
             //   Toast.makeText(getActivity(),"Hi",Toast.LENGTH_LONG).show();

            Fragment fragment = new Fragment6();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(((ViewGroup) getView().getParent()).getId(), fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();




            }

        else if (v.getId()==R.id.cv3)
        {
          //  Toast.makeText(getActivity(),"Hi",Toast.LENGTH_LONG).show();

            Fragment fragment = new Fragment7();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(((ViewGroup) getView().getParent()).getId(), fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();




        }
        else if (v.getId()==R.id.cv4)
        {
           // Toast.makeText(getActivity(),"Hi",Toast.LENGTH_LONG).show();

            Fragment fragment = new Fragment8();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(((ViewGroup) getView().getParent()).getId(), fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();




        }





    }


    

}
