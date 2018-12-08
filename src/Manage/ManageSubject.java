package Manage;

import java.util.List;
import java.util.Scanner;

import Database.DAOSubject;
import Database.Subject;

public class ManageSubject {
	String subjectName;
	String profName;
	Integer credit;
	Scanner scan = new Scanner(System.in);
	DAOSubject daos = DAOSubject.sharedInstance();
	Subject subject = new Subject();
	
	public void run() {
		boolean run = true;
		while(run) {
			System.out.println();System.out.println();
			System.out.println("실행할 업무를 선택하세요.");
			int chooseWork = this.inputInt("1.개설 과목 등록  2.개설 과목 수정  3.개설 과목 삭제  7.종료 ");

			switch (chooseWork) {
			case 1: // 개설 과목 등록
				System.out.println("개설 과목을 등록합니다.");
				this.enrollSubject();
				break;

			case 2: // 개설 과목 수정
				System.out.println("개설 과목을 수정합니다.");
				this.modifySubject();
				break;
				
			case 3: // 개설 과목 삭제
				System.out.println("개설 과목을 삭제합니다.");
				this.deleteSubject();
				break;
				
			case 7: // 종료
				System.out.println("종료합니다.");
				run = false;
				break;
			default:
			}
		}
	}
	
	private void deleteSubject() {
		List<Subject> list = daos.getSubjectList();
		
		for(Subject u : list) {
			System.out.println(u);
		}
	}
	
	private void modifySubject() {
		List<Subject> list = daos.getSubjectList();
		
		for(Subject u : list) {
			System.out.println(u);
		}
	}

	private void enrollSubject() {
		
		boolean r = daos.InsertSubject(subject);
		
		if(r)
			System.out.println("교과목등록이 완료되었습니다.");
		else
			System.out.println("교과목등록이 실패하였습니다.");

		// 저장된 거 확인 겸 select 해봄
		List<Subject> list = daos.getSubjectList();
		
		for(Subject u : list) {
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
