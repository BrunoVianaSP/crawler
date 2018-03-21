package application;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ TestDownload.class, TestFileParser.class, TestFeedBuilder.class, TestDescriptionParser.class })
public class TestAll {

	public TestAll() {
		// TODO Auto-generated constructor stub
	}
}
