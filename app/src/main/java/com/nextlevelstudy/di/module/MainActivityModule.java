package com.nextlevelstudy.di.module;

import com.nextlevelstudy.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityModule {
  @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
  abstract MainActivity contributeMainActivity();

}
