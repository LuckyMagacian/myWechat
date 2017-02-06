package yyj.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import yyj.service.WechatService;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Controller
public class TestController {
	@Resource
	private WechatService wechat;
	public TestController() {
		System.out.println("TestController init !"+System.nanoTime());
	}
	
	@RequestMapping("/message.do")
	public String receiveMessage(HttpServletRequest req,HttpServletResponse res){
		System.out.println("有新消息!");
		return wechat.receiveMessage(req, res);
	}
}
