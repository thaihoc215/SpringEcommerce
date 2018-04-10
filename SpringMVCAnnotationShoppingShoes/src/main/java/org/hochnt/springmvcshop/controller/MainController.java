package org.hochnt.springmvcshop.controller;

import org.hochnt.springmvcshop.dao.OrderDAO;
import org.hochnt.springmvcshop.dao.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
// Cần thiết cho Hibernate Transaction.
@Transactional
// Cần thiết để sử dụng RedirectAttributes
@EnableWebMvc
public class MainController {
//	@Autowired
//	private OrderDAO orderDAO;
//
//	@Autowired
//	private ProductDAO productDAO;
//
//	@RequestMapping("/403")
//	public String accessDenied() {
//		return "/403";
//	}

	@RequestMapping("/")
	public String home() {
		return "index";
	}

//	@RequestMapping("/")
//	public String listProductHandler(Model model, //
//			@RequestParam(value = "name", defaultValue = "") String likeName,
//			@RequestParam(value = "page", defaultValue = "1") int page) {
//		return "productList";
//	}

}
