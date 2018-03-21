package application;

import static org.junit.Assert.assertNotNull;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.util.Parser;

public class TestFileParser extends TestBase {

	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void convertXMLToJSONSuccessfuly() throws JSONException {
		log.info("convertXMLToJSONSuccessfuly");
		JSONObject jsondata = Parser.parseFromXMLToJson(sResponse);
		assertNotNull(jsondata);
		log.info("sResponse: " + jsondata.toString(1));
	}
}
