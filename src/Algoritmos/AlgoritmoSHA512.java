package Algoritmos;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class AlgoritmoSHA512 implements Algoritmos {
        MessageDigest messageDigest;
        public AlgoritmoSHA512() throws NoSuchAlgorithmException {
            messageDigest = MessageDigest.getInstance("SHA-512");
        }

        public String getSalt(){
            SecureRandom secureRandom = null;
            try {
                secureRandom = SecureRandom.getInstance("SHA1PRNG");
                byte[] salt = new byte[16];
                secureRandom.nextBytes(salt);
                return Hex.encodeHexString(salt);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return "Salt!";
            }
        }

        public String getSaltedPasswordHash(String password, String salt) {
            if (messageDigest == null)
                try {
                    throw new NoSuchAlgorithmException("No existe el algoritmo de hash");
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            messageDigest.update(salt.getBytes());
            byte[] bytes = messageDigest.digest(password.getBytes());
            return new String(Hex.encodeHex(bytes));
        }
        public boolean verificarSaltedPassword(String password, String salt, String passwordHash) throws NoSuchAlgorithmException {
            String nuevoHash = getSaltedPasswordHash(password,salt);
            return nuevoHash.equals(passwordHash);
}


}

