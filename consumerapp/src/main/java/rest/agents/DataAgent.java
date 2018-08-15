package rest.agents;

import java.util.List;
import Interfaces.DataServiceProvider;
import Interfaces.GetDataService;
import rest.client.RetrofitRestClient;
import rest.models.Restaurant;
import retrofit2.Call;
import retrofit2.Callback;

public class DataAgent implements DataServiceProvider {

	private GetDataService service;

	public DataAgent () {
		service = RetrofitRestClient.getRetrofitInstance().create(GetDataService.class);
	}

	public DataAgent (GetDataService service) {
		this.service = service;
	}

	@Override
	public void getRestaurants(float lat, float lng, int offset, int limit, Callback<List<Restaurant>> callback) {
		Call<List<Restaurant>> call = service.getRestaurants(lat, lng, offset, limit);
		call.enqueue(callback);
	}

	@Override
	public void getRestaurantsNearHQ(int offset, int limit, Callback<List<Restaurant>> callback) {
		getRestaurants(37.422740f, -122.139956f, offset, limit, callback);
	}
}
