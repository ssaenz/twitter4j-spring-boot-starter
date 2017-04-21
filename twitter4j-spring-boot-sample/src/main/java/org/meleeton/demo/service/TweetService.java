/**
 * 
 */
package org.meleeton.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import twitter4j.FilterQuery;
import twitter4j.ResponseList;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * @author Siva
 *
 */
@Service
public class TweetService {
	
	@Autowired
	private Twitter twitter;
	
	public List<String> getLatestTweets(){
		List<String> tweets = new ArrayList<>();
		try {
			ResponseList<Status> homeTimeline = twitter.getHomeTimeline();
			for (Status status : homeTimeline) {
				tweets.add(status.getText());
			}
		} catch (TwitterException e) {
			throw new RuntimeException(e);
		}
		return tweets;
	}
	
	public void streamSample () {
		StatusListener listener = new StatusListener(){
	        public void onStatus(Status status) {
	            System.out.println(status.getUser().getName() + " : " + status.getText());
	        }
	        public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {}
	        public void onTrackLimitationNotice(int numberOfLimitedStatuses) {}
	        public void onException(Exception ex) {
	            ex.printStackTrace();
	        } 
	        public void onScrubGeo(long userId, long upToStatusId) {
	            System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
	        }
			@Override
			public void onStallWarning(StallWarning warning) {
				// TODO Auto-generated method stub
				
			}
	    };
	    TwitterStream twitterStream = new TwitterStreamFactory().getInstance(twitter.getAuthorization());
	    twitterStream.addListener(listener);
	    // sample() method internally creates a thread which manipulates TwitterStream and calls these adequate listener methods continuously.
	    twitterStream.sample();
	}
	
	public static void main(String[] args) {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		
		
	    cb.setDebugEnabled(true);
	    cb.setOAuthConsumerKey("PZmhozUUdQf5QWsmQ79SanXAn");
	    cb.setOAuthConsumerSecret("tK2MqqslNeIBIWvmjfhzpf7vfCXmwtIrCMhUQ1smA3I9NqByYu");
	    cb.setOAuthAccessToken("3201252621-1lb5ZvNbkr78SsFcNk6w1imPx7i7ODBHcIXmPZS");
	    cb.setOAuthAccessTokenSecret("ZtrITUfxQubUTqBaPitVrvrUnr4k5U6aVXFXiNnK4YhPy");
	    TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
	    StatusListener listener = new StatusListener(){
	        public void onStatus(Status status) {
	            System.out.println(status.getUser().getName() + " : " + status.getText());
	        }
	        public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {}
	        public void onTrackLimitationNotice(int numberOfLimitedStatuses) {}
	        public void onException(Exception ex) {
	            ex.printStackTrace();
	        } 
	        public void onScrubGeo(long userId, long upToStatusId) {
	            System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
	        }
			@Override
			public void onStallWarning(StallWarning warning) {
				// TODO Auto-generated method stub
				
			}
	    };
	    
	    FilterQuery fq = new FilterQuery();
	    String keywords[] = {"angular"};

	    fq.track(keywords);

	    twitterStream.addListener(listener);
	    twitterStream.filter(fq);    
	}
}
