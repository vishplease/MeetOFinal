package com.example.meetofinal626;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Time;
import java.util.List;

public class TripsAdapter extends RecyclerView.Adapter<TripsAdapter.TripViewHolder>{
	public Context context;

	@NonNull
	@Override
	public TripViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_card, parent, false);
		v.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), TravelOneWay.class);
				v.getContext().startActivity(intent);
			}
		});
		TripViewHolder tvh = new TripViewHolder(v);
		return tvh;
	}

	@Override
	public void onBindViewHolder(@NonNull TripViewHolder holder, int position) {
		holder.tripStatus.setText(String.valueOf(new Time(trips.get(position).requestedTime)));
	}

	@Override
	public int getItemCount() {
		return trips.size();
	}

	public void onAttachedToRecyclerView(RecyclerView recyclerView) {
		super.onAttachedToRecyclerView(recyclerView);
	}

	public static class TripViewHolder extends RecyclerView.ViewHolder {
		CardView cv;
		TextView tripStatus;

		TripViewHolder(View itemView) {
			super(itemView);
			cv = itemView.findViewById(R.id.cv);
			tripStatus = (TextView)itemView.findViewById(R.id.textViewStatus);
		}
	}

	List<TripRequest> trips;

	TripsAdapter(List<TripRequest> trips){
		this.trips = trips;
	}

}
