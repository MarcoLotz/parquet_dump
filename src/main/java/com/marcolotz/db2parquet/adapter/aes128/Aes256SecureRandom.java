package com.marcolotz.db2parquet.adapter.aes128;

import java.security.SecureRandom;

/**
 * Helper class that contains a single static instance of SecureRandom.
 * <p>
 * Secure random is thread safe, thus this class and its functions are thread safe.
 */
public class Aes256SecureRandom {

  public static final SecureRandom secureRandom = new SecureRandom();


  /**
   * Fill the bytes from SecureRandom.
   */
  public static final void nextBytes(byte[] bts) {
    secureRandom.nextBytes(bts);
  }
}
