package edu.cqu.common;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
public class DownloadFile {
	/**
	 * file name show in the dialog box
	 */
	private String filename = null;
	/**
	 * full absolute file path
	 */
	private String filepath = null;
	/**
	 * encoding of file name
	 */
	private String encoding = "UTF-8";
	private static Log log = LogFactory.getLog(DownloadFile.class);
	/**
	 * construct download object according to file name and encoding
	 * @param filename
	 * @param encoding
	 */
	public DownloadFile(String filename, String filepath, String encoding) {
		this.filename = filename;
		this.encoding = encoding;
		this.filepath = filepath;
	}
	public DownloadFile(String filename) {
		this(filename, null, null);
	}
	public DownloadFile(String filename, String filepath) {
		this(filename, filepath, "utf-8");
	}
	/**
	 * write file to response object 
	 */
	public void download(HttpServletResponse response) throws IOException {
		String f;
		if (this.filename != null) {
			File file = new File(this.filepath);
			if (!file.exists()) {
				throw new IOException("file does not exit:" + filename);
			} else {
				f = toUtf8String(filename);
				response.setContentType("application/x-msdownload");
				response.setContentLength((int) file.length());
				response.setHeader("Content-Disposition", "attachment;"
						+ " filename=" + f);
				write(response.getOutputStream());
			}
		}
	}
	/**
	 * write file to output stream
	 */
	protected void write(OutputStream os) throws IOException {
		FileInputStream fis = new FileInputStream(this.filepath);
		BufferedInputStream bis = new BufferedInputStream(fis);
		byte[] s = new byte[1024];
		int i = 0;
		while ((i = bis.read(s)) > 0) {
			os.write(s, 0, i);
		}
		if (os != null) {
			os.flush();
			os.close();
		}
		if (bis != null)
			bis.close();
		if (fis != null)
			fis.close();
	}
	/**
	 * transfer file name to utf-8 encoding
	 */
	public static String toUtf8String(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception ex) {
					System.out.println(ex);
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}
}
