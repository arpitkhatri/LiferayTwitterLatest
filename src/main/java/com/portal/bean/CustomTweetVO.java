package com.portal.bean;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class CustomTweetVO {
	
	private String name;
	private String screenName;
	private String profileImageUrl;
	private String latestTweet;
	private String latestTweetDate;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public String getProfileImageUrl() {
		return profileImageUrl;
	}
	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}
	public String getLatestTweet() {
		return latestTweet;
	}
	public void setLatestTweet(String latestTweet) {
		this.latestTweet = latestTweet;
	}
	public String getLatestTweetDate() {
		return latestTweetDate;
	}
	public void setLatestTweetDate(String latestTweetDate) {
		this.latestTweetDate = latestTweetDate;
	}

	
	
	
}
