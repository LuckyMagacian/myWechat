package yyj.wechat.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import yyj.util.AppException;
import yyj.wechat.manageer.TokenManager;
@Service
public class WebTokenService {
	/**
	 * 获取网页token
	 * @param req
	 * @param res
	 */
	public void makeWebToken(HttpServletRequest req, HttpServletResponse res){
		try {
			req.setCharacterEncoding("utf-8");
			res.setCharacterEncoding("utf-8");
			String code=req.getParameter("code");
			TokenManager.generatorWebAccessTokenMetadata(code);
		} catch (Exception e) {
			throw new AppException("获取code异常", e);
		}

	}
}
