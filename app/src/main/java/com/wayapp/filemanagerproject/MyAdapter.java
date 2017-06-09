package com.wayapp.filemanagerproject;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Marina on 07.06.2017.
 */

class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    File[] newFiles;
    MainActivity mainActivity;

    public MyAdapter(MainActivity mainActivity, File[] newFiles) {
        this.newFiles = newFiles;
        this.mainActivity = mainActivity;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewRow;
        ImageView imageViewRow;
        LinearLayout rowLayoutView;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewRow = (TextView)itemView.findViewById(R.id.text_view_row);
            imageViewRow = (ImageView)itemView.findViewById(R.id.image_view_row);
            rowLayoutView = (LinearLayout)itemView.findViewById(R.id.row_layout_view);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row, parent, false);
        ViewHolder viewHolderh = new ViewHolder(view);
        return viewHolderh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final File file = newFiles[position];
        String title = position == 0 ? "...": file.getName();
        holder.textViewRow.setText(title);
        if(file.isFile()){
            holder.imageViewRow.setImageResource(R.drawable.icons8_file_48);
        } else {
            holder.imageViewRow.setImageResource(R.drawable.icons8_folder_48);
            holder.rowLayoutView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(position == 0){
                        mainActivity.show(file.getParentFile());
                    } else {
                        mainActivity.show(file);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return newFiles.length;
    }
}
