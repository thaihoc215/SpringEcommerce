package org.hochnt.springmvcshop.controller;

import javax.servlet.http.HttpServletRequest;

import org.hochnt.springmvcshop.util.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
// Cần thiết cho Hibernate Transaction.
@Transactional
// Cần thiết để sử dụng RedirectAttributes
@EnableWebMvc
public class MainController {
	// @Autowired
	// private OrderDAO orderDAO;
	//
	// @Autowired
	// private ProductDAO productDAO;
	//
	@RequestMapping("/403")
	public String accessDenied() {
		return "/403";
	}

	@RequestMapping("/")
	public String home() {
		//di chuyển đến trang jsp tương ứng
		return "index";
	}

	// Danh sách sản phẩm.
	@RequestMapping("/productList")
	public String listProductHandler(Model model, //
			@RequestParam(value = "name", defaultValue = "") String likeName,
			@RequestParam(value = "page", defaultValue = "1") int page) {
		
		return "productList";
	}

	 // GET: Hiển thị giỏ hàng.
	   @RequestMapping(value = { "/shoppingCart" }, method = RequestMethod.GET)
	   public String shoppingCartHandler(HttpServletRequest request, Model model) {
		// CartInfo myCart = Utils.getCartInSession(request);
		//
		// model.addAttribute("cartForm", myCart);
	       return "shoppingCart";
	   }
}
