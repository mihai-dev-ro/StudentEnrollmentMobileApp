package com.nextlevelstudy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nextlevelstudy.dummy.DummyContent;

import java.util.List;

public class UniversityListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university_list);

        View recyclerView = findViewById(R.id.university_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, DummyContent.ITEMS));
    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final UniversityListActivity mParentActivity;
        private final List<DummyContent.DummyUniversity> mValues;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DummyContent.DummyUniversity item = (DummyContent.DummyUniversity) view.getTag();
                Context context = view.getContext();
                Intent intent = new Intent(context, UniversityDetailActivity.class);
                intent.putExtra(UniversityDetailFragment.ARG_ITEM_ID, item.id);

                context.startActivity(intent);

            }
        };

        SimpleItemRecyclerViewAdapter(UniversityListActivity parent,
                                      List<DummyContent.DummyUniversity> items) {
            mValues = items;
            mParentActivity = parent;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_university_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mUniversityNameView.setText(mValues.get(position).id);
            holder.mUniversityCountryView.setText(mValues.get(position).name);

            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView mUniversityNameView;
            final TextView mUniversityCountryView;

            ViewHolder(View view) {
                super(view);
                mUniversityNameView = (TextView) view.findViewById(R.id.university_name);
                mUniversityCountryView = (TextView) view.findViewById(R.id.university_country);
            }
        }
    }
}
