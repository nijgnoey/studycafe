import java.util.*;
import java.io.*;

public class Management implements Serializable {
	private ArrayList<Room> roomArray = new ArrayList<Room>(); // ���͵�� ArrayList
	private Room room = new Room();
	private int[][] income = new int[12][31]; // �� �� ���� �迭
	private Integer password = 1234; // ������ ��� ���� ��й�ȣ
	
	// StudyCafeData�� �ҷ����� �޼ҵ�
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
	
	// StudyCafeData�� �����ϴ� �޼ҵ�
	void writeStudycafeData(ObjectOutputStream out) throws IOException {
		int roomCount = roomArray.size();
		
		out = new ObjectOutputStream(new FileOutputStream("StudyCafeData.dat"));
		// roomCount(���͵�� ����) ����
		out.writeObject(roomCount);
		// roomArray(���͵��) ����
		for (int i = 0; i < roomCount; i++) {
			out.writeObject(roomArray.get(i));
		}
		// income(����) ����
		out.writeObject(income);
		// password(��й�ȣ) ����
		out.writeObject(password);
		
		out.close();
	}
	
	// Room Ŭ���� ��ü�� ��ȯ�ϴ� �޼ҵ�
	ArrayList<Room> getRoom() {
		return roomArray;
	}
	
	// roomCount(���͵�� ����)�� ��ȯ�ϴ� �޼ҵ�
	int getRoomCount() {
		int roomCount = roomArray.size();
		return roomCount;
	}
	
	// ������ ��� ���� ��й�ȣ(password)�� ��ȯ�ϴ� �޼ҵ�
	int getPassword() {
		return password;
	}
	
	// �ش� �� ��ȣ�� ���� ���͵���� �����ϴ��� �˻��ϴ� �޼ҵ�
	public boolean searchRoom (String roomNum) {
		room.setRoomNum(roomNum);
		return roomArray.contains(room);
	}
	
	// �ش� �� ��ȣ�� ���� ���͵���� index�� ã�� �޼ҵ�
	int searchRoomIndex(String roomNum) {
		room.setRoomNum(roomNum);
		return roomArray.indexOf(room);
	}
	
	// n�ν� ���͵���� �߰��ϴ� �޼ҵ�
	void addRoom(int roomSize, String roomNum, int price) {		
		roomArray.add(new Room(roomSize, roomNum, price));
	}
	
	// n�ν� ���͵���� �����ϴ� �޼ҵ�
	void deleteRoom(String roomNum) throws Exception {
		// �ش� ��ȣ�� ���͵�� �˻�
		int roomIndex = searchRoomIndex(roomNum);
		roomArray.remove(roomIndex);
	}
	
	// ���͵�� ������ �����ϴ� �޼ҵ�
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
	
	// ���͵�� ���� �ο� ������ �����ϴ� �޼ҵ�
	void updateRoomSize(String roomNum, int newRoomSize) throws Exception {
		int roomIndex = searchRoomIndex(roomNum);
		
		if (roomArray.get(roomIndex).isEmpty() && (newRoomSize == 1 || newRoomSize == 4 || newRoomSize == 6))
			roomArray.get(roomIndex).setRoomSize(newRoomSize);
		else {
			Exception e = new Exception();
			throw e;
		}
	}
	
	// ���͵�� ��ȣ ������ �����ϴ� �޼ҵ�
	void updateRoomNum(String roomNum, String newRoomNum) throws Exception {
		int roomIndex = searchRoomIndex(roomNum);
		
		if (roomArray.get(roomIndex).isEmpty() && !searchRoom(newRoomNum))
			roomArray.get(roomIndex).setRoomNum(newRoomNum);
		else {
			Exception e = new Exception();
			throw e;
		}
	}
	
	// ���͵�� ���� ������ �����ϴ� �޼ҵ�
	void updateRoomPrice(String roomNum, int newRoomPrice) throws Exception {
		int roomIndex = searchRoomIndex(roomNum);
		
		if (roomArray.get(roomIndex).isEmpty())
			roomArray.get(roomIndex).setPrice(newRoomPrice);
		else {
			Exception e = new Exception();
			throw e;
		}
	}
	
	// �� ���͵���� ã�� ��ȯ�ϴ� �޼ҵ�
	ArrayList<String> searchEmptyRoom(int roomSize) {
		ArrayList<String> emptyRoom = new ArrayList<String>(); // �� ���͵�� ArrayList
		int roomCount = roomArray.size();
		
		// �� ���͵���� ã�� emptyRoom�� �߰��ϱ�
		for (int i = 0; i < roomCount; i++)
			if (roomArray.get(i).getRoomSize() == roomSize && roomArray.get(i).isEmpty() == true)
				emptyRoom.add(roomArray.get(i).getRoomNum());
		
		return emptyRoom;
	}
	
	// �̿� ���� ���͵���� ã�� ��ȯ�ϴ� �޼ҵ�
	ArrayList<String> searchUsingRoom(int roomSize) {
		ArrayList<String> usingRoom = new ArrayList<String>(); // �̿� ���� ���͵�� ArrayList
		int roomCount = roomArray.size();
		
		// �̿� ���� ���͵���� ã�� usingRoom�� �߰��ϱ�
		for (int i = 0; i < roomCount; i++)
			if (roomArray.get(i).getRoomSize() == roomSize && roomArray.get(i).isEmpty() == false)
				usingRoom.add(roomArray.get(i).getRoomNum());
		
		return usingRoom;
	}
	
	// (GUI��) ���͵���� ������ ��� ��ȯ�ϴ� �޼ҵ�
	Object[] searchRoom(int i) {
		Object room[] = new Object[4];
		
		room[0] = roomArray.get(i).getRoomSize();
		room[1] = roomArray.get(i).getRoomNum();
		room[2] = roomArray.get(i).getPrice();
		if (roomArray.get(i).isEmpty())
			room[3] = "�̿� ����";
		else
			room[3] = "�̿� ��";
		
		return room;
	}
	
	// (GUI��) �� ���͵���� ������ ��� ��ȯ�ϴ� �޼ҵ�
	Object[] searchEmptyRoom_2 (int i) {
		Object room[] = new Object[4];
		
		if (roomArray.get(i).isEmpty()) {
			room[0] = roomArray.get(i).getRoomSize();
			room[1] = roomArray.get(i).getRoomNum();
			room[2] = roomArray.get(i).getPrice();
			room[3] = "�̿� ����";
		}
		
		return room;
	}
	
	// (GUI��) �̿� ���� ���͵���� ������ ��� ��ȯ�ϴ� �޼ҵ�
	Object[] searchUsingRoom_2 (int i) {
		Object room[] = new Object[4];
		
		if (!roomArray.get(i).isEmpty()) {
			room[0] = roomArray.get(i).getRoomSize();
			room[1] = roomArray.get(i).getRoomNum();
			room[2] = roomArray.get(i).getPrice();
			room[3] = "�̿� ��";
		}
		
		return room;
	}
	
	// ����(income)�� �����ϴ� �޼ҵ�, ���� ������Ʈ
	void setIncome(int payment) {
		// ��¥ Ȯ��
		GregorianCalendar calendar = new GregorianCalendar();
		int month = calendar.get(Calendar.MONTH);
		int date = calendar.get(Calendar.DATE);
		
		// ��¥�� �´� �迭�� ���� ������Ʈ
		income[month][date - 1] += payment;
	}
	
	// �Ϸ� ������ ��ȯ�ϴ� �޼ҵ�
	int getDailyIncome(int month, int date) {
		return income[month - 1][date - 1];
	}
	
	// �� �� ������ ��ȯ�ϴ� �޼ҵ�
	int getMonthIncome(int month) {
		int monthIncome = 0;
		
		for (int i = 0; i < 31; i++)
			monthIncome += income[month - 1][i];
		
		return monthIncome;
	}
	
	// �� ������ ��ȯ�ϴ� �޼ҵ�
	int getTotalIncome() {
		int totalIncome = 0;
		
		for (int i = 0; i < 12; i++)
			for (int j = 0; j < 31; j++)
				totalIncome += income[i][j];
		
		return totalIncome;
	}
	
	// üũ�� �޼ҵ�
	void checkIn(String roomNum, String name, String phoneNum) throws Exception {
		// �ش� ��ȣ�� ���͵�� �˻�
		int roomIndex = searchRoomIndex(roomNum);
		
		// �ش� ��ȣ�� ���͵���� ��� ���� ��� üũ��
		if (roomArray.get(roomIndex).isEmpty() == true) {
			roomArray.get(roomIndex).checkIn(name, phoneNum);
		}
		else {
			Exception e = new Exception();
			throw e;
		}
	}
	
	// üũ�ƿ� �޼ҵ�
	void checkOut(String roomNum, String phoneNum) throws Exception {
		int payment;
		
		// �ش� ��ȣ�� ���͵�� �˻�
		int roomIndex = searchRoomIndex(roomNum);
		
		// �ش� ��ȣ�� ���͵���� ��� ���� ��� üũ�ƿ�
		if (roomArray.get(roomIndex).isEmpty() == false) {
			if (phoneNum.equals(roomArray.get(roomIndex).getUser().getPhoneNum())) {
				roomArray.get(roomIndex).setCheckOutTime(); // üũ�ƿ� �ð� Ȯ��
				payment = roomArray.get(roomIndex).payment(); // ���� �ݾ� ���
				setIncome(payment); // ���� ������Ʈ
				roomArray.get(roomIndex).checkOut(); // üũ�ƿ�
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
	
	// ���� �ݾ��� ��ȯ�ϴ� �޼ҵ�
	int getPayment(String roomNum) {
		int payment = 0;
		
		// �ش� ��ȣ�� ���͵�� �˻�
		int roomIndex = searchRoomIndex(roomNum);
		
		// �ش� ��ȣ�� ���͵���� ������ ���
		if (roomIndex != -1)
			payment = roomArray.get(roomIndex).getPayment();
		
		return payment;
	}
}