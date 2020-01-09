package com.nextlevelstudy.database;


import android.graphics.Movie;

import androidx.room.Database;

import com.nextlevelstudy.database.dao.UniversityDao;
import com.nextlevelstudy.models.SecurityUser;
import com.nextlevelstudy.models.University;

/**
 * Main database description.
 */
@Database(entities = {University.class}, version = 1)
public abstract class StudentEnrollmentDb {

  abstract public UniversityDao universityDao();
}
