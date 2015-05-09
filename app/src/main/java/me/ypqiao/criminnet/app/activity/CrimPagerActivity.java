package me.ypqiao.criminnet.app.activity;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import me.ypqiao.criminnet.app.R;
import me.ypqiao.criminnet.app.fragment.CrimeFragment;
import me.ypqiao.criminnet.app.model.Crim;
import me.ypqiao.criminnet.app.model.CrimLab;

import java.util.List;
import java.util.UUID;

/**
 * Created by ypqiao on 4/3/2015.
 */
public class CrimPagerActivity extends FragmentActivity {

    private List<Crim> crimList;
    private ViewPager fragment_crim_viewPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        crimList = CrimLab.getCrimLab(this).getCrimList();

        fragment_crim_viewPage = new ViewPager(this);
        fragment_crim_viewPage.setId(R.id.fragment_crim_viewPage);
        setContentView(fragment_crim_viewPage);

        FragmentManager fm = getSupportFragmentManager();
        fragment_crim_viewPage.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {

                Crim crim = crimList.get(position);
                return CrimeFragment.newInstance(crim.getId());
            }

            @Override
            public int getCount() {
                return crimList.size();
            }
        });


        UUID uuid = (UUID)getIntent().getSerializableExtra(CrimeFragment.CRIM_ID_KEY);
        for(int i=0; i<crimList.size(); i++)
            if(crimList.get(i).getId().equals(uuid))
                fragment_crim_viewPage.setCurrentItem(i);

        fragment_crim_viewPage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Crim crim = crimList.get(position);
                setTitle(crim.getTitle());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
