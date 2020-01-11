package com.nextlevelstudy.di.module;

import com.nextlevelstudy.ui.login.LoginFragment;
import com.nextlevelstudy.ui.university.UniversityDetailFragment;
import com.nextlevelstudy.ui.university.UniversityListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuildersModule {

  @ContributesAndroidInjector
  abstract UniversityListFragment contributeUniversityListFragment();

  @ContributesAndroidInjector
  abstract UniversityDetailFragment contributeUniversityDetailFragment();

  @ContributesAndroidInjector
  abstract LoginFragment contributeLoginFragment();
}
