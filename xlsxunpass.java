//package xlsxunpass;
// HSSF is xls <2007
// XSSF is xlsx (OLE/xml) >= 2007

import java.io.*;

import org.apache.poi.openxml4j.exceptions.*; //not needed
import org.apache.poi.ss.usermodel.*; //not needed
//
import org.apache.poi.xssf.usermodel.*; //wb
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import java.security.GeneralSecurityException;

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

          // read password protected xlsx
          POIFSFileSystem fs = new POIFSFileSystem(new File(inputfile), true);
          EncryptionInfo encInfo = new EncryptionInfo(fs);
          Decryptor d = Decryptor.getInstance(encInfo);
          if(!d.verifyPassword(password)) { exiterr("bad password"); }
          XSSFWorkbook wb = new XSSFWorkbook(d.getDataStream(fs));

          writeNopass(wb, outputfile);

      } catch(OfficeXmlFileException e) {
        /*  FileInputStream fs = new FileInputStream(new File(inputfile));
          var info = new EncryptionInfo(fs);
          Decryptor d = Decryptor.getInstance(info);
          XSSFWorkbook wb = new XSSFWorkbook(d.getDataStream(fs));
          writeNopass(wb, outputfile);

          EncryptionInfo info = new EncryptionInfo(filesystem);
          Decryptor d = Decryptor.getInstance(info);
          d.verifyPassword(password) InputStream dataStream = d.getDataStream(filesystem);

              //rmpass(encInfo, password, outputfile);
          e.printStackTrace();

         */
          e.printStackTrace();

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
  public static void unpassxls(String inputfile, String outputfile, String password) {
     // xls instead of xlsx
     try {
          InputStream inp = new FileInputStream(inputfile);
          org.apache.poi.hssf.record.crypto.Biff8EncryptionKey.setCurrentUserPassword(password);
          Workbook wb = WorkbookFactory.create(inp);
          writeNopass(wb, outputfile);
      } catch (FileNotFoundException e) {
          e.printStackTrace();
      } catch (IOException e) {
          e.printStackTrace();
      }
  }

  public static void writeNopass(Workbook wb, String outputfile) throws java.io.IOException {
      // Write the output to a file
      FileOutputStream fileOut;
      fileOut = new FileOutputStream(outputfile);
      wb.write(fileOut);
      fileOut.close();
      wb.close();
  }
  
  // commandline
  public static void main(String[] args) {
   // input issues
   if( args.length != 3) { exiterr("arugments: input output password"); }
   if( ! new File(args[0]).isFile() ) { exiterr("first argument inputfile must exist"); }

   // xlsx vs xls
   if( args[0].substring( args[0].length() - 4) == ".xls" ) {
      System.out.println("trying old 2003 format xls");
      unpassxls(args[0],args[1],args[2]);
   } else {
      readProtectedBinFile(args[0],args[1],args[2]);
   }
  }

}
