package com.shuyue.snack.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.shuyue.snack.R;
import com.shuyue.snack.activity.DetailActivity;
import com.shuyue.snack.adaptor.HomeAdapter;
import com.shuyue.snack.animator.MyAnimation3;
import com.shuyue.snack.data.DataServer;
import com.shuyue.snack.model.Snack;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    @BindView(R.id.homeRecyclerView)
    RecyclerView homeRecyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        // 绑定资源
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // 首页瀑布流列表
        homeRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        initHomeAdapter();
    }

    private void initHomeAdapter() {
        // 实例化购物车列表适配器对象
        HomeAdapter adapter = new HomeAdapter(DataServer.getHomeList());
        // 设置动画效果
        adapter.setAnimationEnable(true);
//        adapter.setAnimationFirstOnly(false);
//        adapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.SlideInBottom);
        adapter.setAdapterAnimation(new MyAnimation3());
        // 设置头部
        adapter.setHeaderView(getHeadView(), 1);
        // 设置尾部
        adapter.setFooterView(getFooterView(), 1);

        // 点击事件监听器
        adapter.setOnItemClickListener((adapter1, view, position) -> {
            Snack snack = (Snack) adapter1.getItem(position);
            DetailActivity.actionStart(getContext(), snack);
        });

        // 设置适配器
        homeRecyclerView.setAdapter(adapter);
    }

    /**
     * 首页RecyclerView头部View
     */
    private View getHeadView() {
        return getLayoutInflater().inflate(R.layout.head_home_image, homeRecyclerView, false);
    }

    /**
     * 首页RecyclerView尾部View
     */
    private View getFooterView() {
        return getLayoutInflater().inflate(R.layout.footer_no_item, homeRecyclerView, false);
    }
}