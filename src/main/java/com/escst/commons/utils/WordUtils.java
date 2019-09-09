package com.escst.commons.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * @desc 导出word工具类
 * @author niejing
 * @date 2017年12月14日 下午2:23:40
 */
public class WordUtils {
	private static final Logger logger = LoggerFactory.getLogger(WordUtils.class);

	public static File createDoc(Map<?, ?> dataMap, String ftlName) {
		// 生成的word文件名称
		String resultFileName = DateUtils.format(new Date(), DateUtils.TO_MILLISECOND_N) + ".doc";

		// 获取java项目编译后根路径
		String localPath = ContextUtils.getSession().getServletContext().getRealPath("/");
		String relativePath = "resources" + File.separator + "static";// 文件的相对路径
		String templateRelativePath = relativePath + File.separator + "template" + File.separator;// 模版的相对路径
		String downloadRelativePath = relativePath + File.separator + "download" + File.separator + resultFileName;// 下载文件的相对路径

		String tmpWordPath = localPath + templateRelativePath;
		String destFilePath = localPath + downloadRelativePath;
		logger.info("导出word文档 tmpWordName is {} destFilePath is{}", tmpWordPath, destFilePath);
		return createDoc(dataMap, ftlName, tmpWordPath, destFilePath);
	}

	public static File createDoc(Map<?, ?> dataMap, String ftlName, String tmpWordPath, String resultFilePath) {
		Configuration configuration = new Configuration();
		configuration.setDefaultEncoding("utf-8");

		Map<String, Template> allTemplates = new HashMap<String, Template>();
		try {
			configuration.setDirectoryForTemplateLoading(new File(tmpWordPath));
			allTemplates.put("resume", configuration.getTemplate(ftlName));
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		File f = new File(resultFilePath);
		Template t = allTemplates.get("resume");
		try {
			Writer w = new OutputStreamWriter(new FileOutputStream(f), "utf-8");
			t.process(dataMap, w);
			w.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return f;
	}
}
