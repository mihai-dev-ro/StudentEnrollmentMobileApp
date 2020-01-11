package com.nextlevelstudy.database;


import android.graphics.Movie;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.nextlevelstudy.database.dao.StudentDao;
import com.nextlevelstudy.database.dao.UniversityDao;
import com.nextlevelstudy.models.StudentWithToken;
import com.nextlevelstudy.models.University;

/**
 * Main database description.
 */
@Database(entities = {University.class, StudentWithToken.class}, version = 1, exportSchema = false)
public abstract class StudentEnrollmentDb extends RoomDatabase {

  public abstract UniversityDao universityDao();

  public abstract StudentDao studentDao();
}
