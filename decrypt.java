package decrypt;

import java.io.*;

import org.apache.poi.openxml4j.exceptions.*;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;

class decrypt {
// copied from
// http://stackoverflow.com/questions/1204382/how-can-we-read-protected-password-excel-file-xls-with-poi-api
// depneds on apache-poi org.apache.poi;
public static void readProtectedBinFile(String inputfile, String outputfile, String password) {
    try {

        InputStream inp = new FileInputStream(inputfile);
        // Biff8EncryptionKey not a member of crytpo?
        //org.apache.poi.hssf.record.crypto.Biff8EncryptionKey.setCurrentUserPassword(password); 

        Workbook wb;
        wb = WorkbookFactory.create(inp);

        // Write the output to a file
        FileOutputStream fileOut;
        fileOut = new FileOutputStream(outputfile);
        wb.write(fileOut);
        fileOut.close();
    } catch (InvalidFormatException e) {
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
    // readProtectedBinFile(args[0],args[1],args[2])
   System.out.println(args[1]);
 }
}

}
