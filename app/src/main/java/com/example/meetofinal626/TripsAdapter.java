package com.example.meetofinal626;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Array;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TripsAdapter extends RecyclerView.Adapter<TripsAdapter.TripViewHolder>{
	public static Context context;
	private ArrayList<TripRequest> mTriprequests;
	private OnItemClickListener mListener;

	public TripsAdapter(Context c, ArrayList<TripRequest> t){
		context = c;
		mTriprequests = t;
	}

	/*
	List<TripRequest> trips;
	//taking the Trip array from UpcomingTrips and passing the data along to render the recycler view
	TripsAdapter(List<TripRequest> trips){
		this.trips = trips;
	}

	 */

	public interface OnItemClickListener {

		void onItemClick(int position);

	}

	public void setOnItemClickListener(OnItemClickListener listener){
		mListener = listener;
	}

	public static class TripViewHolder extends RecyclerView.ViewHolder {
		CardView cv;
		TextView textViewStatus,
				textViewTripDate,
				textViewTripStart,
				textViewTripEnd,
				textViewTripTime;

		TripViewHolder(View itemView, final OnItemClickListener listener, final ArrayList<TripRequest> Triprequests ) {
			super(itemView);
			cv = itemView.findViewById(R.id.cv);
			final ArrayList<TripRequest> tripRequests = Triprequests;
			textViewStatus = itemView.findViewById(R.id.textViewStatus);
			textViewTripTime = itemView.findViewById(R.id.textViewTripTime);
			textViewTripDate = itemView.findViewById(R.id.textViewTripDate);
			textViewTripStart = itemView.findViewById(R.id.textViewTripStart);
			textViewTripEnd = itemView.findViewById(R.id.textViewTripEnd);

			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {

					if(listener != null) {
						int position = getAdapterPosition();
						if (position != RecyclerView.NO_POSITION){
							listener.onItemClick(position);

							TripRequest currentPosition;
							currentPosition = tripRequests.get(position);

							String createRider = currentPosition.riderID;
							String createUser = currentPosition.userID;
							String createStartLocation = currentPosition.startLocation;
							String createEndLocation = currentPosition.endLocation;
							Long createRequestedTime = currentPosition.requestedTime;
							Integer createCarryOnCount = currentPosition.handBag;
							Integer createRollaboardCount = currentPosition.carryOn;
							Integer createCheckInCount = currentPosition.checkIn;
							String createStatus = currentPosition.status;


							Bundle tripRequest = new Bundle();

							tripRequest.putString("createRider", createRider);
							tripRequest.putString("createUser", createUser);
							tripRequest.putString("createStartLocation", createStartLocation);
							tripRequest.putString("createEndLocation", createEndLocation);
							tripRequest.putLong("createRequestedTime", createRequestedTime);
							tripRequest.putInt("createCarryOnCount", createCarryOnCount);
							tripRequest.putInt("createRollaboardCount", createRollaboardCount);
							tripRequest.putInt("createCheckInCount", createCheckInCount);
							tripRequest.putString("createStatus", createStatus);




							if (currentPosition.status.equals("Pending")){

								Intent intent = new Intent(context, MatchInProgress.class);
								intent.putExtras(tripRequest);
								context.startActivity(intent);


							} else if (currentPosition.status.equals("No match")) {

								Intent intent = new Intent(context, AlternativeTravel.class);
								intent.putExtras(tripRequest);
								context.startActivity(intent);

							} else if (currentPosition.status.equals("Riders found")) {

								Intent intent = new Intent(context, ConfirmTrip.class);
								intent.putExtras(tripRequest);
								context.startActivity(intent);

							} else if (currentPosition.status.equals("Trip confirmed")) {

								Intent intent = new Intent(context, MatchedTripSummary.class);
								intent.putExtras(tripRequest);
								context.startActivity(intent);

							}

							//Toast.makeText(view.getContext(), tripRequests.get(position).status + " is clicked", Toast.LENGTH_SHORT).show();
						}
					}

				}
			});

		}
/*
		public void onClick(final int position) {
			cv.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {

					Toast.makeText(context, position+ " is clicked", Toast.LENGTH_SHORT).show();

				}
			});
		}

 */
	}

	public TripsAdapter(ArrayList<TripRequest> triprequests) {
		mTriprequests = triprequests;
	}

	@NonNull
	@Override
	public TripViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


		//inflate layout trip card

		View v = LayoutInflater.from(context).inflate(R.layout.trip_card, parent, false);

		TripViewHolder tvh = new TripViewHolder(v, mListener, mTriprequests);
		return tvh;
	}

	//rendering of the card, populated with the trip details happens here
	@Override
	public void onBindViewHolder(@NonNull TripViewHolder holder, int position) {

		TripRequest currentItem = mTriprequests.get(position);

		//format time to show up properly in cardview
		SimpleDateFormat timeformatter = new SimpleDateFormat("hh:mm a");
		Time cardTime = new Time(currentItem.requestedTime);
		String cardTimeOutput = timeformatter.format(cardTime);

		//format date to show up properly in cardview

		SimpleDateFormat dateformatter = new SimpleDateFormat("MM/dd/yy");
		Date cardDate = new Date(currentItem.requestedTime);
		String cardDateOutput = dateformatter.format(cardDate);

		//rename pending to MATCH IN PROGRESS
		String tripStatus = String.valueOf(currentItem.status);

		if (tripStatus.equals("Pending")){
			tripStatus = "MATCH IN PROGRESS";
		} else if (tripStatus.equals("No match")){
			tripStatus = "NO MATCH";
		} else if (tripStatus.equals("Riders found")){
			tripStatus = "MATCHED - PLEASE CONFIRM";
		} else if(tripStatus.equals("Trip confirmed")){
			tripStatus = "TRIP CONFIRMED";
		}

		//get user email for testing purposes
		//String userEmail = String.valueOf(triprequests.get(position).riderID);


		//gets position of the trip in the recycler view, finds an item on the card (refer to trip card xml)
		holder.textViewTripTime.setText(cardTimeOutput);
		holder.textViewStatus.setText(tripStatus);
		holder.textViewTripDate.setText(cardDateOutput);
		holder.textViewTripStart.setText(String.valueOf(currentItem.startLocation).toUpperCase());
		holder.textViewTripEnd.setText(String.valueOf(currentItem.endLocation).toUpperCase());
		//holder.onClick(position);

	}

	@Override
	public int getItemCount() {
		return mTriprequests.size();
	}

	public void onAttachedToRecyclerView(RecyclerView recyclerView) {
		super.onAttachedToRecyclerView(recyclerView);
	}

	//




}
