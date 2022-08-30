package com.example.freshmanutilites;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VersionsAdapter extends RecyclerView.Adapter<VersionsAdapter.VersionVH> { // try everything to remove the VersionVH Redlight**

    // you know list is the need thing here

    List<Versions> versionsList;

    // implement const

    public VersionsAdapter(List<Versions> versionsList) {
        this.versionsList = versionsList;
    }



    @NonNull
    @Override
    public VersionsAdapter.VersionVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        return new VersionVH(view); // return view;             -- both are same  my dear

        // then we go to the count
    }

    @Override
    public void onBindViewHolder(@NonNull VersionsAdapter.VersionVH holder, int position) {

        // it's something like where to set who -- :) you know we declare something down |||||| so ;) now attach every of the model class thing

        Versions versions = versionsList.get(position);

        holder.codeNameTxt.setText(versions.getCodeName()); // set korbo get theke niye ;)
        holder.versionTxt.setText(versions.getVersion());
        holder.apiLevelTxt.setText(versions.getApiLevel());
        holder.descriptionTxt.setText(versions.getDescription());

        // so now in go to the  model class

        // here again

        boolean isExpandable = versionsList.get(position).isExpandable();
        holder.expandableLayout.setVisibility(isExpandable? View.VISIBLE:View.GONE);

        // from here we end and go to the FAQActivity.java where we call this th*gs


    }

    @Override
    public int getItemCount() {
        return versionsList.size();
    }

    public class VersionVH extends RecyclerView.ViewHolder { // **Redlight
        // from here we start some code than go to the OnCreate ***************

        // item go here ;)

        TextView codeNameTxt , versionTxt , apiLevelTxt, descriptionTxt;
        LinearLayout linearLayout;
        RelativeLayout expandableLayout;

        public VersionVH(@NonNull View itemView) {
            super(itemView);

            // here go our things

            codeNameTxt = itemView.findViewById(R.id.code_name);
            versionTxt = itemView.findViewById(R.id.version);
            apiLevelTxt = itemView.findViewById(R.id.api_level);
            descriptionTxt = itemView.findViewById(R.id.description);

            linearLayout = itemView.findViewById(R.id.linear_layout);
            expandableLayout = itemView.findViewById(R.id.expandable_layout);

            // now we are going to onCreateViewHolder -- ^^^^^^^^^^^^


            // here we again to attach a set on click listener ;) for the expandable list view

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Versions versions = versionsList.get(getAdapterPosition());
                    versions.setExpandable(!versions.isExpandable());
                    notifyItemChanged(getAdapterPosition());

                    // now again we go to the OnBind! :) :)

                }
            });

        }


    }

    // here VersionVH is viewHolder class name

}
