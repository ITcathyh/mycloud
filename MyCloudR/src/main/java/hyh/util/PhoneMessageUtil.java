package hyh.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 短信发送工具
 *
 * @author ITcathyh
 * @version 1.0
 */
@Component
@ConfigurationProperties(prefix="phoneutil")
public final class PhoneMessageUtil {
    private String uidstr;
    private String keystr;
    private short codeLen;
    private String content;

    /**
     * 发送短信
     * 使用socket进行连接平台
     *
     * @param phoneNum 手机号
     */
    public JSONObject sendMsg(String phoneNum) throws IOException {
        HttpClient client = new HttpClient();
        JSONObject json = new JSONObject();
        PostMethod post = new PostMethod("http://gbk.api.smschinese.cn");
        String code = GetRandomString.getRandomString(codeLen);
        NameValuePair[] data = {new NameValuePair("Uid", uidstr),
                new NameValuePair("Key", keystr),
                new NameValuePair("smsMob", phoneNum),
                new NameValuePair("smsText", String.format(content, code))};

        post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=gbk");
        post.setRequestBody(data);
        client.executeMethod(post);
        int result = Integer.parseInt(new String(post.getResponseBodyAsString().getBytes("gbk")));
        post.releaseConnection();

        if (result > 0) {
            json.put("done", true);
            json.put("result", code);
        } else {
            /*
             * 错误代码查看：
             * http://www.webchinese.com.cn/api.shtml
             */
            json.put("done", false);
            json.put("result", result);
        }

        return json;
    }

    public void setUidstr(String uidstr) {
        this.uidstr = uidstr;
    }

    public void setKeystr(String keystr) {
        this.keystr = keystr;
    }

    public void setCodeLen(short codeLen) {
        this.codeLen = codeLen;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
