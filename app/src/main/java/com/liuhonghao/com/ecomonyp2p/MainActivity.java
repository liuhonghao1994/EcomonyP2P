package com.liuhonghao.com.ecomonyp2p;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.liuhonghao.com.ecomonyp2p.base.BaseFragment;
import com.liuhonghao.com.ecomonyp2p.command.AppManager;
import com.liuhonghao.com.ecomonyp2p.fragment.HomeFragment;
import com.liuhonghao.com.ecomonyp2p.fragment.InvestFragment;
import com.liuhonghao.com.ecomonyp2p.fragment.MoreFragment;
import com.liuhonghao.com.ecomonyp2p.fragment.PropertityFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;



public class MainActivity extends AppCompatActivity {
    RadioGroup mainRg;
    private List<BaseFragment> fragments;
    private int position;
    //缓存fragment用来存放切以后被切换过的碎片
    private Fragment countfragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainRg= (RadioGroup) findViewById(R.id.main_rg);
        //添加到栈
        AppManager.getInstance().addActivity(this);
        //初始化fragment
        initFragmenr();

        //监听
        intListener();
    }


    private void intListener() {

        mainRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                switch (id) {
                    case R.id.rb_main:
                        position = 0;
                        break;
                    case R.id.rb_invest:
                        position = 1;
                        break;
                    case R.id.rb_propert:
                        position = 2;
                        break;
                    case R.id.rb_more:
                        position = 3;
                        break;
                }
                Fragment baseFragment = fragments.get(position);
                SwitchFragment(baseFragment);
            }
        });
        mainRg.check(R.id.rb_main);
        SwitchFragment(fragments.get(0));
    }

    private void SwitchFragment(Fragment baseFragment) {
        if (baseFragment != countfragment) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if (!baseFragment.isAdded()) {
                if (countfragment != null) {
                    ft.hide(countfragment);
                }
                ft.add(R.id.fl_main_content, baseFragment);

            } else {
                if (countfragment != null) {
                    ft.hide(countfragment);
                }
                ft.show(baseFragment);
            }
            ft.commit();
            countfragment = baseFragment;

        }
    }
    private void initFragmenr() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());//0
        fragments.add(new InvestFragment());//1
        fragments.add(new PropertityFragment());
        fragments.add(new MoreFragment());

    }
    private boolean isExit=false;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getAction()==KeyEvent.ACTION_DOWN){
            if(isExit){
                finish();
            }
            Toast.makeText(MainActivity.this, "再次点击退出应用哦", Toast.LENGTH_SHORT).show();
            isExit=true;
            //事件计数器
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                        isExit=false;
                }
            },2000);
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().removeActivity(this);

    }
}
