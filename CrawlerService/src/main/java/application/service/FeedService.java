package application.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import application.domain.Feed;
import application.parser.DescriptionParser;
import application.parser.LinkParser;
import application.parser.ParagraphParser;
import application.util.Parser;

@Service
public class FeedService {

	public FeedService() {
		// TODO Auto-generated constructor stub
	}

	public Feed createFeed(BufferedReader oReader) throws IOException {
		String strFeed = readLines(oReader);
		JSONObject jsonPage = Parser.parseFromXMLToJson(strFeed);
		List<DescriptionParser> parsers = getParsers();
		FeedBuilder fb = new FeedBuilder(jsonPage, parsers);
		return fb.buildFeed();
	}

	private List<DescriptionParser> getParsers() {
		List<DescriptionParser> parsers = new ArrayList<>();
		parsers.add(new LinkParser());
		parsers.add(new ParagraphParser());
		parsers.add(new LinkParser());
		return parsers;
	}

	private String readLines(BufferedReader buffer) throws IOException {
		StringBuilder sbResponse = new StringBuilder();
		String sLine = null;
		while ((sLine = buffer.readLine()) != null) {
			sbResponse.append(sLine);
		}
		return sbResponse.toString();
	}
}
