package com.doordash.consumer;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.HashSet;
import java.util.Map;

import rest.agents.DataAgent;

public class ContextSingleton {
	private static Picasso picasso;
	private static DataAgent dataAgent;
	private static HashSet<Integer> favoriteRestaurants;

	public static final int PAGE_SIZE = 20;

	public static Picasso getPicassoInstance (Context context) {
		if (picasso == null) {
			Picasso.Builder builder = new Picasso.Builder(context);
			builder.downloader(new OkHttp3Downloader(context));
			picasso = builder.build();
		}

		return picasso;
	}


	public static DataAgent getRestaurantAgent () {
		if (dataAgent == null) {
			dataAgent = new DataAgent();
		}
		return dataAgent;
	}

	public static HashSet<Integer> getFavoriteRestaurants (Context context) {
		if (favoriteRestaurants == null) {
			favoriteRestaurants = new HashSet<>();

			SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

			Map<String,?> keys = sharedPreferences.getAll();

			for(Map.Entry<String,?> entry : keys.entrySet()) {
				favoriteRestaurants.add (Integer.parseInt(entry.getKey()));
			}
		}

		return favoriteRestaurants;
	}

	public static void updateFavorites (Context context) {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

		SharedPreferences.Editor editor = sharedPreferences.edit();

		for (Integer id : favoriteRestaurants) {
			if (!sharedPreferences.contains(id.toString())) {
				editor.putBoolean(id.toString(), true);
			}
		}

		editor.commit();

		Map<String,?> keys = sharedPreferences.getAll();

		HashSet<Integer> remove = new HashSet<>();

		for(Map.Entry<String,?> entry : keys.entrySet()) {
			if (!favoriteRestaurants.contains(Integer.parseInt(entry.getKey()))) {
				remove.add(Integer.parseInt(entry.getKey()));
			}
		}

		for (Integer id : remove) {
			editor.remove(id.toString());
		}
		editor.commit();
	}
}
