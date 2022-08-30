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

public class IPEContactActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Contacts> contactsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipecontact);

        recyclerView = findViewById(R.id.recyclerViewIPEContact);



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

        InputStream is = this.getResources().openRawResource(R.raw.ipe_contact);

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
        String ipe_contact[] = contact_data.split("~~~~");
        //String ipe_contact[] = {"Professor~Dr. Engr. Mohammad Iqbal	~/427~iqbalm_ipe@yhoo.com, iqbalm-ipe@sust.edu","Professor~Dr Abul Mukid Mohammad Mukaddes ~880-821-713491~mukaddes-ipe@sust.edu, mukaddes1975@gmail.com","Professor~Dr Md Ariful Islam  ~88-0821-717850 Ext. 425~arif-ipe@sust.edu","Professor~Dr Mohammad Muhshin Aziz Khan ~88-0821-713491~muhshin-ipe@sust.edu","Professor~Dr Muhammad Mahamood Hasan~+880-821-717850~muhammad.hasan-ipe@sust.edu","Professor~Dr. Md Abu Hayat Mithu~01761591170 ~mithu-ipe@sust.edu (official),  mithuipe@gmail.com (personal)","Professor~Dr. A. B. M. Abdul Malek~+880821717850/2636~abmmalek@gmail.com, bashar@sust.edu","Professor~Dr. Ahmed Sayem~+8801646699859~sayem-ipe@sust.edu; ahmedsayem02@gmail.com","Professor~Dr. Choudhury Abul Anam Rashed  ~+88-0821-717850/713850/714479/713491~rashed-ipe@sust.edu","Professor~Dr. Mst. Nasima Bagum~01617535997 ~nasima-ipe@sust.edu","Associate Professor~Syed Misbah Uddin~0821-713850; Ext: 268~misbah-ipe@sust.edu","Associate Professor~Md Anisul Islam~0821-713850; Ext: 268~anis134ipe@yahoo.com","Associate Professor~ Chowdury Md Luthfur Rahman ~01715746648~clr-ipe@sust.edu","Associate Professor~Muhammad Abdus Samad~01760362680~samad-ipe@sust.edu ","Associate Professor~Md Rezaul Hasan Shumon~01712912063~shumon330@gmail.com","Assistant Professor~Shuchisnigdha Deb~01712912063~ssdsust@yahoo.com","Assistant Professor~Engr Mohammed Abdul Karim~01684417812~karim-ipe@sust.edu, farhad.karim@gmail.com","Assistant Professor~Shanta Saha~01674806784~shanta-ipe@sust.edu","Assistant Professor~ Syeda Kumrun Nahar~01676862422~aurjoma@yahoo.com","Assistant Professor~Jahid Hasan~01712832699~j.hasan-ipe@sust.edu, j.hasan.ipe@gmail.com","Assistant Professor~Pronob Kumar Biswas~01750887879~pronob-ipe@sust.edu","Assistant Professor~Mahathir Mohammad Bappy~88-0821-717850/713850/714479/713491~mahathir-ipe@sust.edu; bappymm@gmail.com","Assistant Professor~Md. Mehedi Hasan Kibria~01914-790276~kibria-ipe@sust.edu, mehedikibria11@gmail.com","Assistant Professor~Saiful Islam~01675-665529~saiful-ipe@sust.edu",};
        for(String contact:ipe_contact){
            //Toast.makeText(getApplicationContext(), contact, Toast.LENGTH_SHORT).show();
            String infos[] = contact.split("~~");
            //Toast.makeText(getApplicationContext(), infos[0], Toast.LENGTH_SHORT).show();
            contactsList.add(new Contacts(infos[0]+"",infos[1]+"",infos[2]+"",infos[3]+"","N/A"));
        }
    }

}