package com.wayapp.filemanagerproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

   RecyclerView myRecyclerView;
   RecyclerView.Adapter myAdapter;
   RecyclerView.LayoutManager myLayoutManager;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

        myRecyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        myLayoutManager = new LinearLayoutManager(getApplicationContext());
        myRecyclerView.setLayoutManager(myLayoutManager);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(permissions[0].equals(android.Manifest.permission.READ_EXTERNAL_STORAGE) && grantResults[0]== PackageManager.PERMISSION_GRANTED){
            show(Environment.getExternalStorageDirectory());
        } else {
            finish();
        }
    }

    void show(File incomingFile){
        File[] newFiles = new File[incomingFile.listFiles().length + 1];
//        newFiles[0] = incomingFile;

        for(int i = 0; i < incomingFile.listFiles().length; i++){
            newFiles[i + 1] = incomingFile.listFiles()[i];
        }
        Arrays.sort(newFiles);
        myAdapter = new MyAdapter(this, newFiles);
        myRecyclerView.setAdapter(myAdapter);
    }

//    private void browseTo(final File aDirectory){
//        currentDir = aDirectory;
//        //if we want to browse directory
//        if (aDirectory.isDirectory()){
//            //fill list with files from this directory
//            fill(aDirectory.listFiles());
//
//            //set titleManager text
//            TextView titleManager = (TextView) findViewById(R.id.text_view_row);//??????
//            titleManager.setText(aDirectory.getName());
//        } else {
//            //if we want to open file, show this dialog:
//            //listener when YES button clicked
//            DialogInterface.OnClickListener okButtonListener = new DialogInterface.OnClickListener(){
//                public void onClick(DialogInterface arg0, int arg1) {
//                    //intent to navigate file
//                    Intent i = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("file://" + aDirectory.getAbsolutePath()));
//                    //start this activity
//                    startActivity(i);
//                }
//            };
//            //listener when NO button clicked
//            DialogInterface.OnClickListener cancelButtonListener = new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface arg0, int arg1) {
//                    //do nothing
//                    //or add something you want
//                }
//            };
//
//            //create dialog
//            new AlertDialog.Builder(this)
//                    .setTitle("Подтверждение") //title
//                    .setMessage("Хотите открыть файл "+ aDirectory.getName() + "?") //message
//                    .setPositiveButton("Да", okButtonListener) //positive button
//                    .setNegativeButton("Нет", cancelButtonListener) //negative button
//                    .show(); //show dialog
//        }
//    }
//
//    private void fill(File[] files) {
//        //clear list
//        directoryEntries.clear();
//
//        directoryEntries.set(0, new File("..."));
//
//        //add every file into list
//        for (File file : files) {
//            directoryEntries.add(file.getName());
//        }
//    }

//    @Override
//    protected void onListItemClick(ListView l, View v, int position, long id) {
//        //get selected file name
//        int selectionRowID = position;
//        String selectedFileString = directoryEntries.get(selectionRowID);
//
//        //if we select "..." then go upper
//        if(selectedFileString.equals("...") && currentDir!=null &&currentDir.getParentFile()!=null){
//            browseTo(currentDir);
//        } else {
//            //browse to clicked file or directory using browseTo()
//            File clickedFile = null;
//            clickedFile = new File(selectedFileString);
//            if (clickedFile != null)
//                browseTo(clickedFile);
//        }
//    }
}