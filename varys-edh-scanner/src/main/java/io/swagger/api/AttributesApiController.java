package io.swagger.api;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.ApiParam;
import io.swagger.model.Attribute;
import io.swagger.service.EdhScannerService;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-12-07T17:59:04.303-07:00")

@Controller
public class AttributesApiController implements AttributesApi {

	/**
	 * 
	 */
	public ResponseEntity<List<Attribute>> attributesFileGet(
			@ApiParam(value = "Amazon Web Services (AWS)  Simple Storage Service (S3) Amazon Resource Name(ARN).  For example, arn:aws:s3:::my_data_bucket/data-file.tdo", required = true) @PathVariable("file") String file) {
		
		// debugging.
		List<Attribute> attributes = new EdhScannerService().getFileAttributes();
		ResponseEntity<List<Attribute>> resp = new ResponseEntity<List<Attribute>>(attributes, HttpStatus.OK);

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
		
		// debugging.
		List<Attribute> attributes = new EdhScannerService().getAttributes(bucket, key);
		ResponseEntity<List<Attribute>> resp = new ResponseEntity<List<Attribute>>(attributes, HttpStatus.OK);

		return resp;
	}

}