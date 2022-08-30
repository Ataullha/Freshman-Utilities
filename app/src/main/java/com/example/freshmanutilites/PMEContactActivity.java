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

public class PMEContactActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Contacts> contactsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pmecontact);

        recyclerView = findViewById(R.id.recyclerViewPMEContact);



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

        InputStream is = this.getResources().openRawResource(R.raw.pme_contact);

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
        String pme_contact[] = contact_data.split("~~~~");
        //String pme_contact[] = {"Professor~Dr. Md. Shofiqul Islam~PABX 0821-713491~shofiq-pme@sust.edu, sho_fiq@yahoo.com, shofiqpgeg","Professor~Dr. Md. Saiful Alam~+8801711954946~saifulraju-pme@sust.edu, saifulraju@yahoo.com ","Professor & Head~Dr. Muhammad Farhad Howladar ~/627~farhadpme@gmail.com; farhad-pme@sust.edu","Associate Professor~Mr Mohammed Omar Faruque~+8801819847124~faruque_pge@yahoo.com / faruque-pge@sust.edu","Associate Professor~Arifur Rahman~+8801717265093~arif-pmesust.edu, arifpme930gmail.com","Assistant Professor~Md. Jakaria~8801757092487~jkr-mpt@sust.edu, jakariapge@gmail.com","Assistant Professor~Mr Mohammad Shahedul Hossain~01712537886~shahed-pme@sust.edu/  shahedulhossain@gmail.com","Assistant Professor~Pulok Kanti Deb~880-821-713491~pulok-pme@sust.edu, pulokpme@gmail.com","Assistant Professor~A. T. M. Shahidul Huqe Muzemder~8801717502185~shahidul-pme@sust.edu","Assistant Professor~Md Ashraf Hussain~+8801726308479~ashrafpgesust@gmail.com, ashraf-pme@sust.edu","Assistant Professor~Labiba Nusrat Jahan~01675847390~labiba-pme@sust.edu;  labiba.nusrat@gmail.com","Assistant Professor~Md Sifat Tanveer~+8801737565073~sifat-pme@sust.edu","Assistant Professor~Sharmin Akter~+8801622264431<~sifat-pme@sust.edu","Assistant Professor~Mahamudul Hashan~01638835169~mahmud-pme@sust.edu;  mahmud.pme@gmail.com","Assistant Professor~Md. Abdullah Al Numanbakth~+8801743-007007~numanbakth@gmail.com; numan-pme@sust.edu","Assistant Professor~Majedul Islam Khan~+8801933709064~majedul-pme@sust.edu"};
        for(String contact:pme_contact){
            //Toast.makeText(getApplicationContext(), contact, Toast.LENGTH_SHORT).show();
            String infos[] = contact.split("~~");
            //Toast.makeText(getApplicationContext(), infos[0], Toast.LENGTH_SHORT).show();
            contactsList.add(new Contacts(infos[0]+"",infos[1]+"",infos[2]+"",infos[3]+"","N/A"));
        }
    }

}