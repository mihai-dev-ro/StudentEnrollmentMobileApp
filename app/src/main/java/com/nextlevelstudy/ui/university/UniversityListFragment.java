package com.nextlevelstudy.ui.university;

import android.content.Context;
import android.graphics.Movie;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.nextlevelstudy.R;
import com.nextlevelstudy.di.Injectable;
import com.nextlevelstudy.models.University;
import com.nextlevelstudy.services.utils.Resource;
import com.nextlevelstudy.ui.common.NavigationController;
import com.nextlevelstudy.ui.university.adapters.SearchItemAdapter;
import com.nextlevelstudy.ui.university.adapters.UniversityListAdapter;
import com.nextlevelstudy.utils.DeviceUtils;
import com.nextlevelstudy.view_models.university.UniversityListViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UniversityListFragment extends Fragment implements Injectable {

  @Inject
  public ViewModelProvider.Factory viewModelFactory;
  @Inject
  public NavigationController navigationController;

  @BindView(R.id.recyclerView)
  RecyclerView mRecyclerView;
  @BindView(R.id.search_results)
  RecyclerView searchResultsRecyclerView;
  @BindView(R.id.progressbar)
  ProgressBar progressBar;
  @BindView(R.id.tvError)
  TextView errorTextView;
  @BindView(R.id.toolbar)
  Toolbar mToolbar;
  @BindView(R.id.search_view)
  MaterialSearchView materialSearchView;

  private UniversityListViewModel mUniversityListViewModel;
  private UniversityListAdapter mUniversityListAdapter;
  private SearchItemAdapter searchAdapter;
  private List<University> mUniversityList = new ArrayList<>();

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_university_list, container, false);
    ButterKnife.bind(this, view);

    return view;
  }


  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
    setHasOptionsMenu(true);

    DeviceUtils.setTranslucentStatusBar(getActivity().getWindow(), R.color.colorPrimaryDark);

    GridLayoutManager gridLayoutManager = new GridLayoutManager(mRecyclerView.getContext(), 3);
    mRecyclerView.setLayoutManager(gridLayoutManager);

    mUniversityListAdapter = new UniversityListAdapter(
      (university) -> navigationController.navigateToUniversityDetailFragment(university)
    );
    mRecyclerView.setAdapter(mUniversityListAdapter);


    mUniversityListViewModel = ViewModelProviders.of(this, viewModelFactory)
      .get(UniversityListViewModel.class);

    mUniversityListViewModel.getTopUniversities()
      .observe(this, this::handleResponse);

    mUniversityListViewModel.getSearchResults()
      .observe(this, this::handleSearchResponse);


    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
      getActivity(), LinearLayoutManager.VERTICAL, false
    );
    searchResultsRecyclerView.setLayoutManager(linearLayoutManager);

    searchAdapter = new SearchItemAdapter(
      (university) -> navigationController.navigateToUniversityDetailFragment(university)
    );
    searchResultsRecyclerView.setAdapter(searchAdapter);
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.menu_main, menu);
    super.onCreateOptionsMenu(menu, inflater);

    // Retrieve the SearchView and plug it into SearchManager
    MenuItem item = menu.findItem(R.id.action_search);
    materialSearchView.setMenuItem(item);
    materialSearchView.setCursorDrawable(R.drawable.color_cursor_white);

    materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
      @Override
      public boolean onQueryTextSubmit(String query) {
        findUniversity(query);
        dismissKeyboard(materialSearchView.getWindowToken());
        return true;
      }

      @Override
      public boolean onQueryTextChange(String newText) {
        findUniversity(newText);
        return false;
      }
    });

    materialSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
      @Override
      public void onSearchViewShown() {
        searchResultsRecyclerView.setVisibility(View.VISIBLE);
      }

      @Override
      public void onSearchViewClosed() {
        searchResultsRecyclerView.setVisibility(View.GONE);
      }
    });

  }

  private void findUniversity(String query) {
    if (!query.isEmpty() || !query.equals("")) {
      final List<University> filteredModelList = filter(mUniversityList, query);
      if (filteredModelList.size() <= 0) {
        mUniversityListViewModel.setSearchQuery(query);
      } else {
        searchAdapter.setItems(filteredModelList);
        searchAdapter.notifyDataSetChanged();
      }
    }

  }

  private List<University> filter(List<University> models, String query) {
    query = query.toLowerCase();
    final List<University> filteredModelList = new ArrayList<>();
    for (University model : models) {
      final String name = model.name.toLowerCase();
      final String countryName = model.countryName.toLowerCase();
      if (name.contains(query) || countryName.contains(query)) {
        filteredModelList.add(model);
      }
    }
    return filteredModelList;
  }

  private void handleResponse(Resource<List<University>> listResource) {
    if (listResource != null) {
      switch (listResource.status) {
        case ERROR:
          progressBar.setVisibility(View.GONE);
          errorTextView.setVisibility(View.VISIBLE);
          errorTextView.setText(listResource.message);
          break;
        case LOADING:
          progressBar.setVisibility(View.VISIBLE);
          errorTextView.setVisibility(View.GONE);
          break;
        case SUCCESS:
          progressBar.setVisibility(View.GONE);
          errorTextView.setVisibility(View.GONE);
          if (listResource.data != null && listResource.data.size() > 0) {
            mUniversityList = listResource.data;
            mUniversityListAdapter.setData(listResource.data);
            mUniversityListAdapter.notifyDataSetChanged();
          } else {
            errorTextView.setText(getResources().getString(R.string.university_list_error_loading));
            errorTextView.setVisibility(View.VISIBLE);
          }
          break;
        default:
          progressBar.setVisibility(View.GONE);
          errorTextView.setText(getResources().getString(R.string.university_list_error_loading));
          break;
      }
    }
  }

  private void handleSearchResponse(Resource<List<University>> listResource) {
    if (listResource != null) {
      switch (listResource.status) {
        case ERROR:
          progressBar.setVisibility(View.GONE);
          errorTextView.setText(listResource.message);
          break;
        case LOADING:
          progressBar.setVisibility(View.VISIBLE);
          errorTextView.setVisibility(View.GONE);
          break;
        case SUCCESS:
          progressBar.setVisibility(View.GONE);
          errorTextView.setVisibility(View.GONE);
          if (listResource.data != null && listResource.data.size() > 0) {
            searchAdapter.setItems(listResource.data);
            searchAdapter.notifyDataSetChanged();
          } else {
            errorTextView.setText(getResources().getString(R.string.university_list_error_loading));
            errorTextView.setVisibility(View.VISIBLE);
          }
          break;
        default:
          progressBar.setVisibility(View.GONE);
          errorTextView.setText(getResources().getString(R.string.university_list_error_loading));
          break;
      }
    }
  }

  private void dismissKeyboard(IBinder windowToken) {
    FragmentActivity activity = getActivity();
    if (activity != null) {
      InputMethodManager imm = (InputMethodManager) activity.getSystemService(
        Context.INPUT_METHOD_SERVICE);
      if (imm != null) {
        imm.hideSoftInputFromWindow(windowToken, 0);
      }
    }
  }
}
