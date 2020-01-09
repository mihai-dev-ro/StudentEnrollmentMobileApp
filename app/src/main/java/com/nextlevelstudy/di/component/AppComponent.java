package com.nextlevelstudy.di.component;

import android.app.Application;

import com.nextlevelstudy.StudentEnrollmentApp;
import com.nextlevelstudy.di.module.AppModule;
import com.nextlevelstudy.di.module.MainActivityModule;
import com.nextlevelstudy.di.module.RoomModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {
  AndroidInjectionModule.class,
  AppModule.class,
  MainActivityModule.class,
  RoomModule.class,
})
public interface AppComponent {
  @Component.Builder
  interface Builder {
    @BindsInstance
    Builder application(Application application);

    AppComponent build();
  }

  void inject(StudentEnrollmentApp studentEnrollmentApp);
}
