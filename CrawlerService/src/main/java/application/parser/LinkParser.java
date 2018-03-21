package application.parser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.domain.Description;

public class LinkParser extends DescriptionParser {

	@Override
	public List<Description> parse(final String str) {

		List<Description> array = new ArrayList<>();
		Pattern p = Pattern.compile("href=\"(.*?)\"\\>");
		Matcher m = p.matcher(str);

		Set<String> links = new HashSet<>();
		while (m.find()) {
			String line = m.group(1);
			links.add(line);
		}

		Description desc = new Description();
		desc.setType("links");
		desc.setContent(links);
		array.add(desc);
		return array;
	}
}
