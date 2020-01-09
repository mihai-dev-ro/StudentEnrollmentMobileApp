package com.nextlevelstudy.di.module;

import com.nextlevelstudy.ui.university.UniversityDetailFragment;
import com.nextlevelstudy.ui.university.UniversityListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public class FragmentBuildersModule {

  @ContributesAndroidInjector
  abstract UniversityListFragment contributeUniversityListFragment();

  @ContributesAndroidInjector
  abstract UniversityDetailFragment contributeUniversityDetailFragment();
}
