package me.ypqiao.criminnet.app.model;

import android.content.Context;

import java.util.Date;
import java.util.UUID;

/**
 * Created by ypqiao on 3/31/2015.
 */
public class Crim {

    private UUID id;
    private String title;
    private String detail;
    private Date date;
    private boolean solved;


    public Crim(Context context){
        this(UUID.randomUUID(),null,null,new Date());
    }

    public Crim(String title,String detail,Context context){
        this(UUID.randomUUID(),title,detail,new Date());
    }


    public Crim(UUID id, String title, String detail, Date date) {
        this.id = id;
        this.title = title;
        this.detail = detail;
        this.date = date;
        this.solved = false;
    }

    @Override
    public String toString() {
        return title;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

}
