package com.t3h.ailatrieuphu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.t3h.ailatrieuphu.excute.PlayFrag;
import com.t3h.ailatrieuphu.frag.InstrFrag;
import com.t3h.ailatrieuphu.frag.MainFrag;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        MainFrag mainFrag = new MainFrag();
        ft.add(R.id.content,mainFrag,MainFrag.class.getName());
        ft.commit();
    }

    public void openPlay() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        Fragment fragment = manager.findFragmentByTag(MainFrag.class.getName());
        ft.remove(fragment);
        PlayFrag playFrag = new PlayFrag();
        ft.add(R.id.content,playFrag,PlayFrag.class.getName());
        ft.commit();
    }

    public void openInstr(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        Fragment fragment = manager.findFragmentByTag(MainFrag.class.getName());
        ft.remove(fragment);
        InstrFrag instrFrag = new InstrFrag();
        ft.add(R.id.content,instrFrag,InstrFrag.class.getName());
        ft.commit();
    }
}
