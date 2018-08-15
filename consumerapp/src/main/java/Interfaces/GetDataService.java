package Interfaces;

import java.util.List;

import rest.models.Restaurant;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetDataService {

	@GET("/v2/restaurant/")
	Call<List<Restaurant>> getRestaurants(@Query("lat") float lat, @Query("lng") float lng, @Query("offset") int offset, @Query("limit") int limit);

}