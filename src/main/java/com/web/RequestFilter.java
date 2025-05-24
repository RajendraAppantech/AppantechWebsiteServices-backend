package com.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class RequestFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(RequestFilter.class);

	@Override
	public void init(FilterConfig config) throws ServletException {
		logger.info(
				"*********************************** Filter initialized *******************************************");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		final String contextPath = ((HttpServletRequest) servletRequest).getRequestURI();
		final byte[] requestBytes = servletRequest.getInputStream().readAllBytes();
		final HttpServletRequest wrappedRequest = ServletUtils.getWrappedHttpServletRequest(servletRequest,
				requestBytes);
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

		String requestHeaders = Collections.list(httpServletRequest.getHeaderNames()).stream()
				.map(headerName -> headerName + ": " + httpServletRequest.getHeader(headerName))
				.reduce("", (acc, header) -> acc + header + ", ");
		//logger.info("requestHeaders : " + requestHeaders);
		String requestBody = new String(requestBytes, StandardCharsets.UTF_8);
		String clientIp = httpServletRequest.getRemoteAddr();
		logger.info("clientIp : " + clientIp);
		logger.info("\r\n");
		// if (contextPath.startsWith("/psp/")) {
		logger.info("========================== New Request Found ======================= : "+contextPath);
		logger.info("=========================== Request Begin ===========================");
		logger.info("URI          : " + ((HttpServletRequest) servletRequest).getRequestURL());
		logger.info("Method       : " + ((HttpServletRequest) servletRequest).getMethod());
		logger.info("Headers      : " + requestHeaders);
		logger.info("Request Body : " + requestBody.replaceAll("\\s+", " "));
		logger.info("============================ Request End ============================");

		logger.info("=========================== Response Begin ==========================");
		final ByteArrayOutputStream responseOutputStream = new ByteArrayOutputStream();
		final HttpServletResponse wrappedResponse = ServletUtils.getWrappedHttpServletResponse(servletResponse,
				responseOutputStream);
		chain.doFilter(wrappedRequest, wrappedResponse);
		final byte[] responseBytes = responseOutputStream.toByteArray();
		String responseBody = new String(responseBytes, StandardCharsets.UTF_8);
		logger.info("Status code   : " + wrappedResponse.getStatus());
		logger.info("Response Body : " + responseBody);
		logger.info("============================ Response End ============================");
		servletResponse.getOutputStream().write(responseBytes);
		// }
	}

	@Override
	public void destroy() {
	}

}