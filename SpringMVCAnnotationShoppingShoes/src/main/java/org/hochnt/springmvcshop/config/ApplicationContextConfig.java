package org.hochnt.springmvcshop.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hochnt.springmvcshop.dao.AccountDAO;
import org.hochnt.springmvcshop.dao.CategoryDAO;
import org.hochnt.springmvcshop.dao.OrderDAO;
import org.hochnt.springmvcshop.dao.ProductDAO;
import org.hochnt.springmvcshop.dao.impl.AccountDAOImpl;
import org.hochnt.springmvcshop.dao.impl.CategoryDAOImpl;
import org.hochnt.springmvcshop.dao.impl.OrderDAOImpl;
import org.hochnt.springmvcshop.dao.impl.ProductDAOImpl;
import org.hochnt.springmvcshop.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan("org.hochnt.springmvcshop.*")
@EnableTransactionManagement
@PropertySource("classpath:datasource-cfg.properties")
public class ApplicationContextConfig {

	// lưu trữ các thuộc tính load bởi @PropertySource
	@Autowired
	private Environment env;

	// Nội dung của validator.properties đã được load bởi ApplicationContextConfig
	// đưa vào sử dụng trong các resourcebundle được định nghĩa autowird ở các class
	@Bean
	public ResourceBundleMessageSource messageSource() {
		// trả ra các message lỗi được spring cấu hình
		ResourceBundleMessageSource rb = new ResourceBundleMessageSource();
		// Load property in message/validator.properties
		rb.setBasenames(new String[] { "messages/validator" });
		return rb;
	}

	@Bean(name = "viewResolver")
	public InternalResourceViewResolver getViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		// Cấu hình Viewresolver sử dụng công nghệ cho tầng view
		viewResolver.setPrefix("/WEB-INF/pages/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	// Định nghĩa bean cho xử lý upload file
	@Bean(name = "multipartResolver")
	public MultipartResolver getMultipartResolver() {
		// Tạo io & dj CommonsMultipartFile duyệt file upload
		CommonsMultipartResolver resover = new CommonsMultipartResolver();
		// 1MB
		resover.setMaxUploadSize(1 * 1024 * 1024);

		return resover;
	}

	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		/*
		 * Bean sử dụng để lấy thông tin connect database từ file cấu hình trong
		 * enviroment sử dụng ở DAO cũng như truyền vào datasource khi khởi tạo session
		 */
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		// Xem: ds-hibernate-cfg.properties
		dataSource.setDriverClassName(env.getProperty("ds.database-driver"));
		dataSource.setUrl(env.getProperty("ds.url"));
		dataSource.setUsername(env.getProperty("ds.username"));
		dataSource.setPassword(env.getProperty("ds.password"));

		System.out.println("## getDataSource: " + dataSource);

		return dataSource;
	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) throws Exception {

		Properties properties = new Properties();

		// Xem: ds-hibernate-cfg.properties
		properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		properties.put("current_session_context_class", env.getProperty("current_session_context_class"));

		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

		// Package chứa các entity class.
		factoryBean.setPackagesToScan(new String[] { "org.hochnt.springmvcshop.entity" });
		factoryBean.setDataSource(dataSource);
		factoryBean.setHibernateProperties(properties);
		factoryBean.afterPropertiesSet();
		//
		SessionFactory sf = factoryBean.getObject();
		System.out.println("## getSessionFactory: " + sf);
		return sf;
	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		// qua autowired nhận sessionFac từ trên
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

		return transactionManager;
	}

	// tạo Bean cho các lớp xử lý DAO tương ứng
	@Bean(name = "accountDAO")
	public AccountDAO getApplicantDAO() {
		return new AccountDAOImpl();
	}

	@Bean(name = "productDAO")
	public ProductDAO getProductDAO() {
		return new ProductDAOImpl();
	}

	@Bean(name = "orderDAO")
	//khoi tao 1 bean dao de truyen vao cac autowired trong controller
	public OrderDAO getOrderDAO() {
		return new OrderDAOImpl();
	}

	@Bean(name = "accountDAO")
	public AccountDAO getAccountDAO() {
		return new AccountDAOImpl();
	}

	@Bean(name = "categoryDAO")
	public CategoryDAO getCategoryDAO() {
		return new CategoryDAOImpl();
	}
}
