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

public class BMBContactActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Contacts> contactsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmbcontact);

        recyclerView = findViewById(R.id.recyclerViewBMBContact);



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

        InputStream is = this.getResources().openRawResource(R.raw.bmb_contact);

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
        String bmb_contact[] = contact_data.split("~~~~");
        // String  bmb_contact[] = {"Professor~Shamim Ahmed, PhD~88-01776196006~shamim1174@gmail.com, shamim1174-bmb@sust.edu ","Associate Professor~Mst Arzuba Akter, PhD~01731549116~akhi_bmb@yahoo.com, arzuba-bmb@sust.edu","Associate Professor~Mohammad Abdullah-Al-Shoeb, PhD~88-01787501398~maashoeb-bmb@sust.edu","Associate Professor~Muhammad Abul Kalam Azad, PhD.~  +8801302897622~makazad-bmb@sust.edu, bmbsumon@yahoo.co.uk","Associate Professor~Zafrul Hasan, PhD~+88-01706247124~zafrul-bmb@sust.edu, jafrul05@yahoo.com","Associate Professor~Ajit Ghosh, PhD~88-01679436700~aghosh-bmb@sust.edu ","Assistant Professor~Dr Mostafa Kamal Masud~88-01819647634~masud-bmb@sust.edu, mas.sust@gmail.com, m.masud@uq.edu.au","Assistant Professor~Md Kawsar Khan~88-01923159836~kawsarkhan-bmb@sust.edu, bmbkawsar@gmail.com","Assistant Professor~H. M. Syfuddin~ 8801723333287~syfuddin-bmb@sust.edu, hasan.syfuddin@oncology.ox.ac.uk","Assistant Professor~Payal Barua~88-01714248818~baruapayal-bmb@sust.edu","Assistant Professor~Khandaker Atkia Fariha~88-01710655437~kafb.2312@gmail.com","Assistant Professor~Dr. Md. Nurshad Ali~88-01723205092~nali-bmb@sust.edu; nur_rubd@yahoo.com ","Assistant Professor~Dr. Shaikh Mirja Nurunnabi~88-01717-523220~shaikhmir-bmb@sust.edu,  noor_ru07@yahoo.com.au, ","Assistant Professor~Md. Waseque Mia~88-01720183324~waseq.bmb.cu@gmail.com, waseqbd-bmb@sust.edu","Assistant Professor~Nayan Chandra Mohanto~8801737897779~nmohanto17-bmb@sust.edu","Assistant Professor~Mohammad Abul Hasnat~+88-01710525919~lalon.hasnat@gmail.com","Assistant Professor~Farjana Islam~+88-01756693028~farjana-bmb@sust.edu, farjanaislam308@gmail.com","Assistant Professor~Tanvir Hossain~01878603466~tanvir-bmb@sust.edu, tanvirain@gmail.com","Assistant Professor~Moshiul Alam Mishu~ +88-01670625763~moshiul-bmb@sust.edu, moshiul.alam42@gmail.com ","Assistant Professor~Abu Ali Ibn Sina~01711464969~Ibnsina_shuvo@yahoo.com",};
        for(String contact:bmb_contact){
            //Toast.makeText(getApplicationContext(), contact, Toast.LENGTH_SHORT).show();
            String infos[] = contact.split("~~");
            //Toast.makeText(getApplicationContext(), infos[0], Toast.LENGTH_SHORT).show();
            contactsList.add(new Contacts(infos[0]+"",infos[1]+"",infos[2]+"",infos[3]+"","N/A"));
        }
    }

}