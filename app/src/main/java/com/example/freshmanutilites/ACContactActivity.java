package com.example.freshmanutilites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ACContactActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Contacts> contactsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accontact);

        recyclerView = findViewById(R.id.recyclerViewACContact);

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
        contactsList.add(new Contacts("Varsity","SUST Helpline","880-821-713491","","Sylhet"));
        contactsList.add(new Contacts("Helpline","Hrassment Prevention and Protection Cell","01711897079","",""));
        contactsList.add(new Contacts("Proctor","Md Alamgir Kabir","01712160948","joy_sust123@yahoo.com",""));
        contactsList.add(new Contacts("Assistant Proctor","Md Abu Hena Pohil","01723048735","",""));
    }

}