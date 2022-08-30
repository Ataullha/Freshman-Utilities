package com.example.freshmanutilites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/*
 * All code in the contact section and the parser section is done by
 * @Ataullha
 * CSE-2018,SUST
 */
public class ContactSubSection extends AppCompatActivity {

    TextView ec,cse,eee,fet,pme,ipe,cep,me,arc,cee,ac,anp,bba,bmb,bng,che,eco,eng,fes,geb,gee,mat,ocg,pad,phy,pss,scw,soc,sta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_sub_section);

        ec = findViewById(R.id.ec);
        ac = findViewById(R.id.ac);
        cse = findViewById(R.id.cse);
        cee = findViewById(R.id.cee);
        eee = findViewById(R.id.eee);
        fet = findViewById(R.id.fet);
        pme = findViewById(R.id.pme);
        ipe = findViewById(R.id.ipe);
        cep = findViewById(R.id.cep);
        me = findViewById(R.id.me);
        arc = findViewById(R.id.arc);
        anp = findViewById(R.id.anp);
        bba = findViewById(R.id.bba);
        bmb = findViewById(R.id.bmb);
        bng = findViewById(R.id.bng);
        che = findViewById(R.id.che);
        eco = findViewById(R.id.eco);
        eng = findViewById(R.id.eng);
        fes = findViewById(R.id.fes);
        geb = findViewById(R.id.geb);
        gee = findViewById(R.id.gee);
        mat = findViewById(R.id.mat);
        ocg = findViewById(R.id.ocg);
        pad = findViewById(R.id.pad);
        phy = findViewById(R.id.phy);
        pss = findViewById(R.id.pss);
        scw = findViewById(R.id.scw);
        soc = findViewById(R.id.soc);
        sta = findViewById(R.id.sta);


        ec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ECContactActivity.class));
            }
        });

        ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ACContactActivity.class));
            }
        });

        cse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CSEContactActivity.class));
            }
        });

        cep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CEPContactActivity.class));
            }
        });

        arc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ARCContactActivity.class));
            }
        });

        cee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CEEContactActivity.class));
            }
        });

        eee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),EEEContactActivity.class));
            }
        });

        fet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),FETContactActivity.class));
            }
        });

        ipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),IPEContactActivity.class));
            }
        });

        me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MEEContactActivity.class));
            }
        });

        pme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PMEContactActivity.class));
            }
        });


        anp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ANPContactActivity.class));
            }
        });
        bba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),BBAContactActivity.class));
            }
        });

        bmb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),BMBContactActivity.class));
            }
        });

        bng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),BNGContactActivity.class));
            }
        });

        che.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CHEContactActivity.class));
            }
        });

        eco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ECOContactActivity.class));
            }
        });

        eng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ENGContactActivity.class));
            }
        });

        fes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),FESContactActivity.class));
            }
        });

        geb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),GEBContactActivity.class));
            }
        });

        gee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),GEEContactActivity.class));
            }
        });

        mat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MATContactActivity.class));
            }
        });

        ocg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),OCGContactActivity.class));
            }
        });

        pad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PADContactActivity.class));
            }
        });

        phy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PHYContactActivity.class));
            }
        });

        pss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PSSContactActivity.class));
            }
        });

        scw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SCWContactActivity.class));
            }
        });

        soc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SOCContactActivity.class));
            }
        });

        sta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(),STAContactActivity.class));
            }
        });



    }




}