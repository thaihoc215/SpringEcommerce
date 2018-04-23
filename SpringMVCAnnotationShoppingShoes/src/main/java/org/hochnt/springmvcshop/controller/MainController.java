package org.hochnt.springmvcshop.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hochnt.springmvcshop.dao.OrderDAO;
import org.hochnt.springmvcshop.dao.ProductDAO;
import org.hochnt.springmvcshop.entity.Product;
import org.hochnt.springmvcshop.model.CartInfo;
import org.hochnt.springmvcshop.model.CustomerInfo;
import org.hochnt.springmvcshop.model.PaginationResult;
import org.hochnt.springmvcshop.model.ProductInfo;
import org.hochnt.springmvcshop.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class MainController {
	@Autowired
	// autowired from appcontext bean get from DAO
	private OrderDAO orderDAO;

	@Autowired
	// autowired from DAO
	private ProductDAO productDAO;

	@RequestMapping("/403")
	public String accessDenied() {
		return "/403";
	}

	@RequestMapping("/")
	public String home() {
		// di chuyển đến trang jsp tương ứng
		return "index";
	}

	/*------------------------Sản phẩm-------------------------*/
	// Danh sách sản phẩm.
	@RequestMapping("/productList")
	public String listProductHandler(Model model, //
			@RequestParam(value = "name", defaultValue = "") String likeName,
			@RequestParam(value = "page", defaultValue = "1") int page) {

		final int maxResult = 5;// hien thi toi da 5 san pham 1 tran
		final int maxNavigationPage = 10;// hien thi toi da 10 trang mot lan

		// lay ket qua phan trang va dua vao paginationProduct tren view
		PaginationResult<ProductInfo> rs = productDAO.queryProducts(page, maxResult, maxNavigationPage, likeName);
		model.addAttribute("paginationProducts", rs);
		return "productList";
	}

	// mua sản phẩm
	@RequestMapping({ "/buyProduct" })
	public String listProductHandler(HttpServletRequest request, Model model,
			@RequestParam(value = "code", defaultValue = "") String code) {
		Product product = null;
		if (code != null && code.length() > 0) {
			product = productDAO.findProduct(code);
		}

		if (product != null) {
			// Thông tin giỏ hàng có thể đã lưu vào trong Session trước đó.
			CartInfo cartInfo = Utils.getCartInSession(request);

			ProductInfo productInfo = new ProductInfo(product);
			// nếu đã có sản phẩm trong giỏ hàng, thì add thêm 1
			cartInfo.addProduct(productInfo, 1);
		}
		// Chuyển sang trang danh sách các sản phẩm đã mua.
		return "redirect:/shoppingCart";
	}

	// thong tin san pham
	@RequestMapping(value = { "/productInfo" }, method = RequestMethod.GET)
	/**
	 * Xem thong tin san pham
	 * @param model
	 * @param code
	 * @return
	 */
	public String productInfoHandler(Model model, @RequestParam(value = "code", defaultValue = "") String code) {
		ProductInfo productInfo = null;
		
		if (code != null && code.length() > 0) {
			productInfo = productDAO.findProductInfo(code);
		}
		if(productInfo == null)
			return "redirect:/productList";
		model.addAttribute("productForm",productInfo);
		return "productInfo";
	}
	
	@RequestMapping(value = { "/productInfo" }, method = RequestMethod.POST)
	/**
	 * Mua san pham
	 * @param model
	 * @param code
	 * @return
	 */
	public String productBuyHandler(Model model, @RequestParam(value = "code", defaultValue = "") String code) {
		return "redirect:/buyProduct?code=" + code;
	}
	/*------------------------Sản phẩm-------------------------*/

	/*---------------------------Giỏ hàng------------------------*/
	// GET: Hiển thị giỏ hàng.
	@RequestMapping(value = { "/shoppingCart" }, method = RequestMethod.GET)
	public String shoppingCartHandler(HttpServletRequest request, Model model) {
		// Lay danh sach san pham trong session hien tai
		CartInfo myCart = Utils.getCartInSession(request);

		model.addAttribute("cartForm", myCart);
		return "shoppingCart";
	}

	// POST: Cập nhật số lượng cho các sản phẩm trong giỏ hàng
	@RequestMapping(value = { "/shoppingCart" }, method = RequestMethod.POST)
	public String shoppingCartUpdateQuantityHandler(HttpServletRequest request, Model model,
			@ModelAttribute("cartForm") CartInfo cartForm) {
		// Lay danh sach san pham trong session hien tai
		CartInfo myCart = Utils.getCartInSession(request);

		myCart.updateQuantity(cartForm);
		return "redirect:/shoppingCart";
	}

	// xóa sản phẩm ra khỏi giỏ hàng
	@RequestMapping({ "/shoppingCartRemoveProduct" })
	public String removeProductHandler(HttpServletRequest request, Model model,
			@RequestParam(value = "code", defaultValue = "") String code) {
		Product product = null;
		if (code != null && code.length() > 0) {
			product = productDAO.findProduct(code);
		}
		if (product != null) {
			// check trong session
			CartInfo cartInfo = Utils.getCartInSession(request);
			ProductInfo info = new ProductInfo(product);
			cartInfo.removeProduct(info);
		}
		return "redirect:/shoppingCart";

	}
	/*---------------------------Giỏ hàng------------------------*/

	/*------------------------Thông tin khách hàng--------------------*/
	// GET:Nhap thong tin khach hang
	@RequestMapping(value = { "/shoppingCartCustomer" }, method = RequestMethod.GET)
	public String shoppingCartCustomerForm(HttpServletRequest request, Model model) {
		CartInfo cartInfo = Utils.getCartInSession(request);

		// Kiem tra gio hang
		if (cartInfo.isEmpty()) {
			// quay ve gio hang
			return "redirect:/shoppingCart";
		}
		CustomerInfo customerInfo = cartInfo.getCustomerInfo();
		if (customerInfo == null) {
			customerInfo = new CustomerInfo();
		}
		model.addAttribute("customerForm", customerInfo);
		return "shoppingCartCustomer";
	}

	// POST:luu thong tin khach hang
	@RequestMapping(value = { "/shoppingCartCustomer" }, method = RequestMethod.POST)
	public String shoppingCartCustomerSave(HttpServletRequest request, Model model,
			@ModelAttribute("customerForm") @Validated CustomerInfo customerForm, //
			BindingResult result, //
			final RedirectAttributes redirectAttributes) {
		// Kết quả Validate CustomerInfo.
		// if (result.hasErrors()) {
		// customerForm.setValid(false);
		// // Forward to reenter customer info.
		// // Forward tới trang nhập lại.
		// return "shoppingCartCustomer";
		// }

		customerForm.setValid(true);
		CartInfo cartInfo = Utils.getCartInSession(request);
		// luu thong tin cua nguoi dung vao session
		// ghi đè thông tin
		cartInfo.setCustomerInfo(customerForm);

		// chuyen huong den trang xac nhan
		return "redirect:/shoppingCartConfirmation";
	}
	/*------------------------Thông tin khách hàng--------------------*/

	/*---------Xác nhận và thanh toán đơn hàng*/
	// GET: Check thông tin xác nhận
	@RequestMapping(value = { "/shoppingCartConfirmation" }, method = RequestMethod.GET)
	public String shoppingCartConfirmationCheck(HttpServletRequest request, Model model) {
		// Lay thong tin gio hang trong session
		CartInfo cartInfo = Utils.getCartInSession(request);

		// Chưa mua mặt hàng nào.
		if (cartInfo.isEmpty()) {

			// Chuyển tới trang danh giỏ hàng
			return "redirect:/shoppingCart";
		} else if (!cartInfo.isValidCustomer()) { // kiem tra thong tin khach hang

			// Chuyển tới trang nhập thông tin khách hàng.
			return "redirect:/shoppingCartCustomer";
		}
		return "shoppingCartConfirmation";
	}

	// POST: Gửi đơn hàng đã xác nhận và lưu đơn hàng
	@RequestMapping(value = { "/shoppingCartConfirmation" }, method = RequestMethod.POST)
	// Tránh ngoại lệ: UnexpectedRollbackException
	@Transactional(propagation = Propagation.NEVER)
	public String shoppingCartConfirmationSave(HttpServletRequest request, Model model) {

		CartInfo cartInfo = Utils.getCartInSession(request);

		// kiem tra lai gio hang
		if (cartInfo.isEmpty())
			return "redirect:/shoppingCart";
		else if (!cartInfo.isValidCustomer())
			return "redirect:/shoppingCartCustomer";
		// luu thong tin vao DAO
		try {
			orderDAO.saveOrder(cartInfo);
		} catch (Exception e) {

			// Propagation.NEVER?
			return "shoppingCartConfirmation";
		}
		// xoa gio hang ra khoi session
		Utils.removeCartInSession(request);
		// luu thong tin don hang da mua
		Utils.storeLastOrderedCartInSession(request, cartInfo);
		// di den man hinh hoan thanh viec mua hang
		return "shoppingCartFinalize";
	}
	/*---------Xác nhận và thanh toán đơn hàng*/

	@RequestMapping(value = { "/productImage" }, method = RequestMethod.GET)
	public void productImage(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam("code") String code) throws IOException {
		Product product = null;
		if (code != null) {
			product = this.productDAO.findProduct(code);
		}
		if (product != null && product.getImage() != null) {
			response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
			response.getOutputStream().write(product.getImage());
		}
		response.getOutputStream().close();
	}
}
