import java.io.Serializable;
import java.util.Calendar;

public class Room implements Serializable {
	private int roomSize; // ���͵�� ũ�� (���� �ο�)
	private String roomNum; // ���͵�� ��ȣ
	private int price; // ���� (�� ����)
	private boolean isEmpty = true; // ���͵�� ���� (����� �� true)
	private long checkInTime; // üũ�� �ð�
	private long checkOutTime; // üũ�ƿ� �ð�
	private int payment; // ���� �ݾ�
	
	User user = new User();
	
	// ������ �޼ҵ�
	Room() {
		
	}
	
	// ���͵�� ũ��(roomSize)�� ���͵�� ��ȣ(roomNum)�� �޴� ������ �޼ҵ�
	Room(int roomSize, String roomNum, int price) {
		this.roomSize = roomSize;
		this.roomNum = roomNum;
		this.price = price;
	}
	
	// ���� �����͸� �����ϴ� �޼ҵ�
	void setRoomData(boolean isEmpty, long checkInTime, String name, String phoneNum) {
		setIsEmpty(isEmpty);
		this.checkInTime = checkInTime;
		user.setName(name);
		user.setPhoneNum(phoneNum);
	}
	
	// ���͵�� ũ��(roomSize)�� �����ϴ� �޼ҵ�
	void setRoomSize(int roomSize) {
		this.roomSize = roomSize;
	}
	
	// ���͵�� ũ��(roomSize)�� ��ȯ�ϴ� �޼ҵ�
	int getRoomSize() {
		return roomSize;
	}
	
	// ���͵�� ��ȣ(roomNum)�� �����ϴ� �޼ҵ�
	void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	
	// ���͵�� ��ȣ(roomNum)�� ��ȯ�ϴ� �޼ҵ�
	String getRoomNum() {
		return roomNum;
	}
	
	// ���͵�� ����(price)�� �����ϴ� �޼ҵ�
	void setPrice(int price) {
		this.price = price;
	}
	
	// ���͵�� ����(price)�� ��ȯ�ϴ� �޼ҵ�
	int getPrice() {
		return price;
	}
	
	// ���͵�� ����(isEmpty)�� �����ϴ� �޼ҵ�
	void setIsEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}
	
	// ���͵�� ����(isEmpty)�� ��ȯ�ϴ� �޼ҵ�
	boolean isEmpty() {
		return isEmpty;
	}
	
	// üũ�� �ð�(checkInTime)�� �����ϴ� �޼ҵ�
	void setCheckInTime() {
		Calendar date = Calendar.getInstance();
		date.setTimeInMillis(System.currentTimeMillis());
		
		checkInTime = date.getTimeInMillis();
	}
	
	// üũ�� �ð�(checkInTime)�� ��ȯ�ϴ� �޼ҵ�
	long getCheckInTime() {
		return checkInTime;
	}
	
	// üũ�ƿ� �ð�(checkOutTime)�� �����ϴ� �޼ҵ�
	void setCheckOutTime() {
		Calendar date = Calendar.getInstance();
		date.setTimeInMillis(System.currentTimeMillis());
		
		checkOutTime = date.getTimeInMillis();
	}
	
	// üũ�ƿ� �ð�(checkOutTime)�� ��ȯ�ϴ� �޼ҵ�
	long getCheckOutTime() {
		return checkOutTime;
	}
	
	// ���� �ݾ�(payment)�� ����ϴ� �޼ҵ�
	// ���� ���� �Ѿ�� üũ�ƿ��� �� ��쿡�� �ð� �� ���� �ݾ� ��꿡 ���� ���� Ȯ��
	int payment() {
		long differ = (checkOutTime - checkInTime) / 1000 / 60;
		payment = (int) differ * price;
		
		return payment;
	}
	
	// ���� �ݾ�(payment)�� ��ȯ�ϴ� �޼ҵ�
	int getPayment() {
		return payment;
	}
	
	// üũ�� �޼ҵ� (User Ŭ���� ��ü ����)
	void checkIn(String name, String phoneNum) {
		user.setName(name);
		user.setPhoneNum(phoneNum);
		
		isEmpty = false;
		setCheckInTime();
	}
	
	// üũ�ƿ� �޼ҵ�
	void checkOut() {
		isEmpty = true;
		user.setName(" ");
		user.setPhoneNum(" ");
		this.checkInTime = 0;
		this.checkOutTime = 0;
	}
	
	// User Ŭ���� ��ü�� ��ȯ�ϴ� �޼ҵ�
	User getUser() {
		return user;
	}
	
	// equals �Լ� �������̵�
	public boolean equals(Object obj) {
		Room room = (Room) obj;
		
		if (roomNum.equals(room.getRoomNum()))
			return true;
		else
			return false;
	}
}