package com.govtech.todotask;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ReadFiles {

public ReadFiles() {
}

public static void main(String[] args) throws FileNotFoundException {
	
		String filePath = args[0];

		Path p = Paths.get(filePath);//file path to find the TODO
		listFiles(p, "TODO");
}

public static void listFiles(Path dir , String text)
{
   try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(dir))
   {
       for (Path path : directoryStream)
       {
           if (Files.isRegularFile(path) && Files.isReadable(path))
           {
               if(findString(path, text)){
              System.out.println("File path: "+path.toAbsolutePath());

               }
           }
       }
   }
   catch (IOException ex)
   {
       ex.printStackTrace();
   }
}

private static boolean findString(Path file, String text)
	{
		try {
			Scanner sc = new Scanner(file.toAbsolutePath().toFile());
			String input;
			StringBuffer sb = new StringBuffer();
			while (sc.hasNextLine()) {
				input = sc.nextLine();
				sb.append(input + " ");
			}
			if (sb.toString().contains(text))
				return true;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return false;

	}

}
