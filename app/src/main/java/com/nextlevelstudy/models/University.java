package com.nextlevelstudy.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.nextlevelstudy.database.dao.helpers.DbTypeConverters;

import java.util.List;


@Entity(indices = {@Index("id")},
  primaryKeys = {"id"})
@TypeConverters(DbTypeConverters.class)
public class University implements Parcelable {

  @SerializedName("id")
  @Expose
  @NonNull
  public int id;

  public String name;
  @SerializedName(value = "countyCode")
  public String countryCode;
  public String countryName;

  public List<String> dnsDomains;
  public List<String> websites;

  public University(int id, String name, String countryCode, String countryName,
                    List<String> dnsDomains, List<String> websites){

    this.id = id;
    this.name = name;
    this.countryCode = countryCode;
    this.countryName = countryName;
    this.dnsDomains = dnsDomains;
    this.websites = websites;

  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(id);
    dest.writeString(name);
    dest.writeString(countryCode);
    dest.writeString(countryName);
    dest.writeStringList(dnsDomains);
    dest.writeStringList(websites);
  }
}
