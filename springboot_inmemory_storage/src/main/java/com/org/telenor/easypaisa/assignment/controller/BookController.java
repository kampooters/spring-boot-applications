package com.org.telenor.easypaisa.assignment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.org.telenor.easypaisa.assignment.dao.BookDao;
import com.org.telenor.easypaisa.assignment.locale.i18Messages;
import com.org.telenor.easypaisa.assignment.model.Book;
import com.org.telenor.easypaisa.assignment.response.ResponseModel;
import com.org.telenor.easypaisa.assignment.util.CONSTANT;

/**
 * @author abdul | 13-Feb-2019 | 10:42:50 AM
 * @version 1.0
 * 
 *          BookController holds the Rest APIs implementation for User. The all
 *          CUD operations are supported through REST.
 * 
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/book")
public class BookController {
	private static Logger logger = LoggerFactory.getLogger(BookController.class);

	@Autowired
	private BookDao bookDao;

	@GetMapping("/test")
	public Object getTestMessage() {
		logger.info(i18Messages.REQUEST_RECIEVED + " @ api/book/test");
		ResponseModel responseModel = new ResponseModel(1, "", -1,
				String.valueOf(javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR), CONSTANT.REST_RESPONSE,
				"API is running successfully");
		;

		try {
			responseModel = new ResponseModel(1, "", -1,
					String.valueOf(javax.servlet.http.HttpServletResponse.SC_OK),
					CONSTANT.REST_RESPONSE, "API is running successfully");
		} catch (Exception e) {
			responseModel = new ResponseModel(1, "", -1,
					String.valueOf(javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR),
					CONSTANT.REST_RESPONSE, e.getMessage());
		}
		return responseModel;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/get/{id:[\\d]+}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseModel getBook(@PathVariable("id") long id) {
		logger.info(i18Messages.REQUEST_RECIEVED + " @ api/book/get");
		ResponseModel responseModel = new ResponseModel(1, "", -1,
				String.valueOf(javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR), CONSTANT.REST_RESPONSE,
				null);
		;

		try {
			responseModel = new ResponseModel(1, "", -1,
					String.valueOf(javax.servlet.http.HttpServletResponse.SC_OK),
					CONSTANT.REST_RESPONSE, bookDao.findById(id));
		} catch (Exception e) {
			responseModel = new ResponseModel(1, "", -1,
					String.valueOf(javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR),
					CONSTANT.REST_RESPONSE, e.getMessage());
		}
		return responseModel;
	}

	@RequestMapping(value = "/get/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseModel getBooks() {
		logger.info(i18Messages.REQUEST_RECIEVED + " @ api/book/all");
		ResponseModel responseModel = new ResponseModel(1, "", -1,
				String.valueOf(javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR), CONSTANT.REST_RESPONSE,
				null);

		try {
			responseModel = new ResponseModel(1, "", -1,
					String.valueOf(javax.servlet.http.HttpServletResponse.SC_OK),
					CONSTANT.REST_RESPONSE, bookDao.findAll());
		} catch (Exception e) {
			responseModel = new ResponseModel(1, "", -1,
					String.valueOf(javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR),
					CONSTANT.REST_RESPONSE, e.getMessage());
		}
		return responseModel;

	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseModel saveBook(@RequestBody Book u) {

		logger.info(i18Messages.REQUEST_RECIEVED + " @ api/book/save");
		ResponseModel responseModel = new ResponseModel(1, "", -1,
				String.valueOf(javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR), CONSTANT.REST_RESPONSE,
				null);

		try {
			Book newBook = new Book();
			newBook = bookDao.save(u);
			responseModel = new ResponseModel(1, "", -1,
					String.valueOf(javax.servlet.http.HttpServletResponse.SC_OK),
					CONSTANT.REST_RESPONSE, newBook);
		} catch (Exception e) {
			responseModel = new ResponseModel(1, "", -1,
					String.valueOf(javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR),
					CONSTANT.REST_RESPONSE, e.getMessage());
		}
		return responseModel;

	}

	@RequestMapping(value = "/delete/{id:[\\d]+}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseModel deleteBook(@PathVariable("id") long id) {

		logger.info(i18Messages.REQUEST_RECIEVED + " @ api/book/delete");
		ResponseModel responseModel = new ResponseModel(1, "", -1,
				String.valueOf(javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR), CONSTANT.REST_RESPONSE,
				null);

		try {
			bookDao.deleteById(id);
			responseModel = new ResponseModel(1, "", -1,
					String.valueOf(javax.servlet.http.HttpServletResponse.SC_OK),
					CONSTANT.REST_RESPONSE, "");
		} catch (Exception e) {
			responseModel = new ResponseModel(1, "", -1,
					String.valueOf(javax.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR),
					CONSTANT.REST_RESPONSE, e.getMessage());
		}
		return responseModel;

	}

}