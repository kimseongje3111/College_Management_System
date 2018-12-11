package Manage;

import java.util.List;
import java.util.Scanner;

import Database.DAOGrade;
import Database.DAOSubject;
import Database.Grade;
import Database.Subject;

public class ManageAttending {
	String classIdNum;
	String userId;
	Float grade;
	Scanner scan = new Scanner(System.in);
	DAOSubject daos = DAOSubject.sharedInstance();
	DAOGrade daog = DAOGrade.sharedInstance();
	
	public void run() {
		boolean run = true;
		while(run) {
			System.out.println();System.out.println();
			System.out.println("실행할 업무를 선택하세요.");
			int chooseWork = this.inputInt("1. 개설과목 관리  2.강의시간표 조회  3.강의계획서 조회  4.수강신청  5.수강취소  7.종료 ");

			switch (chooseWork) {
			case 1: // 개설 과목 등록
				System.out.println("개설 과목을 관리합니다.");
				new ManageSubject().run();
				break;

			case 2: // 강의시간표 조회
				System.out.println("강의시간표를 조회합니다.");
				this.inquiryTimetable();
				break;
				
			case 3: // 강의계획서 조회
				System.out.println("강의계획서를 조회합니다.");
				this.inquirySyllabus();
				break;
				
			case 4: // 수강신청
				System.out.println("수강 신청합니다.");
				this.attendClass();
				break;
				
			case 5: // 수강취소
				System.out.println("수강 취소합니다.");
				this.deleteClass();
				break;
				
			case 7: // 종료
				System.out.println("종료합니다.");
				run = false;
				break;
			default:
			}
		}
	}
	
	private void deleteClass() {
	}

	private void attendClass() {
	}

	private void inquirySyllabus() {
		// 학수번호로 조회해야 한다. 왜냐하면 과목명으로는 다른 강의계획서가 존재할 수도 있다.
		
	}

	private void inquiryTimetable() {
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
