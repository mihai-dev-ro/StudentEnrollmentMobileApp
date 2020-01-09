package com.nextlevelstudy.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.nextlevelstudy.R;
import com.nextlevelstudy.ui.common.NavigationController;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.*;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector {

  @Inject
  DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

  @Inject
  NavigationController navigationController;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    AndroidInjection.inject(this);

    // Add project list fragment if this is first creation
    if (savedInstanceState == null) {
      navigationController.navigateToLogin();
    }

    // Being here means we are in animation mode
    supportPostponeEnterTransition();

  }

  @Override
  public AndroidInjector<Fragment> supportFragmentInjector() {
    return dispatchingAndroidInjector;
  }
}
