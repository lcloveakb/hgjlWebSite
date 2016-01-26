package com.senseId.social.controller;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.StrutsRequestWrapper;
import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

public class StrutsFilter extends StrutsPrepareAndExecuteFilter {

		@Override
		public void doFilter(ServletRequest request, ServletResponse resopnse,
				FilterChain chain) throws IOException, ServletException {

			HttpServletRequest req = (HttpServletRequest) request;
			String url = req.getRequestURI();
			if (decideURI(url)) {
				chain.doFilter(new StrutsRequestWrapper((HttpServletRequest) request),resopnse);
			}else{
				chain.doFilter(request, resopnse);
			}
		}
		
		private boolean decideURI(String url){
			if(url.endsWith("imageUp.jsp")){
			return true;
			}else if(url.endsWith("fileUp.jsp")){
			return true;
			}
			return false;
			}
	}

