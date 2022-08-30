package com.example.freshmanutilites;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/*
 * All code in the contact section and the parser section is done by
 * @Ataullha
 * CSE-2018,SUST
 */

public class MATContactActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Contacts> contactsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matcontact);

        recyclerView = findViewById(R.id.recyclerViewMATContact);



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

        String mat_contact[] = {"Professor~Dr Md Elias Uddin Biswas~721101~ebiswasmat@yahoo.com   ","Professor~Dr Md Shajedul Karim    ~01766061795~msk-mat@sust.edu","Professor~Md Rashed Talukder, PhD    ~+88-01712239309~r.talukder-mat@sust.edu,","Professor~Dr Md Anowarul Islam    ~01919064555~mislam32@gmail.com","Professor~Dr Md Gulam Ali Hayder Chowdhury   ~ 01711967558~gahc_mat@yahoo.com, mgahayder1-mat@sust.edu","Professor~Dr Md Ashraf Uddin~01715041008~auddin-mat@sust.edu","Professor & Head~Dr Md Mahbubur Rashid       ~01711731902~mrashid_math@yahoo.com","Professor~Dr Shamsun Naher Begum   ~01725299507~snaher@yahoo.com","Professor~Dr Nazneen Akhtar                  ~01721484955~nazneenakhtar2827@gmail.com","Professor~Dr Sujoy Chakraborty       ~01712622449~sujoy_chbty@yahoo.com, sujoy-mat@sust.edu, sujoy.chbty@gmail.com","Professor~Dr Mohammed Ashaque Meah ~01748928131~mam-mat@sust.edu","Professor~Dr Mohammad Khayrul Hasan ~01716284646~khayrulmat@gmail.com","Professor~Dr Muhammad Mizanur Rahman  ~+8801711903950~mizanmath_sust@yahoo.com ;  mizan-mat@sust.edu","Professor~Dr  Md  Aminul Haque~01834280832~aminul202@yahoo.com","Professor~Dr Mohammad Sayful Islam~01775484990~sislam_25@yahoo.com, sayful-mat@sust.edu","Professor~Md Shah Noor        ~01715388865~noorms100@gmail.com ","Professor~Dr Kausari Sultana    ~01912067153~kausarisu@gmail.com","Professor~Dr. Razwan Ahamad~+8801740597938~rahamad-mat@sust.edu, shaon_pola@yahoo.com","Professor~Dr Pabel Shahrear  ~01711452071~pabelshahrear@yahoo.com","Professor~Dr Md Abdul Hye     ~01552349695~ahye-mat@sust.edu; ahyemat@gmail.com","Professor~ Dr. Chandrani Nag~01916129200~nagbd@yahoo.com","Associate Professor~Md Matiar Rahman      ~01768291379~mmrahman-mat@sust.edu","Associate Professor~Dr Md Alamgir Kabir      ~01712160948~akabir-mat@sust.edu , ","Assistant Professor~Himadri Shekhar Chakraborty  ~01712568246~himadri-mat@sust.edu","Assistant Professor~Mohammad Salah Uddin      ~01911614515~salahuddin-mat@sust.edu","Assistant Professor~Syed Md Omar Faruk      ~01912730460~omarfaruk-mat@sust.edu","Assistant Professor~S. M. Saydur Rahman~+8801675469943~saydur-mat@sust.edu","Assistant Professor~Amit Kumar Chakraborty~+8801713936851~amit-mat@sust.edu; amit.math.sust@gmail.com","Assistant Professor~Dipok Deb~ +8801819094719~dipok-mat@sust.edu, dipokdeb009@gmail.com"};        for(String contact:mat_contact){
            //Toast.makeText(getApplicationContext(), contact, Toast.LENGTH_SHORT).show();
            String infos[] = contact.split("~");
            //Toast.makeText(getApplicationContext(), infos[0], Toast.LENGTH_SHORT).show();
            contactsList.add(new Contacts(infos[0]+"",infos[1]+"",infos[2]+"",infos[3]+"","N/A"));
        }
    }

}