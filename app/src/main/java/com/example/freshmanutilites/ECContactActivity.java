package com.example.freshmanutilites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ECContactActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Contacts> contactsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eccontact);

        recyclerView = findViewById(R.id.recyclerViewECContact);

        initData();
        setRecylerView();
    }

    private void setRecylerView() {
        ContactAdapter contactAdapter = new ContactAdapter(contactsList);
        recyclerView.setAdapter(contactAdapter);
        recyclerView.setHasFixedSize(true);
    }

    private void initData() {
        contactsList = new ArrayList<>();
        contactsList.add(new Contacts("Emergency","Police, Fire Service and Ambulance Services","999","",""));
        contactsList.add(new Contacts("Women and Children","Women and Children Abuse","109","",""));
        contactsList.add(new Contacts("Police","Bangladesh Police Helpdesk","100","",""));
        contactsList.add(new Contacts("RAN","RAB Helpdesk","101","",""));
        contactsList.add(new Contacts("Fire Service","Fire Service Hotline","102","",""));
        contactsList.add(new Contacts("Hospital","Mount Adora Hospital","01711-580147","",""));
        contactsList.add(new Contacts("Hospital","Sylhet MAG Osmain Medical Clg ","0821-713487","",""));
        contactsList.add(new Contacts("Hospital","Jalalabad Ragib-Rabeya Medical College & Hospital","+88 0821719096","",""));
        contactsList.add(new Contacts("Ambulance","Sylhet Ambulance Service","+880 821 721103","",""));
    }

}