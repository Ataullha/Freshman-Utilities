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

public class GEBContactActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Contacts> contactsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gebcontact);

        recyclerView = findViewById(R.id.recyclerViewGEBContact);



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

        InputStream is = this.getResources().openRawResource(R.raw.geb_contact);

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
        String geb_contact[] = contact_data.split("~~~~");
        //String geb_contact[] = {"Professor~Dr Md Abul Kalam Azad~01715160921~dakazad-btc@sust.edu/ dakazad@yahoo.com","Professor~Md Shamsul Haque Prodhan, PhD ~ 01735948380~shamsulhp-btc@sust.edu/ shamsulhp@gmail.com","Professor~Dr S M Abu Sayem~01717082520~asayem08@yahoo.com","Professor~Dr Md Kamrul Islam~+880-821-713491-714479/713850/717850 Ext. 2618~kamrul-gen@sust.edu/kamu_islamm@yahoo.com","Professor~Dr Md Abdullah Al Mamun ~ 01714516919~mssohel@yahoo.com; mamamun-geb@sust.edu","Professor~Dr. Md. Faruque Miah~ 01712865321~faruque-btc@sust.edu","Professor~Dr Mohammad Jakir Hosen~ Mobile: +08801777195857~jakir-gen@sust.edu, jakir_gen@yahoo.com","Professor~Dr. Md Jahangir Alam~+8801911939040~jalambioru@yahoo.com; jahangir-btc@sust.edu","Professor~Dr. Mohammad Zahangir Alam ~+8801712121397~mzalamgen@yahoo.com, mzalamgen@gmail.com, mzalam-geb@sust.edu","Professor~Dr. Asif Iqbal~01717552542~asif-gen@sust.edu/asif_abg@yahoo.com","Professor~Md Asraful Jahan~01911825536~smajahan@yahoo.com","Professor~Md Shakhinur Islam Mondal, PhD~01787964757~shakhin2000@yahoo.com,  shakhin200-gen@sust.edu","Professor~Dr. Md Ashrafuzzaman~+8801304189111~azamanbt@gmail.com or azamangeb-gen@sust.edu","Associate Professor~Dr. Gokul Chandra Biswas~+8801787114414~gcbiswas-geb@sust.edu / nilnil238@gmail.com","Associate Professor~Anindita Chakraborty, PhD~01785767517~anindita-geb@sust.edu, aninditamoury@yahoo.com","Associate Professor~Md Jahangir Alam, PhD~+8801919318542~jahangir-geb@sust.edu, mja.gebsust@yahoo.com","Associate Professor~Zobada Kanak Khan~01753882274~zkkhangeb@yahoo.com","Assistant Professor~Md Javed Foysal~01717389379~mjfoysal-geb@sust.edu faisalron04@gmail.com","Assistant Professor~Md Hazrat Ali~01716299245~hazratsust05@gmail.com","Assistant Professor~Md Hammadul Hoque ~+8801717999459~hammadul-geb@sust.edu","Assistant Professor~Sabrina Suhani~01717282758~sabrinasuhani@gmail.com, suhani-geb@sust.edu","Assistant Professor~Ziaul Faruque Joy~01674951523~zfjoy-geb@sust.edu, ","Assistant Professor~Mr Md Toasin Hossain Aunkor~01628752727~aunkorgeb@gmail.com /  toasin-geb@ sust.edu","Assistant Professor~Asif Mahmud~01723154515~asif-geb@sust.edu","Assistant Professor~Md. Akkas Ali ~+8801723866645~akkas-geb@sust.edu, akkasali.sust@gmail.com; Website: https://sites.google.com/sust.edu/hridoy/home","Assistant Professor~G. M. Nurnabi Azad Jewel~+8801712404214~najewel-geb@sust.edu"};
        for(String contact:geb_contact){
            //Toast.makeText(getApplicationContext(), contact, Toast.LENGTH_SHORT).show();
            String infos[] = contact.split("~~");
            //Toast.makeText(getApplicationContext(), infos[0], Toast.LENGTH_SHORT).show();
            contactsList.add(new Contacts(infos[0]+"",infos[1]+"",infos[2]+"",infos[3]+"","N/A"));
        }
    }

}