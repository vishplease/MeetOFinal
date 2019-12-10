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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TripsAdapter extends RecyclerView.Adapter<TripsAdapter.TripViewHolder>{
	public Context context;

	@NonNull
	@Override
	public TripViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

		//inflate layout trip card
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_card, parent, false);

		//when i click on it, where do i go - instead of traveloneway it should go to trip summary. Onclick get the group trip ID
		//send to different intents conditionally based on status of the trip
		v.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), MatchedTripSummary.class);
				v.getContext().startActivity(intent);
			}
		});
		TripViewHolder tvh = new TripViewHolder(v);
		return tvh;
	}

	//rendering of the card, populated with the trip details happens here
	@Override
	public void onBindViewHolder(@NonNull TripViewHolder holder, int position) {
		//format time to show up properly in cardview
		SimpleDateFormat timeformatter = new SimpleDateFormat("hh:mm a");
		Time cardTime = new Time(trips.get(position).requestedTime);
		String cardTimeOutput = timeformatter.format(cardTime);

		//format date to show up properly in cardview

		SimpleDateFormat dateformatter = new SimpleDateFormat("MM/dd/yy");
		Date cardDate = new Date(trips.get(position).requestedTime);
		String cardDateOutput = dateformatter.format(cardDate);

		//rename pending to MATCH IN PROGRESS
		String tripStatus = String.valueOf(trips.get(position).status);

		if (tripStatus.equals("Pending")){
			tripStatus = "MATCH IN PROGRESS";
		}






		//gets position of the trip in the recycler view, finds an item on the card (refer to trip card xml)
		holder.textViewTripTime.setText(cardTimeOutput);
		holder.textViewStatus.setText(tripStatus);
		holder.textViewTripDate.setText(cardDateOutput);
		holder.textViewTripStart.setText(String.valueOf(trips.get(position).startLocation).toUpperCase());
		holder.textViewTripEnd.setText(String.valueOf(trips.get(position).endLocation).toUpperCase());



	}

	@Override
	public int getItemCount() {
		return trips.size();
	}

	public void onAttachedToRecyclerView(RecyclerView recyclerView) {
		super.onAttachedToRecyclerView(recyclerView);
	}

	//
	public static class TripViewHolder extends RecyclerView.ViewHolder {
		CardView cv;
		TextView textViewStatus,
				textViewTripDate,
				textViewTripStart,
				textViewTripEnd,
				textViewTripTime;

		TripViewHolder(View itemView) {
			super(itemView);
			cv = itemView.findViewById(R.id.cv);
			textViewStatus = itemView.findViewById(R.id.textViewStatus);
			textViewTripTime = itemView.findViewById(R.id.textViewTripTime);
			textViewTripDate = itemView.findViewById(R.id.textViewTripDate);
			textViewTripStart = itemView.findViewById(R.id.textViewTripStart);
			textViewTripEnd = itemView.findViewById(R.id.textViewTripEnd);
		}
	}

	List<TripRequest> trips;
//taking the Trip array from UpcomingTrips and passing the data along to render the recycler view
	TripsAdapter(List<TripRequest> trips){
		this.trips = trips;
	}

}
