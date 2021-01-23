package com.codepath.flicks.model;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.flixster.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;


@Parcel // annotation indicates class is Parcelable
public class MovieDetailsActivity {

    // fields must be public for parceler
    String title;
    String overview;
    String posterPath;
    String backdropPath;

    // no-arg, empty constructor required for Parceler
    public MovieDetailsActivity() {}

    public MovieDetailsActivity(JSONObject movie) throws JSONException {
        title = movie.getString("title");
        overview = movie.getString("overview");
        posterPath = movie.getString("poster_path");
        backdropPath = movie.getString("backdrop_path");
    }

    // ... additional code

}

// class *cannot* be static
// implements View.OnClickListener
public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView ivPrimaryImage;
    TextView tvTitle;
    TextView tvOverview;

    public ViewHolder(View itemView) {
        super(itemView);
        ivPrimaryImage = (ImageView) itemView.findViewById(R.id.ivPrimaryImage);
        tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        tvOverview = (TextView) itemView.findViewById(R.id.tvOverview);
        // add this as the itemView's OnClickListener
        itemView.setOnClickListener(this);
    }

    // when the user clicks on a row, show MovieDetailsActivity for the selected movie
    @Override
    public void onClick(View v) {
        // gets item position
        int position = getAdapterPosition();
        // make sure the position is valid, i.e. actually exists in the view
        if (position != RecyclerView.NO_POSITION) {
            // get the movie at the position, this won't work if the class is static
            Movie movie = movies.get(position);
            // create intent for the new activity
            Intent intent = new Intent(context, MovieDetailsActivity.class);
            // serialize the movie using parceler, use its short name as a key
            intent.putExtra(Movie.class.getSimpleName(), Parcels.wrap(movie));
            // show the activity
            context.startActivity(intent);
        }
    }
}