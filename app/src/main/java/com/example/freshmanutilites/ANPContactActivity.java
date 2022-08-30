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

public class ANPContactActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Contacts> contactsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anpcontact);

        recyclerView = findViewById(R.id.recyclerViewANPContact);



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

        InputStream is = this.getResources().openRawResource(R.raw.anp_contact);

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
        String anp_contact[] = contact_data.split("~~~~");
       // String anp_contact[] = {"Professor~Dr Abdul Awwal Biswas       ~ 01711966412~awwal_biswas@yahoo.com","Professor~A K M Mazharul Islam~ 8801711346909~akmmislam@gmail.com, mazharul-anp@sust.edu","Professor & Head~Dr. Nur Mohammad Majumder~+8801815107547~majumder.sustedu@gmail.com","Professor~afm zakaria ~ 8801756464646  @ZakiAfm~afmanp@gmail.com, zakaria-anp@sust.edu","Professor~Md. Mokhlesur Rahman    ~ 01712100255~mokhles_0430@yahoo.com","Associate Professor~Dr. Choudhury Farhana Jhuma~01711329373~farhanajhuma@yahoo.com","Associate Professor~Zafrin Ahmed Liza~+8801841666387~ahmedliza@gmail.com, liza-anp@sust.edu","Assistant Professor~Amina Khatun~01712843797~akhatun6july@yahoo.com","Assistant Professor~Halima Akhter~880-821-714479-270~halima-anp@sust.edu","Assistant Professor~Sanjay Krishno Biswas ~+880-821-714479 (Ext. 270/453)~sanjay-anp@sust.edu","Assistant Professor~ Moni Paul~+8801712560411~moni.paul.2008@gmail.com, monipaul-anp@sust.edu","Assistant Professor~Md Shahgahan Miah~ +8801813910608~shahgahan-anp@sust.edu, shahgahan81@gmail.com","Assistant Professor~Mohammed Javed Kaisar Ibne Rahman ~01711588023~jvdkaisar-anp@sust.edu, javedkaiser555@gmail.com","Assistant Professor~Korima Begum~ 01712814839~korimahe@yahoo.com","Assistant Professor~ Dr. Mohammad Monjur-Ul-Haider  ~ 717850/270           Cellphone: 01711960288~monjur-anp@sust.edu   ","Assistant Professor~Muhammad Salim Miah~01710185538~muhammadsalim-anp@sust.edu","Lecturer~Navila Kawser~+880-1720077077~nkawser-anp@sust.edu, Skype: navila.kawser","Lecturer~Subarna Nandi Majumdar~01749929444~subarnanm-anp@sust.edu , subarna.sust444@gmail.com"};
        for(String contact:anp_contact){
            //Toast.makeText(getApplicationContext(), contact, Toast.LENGTH_SHORT).show();
            String infos[] = contact.split("~~");
            //Toast.makeText(getApplicationContext(), infos[0], Toast.LENGTH_SHORT).show();
            contactsList.add(new Contacts(infos[0]+"",infos[1]+"",infos[2]+"",infos[3]+"","N/A"));
        }
    }

}