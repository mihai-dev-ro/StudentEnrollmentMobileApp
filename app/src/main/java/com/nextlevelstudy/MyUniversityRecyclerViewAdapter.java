package com.nextlevelstudy;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nextlevelstudy.UniversityDetailFragment.OnListFragmentInteractionListener;
import com.nextlevelstudy.dummy.DummyContent.DummyUniversity;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link com.nextlevelstudy.dummy.DummyContent.DummyUniversity} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyUniversityRecyclerViewAdapter extends RecyclerView.Adapter<MyUniversityRecyclerViewAdapter.ViewHolder> {

    private final List<DummyUniversity> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyUniversityRecyclerViewAdapter(List<DummyUniversity> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_university, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mUniversityName.setText(mValues.get(position).name);
        holder.mUniversityCountry.setText(mValues.get(position).country);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mUniversityName;
        public final TextView mUniversityCountry;
        public DummyUniversity mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mUniversityName = (TextView) view.findViewById(R.id.university_name);
            mUniversityCountry = (TextView) view.findViewById(R.id.university_name);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mUniversityCountry.getText() + "'";
        }
    }
}
