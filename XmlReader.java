package jp.hiradate.xml;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import jp.hiradate.php.PHPSolver;
import jp.hiradate.php.variable.StringVariable;
import jp.hiradate.php.variable.Variable;

public class XmlReader {
	public static Element domRead(String file) throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = factory.newDocumentBuilder();
		Document document = documentBuilder.parse(file);

		Element root = document.getDocumentElement();

		return root;

	}

	public static Variable getString(Element root, String[] proc, PHPSolver solver) {

		// procの解釈
		String[] nodeStatus = proc[0].split("\\[");

		String nodeName = nodeStatus[0];
		int index = Integer.parseInt((new jp.hiradate.php.Node(nodeStatus[1].substring(0, nodeStatus[1].length() - 1)))
				.calculate(solver).toString());

		// ルート要素の子ノードを取得する
		NodeList rootChildren = root.getChildNodes();

		// ノードの探索
		NodeList personChildren = null;
		for (int i = 0, count = 0; i < rootChildren.getLength(); i++) {
			Node node = rootChildren.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				if (((Element) node).getNodeName().equals(nodeName)) {
					if (count == index) {
						if (proc[1].equals("name")) {
							return new StringVariable(node.getAttributes().getNamedItem(proc[1]).getNodeValue());
						}
						personChildren = node.getChildNodes();
						break;
					}
					count++;
				}
			}
		}

		for (int j = 0; j < personChildren.getLength(); j++) {
			Node personNode = personChildren.item(j);
			if (personNode.getNodeType() == Node.ELEMENT_NODE) {

				if (personNode.getNodeName().equals(proc[1])) {
					return new StringVariable(personNode.getTextContent());
				}
			}
		}

		return null;
	}

	public static int length(Element root) {
		int count = 0;
		// ルート要素の子ノードを取得する
		NodeList rootChildren = root.getChildNodes();

		// ノードの探索
		for (int i = 0; i < rootChildren.getLength(); i++) {
			Node node = rootChildren.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				count++;
			}
		}
		
		return count;
	}
	
	public static void addElement(String fileName, String receiveFileName) throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = factory.newDocumentBuilder();
		Document document = documentBuilder.parse(fileName);
		
		Date day = new Date();
		
		Element file = document.createElement("file");
		file.setAttribute("name", receiveFileName);
		
		Element date = document.createElement("date");
		DateFormat format = new SimpleDateFormat("yy/MM/dd hh:mm:ss");
		
		date.appendChild(document.createTextNode(format.format(day)));
		file.appendChild(date);

		Element desc = document.createElement("desc");
		date.appendChild(document.createTextNode(""));
		file.appendChild(desc);
		

		Element author = document.createElement("author");
		date.appendChild(document.createTextNode(""));
		file.appendChild(author);

		Element root = document.getDocumentElement();
		root.appendChild(file);
		
		System.out.println(length(root));
		
		File xmlFile = new File(fileName);
		write(xmlFile, document);
	}

    public static boolean write(File file, Document document) {

         // Transformerインスタンスの生成
         Transformer transformer = null;
         try {
              TransformerFactory transformerFactory = TransformerFactory.newInstance();
              transformer = transformerFactory.newTransformer();
         } catch (TransformerConfigurationException e) {
              e.printStackTrace();
              return false;
         }

         // Transformerの設定
         transformer.setOutputProperty(OutputKeys.METHOD, "xml");
         transformer.setOutputProperty(OutputKeys.INDENT, "yes");
         transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","4");
         
         // XMLファイルの作成
         try {
              transformer.transform(new DOMSource(document), new StreamResult(file));
         } catch (TransformerException e) {
              e.printStackTrace();
              return false;
         }

         return true;
    }
}