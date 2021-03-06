package com.newroad.util.apiresult;

public enum ReturnCode {
  /** 返回码,成功:200 */
  OK(200, "OK"),

  /** 返回码,部分成功:201 */
  PARTIAL_OK(201, "PARTIAL OK"),

  /** 返回码,用户总容量超出:202 */
  SPACE_OVERFLOW(202, "SPACE OVERFLOW"),

  /** 返回码,单个附件容量超出:203 */
  SINGLE_OVERFLOW(203, "SINGLE OVERFLOW"),

  /** 返回码,未返回数据:204 */
  NO_DATA_RETURN(204, "NO DATA RETURN"),
  
  /** 返回码,未返回数据:205 */
  DATA_EXIST(205, "DATA EXIST"),

  /** 返回码,提交数据格式错误:400 */
  BAD_REQUEST(400, "BAD REQUEST"),

  /** 认证失败:401 */
  UNAUTHORIZED(401, "Unauthorized"),

  /** 返回码,无token，或过期:402 */
  NO_TOKEN(402, "TOKEN Fail"),

  /** 返回码,非法的token:403 */
  ILLEGAL_TOKEN(403, "Forbidden"),

  /** 返回码,检索不到指定的数据:404 */
  DATA_NOT_FOUND(404, "DATA NOT FOUND"),

  /** 返回码,此方法不允或无权限调用:405 */
  NO_PERMISSION(405, "NO PERMISSION"),

  /** 返回码,非法的参数:409 */
  ILLEGAL_DATA(409, "ILLEGAL DATA"),

  /** 返回码,版本不匹配:412 */
  VERSION_MISMATCHING(412, "VERSION MISMATCHING"),

  /** 返回码, http头 无consumer key :420 */
  NO_CONSUMER_KEY(420, "NO CONSUMER KEY"),

  /** 返回码, http头 无consumer secret :421 */
  NO_CONSUMER_SECRET(421, "NO CONSUMER SECRET"),

  /** 返回码, 应用不可用 (未使用):422 */
  CONSUMER_KEY_UNAVAILABLE(422, "CONSUMER KEY UNAVAILABLE"),

  /** 返回码, 应用不可用 (待审核):423 */
  CONSUMER_KEY_VERIFY(423, "CONSUMER KEY VERIFY"),

  /** 返回码, 应用不可用 (已停用):424 */
  CONSUMER_KEY_DISABLE(424, "CONSUMER KEY DISABLE"),

  /** 返回码, 应用不可用 (当前IP已停用):425 */
  CONSUMER_KEY_DISABLE_IP(425, "CONSUMER KEY DISABLE IP"),

  /** 返回码, 无权限访问当前API:426 */
  NO_API_ACCESS(426, "NO API ACCESS"),

  /** 返回码, 不安全的访问请求:427 */
  UN_SECURE_REQUEST(427, "UN SECURE REQUEST"),

  /** 返回码, 无法获得有效随机数:428 */
  NO_AVAILABLE_RANDOM_CODE(428, "NO AVAILABLE RANDOM CODE"),
  
  SMS_SEND_ERROR(429, "SMS SEND ERROR"),
  
  SERVICE_UNREACHABLE(430,"SERVICE UNREACHABLE"),

  /** 返回码,服务器错误:500 */
  SERVER_ERROR(500, "SERVER ERROR"),

  /** 返回码,服务器正忙:503 */
  SERVER_UNAVAILABLE(503, "SERVER UNAVAILABLE"),

  /** 返回码,认证服务不可用:504 */
  AUTH_UNAVAILABLE(504, "AUTH UNAVAILABLE");

  private int value;
  private String returnMessage;

  private ReturnCode(int value, String returnMessage) {
    this.value = value;
    this.returnMessage = returnMessage;
  }

  public static ReturnCode fromCode(int code) {
    for (ReturnCode _code : values()) {
      if (_code.getValue() == code) {
        return _code;
      }
    }
    return null;
  }

  public int getValue() {
    return this.value;
  }

  public String getReturnMessage() {
    return returnMessage;
  }

  public static String getReturnMessage(int value) {
    for (ReturnCode returnCode : ReturnCode.values()) {
      if (value == returnCode.getValue()) {
        return returnCode.getReturnMessage();
      }
    }
    return ReturnCode.getReturnMessage(500);
  }

  public String toString() {
    return String.valueOf(this.value);
  }

}
