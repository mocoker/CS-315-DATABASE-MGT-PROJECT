import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class WriteToFile {
	
	// TO WRITE THE CHURCH MEMBER CONTACTS INTO FILE
	public static void writeChurchMemberToFile (String toWrite, File outPutFile) throws IOException	{
		
		if (outPutFile.exists())	{
			FileWriter appendFile = new FileWriter(outPutFile, true);
			BufferedWriter appendToFile = new BufferedWriter(appendFile);
			PrintWriter append_to_file = new PrintWriter(appendToFile);
			append_to_file.println(toWrite);
			append_to_file.close();
		}
		else	{
			outPutFile.createNewFile();
			FileWriter writeFile = new FileWriter(outPutFile);
			BufferedWriter writeToFile = new BufferedWriter(writeFile);
			PrintWriter write_to_file = new PrintWriter(writeToFile);
			write_to_file.println(toWrite);
			write_to_file.close();
		}
	}
	
	// TO WRITE THE CHURCH DONATION INTO FILE
	public static void writeChurchDonationToFile (String toWrite, File outPutFile) throws IOException	{
		
		if (outPutFile.exists())	{
			FileWriter appendFile = new FileWriter(outPutFile, true);
			BufferedWriter appendToFile = new BufferedWriter(appendFile);
			PrintWriter append_to_file = new PrintWriter(appendToFile);
			append_to_file.println(toWrite);
			append_to_file.close();
		}
		else	{
			outPutFile.createNewFile();
			FileWriter writeFile = new FileWriter(outPutFile);
			BufferedWriter writeToFile = new BufferedWriter(writeFile);
			PrintWriter write_to_file = new PrintWriter(writeToFile);
			write_to_file.println(toWrite);
			write_to_file.close();
		}
	}
	
	// TO WRITE DATE OF BIRTH INTO FILE
	public static void writeDateOfBirthToFile(String toWrite, File outPutFile) throws IOException	{
		
		if (outPutFile.exists())	{
			FileWriter appendFile = new FileWriter(outPutFile, true);
			BufferedWriter appendToFile = new BufferedWriter(appendFile);
			PrintWriter append_to_file = new PrintWriter(appendToFile);
			append_to_file.println(toWrite);
			append_to_file.close();
		}
		else	{
			outPutFile.createNewFile();
			FileWriter writeFile = new FileWriter(outPutFile);
			BufferedWriter writeToFile = new BufferedWriter(writeFile);
			PrintWriter write_to_file = new PrintWriter(writeToFile);
			write_to_file.println(toWrite);
			write_to_file.close();
		}
	}	
}		// END OF CLASS
