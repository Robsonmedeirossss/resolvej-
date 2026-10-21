package com.dev.resolveja.config.aws;

import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3AsyncClient;

@Configuration 
@RequiredArgsConstructor 
public class S3Config {

    private final AwsCredentialsProvider awsCredentials;
    
    public S3AsyncClient s3Client() {
        return S3AsyncClient.builder()
            .region(Region.US_EAST_1)
            .credentialsProvider(awsCredentials)
        .build();
    }
}
