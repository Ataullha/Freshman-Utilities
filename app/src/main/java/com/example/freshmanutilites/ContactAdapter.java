package com.example.freshmanutilites;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.api.Context;
import com.google.common.hash.HashingOutputStream;

import java.util.List;



public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactVH> {

    List<Contacts> contactsList;

    public ContactAdapter(List<Contacts> contactsList) {
        this.contactsList = contactsList;
    }

    @NonNull
    @Override
    public ContactAdapter.ContactVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.a_contact,parent,false);
        return new ContactAdapter.ContactVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ContactVH holder, int position) {

        Contacts contacts = contactsList.get(position);

        holder.DesignationTXT.setText(contacts.gettDesignation()); // t korbo get theke niye ;)se
        holder.NameTXT.setText(contacts.gettName());
        holder.EmailTXT.setText(contacts.gettEmail());
        holder.MobNoTXT.setText(contacts.gettMobNo());
        holder.RoomNoTXT.setText(contacts.gettRoomNo());


        //

        boolean isExpandable = contactsList.get(position).isExpandable();
        holder.expandableLayout_1.setVisibility(isExpandable? View.VISIBLE:View.GONE);
        holder.expandableLayout_2.setVisibility(isExpandable? View.VISIBLE:View.GONE);

        //


        String number = contacts.gettMobNo().toString().trim();

        holder.MobNoTXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+number));
                v.getContext().startActivity(callIntent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }

    public class ContactVH extends RecyclerView.ViewHolder {

        TextView DesignationTXT, NameTXT, MobNoTXT, EmailTXT, RoomNoTXT;
        LinearLayout linearLayout, expandableLayout_1, expandableLayout_2;

        public ContactVH(@NonNull View itemView) {
            super(itemView);

            //

            DesignationTXT = itemView.findViewById(R.id.teacher_designation);
            NameTXT = itemView.findViewById(R.id.teacher_name);
            MobNoTXT = itemView.findViewById(R.id.teacher_mobile);
            RoomNoTXT = itemView.findViewById(R.id.teacher_room);
            EmailTXT = itemView.findViewById(R.id.teacher_email);
//

            linearLayout = itemView.findViewById(R.id.linear_layout_contact);
            expandableLayout_1 = itemView.findViewById(R.id.expandable_contact_part_1);
            expandableLayout_2 = itemView.findViewById(R.id.expandable_contact_part_2);

            // now we are going to onCreateViewHolder -- ^^^^^^^^^^^^


            // here we again to attach a set on click listener ;) for the expandable list view

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                  //  Versions versions = versionsList.get(getAdapterPosition());
                    Contacts contacts = contactsList.get(getAdapterPosition());
                  //  versions.setExpandable(!versions.isExpandable());
                    contacts.setExpandable(!contacts.isExpandable());
                    notifyItemChanged(getAdapterPosition());

                    // now again we go to the OnBind! :) :)

                }
            });



            ///


        }
    }
}
