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

public class BNGContactActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Contacts> contactsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bngcontact);

        recyclerView = findViewById(R.id.recyclerViewBNGContact);



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

        InputStream is = this.getResources().openRawResource(R.raw.bng_contact);

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
        String bng_contact[] = contact_data.split("~~~~");
        //String bng_contact[] = {"Professor~Dr Md Abdur Rahim~ 01712737022~rahimdr.abdur@yahoo.com","Professor~Dr Sharadindu Bhattacharjee  ~ 01731412679~drsharadindu.bhattacharjee@gmail.com","Professor~Dr. Md. Ashraful Karim      ~ 01711-327836~akarimbng@gmail.com","Professor & Head~Farjana Siddika~ 01731292222~farjanasiddika.sust@gmail.com","Professor~Dr Md Rejaul Islam~ 01718993900~rejaul-bng@sust.edu","Professor~Dr Md Foyzul Haque             ~01756852323~foyz-bng@sust.edu","Professor~Dr Shirin Akter Sarker~ 01711052772~shirin-bng@sust.edu","Professor~Nilufa Akter~ 01711013131~nilufa.akter_sust@yahoo.com ","Professor~Dr Md Zafir Uddin (Zafir Setu)~ 01712121741~zafirsetu@yahoo.com","Associate Professor~Dr. Md Zahangir Alam ~ 01713816034~zahangir.sust1988@gmail.com ","Assistant Professor~Masud Parvaj ~01717422167~parvajm@gmail.com","Assistant Professor~Sarker Sohel Rana ~01922452668~sarkerranadu@gmail.com","Assistant Professor~Md. Monirul Islam~ 01923137672~monirul.islam277@gmail.com & monirul-bng@sust.edu","Assistant Professor~Sanjoy Bikrom~ 01682277512~sanjoy1994-bng@sust.edu  Or,  sanjoybikrom@gmail.com","Assistant Professor~Md. Abu Baker Siddique~ 01723710693~abubaker-bng@sust.edu","Assistant Professor~Mst. Anjumonara Begum~01747047997~anjumonara-bng@sust.edu"};
        for(String contact:bng_contact){
            //Toast.makeText(getApplicationContext(), contact, Toast.LENGTH_SHORT).show();
            String infos[] = contact.split("~~");
            //Toast.makeText(getApplicationContext(), infos[0], Toast.LENGTH_SHORT).show();
            contactsList.add(new Contacts(infos[0]+"",infos[1]+"",infos[2]+"",infos[3]+"","N/A"));
        }
    }

}