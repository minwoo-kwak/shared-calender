import java.util.Scanner;

public class CalendarSystem {
	
    Scanner sc = new Scanner(System.in);
    MakeCalendar mc;
    
    public void init() {
    	System.out.println("날짜를 입력하세요, 종료:q");
        System.out.println("형식 : yyyy-MM");
        System.out.print("> ");
        String date = sc.nextLine();
        String[] dateArr = date.split("-");  
        mc = new MakeCalendar(Integer.parseInt(dateArr[0]),Integer.parseInt(dateArr[1]));
        mc.show();
        menu();
    }
    public void menu() {
    	while(true) {
        	System.out.println("1.하루 일정 보기 2.일정 검색 3.뒤로가기");
            System.out.print("> ");
            String menu = sc.nextLine();
            switch (menu) {
    		case "1":
    			int[] info = showList();
    			int selectedDay = info[0];
    			int status= info[1];
    			scheduleManage(status,selectedDay);
    			break;
    		case "2":
    			//search();
    			break;
    		case "3":
    			mc.show();
    			break;

    		default:
    			break;
    		}
    	}
    }
    public int[] showList() {
    	System.out.println("일정을 볼 날짜를 입력하세요");
        System.out.print("> ");
        int selectedDay = Integer.parseInt(sc.nextLine());
        int status = mc.daySchedule(selectedDay);
        int[] info ={selectedDay,status};
        return info;
        
        
    }
    public void scheduleManage(int status, int selectedDay) {
    	if(status==0) {
            System.out.println("해당일자에 일정이 없습니다.");
            System.out.println("1.일정 추가");
         }else{
            System.out.println("1.일정 추가 2.일정 상세 보기  3.일정 수정 4.일정 삭제 5.뒤로가기");
         }
    	String menu = sc.nextLine();
        switch (menu) {
        case "1":
        	addSchedule(selectedDay);
           break;
        case "2":
        	scheduleDetail(selectedDay);
           break;
        case "3":
        	updateSchedule(selectedDay);
           break;   
        case "4":
        	removeSchedule(selectedDay);
           break;
        case "5":
        	mc.show();
           break;
        }
    }
	private void removeSchedule(int selectedDay) {
		// TODO Auto-generated method stub
		System.out.println("삭제할 일정의 일정번호를 입력하세요 > ");
		int upNum = Integer.parseInt(sc.nextLine());
		int i=0;
		//mc.daySchedule(selectedDay);
		for(Schedule sch : mc.getScheduleList()) {
			
			if(sch.getNo()==upNum) {
				mc.removeSchedule(sch);
			}
		}
		isSchedule(i);
	}
	private void scheduleDetail(int selectedDay) {
		// TODO Auto-generated method stub
		System.out.println("상세보기할 일정의 일정번호를 입력하세요 > ");
		int upNum = Integer.parseInt(sc.nextLine());
		int i=0;
		//mc.daySchedule(selectedDay);
		for(Schedule sch : mc.getScheduleList()) {
			
			if(sch.getNo()==upNum) {
				System.out.println("일정 번호\t일정 이름\t일정 작성자\t일정 카테고리\t일정 권한\t일정설명");
				System.out.print(sch.getNo()+"\t");
				System.out.print(sch.getScheduleName()+"\t");
				System.out.print(sch.getWriter()+"\t");
				System.out.print(sch.getCategory()+"\t");
				System.out.print(sch.getAuthority()+"\t");
				System.out.print(sch.getContent()+"\n");
				i++;
			}
		}
		isSchedule(i);
	}
	private void updateSchedule(int selectedDay) {
		System.out.print("수정할 일정의 일정번호를 입력하세요 > ");
		int upNum = Integer.parseInt(sc.nextLine());
		int i=0;
		//mc.daySchedule(selectedDay);
		for(Schedule sch : mc.getScheduleList()) {
			
			if(sch.getNo()==upNum) {
		        System.out.println("수정할 일정을 입력하세요");
		        System.out.print("일정이름 > ");
		        sch.setScheduleName(sc.nextLine());
		        System.out.print("작성자 > ");
		        sch.setWriter(sc.nextLine());		  
		        System.out.print("일정권한 > ");
		        sch.setAuthority(sc.nextLine());		       
		        System.out.print("설명 > ");
		        sch.setContent(sc.nextLine());		        
		        System.out.print("기간 > ");
		        sch.setPeriod(Integer.parseInt(sc.nextLine()));		        
		        System.out.print("알람여부 y or n > ");
		        //Alarm alarm = new Alarm();
		        if(sc.nextLine().equals("y")) {
		        	sch.getAlarm().setStatus(true);
		        }else {
		        	sch.getAlarm().setStatus(false);
		        }
		        //sch.setAlarm(alarm);
		        //Alarm alarm = sch.ge
		        String al=sc.nextLine();
		        System.out.print("카테고리 > ");
		        sch.setCategory(sc.nextLine());
		        //String category=sc.nextLine();
		        //Alarm alarm = new Alarm();
		        i++;
			}
		}
		isSchedule(i);
		
		
	}
	private void addSchedule(int selectedDay) {
        System.out.println("추가할 일정을 입력하세요");
        System.out.print("일정이름 > ");
        String scheduleName=sc.nextLine();
        System.out.print("작성자 > ");
        String writer=sc.nextLine();
        System.out.print("일정권한 > ");
        String authority=sc.nextLine();
        System.out.print("설명 > ");
        String content=sc.nextLine();
        System.out.print("기간 > ");
        String period=sc.nextLine();
        System.out.print("알람여부 y or n > ");
        String al=sc.nextLine();
        System.out.print("카테고리 > ");
        String category=sc.nextLine();
        Alarm alarm = new Alarm();
        if(al.equals("y")) {
           alarm.setStatus(true);
        }else {
           alarm.setStatus(false);
        }
        Schedule schedule = new Schedule(writer, scheduleName, Integer.parseInt(period), selectedDay, content, authority, alarm, category);
        
        mc.addSchedule(schedule);
        System.out.println("일정 추가 완료");
        mc.show();
        menu();
	}
	public void isSchedule(int status) {
		if(status==0) {
			System.out.println("해당하는 일정이 없습니다");
			mc.show();
			menu();
		}else {
			System.out.println("완료");
			mc.show();
			menu();
		}
	}
    

}

