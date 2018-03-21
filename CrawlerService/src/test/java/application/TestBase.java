package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;

import application.util.Parser;

public class TestBase {

	protected final Logger log = Logger.getLogger(TestBase.class);

	protected String url = "https://revistaautoesporte.globo.com/rss/ultimas/feed.xml";
	protected URL oURL;
	protected URLConnection oConnection;
	protected BufferedReader oReader;
	protected String sLine;
	protected StringBuilder sbResponse;
	protected String sResponse = null;
	protected JSONObject jsonPage;
	protected JSONObject jsonItemNode;
	protected String description;

	@Before
	public void setUp() throws Exception {
		oURL = new URL(url);
		try {
			oConnection = oURL.openConnection();
			oConnection.setConnectTimeout(5000);
			oReader = new BufferedReader(new InputStreamReader(oConnection.getInputStream()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		readLines(oReader);
		jsonPage = Parser.parseFromXMLToJson(sResponse);
		readFirstItemNode();
	}

	private void readFirstItemNode() {
		JSONObject jsonRSS = (JSONObject) jsonPage.get("rss");
		JSONObject channel = (JSONObject) jsonRSS.get("channel");
		JSONArray items = (JSONArray) channel.get("item");
		jsonItemNode = (JSONObject) items.get(0);
		description = jsonItemNode.getString("description");
	}

	@After
	public void tearDown() throws Exception {
		if (oReader != null) {
			try {
				oReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void readLines(BufferedReader buffer) throws IOException {
		sbResponse = new StringBuilder();
		while ((sLine = oReader.readLine()) != null) {
			sbResponse.append(sLine);
		}
		sResponse = sbResponse.toString();
	}

}
