package com.example.freshmanutilites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

public class EEEContactActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Contacts> contactsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eeecontact);

        recyclerView = findViewById(R.id.recyclerViewEEEContact);



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

        InputStream is = this.getResources().openRawResource(R.raw.eee_contact);

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
        String eee_contact[] = contact_data.split("~~~~");
        //String eee_contact[] = {"Associate Professor & Head~Dr. Ifte Khairul Amin~+8801911034624~iftekhar-eee@sust.edu","Associate Professor~Biswajit Paul~+880-1712-554955~biswajit-eee@sust.edu","Assistant Professor~Md Rasedujjaman~01714557885~mrased-eee@sust.edu","Assistant Professor~Tuhin Dev~8801671573686 ~deytuhin-eee@sust.edu; dev.tuhin.eee@gmail.com","Assistant Professor~Mohammad Kamruzzaman Khan Prince ~01737327023~kzaman.eee@gmail.com","Assistant Professor~Arif Ahammad~01720122789~arif-eee@sust.edu, arif08eee@gmail.com","Assistant Professor~Jawata Afnan ~01717259325~jawatafnan-eee@sust.edu","Assistant Professor~Jibesh Kanti Saha~01680003287~jibesh-eee@sust.edu, jksaha14@gmail.com","Assistant Professor~Md. Mohsinur Rahman Adnan~01916422526~mmradnan-eee@sust.edu, adnanmohsin27@gmail.com","Assistant Professor~Hriteshwar Talukder~+8801745311342~hriteshwar@gmail.com, hriteshwar-eee@sust.edu","Assistant Professor~Tahmid Aziz Chowdhury~01720640456~tahmid.aziz@gmail.com; tahmid-eee@sust.edu","Assistant Professor~Md. Asaduz Zaman Mamun~01515623326~asadmamun-eee@sust.edu","Assistant Professor~Nuren Zabin Shuchi~01521460665<~asadmamun-eee@sust.edu","Assistant Professor~Showmik Singha~+8801712331122~showmiksingha-eee@sust.edu, showmik.eee@gmail.com, ","Assistant Professor~Md. Ishfak Tahmid ~01715279154~ishfaktahmid@gmail.com","Lecturer~Nafis Imtiaz Rahman~01711519433~nafisimtiaz71@gmail.com"};
        for(String contact:eee_contact){
            //Toast.makeText(getApplicationContext(), contact, Toast.LENGTH_SHORT).show();
            String infos[] = contact.split("~~");
            //Toast.makeText(getApplicationContext(), infos[0], Toast.LENGTH_SHORT).show();
            contactsList.add(new Contacts(infos[0]+"",infos[1]+"",infos[2]+"",infos[3]+"","N/A"));
        }
    }

}