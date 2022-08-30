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

public class CEEContactActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Contacts> contactsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ceecontact);

        recyclerView = findViewById(R.id.recyclerViewCEEContact);



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

        InputStream is = this.getResources().openRawResource(R.raw.cee_contact);

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
        String cee_contact[] = contact_data.split("~~~~");
        //String cee_contact[] = {"Professor~Dr. Mohammod Aktarul Islam Chowdhury~821-713491 (EXT: 269)~maicee@gmail.com","Professor~Dr. Md. Jahir Bin Alam~88-0821 713491 (EXT: 269)~jahiralam@yahoo.com ","Professor~Dr. Mushtaq Ahmed~0821-713491/714479/713850 (EXT 269)~mushtaq_cee@yahoo.com","Professor~Dr. Muhammad Azizul Hoque~880-821-713850~ahoque-cee@sust.edu, aziz_cee@yahoo.com","Professor~Dr. Md. Misbah Uddin~88-01939671364~mun_cee@yahoo.co.uk, mun-cee@sust.edu","Professor~Dr. Mohammad Shahidur Rahman~0821-713491/714479/713850 (EXT 269)~msr.ceesust@gmail.com","Professor~Dr. Bijit Kumar Banik~88-01798599471~bijit-cee@sust.edu","Professor~Dr. Muhammad Saiful Islam~+8801889170438~saiful-cee@sust.edu","Professor~Dr. H.M.A.Mahzuz~88-01929983057~mahzuz_211@yahoo.com","Associate Professor~Dr. Md. Imran Kabir~880-821-713491 (EXT: 169)~ imran.kabir-cee@sust.edu","Associate Professor~Gulam Md. Munna~88-0821-713850 (Ext. 269)~gmunna192-cee@sust.edu","Associate Professor~Dr. Md Bashirul Haque~01973409490~bashir-cee@sust.edu","Associate Professor~Dr. Tajmunnaher~01919813027~moon_cee@yahoo.com","Associate Professor~Shilpy Rani Basak~01712806038~shilpy_basak@yahoo.com","Associate Professor~Dr. Ahmad Hasan Nury~88-01756380455~hasan-cee@sust.edu","Associate Professor~Dr. Shriful Islam~+8801716687869~sharif_sust_cee@yahoo.com, sharif-cee@sust.edu","Assistant Professor~Sourav Ray~880821-713491 (Ext. 642/269)~sourav-cee@sust.edu; sourav.ceesust@gmail.com","Assistant Professor~Mohammad Rafiqul Islam~88-0821-713491 (Ext. 269)~rafiqul-cee@sust.edu","Assistant Professor~Mohaiminul Haque~88-0821-713491 (Ext. 269)~mohaiminul-cee@sust.edu; pallab87.sust@gmail.com","Assistant Professor~Md. Aminul Islam~880 821 713491 (EXT: 269) ~aminul-cee@sust.edu, kajol_cee@yahoo.com ","Assistant Professor~Khairul Hasan~88-01674542117~k.hasancee@gmail.com, khairul-cee@sust.edu","Assistant Professor~Nur Md. Robiul Hoque~88-01920819986~hoque.cee@gmail.com; hoque-cee@sust.edu","Lecturer~Ayesha Ferdous Mita~01614333046~mita-cee@sust.edu; mita.sustcee@gmail.com","Lecturer~Khayrun Nahar Mitu~01866451509~mitu-cee@sust.edu","Lecturer~Sabrin Ara~0171652951~ sabrin-cee@sust.edu; sabrinara.sust@gmail.com"};
        for(String contact:cee_contact){
            //Toast.makeText(getApplicationContext(), contact, Toast.LENGTH_SHORT).show();
            String infos[] = contact.split("~~");
            //Toast.makeText(getApplicationContext(), infos[0], Toast.LENGTH_SHORT).show();
            contactsList.add(new Contacts(infos[0]+"",infos[1]+"",infos[2]+"",infos[3]+"","N/A"));
        }
    }

}