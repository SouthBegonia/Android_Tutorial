package com.example.androidtutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.androidtutorial.Utils.CommonUtils;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RefreshMainView();
    }

    private void RefreshMainView() {
        //Button
        Button androidBtn = findViewById(R.id.bt_android);
        androidBtn.setText("Btn01");
        androidBtn.setBackgroundColor(Color.LTGRAY);
        androidBtn.setOnClickListener((View view) -> {
            //TODO Test

            //Toast
            CommonUtils.ShowCommonToast(this, "Hello World");

            //PackageName
            //CommonUtils.ShowCommonToast(this, CommonUtils.GetPackageName(this));

            //跳转商店
            //String packageName = "com.facebook.katana"; //CommonUtils.GetPackageName(this);
            //String storePkg = CommonUtils.GetAppStorePkg(CommonUtils.E_AppStorePkg.GooglePlay);
            //CommonUtils.JumpToAppStore(this, packageName, storePkg);
            //CommonUtils.JumpToAppStore(this, storePkg);
        });

        //ListView
        ListView listView = findViewById(R.id.listView_android);
        String[] arr = {"A", "B", "C", "D", "E"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, arr);
        listView.setAdapter(arrayAdapter);
    }
}