import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;


public class Connect	{
	
	//private static String url = "jdbc:mysql://localhost:3306/mocoker_final_project_db";

	private static final String url = "jdbc:mysql://localhost:3306/mocoker_final_project_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=US/Central";

	private static String user = "student";
	private static String password = "student";
	private static Connection myConn = null;
   
	// This method returns a connection to your database

	public static Connection getConnection() throws SQLException	{
      
		try   {
      
			myConn = DriverManager.getConnection(url, user, password);
      
		}	catch(SQLException e)	{
				e.printStackTrace();
			}
		return myConn;
	}
   
	// THIS METHOD IS JUST TO TEST CONNECTION..... DON'T CALL THIS METHOD *********************************
	public static void test()	{
		try   {
			Connection myConn = getConnection();
			if (myConn != null)
				System.out.println("Connection successful!!!!!!");
				myConn.close();
		}	catch(SQLException e)	{
            	e.printStackTrace();
			}
	}
   
	// Close Connection on Logout
	public static void close(){
		try   {
			myConn.close();
      
		}	catch(SQLException e)	{
				e.printStackTrace();
			}
	}
	
	// THIS METHOD IS TO GET ADMIN USER LOGIN
	public static String getAdminUser(String user) throws SQLException	{
		String login = null;
		String uName = null;
		String pWord = null;
		PreparedStatement sqlQ = null;
		ResultSet result = null;
		
		sqlQ = myConn.prepareStatement("SELECT user_Name, pass_Word FROM Admin_Users WHERE user_Name=?"); 		//PREPARED STATEMENT
		
		sqlQ.setString(1, user);			//SET THE PARAMETERS
		
		result = sqlQ.executeQuery();		//EXECUTE THE QUERY
		
		while (result.next())	{
			uName = result.getString(1);
			pWord = result.getString(2);
		}
			
		login = uName + " " + pWord;
		return login;	
	}
	
	//THIS METHOD IS TO VIEW CHURCH MEMBER CONTACT BEFORE INSERT ##################################333
			public static void viewMemberContactB4Insert() throws SQLException, IOException	{
				
				String sqlSelect = "SELECT Member_ID, Full_Name, Date_of_Birth, Contact_Address, Phone_Number, E_Mail, Notes FROM Church_Member_Contacts";
				
				Statement selectSQL = myConn.createStatement();		
				ResultSet result = selectSQL.executeQuery(sqlSelect);
				
				MainApp.tableViewMemberContactB4Insert.setModel(DbUtils.resultSetToTableModel(result));
			}
	
	// THIS METHOD IS TO INSERT INTO CHURCH MEMBER CONTACT INFORMATION
	public static boolean insertChurchMemberInfo(String M_ID, String f_N, Date DOB, String CA, String P, String E, String notes, String DB_U) throws SQLException	{
		
		String sqlIns = "INSERT INTO Church_Member_Contacts (Member_ID, Full_Name, Date_of_Birth, Contact_Address, Phone_Number, E_Mail, Notes, Admin_Users) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement sqlQ = myConn.prepareStatement(sqlIns);
		sqlQ.setString(1, M_ID);
		sqlQ.setString(2, f_N);
		sqlQ.setDate(3, DOB);
		sqlQ.setString(4, CA);
		sqlQ.setString(5, P);
		sqlQ.setString(6, E);
		sqlQ.setString(7, notes);
		sqlQ.setString(8, DB_U);
		
		int rowsInserted = sqlQ.executeUpdate();
		if (rowsInserted > 0) {
			return true;
		}
		return false;
	}
	
	//THIS METHOD IS TO VIEW CHURCH DONATIONS BEFORE INSERT ##################################333
		public static void viewDonationB4Insert() throws SQLException, IOException	{
			
			String sqlSelect = "SELECT Donation_ID, Full_Name, Phone_Number, E_Mail, Date_of_Donation, Member_ID FROM Church_Donations";
			
			Statement selectSQL = myConn.createStatement();		
			ResultSet result = selectSQL.executeQuery(sqlSelect);
			
			MainApp.tableViewDonationB4Insert.setModel(DbUtils.resultSetToTableModel(result));
		}
	
	// THIS METHOD IS TO INSERT INTO CHURCH DONATION
	public static boolean insertChurchDonationInfo(String D_ID, String f_N, BigDecimal cashD, String N_cashD, String P, String E, Date dateOfD, String M_ID, String DB_User) throws SQLException	{
		
		String sqlIns = "INSERT INTO Church_Donations (Donation_ID, Full_Name, Cash_Donations, Non_Cash_Donation, Phone_Number, E_Mail, Date_of_Donation, Member_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement sqlQ = myConn.prepareStatement(sqlIns);
		sqlQ.setString(1, D_ID);
		sqlQ.setString(2, f_N);
		sqlQ.setBigDecimal(3, cashD);
		sqlQ.setString(4, N_cashD);
		sqlQ.setString(5, P);
		sqlQ.setString(6, E);
		sqlQ.setDate(7, dateOfD);
		sqlQ.setString(8, M_ID);
			
		int rowsInserted = sqlQ.executeUpdate();
		if (rowsInserted > 0)	{
			String donor = "Church Donor";
			
			String sqlUpD = "UPDATE Church_Member_Contacts SET Notes = ?, Admin_Users = ? WHERE Member_ID = ?";
			
			PreparedStatement sqlUp = myConn.prepareStatement(sqlUpD);
			
			sqlUp.setString(1, donor);
			sqlUp.setString(2, DB_User);
			sqlUp.setString(3, M_ID);
			
			int rowsUpdated = sqlUp.executeUpdate();
			if (rowsUpdated > 0)	{
				JOptionPane.showMessageDialog(null, "Master Contact List Successfully Updated ! ! !");								//MESSAGE POP UP IF MASTER TABLE WAS SUCCESSFULLY UPDATED
			}
			return true;
		}
		return false;
	}
	
	//THIS METHOD IS TO SELECT FROM CHURCH MEMBER CONTACT TABLE * * * MASTER LIST * * *
	public static void selectFromMasterList() throws SQLException, IOException	{
		String sqlSelect = "SELECT Member_ID, Full_Name, Date_of_Birth, Contact_Address, Phone_Number, E_Mail, Notes FROM Church_Member_Contacts";
				
		Statement selectSQL = myConn.createStatement();
		ResultSet result = selectSQL.executeQuery(sqlSelect);
		
		MainApp.tableMasterList.setModel(DbUtils.resultSetToTableModel(result));
	}
	
	//THIS METHOD IS TO VIEW CHURCH DONORS FROM THE  * * * MASTER LIST * * *
	public static void viewChurchDonors(String note) throws SQLException, IOException	{
		PreparedStatement sqlQ = null;
		ResultSet result = null;
		
		String sqlSelect = "SELECT Member_ID, Full_Name, Date_of_Birth, Contact_Address, Phone_Number, E_Mail, Notes FROM Church_Member_Contacts WHERE Notes = ?";
		
		sqlQ = myConn.prepareStatement(sqlSelect); 		//PREPARED STATEMENT
		sqlQ.setString(1, note);							//SET THE PARAMETERS
		result = sqlQ.executeQuery();
		
		MainApp.tableMasterList.setModel(DbUtils.resultSetToTableModel(result));
	}
	
	//THIS METHOD IS TO VIEW CHURCH DONATIONS
	public static void viewChurchDonations() throws SQLException, IOException	{
		
		String sqlSelect = "SELECT Donation_ID, Full_Name, Phone_Number, E_Mail, Date_of_Donation, Member_ID FROM Church_Donations";
		
		Statement selectSQL = myConn.createStatement();		
		ResultSet result = selectSQL.executeQuery(sqlSelect);
		
		MainApp.tableMasterList.setModel(DbUtils.resultSetToTableModel(result));
	}
	// * * * * * METHOD TO CHECK IF MASTER LIST IS NOT NULL * * * * *
	public static boolean checkMasterList()	{
			
		if (MainApp.tableMasterList.getRowCount() > 0)	{
			return true;	
		}
		return false;
	}
	
	//THIS METHOD IS TO SELECT DATA FROM THE MASTER LIST TO EDIT AND UPDATE
	public static boolean selectDataToUpdate(String memID) throws SQLException	{
		String f_Name = null;
		String DOB = null;
		String contactAddy = null;
		String phoneNo = null;
		String eMail = null;
		
		PreparedStatement sqlQ = null;
		ResultSet result = null;
		
		sqlQ = myConn.prepareStatement("SELECT Full_Name, Date_of_Birth, Contact_Address, Phone_Number, E_Mail FROM Church_Member_Contacts WHERE Member_ID = ?"); 		//PREPARED STATEMENT
		
		sqlQ.setString(1, memID);			//SET THE PARAMETERS
		
		result = sqlQ.executeQuery();			//EXECUTE THE QUERY
		
		while (result.next())	{
			f_Name = result.getString(1);
			DOB = result.getString(2);
			contactAddy = result.getString(3);
			phoneNo = result.getString(4);
			eMail = result.getString(5);
		}
		
		MainApp.textField_1EditFullName.setText(f_Name);
		MainApp.textField_2EditDOB.setText(DOB);
		MainApp.textFieldEditContactAdd.setText(contactAddy);
		MainApp.textField_3EditPhoneNo.setText(phoneNo);
		MainApp.textField_4EditEMail.setText(eMail);
				
		if (f_Name.equals(null)) {
			return false;
		}
		return true;
	}
	
	//THIS METHOD IS TO UPDATE THE DATA IN THE CHURCH MEMBER CONTACT LIST
	public static boolean updateChurchMemberContact(String f_N, Date DOB, String CA, String P, String E, String searchMemberID) throws SQLException	{
		
		String sqlUpD = "UPDATE Church_Member_Contacts SET Full_Name = ?, Date_of_Birth = ?, Contact_Address = ?, Phone_Number = ?, E_Mail = ? WHERE Member_ID = ?";
		
		PreparedStatement sqlUp = myConn.prepareStatement(sqlUpD);
		
		sqlUp.setString(1, f_N);
		sqlUp.setDate(2, DOB);
		sqlUp.setString(3, CA);
		sqlUp.setString(4, P);
		sqlUp.setString(5, E);
		sqlUp.setString(6, searchMemberID);
		
		int rowsUpdated = sqlUp.executeUpdate();
		if (rowsUpdated > 0)	{
			return true;
		}
	return false;
	}
	
	//THIS METHOD IS TO SELECT BIRTHDAY FROM CHURCH MEMBER CONTACT TABLE
	public static void searchForDateOfBirth(String DOB) throws SQLException, IOException	{
		PreparedStatement sqlQ = null;
		ResultSet result = null;
		
		String sqlDOB = "SELECT Member_ID, Full_Name, Date_of_Birth, Phone_Number, E_Mail FROM Church_Member_Contacts WHERE Date_of_Birth LIKE ?";
		
		sqlQ = myConn.prepareStatement(sqlDOB); 		//PREPARED STATEMENT
		
		sqlQ.setString(1, DOB);							//SET THE PARAMETERS
		
		result = sqlQ.executeQuery();
		
		MainApp.tableBirthday.setModel(DbUtils.resultSetToTableModel(result));
	}
	// * * * * * METHOD TO CHECK IF BIRTHDAY INFORMATION IS NOT NULL * * * * *
	public static boolean checkMonthlyBirthdayCelebration()	{
		
		if (MainApp.tableBirthday.getRowCount() > 0)	{
			return true;	
		}
		return false;
	}
	
	// TO DELETE FROM THE CHURCH MEMBER CONTACT LIST			- THE MASTER LIST							// DELETE FROM MASTER LIST
	public static boolean deleteChurchMember (String memID_toDelete) throws SQLException	{
		
		String sqlToDel = "DELETE FROM Church_Member_Contacts WHERE Member_ID = ?";
		
		PreparedStatement sqlDel = myConn.prepareStatement(sqlToDel);
		
		sqlDel.setString(1, memID_toDelete);
		
		int rowsDeleted = sqlDel.executeUpdate();
		if (rowsDeleted > 0) {
		    return true;
		}
		return false;
	}
	
	// TO DELETE FROM THE CHURCH DONATION LIST																// DELETE FROM CHURCH DONAIION LIST
	public static boolean deleteChurchDonation (String donationID) throws SQLException	{
		
		String sqlToDelDon = "DELETE FROM Church_Donations WHERE Donation_ID = ?";
		
		PreparedStatement sqlDelDon = myConn.prepareStatement(sqlToDelDon);
		
		sqlDelDon.setString(1, donationID);
		
		int rowsDeleted = sqlDelDon.executeUpdate();
		if (rowsDeleted > 0) {
		    return true;
		}
		return false;
	}
	
	//TO EXPORT MAIN LIST TO FILE ************************************************
	public static void exportMainListToFile() throws SQLException, IOException	{
		
		File outPutFile = new File("Church_Member_Contact.csv");						//THE OUTPUT FILE
		
		String sqlSelect = "SELECT Member_ID, Full_Name, Date_of_Birth, Contact_Address, Phone_Number, E_Mail, Notes FROM Church_Member_Contacts";
		
		Statement selectSQL = myConn.createStatement();
		ResultSet result = selectSQL.executeQuery(sqlSelect);

		while (result.next())	{
			String memID = result.getString(1);
			String fullName = result.getString(2);
		    String DOB = result.getString(3);
		    String contactAddress = result.getString(4);
		    String phoneNo = result.getString(5);
		    String eMail = result.getString(6);
		    String notes = result.getString(7);
			    	    
		    String toWrite = memID + " " + fullName + " " + DOB + " " + contactAddress + " " + phoneNo + " " + eMail + " " + notes; // THE CONCATENATED RESULT FOR OUTPUT
			    
		    WriteToFile.writeChurchMemberToFile(toWrite, outPutFile);
		}
		Connect.checkChurchMemberContactFile();
	}
	// * * * * * METHOD TO CHECK IF FILE EXPORT IS SUCCESSFUL FOR CHURCH MEMBER CONTACT * * * * *
	private static void checkChurchMemberContactFile()	{
				
		File checkFile = new File("Church_Member_Contact.csv");
		
		if (checkFile.exists())	{
			if (checkFile.length() > 0)	{
				JOptionPane.showMessageDialog(null, "Successfully Exported to the Working Folder as Church_Member_Contact.csv");
			}
		}
		else	{
			JOptionPane.showMessageDialog(null, "File Does Not Exist OR No Data Entry in Database ! ! !");
		}
	}
		
	//TO EXPORT CHURCH DONATIONS TO FILE *******************************************************************
	public static void exportChurchDonationToFile() throws SQLException, IOException	{
		
		File outPutFile = new File("Church_Donation.csv");							//THE OUTPUT FILE
		
		String sqlSelect = "SELECT Donation_ID, Full_Name, Cash_Donations, Non_Cash_Donation, Phone_Number, E_Mail, Date_of_Donation, Member_ID FROM Church_Donations";
			
		Statement selectSQL = myConn.createStatement();
		ResultSet result = selectSQL.executeQuery(sqlSelect);
		
		while (result.next())	{
			String D_ID = result.getString(1);
			String fullName = result.getString(2);
			String cashD = result.getString(3);
			String nonCashD = result.getString(4);
			String phoneNo = result.getString(5);
			String eMail = result.getString(6);
			String dateOFD = result.getString(7);
			String memID = result.getString(8);
				
			String toWrite = D_ID + " " + fullName + " " + cashD + " " + nonCashD + " " + phoneNo + "  " + eMail + " " + dateOFD + " " + memID;		// THE CONCATENATED RESULT FOR OUTPUT
				
			WriteToFile.writeChurchDonationToFile(toWrite, outPutFile);
		}
		Connect.checkChurchDonationFile();
	}
	// * * * * * METHOD TO CHECK IF FILE EXPORT IS SUCCESSFUL FOR CHURCH DONATION * * * * *
	private static void checkChurchDonationFile()	{
			
		File checkFile = new File("Church_Donation.csv");
			
		if (checkFile.exists())	{
			if (checkFile.length() > 0)	{
				JOptionPane.showMessageDialog(null, "Success ! ! ! Exported to the Working Folder as Church_Donation.csv");
			}
		}
		else	{
			JOptionPane.showMessageDialog(null, "File Does Not Exist OR No Data Entry in Database ! ! !");
		}
	}
	
	//TO EXPORT MONTHLY BIRTHDAY TO FILE ************************************************
	public static void exportMontlyBDayToFile(String DOB) throws SQLException, IOException	{
		
		File outPutFile = new File("MontlyBirthdayCelebration.csv");						//THE OUTPUT FILE
		
		String sqlSelect = "SELECT Full_Name, Date_of_Birth, Phone_Number, E_Mail FROM Church_Member_Contacts WHERE Date_of_Birth LIKE ?";
		
		PreparedStatement sqlQ = myConn.prepareStatement(sqlSelect); 		//PREPARED STATEMENT
		
		sqlQ.setString(1, DOB);	
		
		ResultSet result = sqlQ.executeQuery();

		while (result.next())	{
			String fullName = result.getString(1);
		    String DOBirth = result.getString(2);
		    String phoneNo = result.getString(3);
		    String eMail = result.getString(4);
		     	    
		    String toWrite = fullName + " " + DOBirth + " " + phoneNo + " " + eMail; 		// THE CONCATENATED RESULT FOR OUTPUT
			    
		    WriteToFile.writeDateOfBirthToFile(toWrite, outPutFile);
		}
		Connect.checkMonthlyBirthdayFile();
	}
	// * * * * * METHOD TO CHECK IF FILE EXPORT IS SUCCESSFUL FOR MONTHLY BIRTHDAY * * * * *
	private static void checkMonthlyBirthdayFile()	{
				
		File checkFile = new File("MontlyBirthdayCelebration.csv");
		
		if (checkFile.exists())	{
			if (checkFile.length() > 0)	{
				JOptionPane.showMessageDialog(null, "Successfully Exported to the Working Folder as MontlyBirthdayCelebration.csv");
			}
		}
		else	{
			JOptionPane.showMessageDialog(null, "File Does Not Exist OR No Data Entry in Database ! ! !");
		}
	}
	
	// TO DELETE EXISTING FILES IF IT EXIST TO AVOID DUPLICATIONS
	public static void deleteAllFiles()	{
		
		File f1 = new File("Church_Member_Contact.csv");
		File f2 = new File("Church_Donation.csv");
		
		if (f1.exists() || f2.exists())	{
			f1.delete();
			f2.delete();
			
			JOptionPane.showMessageDialog(null, "All Existing Files Deleted - Now You can Export New Files ! ! !");
		}
		else	{
			JOptionPane.showMessageDialog(null, "No Existing Files - No Files Deleted ! ! !");
		}	
	}
	
	// TO DELETE EXISITING MONTHLY BIRTHDAY FILE IF IT EXISTS TO AVOID DUPLICATIONS
	public static void deleteBirthdayFile()	{
		
		File f1 = new File("MontlyBirthdayCelebration.csv");
		
		if (f1.exists())	{
			f1.delete();
			JOptionPane.showMessageDialog(null, "Existing Files Deleted - Now You can Export New File ! ! !");
		}
		else	{
			JOptionPane.showMessageDialog(null, "No Existing File - No File Deleted ! ! !");
		}	
	}
	
	//THIS METHOD IS TO VIEW FROM CHURCH MEMBER CONTACT TABLE * * * MASTER LIST * * * TO DELETE
	public static void selectFromMasterListToDel() throws SQLException, IOException	{
		String sqlSelect = "SELECT Member_ID, Full_Name, Date_of_Birth, Contact_Address, Phone_Number, E_Mail, Notes FROM Church_Member_Contacts";
				
		Statement selectSQL = myConn.createStatement();
		ResultSet result = selectSQL.executeQuery(sqlSelect);
		
		MainApp.tableViewToDelete.setModel(DbUtils.resultSetToTableModel(result));
	}
	
	//THIS METHOD IS TO VIEW FROM CHURCH DONATIONS TO DELETE
	public static void viewChurchDonationsToDel() throws SQLException, IOException	{
		
		String sqlSelect = "SELECT Donation_ID, Full_Name, Cash_Donations, Non_Cash_Donation, Phone_Number, E_Mail, Date_of_Donation, Member_ID FROM Church_Donations";
		
		Statement selectSQL = myConn.createStatement();		
		ResultSet result = selectSQL.executeQuery(sqlSelect);
		
		MainApp.tableViewToDelete.setModel(DbUtils.resultSetToTableModel(result));
	}
	// * * * * * METHOD TO CHECK IF MASTER LIST IS NOT NULL * * * * *
	public static boolean checkMasterListDel()	{
			
		if (MainApp.tableViewToDelete.getRowCount() > 0)	{
			return true;	
		}
		return false;
	}
}		// END OF CLASS