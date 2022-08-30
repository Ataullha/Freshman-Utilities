package com.example.freshmanutilites;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
 * All code in the contact section and the parser section is done by
 * @Ataullha
 * CSE-2018,SUST
 */

public class ECOContactActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Contacts> contactsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecocontact);

        recyclerView = findViewById(R.id.recyclerViewECOContact);



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

        /*
         *   writer : Md Ataullha,CSE-2018
         */
        String data =  "";
        String contact_data = "";
        StringBuffer stringBuffer = new StringBuffer();

        InputStream is = this.getResources().openRawResource(R.raw.eco_contact);

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        if (is!=null){
            try {
                while ((data = reader.readLine())!=null){
                    stringBuffer.append(data+"\n");
                    contact_data = ""+data;
                }
                // textView.setText(stringBuffer);
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String eco_contact[] = contact_data.split("~~~~");
        //String eco_contact[] = {"Professor~Syed Hasanuzzaman      ~ 01710424109 Ext-252~shzaman-eco@sust.edu","Professor~Dr Mohammad Abdul Munim Joarder~01731866024~munim-eco@sust.edu","Professor & Head~Zahir Uddin Ahmed~ 01711947128~zahir-eco@sust.edu","Professor~Dr Mohammad Rafiqul Islam~01746500155~rafiqieco@gmail.com","Professor~Dr Mohammad Abdul Hannan Pradhan~  01731247170~hannan-eco@sust.edu, pradhanhannan@gmail.com","Professor~Dr Mohammad Sadiqunnabi Chowdhury~ 01915819168~sadique-eco@sust.edu","Professor~Dr. Muntaha Rakib~ 01718222455~muntaha_rakib@yahoo.com","Associate Professor~Mohammad Masud Alam~ 01716072056~masudnb@gmail.com","Associate Professor~Mahmuda Sultana~ 01818936194~mahmuda-eco@sust.edu","Associate Professor~Sabiha Afrin~  01717040166~sabiha-eco@sust.edu","Associate Professor~Nazmunnessa Bakth~ 01715062004~sumisust2002@yahoo.com","Associate Professor~Dr Md Mahbubul Hakim~01833347867~hakim-eco@sust.edu, mahbubulhakim@gmail.com","Associate Professor~Shahnaz Haque~+8801717136114~shahnazh31@gmail.com; shahnaz31-eco@sust.edu","Assistant Professor~Monir Uddin Ahmed~ 01712673224~monir-eco@sust.edu","Assistant Professor~Md Gias Uddin Khan~ 01718792571~khangias33@gmail.com","Assistant Professor~Farhana Ahmed~ 01833347869~farhana-eco@sust.edu or farhanaecon@gmail.com","Assistant Professor~ Md Aslam Hossain~ Mobile no: 8801717201312~aslam-eco@sust.edu ","Assistant Professor~Chowdhury Abdullah-Al-Baki~ 01713259199~baki-eco@sust.edu","Assistant Professor~Dr. Munshi Naser Ibne Afzal~+8801794666824~munshi.naser@gmail.com, ","Assistant Professor~Md Akther Husain~ 01723166779~mahusain-eco@sust.edu","Assistant Professor~Amit Roy~880 821 713491~amit-eco@sust.edu","Assistant Professor~Eshrat Sadiya~8801731752725~ sadiya-eco@sust.edu","Assistant Professor~Tarik Aziz~ 01923621123~tarikaziz-eco@sust.edu, tarik.sust@gmail.com","Professor~Dr Rezai Karim Khondker~01715049888~rkhondker@sust.edu"};
        for(String contact:eco_contact){
            //Toast.makeText(getApplicationContext(), contact, Toast.LENGTH_SHORT).show();
            String infos[] = contact.split("~~");
            //Toast.makeText(getApplicationContext(), infos[0], Toast.LENGTH_SHORT).show();
            contactsList.add(new Contacts(infos[0]+"",infos[1]+"",infos[2]+"",infos[3]+"","N/A"));
        }
    }

}