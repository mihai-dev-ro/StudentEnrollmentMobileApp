package com.nextlevelstudy.ui.university;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nextlevelstudy.R;
import com.nextlevelstudy.di.Injectable;
import com.nextlevelstudy.models.University;
import com.nextlevelstudy.utils.DeviceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UniversityDetailFragment extends Fragment implements Injectable {

    public static final String BUNDLE_UNIVERSITY = "UNIVERSITY_DATA";

    private ArrayAdapter<String> dnsDomainListAdapter;
    private ArrayAdapter<String> websiteListAdapter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.university_details_name)
    TextView universityDetailsName;

    @BindView(R.id.university_details_country)
    TextView universityDetailsCountry;

    @BindView(R.id.university_details_dnsDomains)
    ListView universityDetailsDNSDomains;

    @BindView(R.id.university_details_websites)
    ListView universityDetailsWebsites;

    @BindView(R.id.fab_apply_to_university)
    FloatingActionButton fabApplyToUniversity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Postpone the shared element enter transition.
        postponeEnterTransition();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_university, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    public static UniversityDetailFragment create(University university) {
        UniversityDetailFragment fragment = new UniversityDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(BUNDLE_UNIVERSITY, university);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);

        DeviceUtils.setTranslucentStatusBar(getActivity().getWindow(), android.R.color.transparent);

        toolbar.setNavigationOnClickListener(v -> getActivity().onBackPressed());

        // get the university
        University university = getArguments().getParcelable(BUNDLE_UNIVERSITY);

        // set-up the dns domains
        dnsDomainListAdapter = new ArrayAdapter<String>(this.getActivity(),
          android.R.layout.simple_list_item_1, android.R.id.text1, university.dnsDomains);
        universityDetailsDNSDomains.setAdapter(dnsDomainListAdapter);

        // set-up the websites
        websiteListAdapter = new ArrayAdapter<String>(this.getActivity(),
          android.R.layout.simple_list_item_1, android.R.id.text1, university.websites);
        universityDetailsDNSDomains.setAdapter(websiteListAdapter);
    }

}
