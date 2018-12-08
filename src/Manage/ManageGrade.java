package Manage;

import java.util.List;
import java.util.Scanner;

import Database.DAOGrade;
import Database.Grade;

public class ManageGrade {
	public static boolean endGrading = false;
	String classIdNum;
	String userId;
	Float grade;
	Scanner scan = new Scanner(System.in);
	DAOGrade daog = DAOGrade.sharedInstance();

	public void run() {
		boolean run = true;
		while(run) {
			System.out.println();System.out.println();
			System.out.println("실행할 업무를 선택하세요.");
			int chooseWork = this.inputInt("1.성적 등록  2.성적 수정  3.학생별 성적표 조회  7.종료 ");

			switch (chooseWork) {
			case 1: // 성적 등록
				if(endGrading)
					System.out.println("성적처리가 이미 끝났습니다.");
				else {
					System.out.println("성적 등록을 시작합니다.");
					this.giveGrade();
				}
				break;

			case 2: // 성적 수정
				if(endGrading)
					System.out.println("성적처리가 이미 끝났습니다.");
				else {
					System.out.println("성적 수정을 시작합니다.");
					this.fixGrade();
				}
				break;
				
			case 3: // 학생별 성적표 조회
				System.out.println("학생별 성적표 조회를 시작합니다.");
				this.checkReport();
				break;
				
			case 7: // 종료
				System.out.println("종료합니다.");
				run = false;
				break;
			default:
			}
		}
	}

	private void checkReport() {
	}

	private void fixGrade() {
	}

	private void giveGrade() {
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