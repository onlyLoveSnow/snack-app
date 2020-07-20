package com.shuyue.snack.ui.my;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.shuyue.snack.MyApplication;
import com.shuyue.snack.R;
import com.shuyue.snack.activity.LoginActivity;
import com.shuyue.snack.dao.UserDao;
import com.shuyue.snack.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class MyFragment extends Fragment {

    private MyViewModel dashboardViewModel;

    @BindView(R.id.myUserHead)
    CircleImageView image;

    @BindView(R.id.myUserNickName)
    TextView nickname;

    @BindView(R.id.myUserName)
    TextView username;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(MyViewModel.class);
        View root = inflater.inflate(R.layout.fragment_my, container, false);

        // 绑定资源
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        checkLogin();
        initView();
    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
    }

    @SuppressLint("SetTextI18n")
    private void initView() {
        if (MyApplication.isLogin()) {
            User user = MyApplication.getUser();
            image.setImageResource(user.getHeadImage());
            nickname.setText(user.getNickname());
            username.setText("账号: " + user.getUsername());
        }
    }

    private void checkLogin() {
        if (MyApplication.isLogin()) {
            User user = MyApplication.getUser();
            image.setImageResource(user.getHeadImage());
            username.setText(user.getUsername());
            nickname.setText(user.getNickname());
        }
    }

    @OnClick(R.id.myUserHead)
    void clickImage() {
        LoginActivity.actionStart(getActivity());
    }

    @OnClick(R.id.logoutBtn)
    void logout() {
        // 清除持久化数据
        UserDao.removeUserAndLoginStatus();
        // 清除全局数据
        MyApplication.isLogin(false);
        MyApplication.setUser(null);
        nickname.setText("未登录");
        username.setText("");
        image.setImageResource(R.mipmap.avatar);
    }
}