package me.ypqiao.criminnet.app.model;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by ypqiao on 3/31/2015.
 */
public class CrimLab {


    private static final String TAG = "CrimLab";

    private static CrimLab CRIMLAB;

    private List<Crim> crimList = new ArrayList<Crim>();

    private Context context;

    private CrimLab(Context context){ this.context = context;};

    public static CrimLab getCrimLab(Context context){

        if(CRIMLAB == null)
            CRIMLAB = new CrimLab(context);

        return CRIMLAB;

    }

    public void add(Crim crim){

        crimList.add(crim);
    }

    public Crim get(UUID id){

        for(Crim c : crimList)

            if(c.getId().equals(id))
                return c;

        return null;

    }


    public List<Crim> getCrimList(){
        return crimList;
    }

}
