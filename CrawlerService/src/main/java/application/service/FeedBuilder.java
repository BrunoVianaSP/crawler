package application.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import application.domain.Description;
import application.domain.Feed;
import application.domain.Item;
import application.parser.DescriptionParser;

public class FeedBuilder {

	protected final Logger log = Logger.getLogger(getClass().getSimpleName());
	private final JSONObject jsonFeed;
	private final List<DescriptionParser> descriptionParsers;

	public FeedBuilder(JSONObject jsonFeed, List<DescriptionParser> descriptionParsers) {
		this.jsonFeed = jsonFeed;
		this.descriptionParsers = descriptionParsers;
	}

	public Item readItem(int index) throws JSONException {
		log.info("readItem");
		JSONArray items = (JSONArray) jsonFeed.get("item");
		JSONObject itemNode = (JSONObject) items.get(0);
		Item item = new Item();
		item.setTitle(itemNode.getString("title"));
		item.setLink(itemNode.getString("link"));
		List<Description> descriptions = readDescriptions(itemNode.getString("description"));
		item.setDescription(descriptions);
		return item;
	}

	private List<Description> readDescriptions(String description) throws JSONException {
		List<Description> array = new ArrayList<>();
		for (DescriptionParser parser : descriptionParsers) {
			List<Description> list = parser.parse(description);
			// log.info("list: " + list);
			array.addAll(list);
		}
		return array;
	}

	public Feed buildFeed() {
		Feed feed = new Feed();
		feed.setTitle(jsonFeed.getString("title"));
		feed.setLink(jsonFeed.getString("link"));
		JSONArray items = (JSONArray) jsonFeed.get("item");
		for (int index = 0; index < items.length(); index++) {
			feed.add(readItem(index));
		}
		return feed;
	}
	
	
	
}
