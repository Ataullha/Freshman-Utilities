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

public class OCGContactActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Contacts> contactsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocgcontact);

        recyclerView = findViewById(R.id.recyclerViewOCGContact);



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

        InputStream is = this.getResources().openRawResource(R.raw.ocg_contact);

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
        String ocg_contact[] = contact_data.split("~~~~");
        //String ocg_contact[] = {"Assistant Professor & Head~Dr. Subrata Sarker~ 01312417753~subratasrk-ocg@sust.edu ","Assistant Professor~Md. Ahsanul Islam~01920653592~ahsanislam-ocg@sust.edu, ahsanimsf@yahoo.com","Assistant Professor~Md. Solaiman Hossain~01920314841~solaiman_du@yahoo.com, solaiman-ocg@sust.edu","Assistant Professor~Morgina Akter~01728118762~m.akter-ocg@sust.edu, Aktermorgina87@gmail.com","Lecturer~Mahnaz Islam Sonia~+8801759453051~mahnaz1120@gmail.com","Lecturer~Muhammad Mizanur Rahman~01711231358~mmrahman-ocg@sust.edu","Lecturer~MD Shajjadur Rahman ~+8801839234261~shajjadshishir92@gmail.com","Lecturer~Abu Bokkar Siddique~01621492408~absiddique-ocg@sust.edu","Lecturer~Md. Azizul Fazal ~01674443839~mafazal-ocg@sust.edu ","Lecturer~Faisal Sobhan~01674232796~f.sobhan-ocg@sust.edu"};
        for(String contact:ocg_contact){
            //Toast.makeText(getApplicationContext(), contact, Toast.LENGTH_SHORT).show();
            String infos[] = contact.split("~~");
            //Toast.makeText(getApplicationContext(), infos[0], Toast.LENGTH_SHORT).show();
            contactsList.add(new Contacts(infos[0]+"",infos[1]+"",infos[2]+"",infos[3]+"","N/A"));
        }
    }

}