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

    private SnackViewModel mViewModel;

    public static SnackFragment newInstance() {
        return new SnackFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_snack, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(SnackViewModel.class);
        // TODO: Use the ViewModel
     }

}