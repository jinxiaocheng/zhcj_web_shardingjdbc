package com.escst.commons.utils;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.httpclient.util.EncodingUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author caozx
 * @desc 文件上传工具类
 * @date 2017/8/12 10:31
 */
public class FileUploadUtils {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadUtils.class);

    /**
     * 设置编码
     **/
    private static Charset charset = Charset.forName("UTF-8");

    /**
     * 上传文件参数名称
     **/
    private static final String UPLOAD_FILE_PARAM_NAME = "files";


    /**
     * 上传多个文件
     *
     * @param files
     * @param map
     * @return
     */
    public static String uploadFiles(MultipartFile[] files, Map<String, Object> map) {
        String result = "";
        //1:创建一个httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            //2：创建http的发送方式对象
            String url = ResourceUtil.getFileUploadPath();
            HttpPost httppost = new HttpPost(url);

            //3：创建要发送的实体，就是key-value的这种结构，借助于这个类，可以实现文件和参数同时上传
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setCharset(charset);
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

            //对上传的文件进行组装
            addFileBodyPart(UPLOAD_FILE_PARAM_NAME, files, builder);

            //对参数进行组装
            addParamBodyPart(map, builder);

            HttpEntity entity = builder.build();
            httppost.setEntity(entity);

            //4：执行httppost对象，从而获得信息
            HttpResponse response = httpClient.execute(httppost);
            HttpEntity resEntity = response.getEntity();

            //获得返回来的信息，转化为字符串string
            result = EntityUtils.toString(resEntity, charset);
            System.out.println(result);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
     * 上传多个文件
     *
     * @param files
     * @param map
     * @return
     */
    public static String uploadFiles(MultipartFile[] files, Map<String, Object> map,String url) {
        String result = "";
        //1:创建一个httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            //2：创建http的发送方式对象
            HttpPost httppost = new HttpPost(url);

            //3：创建要发送的实体，就是key-value的这种结构，借助于这个类，可以实现文件和参数同时上传
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setCharset(charset);
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

            //对上传的文件进行组装
            addFileBodyPart(UPLOAD_FILE_PARAM_NAME, files, builder);

            //对参数进行组装
            addParamBodyPart(map, builder);

            HttpEntity entity = builder.build();
            httppost.setEntity(entity);

            //4：执行httppost对象，从而获得信息
            HttpResponse response = httpClient.execute(httppost);
            HttpEntity resEntity = response.getEntity();

            //获得返回来的信息，转化为字符串string
            result = EntityUtils.toString(resEntity, charset);
            System.out.println(result);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
     * 对参数进行组装
     *
     * @param map
     * @param builder
     */
    private static void addParamBodyPart(Map<String, Object> map, MultipartEntityBuilder builder) {
        if (CollectionUtils.isEmpty(map)) {
            logger.info("没有参数--------");
            return;
        }
        ContentType contentType = ContentType.create("text/plain", charset);
        Set<String> set = map.keySet();
        for (String key : set) {
            String value = (String) map.get(key);
            StringBody fileBody = new StringBody(value, contentType);
            builder.addPart(key, fileBody);
        }
    }

    /**
     * 对上传的文件进行组装
     *
     * @param paramName
     * @param files
     * @param builder
     */
    private static void addFileBodyPart(String paramName, MultipartFile[] files, MultipartEntityBuilder builder) {
        List<FileBody> fileBodys = new ArrayList<FileBody>();
        for (MultipartFile multipartFile : files) {
            CommonsMultipartFile cf = (CommonsMultipartFile) multipartFile;
            DiskFileItem fi = (DiskFileItem) cf.getFileItem();
            File file = fi.getStoreLocation();
            ContentType contentType= ContentType.create("multipart/form-data", charset);
            FileBody fileBody = new FileBody(file, contentType, multipartFile.getOriginalFilename());
            fileBodys.add(fileBody);
        }
        if (fileBodys == null || fileBodys.size() < 1 || builder == null || paramName == null) {
            return;
        }
        for (FileBody fileBody : fileBodys) {
            builder.addPart(paramName, fileBody);
        }
    }

    public static void main(String[] args) {
        String filenam = "你好";
        byte[] b = EncodingUtil.getBytes(filenam,"utf-8");
        System.out.println(b);
        String s = EncodingUtil.getString(b,"utf-8");
        System.out.println(s);
    }

}
