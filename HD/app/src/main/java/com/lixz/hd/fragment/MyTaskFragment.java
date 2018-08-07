package com.lixz.hd.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lixz.hd.MainActivity;
import com.lixz.hd.R;

public class MyTaskFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_my_task,container,false);
        //FragmentMyTaskBinding binding=DataBindingUtil.setContentView(getActivity(), R.layout.fragment_my_task);
        return view;
    }
}
