package yyj.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by 1 on 2016/11/9.
 */
public class FileUtil {
	
	private static String testPath="";
	
	/**
	 * 获取指定目录下的文件或目录(可以指定文件后缀名)
	 * 
	 * @param dirPath
	 *            文件路径
	 * @param fileFormat
	 *            文件后缀名
	 * @return File[]
	 */
	public static File[] getFiles(String dirPath, String fileFormat) {
		if (dirPath == null || dirPath.trim().equals(""))
			return null;
		File file = new File(dirPath);
		if (!file.isDirectory())
			return null;
		File[] files = null;
		if (file.isDirectory()) {
			files = file.listFiles();
			if (null == fileFormat)
				return files;
			else {
				if (files != null) {
					List<File> tempList = new ArrayList<>();
					for (File each : files)
						if (each.getName().endsWith(fileFormat))
							tempList.add(each);
					return tempList.toArray(new File[tempList.size()]);
				}
			}
		}
		return files;
	}

	/**
	 * 获取指定目录下的文件或目录(可以指定文件后缀名)
	 * 
	 * @param dirPath
	 *            文件路径
	 * @param fileFormat
	 *            文件后缀名
	 * @return list<File>
	 */
	public static List<File> getFileList(String dirPath, String fileFormat) {
		File[] files = getFiles(dirPath, fileFormat);
		return Arrays.asList(files);
	}

	/**
	 * 获取classPath目录下的文件
	 * 
	 * @param path
	 * @return File
	 */
	public static File getClassPathFile(String path) {
		try {
			String classPath = FileUtil.class.getClassLoader().getResource("").toURI().getPath();
			path = path.startsWith(File.separator) || path.startsWith("/") ? path : "/" + path;
			if(classPath.contains("test-classes")){
				System.out.println("调用测试路径:当前路径->为项目/src/main");
				path=testPath.substring(0, testPath.length()-1)+path;
			}
			File file = new File(classPath + path);
			if (!file.isDirectory())
				return file;
			else
				return null;
		} catch (Exception e) {
			throw new AppException("获取classPath目录下指定文件异常", e);
		}
	}

	/**
	 * 获取classPath指定目录下的目录或文件(可以指定文件后缀名)
	 * 
	 * @param path
	 * @param format
	 * @return File[]
	 */
	public static File[] getClassPathFiles(String path, String format) {
		try {
			return getFiles(getClassPathFile(path).getAbsolutePath(), format);
		} catch (Exception e) {
			throw new AppException("获取classPath目录下指定文件异常", e);
		}
	}

	/**
	 * 获取classPath指定目录下的目录或文件(可以指定文件后缀名)
	 * 
	 * @param path
	 * @param format
	 * @return List<File>
	 */
	public static List<File> getClassPathFileList(String path, String format) {
		File[] files = getClassPathFiles(path, format);
		return Arrays.asList(files);
	}
	
	/**
	 * 相对与classpath获取文件 ..表示上一级目录
	 * 
	 * @param path
	 * @return File
	 */
	public static File getFileOppositeClassPath(String path) {
		try {
			String classPath = FileUtil.class.getClassLoader().getResource("").toURI().getPath();
			System.out.println(classPath);
			File file = new File(classPath);
			if (path == null || "".equals(path.trim()))
				return file;
			if (path.startsWith("/"))
				path = path.substring(1);
			if(classPath.contains("test-classes")){
				System.out.println("调用测试路径");
				path=testPath+path;
			}
			String[] strs = path.split("/");
			for (String each : strs) {
				if ("..".equals(each))
					file = file.getParentFile();
				else if (file.isDirectory()) {
					file = new File(file.getAbsolutePath() + "/" + each);
				} else
					throw new AppException("路径"+path+"是一个文件而非目录或路径不存在,无法进入下一级");
			}
			return file;
		} catch (Exception e) {
			throw new AppException("相对classPath获取文件异常", e);
		}
	}
	/**
	 * 获取相对于classpath下的目录中的文件(可以指定后缀名)
	 * @param path
	 * @param format
	 * @return File[]
	 */
	public static File[] getFilesOppositeClassPath(String path, String format) {
		try {
			return getFiles(getFileOppositeClassPath(path).getAbsolutePath(), format);
		} catch (Exception e) {
			throw new AppException("获取相对于classpath目录下指定文件列表异常", e);
		}
	}
	/**
	 * 获取相对于classpath下的目录中的文件(可以指定后缀名)
	 * @param path
	 * @param format
	 * @return List<File>
	 */
	public static List<File> getFileListOppositeClassPath(String path, String format){
		File[] files=getFilesOppositeClassPath(path, format);
		return Arrays.asList(files);
	}
	/**
	 * 以Byte数组形式读取文件并返回
	 * @param file
	 * @return
	 */
	public static byte[] getFileContentByte(File file){
		try{
			FileInputStream fin=new FileInputStream(file);
			Integer temp=null;
		    if(file.length()>Integer.MAX_VALUE)
		    	throw new AppException("文件太大无法读取");
			byte[] bytes=new byte[(int)file.length()];
			fin.read(bytes);
			return bytes;
		}catch (Exception e) {
			new AppException("获取文件内容以byte形式异常", e);
		}
		return null;
	}
	/**
	 * 以字符串形式读取文件并返回
	 * @param file
	 * @return
	 */
	public static String getFileContentString(File file){
		try{
			byte[]  bytes=getFileContentByte(file);
			return new String(bytes);
		}catch (Exception e) {
			new AppException("获取文件内容以String形式异常", e);
		}
		return null;
	}
}
