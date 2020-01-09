package com.nextlevelstudy.ui.university.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nextlevelstudy.R;
import com.nextlevelstudy.models.University;
import com.nextlevelstudy.ui.university.callbacks.UniversityCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UniversityListAdapter
  extends RecyclerView.Adapter<UniversityListAdapter.UniversityViewHolder> {

  private List<University> mUniversityList = new ArrayList<>();
  UniversityCallback mUniversityCallback;

  public UniversityListAdapter(UniversityCallback universityCallback) {
    mUniversityCallback = universityCallback;
  }

  @Override
  public UniversityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
      .inflate(R.layout.fragment_university_list_item, parent, false);
    return new UniversityViewHolder(view);
  }

  @Override
  public void onBindViewHolder(UniversityViewHolder holder, int position) {

    University university = mUniversityList.get(position);

    holder.universityName.setText(university.countryName);
    holder.univetsityCountryName.setText(university.countryName);

    holder.itemView.setOnClickListener(view -> mUniversityCallback.onClick(university));

  }

  @Override
  public int getItemCount() {
    return mUniversityList.size();
  }

  public void setData(List<University> universityList) {
    mUniversityList= universityList;
    notifyDataSetChanged();
  }

  public void clearAdapter() {
    mUniversityList.clear();
    notifyDataSetChanged();
  }

  /**
   * ViewHolder class
   */
  public class UniversityViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.university_list_item_name)
    public TextView universityName;

    @BindView(R.id.university_list_item_country)
    public TextView univetsityCountryName;

    public View itemView;

    public UniversityViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);

      this.itemView = itemView;
    }
  }
}

