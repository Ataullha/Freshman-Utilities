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

public class SCWContactActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Contacts> contactsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scwcontact);

        recyclerView = findViewById(R.id.recyclerViewSCWContact);



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

        InputStream is = this.getResources().openRawResource(R.raw.scw_contact);

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
        String scw_contact[] = contact_data.split("~~~~");
        //String scw_contact[] = {"Professor~Dr Tulshi Kumar Das    ~ 88-01716120886~tulshikumardas@gmail.com ,  dastulshi-scw@sust.edu","Professor~Dr A K M Mahbubuzzaman~ 01915841644~zamansust@gmail.com","Professor~Dr Neaz Ahmed~ 88-01716382906~nahmed1973@gmail.com","Professor~Dr. Md. Faisal Ahmmed~ 88-01716201325~faisal-scw@sust.edu, dr.faisal_ahmed@yahoo.com","Professor & Head~Dr. Md. Ismail Hossain~ 88-01711069070~ismailscw@yahoo.com, ismail-scw@sust.edu","Professor~Amina Pervin~ 01712375748~apervin-scw@sust.edu , apervin_scw@yahoo.co.in","Professor~Muhammed Muazzam Hussain ~ 01716562079~muazzam777@yahoo.com","Professor~Md Mizanur Rahman  ~ +8801712818010~mmrahman.scw@gmail.com, mmrahman_scw@yahoo.com, mm","Professor~Syeda Sultana Parveen ~01790736848~sultana-scw@sust.edu","Professor~Md Abdul Jalil~ Cell: 01558301168~jalil_sust@yahoo.com","Professor~Tahmina Islam~01732240178~tahmina-scw@sust.edu","Professor~Shofiqur Rahman Chowdhury, PhD~ +8801919604981 (whatsapp only)~srahman-scw@sust.edu   areeba.scwsust@gmail.com chowdhury.socialwork@yahoo.com","Associate Professor~Mohammad Ali Oakkas, PhD.~ +8801712816855~alioakkas@gmail.com,alioakkas-scw@sust.edu","Associate Professor~Abul Kashem~01712912313~akashemscw2014@gmail.com","Associate Professor~ Md Fakhrul Alam~+8801712346856~fakhrulsust@gmail.com, fakhrul-scw@sust.edu    ","Assistant Professor~Krittebas Paul               ~ 01718737348~kritte_bas@yahoo.com, krittebas-scw@sust.edu","Assistant Professor~Priyanka Bhattacharjee~01732240707~priyankasust07@yahoo.com"};
        for(String contact:scw_contact){
            //Toast.makeText(getApplicationContext(), contact, Toast.LENGTH_SHORT).show();
            String infos[] = contact.split("~~");
            //Toast.makeText(getApplicationContext(), infos[0], Toast.LENGTH_SHORT).show();
            contactsList.add(new Contacts(infos[0]+"",infos[1]+"",infos[2]+"",infos[3]+"","N/A"));
        }
    }

}