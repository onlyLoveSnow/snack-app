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
import com.shuyue.snack.R;
import com.shuyue.snack.adaptor.SnackLeftAdapter;
import com.shuyue.snack.animator.SnackLeftAnimation;
import com.shuyue.snack.data.DataServer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SnackFragment extends Fragment {

    @BindView(R.id.snackLeftRecyclerView)
    RecyclerView leftRecyclerview;

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
        // TODO: Use the ViewModel
        leftRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

        initLeftAdapter();
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

    }
}