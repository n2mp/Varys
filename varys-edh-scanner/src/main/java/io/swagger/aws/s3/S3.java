/**
 * 
 */
package io.swagger.aws.s3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

/**
 * @author n2mp
 *
 */
public class S3 {

	/**
	 * 
	 */
	public S3() {
		// TODO Auto-generated constructor stub
	}
    /**
     * 
     * @param file
     * @return
     */
	public String getObject(String bucketName, String keyName) {
		String s3StringObject = null;
		
		try {
			AmazonS3 s3Client = AmazonS3ClientBuilder.defaultClient();
			s3StringObject = s3Client.getObjectAsString(bucketName, keyName);
		} catch (AmazonServiceException e) {
		    System.err.println(e.getErrorMessage());
		}
		
		return s3StringObject;
	}

}
