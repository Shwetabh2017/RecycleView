package com.shwetabh.ghost.recycleview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import org.json.JSONArray;
import java.util.ArrayList;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import java.util.List;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.shwetabh.ghost.recycleview.Adapter.DataAdapter;
import com.shwetabh.ghost.recycleview.Adapter.RecyclerViewAdapter;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

	List<DataAdapter> ListOfdataAdapter;

	RecyclerView recyclerView;

	String HTTP_JSON_URL = "https://api.androidhive.info/json/movies.json";

	String Image_Name_JSON = "title";

	String Image_URL_JSON = "image";

	String Rating = "rating";
	String ReleasedYear = "releaseYear";

	JsonArrayRequest RequestOfJSonArray ;

	RequestQueue requestQueue ;

	View view ;

	int RecyclerViewItemPosition ;

	RecyclerView.LayoutManager layoutManagerOfrecyclerView;

	RecyclerView.Adapter recyclerViewadapter;

	ArrayList<String> ImageTitleNameArrayListForClick;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		ImageTitleNameArrayListForClick = new ArrayList<>();

		ListOfdataAdapter = new ArrayList<>();

		recyclerView = (RecyclerView) findViewById(R.id.recyclerview1);

		recyclerView.setHasFixedSize(true);

		layoutManagerOfrecyclerView = new LinearLayoutManager(this);

		recyclerView.setLayoutManager(layoutManagerOfrecyclerView);

		JSON_HTTP_CALL();

		// Implementing Click Listener on RecyclerView.
		recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

			GestureDetector gestureDetector = new GestureDetector(MainActivity.this, new GestureDetector.SimpleOnGestureListener() {

				@Override public boolean onSingleTapUp(MotionEvent motionEvent) {

					return true;
				}

			});
			@Override
			public boolean onInterceptTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {

				view = Recyclerview.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

				if(view != null && gestureDetector.onTouchEvent(motionEvent)) {

					//Getting RecyclerView Clicked Item value.
					RecyclerViewItemPosition = Recyclerview.getChildAdapterPosition(view);

					// Showing RecyclerView Clicked Item value using Toast.
					Toast.makeText(MainActivity.this, ImageTitleNameArrayListForClick.get(RecyclerViewItemPosition), Toast.LENGTH_LONG).show();
				}

				return false;
			}

			@Override
			public void onTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {

			}

			@Override
			public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

			}
		});


	}

	public void JSON_HTTP_CALL(){

		RequestOfJSonArray = new JsonArrayRequest(HTTP_JSON_URL,

				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {

						ParseJSonResponse(response);
					}
				},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {

					}
				});

		requestQueue = Volley.newRequestQueue(MainActivity.this);

		requestQueue.add(RequestOfJSonArray);
	}

	public void ParseJSonResponse(JSONArray array){

		for(int i = 0; i<array.length(); i++) {

			DataAdapter GetDataAdapter2 = new DataAdapter();

			JSONObject json = null;
			try {

				json = array.getJSONObject(i);

				GetDataAdapter2.setImageTitle(json.getString(Image_Name_JSON));
				GetDataAdapter2.setRating(json.getString(Rating));
				GetDataAdapter2.setReleaseyear(json.getString(ReleasedYear));

				// Adding image title name in array to display on RecyclerView click event.
				ImageTitleNameArrayListForClick.add(json.getString(Image_Name_JSON));

				GetDataAdapter2.setImageUrl(json.getString(Image_URL_JSON));


				// Genre is json array
				JSONArray genreArry = json.getJSONArray("genre");
				ArrayList<String> genre = new ArrayList<String>();
				for (int j = 0; j < genreArry.length(); j++) {
					genre.add((String) genreArry.get(j));
				}
				GetDataAdapter2.setGenre(String.valueOf(genre));

			} catch (JSONException e) {

				e.printStackTrace();
			}
			ListOfdataAdapter.add(GetDataAdapter2);
		}

		recyclerViewadapter = new RecyclerViewAdapter(ListOfdataAdapter, this);

		recyclerView.setAdapter(recyclerViewadapter);
	}
}