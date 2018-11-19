package com.example.santai.demolai_2.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.santai.demolai_2.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;


public class SettingActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private DrawerLayout drawerLayout;
    private NavigationView navigation_view;
    //google帳號使用
    private GoogleSignInAccount mGoogleSignInAccount;
    private GoogleSignInClient mGoogleSignInClient;
    private GoogleSignInOptions gso;
    private SignInButton signInButton;
    static int RC_SIGN_IN = 100;
    //google帳號使用結束

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    // Check for existing Google Sign In account, if the user is already signed in the GoogleSignInAccount will be non-null.
        mGoogleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);
        signInButton = (SignInButton) findViewById(R.id.sign_in_button);//抓取畫面元件
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(intent,RC_SIGN_IN);
            }
        });


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
                    intent.setClass(SettingActivity.this, todaysRankActivity.class);
                    startActivity(intent);

                    SettingActivity.this.finish();//結束目前 Activity
                    return true;
                }
                else if (id == R.id.myVideo) {
                    // 按下「我的影片」要做的事
                    Intent intent = new Intent();
                    intent.setClass(SettingActivity.this, MyVideoActivity.class);
                    startActivity(intent);

                    SettingActivity.this.finish();//結束目前 Activity
                    return true;
                } else if (id == R.id.settings) {
                    // 按下「設定」要做的事
//                    Intent intent = new Intent();
//                    intent.setClass(SettingActivity.this, SettingActivity.class);
//                    startActivity(intent);
//
//                    SettingActivity.this.finish();//結束目前 Activity

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

    public void signInButtonOnClick(View view) {
        Log.e("1111","1111111111111111");
        Toast toast = Toast.makeText(this, R.string.uploadButtonClickMessage, Toast.LENGTH_SHORT);
        toast.show();
    }
    public void onCLickSignInButton(View view) {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi
                    .getSignInResultFromIntent(data);

            if (result.isSuccess()){
                mGoogleSignInAccount
                        = result.getSignInAccount();
                String displayName="";
                if(mGoogleSignInAccount!=null)
                displayName=mGoogleSignInAccount.getDisplayName().toString();
                Toast toast = Toast.makeText(this, R.string.login_in_success+displayName, Toast.LENGTH_SHORT);
                toast.show();
                Log.e("accout","getDisplayName"+mGoogleSignInAccount.getDisplayName());
                Log.e("accout","getId"+mGoogleSignInAccount.getId());
                Log.e("accout","getEmail"+mGoogleSignInAccount.getEmail());
            }else{
                Log.e("accout","Sign in fail");
            }
        }
    }

}
