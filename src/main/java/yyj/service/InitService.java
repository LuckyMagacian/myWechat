package yyj.service;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@Order(Ordered.LOWEST_PRECEDENCE)
public class InitService {
	
	public InitService() {
		System.out.println("InitService init !"+System.nanoTime());
	}
}
