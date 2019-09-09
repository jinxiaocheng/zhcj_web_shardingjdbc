package com.escst.commons.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.escst.commons.vo.ReturnJson;

/**
 * @desc http请求工具类
 * @author caozx
 * @date 2017/2/22 13:38
 */
public class HttpClientUtils {
	
	private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

    private static PoolingHttpClientConnectionManager poolingHttpClientConnectionManager;
    private static String EMPTY_STR = "";
    private static String CHAR_SET_UTF_8 = "UTF-8";

    private static void init() {
        if (poolingHttpClientConnectionManager == null) {
            poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
            poolingHttpClientConnectionManager.setMaxTotal(10);   //整个连接池的最大并发连接数
            poolingHttpClientConnectionManager.setDefaultMaxPerRoute(5);// 单路由最大并发连接数，默认值是2
        }
    }

    /**
     * 通过连接池获取HttpClient
     *
     * @return
     */
    private static CloseableHttpClient getHttpClient() {
        init();
        return HttpClients.custom().setConnectionManager(poolingHttpClientConnectionManager).build();
    }

    /**
     * 处理http请求
     * @param request
     * @return
     */
    private static String getResult(HttpRequestBase request) {
        CloseableHttpClient httpClient = getHttpClient();
        CloseableHttpResponse response = null;
        String result = EMPTY_STR;
        try {
            response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity,CHAR_SET_UTF_8);
                response.close();
            }
        } catch (IOException e) {
            logger.error("http request error", e);
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {}
            }
        }
        return result;
    }

    /**
     * 将传入的参数转换成NameValuePair对象，并存到list中
     * @param params
     * @return
     */
    private static List<NameValuePair> convertParams2Nvps(Map<String, Object> params) {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        Set<Map.Entry<String, Object>> set = params.entrySet();
        for (Map.Entry<String, Object> param : set) {
            nvps.add(new BasicNameValuePair(param.getKey(), String.valueOf(param.getValue())));
        }
        return nvps;
    }

    /**
     * http get请求
     * @param url
     * @return
     */
    public static String httpGetRequest(String url) {
    	logger.debug("http request url : " + url);
        HttpGet httpGet = new HttpGet(url);
        return getResult(httpGet);
    }

    /**
     * http get请求
     * @param url
     * @param params 传入的参数
     * @return
     * @throws URISyntaxException
     */
    public static String httpGetRequest(String url, Map<String, Object> params) throws URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder();
        List<NameValuePair> nvps = convertParams2Nvps(params);
        uriBuilder.setPath(url);
        uriBuilder.setParameters(nvps);
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        return getResult(httpGet);
    }

    /**
     * http get请求
     * @param url
     * @param headers
     * @param params
     * @return
     * @throws URISyntaxException
     */
    public static String httpGetRequest(String url, Map<String, Object> headers, Map<String, Object> params)
            throws URISyntaxException {
        URIBuilder ub = new URIBuilder();
        ub.setPath(url);
        List<NameValuePair> nvps = convertParams2Nvps(params);
        ub.setParameters(nvps);
        HttpGet httpGet = new HttpGet(ub.build());
        for (Map.Entry<String, Object> param : headers.entrySet()) {
            httpGet.addHeader(param.getKey(), String.valueOf(param.getValue()));
        }
        return getResult(httpGet);
    }

    /**
     * http post请求
     * @param url
     * @return
     */
    public static String httpPostRequest(String url) {
        HttpPost httpPost = new HttpPost(url);
        return getResult(httpPost);
    }

    /**
     * http post请求
     * @param url
     * @param params
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String httpPostRequest(String url, Map<String, Object> params) {
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> nvps = convertParams2Nvps(params);
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps,CHAR_SET_UTF_8));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return getResult(httpPost);
    }

    /**
     * http post请求
     * @param url
     * @param headers
     * @param params
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String httpPostRequest(String url, Map<String, Object> headers, Map<String, Object> params)
            throws UnsupportedEncodingException {
        HttpPost httpPost = new HttpPost(url);
        for (Map.Entry<String, Object> param : headers.entrySet()) {
            httpPost.addHeader(param.getKey(), String.valueOf(param.getValue()));
        }
        List<NameValuePair> nvps = convertParams2Nvps(params);
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, CHAR_SET_UTF_8));
        return getResult(httpPost);
    }


	/**
     * 
     * @desc http json请求
     * @param url
     * @param ob
     * @return 
     * @author niejing
     * @date 2017年6月7日 上午9:37:59
     */
    public static String httpPostWithJSON(String url, Object ob) {

		HttpPost httpPost = new HttpPost(url);

		// json方式
		String jsonParam = JSONObject.toJSONString(ob);

		StringEntity entity = new StringEntity(jsonParam, "utf-8");// 解决中文乱码问题
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		httpPost.setEntity(entity);

		return getResult(httpPost);
	}
    
    public static String httpClientUploadFile(String url, MultipartFile file) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = "";
        try {
            String fileName = file.getOriginalFilename();
            HttpPost httpPost = new HttpPost(url);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addBinaryBody("file", file.getInputStream(), ContentType.MULTIPART_FORM_DATA, fileName);// 文件流
            builder.addTextBody("filename", fileName);// 类似浏览器表单提交，对应input的name和value
            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);// 执行提交
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                // 将响应内容转换为字符串
                result = EntityUtils.toString(responseEntity, Charset.forName("UTF-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * @desc http请求返回ReturnJson的JSON字符串
     * @param url
     * @return 
     * @author zhouwei
     * @date 2017年11月23日 下午6:59:44
     */
    public static Object getHttpGetResult(String url) {
    	String returnJson = httpGetRequest(url);
    	if (StringUtils.isBlank(returnJson)) {
    		return null;
    	}
    	ReturnJson returnObj = null;
    	try {
    		returnObj = JSONObject.parseObject(returnJson, ReturnJson.class);
    		if (returnObj.getStatus() != 1) {
    			logger.error("request result : " + returnJson);
    			return null;
    		}
    	}
    	catch (Exception e) {
    		String msg = "http请求返回的字符串[%s]转换成对象时出现异常";
    		logger.error(String.format(msg, returnJson), e);
    	}
		return returnObj == null ? null : returnObj.getValue();
    }
    
    public static void main(String[] args) {
        String url = "http://127.0.0.1:24010/ZKIDROnline/ScanReadIdCardInfo?OP-DEV=1&CMD-URL=4";
//        String cameraUrl = "http://127.0.0.1:8080/escst_platform/video/preview/fetchStartPreviewParam";
        String result = httpGetRequest(url);
        System.out.println(result);
//        Map<String, Object> params = new HashMap<String,Object>();
//        params.put("cameraId","052ead9a6e61402dbfb3f0a5b0719e2f");
//        result = httpPostRequest(cameraUrl,params);
//        System.out.println(result);

    }
}
