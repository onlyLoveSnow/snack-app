package com.shuyue.snack.ui.snack;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.shuyue.snack.animator.SnackLeftAnimation;
import com.shuyue.snack.data.DataServer;
import com.shuyue.snack.model.Snack;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SnackFragment extends Fragment {

    @BindView(R.id.snackLeftRecyclerView)
    RecyclerView leftRecyclerview;

    @BindView(R.id.snackRightRecyclerView)
    RecyclerView rightRecyclerView;

    private SnackViewModel mViewModel;

    public static SnackFragment newInstance() {
        return new SnackFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_snack, container, false);
        // 绑定资源
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SnackViewModel.class);

        leftRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        rightRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        initLeftAdapter();
        initRightAdapter();
    }

    private void initLeftAdapter() {
        SnackLeftAdapter leftAdapter = new SnackLeftAdapter(DataServer.getTypeData());
        leftAdapter.setAnimationEnable(true);
        leftAdapter.setAdapterAnimation(new SnackLeftAnimation());

        // 触发点击按钮
        leftAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                String item = (String) adapter.getItem(position);
                Toast.makeText(getActivity(), "点击了" + item, Toast.LENGTH_SHORT).show();
            }
        });

        // 设置左边列表适配器
        leftRecyclerview.setAdapter(leftAdapter);
    }

    public void initRightAdapter() {
        SnackRightAdapter rightAdapter = new SnackRightAdapter(DataServer.getSnack());
        rightAdapter.setAnimationEnable(true);
        rightAdapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.SlideInRight);

        // 左边列表点击事件
        rightAdapter.addChildClickViewIds(R.id.snackRightAddBtn);
        rightAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            Snack snack = (Snack) adapter.getItem(position);
            if (view.getId() == R.id.snackRightAddBtn) {
                // 添加到购物车
                MyApplication.getCartSnacks().add(snack);
                Toast.makeText(getActivity(), "已添加" + snack.getName() + "到购物车", Toast.LENGTH_SHORT).show();
            }
        });

        // 设置右边列表适配器
        rightRecyclerView.setAdapter(rightAdapter);
    }
}