package com.escst.file.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.*;
import com.escst.file.entity.FileEntity;
import com.escst.file.mapper.FileMapper;
import com.escst.file.vo.FilePathVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author caozx
 * @desc
 * @date 2017/3/15 11:12
 */
@Service
@Transactional
public class FileService {

    private static Logger logger = LoggerFactory.getLogger(FileService.class);

    @Autowired
    private FileMapper fileMapper;

    /**
     * @param modelName
     * @return
     * @desc 得到上传文件的相对路径
     * @author zhouwei
     * @date 2017年11月27日 下午2:56:24
     */
    public String getUploadRelativePath(String modelName) {
        StringBuilder sb = new StringBuilder();
        sb.append("upload").append("/").append(modelName).append("/");
        sb.append(DateUtils.format(new Date(), "yyyyMM"));
        return sb.toString();
    }

    /**
     * @param multipartFile
     * @param modelName
     * @return
     * @desc 上传单个文件
     * @author zhouwei
     * @date 2017年11月27日 下午3:34:20
     */
    public String uploadFile(String modelName, MultipartFile multipartFile) {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        String filePath = getUploadRelativePath(modelName);
        paramsMap.put("filePath", filePath);
        String result = FileUploadUtils.uploadFiles(new MultipartFile[]{multipartFile}, paramsMap);
        JSONObject jsonObject = JSONObject.parseObject(result);
        Integer status = jsonObject.getInteger("status");
        if (status == 0) {
            throw new EscstException("文件上传失败！");
        }
        JSONArray jsonArray = jsonObject.getJSONArray("value");
        return (String) jsonArray.get(0);
    }

//    public String uploadFile(String name,String baseFile){
//
//	}


    /**
     * @param modelName     模块名称
     * @param multipartFile
     * @return
     * @desc 上传临时文件到本地, 主要用于excel导入
     * @author zhouwei
     * @date 2017年11月28日 下午1:29:50
     */
    public String uploadTempFile(String modelName, MultipartFile multipartFile) {
        if (multipartFile.getSize() <= 0) {
            throw new EscstException("上传文件为空");
        }
        //文件存储的存到数据库的路径
        String savePath = getUploadRelativePath(modelName);
        //文件存储的绝对路径
        String filePath = ContextUtils.getSession().getServletContext().getRealPath("/") + File.separator + "resources" + File.separator + savePath;
        String originalFilename = multipartFile.getOriginalFilename();
        String suffix = "";
        if (originalFilename.lastIndexOf(".") != -1) {
            suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        File file = new File(filePath);
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
        //对上传的文件进行重新命名
        String uuid = UuidUtils.getUuid();
        String fileName = uuid + suffix;
        filePath = filePath + File.separator + fileName;
        file = new File(filePath);
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            logger.error("上传文件出现异常", e);
            throw new EscstException("上传文件出现异常：", e);
        }
        return filePath;
    }

    /**
     * @param modelName
     * @param businessId
     * @param files
     * @desc 上传附件
     * @author zhouwei
     * @date 2017年11月28日 下午3:01:20
     */
    public void uploadFile(String modelName, String businessId, MultipartFile[] files) {
        String filePath = getUploadRelativePath(modelName);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("businessId", businessId);
        map.put("filePath", filePath);
        String result = FileUploadUtils.uploadFiles(files, map);
        JSONObject jsonObject = JSONObject.parseObject(result);
        Integer status = jsonObject.getInteger("status");
        if (status == 0) {
            throw new EscstException("文件上传失败！");
        }
    }

    /**
     * @param businessId
     * @return
     * @desc 根据业务id查询业务所关联的文件id集合
     * @author caozx
     * @date 2017/11/21 10:17
     */
    public List<String> queryFilePathByBusinessId(String businessId) {
//		String url = ResourceUtil.getFileServerUrl() + "queryFilePathByBusinessId?businessId=" + businessId;
//        Object returnObj = HttpClientUtils.getHttpGetResult(url);
//        if (returnObj == null) {
//        	return null;
//        }
//        @SuppressWarnings("unchecked")
//		List<JSONObject> list = (List<JSONObject>)returnObj;
        List<String> rst = new ArrayList<String>();
//        for (JSONObject jsonObj : list) {
//        	rst.add(jsonObj.getString("id"));
//        }
        ////modify by daiwenjie 获取Apache文件服务器路径
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("businessId", businessId);
        rst = getFileId(map);
        return rst;
    }

    /**
     * 获取文件路径集合，包括图片路径、图片缩微图路径、文件后缀名
     *
     * @param businessId
     * @return
     */
    public List<FilePathVO> queryFilePath(String businessId) {
//		String url = ResourceUtil.getFileServerUrl() + "queryFilePathByBusinessId?businessId=" + businessId;
//        Object returnObj = HttpClientUtils.getHttpGetResult(url);
//        if (returnObj == null) {
//        	return null;
//        }
//        @SuppressWarnings("unchecked")
//		List<JSONObject> list = (List<JSONObject>)returnObj;
        List<FilePathVO> rst = new ArrayList<FilePathVO>();
//        for (JSONObject jsonObj : list) {
//        	String id = jsonObj.getString("id");
//        	FilePathVO vo = new FilePathVO();
//        	vo.setFilePath(getFilePath(id));
//        	vo.setSmallFilePath(getThumbPath(id));
//        	String type = jsonObj.getString("type");
//        	vo.setFileSuffix(type.substring(1));//type中有点号,需要去掉
//        	rst.add(vo);
//        }
        //modify by daiwenjie 获取Apache文件服务器路径
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("businessId", businessId);
        rst = selectFilePath(map);
        return rst;
    }


    /**
     * @param businessIds
     * @return
     * @desc 根据业务ID得到附件的map
     * @author zhouwei
     * @date 2017年11月23日 下午5:29:19
     */
    public Map<String, List<FilePathVO>> getPicturePathMapByBusinessIds(List<String> businessIds) {
        Map<String, List<FilePathVO>> map = new HashMap<String, List<FilePathVO>>();
        //modify by daiwenjie 获取Apache文件服务器路径
        for (String businessId : businessIds) {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("businessId", businessId);
            List<FilePathVO> filePathList = selectFilePath(paramMap);
            map.put(businessId, filePathList);
        }
//		String ids = StringUtils.join(businessIds, ",");
//		String url = ResourceUtil.getFileServerUrl() + "queryFileIdsByBusinessIds?businessIds=" + ids;
//		logger.debug("http request: " + url);
//		Object returnObj = HttpClientUtils.getHttpGetResult(url);
//        if (returnObj == null) {
//        	logger.error("getPicturePathMapByBusinessIds 获取附件结果为空");
//        	return map;
//        }
//		@SuppressWarnings("unchecked")
//		Map<String, List<String>> filePathMap = (Map<String, List<String>>)returnObj;
//		Iterator<Map.Entry<String, List<String>>> it = filePathMap.entrySet().iterator();
//		while (it.hasNext()) {
//			Map.Entry<String, List<String>> entry = it.next();
//			String businessId = entry.getKey();
//			List<String> idList = entry.getValue();
//			List<FilePathVO> filePathList = new ArrayList<FilePathVO>(idList.size());
//			for (String id : idList) {
//				FilePathVO filePathVO = new FilePathVO();
//				filePathVO.setFilePath(getFilePath(id));
//				filePathVO.setSmallFilePath(getThumbPath(id));
//				filePathList.add(filePathVO);
//			}
//			map.put(businessId, filePathList);
//		}
        return map;
    }

    /**
     * @param id
     * @return
     * @desc 得到文件的访问地址
     * @author zhouwei
     * @date 2017年11月22日 下午3:41:20
     */
    public String getFilePath(String id) {
        return getPath(id, 1);
    }

    /**
     * @param path
     * @return
     * @desc 得到文件的访问地址
     * @author dwj
     * @date 2018/3/6 13:53
     */
    public String getFilePath(String path, String type) {
        return getPath(path, 1, type);
    }

    /**
     * @param id
     * @return
     * @desc 得到图片缩略图的访问地址
     * @author zhouwei
     * @date 2017年11月22日 下午3:40:54
     */
    public String getThumbPath(String id) {
        return getPath(id, 2);
    }

    /**
     * @param path
     * @return
     * @desc 得到图片缩略图的访问地址
     * @author dwj
     * @date 2018/3/6 13:55
     */
    public String getThumbPath(String path, String type) {
        return getPath(path, 2, type);
    }

    public String getPreviewPath(String id) {
        return getPath(id, 3);
    }

    /**
     * @param id
     * @param type 1:原始文件;2:缩略图
     * @return
     * @desc
     * @author zhouwei
     * @date 2017年11月22日 下午3:41:58
     */
    private String getPath(String id, int type) {
        StringBuilder sb = new StringBuilder();
        sb.append(ResourceUtil.getFileDownloadPath());
        sb.append("?id=").append(id);
        sb.append("&type=").append(type);
        return sb.toString();
    }

    /**
     * @param path
     * @param type 1:原始文件;2:缩略图
     * @return
     * @desc
     * @author dwj
     * @date 2018/3/6 13:56
     */
    private String getPath(String path, int type, String fileType) {
        StringBuilder sb = new StringBuilder();
        sb.append(ResourceUtil.getFileDownloadPathNew());
        if (type == 2) {
            sb.append(StringUtils.substringBefore(path, "."));
            sb.append("_100x100");
            sb.append(fileType);
        } else {
            sb.append(path);
        }
        return sb.toString();
    }

    /**
     * 根据文件ID获取原图
     *
     * @param fileId
     * @return
     */
    public String queryFilePathById(String fileId) {
//		String url = ResourceUtil.getFileServerUrl() + "queryFileById?id=" + fileId;
//		Object returnObj = HttpClientUtils.getHttpGetResult(url);
//		if (returnObj == null) {
//			return null;
//		}
//		JSONObject jsonObject = (JSONObject)returnObj;
//		String id = jsonObject.getString("id");
//		String path = getFilePath(id);
        //modify by daiwenjie 获取Apache文件服务器路径
        String path = queryFilePathByFileId(fileId);
        return path;
    }


    /**
     * 根据文件ID获取在线预览
     *
     * @param fileId
     * @return
     */
    public String queryPreviewPathById(String fileId) {
        String url = ResourceUtil.getFileServerUrl() + "queryFileById?id=" + fileId;
        Object returnObj = HttpClientUtils.getHttpGetResult(url);
        if (returnObj == null) {
            return null;
        }
        JSONObject jsonObject = (JSONObject) returnObj;
        String id = jsonObject.getString("id");
        String path = getPreviewPath(id);
        return path;
    }

    /**
     * @param map
     * @return
     * @desc 获取Apache 文件服务器路径
     * @author dwj
     * @date 2018/3/7 13:28
     */
    private List<FilePathVO> selectFilePath(Map<String, Object> map) {
        List<FilePathVO> rst = new ArrayList<FilePathVO>();

        try {
            List<FileEntity> list = fileMapper.queryByBusinessId(map);
            for (FileEntity fileEntity : list) {
                FilePathVO vo = new FilePathVO();
                String path = fileEntity.getPath();
                String type = fileEntity.getType();
                vo.setId(fileEntity.getId());
                vo.setFilePath(getFilePath(path, type));
                vo.setSmallFilePath(getThumbPath(path, type));
                vo.setFileSuffix(type.substring(1));//type中有点号,需要去掉
                rst.add(vo);
            }
        } catch (Exception e) {
            throw new EscstException("获取文件路劲异常");
        }
        return rst;
    }

    /**
     * @param map
     * @return
     * @desc 获取文件Id
     * @author dwj
     * @date 2018/3/7 13:39
     */
    private List<String> getFileId(Map<String, Object> map) {
        List<String> list = new ArrayList<String>();
        try {
            List<FileEntity> fileEntityList = fileMapper.queryByBusinessId(map);
            for (FileEntity fileEntity : fileEntityList) {
                String id = fileEntity.getId();
                list.add(id);
            }
        } catch (Exception e) {
            throw new EscstException("获取文件Id失败");
        }
        return list;
    }

    /**
     * @param fileId
     * @return
     * @desc 获取文件访问地址
     * @author dwj
     * @date 2018/3/7 10:27
     */
    public String queryFilePathByFileId(String fileId) {
        String filePath = null;
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("fileId", fileId);
            FileEntity entity = fileMapper.queryById(map);
            if (entity == null) {
                return null;
            }
            String type = entity.getType();
            String path = entity.getPath();
            filePath = getFilePath(path, type);
        } catch (Exception e) {
            throw new EscstException("获取文件路径异常");
        }
        return filePath;
    }

    /**
     * @param businessId
     * @return
     * @desc 获取头像文件访问地址
     * @author dwj
     * @date 2018/3/20 15:13
     */
    public String queryPathByBusinessId(String businessId) {
        String filePath = null;
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("businessId", businessId);
            FileEntity entity = fileMapper.queryById(map);
            if (entity == null) {
                return null;
            }
            String type = entity.getType();
            String path = entity.getPath();
            filePath = getFilePath(path, type);
        } catch (Exception e) {
            throw new EscstException("获取文件路径异常");
        }
        return filePath;
    }





}
