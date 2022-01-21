import java.io.Serializable;
import java.util.Calendar;

public class Room implements Serializable {
	private int roomSize; // 스터디룸 크기 (수용 인원)
	private String roomNum; // 스터디룸 번호
	private int price; // 가격 (분 단위)
	private boolean isEmpty = true; // 스터디룸 상태 (비었을 때 true)
	private long checkInTime; // 체크인 시간
	private long checkOutTime; // 체크아웃 시간
	private int payment; // 지불 금액
	
	User user = new User();
	
	// 생성자 메소드
	Room() {
		
	}
	
	// 스터디룸 크기(roomSize)와 스터디룸 번호(roomNum)를 받는 생성자 메소드
	Room(int roomSize, String roomNum, int price) {
		this.roomSize = roomSize;
		this.roomNum = roomNum;
		this.price = price;
	}
	
	// 이전 데이터를 복구하는 메소드
	void setRoomData(boolean isEmpty, long checkInTime, String name, String phoneNum) {
		setIsEmpty(isEmpty);
		this.checkInTime = checkInTime;
		user.setName(name);
		user.setPhoneNum(phoneNum);
	}
	
	// 스터디룸 크기(roomSize)를 설정하는 메소드
	void setRoomSize(int roomSize) {
		this.roomSize = roomSize;
	}
	
	// 스터디룸 크기(roomSize)를 반환하는 메소드
	int getRoomSize() {
		return roomSize;
	}
	
	// 스터디룸 번호(roomNum)를 설정하는 메소드
	void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	
	// 스터디룸 번호(roomNum)를 반환하는 메소드
	String getRoomNum() {
		return roomNum;
	}
	
	// 스터디룸 가격(price)를 설정하는 메소드
	void setPrice(int price) {
		this.price = price;
	}
	
	// 스터디룸 가격(price)를 반환하는 메소드
	int getPrice() {
		return price;
	}
	
	// 스터디룸 상태(isEmpty)를 변경하는 메소드
	void setIsEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}
	
	// 스터디룸 상태(isEmpty)를 반환하는 메소드
	boolean isEmpty() {
		return isEmpty;
	}
	
	// 체크인 시간(checkInTime)을 설정하는 메소드
	void setCheckInTime() {
		Calendar date = Calendar.getInstance();
		date.setTimeInMillis(System.currentTimeMillis());
		
		checkInTime = date.getTimeInMillis();
	}
	
	// 체크인 시간(checkInTime)을 반환하는 메소드
	long getCheckInTime() {
		return checkInTime;
	}
	
	// 체크아웃 시간(checkOutTime)을 설정하는 메소드
	void setCheckOutTime() {
		Calendar date = Calendar.getInstance();
		date.setTimeInMillis(System.currentTimeMillis());
		
		checkOutTime = date.getTimeInMillis();
	}
	
	// 체크아웃 시간(checkOutTime)을 반환하는 메소드
	long getCheckOutTime() {
		return checkOutTime;
	}
	
	// 지불 금액(payment)을 계산하는 메소드
	// 다음 날로 넘어가서 체크아웃을 한 경우에도 시간 및 지불 금액 계산에 문제 없음 확인
	int payment() {
		long differ = (checkOutTime - checkInTime) / 1000 / 60;
		payment = (int) differ * price;
		
		return payment;
	}
	
	// 지불 금액(payment)을 반환하는 메소드
	int getPayment() {
		return payment;
	}
	
	// 체크인 메소드 (User 클래스 객체 생성)
	void checkIn(String name, String phoneNum) {
		user.setName(name);
		user.setPhoneNum(phoneNum);
		
		isEmpty = false;
		setCheckInTime();
	}
	
	// 체크아웃 메소드
	void checkOut() {
		isEmpty = true;
		user.setName(" ");
		user.setPhoneNum(" ");
		this.checkInTime = 0;
		this.checkOutTime = 0;
	}
	
	// User 클래스 객체를 반환하는 메소드
	User getUser() {
		return user;
	}
	
	// equals 함수 오버라이딩
	public boolean equals(Object obj) {
		Room room = (Room) obj;
		
		if (roomNum.equals(room.getRoomNum()))
			return true;
		else
			return false;
	}
}