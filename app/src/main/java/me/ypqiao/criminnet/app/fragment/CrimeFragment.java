package me.ypqiao.criminnet.app.fragment;

import android.content.Intent;
import android.os.Bundle;


import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import me.ypqiao.criminnet.app.R;
import me.ypqiao.criminnet.app.code.ResponseCode;
import me.ypqiao.criminnet.app.model.Crim;
import me.ypqiao.criminnet.app.model.CrimLab;
import me.ypqiao.criminnet.app.util.DateUtil;

import java.util.Date;
import java.util.UUID;

/**
 * Created by ypqiao on 3/31/2015.
 */
public class CrimeFragment extends Fragment {


    public static final String CRIM_ID_KEY = " me.ypqiao.criminnet.app.fragment.CrimeFragment.CRIM_ID_KEY";

    private Crim crim;

    private EditText title_crim_edittext;
    private Button date_crim_button;
    private CheckBox checkBox;

    public static CrimeFragment newInstance(UUID uuid){

        Bundle arguments = new Bundle();
        arguments.putSerializable(CrimeFragment.CRIM_ID_KEY,uuid);

        CrimeFragment fragment = new CrimeFragment();

        fragment.setArguments(arguments);

        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID crim_id = (UUID)getArguments().getSerializable(CrimeFragment.CRIM_ID_KEY);
        crim = CrimLab.getCrimLab(getActivity()).get(crim_id);
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_crim,container,false);

        title_crim_edittext = (EditText)v.findViewById(R.id.crim_title);
        title_crim_edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                crim.setTitle(title_crim_edittext.getText().toString());
            }
        });


        date_crim_button = (Button)v.findViewById(R.id.crim_date);
        date_crim_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerFragment datePickerFragment = DatePickerFragment.newInstance(crim.getDate());
                datePickerFragment.setTargetFragment(CrimeFragment.this, DatePickerFragment.REQUEST_CODE);

                datePickerFragment.show(getActivity().getSupportFragmentManager(),"");


            }
        });

        checkBox = (CheckBox)v.findViewById(R.id.crim_solved);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                crim.setSolved(isChecked);
            }
        });

        if(crim != null) {
            title_crim_edittext.setText(crim.getTitle());
            date_crim_button.setText(DateUtil.formatDate(crim.getDate(),DateUtil.F1));
            checkBox.setChecked(crim.isSolved());
        }

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if(resultCode != ResponseCode.OK)
            return;

        if(requestCode == DatePickerFragment.REQUEST_CODE){
            Date date = (Date)intent.getSerializableExtra(DatePickerFragment.DATE_KEY);
            crim.setDate(date);
            date_crim_button.setText(DateUtil.formatDate(crim.getDate(),DateUtil.F1));
        }


    }
}
