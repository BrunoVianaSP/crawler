package application;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.domain.Feed;
import application.domain.Item;
import application.parser.DescriptionParser;
import application.parser.LinkParser;
import application.parser.ParagraphParser;
import application.service.FeedBuilder;

public class TestFeedBuilder extends TestBase {

	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void createItemNodeDescriptionsSuccessfuly() throws JSONException {
		log.info("createItemNodeDescriptionsSuccessfuly");
		List<DescriptionParser> parsers = new ArrayList<>();
		parsers.add(new LinkParser());
		parsers.add(new ParagraphParser());
		parsers.add(new LinkParser());
		FeedBuilder fb = new FeedBuilder(jsonPage, parsers);
		Item item = fb.readItem(0);
		log.info("item: " + item);
		assertTrue(item.getDescription().size() > 0);
	}
	
	@Test
	public void createFullFeedSuccessfuly() throws JSONException {
		log.info("createFullFeedSuccessfuly");
		List<DescriptionParser> parsers = new ArrayList<>();
		parsers.add(new LinkParser());
		parsers.add(new ParagraphParser());
		parsers.add(new LinkParser());
		FeedBuilder fb = new FeedBuilder(jsonPage, parsers);
		Feed feed = fb.buildFeed();
		log.info("feed: " + feed);
		assertNotNull(feed);
	}
	

}
