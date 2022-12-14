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

public class CHEContactActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Contacts> contactsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checontact);

        recyclerView = findViewById(R.id.recyclerViewCHEContact);



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

        InputStream is = this.getResources().openRawResource(R.raw.che_contact);

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
        String che_contact[] = contact_data.split("~~~~");
        //String che_contact[] = {"Professor~Dr Syed Shamsul Alam       ~+8801718350953~ssa1997@yahoo.com, ssa-che@sust.edu","Professor~Dr Muhammad Younus        ~ 01711736177~myounus-che@sust.edu","Professor~Dr S M Saiful Islam~01972448030~saiful-che@sust.edu","Professor~Dr Md Mizanur Rahman~ 01711901858~rmizanur@sust.edu","Professor~Dr Md Abdus Subhan~01716073270~subhan-che@sust.edu","Professor~ Dr. Md. Ashraful Alam         ~01718364976~ashraf_sust@yahoo.com","Professor & Head~Dr Shameem Ara Begum~01731789864~meem_sust@yahoo.com","Professor~Dr Iqbal Ahmed Siddiquey~  Mobile: +88-01732222182~iqbal_siddiquey@yahoo.com","Professor~Dr. Md. Rezwan Miah~Cell : +8801746055891 ~rezwan-che@sust.edu","Professor~Dr Md Nizam Uddin            ~01926372680~nizam3472@yahoo.com","Professor~Dr Mohammad Jalilur Rahman ~01726887768~jalil-che@sust.edu, mjrche@yahoo.com","Professor~Dr Mohammad Abul Hasnat~  88-01766497022~mah-che@sust.edu, mahtazim@yahoo.com","Professor~Dr Dipen Debnath~01733542585~dipen@sust.edu","Professor~Dr Mohammad Mizanur Rahman Khan ~ Cell Phone: 0088 01712185663~mizan_su@yahoo.com, mmrkhan1976@gmail.com","Professor~Dr Ahmed Jalal Farid Us Samed ~8801712-174049~ajf-che@sust.edu","Professor~Dr Rockshana Begum~01731246924~ruchy01@yahoo.com","Professor~Dr Nur Uddin Ahamad~01940174653~nur-che@sust.edu","Professor~Dr Mohammad Salim~ 01705122003~salbd2002@yahoo.com","Professor~Dr. Md Mahbubul Alam ~Cell:+8801711200306 ~mahbubana-che@sust.edu","Professor~Dr. Sabina Begum         ~01827566536~sabina-che@sust.edu","Professor~ Dr Md Razaul Karim~01750200968~krazaul@yahoo.com","Professor~Dr Md Mostafizur Rahman~ 01535767475~mostafiz-che@sust.edu","Associate Professor~Dr. Md. Azharul Arafath~+8801708528243~arafath-che@sust.edu, arafath_sustche90@yahoo.com, ","Associate Professor~Dr. Shishir Kanti Pramanik~+880-1717266762~shishir_kantif@yahoo.com / shishir-che@sust.edu","Associate Professor~Dr. Belal Ahmed~+8801714609507~belal-che@sust.edu, bahmed2021@gmail.com ","Assistant Professor~Md Shahadat Hussain Chowdhury ~01712280880~Shahadatchowdhury@yahoo.com","Assistant Professor~Ramkrishna Saha~01712757581~ramkrishna-che@sust.edu","Assistant Professor~Md Masum Talukder~01716310110~mtalukder30@yahoo.com ","Assistant Professor~Md Saiful Alam~01675762491~saiful_alam023@yahoo.com","Assistant Professor~Rehana Pervin~01720373336~boby13.sust@yahoo.com","Assistant Professor~Masnun Naher~01726220406~ masnun-che@sust.edu and masnuna.che@gmail.com","Assistant Professor~Dr. S. M. Nizam Uddin~+88-01712-013266~ontic.sohel@gmail.com, sohel-che@sust.edu","Assistant Professor~Pallab Chandra Saha~+8801712348365~pallab-che@sust.edu, pallabsahabd@gmail.com"};
        for(String contact:che_contact){
            //Toast.makeText(getApplicationContext(), contact, Toast.LENGTH_SHORT).show();
            String infos[] = contact.split("~~");
            //Toast.makeText(getApplicationContext(), infos[0], Toast.LENGTH_SHORT).show();
            contactsList.add(new Contacts(infos[0]+"",infos[1]+"",infos[2]+"",infos[3]+"","N/A"));
        }
    }

}