package com.biriba.autotrader.core.utils.http;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

import com.biriba.autotrader.exceptions.AutoTraderException;

public class HttpExSignature {
    private HttpExSignature() {
        throw new IllegalStateException("Utility class");
    }

    public static String signSHA256RSA(String inputStr, Path privateKeyPath) {
        String sign = "";
        try {
            String inputKey = new String(Files.readAllBytes(privateKeyPath));
            String key = inputKey.replace("-----END PRIVATE KEY-----", "")
                    .replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("\n", "");
    
            byte[] keyBytes = Base64.getDecoder().decode(key);
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
            
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = kf.generatePrivate(spec);
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(privateKey);
            signature.update(inputStr.getBytes(StandardCharsets.UTF_8));
            byte[] s = signature.sign();
            sign = Base64.getEncoder().encodeToString(s);
        } catch (IOException e) {
            throw new AutoTraderException(AutoTraderException.SIGNATURE_ERROR, e.getLocalizedMessage());
        } catch (NoSuchAlgorithmException e) {
            throw new AutoTraderException(AutoTraderException.SIGNATURE_ERROR, "'RSA' crytographic algorithm is not available in the environment.\n" + e.getLocalizedMessage());
        } catch (InvalidKeySpecException e) {
            throw new AutoTraderException(AutoTraderException.SIGNATURE_ERROR, "Invalid key specification.\n" + e.getLocalizedMessage());
        } catch (InvalidKeyException e) {
            throw new AutoTraderException(AutoTraderException.SIGNATURE_ERROR, "Invalid private key.\n" + e.getLocalizedMessage());
        } catch (SignatureException e) {
            throw new AutoTraderException(AutoTraderException.SIGNATURE_ERROR, "Exception generating the signature.\n" + e.getLocalizedMessage());
        }
    return sign.replace("\n", "");
    }

    public static String signSHA256RSA(String data, String secretKey) {
        // NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, SignatureException {
        byte[] hmacSHA256 = {'\0'};
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(),"HmacSHA256");
            mac.init(secretKeySpec);
            hmacSHA256 = mac.doFinal(data.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new AutoTraderException(AutoTraderException.SIGNATURE_ERROR, "'HmacSHA256' crytographic algorithm is not available in the environment.\n" + e.getLocalizedMessage());
        } catch (InvalidKeyException e) {
            throw new AutoTraderException(AutoTraderException.SIGNATURE_ERROR, "Invalid secret key.\n" + e.getLocalizedMessage());
        }
        return new String(Hex.encodeHex(hmacSHA256));
    }   
}
