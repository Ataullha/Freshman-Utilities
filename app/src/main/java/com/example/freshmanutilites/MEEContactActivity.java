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

public class MEEContactActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Contacts> contactsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meecontact);

        recyclerView = findViewById(R.id.recyclerViewMEEContact);



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

        InputStream is = this.getResources().openRawResource(R.raw.mee_contact);

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
        String mee_contact[] = contact_data.split("~~~~");
        //String mee_contact[] = {"Assistant Professor~Md. Zahidul Islam ~01711161075~mzislam-mee@sust.edu","Assistant Professor~Md.	 Ferdous Alam ~+88-01718323266~nayan.toe@gmail.com; mfalam-mee@sust.edu","Assistant Professor~Nuruzzaman Sakib~01829685368~nzsakib-mee@sust.edu","Assistant Professor~Nafiza Anjum~01972225872~nafizapro-mee@sust.edu, promeenaa@gmail.com","Assistant Professor & Head~Md. Shafiqul Islam~+88-01743033430~msislam-mee@sust.edu","Assistant Professor~Mohd. Mahfuzur Rahman~+88-01789794895~mahfuzur984@gmail.com","Lecturer~Md. Mahmud-Or-Rashid~01758639844~mmrashid-mee@sust.edu","Lecturer~A K M Ashikuzzaman~01878939415~akmashik-mee@sust.edu","Lecturer~Md. Syamul Bashar ~01307486552~md.syamul-mee@sust.edu"};
        for(String contact:mee_contact){
            //Toast.makeText(getApplicationContext(), contact, Toast.LENGTH_SHORT).show();
            String infos[] = contact.split("~~");
            //Toast.makeText(getApplicationContext(), infos[0], Toast.LENGTH_SHORT).show();
            contactsList.add(new Contacts(infos[0]+"",infos[1]+"",infos[2]+"",infos[3]+"","N/A"));
        }
    }

}