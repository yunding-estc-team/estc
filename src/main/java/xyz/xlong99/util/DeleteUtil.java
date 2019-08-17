package xyz.xlong99.util;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 用来删除照片的工具类
 * @author xlong
 * @date 2019-08-08 10:58
 */
public class DeleteUtil {
    private static String url = "https://sm.ms/api/v2/delete/";
        public static JSONObject deletePhoto(String hash)  {
            String newurl = url+hash;
            Map<String, String> messageParam = new HashMap<>(1);
            messageParam.put("format","json");
            JSONObject jsonObject = null;
            try {
                jsonObject = HttpsUtil.httpRequest(newurl,"GET", null);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return jsonObject;
        }

    public static void main(String[] args) {
        System.out.println(deletePhoto("tASfz3PuIXpljceWFx7RC5iMOo"));
    }
}
