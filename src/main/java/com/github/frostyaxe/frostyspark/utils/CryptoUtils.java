package com.github.frostyaxe.frostyspark.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.apache.commons.codec.binary.Base64;



/**
 * <b>Description:</b> This utility class allows the user to encrypt and the
 * decrypt the secret text.
 * 
 * @author Abhishek Prajapati
 * @since 1.0
 * @author Abhishek Prajapati
 */
public class CryptoUtils
{
	
	/*
	 *  Declaration/Initialization of class/instance variables.
	 */
	private String text;
	private Cipher cipher;
	
	
	
	
	
	/**
	 * <b>Description:</b> This method must be called when user wants to encrypt or
	 * decrypt the secret text. During the encryption, user needs to provide that
	 * secret text that needs to be encrypted or during the decryption, user needs
	 * to provide the encrypted string.
	 * 
	 * @param text : During encryption, it accepts secret text and during
	 *             decryption, it accepts encrypted string.
	 * @return Updated instance of the current class.
	 * @since 1.0
	 * @author Abhishek Prajapati
	 */
	public final CryptoUtils setText(String text)
	{
		this.text = text;
		return this;
	}
	
	
	
	/**
	 * <b>Description:</b> This method generates the key pair and returns the
	 * instance of the key pairs.
	 * 
	 * @return Auto generated key pair.
	 * 
	 * @since 1.0
	 * @author Abhishek Prajapati
	 */
	public final KeyPair keyPairGen()
	{
		try 
		{
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
			keyPairGen.initialize(2048);
			KeyPair pair = keyPairGen.generateKeyPair();
			return pair;
		} 
		catch (NoSuchAlgorithmException e) 
		{
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
	
	
	
	/**
	 * <b>Description:</b> This method needs to be called mandatory in order to
	 * initialize the cipher object. This cipher object can be used for either
	 * encryption or decryption.
	 * 
	 * @return Updated instances of the current class with Cipher object.
	 * 
	 * @since 1.0
	 * @author Abhishek Prajapati
	 */
	public final CryptoUtils initCipher()
	{
		
		try 
		{
			this.cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		} 
		catch (NoSuchAlgorithmException | NoSuchPaddingException e) 
		{
			e.printStackTrace();
		}
		
		return this;
	}
	
	
	
	
	/**
	 * <b>Description:</b> This method encrypts the secret string passed by the user
	 * with the help of auto generated private key. It also generates a public key
	 * file that can be use to decrypt the encrypted string.
	 * 
	 * @param publicKeyPath : Path of the file in which public key will be stored.
	 * 
	 * @since 1.0
	 * @author Abhishek Prajapati
	 */
	public final void encrypt(String publicKeyPath)
	{
		
		KeyPair keypair = keyPairGen();
		try 
		{
			this.cipher.init(Cipher.ENCRYPT_MODE, keypair.getPrivate());
		} 
		catch (InvalidKeyException e) 
		{
			e.printStackTrace();
		}
		
		try 
		{
			System.out.println( "Encrypted String: "  + Base64.encodeBase64String(cipher.doFinal(this.text.getBytes("UTF-8") )));
		} 
		catch (IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		
	      exportPublicKey(publicKeyPath, keypair.getPublic().getEncoded());
		
	}
	
	
	
	/**
	 * <b>Description:</b> This method allows the user to decrypt the encrypted
	 * string with the help of public key file.
	 * 
	 * @param publicKeyFilePath : Path of the public key file.
	 * 
	 * @since 1.0
	 * @author Abhishek Prajaapati
	 */
	public final String decrypt(String publicKeyFilePath)
	{
		PublicKey pubKey = null;
		
		try 
		{
			pubKey = getPublic(publicKeyFilePath);
		}
		catch (NoSuchAlgorithmException | InvalidKeySpecException | IOException e) 
		{
			e.printStackTrace();
		}
		
		try 
		{
			this.cipher.init(Cipher.DECRYPT_MODE, pubKey);
		} 
		catch (InvalidKeyException e) 
		{
			e.printStackTrace();
		}
		
		try 
		{
			return new String(cipher.doFinal(Base64.decodeBase64(text)), "UTF-8");
		} catch (UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	
	/**
	 * <b>Description:</b> This method exports the public key in the file format.
	 * 
	 * @param path : Path of the file in which public key needs to be stored
	 * @param key  : Public key to be stored in the file.
	 * 
	 * @since 1.0
	 * @author Abhishek Prajapati
	 */
	private final void exportPublicKey(String path, byte[] key)
	{
		File publicKeyFile = new File(path);
		publicKeyFile.getParentFile().mkdirs();

		FileOutputStream fos = null;
		try 
		{
			fos = new FileOutputStream(publicKeyFile);
		} 
		catch (FileNotFoundException e1) 
		{
			e1.printStackTrace();
		}
		try 
		{
			fos.write(key);
			fos.flush();
			fos.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * <b>Description:</b> This method returns public key from the file in order to
	 * do the decryption of an encrypted string.
	 * 
	 * @param filename : File path containing the public key.
	 * @return public key extracted from the file.
	 * @throws IOException              : IOException
	 * @throws NoSuchAlgorithmException : When the specified algorithm is not found.
	 * @throws InvalidKeySpecException  : When the specified key is invalid
	 * 
	 * @since 1.0
	 * @author Abhishek Prajapati
	 */
	private final PublicKey getPublic(String filename) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException 
	{
		byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
		X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance("RSA");
		return kf.generatePublic(spec);
	}
	

}
