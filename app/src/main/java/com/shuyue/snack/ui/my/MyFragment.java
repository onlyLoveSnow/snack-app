package com.shuyue.snack.ui.my;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.shuyue.snack.MyApplication;
import com.shuyue.snack.R;
import com.shuyue.snack.activity.LoginActivity;
import com.shuyue.snack.activity.OrderActivity;
import com.shuyue.snack.dao.UserDao;
import com.shuyue.snack.model.User;
import com.shuyue.snack.utils.Tips;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class MyFragment extends Fragment {

    private MyViewModel myViewModel;

    @BindView(R.id.myUserHead)
    CircleImageView image;

    @BindView(R.id.myUserNickName)
    TextView nickname;

    @BindView(R.id.myUserName)
    TextView username;

    @BindView(R.id.myModifyView)
    LinearLayout modifyView;

    @BindView(R.id.myGeneralView)
    LinearLayout generalView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myViewModel =
                ViewModelProviders.of(this).get(MyViewModel.class);
        View root = inflater.inflate(R.layout.fragment_my, container, false);

        // 绑定资源
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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

    /**
     * 点击头像
     */
    @OnClick(R.id.myUserHead)
    void clickImage() {
        if (MyApplication.isLogin()) {
            Tips.show("已登录");
        } else {
            LoginActivity.actionStart(getActivity());
        }
    }

    /**
     * 点击我的订单
     */
    @OnClick(R.id.myOrderView)
    void clickOrder() {
        if (MyApplication.isLogin()) {
            OrderActivity.actionStart(getContext());
        } else {
            Tips.show("请先登录");
        }
    }

    @OnClick(R.id.myModifyText)
    void clickShowModify() {
        if (modifyView.getVisibility() == View.GONE) {
            modifyView.setVisibility(View.VISIBLE);
        } else {
            modifyView.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.myGeneralText)
    void clickShowGeneral() {
        if (generalView.getVisibility() == View.GONE) {
            generalView.setVisibility(View.VISIBLE);
        } else {
            generalView.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.myModifyBtn)
    void clickModifySubmit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("提示")
                .setMessage("是否保存地址信息")
                .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        modifyView.setVisibility(View.GONE);
                    }
                })
                .create()
                .show();
    }

    @OnClick(R.id.myGeneralBtn)
    void clickGeneralSubmit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("提示")
                .setMessage("是否保存通用设置")
                .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        generalView.setVisibility(View.GONE);
                    }
                })
                .create()
                .show();
    }

    /**
     * 点击退出登录
     */
    @OnClick(R.id.logoutBtn)
    void clickLogout() {
        if (MyApplication.isLogin()) {
            // 清除持久化数据
            UserDao.removeUserAndLoginStatus();
            // 清除全局数据
            MyApplication.isLogin(false);
            MyApplication.setUser(null);
            nickname.setText("未登录");
            username.setText("");
            image.setImageResource(R.mipmap.logo);
        } else {
            Tips.show("还没有登录，请先登录");
        }
    }
}