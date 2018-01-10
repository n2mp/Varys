/**
 * 
 */
package io.swagger.service;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import io.swagger.aws.s3.S3;
import io.swagger.model.Attribute;

/**
 * @author n2mp
 *
 */
public class EdhScannerService {

	/**
	 * 
	 */
	public EdhScannerService() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Attribute> getFileAttributes() {
		
		String bucketName = "pawlowski-comcast";
		String keyName = "varys/Varys.jmx";

		// debugging.
		List<Attribute> attributes = new ArrayList<Attribute>();

		for (int i = 0; i < 10; i++) {
			Attribute attribute = new Attribute().value(UUID.randomUUID() + "");
			attribute.setKey("" + (int) (Math.random() * 1000));

			attributes.add(attribute);
		}

		// Make a call to retrieve the S3 object
		String s3Object = new S3().getObject(bucketName, keyName);

		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder;
		try {
			docBuilder = docBuilderFactory.newDocumentBuilder();
			Document document = docBuilder.parse(new InputSource(new StringReader(s3Object)));

			System.out.println("As XML -> " + document.toString());
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return attributes;
	}
	
	/**
	 * 
	 * @param bucket
	 * @param key
	 * @return
	 */
	public List<Attribute> getAttributes(String bucket, String key) {

		System.err.println("Query parameters: bucket["+bucket+"] key["+key+"]");
		
		// Make a call to retrieve the S3 object
		String s3Object = new S3().getObject(bucket, key);

		System.err.println("S3 Object:");
		System.err.println(s3Object);
				
		// debugging.
		List<Attribute> attributes = new ArrayList<Attribute>();
		attributes.add(new Attribute().key("Hello").value("World"));
		attributes.add(new Attribute().key("Date").value(new Date().toString()));
		attributes.add(new Attribute().key("S3-Object").value(s3Object));

		return attributes;
	}

}
