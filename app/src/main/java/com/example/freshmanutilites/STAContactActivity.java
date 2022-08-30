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

public class STAContactActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Contacts> contactsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stacontact);

        recyclerView = findViewById(R.id.recyclerViewSTAContact);



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

        // input stream for input
        InputStream is = this.getResources().openRawResource(R.raw.sta_contact);
        // buffered reader
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
        // string to string array
        String sta_contact[] = contact_data.split("~~~~");
        //String sta_contact[] = {"Professor~Dr. Md. Kabir Hossain~+8801711184818~khossain.sust@gmail.com","Professor~Dr. Md Zakir Hossain   ~01711140801~mzhossain.bds@gmail.com; mzh-sta@sust.edu","Professor~Dr Sabina Islam~ 01911720525 (Cell)~profsabinaislam@gmail.com / sabina-sta@sust.edu","Professor~Dr Ahmad Kabir~ 01711116908~akabir_sta@yahoo.com","Professor~Md. Ahmed Kabir Chowdhury~ 01714227456~makc@sust.edu; makc-sust@live.com","Professor & Head~Dr Rahmat Ali~ 01552438862~ralistat@sust.edu","Professor~Dr. Md. Azizul Baten~+8801716262947~baten-sta@sust.edu, baten_math@yahoo.com","Professor~Dr. Mohammed Taj Uddin~ +8801716348194~mtajstat@yahoo.com","Professor~Dr Mohammad Shahidul Islam~ 01777827876~shahed.stat@gmail.com","Professor~Dr Md Nazrul Islam~01711466280~nazrul330@yahoo.com, nazrul-sta@sust.edu","Professor~Dr. S. M. Khurshid Alam~+880-1716090465~khurshid@sust.edu","Professor~Dr. Mohammad Ohid Ullah~ Mobile: +88-01818101435~ohid-sta@sust.edu, ohidullah@gmail.com","Professor~Dr. Khalidur Rahman~ 01712068785~khalid_sust@yahoo.com; khalid-sta@sust.edu","Professor~Dr. Md Jamal Uddin~+8801716972846 (private)~jamal-sta@sust.edu, jamalsust@gmail.com","Professor~Dr. Luthful Alahi Kawsar~ 01611318223~lakawsar-sta@sust.edu, lakawsar@gmail.com ","Associate Professor~Sumonkanti Das, PhD~+8801818824282~sumonkanti-sta@sust.edu","Associate Professor~Mohammad Romel Bhuia~+8801782728082~mr.bhuia@sust.edu | romel_stat@yahoo.com","Associate Professor~Mossamet Kamrun Nesa~+8801796747485~mknesa-sta@sust.edu","Associate Professor~Dr. Mirza Nazmul Hasan~+8801767013458~hasanmirza-sta@sust.edu; mirzanazmulhasan@gmail.com","Assistant Professor~ Mirajul Islam~01675018522~miraj.isrt10@gmail.com","Assistant Professor~Dr.  Kanis Fatama Ferdushi~01818824338~kanisusm@gmail.com, kanis-sta@sust.edu","Assistant Professor~Sabbir Tahmidur Rahman~ 01717340857~str-sta@sust.edu, sabbi_01@yahoo.com ","Assistant Professor~Nahid Sultana~ 88-01728707307~nahid-sta@sust.edu, nahids@rocketmail.com","Professor~Emad Uddin Choudhury~ 01720260321~ "};

    for(String contact:sta_contact){
            //Toast.makeText(getApplicationContext(), contact, Toast.LENGTH_SHORT).show();
            String infos[] = contact.split("~~");
            //Toast.makeText(getApplicationContext(), infos[0], Toast.LENGTH_SHORT).show();
            contactsList.add(new Contacts(infos[0]+"",infos[1]+"",infos[2]+"",infos[3]+"","N/A"));
        }
    }

}

//sta_contact[] = {"Professor~Dr. Md. Kabir Hossain~+8801711184818~khossain.sust@gmail.com","Professor~Dr. Md Zakir Hossain   ~01711140801~mzhossain.bds@gmail.com; mzh-sta@sust.edu","Professor~Dr Sabina Islam~ 01911720525 (Cell)~profsabinaislam@gmail.com / sabina-sta@sust.edu","Professor~Dr Ahmad Kabir~ 01711116908~akabir_sta@yahoo.com","Professor~Md. Ahmed Kabir Chowdhury~ 01714227456~makc@sust.edu; makc-sust@live.com","Professor & Head~Dr Rahmat Ali~ 01552438862~ralistat@sust.edu","Professor~Dr. Md. Azizul Baten~+8801716262947~baten-sta@sust.edu, baten_math@yahoo.com","Professor~Dr. Mohammed Taj Uddin~ +8801716348194~mtajstat@yahoo.com","Professor~Dr Mohammad Shahidul Islam~ 01777827876~shahed.stat@gmail.com","Professor~Dr Md Nazrul Islam~01711466280~nazrul330@yahoo.com, nazrul-sta@sust.edu","Professor~Dr. S. M. Khurshid Alam~+880-1716090465~khurshid@sust.edu","Professor~Dr. Mohammad Ohid Ullah~ Mobile: +88-01818101435~ohid-sta@sust.edu, ohidullah@gmail.com","Professor~Dr. Khalidur Rahman~ 01712068785~khalid_sust@yahoo.com; khalid-sta@sust.edu","Professor~Dr. Md Jamal Uddin~+8801716972846 (private)~jamal-sta@sust.edu, jamalsust@gmail.com","Professor~Dr. Luthful Alahi Kawsar~ 01611318223~lakawsar-sta@sust.edu, lakawsar@gmail.com ","Associate Professor~Sumonkanti Das, PhD~+8801818824282~sumonkanti-sta@sust.edu","Associate Professor~Mohammad Romel Bhuia~+8801782728082~mr.bhuia@sust.edu | romel_stat@yahoo.com","Associate Professor~Mossamet Kamrun Nesa~+8801796747485~mknesa-sta@sust.edu","Associate Professor~Dr. Mirza Nazmul Hasan~+8801767013458~hasanmirza-sta@sust.edu; mirzanazmulhasan@gmail.com","Assistant Professor~ Mirajul Islam~01675018522~miraj.isrt10@gmail.com","Assistant Professor~Dr.  Kanis Fatama Ferdushi~01818824338~kanisusm@gmail.com, kanis-sta@sust.edu","Assistant Professor~Sabbir Tahmidur Rahman~ 01717340857~str-sta@sust.edu, sabbi_01@yahoo.com ","Assistant Professor~Nahid Sultana~ 88-01728707307~nahid-sta@sust.edu, nahids@rocketmail.com","Professor~Emad Uddin Choudhury~ 01720260321};
//