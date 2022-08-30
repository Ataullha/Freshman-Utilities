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

public class CSEContactActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Contacts> contactsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csecontact);

        recyclerView = findViewById(R.id.recyclerViewCSEContact);



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

        InputStream is = this.getResources().openRawResource(R.raw.cse_contact);

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
        String cse_contact[] = contact_data.split("~~~~");
        //String cse_contact[] = {"Professor~Mohammad Shahidur Rahman, PhD, SMIEEE~+880-821-713491~rahmanms@sust.edu, rahmanms.bd@gmail.com","Professor~Dr Mohammad Reza Selim~/672~selim@sust.edu","Professor~M. Jahirul Islam, PhD., PEng.~+880-821- 713850/ 714479/ 717850 Ext. 2671 (O)~jahir-cse@sust.edu","Professor & Head~Mohammad Abdullah Al Mumin, PhD~88-01711445110~mumin-cse@sust.edu","Professor~Md Masum~88-01919736248~masum-cse@sust.edu","Professor~Dr. Farida Chowdhury~01743018917~deeba.bd@gmail.com","Professor~Dr. Md Forhad Rabbi, SMIEEE~+8801844175805 (WhatsApp)~frabbi-cse@sust.edu","Associate Professor~Husne Ara Chowdhury~88-01558304163~husne-cse@sust.edu","Associate Professor~Sadia Sultana~88-01558304163~sadia-cse@sust.edu","Assistant Professor~Mahruba Sharmin Chowdhury~88-01917566699~mahruba-cse@sust.edu , mahrubacse@gmail.com","Assistant Professor~Ayesha Tasnim~01713328269~tasnim-cse@sust.edu","Assistant Professor~Md Eamin Rahman~88-01677014633~eamin-cse@sust.edu","Assistant Professor~Md Saiful Islam~8801717960979~saiful-cse@sust.edu, saif.acm@gmail.com","Assistant Professor~Sheikh Nabil Mohammad ~8801678137313~nabil-cse@sust.edu, sknabil@gmail.com","Assistant Professor~Marium-E-Jannat~88-01727354101~jannat-cse@sust.edu,  jannat.16.11@gmail.com,  jannatcse@yahoo.com","Assistant Professor~Biswapriyo Chakrabarty ~01612300990~biswa-cse@sust.edu","Assistant Professor~Md Mahfuzur Rahaman~8801717831156~mahfuz-cse@sust.edu, mahfuzsustbd@gmail.com","Assistant Professor~Md Mahadi Hasan Nahid~+8801738150127~nahid-cse@sust.edu  || post2nahid@gmail.com ","Assistant Professor~Enamul Hassan~+8801914061632~enam-cse@sust.edu","Assistant Professor~MOQSADUR RAHMAN~+8801937012488~moqsad-cse@sust.edu","Assistant Professor~Summit Haque~01521460106~summit-cse@sust.edu; summit.haque@gmail.com","Lecturer~Arnab Sen Sharma~01752833557~arnab-cse@sust.edu, arnab.api@gmail.com","Lecturer~Maruf Ahmed Mridul~01837363289~mridul133@gmail.com, mridul-cse@sust.edu","Professor~Dr Muhammed Zafar Iqbal~880-821- 713850~mzi@sust.edu"};
        for(String contact:cse_contact){
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