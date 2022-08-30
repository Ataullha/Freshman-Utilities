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

public class PHYContactActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Contacts> contactsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phycontact);

        recyclerView = findViewById(R.id.recyclerViewPHYContact);



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

        InputStream is = this.getResources().openRawResource(R.raw.phy_contact);

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
        String phy_contact[] = contact_data.split("~~~~");
        //String phy_contact[] = {"Professor~Dr Syed Badiuzzaman Faruque  ~ 01912711594~awsbf62@yahoo.com ","Professor~Dr Shumsun Naher Begum~01711065187~shumsun-phy@sust.edu, shumsun_phy@yahoo.com","Professor~Dr Nazia Chawdhury     ~ Cell: +8801733905271~nc-phy@sust.edu, nazia1971@yahoo.com","Professor~Dr Abdul Hannan~Cell 01712979269~ahannan-phy@sust.edu, ahannan.phy@gmail.com","Professor~Dr. Md. Shah Alam~ +8801718440675~ shahalam032003@yahoo.com","Professor~Professor Dr. Mohammed Sujaul Haque Chowdhury ~+8801711392244~schowdhuryphy@yahoo.com, s.chowdhury-phy@sust.edu","Professor~Dr Sharif Md Sharafuddin   ~ 01716308849~sharif-phy@sust.edu","Professor~Dr Sakhawat Hossain~01711301779~shossain-phy@sust.edu","Professor~Dr Mohammad Delawar Hossain ~713850 Ext. 2480. Cell 01826023793.~mdhphy@yahoo.com","Professor~ Dr Mst Khurshida Begum   ~ 88-01726769787~china@sust.edu, chinasust@yahoo..com ","Associate Professor~Subarna Soheli   ~01711478100~subar-phy@sust.edu","Associate Professor~Elhamul Hai~01731500184~elhai@sust.edu","Associate Professor~Dr Sarwat Binte Rafiq~01911529287~sarwat_phy@sust.edu, sarwat07@gmail.com","Associate Professor~Muhammad Omar Faruk~01922333435~ofaruk_sust@yahoo.com ","Associate Professor~Anock Somadder~Phone: +8801710850528~anock-phy@sust.com","Associate Professor~Dr Md Enamul Hoque~ Mobile: +8801719277759~mjonyh@gmail.com, mjonyh-phy@sust.edu","Assistant Professor~Tanvir Ahmed~01736952151~tahmed-phy@sust.edu ","Assistant Professor~Ponkog Kumar Das~ Cell Phone:  01718223554~pkdas-phy@sust.edu, ponkog.sust@sust.edu","Assistant Professor~Mrs. Nujhat Nuri Sultana~+88-01818936905~nujhatnuri2014@gmail.com","Assistant Professor~Jaseer Ahmed~+8801717266867~jaseer-phy@sust.edu","Assistant Professor~Shahadat Hossain~+8801717990246~shahadat-phy@sust.edu","Assistant Professor~Sajib Kumar Mohonta~+8801515241711~skmohonta-phy@sust.edu","Assistant Professor~SADIA KHANAM~01836526910~sadia-phy@sust.edu, safaphy38@gmail.com","Professor~Dr M Habibul Ahsan   ~ 01712530871~h.ahsan@sust.edu","Professor~Dr Yasmeen Haque~  01711339435~yasmeen@sust.edu","Professor~Dr M A Hye Chowdhury~01912711594~hye.chowdhury@manchester.ac.uk","Professor~Dr Susanta Kumar Das~01711436173~skdas-phy@sust.edu","Professor~Dr Mahfuza Ahmed~01711436173~mahfuza.ahmed@manchester.ac.uk"};
        for(String contact:phy_contact){
            //Toast.makeText(getApplicationContext(), contact, Toast.LENGTH_SHORT).show();
            String infos[] = contact.split("~~");
            //Toast.makeText(getApplicationContext(), infos[0], Toast.LENGTH_SHORT).show();
            contactsList.add(new Contacts(infos[0]+"",infos[1]+"",infos[2]+"",infos[3]+"","N/A"));
        }
    }

}