package com.org.telenor.easypaisa.assignment.response;

import com.org.telenor.easypaisa.assignment.util.CONSTANT;

/**
 * @author abdul
 * @version 1.0 ResponseModel holds the response message structure used
 *          throughout the whole application for REST APIs
 */
public class ResponseModel {
	int type = 1;
	String message = "";
	long revisionNo = -1;
	String errorCode = null;
	String _explicitType = CONSTANT.REST_RESPONSE;
	Object data = null;


	/**
	 * @param type
	 *            {@link Integer} default 1
	 * @param message
	 *            {@link String} default empty
	 * @param revisionNo
	 *            {@link Long} default -1
	 * @param errorCode
	 *            {@link String} default null
	 * @param _explicitType
	 *            {@link String} default CONSTANT.SCRYBE_RESPONSE
	 * @param data
	 *            {@link IResponseData} data implementation will be passed
	 */
	public ResponseModel(int type, String message, long revisionNo, String errorCode, String _explicitType,
			Object data) {
		super();
		this.type = type;
		this.message = message;
		this.revisionNo = revisionNo;
		this.errorCode = errorCode;
		this._explicitType = _explicitType;
		this.data = data;
	}
	

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the revisionNo
	 */
	public long getRevisionNo() {
		return revisionNo;
	}

	/**
	 * @param revisionNo
	 *            the revisionNo to set
	 */
	public void setRevisionNo(long revisionNo) {
		this.revisionNo = revisionNo;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode
	 *            the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the _explicitType
	 */
	public String get_explicitType() {
		return _explicitType;
	}

	/**
	 * @param _explicitType
	 *            the _explicitType to set
	 */
	public void set_explicitType(String _explicitType) {
		this._explicitType = _explicitType;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

}
