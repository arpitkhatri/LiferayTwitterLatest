package com.portal.helper;

import com.portal.bean.CustomTweetVO;

public interface TwitterViewHelper {
	public CustomTweetVO fetchTweets() throws Exception;
}
