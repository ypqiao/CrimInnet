package me.ypqiao.criminnet.app.fragment;

import android.app.AlertDialog;
import android.app.Dialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;
import me.ypqiao.criminnet.app.R;
import me.ypqiao.criminnet.app.code.ResponseCode;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by ypqiao on 4/10/2015.
 */
public class DatePickerFragment extends DialogFragment {

    public static final String TAG = "DatePickerFragment";

    public static final String DATE_KEY = "DatePickerFragment.date.key";

    public static int REQUEST_CODE = 0;


    private DatePicker datePicker;
    private Date date;


    public static DatePickerFragment newInstance(Date date){

        Bundle bundle = new Bundle();
        bundle.putSerializable(DATE_KEY,date);

        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.setArguments(bundle);

        return datePickerFragment;

    }



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        date = (Date)getArguments().getSerializable(DATE_KEY);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);


        View v = getActivity().getLayoutInflater()
                .inflate(R.layout.dialog_date,null);

        datePicker = (DatePicker)v.findViewById(R.id.datepicker_dialog_date);
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                date = new GregorianCalendar(year,monthOfYear,dayOfMonth).getTime();
                getArguments().putSerializable(DATE_KEY,date);
            }
        });



        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.title_alertdialog)
                .setPositiveButton(R.string.ok_alertdialog, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendResult(ResponseCode.OK);
                    }
                })
                .setView(v)
                .create();
    }

    private void sendResult(int responseCode) {

        if(getTargetFragment() == null)
            return;

        Intent intent = new Intent();
        intent.putExtra(DATE_KEY,date);

        getTargetFragment().onActivityResult(REQUEST_CODE,responseCode,intent);

    }
}
