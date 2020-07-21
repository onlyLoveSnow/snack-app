package com.shuyue.snack;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.shuyue.snack.dao.UserDao;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // 将每个菜单ID作为一组ID传递
        // 因为每个菜单都应该被视为顶级路径
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_snack, R.id.navigation_place, R.id.navigation_my)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        // 检查登录状态
        checkLogin();
    }

    /**
     * 检查是否有登录信息
     */
    private void checkLogin() {
        // 检查持久化的数据
        if (UserDao.isLogin()) {
            // 已登录
            MyApplication.isLogin(true);
            MyApplication.setUser(UserDao.getUser());
        } else {
            // 未登录
            MyApplication.isLogin(false);
            MyApplication.setUser(null);
        }
    }
}