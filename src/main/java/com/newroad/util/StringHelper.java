package com.newroad.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.newroad.util.encoding.StringEncodeUtil;
import com.newroad.util.exception.CommonError;

public class StringHelper {

  private StringHelper() {}

  private static Logger logger = LoggerFactory.getLogger(StringHelper.class);

  private static final char random[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
      'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
      'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

  public static String generateKeyId() {
    UUID randomUUID = UUID.randomUUID();
    String key = randomUUID.toString().replaceAll("-", "");
    return key;
  }
 
  public static String convertReader2String(BufferedReader br){
    StringBuilder sb = new StringBuilder();
    try {
      String line = null;
      while ((line = br.readLine()) != null) {
        sb.append(line);
      }
    } catch (Exception e) {
      sb.append("{\"" + CommonError.ERROR_KEY + "\":\"" + e.getMessage() + "\"}");
      logger.error("JSONOject getRequestEntityString", e);
    } finally {
      try {
        br.close();
      } catch (Exception e) {
        logger.error("convertReader2String",e);
      }
    }
    return sb.toString();
  }

  /**
   * 从request的entity数据
   * 
   * @param request
   * @return
   */
  public static JSONObject getRequestEntity(HttpServletRequest request) {
    JSONObject result = null;
    try {
      result = JSONObject.fromObject(getRequestEntityString(request));
    } catch (Exception e) {
      logger.error("JSONOject getRequestEntity", e);
      result = JSONObject.fromObject("{" + CommonError.ERROR_KEY + ":'wrong json format'}");
    }
    return result;
  }

  /**
   * 返回request请求参数 String串
   */
  public static String getRequestEntityString(HttpServletRequest request) {
    BufferedReader br = null;
    StringBuilder sb = new StringBuilder();
    try {
      br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream(), "UTF-8"));
      String line = null;
      while ((line = br.readLine()) != null) {
        sb.append(line);
      }
      logger.debug("request info:" + sb);
    } catch (Exception e) {
      sb.append("{\"" + CommonError.ERROR_KEY + "\":\"" + e.getMessage() + "\"}");
      logger.error("JSONOject getRequestEntityString", e);
    } finally {
      try {
        br.close();
      } catch (Exception e) {
        logger.error("request info close error:", e);
      }
    }

    // 验证json格式正确性
    if (StringUtils.isBlank(sb.toString())) {
      sb.append("{'" + CommonError.ERROR_KEY + "':'none data'}");
    }
    // 中文转码
    String str = StringEncodeUtil.parseISO2UTF(sb.toString());
    return str;

  }

  /**
   * 
   * @info : 生成随机字符串
   * @autor : tangzj
   * @param length 字符串长度 (0 100]
   */
  static public String randomString(int length) {
    int len = length;
    if (length == 0)
      len = 1;
    if (length > 100)
      len = 100;

    StringBuffer sb = new StringBuffer(len);
    Random ran = new Random();
    for (int i = 0; i < length; i++) {
      sb.append(random[ran.nextInt(random.length)]);
    }
    return sb.toString();
  }

  /**
   * 将{@code List<String>}转成JSONArray对象,再返回此对象的toString方法 注意:返回的字符串的首尾分别是:[、]
   * 
   * @param list
   * @return
   */
  public static String list2String(List<String> list) {
    return JSONArray.fromObject(list).toString();
  }

  public static String replaceAllHtmlTag(String str) {
    if (str == null)
      return null;
    return str.replaceAll("</?[^>]+>", "");
  }

  /**
   * 判断指定的JSONObject对象中是否包含指定键的字符串值
   * 
   * @param js
   * @param key
   * @return
   */
  public static boolean isJsonHasStringValue(JSONObject js, String key) {
    if (js.containsKey(key) && !JSONNull.getInstance().equals(js.get(key)) && js.get(key) != null
        && !StringUtils.isBlank(js.getString(key))) {
      return true;
    }
    return false;
  }

  /**
   * 校验指定字符串中是否包含有指定列表中的关键字
   * 
   * @param target 待校验的字符串
   * @param keywords 关键字列表
   * @return
   */
  public static boolean isContained(String target, List<String> keywords) {
    if (StringUtils.isBlank(target)) {
      return false;
    }
    for (String keyword : keywords) {
      if (!StringUtils.isBlank(keyword) && target.contains(keyword)) {
        return true;
      }
    }
    return false;
  }

}
