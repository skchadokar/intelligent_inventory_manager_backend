package com.trigun.iim.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.trigun.iim.controllers.UserController;

@Component
public class LoginFilter implements Filter {// implements Filter

	private static final Logger LOGGER = LoggerFactory.getLogger(LoginFilter.class);

	private String TAG_NAME = "LoginFilter";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		LOGGER.info(TAG_NAME + " :: inside doFilter ");
		// TODO Auto-generated method stub
		String mPath = ((HttpServletRequest) request).getServletPath();
		LOGGER.info(TAG_NAME + " :: inside doFilter : currentPath :: "+ mPath);
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
}
