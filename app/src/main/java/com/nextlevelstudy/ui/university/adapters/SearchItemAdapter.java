package com.nextlevelstudy.ui.university.adapters;

import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nextlevelstudy.R;
import com.nextlevelstudy.models.University;
import com.nextlevelstudy.ui.university.callbacks.UniversityCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchItemAdapter
  extends RecyclerView.Adapter<SearchItemAdapter.ViewHolder> {

  private List<University> mUniversityList = new ArrayList<>();
  private UniversityCallback mUniversityClickCallback;


  public SearchItemAdapter(UniversityCallback universityClickCallback) {
    mUniversityClickCallback = universityClickCallback;
  }

  @Override
  public SearchItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext())
      .inflate(R.layout.fragment_item_search_university_layout, parent, false);
    return new ViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(SearchItemAdapter.ViewHolder holder, int position) {
    final University university = mUniversityList.get(position);

    holder.txtSearchField.setText(university.name + " - " + university.countryName);

    holder.itemView.setOnClickListener(view -> mUniversityClickCallback.onClick(university));
  }

  @Override
  public int getItemCount() {
    return mUniversityList.size();
  }

  public void setItems(List<University> universityList) {
    mUniversityList = universityList;
    notifyDataSetChanged();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.university_search_text)
    TextView txtSearchField;

    View itemView;

    public ViewHolder(View view) {
      super(view);
      ButterKnife.bind(this, view);
      this.itemView = view;
    }
  }

}
