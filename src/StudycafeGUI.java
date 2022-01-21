import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;

public class StudycafeGUI {
	private JFrame frame;
	private JPanel Main;
	private JTextField txtJavaStudycafe;
	private JPanel UserMode;
	private JTable Usermode_RoomTable;
	private JButton btn_EmptyRoom_1;
	private JButton btn_UsingRoom_1;
	private JButton btn_AllRoom_1;
	private JPanel PasswordMode;
	private JTextField textField_Password;
	private JTextField EnterPassword;
	private JTabbedPane ManagerMode;
	private JPanel ManagerMode_1;
	private JPanel ManagerMode_2;
	private JTable AllRoom_2;
	private JButton btn_DeleteRoom;
	private JTextField RoomSize;
	private JTextField RoomNum;
	private JTextField RoomPrice;
	private JTextField textField_RoomSize;
	private JTextField textField_RoomNum;
	private JTextField textField_RoomPrice;
	private JButton btn_MakeRoom;
	private JButton btn_UpdateRoom;
	private JButton btn_ManagerToMain_1;
	private JButton btn_EmptyRoom_2;
	private JButton btn_UsingRoom_2;
	private JButton btn_AllRoom;
	private JButton btn_Save;
	private JTextField Name;
	private JTextField PhoneNum;
	private JTextField textField_Name;
	private JTextField textField_PhoneNum;
	private JTextField textField_Notice;
	private JButton btn_TotalIncome;
	private JButton btn_MonthIncome;
	private JButton btn_DailyIncome;
	private JTextField Month_1;
	private JTextField Month_2;
	private JTextField Date;
	private JTextField textField_Month_1;
	private JTextField textField_Month_2;
	private JTextField textField_Date;
	private JButton btn_UserToMain;
	private JButton btn_PasswordToMain;
	private JButton btn_ManagerToMain_2;
	private JTextField RoomNum_1;
	
	public void ReadData(Management studycafe) {
		try {
			ObjectInputStream in = null;
			studycafe.readStudycafeData(in);
		}
		catch (FileNotFoundException fnfe) {
			JOptionPane.showMessageDialog(null, "파일이 없습니다!");
		}
		catch (EOFException eofe) {
			JOptionPane.showMessageDialog(null, "파일 끝...");
		}
		catch (IOException ioe) {
			JOptionPane.showMessageDialog(null, "파일을 읽을 수 없습니다!");
		}
		catch (ClassNotFoundException cnfe) {
			JOptionPane.showMessageDialog(null, "해당 클래스가 존재하지 않습니다!");
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error");
		}
	}
	
	public void SaveData(Management studycafe) {
		try {
			ObjectOutputStream out = null;
			studycafe.writeStudycafeData(out);
		}
		catch (IOException ioe) {
			System.out.println("\n파일로 출력할 수 없습니다.");
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudycafeGUI window = new StudycafeGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StudycafeGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Management studycafe = new Management();
		ReadData(studycafe);
		
		String colNames[] = { "수용 인원", "스터디룸 번호", "가격 (분 단위)", "이용 여부" };
		
		DefaultTableModel model = new DefaultTableModel(colNames, 0);
		for (int i = 0; i < studycafe.getRoomCount(); i++)
			((DefaultTableModel) model).addRow(studycafe.searchRoom(i));
		
		DefaultTableModel model_emptyRoom = new DefaultTableModel(colNames, 0);
		for (int i =0 ; i < studycafe.getRoomCount(); i++)
			((DefaultTableModel) model_emptyRoom).addRow(studycafe.searchEmptyRoom_2(i));
		
		DefaultTableModel model_usingRoom = new DefaultTableModel(colNames, 0);
		for (int i =0 ; i < studycafe.getRoomCount(); i++)
			((DefaultTableModel) model_usingRoom).addRow(studycafe.searchUsingRoom_2(i));
		
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Main = new JPanel();
		Main.setBackground(new Color(255, 240, 245));
		Main.setBounds(0, 0, 786, 463);
		frame.getContentPane().add(Main);
		Main.setLayout(null);
		
		txtJavaStudycafe = new JTextField();
		txtJavaStudycafe.setBackground(new Color(255, 240, 245));
		txtJavaStudycafe.setEditable(false);
		txtJavaStudycafe.setBounds(0, 140, 786, 76);
		txtJavaStudycafe.setBorder(null);
		txtJavaStudycafe.setHorizontalAlignment(SwingConstants.CENTER);
		txtJavaStudycafe.setFont(new Font("a아시아헤드4", Font.PLAIN, 60));
		txtJavaStudycafe.setText("JAVA STUDYCAFE");
		Main.add(txtJavaStudycafe);
		txtJavaStudycafe.setColumns(10);
		
		JButton btn_MainToUser = new JButton("\uC774\uC6A9\uD558\uAE30");
		btn_MainToUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.setVisible(false);
				UserMode.setVisible(true);
			}
		});
		btn_MainToUser.setBackground(Color.BLACK);
		btn_MainToUser.setForeground(Color.WHITE);
		btn_MainToUser.setFont(new Font("a아시아헤드3", Font.PLAIN, 30));
		btn_MainToUser.setBounds(182, 250, 170, 70);
		Main.add(btn_MainToUser);
		
		JButton btn_MainToManager = new JButton("\uAD00\uB9AC\uD558\uAE30");
		btn_MainToManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.setVisible(false);
				PasswordMode.setVisible(true);
			}
		});
		btn_MainToManager.setBackground(Color.BLACK);
		btn_MainToManager.setForeground(Color.WHITE);
		btn_MainToManager.setFont(new Font("a아시아헤드3", Font.PLAIN, 30));
		btn_MainToManager.setBounds(430, 250, 170, 70);
		Main.add(btn_MainToManager);
		
		UserMode = new JPanel();
		UserMode.setBackground(new Color(255, 240, 245));
		UserMode.setBounds(0, 0, 786, 463);
		frame.getContentPane().add(UserMode);
		UserMode.setLayout(null);
		
		Usermode_RoomTable = new JTable(model);
		Usermode_RoomTable.setBackground(new Color(255, 240, 245));
		Usermode_RoomTable.setFont(new Font("a아시아헤드2", Font.PLAIN, 13));
		Usermode_RoomTable.setBounds(243, 5, 300, 0);
		
		JScrollPane Usermode_ScrollPane = new JScrollPane(Usermode_RoomTable);
		Usermode_ScrollPane.setSize(450, 290);
		Usermode_ScrollPane.setLocation(40, 88);
		UserMode.add(Usermode_ScrollPane);
		
		textField_Notice = new JTextField();
		textField_Notice.setHorizontalAlignment(SwingConstants.CENTER);
		textField_Notice.setFont(new Font("a아시아헤드2", Font.PLAIN, 15));
		textField_Notice.setBackground(new Color(255, 240, 245));
		textField_Notice.setText("\uD1F4\uC2E4 \uC2DC\uC5D0\uB294 \uC804\uD654\uBC88\uD638\uB9CC \uC785\uB825\uD574 \uC8FC\uC138\uC694");
		textField_Notice.setEditable(false);
		textField_Notice.setBounds(512, 137, 235, 25);
		textField_Notice.setBorder(null);
		UserMode.add(textField_Notice);
		textField_Notice.setColumns(10);
		
		textField_Name = new JTextField();
		textField_Name.setBackground(new Color(255, 240, 245));
		textField_Name.setFont(new Font("a아시아헤드2", Font.PLAIN, 15));
		textField_Name.setText("\uC774\uB984");
		textField_Name.setEditable(false);
		textField_Name.setBounds(522, 191, 40, 25);
		textField_Name.setBorder(null);
		UserMode.add(textField_Name);
		textField_Name.setColumns(10);
		
		textField_PhoneNum = new JTextField();
		textField_PhoneNum.setBackground(new Color(255, 240, 245));
		textField_PhoneNum.setFont(new Font("a아시아헤드2", Font.PLAIN, 15));
		textField_PhoneNum.setEditable(false);
		textField_PhoneNum.setText("\uC804\uD654\uBC88\uD638");
		textField_PhoneNum.setBounds(522, 226, 60, 25);
		textField_PhoneNum.setBorder(null);
		UserMode.add(textField_PhoneNum);
		textField_PhoneNum.setColumns(10);
		
		Name = new JTextField();
		Name.setBounds(636, 191, 106, 21);
		UserMode.add(Name);
		Name.setColumns(10);
		
		PhoneNum = new JTextField();
		PhoneNum.setBounds(636, 226, 106, 21);
		UserMode.add(PhoneNum);
		PhoneNum.setColumns(10);
		
		JButton btn_CheckIn = new JButton("\uC785\uC2E4\uD558\uAE30");
		btn_CheckIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = Usermode_RoomTable.getSelectedRow();
				String roomNum = String.valueOf(model.getValueAt(row, 1));
				String name = Name.getText();
				String phoneNum = PhoneNum.getText();
				
				try {
					if (!name.equals("") && !phoneNum.equals("")) {
						studycafe.checkIn(roomNum, name, phoneNum);
						model.setValueAt("이용 중", row, 3);
						
						GregorianCalendar calendar = new GregorianCalendar();
						
						int year = calendar.get(Calendar.YEAR);
						int month = calendar.get(Calendar.MONTH) + 1;
						int date = calendar.get(Calendar.DATE);
						int amPm = calendar.get(Calendar.AM_PM);
						int hour = calendar.get(Calendar.HOUR);
						int min = calendar.get(Calendar.MINUTE);
						String sAmPm = amPm == Calendar.AM ? "오전" : "오후";
						
						String str = "# " + roomNum + "번 스터디룸\n" + "# " + year + "년 " + month + "월 " + date + "일 "
								+ sAmPm + " " + hour + "시 " + min + "분\n" + "입실 처리가 완료되었습니다!";
						JOptionPane.showMessageDialog(null, str);
						
						SaveData(studycafe);
					}
					else
						JOptionPane.showMessageDialog(null, "정보를 입력해 주세요!");
				}
				catch (Exception error) {
					JOptionPane.showMessageDialog(null, "입실이 불가능합니다!");
				}
			}
		});
		
		btn_CheckIn.setBackground(new Color(255, 182, 193));
		btn_CheckIn.setForeground(Color.DARK_GRAY);
		btn_CheckIn.setFont(new Font("a아시아헤드3", Font.PLAIN, 17));
		btn_CheckIn.setBounds(522, 286, 100, 40);
		btn_CheckIn.setBorder(null);
		UserMode.add(btn_CheckIn);
		
		JButton btn_CheckOut = new JButton("\uD1F4\uC2E4\uD558\uAE30");
		btn_CheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = Usermode_RoomTable.getSelectedRow();
				String roomNum = String.valueOf(model.getValueAt(row, 1));
				String phoneNum = PhoneNum.getText();
				
				try {
					if (!phoneNum.equals("")) {
						studycafe.checkOut(roomNum, phoneNum);
						model.setValueAt("이용 가능", row, 3);
						
						GregorianCalendar calendar = new GregorianCalendar();
						
						int payment = studycafe.getPayment(roomNum);
						int year = calendar.get(Calendar.YEAR);
						int month = calendar.get(Calendar.MONTH) + 1;
						int date = calendar.get(Calendar.DATE);
						int amPm = calendar.get(Calendar.AM_PM);
						int hour = calendar.get(Calendar.HOUR);
						int min = calendar.get(Calendar.MINUTE);
						String sAmPm = amPm == Calendar.AM ? "오전" : "오후";
						
						String str = "# " + roomNum + "번 스터디룸\n" + "# " + year + "년 " + month + "월 " + date + "일 "
								+ sAmPm + " " + hour + "시 " + min + "분\n" + "# 지불 금액 : "+ payment + "\n"
								+ "퇴실 처리가 완료되었습니다!";
						JOptionPane.showMessageDialog(null, str);
						
						SaveData(studycafe);
					}
					else
						JOptionPane.showMessageDialog(null, "전화번호를 입력해 주세요!");
				}
				catch (Exception error) {
					JOptionPane.showMessageDialog(null, "퇴실이 불가능합니다!");
				}
			}
		});
		btn_CheckOut.setBackground(new Color(255, 182, 193));
		btn_CheckOut.setForeground(Color.DARK_GRAY);
		btn_CheckOut.setFont(new Font("a아시아헤드3", Font.PLAIN, 17));
		btn_CheckOut.setBounds(642, 286, 100, 40);
		btn_CheckOut.setBorder(null);
		UserMode.add(btn_CheckOut);
		
		btn_EmptyRoom_1 = new JButton("\uC774\uC6A9 \uAC00\uB2A5 \uC2A4\uD130\uB514\uB8F8");
		btn_EmptyRoom_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (model.getRowCount() > 0) {
					for (int row = model.getRowCount() - 1; row >= 0; row--)
						model.removeRow(row);
					
					for (int i =0 ; i < studycafe.getRoomCount(); i++)
						if (studycafe.getRoom().get(i).isEmpty())
							model.addRow(studycafe.searchEmptyRoom_2(i));
				}
			}
		});
		btn_EmptyRoom_1.setBackground(new Color(255, 182, 193));
		btn_EmptyRoom_1.setForeground(Color.DARK_GRAY);
		btn_EmptyRoom_1.setFont(new Font("a아시아헤드2", Font.PLAIN, 13));
		btn_EmptyRoom_1.setBounds(42, 53, 120, 25);
		btn_EmptyRoom_1.setBorder(null);
		UserMode.add(btn_EmptyRoom_1);
		
		btn_UsingRoom_1 = new JButton("\uC774\uC6A9 \uC911\uC778 \uC2A4\uD130\uB514\uB8F8");
		btn_UsingRoom_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (model.getRowCount() > 0) {
					for (int row = model.getRowCount() - 1; row >= 0; row--)
						model.removeRow(row);
					
					for (int i =0 ; i < studycafe.getRoomCount(); i++)
						if (!studycafe.getRoom().get(i).isEmpty())
							model.addRow(studycafe.searchUsingRoom_2(i));
				}
			}
		});
		btn_UsingRoom_1.setForeground(Color.DARK_GRAY);
		btn_UsingRoom_1.setFont(new Font("a아시아헤드2", Font.PLAIN, 13));
		btn_UsingRoom_1.setBackground(new Color(255, 182, 193));
		btn_UsingRoom_1.setBounds(174, 53, 120, 25);
		btn_UsingRoom_1.setBorder(null);
		UserMode.add(btn_UsingRoom_1);
		
		btn_AllRoom_1 = new JButton("\uC804\uCCB4 \uC2A4\uD130\uB514\uB8F8");
		btn_AllRoom_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (model.getRowCount() > 0) {
					for (int row = model.getRowCount() - 1; row >= 0; row--)
						model.removeRow(row);
					
					for (int i =0 ; i < studycafe.getRoomCount(); i++)
						model.addRow(studycafe.searchRoom(i));
				}
			}
		});
		btn_AllRoom_1.setForeground(Color.DARK_GRAY);
		btn_AllRoom_1.setFont(new Font("a아시아헤드2", Font.PLAIN, 13));
		btn_AllRoom_1.setBackground(new Color(255, 182, 193));
		btn_AllRoom_1.setBounds(307, 53, 120, 25);
		btn_AllRoom_1.setBorder(null);
		UserMode.add(btn_AllRoom_1);
		
		btn_UserToMain = new JButton("\uBA54\uC778\uC73C\uB85C \uB3CC\uC544\uAC00\uAE30");
		btn_UserToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserMode.setVisible(false);
				Main.setVisible(true);
			}
		});
		
		JButton btn_SearchRoom = new JButton("\uC2A4\uD130\uB514\uB8F8 \uAC80\uC0C9");
		btn_SearchRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String roomNum = RoomNum_1.getText();
				
				if (model.getRowCount() > 0) {
					for (int row = model.getRowCount() - 1; row >= 0; row--)
						model.removeRow(row);
					
					for (int i =0 ; i < studycafe.getRoomCount(); i++)
						if (studycafe.getRoom().get(i).getRoomNum().equals(roomNum))
							model.addRow(studycafe.searchRoom(i));
				}
			}
		});
		
		RoomNum_1 = new JTextField();
		RoomNum_1.setBounds(40, 388, 106, 25);
		UserMode.add(RoomNum_1);
		RoomNum_1.setColumns(10);
		btn_SearchRoom.setForeground(Color.DARK_GRAY);
		btn_SearchRoom.setFont(new Font("a아시아헤드2", Font.PLAIN, 13));
		btn_SearchRoom.setBackground(new Color(255, 182, 193));
		btn_SearchRoom.setBounds(158, 388, 120, 25);
		btn_SearchRoom.setBorder(null);
		UserMode.add(btn_SearchRoom);
		btn_UserToMain.setForeground(Color.DARK_GRAY);
		btn_UserToMain.setFont(new Font("a아시아헤드2", Font.PLAIN, 13));
		btn_UserToMain.setBorder(null);
		btn_UserToMain.setBackground(new Color(255, 182, 193));
		btn_UserToMain.setBounds(627, 53, 120, 25);
		UserMode.add(btn_UserToMain);
		
		UserMode.setVisible(false);
		
		PasswordMode = new JPanel();
		PasswordMode.setBackground(new Color(255, 240, 245));
		PasswordMode.setBounds(0, 0, 786, 463);
		frame.getContentPane().add(PasswordMode);
		PasswordMode.setLayout(null);
		
		textField_Password = new JTextField();
		textField_Password.setForeground(Color.DARK_GRAY);
		textField_Password.setFont(new Font("a아시아헤드2", Font.PLAIN, 20));
		textField_Password.setBackground(new Color(255, 240, 245));
		textField_Password.setHorizontalAlignment(SwingConstants.CENTER);
		textField_Password.setText("\uBE44\uBC00\uBC88\uD638\uB97C \uC785\uB825\uD574 \uC8FC\uC138\uC694");
		textField_Password.setEditable(false);
		textField_Password.setBounds(296, 190, 215, 30);
		textField_Password.setBorder(null);
		PasswordMode.add(textField_Password);
		textField_Password.setColumns(10);
		
		EnterPassword = new JTextField();
		EnterPassword.setBounds(294, 240, 106, 21);
		PasswordMode.add(EnterPassword);
		EnterPassword.setColumns(10);
		
		JButton btn_EnterPassword = new JButton("\uC785\uB825");
		btn_EnterPassword.setBackground(new Color(255, 182, 193));
		btn_EnterPassword.setFont(new Font("a아시아헤드2", Font.PLAIN, 13));
		btn_EnterPassword.setBorder(null);
		btn_EnterPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int password = Integer.parseInt(EnterPassword.getText());
					if (password == studycafe.getPassword()) {
						PasswordMode.setVisible(false);
						ManagerMode.setVisible(true);
					}
					else
						JOptionPane.showMessageDialog(null, "비밀번호를 다시 입력해 주세요!");
				}
				catch (Exception error) {
					JOptionPane.showMessageDialog(null, "비밀번호를 다시 입력해 주세요!");
				}
			}
		});
		btn_EnterPassword.setBounds(412, 238, 95, 25);
		PasswordMode.add(btn_EnterPassword);
		
		btn_PasswordToMain = new JButton("\uBA54\uC778\uC73C\uB85C \uB3CC\uC544\uAC00\uAE30");
		btn_PasswordToMain.setForeground(Color.DARK_GRAY);
		btn_PasswordToMain.setFont(new Font("a아시아헤드2", Font.PLAIN, 13));
		btn_PasswordToMain.setBackground(new Color(255, 182, 193));
		btn_PasswordToMain.setBorder(null);
		btn_PasswordToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PasswordMode.setVisible(false);
				Main.setVisible(true);
			}
		});
		btn_PasswordToMain.setBounds(634, 30, 120, 25);
		PasswordMode.add(btn_PasswordToMain);
		
		ManagerMode = new JTabbedPane(JTabbedPane.TOP);
		ManagerMode.setBackground(new Color(255, 240, 245));
		ManagerMode.setFont(new Font("a아시아헤드3", Font.PLAIN, 15));
		ManagerMode.setBounds(0, 0, 786, 463);
		frame.getContentPane().add(ManagerMode);
		
		ManagerMode_1 = new JPanel();
		ManagerMode_1.setForeground(Color.DARK_GRAY);
		ManagerMode_1.setBackground(new Color(255, 240, 245));
		ManagerMode.addTab("관리", null, ManagerMode_1, null);
		ManagerMode_1.setLayout(null);
		
		AllRoom_2 = new JTable(model);
		AllRoom_2.setBounds(243, 5, 300, 0);
		// ManagerMode_1.add(table_2);
		
		JScrollPane ManagerMode_ScrollPane = new JScrollPane(AllRoom_2);
		ManagerMode_ScrollPane.setSize(450, 230);
		ManagerMode_ScrollPane.setLocation(40, 94);
		ManagerMode_1.add(ManagerMode_ScrollPane);
		
		textField_RoomSize = new JTextField();
		textField_RoomSize.setForeground(Color.DARK_GRAY);
		textField_RoomSize.setBackground(new Color(255, 240, 245));
		textField_RoomSize.setFont(new Font("a아시아헤드2", Font.PLAIN, 15));
		textField_RoomSize.setHorizontalAlignment(SwingConstants.CENTER);
		textField_RoomSize.setText("\uC218\uC6A9 \uC778\uC6D0");
		textField_RoomSize.setEditable(false);
		textField_RoomSize.setColumns(10);
		textField_RoomSize.setBounds(520, 141, 106, 21);
		textField_RoomSize.setBorder(null);
		ManagerMode_1.add(textField_RoomSize);
		
		textField_RoomNum = new JTextField();
		textField_RoomNum.setForeground(Color.DARK_GRAY);
		textField_RoomNum.setBackground(new Color(255, 240, 245));
		textField_RoomNum.setFont(new Font("a아시아헤드2", Font.PLAIN, 15));
		textField_RoomNum.setText("\uC2A4\uD130\uB514\uB8F8 \uBC88\uD638");
		textField_RoomNum.setHorizontalAlignment(SwingConstants.CENTER);
		textField_RoomNum.setEditable(false);
		textField_RoomNum.setColumns(10);
		textField_RoomNum.setBounds(520, 173, 106, 21);
		textField_RoomNum.setBorder(null);
		ManagerMode_1.add(textField_RoomNum);
		
		textField_RoomPrice = new JTextField();
		textField_RoomPrice.setForeground(Color.DARK_GRAY);
		textField_RoomPrice.setFont(new Font("a아시아헤드2", Font.PLAIN, 15));
		textField_RoomPrice.setBackground(new Color(255, 240, 245));
		textField_RoomPrice.setText("\uAC00\uACA9 (\uBD84 \uB2E8\uC704)");
		textField_RoomPrice.setHorizontalAlignment(SwingConstants.CENTER);
		textField_RoomPrice.setEditable(false);
		textField_RoomPrice.setColumns(10);
		textField_RoomPrice.setBounds(520, 204, 106, 21);
		textField_RoomPrice.setBorder(null);
		ManagerMode_1.add(textField_RoomPrice);
		
		RoomSize = new JTextField();
		RoomSize.setBounds(638, 139, 106, 21);
		ManagerMode_1.add(RoomSize);
		RoomSize.setColumns(10);
		
		RoomNum = new JTextField();
		RoomNum.setBounds(638, 171, 106, 21);
		ManagerMode_1.add(RoomNum);
		RoomNum.setColumns(10);
		
		RoomPrice = new JTextField();
		RoomPrice.setBounds(638, 202, 106, 21);
		ManagerMode_1.add(RoomPrice);
		RoomPrice.setColumns(10);
		
		btn_MakeRoom = new JButton("\uC2A4\uD130\uB514\uB8F8 \uC0DD\uC131");
		btn_MakeRoom.setForeground(Color.DARK_GRAY);
		btn_MakeRoom.setFont(new Font("a아시아헤드2", Font.PLAIN, 13));
		btn_MakeRoom.setBackground(new Color(255, 182, 193));
		btn_MakeRoom.setBounds(506, 252, 120, 25);
		btn_MakeRoom.setBorder(null);
		btn_MakeRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String arr[] = new String[4];
					arr[0] = RoomSize.getText();
					arr[1] = RoomNum.getText();
					arr[2] = RoomPrice.getText();
					arr[3] = "이용 가능";
					
					studycafe.addRoom(Integer.parseInt(arr[0]), arr[1], Integer.parseInt(arr[2]));
					model.addRow(arr);
				}
				catch (Exception error) {
					JOptionPane.showMessageDialog(null, "스터디룸을 생성할 수 없습니다!");
					System.out.println(error);
				}
			}
		});
		ManagerMode_1.add(btn_MakeRoom);
		
		btn_UpdateRoom = new JButton("\uC2A4\uD130\uB514\uB8F8 \uC218\uC815");
		btn_UpdateRoom.setForeground(Color.DARK_GRAY);
		btn_UpdateRoom.setFont(new Font("a아시아헤드2", Font.PLAIN, 13));
		btn_UpdateRoom.setBackground(new Color(255, 182, 193));
		btn_UpdateRoom.setBounds(638, 252, 120, 25);
		btn_UpdateRoom.setBorder(null);
		btn_UpdateRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int row = AllRoom_2.getSelectedRow();
					String roomNum = String.valueOf(model.getValueAt(row, 1));
					
					if (!RoomSize.getText().equals("")) {
						int newRoomSize = Integer.parseInt(RoomSize.getText());
						studycafe.updateRoomSize(roomNum, newRoomSize);
						model.setValueAt(newRoomSize, row, 0);
					}
					if (!RoomNum.getText().equals("")) {
						String newRoomNum = RoomNum.getText();
						studycafe.updateRoomNum(roomNum, newRoomNum);
						model.setValueAt(newRoomNum, row, 1);
					}
					if (!RoomPrice.getText().equals("")) {
						int newRoomPrice = Integer.parseInt(RoomPrice.getText());
						studycafe.updateRoomPrice(roomNum, newRoomPrice);
						model.setValueAt(newRoomPrice, row, 2);
					}
					if (RoomSize.getText().equals("") && RoomNum.getText().equals("") && RoomPrice.getText().equals(""))
						JOptionPane.showMessageDialog(null, "수정할 정보를 입력해 주세요!");
				}
				catch (Exception error) {
					JOptionPane.showMessageDialog(null, "스터디룸을 수정할 수 없습니다!");
					System.out.print(error);
				}
			}
		});
		ManagerMode_1.add(btn_UpdateRoom);
		
		btn_DeleteRoom = new JButton("\uC2A4\uD130\uB514\uB8F8 \uC0AD\uC81C");
		btn_DeleteRoom.setForeground(Color.DARK_GRAY);
		btn_DeleteRoom.setFont(new Font("a아시아헤드2", Font.PLAIN, 13));
		btn_DeleteRoom.setBackground(new Color(255, 182, 193));
		btn_DeleteRoom.setBounds(40, 334, 120, 25);
		btn_DeleteRoom.setBorder(null);
		btn_DeleteRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int row = AllRoom_2.getSelectedRow();
					
					if (row != -1 && model.getValueAt(row, 3) == "이용 가능") {
						String roomNum = String.valueOf(model.getValueAt(row, 1));
						studycafe.deleteRoom(roomNum);
						model.removeRow(row);
					}
					else
						JOptionPane.showMessageDialog(null, "이용 중인 스터디룸은 삭제할 수 없습니다!");
				}
				catch (Exception error) {
					JOptionPane.showMessageDialog(null, "스터디룸을 삭제할 수 없습니다!");
				}
			}
		});
		ManagerMode_1.add(btn_DeleteRoom);
		
		btn_EmptyRoom_2 = new JButton("\uC774\uC6A9 \uAC00\uB2A5 \uC2A4\uD130\uB514\uB8F8");
		btn_EmptyRoom_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (model.getRowCount() > 0) {
					for (int row = model.getRowCount() - 1; row >= 0; row--)
						model.removeRow(row);
					
					for (int i =0 ; i < studycafe.getRoomCount(); i++)
						if (studycafe.getRoom().get(i).isEmpty())
							model.addRow(studycafe.searchEmptyRoom_2(i));
				}
			}
		});
		btn_EmptyRoom_2.setForeground(Color.DARK_GRAY);
		btn_EmptyRoom_2.setBackground(new Color(255, 182, 193));
		btn_EmptyRoom_2.setFont(new Font("a아시아헤드2", Font.PLAIN, 13));
		btn_EmptyRoom_2.setBounds(40, 59, 120, 25);
		btn_EmptyRoom_2.setBorder(null);
		ManagerMode_1.add(btn_EmptyRoom_2);
		
		btn_UsingRoom_2 = new JButton("\uC774\uC6A9 \uC911\uC778 \uC2A4\uD130\uB514\uB8F8");
		btn_UsingRoom_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (model.getRowCount() > 0) {
					for (int row = model.getRowCount() - 1; row >= 0; row--)
						model.removeRow(row);
					
					for (int i =0 ; i < studycafe.getRoomCount(); i++)
						if (!studycafe.getRoom().get(i).isEmpty())
							model.addRow(studycafe.searchUsingRoom_2(i));
				}
			}
		});
		btn_UsingRoom_2.setForeground(Color.DARK_GRAY);
		btn_UsingRoom_2.setFont(new Font("a아시아헤드2", Font.PLAIN, 13));
		btn_UsingRoom_2.setBackground(new Color(255, 182, 193));
		btn_UsingRoom_2.setBounds(172, 59, 120, 25);
		btn_UsingRoom_2.setBorder(null);
		ManagerMode_1.add(btn_UsingRoom_2);
		
		btn_AllRoom = new JButton("\uC804\uCCB4 \uC2A4\uD130\uB514\uB8F8");
		btn_AllRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (model.getRowCount() > 0) {
					for (int row = model.getRowCount() - 1; row >= 0; row--)
						model.removeRow(row);
					
					for (int i =0 ; i < studycafe.getRoomCount(); i++)
						model.addRow(studycafe.searchRoom(i));
				}
			}
		});
		btn_AllRoom.setForeground(Color.DARK_GRAY);
		btn_AllRoom.setFont(new Font("a아시아헤드2", Font.PLAIN, 13));
		btn_AllRoom.setBackground(new Color(255, 182, 193));
		btn_AllRoom.setBounds(304, 59, 120, 25);
		btn_AllRoom.setBorder(null);
		ManagerMode_1.add(btn_AllRoom);
		
		btn_Save = new JButton("\uC800\uC7A5\uD558\uAE30");
		btn_Save.setForeground(Color.DARK_GRAY);
		btn_Save.setFont(new Font("a아시아헤드2", Font.PLAIN, 13));
		btn_Save.setBackground(new Color(255, 182, 193));
		btn_Save.setBounds(172, 334, 120, 25);
		btn_Save.setBorder(null);
		btn_Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaveData(studycafe);
			}
		});
		ManagerMode_1.add(btn_Save);
		
		btn_ManagerToMain_1 = new JButton("\uBA54\uC778\uC73C\uB85C \uB3CC\uC544\uAC00\uAE30");
		btn_ManagerToMain_1.setForeground(Color.DARK_GRAY);
		btn_ManagerToMain_1.setFont(new Font("a아시아헤드2", Font.PLAIN, 13));
		btn_ManagerToMain_1.setBackground(new Color(255, 182, 193));
		btn_ManagerToMain_1.setBorder(null);
		btn_ManagerToMain_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerMode.setVisible(false);
				Main.setVisible(true);
			}
		});
		btn_ManagerToMain_1.setBounds(624, 61, 120, 25);
		ManagerMode_1.add(btn_ManagerToMain_1);
		
		ManagerMode_2 = new JPanel();
		ManagerMode_2.setBackground(new Color(255, 240, 245));
		ManagerMode.addTab("매출", null, ManagerMode_2, null);
		ManagerMode_2.setLayout(null);
		
		btn_TotalIncome = new JButton("\uCD1D \uB9E4\uCD9C");
		btn_TotalIncome.setForeground(Color.DARK_GRAY);
		btn_TotalIncome.setFont(new Font("a아시아헤드2", Font.PLAIN, 13));
		btn_TotalIncome.setBackground(new Color(255, 182, 193));
		btn_TotalIncome.setBounds(180, 153, 120, 25);
		btn_TotalIncome.setBorder(null);
		btn_TotalIncome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int income = studycafe.getTotalIncome();
				String str = "총 매출 : " + income;
				JOptionPane.showMessageDialog(null, str);
			}
		});
		ManagerMode_2.add(btn_TotalIncome);
		
		btn_MonthIncome = new JButton("\uC6D4 \uB9E4\uCD9C");
		btn_MonthIncome.setForeground(Color.DARK_GRAY);
		btn_MonthIncome.setFont(new Font("a아시아헤드2", Font.PLAIN, 13));
		btn_MonthIncome.setBackground(new Color(255, 182, 193));
		btn_MonthIncome.setBounds(180, 188, 120, 25);
		btn_MonthIncome.setBorder(null);
		btn_MonthIncome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int month = Integer.parseInt(Month_1.getText());
					
					if (month >= 1 && month <= 12) {
						int income = studycafe.getMonthIncome(month);
						String str = month + "월 매출 : " + income;
						JOptionPane.showMessageDialog(null, str);
					}
					else
						JOptionPane.showMessageDialog(null, "날짜를 정확하게 입력해 주세요!");
				}
				catch (Exception error) {
					JOptionPane.showMessageDialog(null, "매출을 확인할 수 없습니다!");
				}
			}
		});
		ManagerMode_2.add(btn_MonthIncome);
		
		btn_DailyIncome = new JButton("\uC77C \uB9E4\uCD9C");
		btn_DailyIncome.setForeground(Color.DARK_GRAY);
		btn_DailyIncome.setFont(new Font("a아시아헤드2", Font.PLAIN, 13));
		btn_DailyIncome.setBackground(new Color(255, 182, 193));
		btn_DailyIncome.setBounds(180, 223, 120, 25);
		btn_DailyIncome.setBorder(null);
		btn_DailyIncome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int month = Integer.parseInt(Month_2.getText());
					int date = Integer.parseInt(Date.getText());
					
					if (month >= 1 && month <= 12 && date >= 1 && date <= 31) {
						int income = studycafe.getDailyIncome(month, date);
						String str = month + "월 " + date + "일 매출 : " + income;
						JOptionPane.showMessageDialog(null, str);
					}
					else
						JOptionPane.showMessageDialog(null, "날짜를 정확하게 입력해 주세요!");
				}
				catch (Exception error) {
					JOptionPane.showMessageDialog(null, "매출을 확인할 수 없습니다!");
				}
			}
		});
		ManagerMode_2.add(btn_DailyIncome);
		
		Month_1 = new JTextField();
		Month_1.setBounds(312, 187, 106, 25);
		ManagerMode_2.add(Month_1);
		Month_1.setColumns(10);
		
		Month_2 = new JTextField();
		Month_2.setBounds(312, 222, 106, 25);
		ManagerMode_2.add(Month_2);
		Month_2.setColumns(10);
		
		Date = new JTextField();
		Date.setBounds(464, 222, 106, 25);
		ManagerMode_2.add(Date);
		Date.setColumns(10);
		
		textField_Month_1 = new JTextField();
		textField_Month_1.setFont(new Font("a아시아헤드2", Font.PLAIN, 13));
		textField_Month_1.setBackground(new Color(255, 240, 245));
		textField_Month_1.setText("\uC6D4");
		textField_Month_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_Month_1.setEditable(false);
		textField_Month_1.setColumns(10);
		textField_Month_1.setBounds(422, 190, 30, 21);
		textField_Month_1.setBorder(null);
		ManagerMode_2.add(textField_Month_1);
		
		textField_Month_2 = new JTextField();
		textField_Month_2.setFont(new Font("a아시아헤드2", Font.PLAIN, 13));
		textField_Month_2.setBackground(new Color(255, 240, 245));
		textField_Month_2.setText("\uC6D4");
		textField_Month_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_Month_2.setEditable(false);
		textField_Month_2.setColumns(10);
		textField_Month_2.setBounds(422, 225, 30, 21);
		textField_Month_2.setBorder(null);
		ManagerMode_2.add(textField_Month_2);
		
		textField_Date = new JTextField();
		textField_Date.setFont(new Font("a아시아헤드2", Font.PLAIN, 13));
		textField_Date.setBackground(new Color(255, 240, 245));
		textField_Date.setText("\uC77C");
		textField_Date.setHorizontalAlignment(SwingConstants.CENTER);
		textField_Date.setEditable(false);
		textField_Date.setColumns(10);
		textField_Date.setBounds(575, 225, 30, 21);
		textField_Date.setBorder(null);
		ManagerMode_2.add(textField_Date);
		
		btn_ManagerToMain_2 = new JButton("\uBA54\uC778\uC73C\uB85C \uB3CC\uC544\uAC00\uAE30");
		btn_ManagerToMain_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManagerMode.setVisible(false);
				Main.setVisible(true);
			}
		});
		btn_ManagerToMain_2.setForeground(Color.DARK_GRAY);
		btn_ManagerToMain_2.setFont(new Font("a아시아헤드2", Font.PLAIN, 13));
		btn_ManagerToMain_2.setBackground(new Color(255, 182, 193));
		btn_ManagerToMain_2.setBounds(624, 61, 120, 25);
		btn_ManagerToMain_2.setBorder(null);
		ManagerMode_2.add(btn_ManagerToMain_2);
		
		ManagerMode.setVisible(false);
		PasswordMode.setVisible(false);
	}
}