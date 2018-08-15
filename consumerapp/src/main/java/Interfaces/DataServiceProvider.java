package Interfaces;


import java.util.List;
import rest.models.Restaurant;
import retrofit2.Callback;

public interface DataServiceProvider {
	void getRestaurants(float lat, float lng, int offset, int limit, Callback<List<Restaurant>> callback);
	void getRestaurantsNearHQ(int offset, int limit, Callback<List<Restaurant>> callback);
}
