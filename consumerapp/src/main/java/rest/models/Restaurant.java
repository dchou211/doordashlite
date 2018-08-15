package rest.models;

import com.google.gson.annotations.SerializedName;

public class Restaurant {
	@SerializedName("id")
	private Integer id;

	@SerializedName("name")
	private String name;

	@SerializedName("description")
	private String description;

	@SerializedName("cover_img_url")
	private String imgUrl;

	@SerializedName("status")
	private String status;

	@SerializedName("delivery_fee")
	private Integer deliveryFeeCents;

	public Restaurant (Integer id, String name, String description, String imgUrl, String status, Integer deliveryFeeCents) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.imgUrl = imgUrl;
		this.status = status;
		this.deliveryFeeCents = deliveryFeeCents;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getDeliveryFeeCents() {
		return deliveryFeeCents;
	}

	public void setDeliveryFeeCents(Integer deliveryFeeCents) {
		this.deliveryFeeCents = deliveryFeeCents;
	}
}
