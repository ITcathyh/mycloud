package hyh.util;

import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.IOException;

public class TranslationUtil {
    public static String zhTransToEn(String line) {
        return trans("zh-CN", "en", line);
    }

    public static String trans(String from, String to, String line) {
        Map<String, String> params = new HashMap<>();

        params.put("client", "t");
        params.put("sl", from);
        params.put("tl", to);
        params.put("hl", from);
        params.put("dt", "t");
        params.put("ie", "UTF-8");
        params.put("oe", "UTF-8");
        params.put("source", "btn");
        params.put("ssel", "0");
        params.put("tsel", "0");
        params.put("kc", "0");
        params.put("tk", token(line));
        params.put("q", line);

        HttpClient client = new HttpClient();
        GetMethod get = makeGetMethod("http://translate.google.cn/translate_a/single", params);

        try {
            client.executeMethod(get);

            String result = new String(get.getResponseBodyAsString().getBytes(StandardCharsets.UTF_8));
            JSONArray jsonArray = JSONArray.parseArray(result).getJSONArray(0).getJSONArray(0);
            return jsonArray.getString(0);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            get.releaseConnection();
        }

        return null;
    }

    public static String[] getWords(String line) {
        HttpClient client = new HttpClient();
        Map<String, String> params = new HashMap<>();

        params.put("source", line);
        params.put("param1", "0");
        params.put("param2", "0");
        GetMethod get = makeGetMethod("http://114.67.84.223/get.php", params);

        try {
            client.executeMethod(get);

            String result = new String(get.getResponseBodyAsString().getBytes(StandardCharsets.UTF_8));
            return result.split("[\\r][\\n]");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            get.releaseConnection();
        }

        return null;
    }

    private static GetMethod makeGetMethod(String url, Map<String, String> params) {
        GetMethod get = new GetMethod(url);
        NameValuePair[] data = new NameValuePair[params.size()];
        int i = 0;

        for (Map.Entry<String, String> entry : params.entrySet()) {
            data[i++] = new NameValuePair(entry.getKey(), entry.getValue());
        }

        get.setQueryString(data);
        get.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        return get;
    }

    private static String token(String value) {
        String result = "";
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("js");

        try {
            FileReader reader = new FileReader("Google.js");
            engine.eval(reader);

            if (engine instanceof Invocable) {
                Invocable invoke = (Invocable) engine;
                result = String.valueOf(invoke.invokeFunction("token", value));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
