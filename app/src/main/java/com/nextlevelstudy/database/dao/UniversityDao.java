package com.nextlevelstudy.database.dao;

import android.graphics.Movie;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.nextlevelstudy.models.University;

import java.util.List;

@Dao
public abstract class UniversityDao {

  @Query("SELECT * FROM University")
  public abstract LiveData<List<University>> findAll();

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  public abstract void insertUniversities(List<University> universityList);

  @Query("DELETE FROM University")
  public abstract void deleteAll();

  @Query("SELECT * FROM University where name = :name or countryName = :name")
  public abstract LiveData<List<University>> searchUniversitiesByNameOrCountryName(String name);

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  public abstract long createUniversityIfNotExists(University university);
}
