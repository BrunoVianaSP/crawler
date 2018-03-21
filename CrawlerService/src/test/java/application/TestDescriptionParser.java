package application;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.json.JSONException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.domain.Description;
import application.parser.DescriptionParser;
import application.parser.ImageParser;
import application.parser.LinkParser;
import application.parser.ParagraphParser;

public class TestDescriptionParser extends TestBase {

	@Before
	public void setUp() throws Exception {
		super.setUp();

	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void parseTextSuccess() throws JSONException {
		log.info("parseTextSuccess");
		DescriptionParser parser = new ParagraphParser();
		List<Description> texts = parser.parse(description);
		log.info("texts: " + texts);
		assertTrue(texts.size() > 0);
	}

	@Test
	public void parseImagesSuccess() throws JSONException {
		log.info("parseImagesSuccess");
		DescriptionParser parser = new ImageParser();
		List<Description> images = parser.parse(description);
		log.info("images: " + images);
		assertTrue(images.size() > 0);
	}

	@Test
	public void parseLinksSuccess() throws JSONException {
		log.info("parseLinksSuccess");
		DescriptionParser parser = new LinkParser();
		List<Description> links = parser.parse(description);
		log.info("links: " + links);
		assertTrue(links.size() > 0);
	}
}
