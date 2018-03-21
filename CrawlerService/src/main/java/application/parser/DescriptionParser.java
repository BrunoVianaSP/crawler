package application.parser;

import java.util.List;

import application.domain.Description;

public abstract class DescriptionParser {

	public abstract List<Description> parse(final String str);
}
