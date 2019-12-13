package com.example.toolbarapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //支持状态栏透明
        StatusBarUtil.transparencyBar(this);
        //使用谷歌原生方式修改状态栏颜色
        StatusBarUtil.setAndroidNativeLightStatusBar(this,false);
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("标题");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        mToolbar.setOverflowIcon(getResources().getDrawable(R.mipmap.menu));


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_home:
                Toast.makeText(MainActivity.this, "首页", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_me:
                Toast.makeText(MainActivity.this, "我的", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_focus:
                Toast.makeText(MainActivity.this, "关注", Toast.LENGTH_SHORT).show();
                break;
                default: Toast.makeText(MainActivity.this, "返回键", Toast.LENGTH_SHORT).show();


        }
        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @SuppressLint("RestrictedApi")
    @Override
    protected boolean onPrepareOptionsPanel(View view, Menu menu) {
        if (menu != null) {
            if (menu.getClass() == MenuBuilder.class) {
                try {
                    //利用反射获取私有方法
                    Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onPrepareOptionsPanel(view, menu);
    }

}
