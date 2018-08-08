import java.awt.EventQueue;
import java.sql.Date;
import java.sql.SQLException;
import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JScrollPane;


public class MainApp {

	private JFrame frmChurchMembersAnd;
	
	// Below Data Field for Login
	private JPanel panelLogin;
	private JTextField textFieldUserName;
	private JPasswordField passWord;
	
	// Below Data Field for the Menu List page
	private JPanel panelToDo;							// Menu List Page
	static JTable tableBirthday;
	static JTextField textFieldSearchDOB;
	
	// Below Data Field for Church Member Contacts
	private JPanel panelChurchMemberContacts;
	static JTable tableViewMemberContactB4Insert;
	private JTextField textFieldMemberID;
	private JTextField textFieldFullName;
	private JTextField textFieldDOB;
	private JTextField textFieldContactAdd;
	private JTextField textFieldContactPhone;
	private JTextField textFieldContactEmail;
	
	// Below Data Field for Church Donation
	private JPanel panelChurchDonations;
	static JTable tableViewDonationB4Insert;
	private JTextField textFieldDonationID;
	private JTextField textFieldFull_Name;
	private JTextField textFieldCashDonation;
	private JTextField textFieldNonCashDonation;
	private JTextField textFieldPhoneNo;
	private JTextField textFieldEMail;
	private JTextField textFieldDateofDonation;
	private JTextField textFieldMember_ID;
	
	
	// Below Data Field to Search Data for Update
	private JPanel panelEditUpdate;
	static JTextField textFieldSearchToUpdate;
	static String searchMemberID = null;
	
	// Below Data Field to Update the Church Member Contact
	static JTextField textField_1EditFullName;
	static JTextField textField_2EditDOB;
	static JTextField textFieldEditContactAdd;
	static JTextField textField_3EditPhoneNo;
	static JTextField textField_4EditEMail;
	static JTable tableMasterList;
	
	// Below Data Field for Delete
	private JPanel panelDelete;
	private JTextField textFieldToDelete;
	private JTextField textFieldDelDonor;	
	static JTable tableViewToDelete;	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainApp window = new MainApp();
					window.frmChurchMembersAnd.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		// THE FRAME
		frmChurchMembersAnd = new JFrame();
		frmChurchMembersAnd.setTitle("Church Members and Donors Contact Information");
		frmChurchMembersAnd.setBounds(100, 100, 1100, 942);
		frmChurchMembersAnd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmChurchMembersAnd.getContentPane().setLayout(new CardLayout(0, 0));
		
		// THE LOGIN PAGE
		panelLogin = new JPanel();
		frmChurchMembersAnd.getContentPane().add(panelLogin, "name_118203089605874");
		panelLogin.setLayout(null);
		panelLogin.setVisible(false);
		
		textFieldUserName = new JTextField();
		textFieldUserName.setFont(new Font("Verdana", Font.PLAIN, 14));
		textFieldUserName.setBounds(344, 339, 387, 44);
		panelLogin.add(textFieldUserName);
		textFieldUserName.setColumns(10);
		
		passWord = new JPasswordField();
		passWord.setFont(new Font("Verdana", Font.PLAIN, 14));
		passWord.setEchoChar('*');
		passWord.setColumns(10);
		passWord.setBounds(344, 470, 387, 44);
		panelLogin.add(passWord);
		
		
		JLabel lblAdminUsername = new JLabel("Admin UserName");
		lblAdminUsername.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblAdminUsername.setBounds(470, 396, 174, 27);
		panelLogin.add(lblAdminUsername);
		
		JLabel lblAdminPassword = new JLabel("Admin PassWord");
		lblAdminPassword.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblAdminPassword.setBounds(470, 525, 174, 27);
		panelLogin.add(lblAdminPassword);
	
		JButton btnLogin = new JButton("Admin Login");													// USERNAME AND PASSWORD LOGIN
		btnLogin.setFont(new Font("Verdana", Font.BOLD, 16));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String username = textFieldUserName.getText();										// to get password				
				String password = new String(passWord.getPassword());								// to get password
				String encodedP = SHA_HashFunction.getHash(password); 								// DO SHA-1 HASH FUNCTION.
				
				String login = username + " " + encodedP;
				
				if (username.equals("") && password.equals(""))	{									//TO CHECK IF LOGIN CRIDENTIAL IS NOT EMPTY
					JOptionPane.showMessageDialog(null, "Login Credentials cannot be Empty ! ! !");	//MESSAGE POP UP IF LOGIN CRIDENTIAL IS EMPTY
				}
				else	{
					Connect conn = new Connect();
					try	{
						Connect.getConnection();
						if (conn != null)	{
							if (login.equals(Connect.getAdminUser(username)))	{
								panelToDo.setVisible(true);
								panelLogin.setVisible(false);
								panelChurchMemberContacts.setVisible(false);
								panelChurchDonations.setVisible(false);
								panelEditUpdate.setVisible(false);
								panelDelete.setVisible(false);
							}
							else	{
								JOptionPane.showMessageDialog(null, "Incorrect Username and Password ! ! !");	//MESSAGE POP UP IF LOGIN RIDENTIAL IS INCORRECT
								Connect.close();
								textFieldUserName.setText(null);
								passWord.setText(null);
							}
						}	
					}	catch(SQLException e)	{
	            			e.printStackTrace();
						}
				}
			}
		});
		btnLogin.setBounds(448, 608, 196, 36);
		panelLogin.add(btnLogin);
		
		JLabel lblChurchMemberAnd = new JLabel("CHURCH MEMBERS AND DONORS CONTACT INFORMATION");
		lblChurchMemberAnd.setFont(new Font("Verdana", Font.BOLD, 16));
		lblChurchMemberAnd.setBounds(276, 72, 593, 44);
		panelLogin.add(lblChurchMemberAnd);
		
		JLabel labelAdminLogin = new JLabel("Admin User Login");
		labelAdminLogin.setFont(new Font("Verdana", Font.BOLD, 16));
		labelAdminLogin.setBounds(456, 267, 188, 44);
		panelLogin.add(labelAdminLogin);
		
		// TO DO LIST PAGE
		panelToDo = new JPanel();
		frmChurchMembersAnd.getContentPane().add(panelToDo, "name_118281159919430");
		panelToDo.setLayout(null);
		panelToDo.setVisible(false);
		
		JButton btnMemberContacts = new JButton("Insert Church Members Contacts");				// OPEN THE PAGE TO INSERT INTO CHURCH MEMBER CONTACTS
		btnMemberContacts.setFont(new Font("Verdana", Font.BOLD, 14));
		btnMemberContacts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelChurchMemberContacts.setVisible(true);
				panelToDo.setVisible(false);
				panelLogin.setVisible(false);
				panelChurchDonations.setVisible(false);
				panelEditUpdate.setVisible(false);
				panelDelete.setVisible(false);
				tableBirthday.setModel(new DefaultTableModel());
				textFieldSearchDOB.setText("");
			}
		});
		btnMemberContacts.setBounds(83, 119, 376, 49);
		panelToDo.add(btnMemberContacts);
		
		JButton btnChurchDonations = new JButton("Insert Church Donations");					// OPEN THE PAGE TO INSERT INTO CHURCH DONATIONS
		btnChurchDonations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelChurchDonations.setVisible(true);
				panelChurchMemberContacts.setVisible(false);
				panelToDo.setVisible(false);
				panelLogin.setVisible(false);
				panelEditUpdate.setVisible(false);
				panelDelete.setVisible(false);
				tableBirthday.setModel(new DefaultTableModel());
				textFieldSearchDOB.setText("");
			}
		});
		btnChurchDonations.setFont(new Font("Verdana", Font.BOLD, 14));
		btnChurchDonations.setBounds(83, 197, 376, 49);
		panelToDo.add(btnChurchDonations);
		
		JButton btnLogout = new JButton("Logout");													//LOGOUT BUTTON ON MENU LIST PAGE
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connect.close();
				textFieldUserName.setText(null);
				passWord.setText(null);
				panelLogin.setVisible(true);
				panelToDo.setVisible(false);
				panelChurchMemberContacts.setVisible(false);
				panelChurchDonations.setVisible(false);
				panelEditUpdate.setVisible(false);		
				textFieldSearchDOB.setText("");
				tableBirthday.setModel(new DefaultTableModel());
			}
		});
		btnLogout.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnLogout.setBounds(938, 13, 116, 37);
		panelToDo.add(btnLogout);
		
		JLabel lblMenu = new JLabel("MENU LIST");
		lblMenu.setFont(new Font("Verdana", Font.BOLD, 18));
		lblMenu.setBounds(483, 34, 148, 37);
		panelToDo.add(lblMenu);
		
		JButton btnEditupdateChurchMember = new JButton("Edit/Update Church Member Contacts");			// OPEN THE PAGE TO EDIT AND UPDATE DATA IN CHURCH MEMBER CONTACTS
		btnEditupdateChurchMember.setForeground(Color.BLUE);
		btnEditupdateChurchMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelEditUpdate.setVisible(true);
				panelToDo.setVisible(false);
				panelChurchMemberContacts.setVisible(false);
				panelLogin.setVisible(false);
				panelChurchDonations.setVisible(false);
				panelDelete.setVisible(false);
				tableBirthday.setModel(new DefaultTableModel());
				textFieldSearchDOB.setText("");
			}
		});
		btnEditupdateChurchMember.setFont(new Font("Verdana", Font.BOLD, 14));
		btnEditupdateChurchMember.setBounds(631, 119, 376, 49);
		panelToDo.add(btnEditupdateChurchMember);
		
		JButton btnDeleteChurchMember = new JButton("Delete Contacts From The Database");				// OPEN THE PAGE TO DELETE FROM DATABASE
		btnDeleteChurchMember.setForeground(Color.RED);
		btnDeleteChurchMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelDelete.setVisible(true);
				panelEditUpdate.setVisible(false);
				panelToDo.setVisible(false);
				panelChurchMemberContacts.setVisible(false);
				panelLogin.setVisible(false);
				panelChurchDonations.setVisible(false);
				tableBirthday.setModel(new DefaultTableModel());
				textFieldSearchDOB.setText("");
			}
		});
		btnDeleteChurchMember.setFont(new Font("Verdana", Font.BOLD, 14));
		btnDeleteChurchMember.setBounds(631, 197, 376, 49);
		panelToDo.add(btnDeleteChurchMember);
		
		textFieldSearchDOB = new JTextField();
		textFieldSearchDOB.setFont(new Font("Verdana", Font.PLAIN, 14));
		textFieldSearchDOB.setBounds(83, 340, 456, 37);
		panelToDo.add(textFieldSearchDOB);
		textFieldSearchDOB.setColumns(10);
		
		JButton buttonSearchDOB = new JButton("<<  Search Church Members with Birthday in this Month");
		buttonSearchDOB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {												// SEARCH DATE OF BIRTH ***************************
				
				String birthMonth = textFieldSearchDOB.getText();
				String dateOB = "%-" + birthMonth + "-%";
				
				if (dateOB.equals("%--%"))	{
					
					JOptionPane.showMessageDialog(null, "Search Field is Empty ! ! ! Type in the Month (01 - 12)");
				}
				else	{
					try {
						Connect.searchForDateOfBirth(dateOB);
						if (Connect.checkMonthlyBirthdayCelebration() == true)	{
							//textFieldSearchDOB.setText("");
						}
						else	{
							JOptionPane.showMessageDialog(null, "No Church Member has Birthday for this Month ! ! !");
							textFieldSearchDOB.setText("");
						}
						
					}	catch (SQLException | IOException ex) {
						JOptionPane.showMessageDialog(null, "Search Month of Birth Does NOT Exits ! ! !");
						textFieldSearchDOB.setText("");
						ex.printStackTrace();
						}
				}
			}
		});
		buttonSearchDOB.setFont(new Font("Verdana", Font.BOLD, 12));
		buttonSearchDOB.setBounds(551, 341, 456, 37);
		panelToDo.add(buttonSearchDOB);
		
		JLabel lblNewLabelSearchDOB = new JLabel("Search by Month Number [01 - 12] to Celebrate Church Members Birthday");
		lblNewLabelSearchDOB.setFont(new Font("Verdana", Font.BOLD, 14));
		lblNewLabelSearchDOB.setBounds(83, 305, 714, 22);
		panelToDo.add(lblNewLabelSearchDOB);
		
		JLabel lblClickOnAny = new JLabel("Click on any of the Buttons Below to Perform a Preferred Task");
		lblClickOnAny.setForeground(Color.BLACK);
		lblClickOnAny.setFont(new Font("Verdana", Font.BOLD, 14));
		lblClickOnAny.setBounds(295, 84, 584, 22);
		panelToDo.add(lblClickOnAny);
				
		tableBirthday = new JTable();
		tableBirthday.setFont(new Font("Verdana", Font.PLAIN, 14));
				
		JScrollPane scrollPaneBirthday = new JScrollPane(tableBirthday);
		scrollPaneBirthday.setBounds(83, 391, 924, 358);
		panelToDo.add(scrollPaneBirthday);
		
		JButton btnExportChurchMembers = new JButton("Export Church Members with Birthday to File");
		btnExportChurchMembers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String birthMonth = textFieldSearchDOB.getText();
				String dateOB = "%-" + birthMonth + "-%";
				
				if (dateOB.equals("%--%"))	{
					
					JOptionPane.showMessageDialog(null, "Search Field is Empty ! ! ! Type in the Month (01 - 12)");
				}
				else	{
					try {
						Connect.exportMontlyBDayToFile(dateOB);						
					}	catch (SQLException | IOException ex) {
							ex.printStackTrace();
						}
				}				
				
			}
		});
		btnExportChurchMembers.setForeground(Color.BLUE);
		btnExportChurchMembers.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
		btnExportChurchMembers.setBounds(83, 812, 456, 37);
		panelToDo.add(btnExportChurchMembers);
		
		JLabel lblToExportMembers = new JLabel("To Export Church Members with Birthday for this Month to File Click the Button below");
		lblToExportMembers.setForeground(Color.BLUE);
		lblToExportMembers.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 13));
		lblToExportMembers.setBounds(83, 777, 714, 22);
		panelToDo.add(lblToExportMembers);
		
		JButton btnDeleteExistingFile = new JButton("Delete Existing File Before Export");
		btnDeleteExistingFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connect.deleteBirthdayFile();
			}
		});
		btnDeleteExistingFile.setForeground(Color.RED);
		btnDeleteExistingFile.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
		btnDeleteExistingFile.setBounds(551, 812, 456, 37);
		panelToDo.add(btnDeleteExistingFile);
		
		JLabel lblMembersMonthlyBirthday = new JLabel("MEMBERS MONTHLY BIRTHDAY CELEBRATION");
		lblMembersMonthlyBirthday.setForeground(new Color(220, 20, 60));
		lblMembersMonthlyBirthday.setFont(new Font("Verdana", Font.BOLD, 14));
		lblMembersMonthlyBirthday.setBounds(352, 270, 432, 22);
		panelToDo.add(lblMembersMonthlyBirthday);
		
		// CHURCH MEMBER CONTACT PAGE
		panelChurchMemberContacts = new JPanel();
		frmChurchMembersAnd.getContentPane().add(panelChurchMemberContacts, "name_118392875297005");
		panelChurchMemberContacts.setLayout(null);
		
		textFieldFullName = new JTextField();
		textFieldFullName.setFont(new Font("Verdana", Font.PLAIN, 14));
		textFieldFullName.setBounds(572, 672, 464, 39);
		panelChurchMemberContacts.add(textFieldFullName);
		textFieldFullName.setColumns(10);
		
		textFieldDOB = new JTextField();
		textFieldDOB.setFont(new Font("Verdana", Font.PLAIN, 14));
		textFieldDOB.setToolTipText("YYYY-MM-DD");
		textFieldDOB.setColumns(10);
		textFieldDOB.setBounds(240, 722, 222, 39);
		panelChurchMemberContacts.add(textFieldDOB);
		
		textFieldContactPhone = new JTextField();
		textFieldContactPhone.setFont(new Font("Verdana", Font.PLAIN, 14));
		textFieldContactPhone.setColumns(10);
		textFieldContactPhone.setBounds(180, 774, 282, 39);
		panelChurchMemberContacts.add(textFieldContactPhone);
		
		textFieldContactEmail = new JTextField();
		textFieldContactEmail.setFont(new Font("Verdana", Font.PLAIN, 14));
		textFieldContactEmail.setColumns(10);
		textFieldContactEmail.setBounds(610, 774, 426, 39);
		panelChurchMemberContacts.add(textFieldContactEmail);
		
		JLabel lblFullName = new JLabel("Full Name");
		lblFullName.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblFullName.setBounds(480, 672, 80, 39);
		panelChurchMemberContacts.add(lblFullName);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth (YYYY-MM-DD)");
		lblDateOfBirth.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblDateOfBirth.setBounds(23, 722, 211, 39);
		panelChurchMemberContacts.add(lblDateOfBirth);
		
		JLabel lblContactAddress = new JLabel("Contact Address");
		lblContactAddress.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblContactAddress.setBounds(480, 724, 132, 39);
		panelChurchMemberContacts.add(lblContactAddress);
		
		JLabel lblContactPhoneNo = new JLabel("Contact Phone No.");
		lblContactPhoneNo.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblContactPhoneNo.setBounds(23, 772, 145, 39);
		panelChurchMemberContacts.add(lblContactPhoneNo);
		
		JLabel lblContactEmail = new JLabel("Contact E-Mail");
		lblContactEmail.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblContactEmail.setBounds(480, 772, 118, 39);
		panelChurchMemberContacts.add(lblContactEmail);
		
		JButton btnSave = new JButton("Save to Church Member Contacts");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String D_OfBirth = textFieldDOB.getText();
				if (D_OfBirth.equals(""))	{
					D_OfBirth = "1000-01-01";										//DEFAULT DATE VALUE IF THE DATA FIELD IS EMPTY
				}
				
				String memID = textFieldMemberID.getText().toUpperCase();
				String f_Name = textFieldFullName.getText();
				Date dateOfBirth = java.sql.Date.valueOf(D_OfBirth);
				String conAdd = textFieldContactAdd.getText();
				String phone = textFieldContactPhone.getText();
				String e_mail = textFieldContactEmail.getText();
				String DB_User = textFieldUserName.getText();
				String notes = "Church Member";
				
				if (memID.equals("") || f_Name.equals(""))	{																							// TO CHECK IF FULL NAME FEILD IS NOT EMPTY
					JOptionPane.showMessageDialog(null, "Member ID or Full Name Field cannot be Empty ! ! !");
				}
				else if (memID.length() != 9)	{
					JOptionPane.showMessageDialog(null, "Memeber ID format is wrong. The Correct Format - CMID-0000");
				}
				else	{
					try	{
					
						boolean cmi = Connect.insertChurchMemberInfo(memID, f_Name, dateOfBirth, conAdd, phone, e_mail, notes, DB_User);		// INSERT INTO DATABASE OF CHURCH MEMBER CONTACT AND RETURN TRUE IF SUCCESSFUL
					
						if (cmi == true)	{		
						
							JOptionPane.showMessageDialog(null, "Successfully Saved ! ! !");										//MESSAGE POP UP IF DATABASE INSERT WAS SUCCESSFUL
							
							Connect.viewMemberContactB4Insert();
							
							textFieldMemberID.setText("");
							textFieldFullName.setText("");
							textFieldDOB.setText("");
							textFieldContactAdd.setText("");
							textFieldContactPhone.setText("");
							textFieldContactEmail.setText("");
						}
						else	{
						
						JOptionPane.showMessageDialog(null, "Not Successfully Saved ! ! !");									//MESSAGE POP UP IF DATABASE INSERT WAS NOT SUCCESSFUL
						}	
					
					}	catch(Exception e)	{
        			e.printStackTrace();
        			JOptionPane.showMessageDialog(null, "Sorry Member ID ALREADY Exist, Not Successfully Saved ! ! !");
					}
				}
			}
		});
		btnSave.setFont(new Font("Verdana", Font.BOLD, 15));
		btnSave.setBounds(523, 824, 325, 44);
		panelChurchMemberContacts.add(btnSave);
		
		JButton buttonLogout = new JButton("Logout");										//LOGOUT BUTTON ON CHURCH MEMBER CONTACT PAGE
		buttonLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connect.close();
				textFieldUserName.setText(null);
				passWord.setText(null);
				panelLogin.setVisible(true);
				panelToDo.setVisible(false);
				panelChurchMemberContacts.setVisible(false);
				panelChurchDonations.setVisible(false);
				panelEditUpdate.setVisible(false);
				panelDelete.setVisible(false);
				textFieldFullName.setText("");
				textFieldDOB.setText("");
				textFieldContactAdd.setText("");
				textFieldContactPhone.setText("");
				textFieldContactEmail.setText("");
				textFieldMemberID.setText("");
				tableViewMemberContactB4Insert.setModel(new DefaultTableModel());
			}
		});
		buttonLogout.setFont(new Font("Verdana", Font.PLAIN, 14));
		buttonLogout.setBounds(936, 11, 123, 32);
		panelChurchMemberContacts.add(buttonLogout);
		
		JButton btnBackToMenu = new JButton("Back to Menu");								// BACK TO MENU LIST BUTTON ON CHURCH MEMBER CONTACT PAGE
		btnBackToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelToDo.setVisible(true);
				panelChurchMemberContacts.setVisible(false);
				panelLogin.setVisible(false);
				panelChurchDonations.setVisible(false);
				panelEditUpdate.setVisible(false);
				panelDelete.setVisible(false);
				textFieldFullName.setText("");
				textFieldDOB.setText("");
				textFieldContactAdd.setText("");
				textFieldContactPhone.setText("");
				textFieldContactEmail.setText("");
				textFieldMemberID.setText("");
				tableViewMemberContactB4Insert.setModel(new DefaultTableModel());
			}
		});
		btnBackToMenu.setFont(new Font("Verdana", Font.PLAIN, 14));
		btnBackToMenu.setBounds(23, 11, 145, 32);
		panelChurchMemberContacts.add(btnBackToMenu);
		
		JLabel lblInsertChurchMembers = new JLabel("CHURCH MEMBERS CONTACT INFORMATION");
		lblInsertChurchMembers.setFont(new Font("Verdana", Font.BOLD, 16));
		lblInsertChurchMembers.setBounds(355, 26, 417, 39);
		panelChurchMemberContacts.add(lblInsertChurchMembers);
		
		JButton btnClear_1 = new JButton("Clear All Fields");
		btnClear_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textFieldFullName.setText("");
				textFieldDOB.setText("");
				textFieldContactAdd.setText("");
				textFieldContactPhone.setText("");
				textFieldContactEmail.setText("");
				textFieldMemberID.setText("");
			}
		});
		btnClear_1.setFont(new Font("Verdana", Font.BOLD, 15));
		btnClear_1.setBounds(887, 824, 172, 44);
		panelChurchMemberContacts.add(btnClear_1);
		
		textFieldContactAdd = new JTextField();
		textFieldContactAdd.setToolTipText("YYYY-MM-DD");
		textFieldContactAdd.setFont(new Font("Verdana", Font.PLAIN, 14));
		textFieldContactAdd.setColumns(10);
		textFieldContactAdd.setBounds(624, 724, 412, 39);
		panelChurchMemberContacts.add(textFieldContactAdd);
		
		JLabel lblContactForm = new JLabel("Form to Save to Church Member Contact");
		lblContactForm.setFont(new Font("Verdana", Font.BOLD, 16));
		lblContactForm.setBounds(405, 625, 383, 32);
		panelChurchMemberContacts.add(lblContactForm);
		
		textFieldMemberID = new JTextField();
		textFieldMemberID.setToolTipText("Member ID Format is CMID-0000");
		textFieldMemberID.setFont(new Font("Verdana", Font.PLAIN, 14));
		textFieldMemberID.setColumns(10);
		textFieldMemberID.setBounds(224, 670, 238, 39);
		panelChurchMemberContacts.add(textFieldMemberID);
		
		JLabel lblMemberid = new JLabel("Member-ID (CMID-0000)");
		lblMemberid.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblMemberid.setBounds(23, 670, 189, 39);
		panelChurchMemberContacts.add(lblMemberid);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(23, 169, 1036, 444);
		panelChurchMemberContacts.add(scrollPane_2);
		
		tableViewMemberContactB4Insert = new JTable();
		scrollPane_2.setViewportView(tableViewMemberContactB4Insert);
		tableViewMemberContactB4Insert.setFont(new Font("Verdana", Font.PLAIN, 14));
		
		JLabel lblViewTheDonation_1 = new JLabel("View the Donation data in the Database Before Inserting New Data to Make Sure the MEMBER ID is Unique");
		lblViewTheDonation_1.setForeground(Color.RED);
		lblViewTheDonation_1.setFont(new Font("Verdana", Font.BOLD, 13));
		lblViewTheDonation_1.setBounds(166, 78, 826, 32);
		panelChurchMemberContacts.add(lblViewTheDonation_1);
		
		JButton btnViewMemberContactB4Insert = new JButton("Click to View Existing Church Member Contact Data");
		btnViewMemberContactB4Insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {															//TO VIEW CHURCH MEMBER CONTACT BEFORE INSERT
				
				try {
					Connect.viewMemberContactB4Insert();
					if (tableViewMemberContactB4Insert.getRowCount() == 0)	{
						JOptionPane.showMessageDialog(null, "No Information SAVED in Database ! ! !");
					}
					
				} catch (SQLException | IOException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnViewMemberContactB4Insert.setFont(new Font("Verdana", Font.BOLD, 12));
		btnViewMemberContactB4Insert.setBounds(338, 123, 434, 32);
		panelChurchMemberContacts.add(btnViewMemberContactB4Insert);
		
		// CHURCH DONATION PAGE
		panelChurchDonations = new JPanel();
		frmChurchMembersAnd.getContentPane().add(panelChurchDonations, "name_118430330139329");
		panelChurchDonations.setLayout(null);
		
		JLabel lblInsertChurchDonations = new JLabel("CHURCH DONATION INFORMATION");
		lblInsertChurchDonations.setFont(new Font("Verdana", Font.BOLD, 16));
		lblInsertChurchDonations.setBounds(381, 11, 336, 44);
		panelChurchDonations.add(lblInsertChurchDonations);
		
		JLabel labelFullName = new JLabel("Full Name");
		labelFullName.setFont(new Font("Verdana", Font.PLAIN, 14));
		labelFullName.setBounds(535, 605, 83, 39);
		panelChurchDonations.add(labelFullName);
		
		textFieldFull_Name = new JTextField();
		textFieldFull_Name.setFont(new Font("Verdana", Font.PLAIN, 14));
		textFieldFull_Name.setColumns(10);
		textFieldFull_Name.setBounds(639, 606, 420, 39);
		panelChurchDonations.add(textFieldFull_Name);
		
		JLabel lblCashDonations = new JLabel("Cash Donations");
		lblCashDonations.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblCashDonations.setBounds(24, 658, 123, 39);
		panelChurchDonations.add(lblCashDonations);
		
		textFieldCashDonation = new JTextField();
		textFieldCashDonation.setFont(new Font("Verdana", Font.PLAIN, 14));
		textFieldCashDonation.setColumns(10);
		textFieldCashDonation.setBounds(179, 658, 319, 39);
		panelChurchDonations.add(textFieldCashDonation);
		
		JLabel lblNoncashDonations = new JLabel("Non-Cash Donations");
		lblNoncashDonations.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblNoncashDonations.setBounds(535, 658, 157, 39);
		panelChurchDonations.add(lblNoncashDonations);
		
		textFieldNonCashDonation = new JTextField();
		textFieldNonCashDonation.setFont(new Font("Verdana", Font.PLAIN, 14));
		textFieldNonCashDonation.setColumns(10);
		textFieldNonCashDonation.setBounds(714, 659, 345, 39);
		panelChurchDonations.add(textFieldNonCashDonation);
		
		JLabel labelDonorPhone = new JLabel("Contact Phone No.");
		labelDonorPhone.setFont(new Font("Verdana", Font.PLAIN, 14));
		labelDonorPhone.setBounds(24, 713, 143, 39);
		panelChurchDonations.add(labelDonorPhone);
		
		textFieldPhoneNo = new JTextField();
		textFieldPhoneNo.setFont(new Font("Verdana", Font.PLAIN, 14));
		textFieldPhoneNo.setColumns(10);
		textFieldPhoneNo.setBounds(179, 713, 319, 39);
		panelChurchDonations.add(textFieldPhoneNo);
		
		JButton buttonSaveDonor = new JButton("Save to Church Donation");
		buttonSaveDonor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String cashDonate = textFieldCashDonation.getText();
				if (cashDonate.equals(""))	{
					cashDonate = "0.00";										//DEFAULT CASH DONATION VALUE IF THE DATA FIELD IS EMPTY
				}
				
				String D_OfDonate = textFieldDateofDonation.getText();
				if (D_OfDonate.equals(""))	{
					D_OfDonate = "1000-01-01";										//DEFAULT DATE VALUE IF THE DATA FIELD IS EMPTY
				}
				
				String nonCash = textFieldNonCashDonation.getText();
				if (nonCash.equals(""))	{
					nonCash = "NONE";											//DEFAULT NONE CASH DONATION VALUE IF THE DATA FIELD IS EMPTY
				}
				
				String donateID = textFieldDonationID.getText().toUpperCase();
				String f_Name = textFieldFull_Name.getText();
				BigDecimal cashD = new BigDecimal(cashDonate);
				String nonCashD = nonCash;
				String phone = textFieldPhoneNo.getText();
				String e_mail = textFieldEMail.getText();
				Date dateOfDonate = java.sql.Date.valueOf(D_OfDonate);
				String memID = textFieldMember_ID.getText().toUpperCase();
				
				String DB_User = textFieldUserName.getText();
				
				if (donateID.equals("") || memID.equals("") || f_Name.equals(""))	{
					JOptionPane.showMessageDialog(null, "Donation ID or Member ID or Full Name Field cannot be Empty ! ! !");
				}
				else if (donateID.length() != 7)	{
					JOptionPane.showMessageDialog(null, "Donation ID format is wrong. The Correct Format - CD-0000");
				}
				else if (memID.length() != 9)	{
						JOptionPane.showMessageDialog(null, "Memeber ID format is wrong. The Correct Format - CMID-0000");
				}
				else	{
					try	{
					
						boolean cdi = Connect.insertChurchDonationInfo(donateID, f_Name, cashD, nonCashD, phone, e_mail, dateOfDonate, memID, DB_User);			// INSERT INTO DATABASE OF CHURCH DONATION CONTACT AND RETURN TRUE IF SUCCESSFUL
					
						if (cdi == true)	{		
						
							JOptionPane.showMessageDialog(null, "Successfully Saved ! ! !");									//MESSAGE POP UP IF DATABASE INSERT WAS SUCCESSFUL
							
							Connect.viewDonationB4Insert();
							
							textFieldFull_Name.setText("");
							textFieldCashDonation.setText("");
							textFieldNonCashDonation.setText("");
							textFieldPhoneNo.setText("");
							textFieldEMail.setText("");
							textFieldMember_ID.setText("");
							textFieldDonationID.setText("");
							textFieldDateofDonation.setText("");
						}
						else	{
						
							JOptionPane.showMessageDialog(null, "Not Successfully Saved ! ! !");								//MESSAGE POP UP IF DATABASE INSERT WAS NOT SUCCESSFUL
						}
					
					}	catch(Exception ex)	{
							ex.printStackTrace();
							JOptionPane.showMessageDialog(null, "Sorry, Donation ID ALREADY Exist, Not Successfully Saved ! ! !");
						}	
				}
			}
		});
		buttonSaveDonor.setFont(new Font("Verdana", Font.BOLD, 15));
		buttonSaveDonor.setBounds(596, 823, 254, 44);
		panelChurchDonations.add(buttonSaveDonor);
		
		JButton buttonBackToMenu = new JButton("Back to Menu");								// BACK TO MENU BUTTON ON THE CHURCH DONATION PAGE
		buttonBackToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelToDo.setVisible(true);
				panelChurchMemberContacts.setVisible(false);
				panelLogin.setVisible(false);
				panelChurchDonations.setVisible(false);
				panelEditUpdate.setVisible(false);
				panelDelete.setVisible(false);
				textFieldFull_Name.setText("");
				textFieldCashDonation.setText("");
				textFieldNonCashDonation.setText("");
				textFieldPhoneNo.setText("");
				textFieldEMail.setText("");
				textFieldMember_ID.setText("");
				textFieldDonationID.setText("");
				textFieldDateofDonation.setText("");
				tableViewDonationB4Insert.setModel(new DefaultTableModel());
			}
		});
		buttonBackToMenu.setFont(new Font("Verdana", Font.PLAIN, 14));
		buttonBackToMenu.setBounds(24, 11, 143, 32);
		panelChurchDonations.add(buttonBackToMenu);
		
		JButton buttonLogout2 = new JButton("Logout");										// LOGOUT BUTTON ON THE CHURCH DONATION PAGE
		buttonLogout2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connect.close();
				textFieldUserName.setText(null);
				passWord.setText(null);
				panelLogin.setVisible(true);
				panelToDo.setVisible(false);
				panelChurchMemberContacts.setVisible(false);
				panelChurchDonations.setVisible(false);
				panelEditUpdate.setVisible(false);
				panelDelete.setVisible(false);
				textFieldFull_Name.setText("");
				textFieldCashDonation.setText("");
				textFieldNonCashDonation.setText("");
				textFieldPhoneNo.setText("");
				textFieldEMail.setText("");
				textFieldMember_ID.setText("");
				textFieldDonationID.setText("");
				textFieldDateofDonation.setText("");
				tableViewDonationB4Insert.setModel(new DefaultTableModel());
			}
		});
		buttonLogout2.setFont(new Font("Verdana", Font.PLAIN, 14));
		buttonLogout2.setBounds(936, 11, 123, 32);
		panelChurchDonations.add(buttonLogout2);
		
		JLabel lblContactEmail_1 = new JLabel("Contact E-Mail");
		lblContactEmail_1.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblContactEmail_1.setBounds(535, 711, 111, 39);
		panelChurchDonations.add(lblContactEmail_1);
		
		textFieldEMail = new JTextField();
		textFieldEMail.setFont(new Font("Verdana", Font.PLAIN, 14));
		textFieldEMail.setColumns(10);
		textFieldEMail.setBounds(667, 712, 392, 39);
		panelChurchDonations.add(textFieldEMail);
		
		JButton btnClear_2 = new JButton("Clear All Fields");
		btnClear_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldFull_Name.setText("");
				textFieldCashDonation.setText("");
				textFieldNonCashDonation.setText("");
				textFieldPhoneNo.setText("");
				textFieldEMail.setText("");
				textFieldMember_ID.setText("");
				textFieldDonationID.setText("");
				textFieldDateofDonation.setText("");
			}
		});
		btnClear_2.setFont(new Font("Verdana", Font.BOLD, 15));
		btnClear_2.setBounds(887, 823, 172, 44);
		panelChurchDonations.add(btnClear_2);
		
		JLabel lblDonationForm = new JLabel("Form to Save to Church Donations");
		lblDonationForm.setFont(new Font("Verdana", Font.BOLD, 16));
		lblDonationForm.setBounds(417, 560, 337, 32);
		panelChurchDonations.add(lblDonationForm);
		
		JLabel lblMemberIdcmid = new JLabel("Member ID (CMID-0000)");
		lblMemberIdcmid.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblMemberIdcmid.setBounds(535, 765, 182, 39);
		panelChurchDonations.add(lblMemberIdcmid);
		
		textFieldMember_ID = new JTextField();
		textFieldMember_ID.setToolTipText("Member ID Format is CMID-0000");
		textFieldMember_ID.setFont(new Font("Verdana", Font.PLAIN, 14));
		textFieldMember_ID.setColumns(10);
		textFieldMember_ID.setBounds(738, 765, 321, 39);
		panelChurchDonations.add(textFieldMember_ID);
		
		JLabel lblDateOfDonation = new JLabel("Date of Donation (YYYY-MM-DD)");
		lblDateOfDonation.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblDateOfDonation.setBounds(24, 764, 248, 39);
		panelChurchDonations.add(lblDateOfDonation);
		
		textFieldDateofDonation = new JTextField();
		textFieldDateofDonation.setFont(new Font("Verdana", Font.PLAIN, 14));
		textFieldDateofDonation.setColumns(10);
		textFieldDateofDonation.setBounds(284, 765, 214, 39);
		panelChurchDonations.add(textFieldDateofDonation);
		
		JLabel labelDonationID = new JLabel("Donation ID (CD-0000)");
		labelDonationID.setFont(new Font("Verdana", Font.PLAIN, 14));
		labelDonationID.setBounds(24, 605, 182, 39);
		panelChurchDonations.add(labelDonationID);
		
		textFieldDonationID = new JTextField();
		textFieldDonationID.setToolTipText("Donation ID Format is CD-0000");
		textFieldDonationID.setFont(new Font("Verdana", Font.PLAIN, 14));
		textFieldDonationID.setColumns(10);
		textFieldDonationID.setBounds(214, 605, 284, 39);
		panelChurchDonations.add(textFieldDonationID);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(24, 166, 1035, 377);
		panelChurchDonations.add(scrollPane_1);
		
		tableViewDonationB4Insert = new JTable();
		scrollPane_1.setViewportView(tableViewDonationB4Insert);
		tableViewDonationB4Insert.setFont(new Font("Verdana", Font.PLAIN, 14));
		
		JLabel lblViewTheDonation = new JLabel("View the Donation Data in the Database Before Inserting New Data to Make Sure the DONATION ID is Unique");
		lblViewTheDonation.setForeground(Color.RED);
		lblViewTheDonation.setFont(new Font("Verdana", Font.BOLD, 13));
		lblViewTheDonation.setBounds(162, 68, 826, 32);
		panelChurchDonations.add(lblViewTheDonation);
		
		JButton btnViewB4InsertDonation = new JButton("Click to View Existing Donation Data");				// VIEW CHURCH DONATION BEFORE INSERT
		btnViewB4InsertDonation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connect.viewDonationB4Insert();
					if (tableViewDonationB4Insert.getRowCount() == 0)	{
						JOptionPane.showMessageDialog(null, "No Information SAVED in Database ! ! !");
					}
				} 	catch (SQLException | IOException e) 	{
					
						e.printStackTrace();
					}
			}
		});
		btnViewB4InsertDonation.setFont(new Font("Verdana", Font.BOLD, 12));
		btnViewB4InsertDonation.setBounds(354, 113, 392, 32);
		panelChurchDonations.add(btnViewB4InsertDonation);
		
		panelEditUpdate = new JPanel();																//EDIT AND UPDATE PAGE
		frmChurchMembersAnd.getContentPane().add(panelEditUpdate, "name_516899568884336");
		panelEditUpdate.setLayout(null);
		
		JLabel lblEditAndUpdate = new JLabel("EDIT AND UPDATE CHURCH MEMBER CONTACT INFORMATION");
		lblEditAndUpdate.setFont(new Font("Verdana", Font.BOLD, 16));
		lblEditAndUpdate.setBounds(259, 16, 587, 33);
		panelEditUpdate.add(lblEditAndUpdate);
		
		textFieldSearchToUpdate = new JTextField();
		textFieldSearchToUpdate.setForeground(Color.BLUE);
		textFieldSearchToUpdate.setFont(new Font("Verdana", Font.PLAIN, 14));
		textFieldSearchToUpdate.setBounds(26, 685, 578, 33);
		panelEditUpdate.add(textFieldSearchToUpdate);
		textFieldSearchToUpdate.setColumns(10);
		
		JButton btnSearchByFull = new JButton("<<    Search by \"MEMBER ID\" to Edit/Update");			// SEARCH BUTTON TO EDIT AND UPDATE DATA
		btnSearchByFull.setForeground(Color.BLUE);
		btnSearchByFull.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				searchMemberID = textFieldSearchToUpdate.getText().toUpperCase();					//This line is used to update the Master List
				
				String memID = textFieldSearchToUpdate.getText().toUpperCase();
				
				if (memID.equals(""))	{
					JOptionPane.showMessageDialog(null, "Search Field is Empty ! ! !");
				}
				else	{
					try {
						boolean editUpdate = Connect.selectDataToUpdate(memID);
						
						if (editUpdate == true)	{
							textFieldSearchToUpdate.setText("");
						}
						else	{
							textFieldSearchToUpdate.setText("");
							JOptionPane.showMessageDialog(null, "Searched Contact does not Exist in the Database ! ! !");
						}
					} catch (Exception e)	{
						JOptionPane.showMessageDialog(null, "Searched Contact does not Exist in the Database ! ! !");
						textFieldSearchToUpdate.setText("");
						e.printStackTrace();
					}
				}
			}
		});
		btnSearchByFull.setFont(new Font("Verdana", Font.BOLD, 13));
		btnSearchByFull.setBounds(616, 685, 440, 33);
		panelEditUpdate.add(btnSearchByFull);
		
		JButton buttonBck2Menu = new JButton("Back to Menu");									//BACK TO MENU LIST BUTTON ON THE UPDATE PAGE
		buttonBck2Menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelToDo.setVisible(true);
				panelChurchMemberContacts.setVisible(false);
				panelLogin.setVisible(false);
				panelChurchDonations.setVisible(false);
				panelEditUpdate.setVisible(false);
				panelDelete.setVisible(false);
				textField_1EditFullName.setText("");
				textField_2EditDOB.setText("");
				textFieldEditContactAdd.setText("");
				textField_3EditPhoneNo.setText("");
				textField_4EditEMail.setText("");
				tableMasterList.setModel(new DefaultTableModel());
			}
		});
		buttonBck2Menu.setFont(new Font("Verdana", Font.PLAIN, 14));
		buttonBck2Menu.setBounds(26, 11, 132, 32);
		panelEditUpdate.add(buttonBck2Menu);
		
		JButton button_1Logout = new JButton("Logout");											// LOGOUT BUTTON ON THE UPDATE PAGE
		button_1Logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				Connect.close();
				textFieldUserName.setText(null);
				passWord.setText(null);
				panelLogin.setVisible(true);
				panelToDo.setVisible(false);
				panelChurchMemberContacts.setVisible(false);
				panelChurchDonations.setVisible(false);
				panelEditUpdate.setVisible(false);
				panelDelete.setVisible(false);
				textField_1EditFullName.setText("");
				textField_2EditDOB.setText("");
				textFieldEditContactAdd.setText("");
				textField_3EditPhoneNo.setText("");
				textField_4EditEMail.setText("");
				tableMasterList.setModel(new DefaultTableModel());
			}
		});
		button_1Logout.setFont(new Font("Verdana", Font.PLAIN, 14));
		button_1Logout.setBounds(933, 11, 123, 32);
		panelEditUpdate.add(button_1Logout);
		
		JLabel lblUpdateChurchMembers = new JLabel("Below is the Edit and Update Form for the Church Member Master List");
		lblUpdateChurchMembers.setForeground(Color.BLUE);
		lblUpdateChurchMembers.setFont(new Font("Verdana", Font.BOLD, 13));
		lblUpdateChurchMembers.setBounds(26, 643, 606, 33);
		panelEditUpdate.add(lblUpdateChurchMembers);
		
		textField_1EditFullName = new JTextField();
		textField_1EditFullName.setFont(new Font("Verdana", Font.PLAIN, 14));
		textField_1EditFullName.setColumns(10);
		textField_1EditFullName.setBounds(175, 731, 471, 39);
		panelEditUpdate.add(textField_1EditFullName);
		
		JLabel lblFullName_1 = new JLabel("Full Name >>");
		lblFullName_1.setForeground(Color.BLUE);
		lblFullName_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblFullName_1.setBounds(26, 731, 116, 39);
		panelEditUpdate.add(lblFullName_1);
		
		JLabel lblDateOfBirth_1 = new JLabel("Date of Birth (YYYY-MM-DD) >>");
		lblDateOfBirth_1.setForeground(Color.BLUE);
		lblDateOfBirth_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblDateOfBirth_1.setBounds(658, 731, 245, 39);
		panelEditUpdate.add(lblDateOfBirth_1);
		
		textField_2EditDOB = new JTextField();
		textField_2EditDOB.setFont(new Font("Verdana", Font.PLAIN, 14));
		textField_2EditDOB.setToolTipText("YYYY-MM-DD");
		textField_2EditDOB.setColumns(10);
		textField_2EditDOB.setBounds(903, 731, 153, 39);
		panelEditUpdate.add(textField_2EditDOB);
		
		JLabel lblContactAddress_1 = new JLabel("Contact Address >>");
		lblContactAddress_1.setForeground(Color.BLUE);
		lblContactAddress_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblContactAddress_1.setBounds(26, 783, 147, 39);
		panelEditUpdate.add(lblContactAddress_1);
		
		JLabel lblContactPhoneNo_1 = new JLabel("Contact Phone No. >>");
		lblContactPhoneNo_1.setForeground(Color.BLUE);
		lblContactPhoneNo_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblContactPhoneNo_1.setBounds(658, 783, 189, 39);
		panelEditUpdate.add(lblContactPhoneNo_1);
		
		textField_3EditPhoneNo = new JTextField();
		textField_3EditPhoneNo.setFont(new Font("Verdana", Font.PLAIN, 14));
		textField_3EditPhoneNo.setColumns(10);
		textField_3EditPhoneNo.setBounds(845, 783, 211, 39);
		panelEditUpdate.add(textField_3EditPhoneNo);
		
		JLabel lblContactEmail_2 = new JLabel("Contact E-Mail >>");
		lblContactEmail_2.setForeground(Color.BLUE);
		lblContactEmail_2.setFont(new Font("Verdana", Font.PLAIN, 13));
		lblContactEmail_2.setBounds(26, 835, 147, 39);
		panelEditUpdate.add(lblContactEmail_2);
		
		textField_4EditEMail = new JTextField();
		textField_4EditEMail.setFont(new Font("Verdana", Font.PLAIN, 14));
		textField_4EditEMail.setColumns(10);
		textField_4EditEMail.setBounds(175, 834, 471, 39);
		panelEditUpdate.add(textField_4EditEMail);
		
		JButton btnUpdateChurchMember = new JButton("Update Master List");					// BUTTON TO UPDATE MASTER LIST
		btnUpdateChurchMember.setForeground(Color.BLUE);
		btnUpdateChurchMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String D_OfBirth = textField_2EditDOB.getText();
				if (D_OfBirth.equals(""))	{
					D_OfBirth = "1000-01-01";										//DEFAULT DATE VALUE IF THE DATA FIELD IS EMPTY
				}
				
				String FN = textField_1EditFullName.getText();
				Date DOB = java.sql.Date.valueOf(D_OfBirth);
				String CA = textFieldEditContactAdd.getText();
				String PN = textField_3EditPhoneNo.getText();
				String E = textField_4EditEMail.getText();
				String memIDsearch = searchMemberID;
				
				try {
					boolean upDate = Connect.updateChurchMemberContact(FN, DOB, CA, PN, E, memIDsearch);
					
					if (upDate == true)	{
						JOptionPane.showMessageDialog(null, "Master Contact List Successfully Updated ! ! !");
						textField_1EditFullName.setText("");
						textField_2EditDOB.setText("");
						textFieldEditContactAdd.setText("");
						textField_3EditPhoneNo.setText("");
						textField_4EditEMail.setText("");
						memIDsearch = null;
						searchMemberID = null;
						
						try	{
							Connect.selectFromMasterList();										//TO REFRESH THE VIEW TABLE AFTER UPDATE IS DONE
							if (Connect.checkMasterList() == true)	{
								JOptionPane.showMessageDialog(null, "Result Table has been Refreshed, Look through to confirm ! ! !");
							}
						} 	catch (SQLException | IOException ey) {
							ey.printStackTrace();
							}
						
					}
					else	{
						JOptionPane.showMessageDialog(null, "Master Contact List NOT Successfully Updated ! ! !");
						textField_1EditFullName.setText("");
						textField_2EditDOB.setText("");
						textFieldEditContactAdd.setText("");
						textField_3EditPhoneNo.setText("");
						textField_4EditEMail.setText("");
						searchMemberID = null;
						memIDsearch = null;
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Master Contact List NOT Successfully Updated ! ! !");
					textField_1EditFullName.setText("");
					textField_2EditDOB.setText("");
					textFieldEditContactAdd.setText("");
					textField_3EditPhoneNo.setText("");
					textField_4EditEMail.setText("");
					memIDsearch = null;
					searchMemberID = null;
					e1.printStackTrace();
				}
			}
		});
		btnUpdateChurchMember.setFont(new Font("Verdana", Font.BOLD, 14));
		btnUpdateChurchMember.setBounds(658, 836, 225, 36);
		panelEditUpdate.add(btnUpdateChurchMember);
		
		JButton btnClear_3 = new JButton("Clear Fields");
		btnClear_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_1EditFullName.setText("");
				textField_2EditDOB.setText("");
				textFieldEditContactAdd.setText("");
				textField_3EditPhoneNo.setText("");
				textField_4EditEMail.setText("");
			}
		});
		btnClear_3.setFont(new Font("Verdana", Font.BOLD, 14));
		btnClear_3.setBounds(895, 835, 161, 36);
		panelEditUpdate.add(btnClear_3);
		
		textFieldEditContactAdd = new JTextField();
		textFieldEditContactAdd.setToolTipText("YYYY-MM-DD");
		textFieldEditContactAdd.setFont(new Font("Verdana", Font.PLAIN, 14));
		textFieldEditContactAdd.setColumns(10);
		textFieldEditContactAdd.setBounds(175, 783, 471, 39);
		panelEditUpdate.add(textFieldEditContactAdd);
		
		tableMasterList = new JTable();
		tableMasterList.setFont(new Font("Verdana", Font.PLAIN, 14));
		
		JScrollPane scrollPane = new JScrollPane(tableMasterList);
		scrollPane.setBounds(43, 154, 999, 402);
		panelEditUpdate.add(scrollPane);
		
		JButton btnClickToView = new JButton("Click to View ALL Church Members");
		btnClickToView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try	{
					Connect.selectFromMasterList();
					if (Connect.checkMasterList() == false)	{
						JOptionPane.showMessageDialog(null, "No Church Member Record in Database ! ! !");
					}
				} 	catch (SQLException | IOException e) {
					e.printStackTrace();
					}
			}
		});
		btnClickToView.setForeground(new Color(255, 0, 51));
		btnClickToView.setFont(new Font("Verdana", Font.BOLD, 12));
		btnClickToView.setBounds(43, 108, 307, 33);
		panelEditUpdate.add(btnClickToView);
		
		JButton btnViewDonors = new JButton("Click to View Church Donors ONLY");
		btnViewDonors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connect.viewChurchDonors("Church Donor");
					if (Connect.checkMasterList() == false)	{
						JOptionPane.showMessageDialog(null, "No Church Donor Record in Database ! ! !");
					}
				} 	catch (SQLException | IOException e1) {
						e1.printStackTrace();
					}
			}
		});
		btnViewDonors.setForeground(Color.BLACK);
		btnViewDonors.setFont(new Font("Verdana", Font.BOLD, 12));
		btnViewDonors.setBounds(393, 108, 307, 33);
		panelEditUpdate.add(btnViewDonors);
		
		JButton btnExportMasterList = new JButton("Export ALL Church Member List to File");
		btnExportMasterList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connect.exportMainListToFile();
				}	catch (SQLException | IOException e1) 	{
						e1.printStackTrace();
					}
			}
		});
		btnExportMasterList.setForeground(new Color(0, 0, 139));
		btnExportMasterList.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
		btnExportMasterList.setBounds(43, 597, 319, 33);
		panelEditUpdate.add(btnExportMasterList);
		
		JButton btnExportChurchDonor = new JButton("Export ALL Church Donoation List to File");
		btnExportChurchDonor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connect.exportChurchDonationToFile();
				} 	catch (SQLException | IOException e1) 	{
					e1.printStackTrace();
					}
			}
		});
		btnExportChurchDonor.setForeground(new Color(0, 0, 139));
		btnExportChurchDonor.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
		btnExportChurchDonor.setBounds(381, 597, 319, 33);
		panelEditUpdate.add(btnExportChurchDonor);
		
		JLabel lblToExportChurch = new JLabel("To Export Church Members Main List or Church Donations List to File, Click either of the Button Below");
		lblToExportChurch.setForeground(new Color(0, 0, 139));
		lblToExportChurch.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
		lblToExportChurch.setBounds(41, 563, 757, 33);
		panelEditUpdate.add(lblToExportChurch);
		
		JButton btnDeleteAllExisting = new JButton("Delete All Existing Files Before Export");
		btnDeleteAllExisting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Connect.deleteAllFiles();
			}
		});
		btnDeleteAllExisting.setForeground(Color.RED);
		btnDeleteAllExisting.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
		btnDeleteAllExisting.setBounds(723, 597, 319, 33);
		panelEditUpdate.add(btnDeleteAllExisting);
		
		JButton btnClickToView_1 = new JButton("Click to View Church Donations");
		btnClickToView_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connect.viewChurchDonations();
					if (Connect.checkMasterList() == false)	{
						JOptionPane.showMessageDialog(null, "No Church Donations Record in Database ! ! !");
					}
				} 	catch (SQLException | IOException e1) 	{
						e1.printStackTrace();
					}
			}
		});
		btnClickToView_1.setForeground(Color.BLACK);
		btnClickToView_1.setFont(new Font("Verdana", Font.BOLD, 12));
		btnClickToView_1.setBounds(734, 108, 308, 33);
		panelEditUpdate.add(btnClickToView_1);
		
		JLabel lblClickTheFirst = new JLabel("Click the First Button Below to View ALL Church Member Contacts Before Updating Any Contact");
		lblClickTheFirst.setForeground(new Color(255, 0, 51));
		lblClickTheFirst.setFont(new Font("Verdana", Font.BOLD, 13));
		lblClickTheFirst.setBounds(43, 62, 738, 33);
		panelEditUpdate.add(lblClickTheFirst);
		
		panelDelete = new JPanel();
		frmChurchMembersAnd.getContentPane().add(panelDelete, "name_101365915130574");
		panelDelete.setLayout(null);
		
		JButton button_1DeleteBAck = new JButton("Back to Menu");
		button_1DeleteBAck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {											// BACK TO MENU BUTTON ON DELETE PAGE
				panelToDo.setVisible(true);
				panelChurchMemberContacts.setVisible(false);
				panelLogin.setVisible(false);
				panelChurchDonations.setVisible(false);
				panelEditUpdate.setVisible(false);
				panelDelete.setVisible(false);
				textFieldToDelete.setText("");
				textFieldDelDonor.setText("");
				tableViewToDelete.setModel(new DefaultTableModel());
			}
		});
		button_1DeleteBAck.setFont(new Font("Verdana", Font.PLAIN, 14));
		button_1DeleteBAck.setBounds(24, 13, 133, 32);
		panelDelete.add(button_1DeleteBAck);
		
		JButton button_2DeleteLogout = new JButton("Logout");
		button_2DeleteLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {											// LOGOUT BUTTON ON DELETE PAGE
				Connect.close();
				textFieldUserName.setText(null);
				passWord.setText(null);
				panelLogin.setVisible(true);
				panelToDo.setVisible(false);
				panelChurchMemberContacts.setVisible(false);
				panelChurchDonations.setVisible(false);
				panelEditUpdate.setVisible(false);
				panelDelete.setVisible(false);
				textFieldToDelete.setText("");
				textFieldDelDonor.setText("");
				tableViewToDelete.setModel(new DefaultTableModel());
			}
		});
		button_2DeleteLogout.setFont(new Font("Verdana", Font.PLAIN, 14));
		button_2DeleteLogout.setBounds(934, 13, 123, 32);
		panelDelete.add(button_2DeleteLogout);
		
		textFieldToDelete = new JTextField();
		textFieldToDelete.setForeground(Color.RED);
		textFieldToDelete.setFont(new Font("Verdana", Font.PLAIN, 14));
		textFieldToDelete.setColumns(10);
		textFieldToDelete.setBounds(68, 680, 526, 33);
		panelDelete.add(textFieldToDelete);
		
		JButton btnDelete = new JButton("<<    Delete From Master List");
		btnDelete.setForeground(Color.RED);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {											// DELETE BUTTON FOR CHURCH MEMBER CONTACTS LIST
				
				String memID_ToDelete = textFieldToDelete.getText().toUpperCase();
				
				if (memID_ToDelete.equals(""))	{
					JOptionPane.showMessageDialog(null, "Search Field is Empty ! ! !");
				}
				else	{
				
					try {
						boolean del = Connect.deleteChurchMember(memID_ToDelete);
					
						if (del == true)	{
							JOptionPane.showMessageDialog(null, "Specified Contact Successfully Deleted ! ! !");
							textFieldToDelete.setText("");
							
							try {																					// TO REFRESH THE MASTER LIST AFTER SUCCESSFUL DELETION
								Connect.selectFromMasterListToDel();
								if (Connect.checkMasterListDel() == false)	{
									JOptionPane.showMessageDialog(null, "No More Church Member Record in Database ! ! !");
								}
								else	{
									JOptionPane.showMessageDialog(null, "Result Table has been Refreshed, Look through for your confirmation ! ! !");
								}
							} 	catch (SQLException | IOException e1) 	{
									e1.printStackTrace();
								}							
						}
						else	{
							JOptionPane.showMessageDialog(null, "Specified Contact Does NOT Exist / No Contact Deleted ! ! !");
							textFieldToDelete.setText("");
						}
					} 	catch (Exception e1)	{
							JOptionPane.showMessageDialog(null, "Specified Contact Does NOT Exist / No Contact Deleted ! ! !");
							textFieldToDelete.setText("");
							e1.printStackTrace();
						}
				}
			}
		});
		btnDelete.setFont(new Font("Verdana", Font.BOLD, 14));
		btnDelete.setBounds(623, 679, 407, 33);
		panelDelete.add(btnDelete);
		
		JLabel lblSearchByFull = new JLabel("Search by \"MEMBER ID\" to Delete from Church Member Contacts List");
		lblSearchByFull.setForeground(Color.RED);
		lblSearchByFull.setFont(new Font("Verdana", Font.BOLD, 14));
		lblSearchByFull.setBounds(68, 646, 594, 32);
		panelDelete.add(lblSearchByFull);
		
		JLabel lblWarningDeleteingAny_1 = new JLabel("Warning: deleteing any contact information from the Master List will also delete from the Church Donation List, if it exists");
		lblWarningDeleteingAny_1.setForeground(Color.RED);
		lblWarningDeleteingAny_1.setFont(new Font("Verdana", Font.BOLD, 12));
		lblWarningDeleteingAny_1.setBounds(68, 717, 962, 25);
		panelDelete.add(lblWarningDeleteingAny_1);
		
		JLabel lblSearchByFull_1 = new JLabel("Search by \"DONATION ID\" to Delete from Church Donations List");
		lblSearchByFull_1.setForeground(Color.BLUE);
		lblSearchByFull_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 14));
		lblSearchByFull_1.setBounds(68, 789, 673, 25);
		panelDelete.add(lblSearchByFull_1);
		
		textFieldDelDonor = new JTextField();
		textFieldDelDonor.setForeground(Color.BLUE);
		textFieldDelDonor.setFont(new Font("Verdana", Font.PLAIN, 14));
		textFieldDelDonor.setColumns(10);
		textFieldDelDonor.setBounds(68, 827, 526, 33);
		panelDelete.add(textFieldDelDonor);
		
		JButton buttonDelDonor = new JButton("<<    Delete From Church Donation List");
		buttonDelDonor.setForeground(Color.BLUE);
		buttonDelDonor.addActionListener(new ActionListener() {									// DELETE BUTTON FOR CHURCH DONATION LIST
			public void actionPerformed(ActionEvent e) {
				
				String donationID_ToDeleteDon = textFieldDelDonor.getText().toUpperCase();
				
				if (donationID_ToDeleteDon.equals(""))	{
					JOptionPane.showMessageDialog(null, "Search Field is Empty ! ! !");
				}
				else	{
				
					try {
						boolean delDon = Connect.deleteChurchDonation(donationID_ToDeleteDon);
					
						if (delDon == true)	{
							JOptionPane.showMessageDialog(null, "Specified Date of Donation Successfully Deleted ! ! !");
							textFieldDelDonor.setText("");
							
							try {																		// TO REFRESH THE CHURCH DONATIONS DATA AFTER DELETION
								Connect.viewChurchDonationsToDel();
								if (Connect.checkMasterListDel() == false)	{
									JOptionPane.showMessageDialog(null, "No More Church Donations Record in Database ! ! !");
								}
								else	{
									JOptionPane.showMessageDialog(null, "Result Table has been Refreshed, Look through for your confirmation ! ! !");
								}
							} 	catch (SQLException | IOException e1) {
									e1.printStackTrace();
								}
						}
						else	{
							JOptionPane.showMessageDialog(null, "Specified Date of Donation Does NOT Exist / No Donation Deleted ! ! !");
							textFieldDelDonor.setText("");
						}
					} 	catch (Exception e1)	{
							JOptionPane.showMessageDialog(null, "Specified Date of Donation Does NOT Exist / No Donation Deleted ! ! !");
							textFieldDelDonor.setText("");
							e1.printStackTrace();
						}
				}
				
			}
		});
		buttonDelDonor.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 14));
		buttonDelDonor.setBounds(623, 827, 407, 33);
		panelDelete.add(buttonDelDonor);
		
		JLabel lblDeleteInformationFrom = new JLabel("DELETE INFORMATION FROM THE DATABASE");
		lblDeleteInformationFrom.setFont(new Font("Verdana", Font.BOLD, 18));
		lblDeleteInformationFrom.setBounds(317, 38, 487, 32);
		panelDelete.add(lblDeleteInformationFrom);
		
		tableViewToDelete = new JTable();
		tableViewToDelete.setFont(new Font("Verdana", Font.PLAIN, 14));
		
		JScrollPane scrollPane_1ToDel = new JScrollPane(tableViewToDelete);
		scrollPane_1ToDel.setBounds(24, 191, 1033, 442);
		panelDelete.add(scrollPane_1ToDel);
		
		JButton button = new JButton("Click to View All Church Members");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connect.selectFromMasterListToDel();
					if (Connect.checkMasterListDel() == false)	{
						JOptionPane.showMessageDialog(null, "No Church Member Record in Database ! ! !");
					}
				} 	catch (SQLException | IOException e1) 	{
						e1.printStackTrace();
					}
			}
		});
		button.setForeground(Color.RED);
		button.setFont(new Font("Verdana", Font.BOLD, 13));
		button.setBounds(24, 138, 362, 33);
		panelDelete.add(button);
		
		JButton button_1 = new JButton("Click to View All Church Donations");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connect.viewChurchDonationsToDel();
					if (Connect.checkMasterListDel() == false)	{
						JOptionPane.showMessageDialog(null, "No Church Donations Record in Database ! ! !");
					}
				} catch (SQLException | IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		button_1.setForeground(Color.BLUE);
		button_1.setFont(new Font("Verdana", Font.BOLD, 13));
		button_1.setBounds(402, 138, 362, 33);
		panelDelete.add(button_1);
		
		JLabel lblToDeleteFrom = new JLabel("To Delete from Church Donation List without Deleting from the Master List use the Search Field and Delete Button below");
		lblToDeleteFrom.setForeground(Color.BLUE);
		lblToDeleteFrom.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
		lblToDeleteFrom.setBounds(68, 763, 883, 25);
		panelDelete.add(lblToDeleteFrom);
		
		JLabel lblWarningBeforeDeleting = new JLabel("Warning: before deleting any data from the database, kindly click on the either buttons below to view the data to be sure of what to delete");
		lblWarningBeforeDeleting.setBackground(Color.BLACK);
		lblWarningBeforeDeleting.setForeground(Color.RED);
		lblWarningBeforeDeleting.setFont(new Font("Verdana", Font.BOLD, 13));
		lblWarningBeforeDeleting.setBounds(24, 100, 1033, 25);
		panelDelete.add(lblWarningBeforeDeleting);
	}
}		// END OF CLASS