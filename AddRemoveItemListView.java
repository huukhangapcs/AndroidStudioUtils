package com.example.adddeleteitemlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String>itemList;
    ArrayAdapter<String>adapter;
    EditText itemText;
    Button addButton;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.list_view);
        addButton =(Button)findViewById(R.id.button_add);
        itemText = (EditText)findViewById(R.id.edit_text);
        itemList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_selectable_list_item,itemList);
        View.OnClickListener addListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemList.add(itemText.getText().toString());
                itemText.setText("");
                adapter.notifyDataSetChanged();
            }
        };
        addButton.setOnClickListener(addListener);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SparseBooleanArray positionChecker = listView.getCheckedItemPositions();
                int count = listView.getCount();
                for (int item = count-1; item>=0; item--)
                {
                    if (positionChecker.get(item))
                    {
                        adapter.remove(itemList.get(item));
                    }
                }
                positionChecker.clear();
                adapter.notifyDataSetChanged();
            }
        });
    }
}