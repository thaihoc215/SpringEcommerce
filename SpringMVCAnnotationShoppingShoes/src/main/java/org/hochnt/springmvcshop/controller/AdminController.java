package org.hochnt.springmvcshop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hochnt.springmvcshop.dao.AccountDAO;
import org.hochnt.springmvcshop.dao.OrderDAO;
import org.hochnt.springmvcshop.dao.ProductDAO;
import org.hochnt.springmvcshop.entity.Account;
import org.hochnt.springmvcshop.model.AccountInfo;
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

	@Autowired
	private AccountDAO accountDAO;

	/*-----------------Tài khoản---------------------*/
	// GET: Hiển thị trang login
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String login(Model model) {

		return "login";
	}

	// GET: Hiển thị trang đăng kí
	@RequestMapping(value = { "/signup" }, method = RequestMethod.GET)
	public String signupHandler(Model model) {

		AccountInfo accountInfo = new AccountInfo();

		model.addAttribute("accountInfo", accountInfo);
		return "signup";
	}

	// POST: Đăng kí tài khoản
	@RequestMapping(value = { "/signup" }, method = RequestMethod.POST)
	public String signupSaveHandler(Model model, @ModelAttribute("accountInfo") @Validated AccountInfo accountForm, //
			BindingResult result, //
			final RedirectAttributes redirectAttributes) {

		// // Nếu validate có lỗi.
		// if (result.hasErrors()) {
		// return "signup";
		// }

		Account accountRegister = null;
		accountRegister = accountDAO.findAccount(accountForm.getUserName());
		if (accountRegister == null)
			try {
				accountRegister = accountDAO.registerNewUserAccount(accountForm);
			} catch (Exception ex) {
				ex.printStackTrace();
				model.addAttribute("errorMessage", "Error " + ex.getMessage());
				return "signup";
			}
		return "redirect:/accountInfo";
	}

	// GET: hien thi trang tao tài khoản
	@RequestMapping(value = { "/createAccount" }, method = RequestMethod.GET)
	public String createAccount(Model model) {
		AccountInfo accountInfo = new AccountInfo();
		model.addAttribute("accountInfo", accountInfo);

		return "createAccount";
	}

	// POST: tạo tài khoản
	@RequestMapping(value = { "/createAccount" }, method = RequestMethod.POST)
	public String createAccountSaveHandler(Model model,
			@ModelAttribute("accountInfo") @Validated AccountInfo accountForm, //
			BindingResult result, //
			final RedirectAttributes redirectAttributes) {

		// // Nếu validate có lỗi.
		// if (result.hasErrors()) {
		// return "signup";
		// }

		Account accountRegister = null;
		accountRegister = accountDAO.findAccount(accountForm.getUserName());
		if (accountRegister == null)
			try {
				accountRegister = accountDAO.registerNewUserAccount(accountForm);
			} catch (Exception ex) {
				ex.printStackTrace();
				model.addAttribute("errorMessage", "Error " + ex.getMessage());
				return "createAccount";
			}
		return "redirect:/";
	}

	// GET: lay danh sach tai khoan
	@RequestMapping(value = { "/manageAccount" }, method = RequestMethod.GET)
	public String manageAccountHandler(Model model, //
			@RequestParam(value = "page", defaultValue = "1") String pageStr) {
		int page = 1;
		try {
			page = Integer.parseInt(pageStr);
		} catch (Exception e) {
		}
		final int MAX_RESULT = 5;
		final int MAX_NAVIGATION_PAGE = 10;

		PaginationResult<AccountInfo> paginationResult //
				= accountDAO.listAccountInfo(page, MAX_RESULT, MAX_NAVIGATION_PAGE);

		model.addAttribute("paginationResult", paginationResult);
		return "manageAccount";
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

	@RequestMapping(value = { "/manageAccountInfo" }, method = RequestMethod.GET)
	public String mangeAccountInfo(Model model, @RequestParam("userName") String userName) {

		// lấy thông tin người dùng
		AccountInfo account = accountDAO.findAccountInfo(userName);
		if (account == null) {// truong hop code sai
			return "redirect:/manageAccount";
		}
		model.addAttribute("accountInfo", account);
		return "manageAccountInfo";
	}

	@RequestMapping(value = { "/manageAccountInfo" }, method = RequestMethod.POST)
	@Transactional(propagation = Propagation.NEVER)
	public String mangeAccountInfoUpdate(Model model, @ModelAttribute("accountInfo") @Validated AccountInfo accountInfo, //
			BindingResult result, //
			final RedirectAttributes redirectAttributes) {

		try {
			accountDAO.saveAccountInfo(accountInfo);
		} catch (Exception e) {
			String message = e.getMessage();
			model.addAttribute("message", message);
			// Show product form.
			return "manageAccountInfo";
		}
		return "redirect:/manageAccount";
	}

	// GET: thay doi trang thai account
	@RequestMapping(value = { "/updateAccStatus" }, method = RequestMethod.GET)
	public String accountStatusChangeHandler(Model model, @RequestParam("userName") String userName) {
		Account account = null;
		if (userName != null) {
			account = this.accountDAO.findAccount(userName);
		}
		if (account == null)
			return "redirect:/manageAccount";

		this.accountDAO.updateAccountStatus(account);
		return "redirect:/manageAccount";

	}
	/*-----------------Tài khoản---------------------*/

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
	// Tránh ngoại lệ: UnexpectedRollbackException
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
			return "redirect:/orderList";

		List<OrderDetailInfo> details = this.orderDAO.listOrderDetailInfo(orderId);
		orderInfo.setDetails(details);

		model.addAttribute("orderInfo", orderInfo);
		return "order";

	}
	/*----------------------Đơn hàng------------------------------------*/
}
