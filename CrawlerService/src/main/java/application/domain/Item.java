package application.domain;

import java.util.List;

import com.google.gson.GsonBuilder;

public class Item {
	private String title;
	private String link;
	private List<Description> description;

	public Item() {
		// TODO Auto-generated constructor stub
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

	public List<Description> getDescription() {
		return description;
	}

	public void setDescription(List<Description> description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return new GsonBuilder().setPrettyPrinting().create().toJson(this);
	}
}
