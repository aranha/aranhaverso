package br.com.gastronomia.util;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * Classe para fazer a criptografia de strings.
 * 
 * @author Rodrigo Machado - rodrigo.domingos@acad.pucrs.br
 * @since 09/06/2017
 * 
 **/
public class EncryptUtil {
	public static String encrypt2(String str) throws NoSuchAlgorithmException {
		// Create MessageDigest instance for MD5
		MessageDigest md = MessageDigest.getInstance("MD5");

		// Add password bytes to digest
		md.update(str.getBytes());

		// Get the hash's bytes
		byte[] bytes = md.digest();

		// This bytes[] has bytes in decimal format;
		// Convert it to hexadecimal format
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}

		// Get complete hashed password in hex format
		return sb.toString();
	}

	public static String encrypt(String str) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = EncryptUtil.getCipher();
		// encrypt the text
		cipher.init(Cipher.ENCRYPT_MODE, EncryptUtil.getKey());

		byte[] encrypted = cipher.doFinal(str.getBytes()); 

		StringBuilder sb = new StringBuilder();
		for (byte b: encrypted) {
			sb.append((char)b);
		}

		return sb.toString();
	}

	public static String decrypt(String str) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		Cipher cipher = EncryptUtil.getCipher();
		// decrypt the text
		cipher.init(Cipher.DECRYPT_MODE, EncryptUtil.getKey());

		byte[] bb = new byte[str.length()];
		for (int i=0; i<str.length(); i++) {
			bb[i] = (byte) str.charAt(i);
		}

		return new String(cipher.doFinal(bb)); 
	}

	public static Cipher getCipher() throws NoSuchAlgorithmException, NoSuchPaddingException {
		return Cipher.getInstance("AES");
	}

	public static Key getKey() {
		String key = "Bar123Bar123Bar1"; // 128 bit key
		// Create key
		return new SecretKeySpec(key.getBytes(), "AES");
	}

}
