package com.nextlevelstudy.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


@Entity(indices = {@Index("id")},
  primaryKeys = {"id"})
public class University {

  @SerializedName("id")
  @Expose
  @NonNull
  public final int id;

  public String name;
  public String countryCode;
  public String countryName;

  public List<String> dnsDomains;
  public List<String> websites;

  public University(int id, String countryCode, String countryName, List<String> dnsdomains,
                    List<String> websites){

    this.id = id;
    this.countryCode = countryCode;
    this.countryName = countryName;
    this.dnsDomains = dnsdomains;
    this.websites = websites;

  }

}
