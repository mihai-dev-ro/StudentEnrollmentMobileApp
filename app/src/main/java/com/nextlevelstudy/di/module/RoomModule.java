package com.nextlevelstudy.di.module;

import android.app.Application;

import androidx.room.Room;

import com.nextlevelstudy.database.StudentEnrollmentDb;
import com.nextlevelstudy.database.dao.UniversityDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

  @Singleton
  @Provides
  StudentEnrollmentDb providesRoomDatabase(Application app) {
    return Room.databaseBuilder(app, StudentEnrollmentDb.class, "studentEnrollment_db").build();
  }

  @Singleton
  @Provides
  UniversityDao provideUniversityDao(StudentEnrollmentDb db){
    return db.universityDao();
  }

}

