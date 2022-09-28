package com.zkhc.recycle_cloud.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.zkhc.recycle_cloud.R;

import java.util.List;

import butterknife.ButterKnife;

public class MetalFragment extends Fragment {

//    @BindView(R.id.id_tv_frg)
//    TextView tv_show;
    private int xml;
    private List<Integer> list;

    private static final String FRG_PARAM = "param";
    private String mParam;


    public MetalFragment() {
    }

    public static MetalFragment newInstance(String param) {
        MetalFragment fragment = new MetalFragment();
        Bundle args = new Bundle();
        args.putString(FRG_PARAM, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam = getArguments().getString(FRG_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.metal, container, false);
        ButterKnife.bind(this, view);
//        tv_show.setText(mParam);
        return view;
    }

}

