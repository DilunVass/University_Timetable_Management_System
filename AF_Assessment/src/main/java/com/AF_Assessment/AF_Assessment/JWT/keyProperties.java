package com.AF_Assessment.AF_Assessment.JWT;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@ConfigurationProperties(prefix = "rsa")
public record keyProperties(RSAPrivateKey privateKey, RSAPublicKey publicKey) {
}
