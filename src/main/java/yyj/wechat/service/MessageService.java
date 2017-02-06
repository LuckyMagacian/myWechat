package yyj.wechat.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import yyj.util.AppException;

@Service
public class MessageService {
	
	public String receiveMessage(HttpServletRequest req,HttpServletResponse res){
		try {
			req.setCharacterEncoding("utf-8");
			InputStream in=req.getInputStream();
			InputStreamReader reader=new InputStreamReader(in, "utf-8");
			BufferedReader buffer=new BufferedReader(reader);
			String temp=null;
			StringBuffer strBuffer=new StringBuffer();
			while((temp=buffer.readLine())!=null)
				strBuffer.append(temp);
			in.close();
			return buffer.toString();
		} catch (Exception e) {
			throw new AppException("接收消息异常!",e);
		}
	}
}
