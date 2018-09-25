package test;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class UkeyTest {


	public static byte[] serverGenerateDigest2(String secKey, byte[] random) throws Exception {
		SecretKey sk = new SecretKeySpec(secKey.getBytes(),"HmacMD5");
	    Mac mac = Mac.getInstance("HmacMD5");
	    mac.init(sk);
	    byte[] digest = mac.doFinal(random);
	    return digest;
	}

	public static void main(String[] args) throws Exception {
		serverGenerateDigest2("safdsafd","sadfasfda".getBytes());
	}

}
