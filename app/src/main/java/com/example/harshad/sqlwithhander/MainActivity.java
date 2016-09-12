package com.example.harshad.sqlwithhander;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button bt_save, bt_showdata;
    private EditText et_fullname;
    private TextInputLayout til_fullname;
    private String str_fullname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        LoaduiElements();
        LoadUILisners();

    }

    private void LoadUILisners() {
        et_fullname.addTextChangedListener(new MyTextWatcher(et_fullname));

        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });
        bt_showdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ShowAllData.class);
                startActivity(intent);
            }
        });
    }

    private void LoaduiElements() {
        //edit text
        et_fullname = (EditText) findViewById(R.id.et_fullname);
        bt_save = (Button) findViewById(R.id.bt_save);
        bt_showdata = (Button) findViewById(R.id.bt_shodata);
        til_fullname = (TextInputLayout) findViewById(R.id.til_fullname);
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }


        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.et_fullname:
                    validateFullName();
                    break;
            }
        }
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        }
    }


    private boolean validateFullName() {

        if (et_fullname.getText().toString().trim().isEmpty()) {
            til_fullname.setError(getString(R.string.err_msg_name));
            requestFocus(et_fullname);
            return false;
        } else {
            til_fullname.setErrorEnabled(false);
        }
        return true;
    }

    private void submitForm() {
        if (!validateFullName()) {
            return;
        } else {

            str_fullname = et_fullname.getText().toString();

            Log.e("fullname: ", str_fullname);

            SqlHandler sqlHandler = new SqlHandler(getApplicationContext());

            //Database insert query
            String insert = "INSERT INTO EmployeeTable(name) values ('" + str_fullname + "')";

            sqlHandler.executeQuery(insert);
        }
    }
}



