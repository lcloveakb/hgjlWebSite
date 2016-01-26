package com.senseId.social.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("supportAction")
public class SupportAction extends ActionSupport  {

	public String toFaq(){
		
		return "faq";
	}
	
	public String toAbout(){
		
		
		return "about";
	}
}
