package com.example.freshmanutilites;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class Fragment1 extends Fragment implements View.OnClickListener {

    TextView nameTv, deptTv, batchTv, mobNoTv, bloodGrpTv, linkTv, hisTv;
    ImageView imageView;
    ImageButton imageButton, imageButtonMenu;
    ImageView fb,github,linkedin,stackoverflow;
    LinearLayout social;

    public boolean isNetworkConnected() {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo ninfo = cm.getActiveNetworkInfo();
            connected = ninfo != null && ninfo.isAvailable() && ninfo.isConnected();
            return connected;
        } catch (Exception e) {

        }
        return connected;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // inflate the xml fragment ...
        View view = inflater.inflate(R.layout.fragment1, container, false);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        nameTv = getActivity().findViewById(R.id.name_fragment_profile_f1);
        deptTv = getActivity().findViewById(R.id.dept_fragment_profile_f1);
        batchTv = getActivity().findViewById(R.id.batch_fragment_profile_f1);
        mobNoTv = getActivity().findViewById(R.id.mobile_number_fragment_profile_f1);
        bloodGrpTv = getActivity().findViewById(R.id.blood_group_fragment_profile_f1);
        linkTv = getActivity().findViewById(R.id.facebook_link_fragment_profile_f1);
        imageView = getActivity().findViewById(R.id.image_fragment_profile_f1);
        imageButtonMenu = getActivity().findViewById(R.id.ib_menu_f1);
        hisTv= getActivity().findViewById(R.id.history_fragment_profile_clickable_f1);

        fb = getActivity().findViewById(R.id.p_fblink);
        github = getActivity().findViewById(R.id.p_githublink);
        linkedin = getActivity().findViewById(R.id.p_linkedinlink);
        stackoverflow = getActivity().findViewById(R.id.p_solink);
        social = getActivity().findViewById(R.id.layout_social);

        imageButton = getActivity().findViewById(R.id.ib_edit_f1);

        imageButton.setOnClickListener(this);
        imageButtonMenu.setOnClickListener(this);
        imageView.setOnClickListener(this);
        linkTv.setOnClickListener(this);
        hisTv.setOnClickListener(this);
        social.setOnClickListener(this);

        //loadData();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //   case R.id.go_to_create_profile:
            //   Intent intent = new Intent(getActivity(), CreateProfileActivity.class);
            //    startActivity(intent);

            case R.id.ib_edit_f1:
                Intent intent = new Intent(getActivity(), UpdateProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.ib_menu_f1:
                BottomSheetMenu bottomSheetMenu = new BottomSheetMenu();
                bottomSheetMenu.show(getFragmentManager(), "bottomsheet");
                break;
            case R.id.image_fragment_profile_f1:
                Intent intent1 = new Intent(getActivity(), ImageActivity.class);
                startActivity(intent1);
                break;

            case R.id.history_fragment_profile_clickable_f1:
                Bottomsheet_history bottomsheet = new Bottomsheet_history();
                bottomsheet.show(getFragmentManager(),"bottom");

                break;
            case R.id.layout_social:
                try {
                    String url = linkTv.getText().toString();
                    Intent intent2 = new Intent(Intent.ACTION_VIEW);
                    intent2.setData(Uri.parse(url));
                    startActivity(intent2);
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Facebook Profile not Exist!", Toast.LENGTH_SHORT).show();
                }

        }
    }

    @Override
    public void onStart() {
        super.onStart();

        loadData();
    }

    private void loadData() {
        if (isNetworkConnected()) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String currentId = user.getUid();
            DocumentReference reference;
            FirebaseFirestore firestore = FirebaseFirestore.getInstance();

            reference = firestore.collection("User").document(currentId);
            reference.get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.getResult().exists()) {
                                String name = task.getResult().getString("name");
                                String dept = task.getResult().getString("Dept");
                                String batch = task.getResult().getString("Batch");
                                String mobile_no = task.getResult().getString("Mobile No");
                                String link = task.getResult().getString("Facebook Profile Link");
                                String iurl = task.getResult().getString("Image URL");
                                String blood = task.getResult().getString("Blood Group");

                                //Picasso.get().load(iurl).centerCrop().fit().into(imageView);
                                //Toast.makeText(getActivity(), iurl, Toast.LENGTH_SHORT).show();
                                if (iurl.contains("https://firebasestorage.googleapis.com/v0/b/freshmanutilites.appspot.com/o/question-mark-1019993_640.jpg?alt=media&token=6067a6e6-7952-4e64-acf5-1d7746104ff0")) Picasso.get().load(R.drawable.no_profile_picture).centerCrop().fit().into(imageView);
                                else Picasso.get().load(iurl).centerCrop().fit().into(imageView);

                                nameTv.setText(name);
                                deptTv.setText(dept);
                                batchTv.setText(batch);
                                mobNoTv.setText(mobile_no);
                                linkTv.setText(link);
                                bloodGrpTv.setText(blood);
                                if (link.contains("fb")||link.contains("facebook")) {
                                    fb.setVisibility(View.VISIBLE);
                                }else if(link.contains("stackoverflow")) {
                                    fb.setVisibility(View.GONE);
                                    stackoverflow.setVisibility(View.VISIBLE);
                                }else if(link.contains("linkedin")){
                                    fb.setVisibility(View.GONE);
                                    linkedin.setVisibility(View.VISIBLE);
                                } else if(link.contains("github")){
                                    github.setVisibility(View.VISIBLE);
                                    fb.setVisibility(View.GONE);
                                } else {
                                    fb.setVisibility(View.GONE);
                                    linkTv.setVisibility(View.VISIBLE);
                                }

                            } else {
                                Intent intent = new Intent(getActivity(), CreateProfileActivity.class);
                                startActivity(intent);
                            }
                        }
                    });

        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("No Internet")
                    .setCancelable(false)
                    .setMessage("Check Your Internet Connection!")
                    .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //startActivity(new Intent(getActivity(), Fragment1.class));
                            //finish();
                            builder.create();
                            builder.show();
                        }
                    }).setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getActivity(), "Goodbye!", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    getActivity().finish();
                    System.exit(0);
                }
            });
            builder.create();
            builder.show();
        }
    }
}