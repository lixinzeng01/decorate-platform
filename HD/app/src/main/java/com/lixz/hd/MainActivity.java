package com.lixz.hd;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.lixz.hd.activity.BaseActivity;
import com.lixz.hd.fragment.MyFragment;
import com.lixz.hd.fragment.MyProjectFragment;
import com.lixz.hd.fragment.MyTaskFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private MyTaskFragment mMyTaskFragment;
    private MyProjectFragment mMyProjectFragment;
    private MyFragment mMyFragment;

    private static final String TASK_FRAGMENT_KEY="MyTaskFragment";
    private static final String PROJECT_FRAGMENT_KEY="MyProjectFragment";
    private static final String MY_FRAGMENT_KEY="MyFragment";

    private List<Fragment> mFragmentList=new ArrayList<>();

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
                    addFragment(mMyTaskFragment);
                    showFragment(mMyTaskFragment);
                    return true;
                case R.id.navigation_project:
                    if(mMyProjectFragment==null){
                        mMyProjectFragment=new MyProjectFragment();
                    }
                    addFragment(mMyProjectFragment);
                    showFragment(mMyProjectFragment);
                    return true;
                case R.id.navigation_my:
                    if(mMyFragment==null){
                        mMyFragment=new MyFragment();
                    }
                    addFragment(mMyFragment);
                    showFragment(mMyFragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        if(savedInstanceState!=null){
            //获取保存的fragment
            mMyTaskFragment = (MyTaskFragment) getSupportFragmentManager().getFragment(savedInstanceState, TASK_FRAGMENT_KEY);
            mMyProjectFragment = (MyProjectFragment) getSupportFragmentManager().getFragment(savedInstanceState, PROJECT_FRAGMENT_KEY);
            mMyFragment= (MyFragment) getSupportFragmentManager().getFragment(savedInstanceState,MY_FRAGMENT_KEY);

            addToList(mMyTaskFragment);
            addToList(mMyProjectFragment);
            addToList(mMyFragment);
        }else {
            initFragment();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //fragment不为空时保存
        if(mMyTaskFragment!=null){
            getSupportFragmentManager().putFragment(outState,TASK_FRAGMENT_KEY,mMyTaskFragment);
        }
        if(mMyProjectFragment!=null){
            getSupportFragmentManager().putFragment(outState,PROJECT_FRAGMENT_KEY,mMyProjectFragment);
        }
        if(mMyFragment!=null){
            getSupportFragmentManager().putFragment(outState,MY_FRAGMENT_KEY,mMyFragment);
        }
        super.onSaveInstanceState(outState);
    }

    private void initView() {
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void addToList(Fragment fragment){
        if(fragment!=null){
            mFragmentList.add(fragment);
        }
    }

    private void initFragment(){
        //默认显示TaskFragment
        mMyTaskFragment=new MyTaskFragment();
        addFragment(mMyTaskFragment);
        showFragment(mMyTaskFragment);
    }

    private void addFragment(Fragment fragment){
        if(!fragment.isAdded()){
            getSupportFragmentManager().beginTransaction().add(R.id.tab,fragment).commit();
            mFragmentList.add(fragment);
        }
    }

    private void showFragment(Fragment fragment){
        for (Fragment frag: mFragmentList){
            if(frag!=fragment){
                getSupportFragmentManager().beginTransaction().hide(frag).commit();
            }
        }
        getSupportFragmentManager().beginTransaction().show(fragment).commit();
    }

}
