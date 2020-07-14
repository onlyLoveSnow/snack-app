package com.shuyue.snack.ui.place;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.shuyue.snack.MyApplication;
import com.shuyue.snack.R;
import com.shuyue.snack.adaptor.PlaceOrderAdapter;
import com.shuyue.snack.model.Snack;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlaceFragment extends Fragment {

    private PlaceViewModel placeViewModel;

    @BindView(R.id.placeRecyclerView)
    RecyclerView orderRecyclerView;

    @BindView(R.id.placeBuyBtn)
    Button buyButton;

    @BindView(R.id.placeMoney)
    TextView placeMoney;

    PlaceOrderAdapter orderAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        placeViewModel =
                ViewModelProviders.of(this).get(PlaceViewModel.class);
        View root = inflater.inflate(R.layout.fragment_place, container, false);
        // 绑定资源
        ButterKnife.bind(this, root);
//        final TextView textView = root.findViewById(R.id.text_notifications);
//        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        orderRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        initOrderAdapter();
//        initClick();
    }

    @Override
    public void onResume() {
        super.onResume();
        // 每次页面显示都计算合计金额
        calcTotalMoney();
    }

    /**
     * 初始化购物车列表适配器
     */
    private void initOrderAdapter() {
        // 实例化购物车列表适配器对象
        orderAdapter = new PlaceOrderAdapter(MyApplication.getCartSnacks());
        // 设置动画效果
        orderAdapter.setAnimationEnable(true);
        orderAdapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.ScaleIn);

        // 注册item内子控件id
        orderAdapter.addChildClickViewIds(R.id.orderLessLabel, R.id.orderAddLabel);
        // 子控件点击监听
        orderAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter adapter, @NonNull View view, int position) {
                Snack snack = (Snack) adapter.getItem(position);
                switch (view.getId()) {
                    case R.id.orderLessLabel:
                        // 点击减少数量
                        if (snack.getCount() == 1) {
                            MyApplication.getCartSnacks().remove(position);
                        } else {
                            MyApplication.getCartSnacks().get(position).setCount(snack.getCount() - 1);
                        }

                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.orderAddLabel:
                        // 点击添加数量
                        MyApplication.getCartSnacks().get(position).setCount(snack.getCount() + 1);
                        adapter.notifyDataSetChanged();
                        break;
                    default:
                        break;
                }
                calcTotalMoney();
            }
        });


        // 设置适配器
        orderRecyclerView.setAdapter(orderAdapter);
    }

    // 点击事件触发器
    @OnClick(R.id.placeBuyBtn)
    void initClick() {
        // 清空购物车数据
        MyApplication.getCartSnacks().removeAll(MyApplication.getCartSnacks());
        // 通知适配器数据变化
        orderAdapter.notifyDataSetChanged();
        // 刷新总金额
        calcTotalMoney();

        Toast.makeText(getActivity(), "下单成功", Toast.LENGTH_SHORT).show();
    }

    // 计算合计金额
    @SuppressLint("SetTextI18n")
    private void calcTotalMoney() {
        BigDecimal totalMoney = new BigDecimal("0");

        // 遍历计算总金额
        if (!MyApplication.getCartSnacks().isEmpty()) {
            for (Snack snack : MyApplication.getCartSnacks()) {
                totalMoney = totalMoney.add(BigDecimal.valueOf(snack.getPrice() * snack.getCount()));
            }
        }

        placeMoney.setText("￥" + totalMoney.toString());
    }
}