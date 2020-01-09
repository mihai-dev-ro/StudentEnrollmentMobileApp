package com.nextlevelstudy.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.nextlevelstudy.di.qualifiers.ViewModelKey;
import com.nextlevelstudy.view_models.ProjectViewModelFactory;
import com.nextlevelstudy.view_models.university.SearchViewModel;
import com.nextlevelstudy.view_models.university.UniversityDetailViewModel;
import com.nextlevelstudy.view_models.university.UniversityListViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

  @Binds
  @IntoMap
  @ViewModelKey(SearchViewModel.class)
  abstract ViewModel bindSearchViewModel(SearchViewModel searchViewModel);

  @Binds
  @IntoMap
  @ViewModelKey(UniversityListViewModel.class)
  abstract ViewModel bindMovieListViewModel(UniversityListViewModel universityListViewModel);

  @Binds
  @IntoMap
  @ViewModelKey(UniversityDetailViewModel.class)
  abstract ViewModel bindMovieDetailViewModel(UniversityDetailViewModel movieDetailViewModel);

  @Binds
  abstract ViewModelProvider.Factory bindViewModelFactory(ProjectViewModelFactory projectViewModelFactory);
}
