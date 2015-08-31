package com.mycompany.fileencryptor;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import android.os.Environment;
import android.app.ListActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PickFile extends ListActivity {

    private String root;
    private List<String> dirList;
    private List<String> fileList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_file);


        dirList = new ArrayList<String>();
        fileList = new ArrayList<String>();
        root = Environment.getExternalStorageDirectory().getPath();
        traverse(new File(root));
        ArrayAdapter<String> newList =
                new ArrayAdapter<String>(this, R.layout.row, fileList);
        setListAdapter(newList);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // TODO Auto-generated method stub
        MainActivity.path = dirList.get(position);

        startActivity(new Intent(v.getContext(), MainActivity.class));

    }

    public void traverse(File dir) {
        if (dir.exists()) {
            File[] files = dir.listFiles();
            for (int i = 0; i < files.length; ++i) {
                File file = files[i];
                if (file.isDirectory()) {
                    traverse(file);
                } else {
                    // do something here with the file
                    String name = file.getName();
                    if (name.endsWith(".txt")) {
                        fileList.add(name);
                        dirList.add(file.getPath());
                    }
                }
            }
        }
    }

}
