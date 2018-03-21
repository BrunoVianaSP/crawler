package application.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.domain.Description;

public class ParagraphParser extends DescriptionParser {

	@Override
	public List<Description> parse(final String str) {
		Pattern p = Pattern.compile("\\<p>(.*?)\\</p>");
		Matcher m = p.matcher(str);

		List<Description> array = new ArrayList<>();
		while (m.find()) {
			String line = m.group(1);

			if (line.contains("&nbsp;")) {
				continue;
			}

			Description desc = new Description();
			desc.setType("text");
			String noNonAsciiChar = clearText(line);
			desc.setContent(noNonAsciiChar);
			array.add(desc);
		}

		return array;
	}

	private String clearText(String line) {
		String newLine = line.replaceAll("\\<.*?>", "");
		newLine = newLine.replaceAll("[^\\p{Alpha}\\p{Digit}]+", " ");
		return newLine;
	}
}
