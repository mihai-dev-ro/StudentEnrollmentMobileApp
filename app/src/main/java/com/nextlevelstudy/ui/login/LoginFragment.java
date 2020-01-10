package com.nextlevelstudy.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.nextlevelstudy.R;
import com.nextlevelstudy.di.Injectable;
import com.nextlevelstudy.models.StudentWithToken;
import com.nextlevelstudy.services.base.AuthInterceptor;
import com.nextlevelstudy.services.utils.Resource;
import com.nextlevelstudy.ui.common.NavigationController;
import com.nextlevelstudy.ui.login.adapters.LoginAdapter;
import com.nextlevelstudy.utils.DeviceUtils;
import com.nextlevelstudy.view_models.login.LoginViewModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.nextlevelstudy.services.utils.Status.ERROR;
import static com.nextlevelstudy.services.utils.Status.LOADING;
import static com.nextlevelstudy.services.utils.Status.SUCCESS;


public class LoginFragment extends Fragment implements Injectable {

  @Inject
  public ViewModelProvider.Factory viewModelFactory;
  @Inject
  public NavigationController navigationController;

  @Inject
  public AuthInterceptor tokenAuthInterceptor;

  @BindView(R.id.login_email)
  EditText loginEmail;
  @BindView(R.id.login_password)
  EditText loginPassword;
  @BindView(R.id.login_authenticate)
  Button authenticateButton;
  @BindView(R.id.login_progressbar)
  ProgressBar progressBar;
  @BindView(R.id.login_signUp_text)
  TextView signUp;
  @BindView(R.id.error_msg)
  TextView errorTextView;
  @BindView(R.id.layout_display_info)
  RelativeLayout layoutDisplayInfo;

  // viewmodel
  LoginViewModel loginViewModel;
  // adapter
  LoginAdapter loginAdapter;


  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_login, container, false);
    ButterKnife.bind(this, view);

    authenticateButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        loginViewModel.authenticate(
          loginEmail.getText().toString(),
          loginPassword.getText().toString());
      }
    });

    return view;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    DeviceUtils.setTranslucentStatusBar(getActivity().getWindow(), R.color.colorPrimaryDark);

    // get the view model
    loginViewModel = ViewModelProviders.of(this, viewModelFactory)
      .get(LoginViewModel.class);

    loginViewModel.getStudent().observe(this, this::handleLoginResponse);

    signUp.setOnClickListener(v -> {
      navigationController.navigateToStudentRegistration();
    });
  }

  private void handleLoginResponse(Resource<StudentWithToken> loginResultResource) {
    if (loginResultResource != null) {
      switch (loginResultResource.status) {
        case LOADING:
          layoutDisplayInfo.setVisibility(View.VISIBLE);
          progressBar.setVisibility(View.VISIBLE);
          errorTextView.setVisibility(View.GONE);
          break;
        case ERROR:
          progressBar.setVisibility(View.GONE);
          errorTextView.setVisibility(View.VISIBLE);
          errorTextView.setText(getResources().getString(R.string.error_failed_login));
          Toast.makeText(this.getActivity(), loginResultResource.message, Toast.LENGTH_SHORT).show();
          break;
        case SUCCESS:
          progressBar.setVisibility(View.GONE);
          errorTextView.setVisibility(View.GONE);
          // save the token and set the authInterceptor
          tokenAuthInterceptor.setJwtToken(loginResultResource.data.token);
          // save the user in the shared preferences
          // todo:
          navigationController.navigateToUniversityListFragment();
          break;
        default:
          progressBar.setVisibility(View.GONE);
          errorTextView.setText(getResources().getString(R.string.university_list_error_loading));
          break;
      }
    }
  }

}
