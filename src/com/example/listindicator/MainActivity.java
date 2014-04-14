package com.example.listindicator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_layout);
        final ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mStrings));
        listView.setTextFilterEnabled(true);

        final SimpleListViewIndicator listIndicator = (SimpleListViewIndicator) findViewById(R.id.indicator);
        listIndicator.setListView(listView);
        listIndicator.notifyDataSetChanged();
        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                // TODO Auto-generated method stub
                listIndicator.setCurrentItem(position);
            }
        });
    }

    private String[] mStrings = { "a", "b", "c", "d", "e", "f", "g", "h" };

}
