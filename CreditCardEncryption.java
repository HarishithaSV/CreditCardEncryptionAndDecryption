package com.mastercard.developer.creditcard.encryption;

import com.mastercard.developer.encryption.EncryptionException;
import com.mastercard.developer.encryption.FieldLevelEncryption;
import com.mastercard.developer.encryption.FieldLevelEncryptionConfig;
import com.mastercard.developer.encryption.FieldLevelEncryptionConfigBuilder;

import java.util.HashMap;
import java.util.Map;

public class CreditCardEncryptionApp {

    public static void main(String[] args) {
        // Sample credit card information
        String creditCardNumber = "1234567812345678";
        String cvv = "123";
        String expirationDate = "1223";

        // Create FieldLevelEncryptionConfig
        FieldLevelEncryptionConfig config = new FieldLevelEncryptionConfigBuilder()
                .withEncryptionPath("$.creditCardNumber", "$.cvv", "$.expirationDate")
                .build();

        // Initialize FieldLevelEncryption
        FieldLevelEncryption fieldLevelEncryption = new FieldLevelEncryption(config);

        try {
            // Encrypt
            Map<String, String> encryptedData = new HashMap<>();
            encryptedData.put("creditCardNumber", fieldLevelEncryption.encrypt(creditCardNumber));
            encryptedData.put("cvv", fieldLevelEncryption.encrypt(cvv));
            encryptedData.put("expirationDate", fieldLevelEncryption.encrypt(expirationDate));

            System.out.println("Encrypted Data:");
            System.out.println(encryptedData);

            // Decrypt
            String decryptedCreditCardNumber = fieldLevelEncryption.decrypt(encryptedData.get("creditCardNumber"));
            String decryptedCvv = fieldLevelEncryption.decrypt(encryptedData.get("cvv"));
            String decryptedExpirationDate = fieldLevelEncryption.decrypt(encryptedData.get("expirationDate"));

            System.out.println("\nDecrypted Data:");
            System.out.println("Credit Card Number: " + decryptedCreditCardNumber);
            System.out.println("CVV: " + decryptedCvv);
            System.out.println("Expiration Date: " + decryptedExpirationDate);

        } catch (EncryptionException e) {
            e.printStackTrace();
        }
    }
}
