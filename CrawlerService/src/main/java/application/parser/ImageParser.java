package application.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.domain.Description;

public class ImageParser extends DescriptionParser {

	@Override
	public List<Description> parse(final String str) {
		Pattern p = Pattern.compile("src=\"(.*?)\"");
		Matcher m = p.matcher(str);

		List<Description> array = new ArrayList<>();

		while (m.find()) {
			String line = m.group(1);
			Description desc = new Description();
			desc.setType("image");
			desc.setContent(line);
			array.add(desc);
		}

		return array;
	}

}
