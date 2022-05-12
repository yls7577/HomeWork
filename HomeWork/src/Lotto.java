import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Lotto {
	public static void main(String[] args) {
		/*
		 1. 1~45 사이의 숫자 중 6개의 번호와 1개의 보너스 번호를 갖는다.
		 2. 모든 번호는 중복될 수 없고, 당첨 번호는 순서와 상관없이 값만 일치하면 된다.
		 3. 당첨번호는 랜덤함수를 이용, 사용자 입력 번호는 스캐너를 사용해서 입력받는다.
		 4. 6개의 번호 중 5개가 일치할 경우 2등 or 3등인데, 남은 번호가 보너스번호(7번째)와 일치하면 2등, 아니면 3등
		 5. 출력 : 맞춘 번호, 맞춘 개수, 등수 
		*/
		
		// 로또 당첨번호 list1 선언
		List<Integer> list1 = new ArrayList<>();
		
		// 랜덤 클래스
		Random ran = new Random();
		
		// 당첨번호 입력 반복문 (보너스 번호까지 총 7번 반복) 
		for(int i=0; i<7; i++) {
			
			// 임시변수에 랜덤값(1~45) 대입
			int temp = ran.nextInt(45) + 1;
			
			// list1의 값에 랜덤값 temp가 있으면
			if(list1.contains(temp)) {
				i--;			// i를 하나빼서 동일 회차 재반복하도록
				continue;		// 이번 회차 반복은 여기서 끝내고 다음 회차 반복 진행
			}
			// 중복된 값이 없으면 list1에 값 추가
			list1.add(temp);
		}
		System.out.println("당첨번호 -> " + list1);
		
		
		// 사용자 로또 번호 list2 선언
		List<Integer> list2 = new ArrayList<>();
		
		// 입력받기 위한 스캐너 선언
		Scanner scan = new Scanner(System.in);
		
		// 사용자 로또 번호는 보너스 번호 없이 총 6개의 숫자 (6번 반복)
		for(int i=0; i<6; i++) {
			System.out.println("숫자를 입력하세요. (" + (i+1) + "번)");
			
			int temp = scan.nextInt();		// 스캐너로 입력받은 정수값을 temp 변수에 대입
			
			// 입력받은 값의 범위가 1~45 사이의 값이 아니면
			if(temp < 1 || temp > 45) {
				i--;		// i를 하나빼서 동일 회차 재반복하도록
				System.out.println("범위값을 초과하였습니다. 1~45 사이의 값을 다시 입력하세요.");
				continue;	// 이번 회차 반복은 여기서 끝내고 다음 회차 반복 진행
			}

			// list2에 temp 변수에 저장된 값이 있으면
			if(list2.contains(temp)) {
				i--;		// i를 하나빼서 동일 회차 재반복하도록
				System.out.println("중복된 값은 입력할 수 없습니다. 다시 입력하세요");
				continue;	// 이번 회차 반복은 여기서 끝내고 다음 회차 반복 진행
			}
			// 중복된 값이 없으면 list2에 값 추가
			list2.add(temp);
		}
		System.out.println("사용자 입력 -> " + list2);
		
		
		// 로또를 비교하여 맞춘 번호, 개수, 등수를 출력
		// 6개 맞으면 1등
		// 5개 맞으면 2등 or 3등 인데, 남은 번호 하나가 보너스 번호(7번째)와 일치하면 2등, 아니면 3등
		// 4개 맞으면 4등, 3개 맞으면 5등
		
		
		// 맞춘 번호를 저장할 list3 선언 및 객체화
		List<Integer> list3 = new ArrayList<>();
		// 맞춘 개수 변수 count 선언 및 초기화
		int count = 0;
		// 등수 변수 rank 선언 및 초기화
		String rank = "";
		
		
		// 로또 당첨번호와 사용자가 입력한 번호 비교
		for(int i=0; i<list1.size()-1; i++) {		// 마지막 7번째 숫자는 보너스 숫자이므로 비교하지 않음.
			for(int j=0; j<list2.size(); j++) {
				
				// 로또 당첨번호와 사용자가 입력한 번호가 같으면
				if(list1.get(i) == list2.get(j)) {
					count++;		// 맞춘 개수 카운트 + 1
					list3.add(list2.get(j));	// 사용자가 맞춘 번호를 list3에 추가
					list2.remove(j);			// 2등과 3등을 가려내기 위해. 맞춘 번호를 지워서 5개 맞췄을 때 마지막 번호 하나만 남김.
				}
				
			}
		}
		
		
		// 맞춘 개수를 비교하여 등수 저장
		if(count == 6) {
			rank = "1등";
		} else if(count == 5) {
			// 당첨번호의 보너스 번호(7번째 값)가 사용자 번호 중 맞춘 5개를 제외한 남은 번호 하나와 같으면 2등, 틀리면 3등.
			if(list1.get(6) == list2.get(0)) {		// list2에는 못맞춘 번호만 남아있음. (5개를 맞췄을 경우 못맞춘 번호 하나만 있음)		
				rank = "2등";
			} else {
				rank = "3등";
			}
		} else if(count == 4) {
			rank = "4등";
		} else if(count == 3) {
			rank = "5등";
		} else {
			rank = "꽝";
		}
		
		
		System.out.println("list2 : " +list2);
		
		System.out.println("---------------------------------------");
		
		System.out.println("맞춘 숫자 : " +list3);
		System.out.println("맞춘 개수 : " + count);
		System.out.println("등수 : " + rank);
		
		
	}

}
