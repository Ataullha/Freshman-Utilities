package com.example.freshmanutilites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class FAQActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Versions> versionsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqactivity);

        recyclerView = findViewById(R.id.recylerView);

        initData();
        setRecylerView();

    }

    // initialize the data
    private void initData() {

        // arraylist
        versionsList = new ArrayList<>();

        // so nibba goes here for the data :)
        versionsList.add(new Versions("What about varsity transportation services at SUST ?","Admin","High","You have to pay a transportation fees (for everyone ) but you can use those BUS at free of cost for regular UP-Down\n\n*FOR BUS DETAILS SCHEDULE VISIT TO THE TOOLS SECTION OF THE APP"));
        versionsList.add(new Versions("What about SUST Medical Centre ?","Admin","High","All the Treatment Avaiable here are free of cost.\n\n*JUST BRING YOUR MEDICAL CARD"));
        versionsList.add(new Versions("Library Hours at SUST Central Library?","Admin","High","Sunday to Thursday:\n08.00 AM to 08.00 PM\nFriday,Saturday & Gov. Holidays: Closed\nYou must return the book within 14 days of borrowing the book or pay a penalty of Tk. 5 per day thereafter.\n\n*JUST TOOK YOUR ID-CARD FOR BETTER FACILITY"));
        versionsList.add(new Versions("What About The Hall Facility?","Others","Mid","-For Girls based on their Application they can easily get a seat in the varsity hall or it's attached hall\n-For Boys based on their Application and Availity in the hostel they can get a seat in the varsity hall"));
        versionsList.add(new Versions("What is Edu-mail and The Advantages of having an Edu Mail?","Admin","High","Many students and Edu email holders should aware of below provided top-level accounts and create the account with EDU Email and verify the email and enjoy the benefits.\nLet’s look at the available benefits features.\nAmazon Prime Account(180 days)-The Washington Post Premium Account\n-The Newyork Times Account\n-Apple iPhone Discounts for Students\n-Adobe Creative Cloud\n-Spotify Account\n-MS office 365\n-Github Developer Student Account(Best)\n-Get Google Drive Unlimited Storage Account]\nMany more...."));
        versionsList.add(new Versions("What If I Forgot My Student(*@student.sust.edu) Email and Password?\n","Admin","High","Contact To The Computer & Information Center (A Buliding)"));
        versionsList.add(new Versions("What About The  Exam Mark Distribution here? ","Admin","Mid","In General The Distribution is\n\n-Assignment-10%%\n-Attendance-10%%\n-Quiz/Mid-Term-20%% and\n -Final Exam-60%%"));
        versionsList.add(new Versions("SUST WiFi Password ?","Admin","Important","SUST10s10"));
        versionsList.add(new Versions("Maximum Number of Total Credit/s One Can Complete in A Semester/Term?","Admin","Low","30 Credit/s At Max"));
        versionsList.add(new Versions("How To Get The Formal Photo I Used At My Admission ?","Admin","Very Low","Visit any of SUST authorize website you can login with registration ( i.e. Library Website ) profile section"));


        //versionsList.add(new Versions());

    }

    // set the arrayadapter to the recycler view
    private void setRecylerView() {
        VersionsAdapter versionsAdapter = new VersionsAdapter(versionsList);
        recyclerView.setAdapter(versionsAdapter);
        recyclerView.setHasFixedSize(true);
    }

}