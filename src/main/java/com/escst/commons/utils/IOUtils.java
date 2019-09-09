package com.escst.commons.utils;

import org.springframework.util.StringUtils;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 用于IO、文件操作的工具类，此类封装了文件读写、复制、删除、创建以及对文件路径转换的方法
 * @author Administrator
 */
public class IOUtils {
	private final static Map<String, String> FILE_TYPE_MAP = new HashMap<String, String>();
	private final static TreeMap<String, String> FILE_TYPE_HEADER_MAP = new TreeMap<String, String>(
			new CompareByLength());
	static {
		FILE_TYPE_MAP.put("jpg", "FFD8FF"); // JPEG (jpg)
		FILE_TYPE_MAP.put("png", "89504E47"); // PNG (png)
		FILE_TYPE_MAP.put("gif", "47494638"); // GIF (gif)
		FILE_TYPE_MAP.put("tif", "49492A00"); // TIFF (tif)
		FILE_TYPE_MAP.put("bmp", "424D"); // Windows Bitmap (bmp)
		FILE_TYPE_MAP.put("dwg", "41433130"); // CAD (dwg)
		FILE_TYPE_MAP.put("html", "68746D6C3E"); // HTML (html)
		FILE_TYPE_MAP.put("rtf", "7B5C727466"); // Rich Text Format (rtf)
		FILE_TYPE_MAP.put("xml", "3C3F786D6C");
		FILE_TYPE_MAP.put("zip", "504B0304");
		FILE_TYPE_MAP.put("rar", "52617221");
		FILE_TYPE_MAP.put("psd", "38425053"); // Photoshop (psd)
		FILE_TYPE_MAP.put("eml", "44656C69766572792D646174653A"); // Email
																	// [thorough
																	// only]
																	// (eml)
		FILE_TYPE_MAP.put("docx", "504b030414000600080000002100");// docx
		FILE_TYPE_MAP.put("dbx", "CFAD12FEC5FD746F"); // Outlook Express (dbx)
		FILE_TYPE_MAP.put("pst", "2142444E"); // Outlook (pst)
		FILE_TYPE_MAP.put("xls", "D0CF11E0"); // MS Word
		FILE_TYPE_MAP.put("doc", "D0CF11E0"); // MS Excel 注意：word 和 excel的文件头一样
		FILE_TYPE_MAP.put("mdb", "5374616E64617264204A"); // MS Access (mdb)
		FILE_TYPE_MAP.put("wpd", "FF575043"); // WordPerfect (wpd)
		FILE_TYPE_MAP.put("eps", "252150532D41646F6265");
		FILE_TYPE_MAP.put("ps", "252150532D41646F6265");
		FILE_TYPE_MAP.put("pdf", "255044462D312E"); // Adobe Acrobat (pdf)
		FILE_TYPE_MAP.put("qdf", "AC9EBD8F"); // Quicken (qdf)
		FILE_TYPE_MAP.put("pwl", "E3828596"); // Windows Password (pwl)
		FILE_TYPE_MAP.put("wav", "57415645"); // Wave (wav)
		FILE_TYPE_MAP.put("avi", "41564920");
		FILE_TYPE_MAP.put("ram", "2E7261FD"); // Real Audio (ram)
		FILE_TYPE_MAP.put("rm", "2E524D46"); // Real Media (rm)
		FILE_TYPE_MAP.put("mpg", "000001BA"); //
		FILE_TYPE_MAP.put("mov", "6D6F6F76"); // Quicktime (mov)
		FILE_TYPE_MAP.put("asf", "3026B2758E66CF11"); // Windows Media (asf)
		FILE_TYPE_MAP.put("mid", "4D546864"); // MIDI (mid)
		for (String key : FILE_TYPE_MAP.keySet()) {
			FILE_TYPE_HEADER_MAP.put(FILE_TYPE_MAP.get(key), key);
		}
	}

	/**
	 * 读取一个文件的内容
	 * 
	 * @param fileName
	 *            String,文件的全路径
	 * @return String,文件内容
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String getFileContent(String fileName) throws FileNotFoundException, IOException {
		Reader reader = null;
		StringBuffer contentBuf = new StringBuffer();
		try {
			reader = new BufferedReader(new FileReader(fileName));
			char[] buf = new char[1024];
			int count = -1;
			while (true) {
				count = reader.read(buf);
				if (count == -1) {
					break;
				}
				contentBuf.append(buf, 0, count);
			}
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		return contentBuf.toString();
	}

	/**
	 * 检测并创建目录
	 * 
	 * @param fileName
	 *            文件路径
	 */
	public static void checkAndCreateDir(String fileName) {

		if (fileName == null) {
			return;
		}
		File file = new File(fileName);
		file.mkdirs();
	}

	/**
	 * 检测并创建目录
	 * 
	 * @param file
	 *            文件对象
	 */
	public static void checkAndCreateDir(File file) {

		if (file == null) {
			return;
		}
		// 如果是目录
		if (file.isDirectory()) {
			file.mkdirs();
		} else {
			// 如果是文件,则检测文件的上一级
			if (file.getParentFile() != null) {
				file.getParentFile().mkdirs();
			}
		}
	}

	/**
	 * 从一个输入流中根据一定的编码方式读取出内容文本
	 * 
	 * @param in
	 *            InputStream，输入流
	 * @param encode
	 *            String，编码方式
	 * @return String 内容文本
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public static String getInputStreamText(InputStream in, String encode) throws UnsupportedEncodingException,
			IOException {

		StringBuffer contentBuf = new StringBuffer();
		Reader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(in, encode));
			char[] buf = new char[1024];
			int count = -1;
			while (true) {
				count = reader.read(buf);
				if (count == -1) {
					break;
				}
				contentBuf.append(buf, 0, count);
			}
		} finally {
			IOUtils.closeQuierly(reader);
		}
		return contentBuf.toString();
	}

	/**
	 * 把一段内容写到指定的文件中
	 * 
	 * @param filename
	 *            String
	 * @param contents
	 *            byte[]
	 * @throws IOException
	 */
	public static void writeToFile(String filename, byte[] contents) throws IOException {

		// 读入文件内容
		OutputStream out = null;
		FileOutputStream fot = null;
		try {
			fot = new FileOutputStream(filename);
			out = new BufferedOutputStream(fot);
			out.write(contents);
			out.flush();
		} finally {
			IOUtils.closeQuierly(out);
			IOUtils.closeQuierly(fot);
		}
	}

	/**
	 * 删除当前目录或者文件。
	 * 
	 * @param file
	 *            待删除目录或者文件。
	 */
	public static void deleteFile(File file) {

		if (file == null) {
			return;
		}
		if (file.isDirectory()) {
			File[] subFiles = file.listFiles();
			if(subFiles != null && subFiles.length > 0){
				for (int i = 0; i < subFiles.length; i++) {
					deleteFile(subFiles[i]);
				}
			}
			// 子目录删除后才可以删除当期目录。
			file.delete();
		} else {
			file.delete();
		}

	}

	/**
	 * 复制文件，采用二进制的形式，不会出现乱码。
	 * 
	 * @param source
	 *            源文件的文件名，含路径
	 * @param dest
	 *            目标文件的文件名，含路径。
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void copy(String source, String dest) throws FileNotFoundException, IOException {

		if (source == null || dest == null) {
			throw new FileNotFoundException();
		}
		if (source.equals(dest)) {
			return;// 不需要复制。
		}
		File sf = new File(source);
		File df = new File(dest);
		if (sf.isDirectory()) {
			if (!df.exists()) {
				df.mkdirs();
			}
			String[] fileList = sf.list();
			if(fileList != null && fileList.length > 0 ){
				for (String fn : fileList) {
					String sfp = parseFilePath(source + File.separator + fn);
					String dfp = parseFilePath(dest + File.separator + fn);
					copy(sfp, dfp);
				}
			}
		} else {
			final byte[] buffer = new byte[1024 * 100];// 100KB
			InputStream input = null;
			OutputStream output = null;
			int n = 0;
			try {
				input = new FileInputStream(sf);
				output = new FileOutputStream(df);
				while (-1 != (n = input.read(buffer))) {
					output.write(buffer, 0, n);
				}
			} finally {
				IOUtils.closeQuierly(input);
				IOUtils.closeQuierly(output);
			}
		}

	}

	/**
	 * 更改文件名。如果当前目录存在destFileName，则更改失败。
	 * 
	 * @param source
	 *            源文件
	 * @param destFileName
	 *            更改后文件名
	 * @return 更改成功返回true 否则返回false
	 */
	public static boolean rename(File source, String destFileName) {

		String destFileNamePath = source.getParentFile().getPath() + File.separator + destFileName;
		File dest = new File(destFileNamePath);
		if (dest.exists()) {
			return false;
		}
		return source.renameTo(dest);

	}

	/**
	 * 转换文件路径
	 * 
	 * @param path
	 * @return
	 */
	public static String parseFilePath(String path) {
		path = path.replace('\\', File.separatorChar);
		path = path.replace('/', File.separatorChar);
		path = path.replaceAll("((\\\\)|/)+", "\\" + File.separator);
		path = new File(path).getPath();
		return path;
	}

	/**
	 * 检查文件是否存在，如果不存在，则创建一个新文件
	 * 
	 * @param path
	 *            文件路径
	 * @return 返回文件存在或是否创建成功 true 文件存在或创建成功 false 文件不存在且创建不成功
	 * @throws IOException
	 */
	public static boolean checkAndBuildFile(String path) throws IOException {
		return checkAndBuildFile(new File(path));
	}

	/**
	 * 检查文件是否存在，如果不存在，则创建一个新文件
	 * 
	 * @param file 文件对象
	 * @return 返回文件存在或是否创建成功 true 文件存在或创建成功 false 文件不存在且创建不成功
	 * @throws IOException
	 */
	public static boolean checkAndBuildFile(File file) throws IOException {
		boolean flag = Boolean.TRUE;
		if (!file.exists()) {
			if (!file.getParentFile().exists()) {
				flag = file.getParentFile().mkdirs();
			}
			if (flag) {
				flag = file.createNewFile();
			}
		}
		return flag;
	}

	/**
	 * 获取文件类型
	 * 
	 * @param path
	 *            文件路径
	 * @return
	 * @throws IOException
	 */
	public static String getFileType(String path) throws IOException {
		return getFileType(new File(path));
	}

	/**
	 * 获取文件类型
	 * 
	 * @param file
	 *            文件对象
	 * @return
	 * @throws IOException
	 */
	public static String getFileType(File file) throws IOException {
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			return getFileType(is);
		} finally {
			IOUtils.closeQuierly(is);
		}
	}

	/**
	 * 获取文件类型
	 * 
	 * @param is
	 *            文件输入流
	 * @return
	 * @throws IOException
	 */
	public static String getFileType(InputStream is) throws IOException {
		byte[] bs = new byte[50];
		is.read(bs);
		String fileHexStr = getHexString(bs);
		String type = null;
		if (StringUtils.hasLength(fileHexStr)) {
			for (String key : FILE_TYPE_HEADER_MAP.keySet()) {
				if (fileHexStr.toUpperCase().startsWith(key)) {
					type = FILE_TYPE_HEADER_MAP.get(key);
					break;
				}
			}
		}
		return type;
	}

	/**
	 * 
	 * @param bytes
	 * @return
	 */
	public static String getHexString(byte[] bytes) {
		String str = null;
		if (bytes != null && bytes.length > 0) {
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				int v = bytes[i] & 0XFF;
				String hv = Integer.toHexString(v);
				if (hv.length() < 2) {
					builder.append(0);
				}
				builder.append(hv);
			}
			str = builder.toString();
		}
		return str;
	}

	/**
	 * 将字符串表示的文件大小转换为字节大小
	 * 
	 * @param fileSize
	 *            表示文件大小的字符串
	 * @return
	 */
	public static long parseFileSize(String fileSize) {
		long size = 0;
		if (fileSize.matches("^\\d+[a-zA-Z]*$")) {
			String unit = fileSize.replaceAll("\\d+", "");
			String num = fileSize.replaceAll("\\D+", "");
			if (StringUtils.hasLength(unit)) {
				size = Long.parseLong(num);
				if (unit.equalsIgnoreCase("kb") || unit.equalsIgnoreCase("k")) {
					size = size * 1024;
				} else if (unit.equalsIgnoreCase("mb") || unit.equalsIgnoreCase("m")) {
					size = size * 1024 * 1024;
				} else if (unit.equalsIgnoreCase("gb") || unit.equalsIgnoreCase("g")) {
					size = size * 1024 * 1024 * 1024;
				} else if (unit.equalsIgnoreCase("tb") || unit.equalsIgnoreCase("t")) {
					size = size * 1024 * 1024 * 1024 * 1024;
				}
			}
		}
		return size;
	}

	/**
	 * 关闭Closeable对象，忽略关闭时抛出的异常
	 * 
	 * @param closeable
	 */
	public static void closeQuierly(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (IOException e) {
			}
		}
	}

	/**
	 * 删除指定目录下的临时文件
	 * 
	 * @param path
	 *            目录路径
	 * @param before
	 *            指定最后更新时间小于当前时间多少毫秒的文件被删除
	 */
	public static void cleanTempFile(String path, long before) {
		cleanTempFile(new File(path), before);
	}

	/**
	 * 删除指定目录下的临时文件
	 * 
	 * @param dir
	 *            目录
	 * @param before
	 *            指定最后更新时间小于当前时间多少毫秒的文件被删除
	 */
	public static void cleanTempFile(File dir, long before) {
		if (dir.exists() && dir.isDirectory()) {
			FilenameFilter filter = new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return name.toLowerCase().endsWith(".tmp");
				}
			};
			File[] fs = dir.listFiles(filter);
			long line = System.currentTimeMillis() - before;
			if(fs != null && fs.length > 0) {
				for (File f : fs) {
					if (f.isDirectory()) {
						cleanTempFile(f, before);
					} else if (f.lastModified() <= line) {
						f.delete();
					}
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		// copy("D:\\test\\war\\config\\reqforward-config.xml",
		// "D:\\test\\war\\config\\reqforward-config.bak.xml");
		// System.out.println("success");
		// System.out.println(parseFilePath("D://test\\war\\config\\reqforward-config.xml"));
		// test(test);
		// System.out.println(getFileType("E:\\cmmi3\\《基于CMMI的软件工程教程》作者张万军\\第4章项目评审管理\\项目评审表.xls"));
		// System.out.println(getFileType("E:\\cmmi3\\代码检视--董科ssms3.001.xls"));
		// System.out.println(getFileType("E:\\lucene-4.3.0.zip"));
		// System.out.println(parseFileSize("1@"));
		System.out.println(getFileType("C:\\Users\\xieyg\\Downloads\\ss.tmp"));
	}

	private static class CompareByLength implements Comparator<String> {

		@Override
		public int compare(String str1, String str2) {
			if (str1 == null) {
				return 1;
			}
			if (str2 == null) {
				return -1;
			}
			int num = str2.length() - str1.length();
			return num == 0 ? str2.compareTo(str1) : num;
		}

	}

}
