package crypto;

import java.io.*;
import java.util.*;
import utilities.string.*;

public class GNUPGCryptographicEngine {

  private String sep = System.getProperty("file.separator");
  private String gpgPath;

  public GNUPGCryptographicEngine() {
  }

  public boolean setupWin32(String gnugpPath, String keyPairPath){
    System.out.println();
    boolean setupOK = true;
    // Ensure GnuPG is present
    boolean GNUPGFound =
        new File(gnugpPath + sep + "gpg.exe").exists() &&
        new File(gnugpPath + sep + "gpgv.exe").exists() &&
        new File(gnugpPath + sep + "iconv.dll").exists();

    // Ensure a key pair is found (secret and public keys)
    boolean keyPairFound =
        new File(keyPairPath + sep + "secring.gpg").exists() &&
        new File(keyPairPath + sep + "pubring.gpg").exists();

    if(GNUPGFound && keyPairFound){
      System.out.println("GnuPG Cryptographic files found... OK");
      System.out.println("Key pair files found... OK");

      this.gpgPath = gnugpPath;

      // Import the key pair so that it can be used by GnuPG
      try{
        Process p = Runtime.getRuntime().exec(gnugpPath + sep + "gpg.exe --import " + keyPairPath + sep + "secring.gpg");
        BufferedReader bR = new BufferedReader(new InputStreamReader(p.getErrorStream()));
        String line;
        while((line = bR.readLine()) != null){
          System.out.println(line);
        }
      }
      catch(Exception e){
        setupOK = false;
        System.out.println("Exception with runtime: " + e);
      }
    }
    else setupOK = false;
    System.out.println();
    return setupOK;
  }

  public String[] decrypt(String cypherTextFileName){
    Vector lines = new Vector();
    try{
      // These are what the program needs
      String appName = "gpg.exe";
      String parameters = " --passphrase-fd 0 --batch";
      String recipient = " -r NextremQuote";
      String fileName = " " + cypherTextFileName;

      Process p = Runtime.getRuntime().exec(this.gpgPath + sep + appName + parameters + recipient + fileName);

      BufferedWriter out = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
      try{
        out.write("nextremquote1969");
        out.close();
      }
      catch(IOException io){
        System.out.println("Exception writing to the process the passphrase: " + io);
      }


      BufferedReader bR = new BufferedReader(new InputStreamReader(p.getErrorStream()));
      String line;
      while((line = bR.readLine()) != null){
        System.out.println(line);
        lines.add(line);
      }
    }
    catch(Exception e){
      System.out.println("Exception with runtime: " + e);
    }
    return new StringManipulationToolkit().createArrayFromVector(lines);
  }

}
