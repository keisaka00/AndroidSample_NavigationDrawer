package com.example.samplenavigationdrawer;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.support.design.widget.Snackbar;
import android.support.design.widget.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;

import com.example.samplenavigationdrawer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //
        // ToolBar（画面上部のバー）のレイアウト設定
        //
        setSupportActionBar(binding.appBarMain.layoutMainToolbar);

        //
        // 画面右下のメールボタンを押したときの処理
        // <動作概要>
        // 画面下にバーを表示し、"Replace with your own action"と表示する
        //
        binding.appBarMain.layoutButtonFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                // ※Snackbar
                // 　ユーザーに短いメッセージを表示できます。
                // 　メッセージは、しばらくすると自動的に消えます。
                // 　Snackbar は、ユーザーが必ずしも対処する必要がない短いメッセージに最適です。たとえば、
                // 　メールアプリで Snackbar を使用して、ユーザーにメールが送信されたことを通知できます。
            }
        });

        
        //
        // NavigationDrawerのセットアップ
        // レイアウトと選択したときのアクションをセットアップする
        // host_fragment_main_contentには、navGraphが定義（navigation_mainを使う）しており、
        // navigation_mainには選択時のnav_home/nav_gallery/nav_slideshowそれぞれに対するfragmentが定義してある。
        //
        NavController navController = Navigation.findNavController(this, R.id.host_fragment_main_content);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        DrawerLayout drawer = binding.drawerLayout;
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);

        // ViewMode: Navigation Drawerのviewと、アクティビティーの動作を紐付ける
        NavigationView navigationView = binding.activityNavMenu;
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.host_fragment_main_content);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}