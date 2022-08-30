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

public class ENGContactActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Contacts> contactsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_engcontact);

        recyclerView = findViewById(R.id.recyclerViewENGContact);



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

        InputStream is = this.getResources().openRawResource(R.raw.eng_contact);

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
        String eng_contact[] = contact_data.split("~~~~");
        //String eng_contact[] = {"Professor~Dr Himadri Sekhar Roy             ~ Cell- 01719266004~roy-eng@sust.edu","Professor~Dr Muhammad Alamgir Toimoor~ 01718337348~toimoor@sust.edu","Professor & Head~Dr. Hossain Al Mamun~2630  +8801711987266~almamun-eng@sust.edu    profham.sust@gmail.com","Professor~Shahnaz Mahmud~01716254923~sm-eng@sust.edu","Professor~Shareefa Yasmeen~ 01912973288~sm-eng@sust.edu","Professor~Md Sikandar Ali~ 01716479311~sikandar.eng@gmail.com, sikandar-eng@sust.edu","Associate Professor~Dr Afruza Khanom~01711824779~afruzak-eng@sust.edu   ;    afruzak.eng@gmail.com","Associate Professor~Mohammad Rajik Miah  ~ 01675848663~profrajik@yahoo.com","Associate Professor~Dr Mohammad Shafiqul Islam~+8801712282136~msislam-eng@sust.edu or msijewel@gmail.com ","Associate Professor~Md Ishrat Ibne Ismail     ~ 01717021650~ishrat384-eng@sust.edu; mismai29@uwo.ca","Associate Professor~Mizanur Rahman~ 01557308871~mr.sust@gmail.com","Associate Professor~Sayeef Ahmed                 ~ 01712574588~sayeefahmed@yahoo.com, sayeef72@gmail.com and ahmed-eng@sust.edu","Assistant Professor~Panna Majumder              ~ 01714548798~majumder81-eng@sust.edu, majumderpanna1981@gmail.com","Assistant Professor~Rokeya Begum ~01716466067~rokeya1212-eng@sust.edu","Assistant Professor~Sahelee Parveen Dipa~01717896188~sahelee.engsust@gmail.com","Assistant Professor~Talukdar Mohammad Misbah Uddin~01712303517~misbah-eng@sust.edu","Assistant Professor~Mrs. Shahrin Fardous~01627519231~fshahrin-eng@sust.edu","Assistant Professor~Md Abu Hena Pohil~01723048735~ahp-eng@sust.edu","Assistant Professor~Zarin Tasnim~01704604397~z.shreyoshi@gmail.com, tasnim-eng@sust.edu","Assistant Professor~Mahabuba Rahman~01719364773~mahabubarahman.bdedu@gmail.com","Assistant Professor~Noor-E-Jannat Meem~+8801962567884~jannatnoor-eng@sust.edu or jannatmeem76@gmail.com","Assistant Professor~Sumaiya Ahmed~01521554009~sumaiya-eng@sust.edu","Assistant Professor~Tasnia Mizan Chowdhury~+8801755045452~tasniachowdhury611@gmail.com","Lecturer~Khadizatul Kobra Urmy~01765-548329~urmykk2469@gmail.com / urmykk-eng@sust.edu","Lecturer~Mazid-Ul-Hasan~ 01868519233~mazid.hasan19@gmail.com, mazidul-eng@sust.edu","Professor~Dr Md Ati Ullah~ 01677312058~mauenglish@yahoo.com"};
                for(String contact:eng_contact){
            //Toast.makeText(getApplicationContext(), contact, Toast.LENGTH_SHORT).show();
            String infos[] = contact.split("~~");
            //Toast.makeText(getApplicationContext(), infos[0], Toast.LENGTH_SHORT).show();
            contactsList.add(new Contacts(infos[0]+"",infos[1]+"",infos[2]+"",infos[3]+"","N/A"));
        }
    }

}