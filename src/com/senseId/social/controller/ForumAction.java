package com.senseId.social.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("forumAction")
public class ForumAction extends ActionSupport  {

	public String toForum(){
		
		return "forum";
	}
}
