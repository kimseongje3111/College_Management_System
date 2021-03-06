package Manage;

import java.util.List;
import java.util.Scanner;

import Database.DAOUser;
import Database.User;

public class ManageRegister {
	String userId;
	String pwd;
	String name;
	String birth;
	String addr;
	String phoneNum;
	Scanner scan = new Scanner(System.in);
	DAOUser daou = DAOUser.sharedInstance();
	User user = new User();
	
	public void run() {
		boolean run = true;
		while(run) {
			System.out.println();System.out.println();
			System.out.println("실행할 업무를 선택하세요.");
			int chooseWork = this.inputInt("1.학적 등록  2.학적 기본  3.학적 수정  4.학적 삭제  7.종료 ");

			switch (chooseWork) {
			case 1: // 학적 등록
				System.out.println("학적을 등록합니다.");
				this.enrollStudent();
				break;

			case 2: // 학적 기본
				System.out.println("학적을 조회합니다.");
				this.inquiryStudent();
				break;
				
			case 3: // 학적 변동
				System.out.println("학적을 변동합니다.");
				this.modifyStudent();
				break;
				
			case 4: // 학적 삭제
				System.out.println("학적을 삭제합니다.");
				this.deleteStudent();
				break;
				
			case 7: // 종료
				System.out.println("종료합니다.");
				run = false;
				break;
			default:
			}
		}
	}
	
	private void deleteStudent() {
		List<User> list = daou.getUserList();
		
		for(User u : list) {
			System.out.println(u);
		}
		
		String userId = this.inputString("삭제할 학번 : ");
		
		user.setUserId(userId);
		
		boolean r = daou.deleteUser(user);
		
		if(r)
			System.out.println("학적수정이 완료되었습니다.");
		else
			System.out.println("학적수정이 실패하였습니다.");
	}
	
	private void modifyStudent() {
		List<User> list = daou.getUserList();
		
		for(User u : list) {
			System.out.println(u);
		}
		
		String userId = this.inputString("변동할 학번 : ");
		String pwd = this.inputString("비밀번호 : ");
		String name = this.inputString("성명 (ex. 이성규) : ");
		String birth = this.inputString("생년월일 (ex. 19940216) : ");
		String addr = this.inputString("주소 : ");
		String phoneNum = this.inputString("전화번호 : ");
		
		user.setUserId(userId);
		user.setPwd(pwd);
		user.setName(name);
		user.setBirth(birth);
		user.setAddr(addr);
		user.setPhoneNum(phoneNum);
		
		boolean r = daou.modifyUser(user);
		
		if(r)
			System.out.println("학적수정이 완료되었습니다.");
		else
			System.out.println("학적수정이 실패하였습니다.");
	}
	
	private void inquiryStudent() {
		List<User> list = daou.getUserList();
		
		for(User u : list) {
			System.out.println(u);
		}
		String userId = this.inputString("조회할 학번 : ");
		user.setUserId(userId);
		list = daou.inquiryStudent(userId);
		for(User u : list) {
			System.out.println(u);
		}
	}
	
	private void enrollStudent() {
		String userId = this.inputString("아이디 : ");
		String pwd = this.inputString("비밀번호 : ");
		String name = this.inputString("성명 (ex. 이성규) : ");
		String birth = this.inputString("생년월일 (ex. 19940216) : ");
		String addr = this.inputString("주소 : ");
		String phoneNum = this.inputString("전화번호 : ");
		// 추가~
		user.setUserId(userId);
		user.setPwd(pwd);
		user.setName(name);
		user.setBirth(birth);
		user.setAddr(addr);
		user.setPhoneNum(phoneNum);
		
		boolean r = daou.InsertUser(user);
		
		if(r)
			System.out.println("학적등록이 완료되었습니다.");
		else
			System.out.println("학적등록이 실패하였습니다.");

		// 저장된 거 확인 겸 select 해봄
		List<User> list = daou.getUserList();
		
		for(User u : list) {
			System.out.println(u);
		}
	}

	private int inputInt(String string) {
		System.out.print(string);
		return Integer.parseInt(scan.nextLine());
	}

	private String inputString(String string) {
		System.out.print(string);
		return scan.nextLine();
	}
}
