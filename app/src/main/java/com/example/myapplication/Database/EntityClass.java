package com.example.myapplication.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "MyTable")
public class EntityClass {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "eventname")
    String eventname;
    @ColumnInfo(name = "eventdeta")
    String eventdeta;
    @ColumnInfo(name = "eventtime")
    String eventtime;
public String getEventname(){
    return eventname;

}
public void setEventname(String eventname){
    this.eventname = eventname;
}
    public String getEventtime(){
        return eventtime;

    }
    public void setEventtime(String eventtime){
        this.eventtime = eventtime;
    }
    public String getEventdeta(){
    return eventdeta;
    }
    public void setEventdeta(String eventdeta){
    this.eventdeta = eventdeta;
    }


}
