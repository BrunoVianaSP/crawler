package application.domain;

import java.util.HashSet;
import java.util.Set;

import com.google.gson.GsonBuilder;

public class Feed {

	private String title;
	private String link;
	private Set<Item> items = new HashSet<>();

	public Feed() {
		// TODO Auto-generated constructor stub
	}

	public boolean add(Item item) {
		return items.add(item);
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return new GsonBuilder().setPrettyPrinting().create().toJson(this);
	}
}
