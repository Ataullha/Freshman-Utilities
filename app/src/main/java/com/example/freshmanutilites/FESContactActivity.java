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

public class FESContactActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Contacts> contactsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fescontact);

        recyclerView = findViewById(R.id.recyclerViewFESContact);



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

        InputStream is = this.getResources().openRawResource(R.raw.fes_contact);

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
        String fes_contact[] = contact_data.split("~~~~");
        //String fes_contact[] = {"Professor~Dr Narayan Saha   ~880-821-713491~nsaha12010-fes@sust.edu, nsaha2002@yahoo.co.in","Professor~Dr Md Qumruzzaman Chowdhury~01734161326~qumrul@gmail.com; qumrul-for@sust.edu","Professor~Dr. A. Z. M. Manzoor Rashid~01711302555; 880-821-714479~pollen_forest@yahoo.com ; rashid-fes@sust.edu","Professor & Head~Dr. Mohammad Belal Uddin~880-821-713491 (Extn.605)~belal405@yahoo.com, belal-for@sust.edu","Professor~Dr. Romel Ahmed~88-01874934514~romel-fes@sust.edu,  romelahmed76@yahoo.com","Professor~Dr. Mohammad Redowan~ Mob: +8801818465634~redowansust@gmail.com, redowan-for@sust.edu","Professor~Dr. Mizanur Rahman~Tel: +880-821-713491 (extn. 2888)~mizan-for@sust.edu, mizanfes@yahoo.com","Professor~Dr. Swapan Kumar Sarker~ 01791069892~swapan-fes@sust.edu; swapan_sust@yahoo.com","Professor~Dr. Mohammed Abu Sayed Arfin Khan~ Mobile: 0088 01917174537~khan-for@sust.edu, nobelarfin@yahoo.com","Professor~Dr. Kazi Mohammad Masum~ 01816052842~kmmasum-for@sust.edu; kmmasum@gmail.com","Associate Professor~Dr Farzana Raihan~ 01712207882~fraihan-for@sust.edu","Associate Professor~Dr. Mahmuda Islam~Tel: +880-821-713491 (extn. 2889)~mahmuda-fes@sust.edu, mahmudafor@yahoo.com","Assistant Professor~Md Abdul Halim~ 01714078386~halim10-fes@sust.edu","Assistant Professor~Mohammad Golam Kibria~ 01716627144~kibria_sust29@yahoo.com; kibria-fes@sust.edu","Assistant Professor~Mahmuda Sharmin ~ 01671635120~benu.mahmuda@gmail.com","Assistant Professor~Fahmida Sultana~ 01911720167~fahmida-fes@sust.edu, fahmida_nilukhan@yahoo.com","Assistant Professor~Sourav Das  ~ 01711348630~sourav_fes@sust.edu ; souravdron@gmail.com","Assistant Professor~Nusrat Islam  ~88-01717113566~nusratislam06@gmail.com","Assistant Professor~Sontosh Kumar Deb  ~ 88-01763880605~sontosh-fes@sust.edu   ,  sontosh.kdeb@gmail.com","Assistant Professor~Dr Mohammed Masum Ul Haque~+8801748714848~masum-fes@sust.edu","Assistant Professor~Anup Datta~+880-1786010404~dattaanup-fes@sust.edu , anupdattasust44@gmail.com","Assistant Professor~Md Saifuzzaman Bhuiyan~+8801628598829~bhuiyan09-fes@sust.edu, bhuiyan1989@yahoo.com","Assistant Professor~Abdur Rakib Bhuiyan~Cell: +880-1916937563~a.uzzal88-fes@sust.edu; a.uzzal88@gmail.com","Lecturer~Rabeya Sultana~+01672229303~rabeya02-fes@sust.edu; sultanafes.sust@gmail.com","Lecturer~Rahela Khatun~Phone: 880-821-713491 (Extn.273)~rahelashupi@gmail.com; rahela-fes@sust.edu        "};
        for(String contact:fes_contact){
            //Toast.makeText(getApplicationContext(), contact, Toast.LENGTH_SHORT).show();
        String infos[] = contact.split("~~");
            //Toast.makeText(getApplicationContext(), infos[0], Toast.LENGTH_SHORT).show();
            contactsList.add(new Contacts(infos[0]+"",infos[1]+"",infos[2]+"",infos[3]+"","N/A"));
        }
    }

}