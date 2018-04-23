package org.hochnt.springmvcshop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hochnt.springmvcshop.dao.OrderDAO;
import org.hochnt.springmvcshop.dao.ProductDAO;
import org.hochnt.springmvcshop.model.OrderDetailInfo;
import org.hochnt.springmvcshop.model.OrderInfo;
import org.hochnt.springmvcshop.model.PaginationResult;
import org.hochnt.springmvcshop.model.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
// Cần thiết cho Hibernate Transaction.
@Transactional
// Cần thiết để sử dụng RedirectAttributes
@EnableWebMvc
public class AdminController {
	@Autowired
	private OrderDAO orderDAO;

	@Autowired
	private ProductDAO productDAO;

	// GET: Show Login Page
	// GET: Hiển thị trang login
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String login(Model model) {

		return "login";
	}

	@RequestMapping(value = { "/accountInfo" }, method = RequestMethod.GET)
	public String accountInfo(Model model) {

		// lấy thông tin người dùng đăng nhập
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(userDetails.getPassword());
		System.out.println(userDetails.getUsername());
		System.out.println(userDetails.isEnabled());

		model.addAttribute("userDetails", userDetails);

		return "accountInfo";
	}

	/*-------------------------Sản Phẩm-------------------------------*/
	// GET: Hien thi product
	@RequestMapping(value = { "/product" }, method = RequestMethod.GET)
	public String productHandler(Model model, @RequestParam(value = "code", defaultValue = "") String code) {
		ProductInfo productInfo = null;
		if (code != null && code.length() > 0) {
			productInfo = productDAO.findProductInfo(code);
		}
		if (productInfo == null) {// truong hop tao moi
			productInfo = new ProductInfo();
			productInfo.setNewProduct(true);
		}
		model.addAttribute("productForm", productInfo);
		return "product";
	}

	// POST: Save product
	@RequestMapping(value = { "/product" }, method = RequestMethod.POST)
	// Tránh ngoại lệ: UnexpectedRollbackException (Xem giải thích thêm).
	@Transactional(propagation = Propagation.NEVER)
	public String productSave(HttpServletRequest request, Model model, //
			@ModelAttribute("productForm") @Validated ProductInfo productInfo, //
			BindingResult result, //
			final RedirectAttributes redirectAttributes) {

		// if (result.hasErrors()) {
		// return "product";
		// }
		try {
			productDAO.save(request, productInfo);
		} catch (Exception e) {
			// Cần thiết: Propagation.NEVER?
			String message = e.getMessage();
			model.addAttribute("message", message);
			// Show product form.
			return "product";

		}
		return "redirect:/productList";
	}
	/*-------------------------Sản Phẩm-------------------------------*/

	/*----------------------Đơn hàng------------------------------------*/
	// GET: Lấy danh sách đơn hàng (khách hàng đã đặt)
	@RequestMapping(value = { "/orderList" }, method = RequestMethod.GET)
	public String orderList(Model model, //
			@RequestParam(value = "page", defaultValue = "1") String pageStr) {
		int page = 1;
		try {
			page = Integer.parseInt(pageStr);
		} catch (Exception e) {
		}
		final int MAX_RESULT = 5;
		final int MAX_NAVIGATION_PAGE = 10;

		PaginationResult<OrderInfo> paginationResult //
				= orderDAO.listOrderInfo(page, MAX_RESULT, MAX_NAVIGATION_PAGE);

		model.addAttribute("paginationResult", paginationResult);
		return "orderList";
	}

	// GET: Hiển thị thông tin đơn hàng
	@RequestMapping(value = { "/order" }, method = RequestMethod.GET)
	public String orderViewHandler(Model model, @RequestParam("orderId") String orderId) {
		OrderInfo orderInfo = null;
		if (orderId != null) {
			orderInfo = this.orderDAO.getOrderInfo(orderId);
		}
		if (orderInfo == null)
			return "redirect/orderList";

		List<OrderDetailInfo> details = this.orderDAO.listOrderDetailInfo(orderId);
		orderInfo.setDetails(details);

		model.addAttribute("orderInfo", orderInfo);
		return "order";

	}
	/*----------------------Đơn hàng------------------------------------*/
}
