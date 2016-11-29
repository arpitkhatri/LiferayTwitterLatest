package com.portal.controller;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.portal.bean.CustomTweetVO;
import com.portal.helper.TwitterViewHelper;
import java.util.Date;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

@Controller
@RequestMapping({"VIEW"})
public class TwitterViewController
{
  private static final Log LOG = LogFactoryUtil.getLog(TwitterViewController.class);

  @Autowired
  private TwitterViewHelper twitterViewHelper;

  @Autowired
  private CustomTweetVO customTweetVO;

  public TwitterViewHelper getTwitterViewHelper() { return this.twitterViewHelper; }

  public void setTwitterViewHelper(TwitterViewHelper twitterViewHelper)
  {
    this.twitterViewHelper = twitterViewHelper;
  }
  @RenderMapping
  public String showView(Model model, RenderRequest request, RenderResponse response) {
    try {
      PortletSession portletSession = request.getPortletSession();

      Date currentDate = new Date();
      long currentTime = System.currentTimeMillis();
      LOG.info("currentTime-->" + currentTime);
      LOG.info("currentDate-->" + currentDate);
      long previousApiCallTime = 0L;
      if (portletSession.getAttribute("apiCallTime") != null) {
        previousApiCallTime = ((Long)portletSession.getAttribute("apiCallTime")).longValue();
        LOG.info("previousApiCallTime-->" + previousApiCallTime);
      }

      long diff = 0L;
      long diffMinutes = 0L;
      if (previousApiCallTime != 0L) {
        diff = currentTime - previousApiCallTime;
        diffMinutes = diff / 60000L % 60L;
        LOG.info("diffMinutes-->" + diffMinutes);
      }

      if (diffMinutes >= 15L) {
        LOG.info("Calling the api in every 15 minutes");
        this.customTweetVO = this.twitterViewHelper.fetchTweets();
        portletSession.setAttribute("customTweetVO", this.customTweetVO);
      }
      else if (previousApiCallTime == 0L) {
        LOG.info("Calling the api for the first time");
        this.customTweetVO = this.twitterViewHelper.fetchTweets();
        portletSession.setAttribute("customTweetVO", this.customTweetVO);
      }
      else {
        LOG.info("15 minutes not happened,so taking value from session");
        this.customTweetVO = ((CustomTweetVO)portletSession.getAttribute("customTweetVO"));
      }

      portletSession.setAttribute("apiCallTime", Long.valueOf(currentTime));

      LOG.info("1-->" + portletSession.getAttribute("apiCallTime").toString());

      model.addAttribute("name", this.customTweetVO.getName());
      model.addAttribute("screenName", this.customTweetVO.getScreenName());
      model.addAttribute("latestTweet", this.customTweetVO.getLatestTweet());
      model.addAttribute("profileImageUrl", this.customTweetVO.getProfileImageUrl());
      model.addAttribute("latestTweetDate", this.customTweetVO.getLatestTweetDate());
    }
    catch (Exception e)
    {
      LOG.error("Some error occurred while fetching the tweets " + e);
    }

    LOG.info("latestTweet " + this.customTweetVO.getLatestTweet());
    return "TwitterBook/view";
  }
}