package com.shuyue.snack.ui.snack;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.shuyue.snack.MyApplication;
import com.shuyue.snack.R;
import com.shuyue.snack.adaptor.SnackLeftAdapter;
import com.shuyue.snack.adaptor.SnackRightAdapter;
import com.shuyue.snack.animator.MyAnimation;
import com.shuyue.snack.animator.MyAnimation2;
import com.shuyue.snack.animator.MyAnimation3;
import com.shuyue.snack.data.DataServer;
import com.shuyue.snack.model.Snack;
import com.shuyue.snack.utils.Tips;
import com.shuyue.snack.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SnackFragment extends Fragment {

    private SnackViewModel snackViewModel;

    // 小吃页面左边列表已选择的Position
    private int leftSelectPosition = 0;

    @BindView(R.id.snackLeftRecyclerView)
    RecyclerView leftRecyclerview;

    @BindView(R.id.snackRightRecyclerView)
    RecyclerView rightRecyclerView;

    // 右边适配器
    private SnackRightAdapter rightAdapter;

    public static SnackFragment newInstance() {
        return new SnackFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        snackViewModel = ViewModelProviders.of(this).get(SnackViewModel.class);
        View root = inflater.inflate(R.layout.fragment_snack, container, false);
        // 绑定资源
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        leftRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        rightRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        initLeftAdapter();
        initRightAdapter();
    }

    /**
     * 初始化左边适配器
     */
    @SuppressLint("ResourceAsColor")
    private void initLeftAdapter() {
        // 实例化左边适配器对象
        SnackLeftAdapter leftAdapter = new SnackLeftAdapter(DataServer.getSnackOrderList());
        // 设置动画效果
        leftAdapter.setAnimationEnable(true);
        leftAdapter.setAnimationFirstOnly(false);
        leftAdapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.SlideInLeft);

        // 触发点击按钮
        leftAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (position != leftSelectPosition) {
                String item = (String) adapter.getItem(position);

                // 原本选中的item变成未选中颜色
                Objects.requireNonNull(adapter.getViewByPosition(leftSelectPosition, R.id.snackLeftType)).setBackgroundResource(R.color.colorContent);
                // 当前item变成选中颜色
                Objects.requireNonNull(adapter.getViewByPosition(position, R.id.snackLeftType)).setBackgroundResource(R.color.colorBgWhite);
                leftSelectPosition = position;

                // 刷新右边列表
//                    rightAdapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.SlideInBottom);
                rightAdapter.setAnimationEnable(false);
                switch (position) {
                    case 1:
                        rightAdapter.setNewInstance(DataServer.getGuangxiList());
                        break;
                    case 2:
                        rightAdapter.setNewInstance(DataServer.getGuangzhouList());
                        break;
                    case 3:
                        rightAdapter.setNewInstance(DataServer.getBeijingList());
                        break;
                    case  4:
                        rightAdapter.setNewInstance(DataServer.getChongqingList());
                        break;
                    default:
                        rightAdapter.setNewInstance(DataServer.getFujianList());
                        break;
                }
            }
        });

        // 设置左边列表适配器
        leftRecyclerview.setAdapter(leftAdapter);
    }

    /**
     * 初始化右边适配器
     */
    public void initRightAdapter() {
        // 实例化右边适配器对象
        rightAdapter = new SnackRightAdapter(DataServer.getFujianList());
        // 设置动画效果
        rightAdapter.setAnimationEnable(true);
        rightAdapter.setAnimationFirstOnly(false);
        rightAdapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.SlideInRight);
        // 设置尾部
        rightAdapter.addFooterView(getFooterView());

        // 左边列表加入购物车点击事件
        rightAdapter.addChildClickViewIds(R.id.snackRightAddBtn);
        rightAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            Snack snack = (Snack) adapter.getItem(position);
            if (view.getId() == R.id.snackRightAddBtn) {
                if (!MyApplication.getCartSnacks().contains(snack)) {
                    // 添加到购物车
                    MyApplication.getCartSnacks().add(snack);
                    Tips.show("已添加" + snack.getName() + "到购物车");
                } else {
                    Tips.show("已在购物车中，不能重复添加");
                }
            }
        });

        // 设置右边列表适配器
        rightRecyclerView.setAdapter(rightAdapter);
    }

    /**
     * 小吃页面右边RecyclerView尾部View
     */
    private View getFooterView() {
        return getLayoutInflater().inflate(R.layout.footer_no_item, rightRecyclerView, false);
    }
}