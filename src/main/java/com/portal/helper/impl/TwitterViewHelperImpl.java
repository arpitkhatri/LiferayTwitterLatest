package com.portal.helper.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.social.twitter.api.TimelineOperations;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Component;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.portal.bean.CustomTweetVO;
import com.portal.helper.TwitterViewHelper;
import com.portal.util.TwitterUtility;

@Component
@Qualifier("twitterViewHelper")
public class TwitterViewHelperImpl implements TwitterViewHelper{
	
	private static final Log LOG = LogFactoryUtil.getLog(TwitterViewHelperImpl.class);
	
	@Autowired
	private CustomTweetVO customTweetVO;
	
	@Override
	/**
	 * This method fetches the user profile details and the latest tweet posted by the user
	 */
	public CustomTweetVO fetchTweets() throws Exception{
		LOG.info("Entry :: inside the fetchTweets method helperImpl");
		List<Tweet> tweetList = new ArrayList<Tweet>();
		String latestTweetByUser = "" ;
		Tweet latestTweet = null;
		String latestTweetDate = "";
		Twitter twitter=TwitterUtility.getTwitterTemplate();//Fetching the twitter object of the specified user
		customTweetVO.setName(twitter.userOperations().getUserProfile().getName());//Fetching the name of the specified user
		customTweetVO.setScreenName(twitter.userOperations().getUserProfile().getScreenName());//Fetching the twitter screen name of the specified user
		customTweetVO.setProfileImageUrl(twitter.userOperations().getUserProfile().getProfileImageUrl().replaceAll("normal","bigger"));//Fetching user profile image
		
		LOG.info("Image-->"+twitter.userOperations().getUserProfile().getProfileImageUrl());
		
		tweetList=queryForTweets( twitter);
		if(tweetList != null && !tweetList.isEmpty()){
			
			latestTweet = tweetList.get(0);
			latestTweetByUser = latestTweet.getText();
			latestTweetDate = formatDate(latestTweet.getCreatedAt());
					
			LOG.info("latestTweetByUSer "+latestTweetByUser);
			LOG.info("tweet date "+latestTweetDate);
		}
		if(latestTweetByUser != null && !("").equals(latestTweetByUser)){
			customTweetVO.setLatestTweet(latestTweetByUser);
		}
		if(latestTweetDate != null && !("").equals(latestTweetDate)){
			customTweetVO.setLatestTweetDate(latestTweetDate);
		}
		LOG.info("Exit :: inside the fetchTweets method helperImpl");
		return customTweetVO;
	}
	
	/**
	 * This method fetches the tweets posted by the user
	 * @param twitter
	 * @return
	 */
	private List<Tweet> queryForTweets(Twitter twitter) {
		
		LOG.info("Entry :: inside the queryForTweets method helperImpl");
		
	    TimelineOperations timelineOps = twitter.timelineOperations();
	    List<Tweet> results = timelineOps.getUserTimeline();//Fetching the tweets posted by the user
	    
	    LOG.info("Exit :: inside the queryForTweets method helperImpl");
	    return results;
	  }
	
	/**
	 * This method converts the tweetDate to EST timezone
	 * @param tweetDate
	 * @return
	 */
	private String formatDate(Date tweetDate){
		
		LOG.info("Entry :: inside the queryForTweets method helperImpl");
		
		SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("EST"));
		String formattedTweetDate = sdf.format(tweetDate);
		
		LOG.info("Exit :: inside the queryForTweets method helperImpl");
		return formattedTweetDate;
	}

}
