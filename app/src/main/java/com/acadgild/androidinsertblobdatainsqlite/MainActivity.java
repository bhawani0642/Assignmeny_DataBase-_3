package com.acadgild.androidinsertblobdatainsqlite;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
    //object of DBhelper class
    private DBhelper DbHelper;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DbHelper = new DBhelper(this);
        //performing insert operation and closing database
        Employee employee_One = new Employee(BitmapFactory.decodeResource(
                getResources(), R.drawable.photo),"Venkat", 25);
        DbHelper.open();
        DbHelper.insertEmpDetails(employee_One);
        DbHelper.close();
        employee_One = null;
        DbHelper.open();
        employee_One = DbHelper.retriveEmpDetails();
        DbHelper.close();
           //Finding texviews and imageview in java and ussing set method
        TextView empname = (TextView) findViewById(R.id.name);
        empname.setText(employee_One.getName());
        ImageView empphoto = (ImageView) findViewById(R.id.photo);
        empphoto.setImageBitmap(employee_One.getBitmap());
        TextView empage = (TextView) findViewById(R.id.age);
        empage.setText("" + employee_One.getAge());

    }
}

