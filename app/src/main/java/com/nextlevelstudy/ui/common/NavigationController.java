package com.nextlevelstudy.ui.common;

import android.os.Build;
import android.transition.Fade;
import android.util.Log;
import android.view.View;

import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentManager;

import com.nextlevelstudy.R;

import com.nextlevelstudy.models.University;
import com.nextlevelstudy.ui.MainActivity;
import com.nextlevelstudy.ui.login.LoginFragment;
import com.nextlevelstudy.ui.university.UniversityDetailFragment;
import com.nextlevelstudy.ui.university.UniversityListFragment;

import javax.inject.Inject;

/**
 * A utility class that handles navigation in {@link MainActivity}.
 */

public class NavigationController {
  private final int containerId;
  private final FragmentManager fragmentManager;

  @Inject
  public NavigationController(MainActivity mainActivity) {
    containerId = R.id.container;
    fragmentManager = mainActivity.getSupportFragmentManager();
  }

  public void navigateToLogin() {
    LoginFragment fragment = new LoginFragment();
    fragmentManager.beginTransaction()
      .replace(containerId, fragment)
      .commitAllowingStateLoss();
  }

  public void navigateToUniversityListFragment() {
    UniversityListFragment fragment = new UniversityListFragment();
    fragmentManager.beginTransaction()
      .replace(containerId, fragment)
      .commitAllowingStateLoss();
  }

  public void navigateToUniversityDetailFragment(University university) {
    UniversityDetailFragment fragment = UniversityDetailFragment.create(university);

    fragmentManager.beginTransaction()
      .setReorderingAllowed(true)
      .replace(containerId, fragment)
      .addToBackStack(null)
      .commitAllowingStateLoss();
  }

  public void applyToUniversity(University university) {
    // todo: require implementation
  }

  public void navigateToStudentRegistration() {
    // todo: implement implementation
  }

}

