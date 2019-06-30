package com.t3h.ailatrieuphu.frag;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.t3h.ailatrieuphu.MainActivity;
import com.t3h.ailatrieuphu.R;

public class MainFrag extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main,container,false);
        view.findViewById(R.id.instrm).setOnClickListener(this);
        view.findViewById(R.id.begin).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        FragmentActivity ac = getActivity();
        switch (v.getId()){
            case R.id.begin:
                ((MainActivity)ac).openPlay();
                break;
            case R.id.instrm:
                ((MainActivity)ac).openInstr();
                break;
        }
    }


}
