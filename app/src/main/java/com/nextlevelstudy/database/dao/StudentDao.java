package com.nextlevelstudy.database.dao;

import android.graphics.Movie;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.nextlevelstudy.models.StudentWithToken;

@Dao
public abstract class StudentDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  public abstract void insertOrUpdateStudent(StudentWithToken student);

  @Query("SELECT * FROM StudentWithToken where email = :email LIMIT 1")
  public abstract LiveData<StudentWithToken> findMyEmail(String email);
}
