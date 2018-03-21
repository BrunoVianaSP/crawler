package application.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import application.domain.Feed;
import application.service.FeedService;

@Controller
public class MainController {

	private final Logger log = Logger.getLogger(MainController.class);

	@Autowired
	private FeedService service;
	
	public MainController() {
		// TODO Auto-generated constructor stub
	}

	@RequestMapping(value = "/feed/download/{user}/{pass}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Feed> getFeed(@PathVariable("user") String user, @PathVariable("pass") String pass) {
		log.info("getFeed");
		try {

			if (!isValidUser(user, pass)) {
				throw new IllegalArgumentException("Invalid user.");
			}

			log.info("User: " + user);
			log.info("Pass: " + pass);
			
			URL oURL = new URL("https://revistaautoesporte.globo.com/rss/ultimas/feed.xml");
			URLConnection oConnection = oURL.openConnection();
			oConnection.setConnectTimeout(5000);
			BufferedReader oReader = new BufferedReader(new InputStreamReader(oConnection.getInputStream()));
			Feed feed = service.createFeed(oReader);
			return new ResponseEntity<Feed>(feed, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<Feed>(new Feed(), HttpStatus.FORBIDDEN);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Feed>(new Feed(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private boolean isValidUser(String user, String pass) {
		return user.equals("admin") && pass.equals("admin");
	}
}


































//package application.controller;
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.net.URL;
//import java.net.URLConnection;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import application.domain.Feed;
//import application.domain.User;
//import application.service.FeedService;
//
//@Controller
//public class MainController {
//
//	private final Logger log = Logger.getLogger(getClass().getSimpleName());
//
//	@Autowired
//	private FeedService service;
//	
//	public MainController() {
//		// TODO Auto-generated constructor stub
//	}
//
//	@RequestMapping(value = "/feed/download", method = RequestMethod.POST)
//	public @ResponseBody ResponseEntity<Feed> getFeed(@RequestBody User user) {
//		log.info("getFeed");
//		try {
//
//			if (!isValidUser(user)) {
//				throw new IllegalArgumentException("Invalid user.");
//			}
//
//			URL oURL = new URL("https://revistaautoesporte.globo.com/rss/ultimas/feed.xml");
//			URLConnection oConnection = oURL.openConnection();
//			oConnection.setConnectTimeout(5000);
//			BufferedReader oReader = new BufferedReader(new InputStreamReader(oConnection.getInputStream()));
//			Feed feed = service.createFeed(oReader);
//			return new ResponseEntity<Feed>(feed, HttpStatus.OK);
//		} catch (IllegalArgumentException e) {
//			return new ResponseEntity<Feed>(new Feed(), HttpStatus.FORBIDDEN);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new ResponseEntity<Feed>(new Feed(), HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
//
//	private boolean isValidUser(User user) {
//		return user.getUsername().equals("admin") && user.getPass().equals("admin");
//	}
//	
//	@RequestParam("name") String name
//}
