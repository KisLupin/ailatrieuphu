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
import com.t3h.ailatrieuphu.excute.PlayFrag;

public class NotifyFrag extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notify,container,false);
        view.findViewById(R.id.btn_back).setOnClickListener(this);
        view.findViewById(R.id.btn_continue).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back:
                backMain();
                break;
            case R.id.btn_continue:
                play();
                break;
        }
    }

    private void backMain(){
            if (getActivity() == null ){
                return;
            }
            FragmentManager manager = getActivity().getSupportFragmentManager();
            FragmentTransaction ft = manager.beginTransaction();
            Fragment fragment1 = manager.findFragmentByTag(PlayFrag.class.getName());
            ft.remove(fragment1);
            Fragment fragment2 = manager.findFragmentByTag(NotifyFrag.class.getName());
            ft.remove(fragment2);
            MainFrag mainFrag = new MainFrag();
            ft.add(R.id.content,mainFrag,MainFrag.class.getName());
            ft.commit();
    }

    private void play(){
        if (getActivity() == null ){
            return;
        }
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        Fragment fragment1 = manager.findFragmentByTag(PlayFrag.class.getName());
        ft.remove(fragment1);
        Fragment fragment2 = manager.findFragmentByTag(NotifyFrag.class.getName());
        ft.remove(fragment2);
        PlayFrag playFrag = new PlayFrag();
        ft.add(R.id.content,playFrag,PlayFrag.class.getName());
        ft.commit();
    }

}
