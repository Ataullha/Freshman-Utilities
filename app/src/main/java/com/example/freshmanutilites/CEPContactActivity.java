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

public class CEPContactActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Contacts> contactsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cepcontact);

        recyclerView = findViewById(R.id.recyclerViewCEPContact);



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

        InputStream is = this.getResources().openRawResource(R.raw.cep_contact);

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
        String cep_contact[] = contact_data.split("~~~~");
        //String cep_contact[] = {"Professor~Dr Md Akhtarul Islam     ~880821714479 Ext:288 (Office)~mislam@sust.edu, islamsust@yahoo.com","Professor~Dr Mohammed Mastabur Rahman ~+880 821 713850 (Ext:448)~mrahmanbsb@gmail.com, mrahman-cep@sust.edu","Professor~Dr Engr Salma Akhter ~/447~salma-cep@sust.edu , salmacep@gmail.com","Professor~Abu Yousuf~01919904480~ayousufcep@yahoo.com","Professor~Dr Md Tamez Uddin ~880 821 714479 Ext 681~mtuddin-cep@sust.edu, mtuddin_cep@yahoo.com","Professor~Dr Md. Salatul Islam Mozumder~Phone: 880 821 713850 (Ext: 2682) Mobile:01631606101~salatul-cep@sust.edu","Professor~Dr Md Zakir Hossain~+8801730205001~zakir-cep@sust.edu, mzhbd1979@gmail.com","Professor~Md Mohibul Alam   ~880 821 714479~mmalam-cep@yahoo.com","Associate Professor~Dr. Muhammad Nurunnabi Siddiquee~880 821 714479 Ext 259 (Office)~ nsiddiquee-cep@sust.edu","Associate Professor~Dr Muhammad Zobayer Bin Mukhlish~01912815531~zobayer_ceps@yahoo.com,  zobayer-cep@sust.edu","Associate Professor~Pradip Saha~/259~pradip-cep@sust.edu","Associate Professor~Mohammad Shaiful Alam Amin  ~880 821 714479 Ext: 468~msaamin-cep@sust.edu","Associate Professor~Dr Md Mostafizur Rahman~88-0821 714479 Ext 259~mostafizur.cep@gmail.com, mostafiz-cep@sust.edu","Assistant Professor~Md Fakar Uddin~/259~mfuddin-cep@sust.edu","Assistant Professor~Sreejon Das~/259~sreejon-cep@sust.edu","Assistant Professor~Muhammad Abdus Salam~88-0821714479  Ext 259~	Email: salam-cep@sust.edu; salamcep@gmail.com","Assistant Professor~Mohammad Rakib Uddin~880 821 714479 Ext 259~rakib-cep@sust.edu; mruddincep@gmail.com ","Assistant Professor~Niloy Chandra Sarker~880 821 714479 Ext 259~niloy.sarker@ndsu.edu, niloy-cep@sust.edu, sarkern","Assistant Professor~Mitun Chandra Bhoumick	~88-0821714479~mcbhoumick.sust@gmail.com, mcbhoumick-cep@sust.edu","Assistant Professor~Humayun Ahmad~88-0821714479 Ext 259 ~humayun-cep@sust.edu, humayun_ceps2007@yahoo.com","Assistant Professor~Rahatun Akter~880 821 714479 Ext 259~rahatuncep@gmail.com, rahatun-cep@sust.edu","Assistant Professor~Md. Shahadat Hossain~+88 01914844809~shahadat_cep43@yahoo.com ; shahadat06-cep@sust.edu","Assistant Professor~Myisha Ahmed Chowdhury~+8801683661920~myishachowdhory36@gmail.com","Assistant Professor~Md. Anisur Rahman~01724024050~anis.cep@gmail.com, anis-cep@sust.edu","Assistant Professor~Md. Delowar Hossain~+8801731744411~delowar-cep@sust.edu, delowar46@gmail.com","Assistant Professor~Shafiul Hossain~+8801515265557~shafiul-cep@sust.edu, shafiulhossain93@gmail.com"};
        for(String contact:cep_contact){
            //Toast.makeText(getApplicationContext(), contact, Toast.LENGTH_SHORT).show();
            String infos[] = contact.split("~~");
            //Toast.makeText(getApplicationContext(), infos[0], Toast.LENGTH_SHORT).show();
            contactsList.add(new Contacts(infos[0]+"",infos[1]+"",infos[2]+"",infos[3]+"","N/A"));
        }
        /**
         contactsList.add(new Contacts("Professor","Dr. M. Shahidur Rahman","+8801914930807","rahmanms@sust.edu","Room No: 322"));
         contactsList.add(new Contacts("Professor","Dr. Md. Reza Selim","+8801972357830","selim@sust.edu","Room No: 319"));
         contactsList.add(new Contacts("Professor","Dr. M. Jahirul Islam","+8801770348185","jahir-cse@sust.edu","Room No: 321"));
         contactsList.add(new Contacts("Professor & Head","Dr. M. Abdullah Al Mumin","+8801711445110","mumin-cse@sust.edu","Room No: 333/312"));
         contactsList.add(new Contacts("Professor","Mr. Md. Masum","+8801919736248","masum-cse@sust.edu","Room No: 311"));
         contactsList.add(new Contacts("Professor","Dr. Farida Chowdhury","+8801743018917","farida-cse@sust.edu","Room No: 310"));
         contactsList.add(new Contacts("Professor","Dr. Md Forhad Rabbi","+8801844175805","frabbi-cse@sust.edu","Room No: 323"));
         contactsList.add(new Contacts("Asst. Professor","Mrs. Mahruba Sharmin Chy.","+8801917566699","mahrubacse@gmail.com","Room No: 313"));
         contactsList.add(new Contacts("Asst. Professor","Mrs. Sadia Sultana","+8801911089612","sadia-cse@sust.edu","Room No: N/A"));
         contactsList.add(new Contacts("Asst. Professor","Ms. Ayesha Tasnim","+8801713328269","ayeshapakhi@gmail.com","Room No: 308"));
         contactsList.add(new Contacts("Asst. Professor","Mr. Md. Mahadi Hasan Nahid","+8801738150127","nahid-cse@sust.edu","Room No: 121"));
         contactsList.add(new Contacts("Asst. Professor","Mr. Enamul Hassan","+8801914061632","enam-cse@sust.edu","Room No: 119"));
         contactsList.add(new Contacts("Asst. Professor","Mr. Summit Haque","+8801521460106","summithaque@gmail.com","Room No: 124"));
         contactsList.add(new Contacts("Lecturer","Arnob Sen Sharma","+8801752833557","arnab-cse@sust.edu","Room No: 214"));
         contactsList.add(new Contacts("Lecturer","Mr. Maruf Ahmed Mridul","+8801837363289","mridul-cse@sust.edu","Room No: 213"));
         **/
    }

}