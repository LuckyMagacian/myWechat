package yyj.service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import yyj.wechat.service.MessageService;
import yyj.wechat.service.ValidServerService;
import yyj.wechat.service.WebTokenService;

@Order(0)
@Service
public class WechatService {
	@Resource
	private ValidServerService valid;
	@Resource
	private MessageService     message;
	@Resource
	private WebTokenService    webToken;
	
	public WechatService() {
		System.out.println("WechatService init !"+System.nanoTime());
	}
	
	public String receiveMessage(HttpServletRequest req,HttpServletResponse res){
		return message.receiveMessage(req, res);
	}
}
