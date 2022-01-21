import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Calendar;

public class StudyCafeUI {
	public static void main(String[] args) {
		Management studycafe = new Management();
		Scanner scan = new Scanner(System.in);
		
		try {
			ObjectInputStream in = null;
			studycafe.readStudycafeData(in);
		}
		catch (FileNotFoundException fnfe) {
			System.out.println("[ ���� ���� ]\n");
		}
		catch (EOFException eofe) {
			System.out.println("[ ���� ��... ]\n");
		}
		catch (IOException ioe) {
			System.out.println("[ ������ ���� �� ���� ]\n");
		}
		catch (ClassNotFoundException cnfe) {
			System.out.println("[ �ش� Ŭ������ �������� ���� ]\n");
		}
		catch (Exception e) {
			System.out.print("[ Error ]\n");
		}
		
		boolean key = true;
		boolean user = false;
		boolean manager = false;
		int mode; // ���� ��带 �����ϱ� ���� ����
		int menu; // �޴��� �����ϱ� ���� ����
		
		System.out.println("JAVA StudyCafe �Դϴ� :)");
		
		while (key) {
			try {
				System.out.println("\n******* MODE *******");
				System.out.println("1. User\n2. Manager\n3. Exit");
				System.out.println("********************");
				
				mode = scan.nextInt();
				
				switch (mode) {
				// 1��(User) ����
				case 1:
					user = true;
					break;
				// 2��(Manager) ����
				case 2:
					manager = true;
					break;
				// 3��(Exit) ����
				case 3:
					System.out.println("\n�̿��� �ּż� �����մϴ� :)");
					key = false;
					break;
				default:
					System.out.println("[ �߸� �Է��ϼ̽��ϴ� ]");
				}
			}
			// �߸��� ��� ����
			catch (java.util.InputMismatchException e) {
				System.out.println("[ �߸� �Է��ϼ̽��ϴ� ]");
				scan.next();
			}
			
			// user mode
			while (user) {
				try {
					System.out.println("\n******* MENU *******");
					System.out.println("1. Check In\n2. Check Out\n3. Search Empty Room\n4. Return To Main\n5. Exit");
					System.out.println("********************");
					
					menu = scan.nextInt();
					
					String roomNum; // ���͵�� ��ȣ(Room Number) �Է� ����
					String phoneNum;
					int year, month, date, amPm, hour, min;
					
					switch (menu) {
					// 1��(Check In) ���� - �Խ�
					case 1:
						System.out.print("\nRoom Number : ");
						scan.nextLine();
						roomNum = scan.nextLine();
						System.out.print("Name : ");
						String name = scan.nextLine();
						System.out.print("Phone Number : ");
						phoneNum = scan.nextLine();
						
						// Check In ����
						try {
							studycafe.checkIn(roomNum, name, phoneNum);
							
							GregorianCalendar calendar = new GregorianCalendar();
							
							year = calendar.get(Calendar.YEAR);
							month = calendar.get(Calendar.MONTH) + 1;
							date = calendar.get(Calendar.DATE);
							amPm = calendar.get(Calendar.AM_PM);
							hour = calendar.get(Calendar.HOUR);
							min = calendar.get(Calendar.MINUTE);
							String sAmPm = amPm == Calendar.AM ? "����" : "����";
							
							System.out.println("[ �Խ� ó���� �Ϸ�Ǿ����ϴ� ]");
							System.out.println("# " + roomNum + "�� ���͵��");
							System.out.println("# �Խ� �ð� : " + year + "�� " + month + "�� " + date + "�� " + sAmPm + " " + hour + "�� " + min + "��");
						}
						// Check In ����
						catch (Exception e) {
							System.out.println("[ �Խ��� �Ұ����մϴ� ]");
						}
						
						break;
						
					// 2��(Check Out) ���� - ���
					case 2:
						System.out.print("\nRoom Number : ");
						scan.nextLine();
						roomNum = scan.nextLine();
						System.out.print("Phone Number : ");
						phoneNum = scan.nextLine();
						
						// Check Out ����
						try {
							studycafe.checkOut(roomNum, phoneNum);
							int payment = studycafe.getPayment(roomNum);
							
							GregorianCalendar calendar = new GregorianCalendar();
							
							year = calendar.get(Calendar.YEAR);
							month = calendar.get(Calendar.MONTH) + 1;
							date = calendar.get(Calendar.DATE);
							amPm = calendar.get(Calendar.AM_PM);
							hour = calendar.get(Calendar.HOUR);
							min = calendar.get(Calendar.MINUTE);
							String sAmPm = amPm == Calendar.AM ? "����" : "����";
							
							System.out.println("[ ��� ó���� �Ϸ�Ǿ����ϴ� ]");
							System.out.println("# " + roomNum + "�� ���͵��");
							System.out.println("# ��� �ð� : " + year + "�� " + month + "�� " + date + "�� " + sAmPm + " " + hour + "�� " + min + "��");
							System.out.println("# ���� �ݾ� : " + payment + "��");
						}
						// Check Out ����
						catch (Exception e) {
							System.out.println("[ ����� �Ұ����մϴ� ]");
						}
						
						break;
						
					// 3��(Search Empty Room) ���� - �� ���͵�� ã��
					case 3:
						System.out.println("[ �̿� ������ ���͵�� ]");
						
						ArrayList<String> emptyRoom1 = studycafe.searchEmptyRoom(1);
						ArrayList<String> emptyRoom4 = studycafe.searchEmptyRoom(4);
						ArrayList<String> emptyRoom6 = studycafe.searchEmptyRoom(6);
						
						// ��� �ִ� ���͵��
						System.out.println("# 1�ν� : " + emptyRoom1);
						System.out.println("# 4�ν� : " + emptyRoom4);
						System.out.println("# 6�ν� : " + emptyRoom6);
						
						break;
						
					// 4��(Return To Main) ���� - �������� ���ư���
					case 4:
						user = false;
						break;
						
					// 5��(Exit) ���� - ���α׷� ����
					case 5:
						System.out.println("\n�̿��� �ּż� �����մϴ� :)");
						key = false;
						user = false;
						break;
						
					// �߸��� �޴� ����
					default:
						System.out.println("[ �߸� �Է��ϼ̽��ϴ� ]");
					}
				}
				
				// �߸��� �޴� ����
				catch (java.util.InputMismatchException e) {
					System.out.println("[ �߸� �Է��ϼ̽��ϴ� ]");
					scan.next();
				}
			}
			
			// manager mode
			while (manager) {
				try {
					int password; // ��й�ȣ �Է� ����
					
					System.out.print("password : ");
					password = scan.nextInt();
					
					// ��й�ȣ ��ġ
					if (studycafe.getPassword() == password) {
						boolean success = true;
						
						while (success) {
							try {
								System.out.println("\n******* MENU *******");
								System.out.println("1. Create Room\n2. Delete Room\n3. Update Room\n4. Search Room\n5. Check Income\n6. Return To Main\n7. Exit");
								System.out.println("********************");
								
								menu = scan.nextInt();
								
								int roomSize; // ���͵�� ũ��(roomSize) �Է� ����
								String roomNum; // ���͵�� ��ȣ(roomNum) �Է� ����
								int input;
								
								switch (menu) {
								// 1��(Create Room) ���� - ���͵�� ����
								case 1:
									try {
										System.out.print("Room Size : ");
										roomSize = scan.nextInt();
										scan.nextLine();
										System.out.print("Room Number : ");
										roomNum = scan.nextLine();
										System.out.print("Room Price In Minutes : ");
										int price = scan.nextInt();
										
										switch (roomSize) {
										// 1�ν� ���͵�� ����
										case 1:
											if (!studycafe.searchRoom(roomNum)) {
												studycafe.addRoom(roomSize, roomNum, price);
												System.out.println("[ " + roomNum + "�� ���͵���� �����Ǿ����ϴ� ]");
											}
											else {
												System.out.println("[ ������ ��ȣ�� ���͵���� �ֽ��ϴ� ]");
											}
											
											break;
											
										// 4�ν� ���͵�� ����
										case 4:
											if (!studycafe.searchRoom(roomNum)) {
												studycafe.addRoom(roomSize, roomNum, price);
												System.out.println("[ " + roomNum + "�� ���͵���� �����Ǿ����ϴ� ]");
											}
											else {
												System.out.println("[ ������ ��ȣ�� ���͵���� �ֽ��ϴ� ]");
											}
											
											break;
											
										// 6�ν� ���͵�� ����
										case 6:
											if (!studycafe.searchRoom(roomNum)) {
												studycafe.addRoom(roomSize, roomNum, price);
												System.out.println("[ " + roomNum + "�� ���͵���� �����Ǿ����ϴ� ]");
											}
											else {
												System.out.println("[ ������ ��ȣ�� ���͵���� �ֽ��ϴ� ]");
											}
											
											break;
											
										// �߸��� �Է�
										default:
											System.out.println("[ ���͵���� 1�ν�, 4�ν�, 6�νǸ� ������ �� �ֽ��ϴ� ]");
										}
									}
									// ���� ������ ������ �ʰ��Ͽ��� ���
									catch (java.lang.ArrayIndexOutOfBoundsException e) {
										System.out.println("[ �� �̻� ���͵���� ������ �� �����ϴ� ]");
									}
									
									break;
									
								// 2��(Delete Room) ���� - ���͵�� ����
								case 2:
									System.out.print("Room Number : ");
									scan.nextLine();
									roomNum = scan.nextLine();
									
									try {
										studycafe.deleteRoom(roomNum);
										System.out.println("[ " + roomNum + "�� ���͵���� �����Ǿ����ϴ� ]");
									}
									catch (Exception e) {
										System.out.println("[ ���͵���� ������ �� �����ϴ� ]");
									}
									
									break;
								
								// 3��(Update Room) ���� - ���͵�� ����
								case 3:
									System.out.print("Room Number : ");
									scan.nextLine();
									roomNum = scan.nextLine();
									
									System.out.print("New Room Size : ");
									int newRoomSize = scan.nextInt();
									System.out.print("New Room Number : ");
									scan.nextLine();
									String newRoomNum = scan.nextLine();
									System.out.print("New Room Price In Minutes : ");
									// scan.nextLine();
									int newPrice = scan.nextInt();
									
									try {
										studycafe.updateRoom(roomNum, newRoomSize, newRoomNum, newPrice);
										System.out.println("[ ���͵�� ������ �����Ǿ����ϴ� ]");
									}
									catch (Exception e) {
										System.out.println("[ ���͵�� ������ ������ �� �����ϴ� ]");
									}
									
									break;
									
								// 4��(Search Room) ���� - ���͵�� ã��
								case 4:
									System.out.println("1. Empty Room\n2. Using Room");
									input = scan.nextInt();
									
									switch (input) {
									// 1��(Empty Room) ���� - ��� �ִ� ���͵�� ã��
									case 1:
										System.out.println("[ �̿� ������ ���͵�� ]");
										
										ArrayList<String> emptyRoom1 = studycafe.searchEmptyRoom(1);
										ArrayList<String> emptyRoom4 = studycafe.searchEmptyRoom(4);
										ArrayList<String> emptyRoom6 = studycafe.searchEmptyRoom(6);
										
										// ��� �ִ� ���͵��
										System.out.println("# 1�ν� : " + emptyRoom1);
										System.out.println("# 4�ν� : " + emptyRoom4);
										System.out.println("# 6�ν� : " + emptyRoom6);
										
										break;
									
									// 2��(Using Room) ���� - �̿� ���� ���͵�� ã��
									case 2:
										System.out.println("[ �̿� ���� ���͵�� ]");
										
										ArrayList<String> usingRoom1 = studycafe.searchUsingRoom(1);
										ArrayList<String> usingRoom4 = studycafe.searchUsingRoom(4);
										ArrayList<String> usingRoom6 = studycafe.searchUsingRoom(6);
										
										// �̿� ���� ���͵��
										System.out.println("# 1�ν� : " + usingRoom1);
										System.out.println("# 4�ν� : " + usingRoom4);
										System.out.println("# 6�ν� : " + usingRoom6);
										
										break;
										
									// �߸��� ����
									default:
										System.out.println("[ �߸� �Է��ϼ̽��ϴ� ]");
									}
									
									break;
									
								// 5��(Check Income) ���� - ���� Ȯ��
								case 5:
									System.out.println("1. Daily Income\n2. Month Income\n3. Total Income");
									input = scan.nextInt();
									
									int month, date;
									
									switch (input) {
									// 1��(Daily Income) ���� - �Ϻ� ����
									case 1:
										System.out.print("Month : ");
										month = scan.nextInt();
										System.out.print("Date : ");
										date = scan.nextInt();
										
										int dailyIncome = studycafe.getDailyIncome(month, date);
										System.out.println("[ " + month + "�� " + date + "�� ���� : " + dailyIncome + "�� ]");
										
										break;
										
									// 2��(Month Income) ���� - ���� ����
									case 2:
										System.out.print("Month : ");
										month = scan.nextInt();
										
										int monthIncome = studycafe.getMonthIncome(month);
										System.out.println("[ " + month + "�� ���� : " + monthIncome + "�� ]");
										
										break;
										
									// 3��(Total Income) ���� - �� ����
									case 3:
										int totalIncome = studycafe.getTotalIncome();
										System.out.println("[ �� ���� : " + totalIncome + "�� ]");
										
										break;
										
									// �߸��� ����
									default:
										System.out.println("[ �߸� �Է��ϼ̽��ϴ� ]");
									}
									
									break;
									
								// 6��(Return To Main) ���� - �������� ���ư���
								case 6:
									manager = false;
									success = false;
									break;
									
								// 7��(Exit) ���� - ���α׷� ����
								case 7:
									System.out.println("\n�̿��� �ּż� �����մϴ� :)");
									key = false;
									manager = false;
									success = false;
									break;
									
								// �߸��� �޴� ����
								default:
									System.out.println("[ �߸� �Է��ϼ̽��ϴ� ]");
								}
							}
							
							// �߸��� �޴� ����
							catch (java.util.InputMismatchException e) {
								System.out.println("[ �߸� �Է��ϼ̽��ϴ� ]");
								scan.next();
							}
						}
					}
					
					// ��й�ȣ ����ġ
					else {
						System.out.println("[ ��й�ȣ�� Ʋ�Ƚ��ϴ� ]");
						manager = false;
					}
				}
				catch (java.util.InputMismatchException e) {
					System.out.println("[ �߸� �Է��ϼ̽��ϴ� ]");
					scan.next();
					manager = false;
				}
			}
		}
		
		scan.close();
		
		// RoomData ����
		try {
			ObjectOutputStream out = null;
			studycafe.writeStudycafeData(out);
		}
		catch (IOException ioe) {
			System.out.println("\n���Ϸ� ����� �� �����ϴ�.");
		}
	}
}