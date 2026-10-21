package com.dev.resolveja.config.aws;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;

@Configuration 
public class AwsCredentialsConfig {
    
    @Bean 
    public AwsCredentialsProvider awsCredentials() {
        return DefaultCredentialsProvider.builder().build();
    }
}
