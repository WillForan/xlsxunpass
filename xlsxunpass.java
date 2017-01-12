//package xlsxunpass;

import java.io.*;

//import org.apache.poi.openxml4j.exceptions.*; //not needed
//import org.apache.poi.ss.usermodel.*; //not needed
//
//import org.apache.poi.xssf.usermodel.*; //wb
//import org.apache.poi.poifs.crypt.*;//Decryptor, EncryptionInfo
//import org.apache.poi.poifs.filesystem.*; //NPOIFSFileSystem
import java.security.*; //GeneralSecurityException

class xlsxunpass {
/*
 copied from
   http://stackoverflow.com/questions/1204382/how-can-we-read-protected-password-excel-file-xls-with-poi-api
   and 
   http://stackoverflow.com/questions/22144519/password-protect-xssfworkbook-apache-poi
   http://poi.apache.org/encryption.html
*/
  public static void readProtectedBinFile(String inputfile, String outputfile, String password) {
      try {
  
          
          // old xls format
          /*
          InputStream inp = new FileInputStream(inputfile);
          org.apache.poi.hssf.record.crypto.Biff8EncryptionKey.setCurrentUserPassword(password); 
          Workbook wb;
          wb = WorkbookFactory.create(inp);
          */
  
          // read password protected xlsx
          org.apache.poi.poifs.filesystem.NPOIFSFileSystem fs = 
              new org.apache.poi.poifs.filesystem.NPOIFSFileSystem(new File(inputfile), true);
          org.apache.poi.poifs.crypt.EncryptionInfo encInfo = 
              new org.apache.poi.poifs.crypt.EncryptionInfo(fs); 

          org.apache.poi.poifs.crypt.Decryptor d = 
              org.apache.poi.poifs.crypt.Decryptor.getInstance(encInfo); 

          if(!d.verifyPassword(password)) { exiterr("bad password"); }
  
          org.apache.poi.xssf.usermodel.XSSFWorkbook wb = 
              new org.apache.poi.xssf.usermodel.XSSFWorkbook(d.getDataStream(fs));
  
  
          // Write the output to a file
          FileOutputStream fileOut;
          fileOut = new FileOutputStream(outputfile);
          wb.write(fileOut);
          fileOut.close();
          wb.close();
  
      //} catch (InvalidFormatException e) {
      } catch (GeneralSecurityException e) {
          e.printStackTrace();
      } catch (FileNotFoundException e) {
          e.printStackTrace();
      } catch (IOException e) {
          e.printStackTrace();
      }
  
  }
  public static void exiterr(String msg) {
     System.out.println(msg);
     System.exit(1);
  }
  
  // commandline
  public static void main(String[] args) {
   if( args.length != 3) {
       exiterr("arugments: input output password");
   } else if( ! new File(args[0]).isFile() ) {
       exiterr("first argument inputfile must exist");
   } else{ 
     readProtectedBinFile(args[0],args[1],args[2]);
     //System.out.println(args[1]);
   }
  }

}
