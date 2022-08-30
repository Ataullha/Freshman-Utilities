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

public class GEEContactActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Contacts> contactsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geecontact);

        recyclerView = findViewById(R.id.recyclerViewGEEContact);



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

        InputStream is = this.getResources().openRawResource(R.raw.gee_contact);

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
        String gee_contact[] = contact_data.split("~~~~");
       // String gee_contact[] = {"Assistant Professor~Md Muyeed Hasan~01686580014~muyeedhasan-gee@sust.edu ","Assistant Professor & Head~Rony Basak~01715545225~rbasak-gee@sust.edu","Assistant Professor~Md. Anowarul Islam~8801722557791~anowar-gee@sust.edu ","Assistant Professor~Md Bahuddin Sikder   ~01714432134~bahuddinsikder-gee@sust.edu, bahuddinsikder@gmail.com","Assistant Professor~Nusrat Jahan Koley ~+08801829671957~nusrat-gee@sust.edu","Assistant Professor~Md. Tariqul Islam~01913-077326~rana_075_du@yahoo.co.in","Assistant Professor~Syeda Ayshia Akter~01616-807573~sayshaa@gmail.com","Assistant Professor~Zia Ahmed~+8801718978705~zia38env@gmail.com, ziaahmed-gee@sust.edu","Assistant Professor~Shetu Akter~+8801676586845~shetuakter-gee@sust.edu","Assistant Professor~Towfiqul Islam Khan~+88-01674330886~khan-gee@sust.edu, towfiq41ju@gmail.com","Assistant Professor~Shahnaj Shemul~01684652044~shahnajshemul10@gmail.com","Lecturer~Md. Najmul Kabir~01948878743~n.kabir-gee@sust.edu; md.najmul.gee.sust@gmail.com","Lecturer~Afruja Begum~+8801719450048~afruja-gee@sust.edu"};
        for(String contact:gee_contact){
            //Toast.makeText(getApplicationContext(), contact, Toast.LENGTH_SHORT).show();
            String infos[] = contact.split("~~");
            //Toast.makeText(getApplicationContext(), infos[0], Toast.LENGTH_SHORT).show();
            contactsList.add(new Contacts(infos[0]+"",infos[1]+"",infos[2]+"",infos[3]+"","N/A"));
        }
    }

}