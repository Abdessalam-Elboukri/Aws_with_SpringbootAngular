package com.uploadfile.backend.Aws;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.waiters.WaiterResponse;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.HeadObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.waiters.S3Waiter;

public class S3Util {
    private static final String BUCKET = "poudlares";

    public static void uploadFile(String fileName, InputStream inputStream,String folder)
            throws S3Exception, AwsServiceException, SdkClientException, IOException {
        S3Client client = S3Client.builder().build();
        if(folder==null){
            folder="";
        }
        String key = folder+fileName;

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(BUCKET)
                .key(key)
                .acl("public-read")
                .build();

        client.putObject(request,
                RequestBody.fromInputStream(inputStream, inputStream.available()));

        S3Waiter waiter = client.waiter();
        HeadObjectRequest waitRequest = HeadObjectRequest.builder()
                .bucket(BUCKET)
                .key(key)
                .build();

        WaiterResponse<HeadObjectResponse> waitResponse = waiter.waitUntilObjectExists(waitRequest);

        waitResponse.matched().response().ifPresent(x -> {
            // run custom code that should be executed after the upload file exists
            System.out.println("file already exist");
        });
    }

    public static URL getObjectURL(String filename, String folder){
        String key = folder+filename;
        AmazonS3 s3Client = AmazonS3ClientBuilder.defaultClient();
        return s3Client.getUrl(BUCKET,key);
    }
}
