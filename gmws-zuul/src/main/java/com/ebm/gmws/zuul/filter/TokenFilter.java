package com.ebm.gmws.zuul.filter;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class TokenFilter extends ZuulFilter {

	@Override
	public Object run() {
		System.out.println("TokenFilter start.");
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		String token = request.getParameter("token");
		HttpServletResponse response = ctx.getResponse();
		if(token == null) {
			try {
				ctx.setSendZuulResponse(true);	//对此请求进行路由
				ctx.setResponseStatusCode(200);
				ctx.set("issuccess", true);	//让下一个Filter看到上一个Filter的状态
				response.getWriter().write("token is empty, check pls");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		} else {
			ctx.setSendZuulResponse(false);	//过滤该请求，不对其进行路由
			ctx.setResponseStatusCode(401);
			ctx.set("issuccess", false);
		}
		System.out.println("zuul TokenFilter 验证通过");
		return null;
	}

	//是否要过滤
	@Override
	public boolean shouldFilter() {
		return false;
	}

	//过滤的顺序
	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre";
	}

}
