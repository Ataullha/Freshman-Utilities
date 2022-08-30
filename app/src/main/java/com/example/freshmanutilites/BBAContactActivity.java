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

public class BBAContactActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Contacts> contactsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bbacontact);

        recyclerView = findViewById(R.id.recyclerViewBBAContact);



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

        InputStream is = this.getResources().openRawResource(R.raw.bba_contact);

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
        String bba_contact[] = contact_data.split("~~~~");
        //String bba_contact[] = {"Professor~Dr Md Nazrul Islam       ~ +88 01712817424~dnislam69@gmail.com,  dnislam969-bus@sust.edu  ","Professor~Dr. Mosaddak Ahmed Chowdhury ~ 01713300240~prof.dr.mosaddak.chowdhury@gmail.com     ","Professor & Head~Dr Mazharul Hasan Mazumder   ~ 01711445062~mazhar1973-bus@sust.edu, mazharmazumder@gmail.com","Professor~Dr Md Khairul Islam          ~01611127028 / 01711127028~khairuldba@gmail.com, khairuldba@sust.edu","Professor~Dr Md Monirul Islam   ~ 01717481319~monir-ban@sust.edu","Professor~Dr. Mohammad Shahidul Hoque   ~ 01972-638938~shahidul-ban@sust.edu","Professor~Dr. Md. Abdul Hamid           ~ Mobile Telephone: +8801716439408~mahamid-bus@sust.edu; mahamid.biz@gmail.com","Professor~Dr Fazle Elahi Md Faisal ~01819115018~fazle.faisal@yahoo.com","Professor~Dr. Mohammad Mizenur Rahaman~01716258962~mizen-ban@sust.edu; mizen.ban.sust@gmail.com; Orcid ID: 0000-0003-0266-3812","Professor~Dr Syed Mohammad Khaled Rahman~+8801721043123~smkld_sust@yahoo.com, kr15sust@gmail.com, khaledscc-bus@sust.edu","Associate Professor~Syed Towfiq Mahmood Hasan~01715018035~stmh1106@gmail.com   ","Associate Professor~Dr. Mohammad Ashraful Ferdous Chowdhury ~01912702643~ashraful_ferdous@yahoo.com, ashraf-ban@sust.edu","Associate Professor~Sobhana Tanzima Atiq~ 01715192713~statiq08@gmail.com   ","Assistant Professor~Dr Md Zillur Rahman~ 01716609814~zillur-bus@sust.edu, fuaddu@yahoo.com","Assistant Professor~Sakufa Chowdhury~01558301108~sakufa_jvr@yahoo.com","Assistant Professor~Tasmina Chowdhury Tania~01911293342~tasmina.tania@gmail.com","Assistant Professor~Saleh Ahmad Abdullah~01552428098<~tasmina.tania@gmail.com","Assistant Professor~Sayma Sadia Shawon~+8801793596767~shawonsrahman@gmail.com"};
        for(String contact:bba_contact){
            //Toast.makeText(getApplicationContext(), contact, Toast.LENGTH_SHORT).show();
            String infos[] = contact.split("~~");
            //Toast.makeText(getApplicationContext(), infos[0], Toast.LENGTH_SHORT).show();
            contactsList.add(new Contacts(infos[0]+"",infos[1]+"",infos[2]+"",infos[3]+"","N/A"));
        }
    }

}