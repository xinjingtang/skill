package com.group.integrate.io;//: io/DirectoryDemo.java
// Sample use of Directory utilities.
import java.io.*;

import com.group.integrate.util.io.Directory;
import com.group.integrate.util.io.PPrint;

import static com.group.integrate.util.io.Print.*;

public class DirectoryDemo {
  public static void main(String[] args) {
    // All directories:
    PPrint.pprint(Directory.walk(".").dirs);
    // All files beginning with 'T'
    for(File file : Directory.local(".", ".*\\.xml"))
      print(file);
    print("----------------------");
    // All Java files beginning with 'T':
    for(File file : Directory.walk(".", ".*.*\\.java"))
      print(file);
    print("======================");
    // Class files containing "Z" or "z":
    for(File file : Directory.walk(".",".*[Zz].*\\.class"))
      print(file);
  }
} /* Output: (Sample)
[.\xfiles]
.\TestEOF.class
.\TestEOF.java
.\TransferTo.class
.\TransferTo.java
----------------------
.\TestEOF.java
.\TransferTo.java
.\xfiles\ThawAlien.java
======================
.\FreezeAlien.class
.\GZIPcompress.class
.\ZipCompress.class
*///:~
