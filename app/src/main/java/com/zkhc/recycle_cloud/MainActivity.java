package com.zkhc.recycle_cloud;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

//import com.zkhc.recycle_cloud.serial.SerialHandle;
//import com.zkhc.recycle_cloud.util.SerialManage;

import com.zkhc.recycle_cloud.util.HideButtonUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity{
    private ImageView type_food;
    private ImageView type_non_recyclab;
    private ImageView type_recyclab;
    private ImageView others;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //去除系统顶部任务栏,时间和电量等
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HideButtonUtils.hideBottomUIMenu(this);
        init();
        jump();
    }


    private void jump() {
        type_food.setOnClickListener(view -> {
            Intent intent = null;
            intent = new Intent(MainActivity.this, NonRecyclableActivity.class);
            intent.putExtra("typeName", "厨余垃圾");
            startActivity(intent);
            finish();
        });
        type_non_recyclab.setOnClickListener(view -> {
            Intent intent = null;
            intent = new Intent(MainActivity.this, NonRecyclableActivity.class);
            intent.putExtra("typeName", "不可回收垃圾");
            startActivity(intent);
            finish();
        });
        type_recyclab.setOnClickListener(view -> {
            Intent intent = null;
            intent = new Intent(MainActivity.this, RecyclableActivity.class);
            intent.putExtra("typeName", "可回收物");
            startActivity(intent);
            finish();
        });
        others.setOnClickListener(view -> {
            Intent intent = null;
            intent = new Intent(MainActivity.this, NonRecyclableActivity.class);
            intent.putExtra("typeName", "其它垃圾");
            startActivity(intent);
            finish();
        });
    }

    //实例化控件
    private void init() {
        type_food = findViewById(R.id.type_food);
        type_non_recyclab = findViewById(R.id.type_non_recyclab);
        type_recyclab = findViewById(R.id.type_recyclab);
        others = findViewById(R.id.others);
    }

}