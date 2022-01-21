import java.util.*;
import java.io.*;

public class Management implements Serializable {
	private ArrayList<Room> roomArray = new ArrayList<Room>(); // 스터디룸 ArrayList
	private Room room = new Room();
	private int[][] income = new int[12][31]; // 한 달 수입 배열
	private Integer password = 1234; // 관리자 모드 접속 비밀번호
	
	// StudyCafeData를 불러오는 메소드
	void readStudycafeData(ObjectInputStream in)throws Exception {
		in = new ObjectInputStream (new FileInputStream("StudyCafeData.dat"));
		int roomCount = (int) in.readObject();
		
		for (int i = 0; i < roomCount; i++) {
			roomArray.add((Room) in.readObject());
		}
		income = (int[][]) in.readObject();
		password = (int) in.readObject();
		
		in.close();
	}
	
	// StudyCafeData를 저장하는 메소드
	void writeStudycafeData(ObjectOutputStream out) throws IOException {
		int roomCount = roomArray.size();
		
		out = new ObjectOutputStream(new FileOutputStream("StudyCafeData.dat"));
		// roomCount(스터디룸 개수) 저장
		out.writeObject(roomCount);
		// roomArray(스터디룸) 저장
		for (int i = 0; i < roomCount; i++) {
			out.writeObject(roomArray.get(i));
		}
		// income(수입) 저장
		out.writeObject(income);
		// password(비밀번호) 저장
		out.writeObject(password);
		
		out.close();
	}
	
	// Room 클래스 객체를 반환하는 메소드
	ArrayList<Room> getRoom() {
		return roomArray;
	}
	
	// roomCount(스터디룸 개수)를 반환하는 메소드
	int getRoomCount() {
		int roomCount = roomArray.size();
		return roomCount;
	}
	
	// 관리자 모드 접속 비밀번호(password)를 반환하는 메소드
	int getPassword() {
		return password;
	}
	
	// 해당 방 번호를 가진 스터디룸이 존재하는지 검사하는 메소드
	public boolean searchRoom (String roomNum) {
		room.setRoomNum(roomNum);
		return roomArray.contains(room);
	}
	
	// 해당 방 번호를 가진 스터디룸의 index를 찾는 메소드
	int searchRoomIndex(String roomNum) {
		room.setRoomNum(roomNum);
		return roomArray.indexOf(room);
	}
	
	// n인실 스터디룸을 추가하는 메소드
	void addRoom(int roomSize, String roomNum, int price) {		
		roomArray.add(new Room(roomSize, roomNum, price));
	}
	
	// n인실 스터디룸을 삭제하는 메소드
	void deleteRoom(String roomNum) throws Exception {
		// 해당 번호의 스터디룸 검색
		int roomIndex = searchRoomIndex(roomNum);
		roomArray.remove(roomIndex);
	}
	
	// 스터디룸 정보를 수정하는 메소드
	void updateRoom(String roomNum, int newRoomSize, String newRoomNum, int newPrice) throws Exception {
		int roomIndex = searchRoomIndex(roomNum);
		
		if (roomArray.get(roomIndex).isEmpty() && (newRoomSize == 1 || newRoomSize == 4 || newRoomSize == 6)) {
			roomArray.get(roomIndex).setRoomSize(newRoomSize);
			roomArray.get(roomIndex).setRoomNum(newRoomNum);
			roomArray.get(roomIndex).setPrice(newPrice);
		}
		else {
			Exception e = new Exception();
			throw e;
		}
	}
	
	// 스터디룸 수용 인원 정보를 수정하는 메소드
	void updateRoomSize(String roomNum, int newRoomSize) throws Exception {
		int roomIndex = searchRoomIndex(roomNum);
		
		if (roomArray.get(roomIndex).isEmpty() && (newRoomSize == 1 || newRoomSize == 4 || newRoomSize == 6))
			roomArray.get(roomIndex).setRoomSize(newRoomSize);
		else {
			Exception e = new Exception();
			throw e;
		}
	}
	
	// 스터디룸 번호 정보를 수정하는 메소드
	void updateRoomNum(String roomNum, String newRoomNum) throws Exception {
		int roomIndex = searchRoomIndex(roomNum);
		
		if (roomArray.get(roomIndex).isEmpty() && !searchRoom(newRoomNum))
			roomArray.get(roomIndex).setRoomNum(newRoomNum);
		else {
			Exception e = new Exception();
			throw e;
		}
	}
	
	// 스터디룸 가격 정보를 수정하는 메소드
	void updateRoomPrice(String roomNum, int newRoomPrice) throws Exception {
		int roomIndex = searchRoomIndex(roomNum);
		
		if (roomArray.get(roomIndex).isEmpty())
			roomArray.get(roomIndex).setPrice(newRoomPrice);
		else {
			Exception e = new Exception();
			throw e;
		}
	}
	
	// 빈 스터디룸을 찾아 반환하는 메소드
	ArrayList<String> searchEmptyRoom(int roomSize) {
		ArrayList<String> emptyRoom = new ArrayList<String>(); // 빈 스터디룸 ArrayList
		int roomCount = roomArray.size();
		
		// 빈 스터디룸을 찾아 emptyRoom에 추가하기
		for (int i = 0; i < roomCount; i++)
			if (roomArray.get(i).getRoomSize() == roomSize && roomArray.get(i).isEmpty() == true)
				emptyRoom.add(roomArray.get(i).getRoomNum());
		
		return emptyRoom;
	}
	
	// 이용 중인 스터디룸을 찾아 반환하는 메소드
	ArrayList<String> searchUsingRoom(int roomSize) {
		ArrayList<String> usingRoom = new ArrayList<String>(); // 이용 중인 스터디룸 ArrayList
		int roomCount = roomArray.size();
		
		// 이용 중인 스터디룸을 찾아 usingRoom에 추가하기
		for (int i = 0; i < roomCount; i++)
			if (roomArray.get(i).getRoomSize() == roomSize && roomArray.get(i).isEmpty() == false)
				usingRoom.add(roomArray.get(i).getRoomNum());
		
		return usingRoom;
	}
	
	// (GUI용) 스터디룸의 정보를 담아 반환하는 메소드
	Object[] searchRoom(int i) {
		Object room[] = new Object[4];
		
		room[0] = roomArray.get(i).getRoomSize();
		room[1] = roomArray.get(i).getRoomNum();
		room[2] = roomArray.get(i).getPrice();
		if (roomArray.get(i).isEmpty())
			room[3] = "이용 가능";
		else
			room[3] = "이용 중";
		
		return room;
	}
	
	// (GUI용) 빈 스터디룸의 정보를 담아 반환하는 메소드
	Object[] searchEmptyRoom_2 (int i) {
		Object room[] = new Object[4];
		
		if (roomArray.get(i).isEmpty()) {
			room[0] = roomArray.get(i).getRoomSize();
			room[1] = roomArray.get(i).getRoomNum();
			room[2] = roomArray.get(i).getPrice();
			room[3] = "이용 가능";
		}
		
		return room;
	}
	
	// (GUI용) 이용 중인 스터디룸의 정보를 담아 반환하는 메소드
	Object[] searchUsingRoom_2 (int i) {
		Object room[] = new Object[4];
		
		if (!roomArray.get(i).isEmpty()) {
			room[0] = roomArray.get(i).getRoomSize();
			room[1] = roomArray.get(i).getRoomNum();
			room[2] = roomArray.get(i).getPrice();
			room[3] = "이용 중";
		}
		
		return room;
	}
	
	// 수입(income)을 설정하는 메소드, 수입 업데이트
	void setIncome(int payment) {
		// 날짜 확인
		GregorianCalendar calendar = new GregorianCalendar();
		int month = calendar.get(Calendar.MONTH);
		int date = calendar.get(Calendar.DATE);
		
		// 날짜에 맞는 배열에 수입 업데이트
		income[month][date - 1] += payment;
	}
	
	// 하루 수입을 반환하는 메소드
	int getDailyIncome(int month, int date) {
		return income[month - 1][date - 1];
	}
	
	// 한 달 수입을 반환하는 메소드
	int getMonthIncome(int month) {
		int monthIncome = 0;
		
		for (int i = 0; i < 31; i++)
			monthIncome += income[month - 1][i];
		
		return monthIncome;
	}
	
	// 총 수입을 반환하는 메소드
	int getTotalIncome() {
		int totalIncome = 0;
		
		for (int i = 0; i < 12; i++)
			for (int j = 0; j < 31; j++)
				totalIncome += income[i][j];
		
		return totalIncome;
	}
	
	// 체크인 메소드
	void checkIn(String roomNum, String name, String phoneNum) throws Exception {
		// 해당 번호의 스터디룸 검색
		int roomIndex = searchRoomIndex(roomNum);
		
		// 해당 번호의 스터디룸이 비어 있을 경우 체크인
		if (roomArray.get(roomIndex).isEmpty() == true) {
			roomArray.get(roomIndex).checkIn(name, phoneNum);
		}
		else {
			Exception e = new Exception();
			throw e;
		}
	}
	
	// 체크아웃 메소드
	void checkOut(String roomNum, String phoneNum) throws Exception {
		int payment;
		
		// 해당 번호의 스터디룸 검색
		int roomIndex = searchRoomIndex(roomNum);
		
		// 해당 번호의 스터디룸이 사용 중일 경우 체크아웃
		if (roomArray.get(roomIndex).isEmpty() == false) {
			if (phoneNum.equals(roomArray.get(roomIndex).getUser().getPhoneNum())) {
				roomArray.get(roomIndex).setCheckOutTime(); // 체크아웃 시간 확인
				payment = roomArray.get(roomIndex).payment(); // 지불 금액 계산
				setIncome(payment); // 수입 업데이트
				roomArray.get(roomIndex).checkOut(); // 체크아웃
			}
			else {
				Exception e = new Exception();
				throw e;
			}
		}
		else {
			Exception e = new Exception();
			throw e;
		}
	}
	
	// 지불 금액을 반환하는 메소드
	int getPayment(String roomNum) {
		int payment = 0;
		
		// 해당 번호의 스터디룸 검색
		int roomIndex = searchRoomIndex(roomNum);
		
		// 해당 번호의 스터디룸이 존재할 경우
		if (roomIndex != -1)
			payment = roomArray.get(roomIndex).getPayment();
		
		return payment;
	}
}