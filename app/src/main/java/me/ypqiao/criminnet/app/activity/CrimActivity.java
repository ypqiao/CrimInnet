package me.ypqiao.criminnet.app.activity;



import android.support.v4.app.Fragment;
import me.ypqiao.criminnet.app.fragment.CrimeFragment;

import java.util.UUID;

public class CrimActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {

        UUID uuid = (UUID)getIntent().getSerializableExtra(CrimeFragment.CRIM_ID_KEY);

        return new CrimeFragment().newInstance(uuid);
    }

}
