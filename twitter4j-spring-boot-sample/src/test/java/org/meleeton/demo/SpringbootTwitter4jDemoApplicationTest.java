/**
 * 
 */
package org.meleeton.demo;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.meleeton.demo.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import twitter4j.TwitterException;

/**
 * @author Siva
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SpringbootTwitter4jDemoApplicationTest  {


	@Autowired
	private TweetService tweetService;
	
	@Test
	public void testGetTweets() throws TwitterException {
		List<String> tweets = tweetService.getLatestTweets();
		for (String tweet : tweets) {
			System.err.println(tweet);
		}
	}

}
