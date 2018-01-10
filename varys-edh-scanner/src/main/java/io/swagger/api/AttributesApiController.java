package io.swagger.api;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import io.swagger.annotations.ApiParam;
import io.swagger.aws.s3.S3;
import io.swagger.model.Attribute;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-12-07T17:59:04.303-07:00")

@Controller
public class AttributesApiController implements AttributesApi {

	/**
	 * 
	 */
	public ResponseEntity<List<Attribute>> attributesFileGet(
			@ApiParam(value = "Amazon Web Services (AWS)  Simple Storage Service (S3) Amazon Resource Name(ARN).  For example, arn:aws:s3:::my_data_bucket/data-file.tdo", required = true) @PathVariable("file") String file) {
		
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

		// Make a call to the TDF library to extract the TDO and Payload EDHs
		// Extract the fields from the EDH
		// Pack fields as key/value pairs into the Attributes list.

		ResponseEntity<List<Attribute>> resp = new ResponseEntity<List<Attribute>>(attributes, HttpStatus.OK);
		// ResponseEntity<List<Attribute>> resp =
		// ResponseEntity.status(HttpStatus.NOT_FOUND).build();

		return resp;
	}

	/**
	 * 
	 * @param bucket
	 * @param key
	 * @return
	 */
	public ResponseEntity<List<Attribute>> attributesGet(
			@NotNull @ApiParam(value = "Amazon Web Services (AWS)  Simple Storage Service (S3) bucket name.", required = true) @RequestParam(value = "bucket", required = true) String bucket,
			@NotNull @ApiParam(value = "Amazon Web Services (AWS)  Simple Storage Service (S3) object key name.", required = true) @RequestParam(value = "key", required = true) String key) {

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


		return new ResponseEntity<List<Attribute>>(attributes, HttpStatus.OK);
	}

}