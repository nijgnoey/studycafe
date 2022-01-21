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
			System.out.println("[ 파일 없음 ]\n");
		}
		catch (EOFException eofe) {
			System.out.println("[ 파일 끝... ]\n");
		}
		catch (IOException ioe) {
			System.out.println("[ 파일을 읽을 수 없음 ]\n");
		}
		catch (ClassNotFoundException cnfe) {
			System.out.println("[ 해당 클래스가 존재하지 않음 ]\n");
		}
		catch (Exception e) {
			System.out.print("[ Error ]\n");
		}
		
		boolean key = true;
		boolean user = false;
		boolean manager = false;
		int mode; // 접근 모드를 선택하기 위한 변수
		int menu; // 메뉴를 선택하기 위한 변수
		
		System.out.println("JAVA StudyCafe 입니다 :)");
		
		while (key) {
			try {
				System.out.println("\n******* MODE *******");
				System.out.println("1. User\n2. Manager\n3. Exit");
				System.out.println("********************");
				
				mode = scan.nextInt();
				
				switch (mode) {
				// 1번(User) 선택
				case 1:
					user = true;
					break;
				// 2번(Manager) 선택
				case 2:
					manager = true;
					break;
				// 3번(Exit) 선택
				case 3:
					System.out.println("\n이용해 주셔서 감사합니다 :)");
					key = false;
					break;
				default:
					System.out.println("[ 잘못 입력하셨습니다 ]");
				}
			}
			// 잘못된 모드 선택
			catch (java.util.InputMismatchException e) {
				System.out.println("[ 잘못 입력하셨습니다 ]");
				scan.next();
			}
			
			// user mode
			while (user) {
				try {
					System.out.println("\n******* MENU *******");
					System.out.println("1. Check In\n2. Check Out\n3. Search Empty Room\n4. Return To Main\n5. Exit");
					System.out.println("********************");
					
					menu = scan.nextInt();
					
					String roomNum; // 스터디룸 번호(Room Number) 입력 변수
					String phoneNum;
					int year, month, date, amPm, hour, min;
					
					switch (menu) {
					// 1번(Check In) 선택 - 입실
					case 1:
						System.out.print("\nRoom Number : ");
						scan.nextLine();
						roomNum = scan.nextLine();
						System.out.print("Name : ");
						String name = scan.nextLine();
						System.out.print("Phone Number : ");
						phoneNum = scan.nextLine();
						
						// Check In 실행
						try {
							studycafe.checkIn(roomNum, name, phoneNum);
							
							GregorianCalendar calendar = new GregorianCalendar();
							
							year = calendar.get(Calendar.YEAR);
							month = calendar.get(Calendar.MONTH) + 1;
							date = calendar.get(Calendar.DATE);
							amPm = calendar.get(Calendar.AM_PM);
							hour = calendar.get(Calendar.HOUR);
							min = calendar.get(Calendar.MINUTE);
							String sAmPm = amPm == Calendar.AM ? "오전" : "오후";
							
							System.out.println("[ 입실 처리가 완료되었습니다 ]");
							System.out.println("# " + roomNum + "번 스터디룸");
							System.out.println("# 입실 시간 : " + year + "년 " + month + "월 " + date + "일 " + sAmPm + " " + hour + "시 " + min + "분");
						}
						// Check In 실패
						catch (Exception e) {
							System.out.println("[ 입실이 불가능합니다 ]");
						}
						
						break;
						
					// 2번(Check Out) 선택 - 퇴실
					case 2:
						System.out.print("\nRoom Number : ");
						scan.nextLine();
						roomNum = scan.nextLine();
						System.out.print("Phone Number : ");
						phoneNum = scan.nextLine();
						
						// Check Out 실행
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
							String sAmPm = amPm == Calendar.AM ? "오전" : "오후";
							
							System.out.println("[ 퇴실 처리가 완료되었습니다 ]");
							System.out.println("# " + roomNum + "번 스터디룸");
							System.out.println("# 퇴실 시간 : " + year + "년 " + month + "월 " + date + "일 " + sAmPm + " " + hour + "시 " + min + "분");
							System.out.println("# 지불 금액 : " + payment + "원");
						}
						// Check Out 실패
						catch (Exception e) {
							System.out.println("[ 퇴실이 불가능합니다 ]");
						}
						
						break;
						
					// 3번(Search Empty Room) 선택 - 빈 스터디룸 찾기
					case 3:
						System.out.println("[ 이용 가능한 스터디룸 ]");
						
						ArrayList<String> emptyRoom1 = studycafe.searchEmptyRoom(1);
						ArrayList<String> emptyRoom4 = studycafe.searchEmptyRoom(4);
						ArrayList<String> emptyRoom6 = studycafe.searchEmptyRoom(6);
						
						// 비어 있는 스터디룸
						System.out.println("# 1인실 : " + emptyRoom1);
						System.out.println("# 4인실 : " + emptyRoom4);
						System.out.println("# 6인실 : " + emptyRoom6);
						
						break;
						
					// 4번(Return To Main) 선택 - 메인으로 돌아가기
					case 4:
						user = false;
						break;
						
					// 5번(Exit) 선택 - 프로그램 종료
					case 5:
						System.out.println("\n이용해 주셔서 감사합니다 :)");
						key = false;
						user = false;
						break;
						
					// 잘못된 메뉴 선택
					default:
						System.out.println("[ 잘못 입력하셨습니다 ]");
					}
				}
				
				// 잘못된 메뉴 선택
				catch (java.util.InputMismatchException e) {
					System.out.println("[ 잘못 입력하셨습니다 ]");
					scan.next();
				}
			}
			
			// manager mode
			while (manager) {
				try {
					int password; // 비밀번호 입력 변수
					
					System.out.print("password : ");
					password = scan.nextInt();
					
					// 비밀번호 일치
					if (studycafe.getPassword() == password) {
						boolean success = true;
						
						while (success) {
							try {
								System.out.println("\n******* MENU *******");
								System.out.println("1. Create Room\n2. Delete Room\n3. Update Room\n4. Search Room\n5. Check Income\n6. Return To Main\n7. Exit");
								System.out.println("********************");
								
								menu = scan.nextInt();
								
								int roomSize; // 스터디룸 크기(roomSize) 입력 변수
								String roomNum; // 스터디룸 번호(roomNum) 입력 변수
								int input;
								
								switch (menu) {
								// 1번(Create Room) 선택 - 스터디룸 생성
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
										// 1인실 스터디룸 생성
										case 1:
											if (!studycafe.searchRoom(roomNum)) {
												studycafe.addRoom(roomSize, roomNum, price);
												System.out.println("[ " + roomNum + "번 스터디룸이 생성되었습니다 ]");
											}
											else {
												System.out.println("[ 동일한 번호의 스터디룸이 있습니다 ]");
											}
											
											break;
											
										// 4인실 스터디룸 생성
										case 4:
											if (!studycafe.searchRoom(roomNum)) {
												studycafe.addRoom(roomSize, roomNum, price);
												System.out.println("[ " + roomNum + "번 스터디룸이 생성되었습니다 ]");
											}
											else {
												System.out.println("[ 동일한 번호의 스터디룸이 있습니다 ]");
											}
											
											break;
											
										// 6인실 스터디룸 생성
										case 6:
											if (!studycafe.searchRoom(roomNum)) {
												studycafe.addRoom(roomSize, roomNum, price);
												System.out.println("[ " + roomNum + "번 스터디룸이 생성되었습니다 ]");
											}
											else {
												System.out.println("[ 동일한 번호의 스터디룸이 있습니다 ]");
											}
											
											break;
											
										// 잘못된 입력
										default:
											System.out.println("[ 스터디룸은 1인실, 4인실, 6인실만 생성할 수 있습니다 ]");
										}
									}
									// 생성 가능한 범위를 초과하였을 경우
									catch (java.lang.ArrayIndexOutOfBoundsException e) {
										System.out.println("[ 더 이상 스터디룸을 생성할 수 없습니다 ]");
									}
									
									break;
									
								// 2번(Delete Room) 선택 - 스터디룸 삭제
								case 2:
									System.out.print("Room Number : ");
									scan.nextLine();
									roomNum = scan.nextLine();
									
									try {
										studycafe.deleteRoom(roomNum);
										System.out.println("[ " + roomNum + "번 스터디룸이 삭제되었습니다 ]");
									}
									catch (Exception e) {
										System.out.println("[ 스터디룸을 삭제할 수 없습니다 ]");
									}
									
									break;
								
								// 3번(Update Room) 선택 - 스터디룸 수정
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
										System.out.println("[ 스터디룸 정보가 수정되었습니다 ]");
									}
									catch (Exception e) {
										System.out.println("[ 스터디룸 정보를 수정할 수 없습니다 ]");
									}
									
									break;
									
								// 4번(Search Room) 선택 - 스터디룸 찾기
								case 4:
									System.out.println("1. Empty Room\n2. Using Room");
									input = scan.nextInt();
									
									switch (input) {
									// 1번(Empty Room) 선택 - 비어 있는 스터디룸 찾기
									case 1:
										System.out.println("[ 이용 가능한 스터디룸 ]");
										
										ArrayList<String> emptyRoom1 = studycafe.searchEmptyRoom(1);
										ArrayList<String> emptyRoom4 = studycafe.searchEmptyRoom(4);
										ArrayList<String> emptyRoom6 = studycafe.searchEmptyRoom(6);
										
										// 비어 있는 스터디룸
										System.out.println("# 1인실 : " + emptyRoom1);
										System.out.println("# 4인실 : " + emptyRoom4);
										System.out.println("# 6인실 : " + emptyRoom6);
										
										break;
									
									// 2번(Using Room) 선택 - 이용 중인 스터디룸 찾기
									case 2:
										System.out.println("[ 이용 중인 스터디룸 ]");
										
										ArrayList<String> usingRoom1 = studycafe.searchUsingRoom(1);
										ArrayList<String> usingRoom4 = studycafe.searchUsingRoom(4);
										ArrayList<String> usingRoom6 = studycafe.searchUsingRoom(6);
										
										// 이용 중인 스터디룸
										System.out.println("# 1인실 : " + usingRoom1);
										System.out.println("# 4인실 : " + usingRoom4);
										System.out.println("# 6인실 : " + usingRoom6);
										
										break;
										
									// 잘못된 선택
									default:
										System.out.println("[ 잘못 입력하셨습니다 ]");
									}
									
									break;
									
								// 5번(Check Income) 선택 - 매출 확인
								case 5:
									System.out.println("1. Daily Income\n2. Month Income\n3. Total Income");
									input = scan.nextInt();
									
									int month, date;
									
									switch (input) {
									// 1번(Daily Income) 선택 - 일별 매출
									case 1:
										System.out.print("Month : ");
										month = scan.nextInt();
										System.out.print("Date : ");
										date = scan.nextInt();
										
										int dailyIncome = studycafe.getDailyIncome(month, date);
										System.out.println("[ " + month + "월 " + date + "일 매출 : " + dailyIncome + "원 ]");
										
										break;
										
									// 2번(Month Income) 선택 - 월별 매출
									case 2:
										System.out.print("Month : ");
										month = scan.nextInt();
										
										int monthIncome = studycafe.getMonthIncome(month);
										System.out.println("[ " + month + "월 매출 : " + monthIncome + "원 ]");
										
										break;
										
									// 3번(Total Income) 선택 - 총 매출
									case 3:
										int totalIncome = studycafe.getTotalIncome();
										System.out.println("[ 총 매출 : " + totalIncome + "원 ]");
										
										break;
										
									// 잘못된 선택
									default:
										System.out.println("[ 잘못 입력하셨습니다 ]");
									}
									
									break;
									
								// 6번(Return To Main) 선택 - 메인으로 돌아가기
								case 6:
									manager = false;
									success = false;
									break;
									
								// 7번(Exit) 선택 - 프로그램 종료
								case 7:
									System.out.println("\n이용해 주셔서 감사합니다 :)");
									key = false;
									manager = false;
									success = false;
									break;
									
								// 잘못된 메뉴 선택
								default:
									System.out.println("[ 잘못 입력하셨습니다 ]");
								}
							}
							
							// 잘못된 메뉴 선택
							catch (java.util.InputMismatchException e) {
								System.out.println("[ 잘못 입력하셨습니다 ]");
								scan.next();
							}
						}
					}
					
					// 비밀번호 불일치
					else {
						System.out.println("[ 비밀번호가 틀렸습니다 ]");
						manager = false;
					}
				}
				catch (java.util.InputMismatchException e) {
					System.out.println("[ 잘못 입력하셨습니다 ]");
					scan.next();
					manager = false;
				}
			}
		}
		
		scan.close();
		
		// RoomData 저장
		try {
			ObjectOutputStream out = null;
			studycafe.writeStudycafeData(out);
		}
		catch (IOException ioe) {
			System.out.println("\n파일로 출력할 수 없습니다.");
		}
	}
}