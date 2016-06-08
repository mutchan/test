package jp.hiradate.http;

import static jp.hiradate.http.HttpConst.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.swing.BoundedRangeModel;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import jp.hiradate.xml.XmlReader;

class FormData {

}

public class HttpBody {
	private final String bodyText;
	private final HttpHeader header;

	ArrayList<Map<String, String>> contentInfo = new ArrayList<Map<String, String>>();
	ArrayList<String> formContents = new ArrayList<String>();

	public HttpBody(InputStream in, HttpHeader header) throws IOException {
		this.header = header;
		StringBuilder body = new StringBuilder();

		body.append(this.readBody(in));

		this.bodyText = body.toString();
	}

	private String readBody(InputStream in) throws IOException {
		if (this.header.isChunkedTransfer()) {
			return this.readBodyByChunkedTransfer(in);
		} else {
			return this.readBodyByContentLength(in);
		}
	}

	private String readBodyByChunkedTransfer(InputStream in) throws IOException {
		StringBuilder body = new StringBuilder();

		int chunkSize = Integer.parseInt(IOUtil.readLine(in), 16);

		while (chunkSize != 0) {
			byte[] buffer = new byte[chunkSize];
			in.read(buffer);

			body.append(IOUtil.toString(buffer));

			IOUtil.readLine(in); // chunk-body の末尾にある CRLF を読み飛ばす
			chunkSize = Integer.parseInt(IOUtil.readLine(in), 16);
		}

		return body.toString();
	}

	private String readBodyByContentLength(InputStream in) throws IOException {
		final int contentLength = this.header.getContentLength();

		if (contentLength <= 0) {
			return null;
		}

		if (!this.header.getContentType().startsWith("multipart/form-data;")) {
			return null;
		}

		byte[] buffer = new byte[contentLength];
		for( int off = 0; off < contentLength; ) {
			off += in.read(buffer, off, contentLength - off);
		}

		String bodys = new String(buffer, StandardCharsets.UTF_8);

		String boundary = "--" + this.header.getContentType().split("boundary=")[1].trim();
		//System.out.println("boundary: " + boundary);

		int fromIndex = 0, lastIndex = 0;
		while ((lastIndex = indexOf(buffer, boundary, fromIndex)) >= 0) {

			String name = null;
			int index = fromIndex;
			while (index < lastIndex) {
				byte[] line = readLine(buffer, index);
				String contentLine = new String(line, StandardCharsets.UTF_8);
				System.out.print(contentLine);
				index += line.length;
				if (line[0] == '\r' && line[1] == '\n') {
					break;
				}
				if (contentLine.startsWith("Content-Disposition: form-data; name=\"fileToUpload\";")) {
					name = contentLine.split(";")[2].trim().split("=")[1];
					name = name.substring(name.lastIndexOf('\\')+1, name.length() - 1);
				}
			}

			if (name != null) {
				byte[] formBody = new byte[lastIndex - index];
				for (int i = 0; i < lastIndex - index; i++) {
					formBody[i] = buffer[index + i];
				}

				File file = new File("./files/UploadFiles/" + name);
				FileOutputStream wr = new FileOutputStream(file);
				wr.write(formBody);

				//System.out.print(new String(formBody, StandardCharsets.UTF_8));
				wr.close();
				
				try {
					XmlReader.addElement("./files/data.xml", name);
				} catch (SAXException | ParserConfigurationException e) {
					e.printStackTrace();
				}
			}
			fromIndex = lastIndex + 1;
		}

		return null;
	}

	public static byte[] readLine(byte[] bytes, int fromIndex) {
		List<Byte> list = new ArrayList<>();

		for (int i = fromIndex; i < bytes.length; i++) {
			byte b = bytes[i];

			list.add(b);

			int size = list.size();
			if (2 <= size) {
				char cr = (char) list.get(size - 2).byteValue();
				char lf = (char) list.get(size - 1).byteValue();

				if (cr == '\r' && lf == '\n') {
					break;
				}
			}
		}

		byte[] buffer = new byte[list.size()]; // CRLF の分減らす
		for (int i = 0; i < list.size(); i++) {
			buffer[i] = list.get(i);
		}

		return buffer;
	}

	private int indexOf(byte[] buffer, String boundary, int fromIndex) throws UnsupportedEncodingException {
		byte[] byteBoundary = boundary.getBytes("UTF-8");
		for (int i = fromIndex; i < buffer.length; i++) {
			for (int j = 0; j < boundary.length(); j++) {
				if (buffer[i + j] != byteBoundary[j]) {
					break;
				} else if (j == boundary.length() - 1) {
					return i;
				}
			}
		}
		return -1;
	}

	public String getText() {
		return "[Body text is unimplemented yet!!]";
	}
}
