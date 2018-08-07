package com.lixz.hd;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.lixz.hd.activity.BaseActivity;
import com.lixz.hd.fragment.MyFragment;
import com.lixz.hd.fragment.MyProjectFragment;
import com.lixz.hd.fragment.MyTaskFragment;

public class MainActivityBackups extends BaseActivity {

    //private FragmentManager fm;
    //private FragmentTransaction ft;

    private MyTaskFragment mMyTaskFragment;
    private MyProjectFragment mMyProjectFragment;
    private MyFragment mMyFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction ft=fm.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_task:
                    if(mMyTaskFragment==null){
                        mMyTaskFragment=new MyTaskFragment();
                    }
                    ft.replace(R.id.tab,mMyTaskFragment);
                    ft.commit();
                    return true;
                case R.id.navigation_project:
                    if(mMyProjectFragment==null){
                        mMyProjectFragment=new MyProjectFragment();
                    }
                    ft.replace(R.id.tab,mMyProjectFragment);
                    ft.commit();
                    return true;
                case R.id.navigation_my:
                    if(mMyFragment==null){
                        mMyFragment=new MyFragment();
                    }
                    ft.replace(R.id.tab,mMyFragment);
                    ft.commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        setDefaultFragment();//设置默认导航栏
    }

    /**
     * 设置默认导航栏
     */
    private void setDefaultFragment(){
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        mMyTaskFragment=new MyTaskFragment();
        ft.add(R.id.tab,mMyTaskFragment);
        ft.commit();
    }

}
