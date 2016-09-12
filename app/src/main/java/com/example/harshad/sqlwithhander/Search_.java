package com.example.harshad.sqlwithhander;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class Search_ extends AppCompatActivity {
    public EditText et_search;
    private RecyclerView rv;

    // converting arraylist to string array intializing size
    private String[] full_name = new String[ShowAllData.emloyee_info.size()];
    // array list
    private ArrayList<String> array_sort;
    int textlength = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.GRAY);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        LoaduiElements();
        LoadUILisners();
    }


    private void LoaduiElements() {
        et_search = (EditText) findViewById(R.id.et_search);
        rv = (RecyclerView) findViewById(R.id.rv);


        // converting arraylist to string array
        for (int i = 0; i < ShowAllData.emloyee_info.size(); i++) {
            try {
                full_name[i] = ShowAllData.emloyee_info.get(i).get("name");
                Log.e("Search employee : ", full_name[i]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        rv.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(mLayoutManager);
        //converting string array to Array List
        array_sort = new ArrayList<String>(Arrays.asList(full_name));
        // seting custom adapter
        rv.setAdapter(new MyAdapter(array_sort));


    }

    private void LoadUILisners() {

        et_search.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                // Abstract Method of TextWatcher Interface.
            }

            public void beforeTextChanged(CharSequence s,
                                          int start, int count, int after) {
                // Abstract Method of TextWatcher Interface.
            }

            //called when user entering text fo search match case
            public void onTextChanged(CharSequence s,
                                      int start, int before, int count) {
                //getting text length
                textlength = et_search.getText().length();
                //clear array list
                array_sort.clear();
                //for loop for cnverting text into lower case and adding into array list of array_sort
                for (int i = 0; i < full_name.length; i++) {
                    if (textlength <= full_name[i].length()) {
                        if (full_name[i].toLowerCase().contains(
                                et_search.getText().toString().toLowerCase().trim())) {
                            array_sort.add(full_name[i]);
                        }
                    }
                }
                // after searched text will be saved in array_sort array list
                rv.setAdapter(new MyAdapter(array_sort));
                Log.e("onTextChanged: ", array_sort + "");
            }
        });

    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private ArrayList<String> mdataset;

        public MyAdapter(ArrayList<String> Emp_info) {
            mdataset = Emp_info;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            private TextView tv_fullname;

            public ViewHolder(View itemView) {
                super(itemView);

                tv_fullname = (TextView) itemView.findViewById(R.id.tv_fullname);
            }
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_show_all_data, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
            final String FullName = mdataset.get(position);

            holder.tv_fullname.setText(FullName);

        }

        @Override
        public int getItemCount()
        {
           return mdataset.size();
        }


    }
}
