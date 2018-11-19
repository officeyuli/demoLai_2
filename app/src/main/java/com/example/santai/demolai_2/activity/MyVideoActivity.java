package com.example.santai.demolai_2.activity;

import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.santai.demolai_2.R;
import com.example.santai.demolai_2.object.youtube.VideoAdapter;
import com.example.santai.demolai_2.object.youtube.YouTubeVideos;

import java.io.File;
import java.util.Vector;

public class MyVideoActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Vector<YouTubeVideos> youtubeVideos = new Vector<YouTubeVideos>();
    static int TAKE_PIC = 1;
    Uri outPutfileUri;
    private DrawerLayout drawerLayout;
    private NavigationView navigation_view;
    //我的影片
    private VideoView vidView;
    private MediaController vidControl;
    String vidAddress = "rtsp://192.168.0.199/myvideo.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//呼叫父類的onCreate
        setContentView(R.layout.my_video);//設置畫面
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);//抓取畫面元件
//        setSupportActionBar(toolbar);//

        //我的影片
        vidView = (VideoView) findViewById(R.id.myVideo);
        vidControl = new MediaController(this);
        vidControl.setAnchorView(vidView);
        vidView.setMediaController(vidControl);


        //影片列表
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new GridLayoutManager(this,2));
        youtubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/QIPPTfTyWm8\" frameborder=\"0\" allowfullscreen=\"allowfullscreen\"></iframe>") );
        youtubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/oHyt7IxE-pY\" frameborder=\"0\" allowfullscreen=\"allowfullscreen\"></iframe>") );
        youtubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/C9hMTm6Ouv4\" frameborder=\"0\" allowfullscreen=\"allowfullscreen\"></iframe>") );
        youtubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/VQ_VgguT_us\" frameborder=\"0\" allowfullscreen=\"allowfullscreen\"></iframe>") );
        youtubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/UV6ine69kQ0\" frameborder=\"0\" allowfullscreen=\"allowfullscreen\"></iframe>") );
        VideoAdapter videoAdapter = new VideoAdapter(youtubeVideos);
        recyclerView.setAdapter(videoAdapter);

        //側拉選單
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
                    intent.setClass(MyVideoActivity.this, todaysRankActivity.class);
                    startActivity(intent);

                    MyVideoActivity.this.finish();//結束目前 Activity
                    return true;
                }
                else if (id == R.id.myVideo) {
                    // 按下「我的影片」要做的事
//                    Intent intent = new Intent();
//                    intent.setClass(MyVideoActivity.this, MyVideoActivity.class);
//                    startActivity(intent);
//
//                    MyVideoActivity.this.finish();//結束目前 Activity
//                    return true;
                } else if (id == R.id.settings) {
                    // 按下「設定」要做的事
                    Intent intent = new Intent();
                    intent.setClass(MyVideoActivity.this, SettingActivity.class);
                    startActivity(intent);

                    MyVideoActivity.this.finish();//結束目前 Activity
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

    public void recButtonOnClick(View v) {
        Intent intent= new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        File file = new File(Environment.getExternalStorageDirectory(),
                "MyPhoto.mpeg");
        outPutfileUri = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outPutfileUri);
        startActivityForResult(intent, TAKE_PIC);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data)
    {
        if (requestCode == TAKE_PIC && resultCode == RESULT_OK){
            Toast.makeText(this, "Recorded " + outPutfileUri.toString(),Toast.LENGTH_LONG).show();
        }
    }

    public void uploadButtonOnClick(View view) {
        Toast toast = Toast.makeText(this, R.string.uploadButtonClickMessage, Toast.LENGTH_SHORT);
        toast.show();
    }
}
