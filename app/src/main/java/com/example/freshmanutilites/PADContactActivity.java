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

public class PADContactActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Contacts> contactsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_padcontact);

        recyclerView = findViewById(R.id.recyclerViewPADContact);



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

        InputStream is = this.getResources().openRawResource(R.raw.pad_contact);

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
        String pad_contact[] = contact_data.split("~~~~");
        //String pad_contact[] = {"Professor~Dr. Mohammad Shafiqul Islam~ 88-01719287907~shafiq.pad@gmail.com, shafiq-pad@sust.edu","Professor~Anwara Begum~ 01715357406~anwara_begum@yahoo.com","Professor~Dr Shamima Tasnim~ 01674008089~shammeepad@gmail.com","Associate Professor~Fatema Khatun~ 01713063721~fatemapa@yahoo.com","Associate Professor~Md Assraf Seddiky~01716152809~mdassrafseddiky@gmail.com","Associate Professor~Chowdhury Abdullah Al-Hossienie~ 01718376074~hossienie-pad@sust.edu, hossienie@yahoo.com","Associate Professor~Esmat Ara~ 01719787133~ara_esmat@yahoo.com","Associate Professor~Mohammad Samiul Islam  ~88 01716072978~samiul-pad@sust.edu","Associate Professor~Mohammad Shahjahan Chowdhury Ph. D. ~  8801775282290~shahjahan-pad@sust.edu , shahjahansust.chowdhury@gmail.com ","Assistant Professor~Mohammad Nashir Uddin~01912250617~nashir.pad@gmail.com, ","Assistant Professor~Kanij Fatema                   ~ 01717173614~kaniz_nipa@yahoo.com","Assistant Professor~Md Mahmud Hasan           ~ 01712967567~mahmud81.hasan@gmail.com","Assistant Professor~Fakhrul Islam                    ~01710186064~f.islam@uqconnect.edu.au or fakhrul-pad@sust.edu","Assistant Professor~Mrs Sabina Yasmin~88-01921839667~syasminpad14@gmail.com , sabina14-pad@sust.edu","Assistant Professor~Mr. Muhammad Mustofa Kamal~8801911089896~mustofa-pad@sust.edu, mustafapadsust@gmail.com","Assistant Professor~Mrs Jobayda Gulshan Ara~880-1670734092~jobayda14-pad@sust.edu ,  jobayda14@gmail.com"};
        for(String contact:pad_contact){
            //Toast.makeText(getApplicationContext(), contact, Toast.LENGTH_SHORT).show();
            String infos[] = contact.split("~~");
            //Toast.makeText(getApplicationContext(), infos[0], Toast.LENGTH_SHORT).show();
            contactsList.add(new Contacts(infos[0]+"",infos[1]+"",infos[2]+"",infos[3]+"","N/A"));
        }
    }

}