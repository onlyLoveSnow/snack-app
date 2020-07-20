package com.shuyue.snack.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.shuyue.snack.MyApplication;
import com.shuyue.snack.R;
import com.shuyue.snack.dao.UserDao;
import com.shuyue.snack.data.DataServer;
import com.shuyue.snack.model.User;
import com.shuyue.snack.utils.Tips;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.loginUsernameEdit)
    EditText usernameEdit;

    @BindView(R.id.loginPasswordEdit)
    EditText passwordEdit;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("登录");

        ButterKnife.bind(this);
    }

    @OnClick(R.id.loginBtn)
    void login() {
        String username = usernameEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        User user = new User(username, password);

        User loginUser = checkUserFromAccountList(user);
        if (loginUser != null) {
            // 登录成功
            Tips.show("登录成功");

            MyApplication.isLogin(true);
            MyApplication.setUser(loginUser);

            // 持久化已登录用户数据
            UserDao.setUser(loginUser);
            UserDao.isLogin(true);

            // 关闭Activity
            finish();
        } else {
            // 登录失败
            Tips.show("登录失败");
            passwordEdit.setText("");
        }
    }

    /**
     * 登录
     *
     * @return 登录成功: 查询到的用户对象, 登录失败: null
     */
    public User checkUserFromAccountList(User u) {
        for (User tmp : DataServer.getAccountList()) {
            if (tmp.equals(u)) {
                return tmp;
            }
        }

        return null;
    }
}