package com.example.lenovo.mytestapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CountryAdapter extends  RecyclerView.Adapter<CountryAdapter.CountryViewHolder>  {
private Context mCtx;
private List<Country> countriesList;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;



    public CountryAdapter(Context mCtx, List<Country> countriesList) {
        this.mInflater = LayoutInflater.from(mCtx);
        this.mCtx = mCtx;
        this.countriesList = countriesList;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater= LayoutInflater.from(mCtx);
        View view= layoutInflater.inflate(R.layout.recyclerview_row,null);
        CountryViewHolder holder= new CountryViewHolder(view);
        return holder;
        
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder countryViewHolder, int position) {
        Country country=countriesList.get(position);
        countryViewHolder.tvCountryName.setText(country.getName());
        countryViewHolder.tvCountryCode1.setText(country.getAlpha2Code());
        countryViewHolder.tvCountryCode2.setText(country.getAlpha3Code());

    }

    @Override
    public int getItemCount() {
        return countriesList.size();
    }

    public class CountryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvCountryName,tvCountryCode1,tvCountryCode2;

        public CountryViewHolder(View view) {
            super(view);
            tvCountryName=view.findViewById(R.id.textViewCountryName);
            tvCountryCode1=view.findViewById(R.id.countryCode1);
            tvCountryCode2=view.findViewById(R.id.countryCode2);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return countriesList.get(id).getName();
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
