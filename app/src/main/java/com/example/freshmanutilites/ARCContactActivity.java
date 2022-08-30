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

public class ARCContactActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Contacts> contactsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arccontact);

        recyclerView = findViewById(R.id.recyclerViewARCContact);



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

        InputStream is = this.getResources().openRawResource(R.raw.arc_contact);

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
        String arc_contact[] = contact_data.split("~~~~");
        //String arc_contact[] = {"Professor~Md. Mustafizur Rahman, PhD~2862~mustafiz-arc@sust.edu","Associate Professor~Iftekhar Rahman~01711120218~ifti48buet@gmail.com, ifti48buet-arc@sust.edu","Associate Professor~Kawshik Saha~01712852564~kawshik-arc@sust.edu ; kawshik.saha@gmail.com","Associate Professor~K Taufiq Elahi ~01786128656~ktaufiqelahi@yahoo.com, elahi-arc@sust.edu","Associate Professor~Mohammad Shamsul Arefin ~+8801719482862~msan_arch-arc@sust.edu","Associate Professor~Mohammad Tanvir Hasan~01676710551~tanvir-arc@sust.edu","Assistant Professor~Shubhajit Chowdhury ~01751731166~shubha_arch@yahoo.com","Assistant Professor~Subrata Das~01719334997~ar.subrata@gmail.com, ar.subrata-arc@sust.edu ","Assistant Professor~Hossain Mohammad Nahyan	~+8801778670733~ar.nahyan@gmail.com","Assistant Professor~Shahidul Islam~+8801748553090~ar.shahidulislam@gmail.com, shahidul-arc@sust.edu","Assistant Professor~Gourpada Dey~01717362528~gourpadadey-arc@sust.edu,  ar.robindey@gmail.com","Assistant Professor~Rupak Dash~88 01768-000999~ar.rupak.sust@gmail.com","Assistant Professor~Shahla Safwat Ravhee~88 01675868645~rvi.arc@gmail.com ","Assistant Professor~Sazdik Ahmed~+8801722443733~sazdik-arc@sust.edu, saz.rki@gmail.com","Assistant Professor~Abhijit Mazumdar~+8801710230265~abhijit-arc@sust.edu , subhoarch@gmail.com","Assistant Professor~Zannat Ara Dilshad Shangi ~+8801682126614~ar.shangi-arc@sust.edu , ar.shangi.zannat@gmail.com","Lecturer~Md Arifur Rahman~+8801674302536~ar.kaushik-arc@sust.edu"};
        for(String contact:arc_contact){
            //Toast.makeText(getApplicationContext(), contact, Toast.LENGTH_SHORT).show();
            String infos[] = contact.split("~~");
            //Toast.makeText(getApplicationContext(), infos[0], Toast.LENGTH_SHORT).show();
            contactsList.add(new Contacts(infos[0]+"",infos[1]+"",infos[2]+"",infos[3]+"","N/A"));
        }
    }

}