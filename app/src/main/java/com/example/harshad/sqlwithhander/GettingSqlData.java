package com.example.harshad.sqlwithhander;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class GettingSqlData {


    Context mcontaxt;
    public static final String KEY_FullName = "name";
    /*
    public static final String KEY_Email = "email";
    public static final String KEY_WorkDept = "work_department";
    public static final String KEY_Designation = "designation";
    public static final String KEY_ContactNo = "phone_number";
    public static final String KEY_DateOfBirth = "date_of_birth";
    public static final String KEY_DateOfHire = "date_of_hire";
    public static final String KEY_Gender = "gender";
    public static final String KEY_Salary = "salary";
    public static final String KEY_Emiratsid = "emirats_id";
    public static final String KEY_PassportNo = "passport_no";
    public static final String KEY_Nationality = "nationality";
*/

    public GettingSqlData(Context mcontaxt) {
        this.mcontaxt = mcontaxt;
    }


    public ArrayList<HashMap<String, String>> getResults() {

        SqlHandler db = new SqlHandler(mcontaxt); //my database helper file

        ArrayList<HashMap<String, String>> resultList = new ArrayList<HashMap<String, String>>();

        String query = "SELECT * FROM EmployeeTable";


        Cursor c = db.selectQuery(query); //function to retrieve all values from a table- written in MyDb.java file
        c.moveToFirst();
        Log.e("getResults: ", c + "");


        // getting length of db column
        int len = c.getCount();

        if (len > 0) {

            do {

                // Storing each Sql lite database item values in variable
                String fullname = c.getString(c.getColumnIndex(KEY_FullName));
                /*String email = c.getString(c.getColumnIndex(KEY_Email));
                String workDept = c.getString(c.getColumnIndex(KEY_WorkDept));
                String designation = c.getString(c.getColumnIndex(KEY_Designation));
                String contactNo = c.getString(c.getColumnIndex(KEY_ContactNo));
                String dateofBirth = c.getString(c.getColumnIndex(KEY_DateOfBirth));
                String dateofHire = c.getString(c.getColumnIndex(KEY_DateOfHire));
                String gender = c.getString(c.getColumnIndex(KEY_Gender));
                String salary = c.getString(c.getColumnIndex(KEY_Salary));
                String emiratsID = c.getString(c.getColumnIndex(KEY_Emiratsid));
                String passportNO = c.getString(c.getColumnIndex(KEY_PassportNo));
                String nationality = c.getString(c.getColumnIndex(KEY_Nationality));
*/
                // creating new HashMap
                HashMap<String, String> map = new HashMap<String, String>();


                // adding each child node to HashMap key => value
                map.put(KEY_FullName, fullname);
                /*map.put(KEY_Email, email);
                map.put(KEY_WorkDept, workDept);
                map.put(KEY_Designation, designation);
                map.put(KEY_ContactNo, contactNo);
                map.put(KEY_DateOfBirth, dateofBirth);
                map.put(KEY_DateOfHire, dateofHire);
                map.put(KEY_Gender, gender);
                map.put(KEY_Salary, salary);
                map.put(KEY_Emiratsid, emiratsID);
                map.put(KEY_PassportNo, passportNO);
                map.put(KEY_Nationality, nationality);
*/

                // adding HashList to ArrayList
                resultList.add(map);


            } while (c.moveToNext());
            c.close();


        } else {
            Toast.makeText(mcontaxt, "No data found", Toast.LENGTH_SHORT).show();
        }


        return resultList;
    }
}
