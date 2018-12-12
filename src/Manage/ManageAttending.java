package Manage;

import java.util.List;
import java.util.Scanner;

import Database.DAOGrade;
import Database.DAOSubject;
import Database.Grade;
import Database.Subject;
import Database.User;

public class ManageAttending {
	String classIdNum;
	String userId;
	Float grade;
	Scanner scan = new Scanner(System.in);
	DAOSubject daos = DAOSubject.sharedInstance();
	DAOGrade daog = DAOGrade.sharedInstance();
	Subject subject = new Subject();
	User user = new User();
	
	public void run() {
		boolean run = true;
		while(run) {
			System.out.println();System.out.println();
			System.out.println("������ ������ �����ϼ���.");
			int chooseWork = this.inputInt("1. �������� ����  2.���ǽð�ǥ ��ȸ  3.���ǰ�ȹ�� ��ȸ  4.������û  5.�������  7.���� ");

			switch (chooseWork) {
			case 1: // ���� ���� ���
				System.out.println("���� ������ �����մϴ�.");
				new ManageSubject().run();
				break;

			case 2: // ���ǽð�ǥ ��ȸ
				System.out.println("���ǽð�ǥ�� ��ȸ�մϴ�.");
				this.inquiryTimetable();
				break;
				
			case 3: // ���ǰ�ȹ�� ��ȸ
				System.out.println("���ǰ�ȹ���� ��ȸ�մϴ�.");
				this.inquirySyllabus();
				break;
				
			case 4: // ������û
				System.out.println("���� ��û�մϴ�.");
				this.attendClass();
				break;
				
			case 5: // �������
				System.out.println("���� ����մϴ�.");
				this.deleteClass();
				break;
				
			case 7: // ����
				System.out.println("�����մϴ�.");
				run = false;
				break;
			default:
			}
		}
	}
	
	private void deleteClass() { //���� ���
		String deleteClassIdNum = this.inputString("���� ����� �м���ȣ : ");
		String deleteUserId = this.inputString("���� ����� �л��� �й� : ");

		Grade grade = new Grade();
		grade.setClassIdNum(deleteClassIdNum);
		grade.setUserId(deleteUserId);

		boolean r = daog.deleteGrade(grade);
		if (r)
			System.out.println("���� ��Ұ� �Ϸ�Ǿ����ϴ�.");
		else
			System.out.println("���� ��Ұ� �����Ͽ����ϴ�.");
	}

	private void attendClass() {
		String newAttendClassIdNum = this.inputString("���� ��û�� �м���ȣ : ");
		subject.setPhoneNum(phoneNum);
		
		boolean r = daou.modifyUser(user);
		
		String newAttenduserId = this.inputString("���� ��û�� �л��� �й� : ");
		
		
	}

	private void inquirySyllabus() {
		// �м���ȣ�� ��ȸ�ؾ� �Ѵ�. �ֳ��ϸ� ��������δ� �ٸ� ���ǰ�ȹ���� ������ ���� �ִ�.
		
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
