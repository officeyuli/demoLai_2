package com.example.santai.demolai_2.activity;


import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.santai.demolai_2.R;


public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private DrawerLayout drawerLayout;
    private NavigationView navigation_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //側拉選單開始
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigation_view = (NavigationView) findViewById(R.id.nav_view);

        // 為navigatin_view設置點擊事件
        navigation_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                // 點選時收起選單
                drawerLayout.closeDrawer(GravityCompat.START);

                // 取得選項id
                int id = item.getItemId();

                // 依照id判斷點了哪個項目並做相應事件
                if (id == R.id.todaysRank) {
                    // 按下「本日排名」要做的事
                     Intent intent = new Intent();
                    intent.setClass(MainActivity.this, todaysRankActivity.class);
                    startActivity(intent);

                    MainActivity.this.finish();//結束目前 Activity
                    return true;
                }
                else if (id == R.id.myVideo) {
                    // 按下「我的影片」要做的事

                    return true;
                } else if (id == R.id.settings) {
                    // 按下「設定」要做的事
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, SettingActivity.class);
                    startActivity(intent);

                    MainActivity.this.finish();//結束目前 Activity
                    return true;
                }

                return false;
            }
        });
        //側拉選單結束
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
