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

public class FETContactActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Contacts> contactsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetcontact);

        recyclerView = findViewById(R.id.recyclerViewFETContact);



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

        InputStream is = this.getResources().openRawResource(R.raw.fet_contact);

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
        String fet_contact[] = contact_data.split("~~~~");
        //String fet_contact[] = {"Professor~Dr Md. Mozammel Hoque~+880-821- 713850/ 714479/ 717850 Ext. 601~hoque-fet@sust.edu ; hoquemm@gmail.com","Professor~Dr Iftekhar Ahmad~+880-821- 713850/ 714479/ 717850 Ext. 2602~iftekharfet.sust@yahoo.com","Professor~Dr G M Rabiul Islam~880-821-713491~rabi-ttc@sust.edu, rabiat14@yahoo.com","Professor~Dr Wahiduzzaman~+880-821- 713850/ 714479/ 717850 Ext. 782~wahid-ttc@sust.edu, wahidanft@yahoo.com ","Professor~Dr Rowshon Ara~+880-821- 713850/ 714479/ 717850 Ext. 783~rowshonara-fet@sust.edu","Professor~Dr. Razia Sultana Chowdhury ~+880-821- 713850/ 714479/ 717850 Ext. 2784~sultana-fet@sust.edu,   sult4n4c@gmail.com","Associate Professor~Md. Belal Hossain Sikder~+880-821- 713850/ 714479/ 717850 Ext.781~belal-ttc@sust.edu","Associate Professor~Dr  Animesh Sarkar~+880-821- 713850/ 714479/ 717850 Ext. 785~animeshsarkarbau@gmail.com/ animesh-fet@sust.edu","Associate Professor~Dr Md Zohurul Islam~+880-821- 713850/ 714479/ 717850 Ext. 242~zohurul-fet@sust.edu","Associate Professor~Md. Rahmatuzzaman Rana~+880-821- 713850/ 714479/ 717850 Ext. 242/601~rzaman-fet@sust.edu, rzaman.fet@gmail.com ","Assistant Professor~Ayesha Sarker~+880-821- 713850/ 714479/ 717850 Ext. 242~ayesha_sust85@yahoo.com","Assistant Professor~ Md. Monir Hossain~+880-821- 713850/ 714479/ 717850 Ext. 242~monir-fet@sust.edu","Assistant Professor~Md. Mahfuzur Rahman~Phone: +880-821- 713850/ 714479/ 717850 Ext. 242~mahfuz-fet@sust.edu/ mahfuz27_sust@yahoo.com","Assistant Professor~Mukta Roy~+880-821- 713850/ 714479/ 717850 Ext. 242~muktaroy_87@yahoo.com, muktaroy-fet@sust.edu","Assistant Professor~Kamrunnaher Monalisa~+880-821- 713850/ 714479/ 717850 Ext. 242~thisismona14@gmail.com, kamrunnaher.monalisa-fet@sust.edu","Assistant Professor~Mohammad Afzal Hossain~Phone: +880-821- 713850/ 714479/ 717850 Ext. 242/601~mahossain-fet@sust.edu / afzal.fet@gmail.com ","Assistant Professor~Mizanur Rahman~880-821- 713850/ 714479/ 717850 Ext. 242~mizan.ftt@gmail.com","Assistant Professor~A.S.M. Sayem~880-821- 713850/ 714479/ 717850 Ext. 242~asm.sayem-fet@sust.edu","Assistant Professor~Md. Yasin~880-821- 713850/ 714479/ 717850 Ext. 242~yasin-fet@sust.edu, yasin.sust@gmail.com","Assistant Professor~Shafaet Ahmed~880-821- 713850/ 714479/ 717850 Ext. 242~shafaet.ahmed19@gmail.com","Assistant Professor~Mitu Samadder~880-821- 713850/ 714479/ 717850 Ext. 242~mitu10pstu@gmail.com","Lecturer~Jahid Hasan Shourove~+880-821- 713850/ 714479/ 717850 Ext. 242~shourove-fet@sust.edu, shourovesust@gmail.com","Lecturer~Mahabub Alam~+8801837572299~mahabub-fet@sust.edu"};
        for(String contact:fet_contact){
            //Toast.makeText(getApplicationContext(), contact, Toast.LENGTH_SHORT).show();
            String infos[] = contact.split("~~");
            //Toast.makeText(getApplicationContext(), infos[0], Toast.LENGTH_SHORT).show();
            contactsList.add(new Contacts(infos[0]+"",infos[1]+"",infos[2]+"",infos[3]+"","N/A"));
        }
    }

}