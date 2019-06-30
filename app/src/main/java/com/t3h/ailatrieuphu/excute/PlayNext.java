package com.t3h.ailatrieuphu.excute;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.t3h.ailatrieuphu.frag.ChartFrag;
import com.t3h.ailatrieuphu.frag.FalseAnsFrag;
import com.t3h.ailatrieuphu.database.ListFalseAns;
import com.t3h.ailatrieuphu.database.ListQuestion;
import com.t3h.ailatrieuphu.database.ManagerSql;
import com.t3h.ailatrieuphu.frag.NotifyFrag;
import com.t3h.ailatrieuphu.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayNext extends Fragment implements View.OnClickListener,Runnable {

    private TextView question;
    private Button ans1;
    private Button ans2;
    private Button ans3;
    private Button ans4;
    private Button money;
    private Button order;
    private String trueAns;
    private List<ListQuestion> listQuestions;
    private ManagerSql managerSql;
    private int temp = 0;
    private boolean isClick;
    private ImageView help50;
    private ImageView help_call;
    private ImageView help_chart;
    private ImageView help_change;
    private Handler handler;
    private Button time;
    private List<ListFalseAns> falseAns;
    private int timeCount = 30;
    private boolean stop;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.play,container,false);
        createData(view);

        intHand();
        Thread th = new Thread( this);
        th.start();

        return view;
    }

    private void inits(int temp){
        question.setText(listQuestions.get(temp).getQuestion());
        ans1.setText(listQuestions.get(temp).getCaseA());
        ans2.setText(listQuestions.get(temp).getCaseB());
        ans3.setText(listQuestions.get(temp).getCaseC());
        ans4.setText(listQuestions.get(temp).getCaseD());
        order.setText("Cau "+(temp+1));
    }

    private void createData(View view){
        help50 = view.findViewById(R.id.help5050);
        help_call = view.findViewById(R.id.help_call);
        help_chart = view.findViewById(R.id.help_chart);
        help_change = view.findViewById(R.id.help_change);
        time = view.findViewById(R.id.time);
        help50.setOnClickListener(this);
        help_call.setOnClickListener(this);
        help_chart.setOnClickListener(this);
        help_change.setOnClickListener(this);

        question = view.findViewById(R.id.question);
        ans1 = view.findViewById(R.id.answer_1);
        ans1.setOnClickListener(this);
        ans2 = view.findViewById(R.id.answer_2);
        ans2.setOnClickListener(this);
        ans3 = view.findViewById(R.id.answer_3);
        ans3.setOnClickListener(this);
        ans4 = view.findViewById(R.id.answer_4);
        ans4.setOnClickListener(this);
        money = view.findViewById(R.id.money);
        order = view.findViewById(R.id.order);

        managerSql = new ManagerSql(getContext());
        listQuestions = managerSql.getAllQuestion();
        inits(temp);

        if (listQuestions.get(temp).getTrueCase().equals("1")){
            trueAns = listQuestions.get(temp).getCaseA();
            falseAns = new ArrayList<>();
            falseAns.add(new ListFalseAns(
                    listQuestions.get(temp).getCaseB()
                    ,listQuestions.get(temp).getCaseC()
                    ,listQuestions.get(temp).getCaseD(),
                    "A"));
        }
        if (listQuestions.get(temp).getTrueCase().equals("2")){
            trueAns = listQuestions.get(temp).getCaseB();
            falseAns = new ArrayList<>();
            falseAns.add(new ListFalseAns(
                    listQuestions.get(temp).getCaseA()
                    ,listQuestions.get(temp).getCaseC()
                    ,listQuestions.get(temp).getCaseD()
                    ,"B"));
        }
        if (listQuestions.get(temp).getTrueCase().equals("3")){
            trueAns = listQuestions.get(temp).getCaseC();
            falseAns = new ArrayList<>();
            falseAns.add(new ListFalseAns(
                    listQuestions.get(temp).getCaseA()
                    ,listQuestions.get(temp).getCaseB()
                    ,listQuestions.get(temp).getCaseD()
                    ,"C"));
        }
        if (listQuestions.get(temp).getTrueCase().equals("4")){
            trueAns = listQuestions.get(temp).getCaseD();
            falseAns = new ArrayList<>();
            falseAns.add(new ListFalseAns(
                    listQuestions.get(temp).getCaseA()
                    ,listQuestions.get(temp).getCaseB()
                    ,listQuestions.get(temp).getCaseC(),
                    "D"));
        }
    }

    private void intHand(){
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 0:
                        int num = (Integer) msg.obj;
                        time.setText(num+"");
                        break;
                }
            }
        };
    }
    @Override
    public void run() {
        while (!stop){
            Message message = new Message();
            message.what = 0;
            message.obj = timeCount;
            message.setTarget(handler);
            handler.sendMessage(message);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (timeCount == 0){
                stop = true;
                showNotify();
            }
            timeCount --;
        }
    }

    @Override
    public void onClick(View v) {
        if (isClick){
            return;
        }
        final Handler handler = new Handler();
        switch (v.getId()){
            case R.id.answer_1:
                isClick = true;
                stop = true;
                ans1.setBackgroundResource(R.drawable.bg_choose2);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (trueAns.equals(ans1.getText())){
                            ans1.setBackgroundResource(R.drawable.bg_true1);
                            temp++;
                            money.setText(setMoney(temp));
                            sendInfo(temp);
                        }else {
                            ans1.setBackgroundResource(R.drawable.bg_faile1);
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    showFalseAnsFrag();
                                }
                            },1000);
                        }
                    }
                },3000);
                break;
            case R.id.answer_2:
                isClick = true;
                stop = true;
                ans2.setBackgroundResource(R.drawable.bg_choose2);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        handler.postDelayed(this,2000);
                        if (trueAns.equals(ans2.getText())){
                            ans2.setBackgroundResource(R.drawable.bg_true1);
                            temp++;
                            money.setText(setMoney(temp));
                            sendInfo(temp);
                        }else {
                            ans2.setBackgroundResource(R.drawable.bg_faile1);
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    showFalseAnsFrag();
                                }
                            },1000);
                        }
                    }
                },1000);
                break;
            case R.id.answer_3:
                isClick = true;
                stop = true;
                ans3.setBackgroundResource(R.drawable.bg_choose2);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        handler.postDelayed(this,2000);
                        if (trueAns.equals(ans3.getText())){
                            ans3.setBackgroundResource(R.drawable.bg_true1);
                            temp++;
                            money.setText(setMoney(temp));
                            sendInfo(temp);
                        }else {
                            ans3.setBackgroundResource(R.drawable.bg_faile1);
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    showFalseAnsFrag();
                                }
                            },1000);
                        }
                    }
                },1000);
                break;
            case R.id.answer_4:
                isClick = true;
                stop = true;
                ans4.setBackgroundResource(R.drawable.bg_choose2);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        handler.postDelayed(this,2000);
                        if (trueAns.equals(ans4.getText())){
                            ans4.setBackgroundResource(R.drawable.bg_true1);
                            temp++;
                            money.setText(setMoney(temp));
                            sendInfo(temp);
                        }else {
                            ans4.setBackgroundResource(R.drawable.bg_faile1);
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    showFalseAnsFrag();
                                }
                            },1000);
                        }
                    }
                },1000);
                break;
            case R.id.help5050:
                help5050();
                break;
            case R.id.help_call:
                break;
            case R.id.help_chart:
                askAudience();
                break;
            case R.id.help_change:
                changeQuestion();
                break;
        }
    }

    private void sendInfo(int temp){
        Intent intent = new Intent();
        intent.putExtra("TEMP",temp);

        if (getActivity() == null) return;
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        Fragment fragment = manager.findFragmentByTag(PlayFrag.class.getName());
        ft.remove(fragment);
        PlayNext playNext = new PlayNext();
        ft.add(R.id.content,playNext,PlayNext.class.getName());
        ft.commit();
    }

    private void help5050(){
        int random = new Random().nextInt(2);
        switch (random){
            case 0:
                falseAns.get(0).setA1("");
                falseAns.get(0).setA2("");
                break;
            case 1:
                falseAns.get(0).setA1("");
                falseAns.get(0).setA3("");
                break;
            case 2:
                falseAns.get(0).setA3("");
                falseAns.get(0).setA2("");
                break;
        }
        if (falseAns.get(0).getTrueAns()=="A"){
            ans2.setText(falseAns.get(0).getA1());
            ans3.setText(falseAns.get(0).getA2());
            ans4.setText(falseAns.get(0).getA3());
        }
        if (falseAns.get(0).getTrueAns()=="B"){
            ans1.setText(falseAns.get(0).getA1());
            ans3.setText(falseAns.get(0).getA2());
            ans4.setText(falseAns.get(0).getA3());
        }
        if (falseAns.get(0).getTrueAns()=="C"){
            ans1.setText(falseAns.get(0).getA1());
            ans2.setText(falseAns.get(0).getA2());
            ans4.setText(falseAns.get(0).getA3());
        }
        if (falseAns.get(0).getTrueAns()=="D"){
            ans1.setText(falseAns.get(0).getA1());
            ans2.setText(falseAns.get(0).getA2());
            ans3.setText(falseAns.get(0).getA3());
        }
    }

    private void askAudience(){
        if (getActivity() == null) return;

        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ChartFrag chartFrag = new ChartFrag();
        ft.add(R.id.notify_out_time,chartFrag, NotifyFrag.class.getName());
        ft.commit();
    }

    private void changeQuestion(){
        if (getActivity() == null) return;

        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        Fragment fragment = manager.findFragmentByTag(PlayFrag.class.getName());
        ft.remove(fragment);
        PlayFrag playFrag = new PlayFrag();
        ft.add(R.id.content,playFrag,PlayFrag.class.getName());
        ft.commit();
    }

    private String setMoney(int temp){
        if (temp == 1){
            return "$ 1000";
        }
        if (temp == 2){
            return "$ 2000";
        }
        else return "$ 0";
    }

    private void showNotify(){
        if (getActivity() == null) return;

        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        NotifyFrag notifyFrag = new NotifyFrag();
        ft.add(R.id.notify_out_time,notifyFrag,NotifyFrag.class.getName());
        ft.commit();
    }

    private void showFalseAnsFrag(){
        if (getActivity() == null) return;

        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        FalseAnsFrag falseAnsFrag = new FalseAnsFrag();
        ft.add(R.id.notify_out_time,falseAnsFrag,FalseAnsFrag.class.getName());
        ft.commit();
    }
}
