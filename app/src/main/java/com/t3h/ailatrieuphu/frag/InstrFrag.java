package com.t3h.ailatrieuphu.frag;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.t3h.ailatrieuphu.R;

public class InstrFrag extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.instruction,container,false);
        view.findViewById(R.id.btn_back).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        Fragment fragment = manager.findFragmentByTag(InstrFrag.class.getName());
        ft.remove(fragment);
        MainFrag mainFrag = new MainFrag();
        ft.add(R.id.content,mainFrag,MainFrag.class.getName());
        ft.commit();
    }
}
