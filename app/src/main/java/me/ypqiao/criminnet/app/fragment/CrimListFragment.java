package me.ypqiao.criminnet.app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import me.ypqiao.criminnet.app.R;
import me.ypqiao.criminnet.app.activity.CrimPagerActivity;
import me.ypqiao.criminnet.app.model.Crim;
import me.ypqiao.criminnet.app.model.CrimLab;

import java.util.ArrayList;

/**
 * Created by ypqiao on 3/31/2015.
 */
public class CrimListFragment extends ListFragment {


    private static final String TAG = "CrimListFragment";

    private CrimLab crimLab;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        crimLab = CrimLab.getCrimLab(getActivity());
        for(int i=0;i<100;i++)
            crimLab.add(new Crim("title-"+i,"detail-"+i,getActivity()));

        setListAdapter( new CrimListAdapter((ArrayList<Crim>)(CrimLab.getCrimLab(getActivity()).getCrimList())));

    }


    @Override
    public void onResume() {
        super.onResume();
        ((CrimListAdapter)getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        Crim crim = (Crim)getListAdapter().getItem(position);

        Intent intent = new Intent(getActivity(), CrimPagerActivity.class);
        intent.putExtra(CrimeFragment.CRIM_ID_KEY,crim.getId());

        startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private class CrimListAdapter extends ArrayAdapter<Crim>{

        public CrimListAdapter(ArrayList<Crim> crimArrayList) {
            super(getActivity(), 0,crimArrayList);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

                     if(convertView == null)
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_itiem_crim,null);

            Crim crim = getItem(position);

            TextView title_crim_textview = (TextView) convertView.findViewById(R.id.title_crim_textview);
            title_crim_textview.setText(crim.getTitle());

            TextView date_crim_textview = (TextView) convertView.findViewById(R.id.date_crim_textview);
            date_crim_textview.setText(crim.getDate().toString());

            CheckBox sloved_crim_checkbox = (CheckBox) convertView.findViewById(R.id.sloved_crim_checkbox);
            sloved_crim_checkbox.setChecked(crim.isSolved());


            return convertView;
        }
    }

}
