package me.ypqiao.criminnet.app.activity;


import android.support.v4.app.Fragment;
import me.ypqiao.criminnet.app.fragment.CrimListFragment;

public class CrimListActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return new CrimListFragment();
    }
}
