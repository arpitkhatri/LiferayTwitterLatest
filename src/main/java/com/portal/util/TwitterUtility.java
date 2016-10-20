package com.portal.util;

import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

import com.liferay.util.portlet.PortletProps;

public class TwitterUtility {
	
	/**
	 * This method authenticates the twitter account of the user and generates the twitterTemplate object
	 * @return
	 */
	public static Twitter getTwitterTemplate(){
		
		/*start :: Fetching the authentication parameters from the properties file*/
		String consumerKey = PortletProps.get("consumerKey");
		String consumerSecret = PortletProps.get("consumerSecret");
		String accessToken = PortletProps.get("accessToken");
		String accessTokenSecret = PortletProps.get("accessTokenSecret");
		/*start :: Fetching the authentication parameters from the properties file*/
		
		
		TwitterTemplate twitterTemplate = new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);
		return twitterTemplate;
		
	}
}
