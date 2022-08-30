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

public class PSSContactActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Contacts> contactsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psscontact);

        recyclerView = findViewById(R.id.recyclerViewPSSContact);



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

        InputStream is = this.getResources().openRawResource(R.raw.pss_contact);

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
        String pss_contact[] = contact_data.split("~~~~");
        //String pss_contact[] = {"Professor~Dilara Rahman~01917911550~mahfuza.ahmed@manchester.ac.uk","Professor~Dr S M Hasan Zakirul Islam~ 01712510914~hzakirul-psa@sust.edu","Professor & Head~Dr. Zayeda Sharmin~Cell: +880-1735562516; Land Phone: +880-821-716123~zsharmin-psa@sust.edu, zayeda_sharmeen@yahoo.co.in, zayeda.sharmin@gmail.com","Professor~Dr. Syed Ashrafur Rahman~+8801716563786~ashrafur-pss@sust.edu","Professor~Dr Md Nazrul Islam~+8801716563786~ashrafur-pss@sust.edu","Professor~ Dr. Mohammad Jahirul Hoque~+88(0)1972026516~jahirul-psa@sust.edu","Professor~Dr. Md Shahabul Haque~ 01711-234056~shahabu14@yahoo.com","Associate Professor~Dr Md Masud Sarker~ 01764280310~masud-psa@sust.edu","Associate Professor~Dr Fahmida Aktar~ 88-01717266201~dollysust_302@yahoo.com, fahmida-pss@sust.edu","Assistant Professor~Syeda Ismat Ara Jahan~ 01912977067~dollysust_302@yahoo.com, fahmida-pss@sust.edu","Assistant Professor~Md Amdadul Haque~ 01717414848~amdad-pss@sust.edu","Assistant Professor~Mohammad Fakhrus Salam~ 8801748919866~salam-pss@sust.edu","Assistant Professor~Hajera Aktar~ 01723326928~hajera_aktar@yahoo.com","Assistant Professor~Md Mahbub Alam~ 01714485619~nafismahbub@gmail.com, mahbub-pss@sust.edu","Assistant Professor~Mohammad Shakil Bhuiyan ~ 01718376448~shakil027-pss@sust.edu","Assistant Professor~Mst. Tahmina Akter~+8801764203306~tahmina-pss@sust.edu; mtahmina06@gmail.com","Assistant Professor~Abu Sufian~+8801921479970~sufian-pss@sust.edu,   shamrat08du@yahoo.com  "};
        for(String contact:pss_contact){
            //Toast.makeText(getApplicationContext(), contact, Toast.LENGTH_SHORT).show();
            String infos[] = contact.split("~~");
            //Toast.makeText(getApplicationContext(), infos[0], Toast.LENGTH_SHORT).show();
            contactsList.add(new Contacts(infos[0]+"",infos[1]+"",infos[2]+"",infos[3]+"","N/A"));
        }
    }

}
// pss_contact[] = {"Professor~Dilara Rahman~01917911550~mahfuza.ahmed@manchester.ac.uk","Professor~Dr S M Hasan Zakirul Islam~ 01712510914~hzakirul-psa@sust.edu","Professor & Head~Dr. Zayeda Sharmin~Cell: +880-1735562516; Land Phone: +880-821-716123~zsharmin-psa@sust.edu, zayeda_sharmeen@yahoo.co.in, zayeda.sharmin@gmail.com","Professor~Dr. Syed Ashrafur Rahman~+8801716563786~ashrafur-pss@sust.edu","Professor~Dr Md Nazrul Islam~+8801716563786~ashrafur-pss@sust.edu","Professor~ Dr. Mohammad Jahirul Hoque~+88(0)1972026516~jahirul-psa@sust.edu","Professor~Dr. Md Shahabul Haque~ 01711-234056~shahabu14@yahoo.com","Associate Professor~Dr Md Masud Sarker~ 01764280310~masud-psa@sust.edu","Associate Professor~Dr Fahmida Aktar~ 88-01717266201~dollysust_302@yahoo.com, fahmida-pss@sust.edu","Assistant Professor~Syeda Ismat Ara Jahan~ 01912977067~dollysust_302@yahoo.com, fahmida-pss@sust.edu","Assistant Professor~Md Amdadul Haque~ 01717414848~amdad-pss@sust.edu","Assistant Professor~Mohammad Fakhrus Salam~ 8801748919866~salam-pss@sust.edu","Assistant Professor~Hajera Aktar~ 01723326928~hajera_aktar@yahoo.com","Assistant Professor~Md Mahbub Alam~ 01714485619~nafismahbub@gmail.com, mahbub-pss@sust.edu","Assistant Professor~Mohammad Shakil Bhuiyan ~ 01718376448~shakil027-pss@sust.edu","Assistant Professor~Mst. Tahmina Akter~+8801764203306~tahmina-pss@sust.edu; mtahmina06@gmail.com","Assistant Professor~Abu Sufian~+8801921479970~sufian-pss@sust.edu,   shamrat08du@yahoo.com  "};////
