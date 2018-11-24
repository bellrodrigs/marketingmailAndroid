package com.examples.listzones;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MyActivity extends Activity implements AdapterView.OnItemClickListener, View.OnClickListener {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView list = new ListView(this);
        setContentView(list);
        
        String[] items = {"Tom", "Sally", "Bill", "John", "Santiago", "Isabella"};
        //Supply this adapter with either R.layout.row_button, R.layout.row_view, or R.layout.row_view_noparent
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.row_view, R.id.text, items) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View row =  super.getView(position, convertView, parent);

                View left = row.findViewById(R.id.left);
                left.setTag(position);
                left.setOnClickListener(MyActivity.this);
                View right = row.findViewById(R.id.right);
                right.setTag(position);
                right.setOnClickListener(MyActivity.this);
                
                return row;
            }
        };
        
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
    }
    
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
        case R.id.left:
            Toast.makeText(this, "Left Accessory "+v.getTag(), Toast.LENGTH_SHORT).show();
            break;
        case R.id.right:
            Toast.makeText(this, "Right Accessory "+v.getTag(), Toast.LENGTH_SHORT).show();
            break;
        default:
            break;
        }
    }
    
    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        Toast.makeText(this, "Item Click "+position, Toast.LENGTH_SHORT).show();
    }
}