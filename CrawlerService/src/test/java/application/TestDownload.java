package application;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDownload extends TestBase {

	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void downloadPageSuccessfuly() {
		log.info("downloadPageSuccessfuly");
		log.info("sResponse: " + sResponse);
		assertNotNull(sResponse);
	}

}
