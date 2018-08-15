package com.doordash.consumer;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import Interfaces.GetDataService;
import rest.agents.DataAgent;
import rest.models.Restaurant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DataAgentTest {

	private DataAgent dataAgent;

	@Mock
	GetDataService dataService;

	@Mock
	Call<List<Restaurant>> call;

	@Before
	public void testConstructor () {
		dataService = mock(GetDataService.class);
		dataAgent = new DataAgent(dataService);
	}

	@Test
	public void getRestaurantsTest () {
		Callback cb = new Callback<List<Restaurant>>() {
			@Override
			public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {

			}

			@Override
			public void onFailure(Call<List<Restaurant>> call, Throwable t) {

			}
		};
		call = mock(Call.class);
		when(dataService.getRestaurants(1f, 1f, 0, 20)).thenReturn(call);
		dataAgent.getRestaurants(1f, 1f, 0, 20, cb);
		verify(dataService).getRestaurants(1f, 1f, 0, 20);
	}

	@Test
	public void getRestaurantsNearHQTest () {
		Callback cb = new Callback<List<Restaurant>>() {
			@Override
			public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {

			}

			@Override
			public void onFailure(Call<List<Restaurant>> call, Throwable t) {

			}
		};
		call = mock(Call.class);
		when(dataService.getRestaurants(37.422740f, -122.139956f, 0, 20)).thenReturn(call);
		dataAgent.getRestaurantsNearHQ(0, 20, cb);
		verify(dataService).getRestaurants(37.422740f, -122.139956f, 0, 20);
	}

	@After
	public void cleanup () {
		dataAgent = null;
	}
}
