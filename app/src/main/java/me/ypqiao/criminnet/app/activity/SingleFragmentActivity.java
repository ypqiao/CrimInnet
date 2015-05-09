package me.ypqiao.criminnet.app.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import me.ypqiao.criminnet.app.R;

/**
 * Created by ypqiao on 3/31/2015.
 */
public abstract  class SingleFragmentActivity extends FragmentActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

        if(fragment == null)
            fragment = createFragment();

        fm.beginTransaction().add(R.id.fragmentContainer,fragment).commit();


    }


    protected abstract Fragment createFragment();

}
