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

	private static final String KEY_CHANNEL = "channel";
	private static final String KEY_RSS = "rss";
	private static final String KEY_ITEM = "item";
	private static final String KEY_LINK = "link";
	private static final String KEY_TITLE = "title";
	
	protected final Logger log = Logger.getLogger(getClass().getSimpleName());
	private final JSONObject jsonFeed;
	private final List<DescriptionParser> descriptionParsers;

	public FeedBuilder(JSONObject jsonFeed, List<DescriptionParser> descriptionParsers) {
		this.jsonFeed = jsonFeed;
		this.descriptionParsers = descriptionParsers;
	}

	public Item readItem(int index) throws JSONException {
		JSONObject itemNode = extractItemNode();
		Item item = createItem(itemNode);
		return item;
	}

	private JSONObject extractItemNode() {
		JSONObject channel = getChannelLevel();
		JSONArray items = (JSONArray) channel.get(KEY_ITEM);
		JSONObject itemNode = (JSONObject) items.get(0);
		return itemNode;
	}

	private Item createItem(JSONObject itemNode) {
		Item item = new Item();
		item.setTitle(itemNode.getString(KEY_TITLE));
		item.setLink(itemNode.getString(KEY_LINK));
		List<Description> descriptions = readDescriptions(itemNode.getString("description"));
		item.setDescription(descriptions);
		return item;
	}

	private List<Description> readDescriptions(String description) throws JSONException {
		List<Description> array = new ArrayList<>();
		for (DescriptionParser parser : descriptionParsers) {
			List<Description> list = parser.parse(description);
			array.addAll(list);
		}
		return array;
	}

	public Feed buildFeed() {

		JSONObject channel = getChannelLevel();

		Feed feed = new Feed();
		feed.setTitle(channel.getString(KEY_TITLE));
		feed.setLink(channel.getString(KEY_LINK));

		JSONArray items = (JSONArray) channel.get(KEY_ITEM);

		for (int index = 0; index < items.length(); index++) {
			feed.add(readItem(index));
		}
		return feed;
	}

	private JSONObject getChannelLevel() {
		JSONObject jsonRSS = (JSONObject) jsonFeed.get(KEY_RSS);
		JSONObject channel = (JSONObject) jsonRSS.get(KEY_CHANNEL);
		return channel;
	}
}
