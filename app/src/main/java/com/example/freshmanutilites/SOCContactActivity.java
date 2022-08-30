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

public class SOCContactActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Contacts> contactsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soccontact);

        recyclerView = findViewById(R.id.recyclerViewSOCContact);



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

        InputStream is = this.getResources().openRawResource(R.raw.soc_contact);

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
        String soc_contact[] = contact_data.split("~~~~");
        //String soc_contact[] = {"Professor~Dr Md Abdul Ghani      ~ 01919218933~maghani_sust@yahoo.com","Professor~Dr Mohammad Jasim Uddin~88-01715055869~mjauddin@yahoo.com","Professor~Dr. Laila Ashrafun~88-01716222070~as_joya@yahoo.com    ","Professor~Dr A H M Belayeth Hussain    ~01794342771~belayeth-soc@sust.edu","Professor~Mohammed Faruque Uddin~~faruq-soc@sust.edu","Professor & Head~Tanzina Choudhury~8801625211381~tanzina-soc@sust.edu","Professor~Dr. Md Al-Amin        ~01716072054~al_aminaub@yahoo.com","Professor~Dr Shah Md Atiqul Haq ~ 713850 Ext. 293; Mob: 88-01766070570~shahatiq1@yahoo.com","Professor~Mohammed Anwar Hossain~01757343617~hossainsoc@yahoo.com","Professor~Dr. Mohammad Morad       ~ 713850; :88-01816504210~moradsust@yahoo.com, morad-soc@sust.edu","Associate Professor~Mohammad Mostufa Kamal     ~01756059938~mostufa-soc@sust.edu","Associate Professor~Al Amin Rabby~ 713850; EXT-755 : Cell: +88-01717468444~alaminrabby@yahoo.com  And  alaminrabby-soc@sust.edu","Associate Professor~Mahed-Ul-Islam Choudhury	~01724795224~mahedsust@gmail.com","Associate Professor~Nadia Haque~01726449858<~mahedsust@gmail.com","Associate Professor~Sebak Kumar Saha~01711267835~sebak.kumar@gmail.com","Associate Professor~Iqbal Ahmed Chowdhury~01675288027~iqbal_chy@yahoo.com","Assistant Professor~Nikhilendu Deb~01712509710~nikhil-soc@sust.edu","Assistant Professor~Sumena Sultana               ~01717431701~sumena_sultana@yahoo.com","Assistant Professor~Sheikh Nasrin Haque               ~+8801717589606~sknhaque-soc@sust.edu","Assistant Professor~Nazia Zabin~+8801717589606~sknhaque-soc@sust.edu","Assistant Professor~Mr Mohammad Mojammel Hussain Raihan~01712064141~raihan-soc@sust.edu","Assistant Professor~Ashis Kumer Banik~01636023886~ashisb-soc@sust.edu","Assistant Professor~Marriya Sultana~01920100996~marriya_soc@yahoo.com","Assistant Professor~Mohammad Maniruzzaman Khan~01783804772~shohag99sust@gmail.com","Lecturer~Avijit Chakrabarty Ayon~01783804772~shohag99sust@gmail.com","Lecturer~Tania Jannatul Kobra~01783804772~shohag99sust@gmail.com","Lecturer~Sanjida Sultana Zerin~01783804772~sszerin-soc@sust.edu","Professor~Dr Kamal Ahmed Chowdhury~ 01819657474~kac_sociology@yahoo.com"};
        for(String contact:soc_contact){
            //Toast.makeText(getApplicationContext(), contact, Toast.LENGTH_SHORT).show();
            String infos[] = contact.split("~~");
            //Toast.makeText(getApplicationContext(), infos[0], Toast.LENGTH_SHORT).show();
            contactsList.add(new Contacts(infos[0]+"",infos[1]+"",infos[2]+"",infos[3]+"","N/A"));
        }
    }

}