import java.util.*; 
public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TimeTable t= new TimeTable();
	/*	System.out.println("Please insert the path of the time-slot file: ");
		Scanner s = new Scanner(System.in); 
		String slot=s.nextLine();
		
		System.out.println("Please insert the path of the exams file: ");
		String ex=s.nextLine();
		System.out.println("Please insert the path of the time-slot file: ");
		 
		String st=s.nextLine();
		s.close();
		t.Initialize(slot,st,ex);*/
		
		t.Initialize("C:\\Users\\angij\\Desktop\\Polito magistrale 1 anno\\Optimization methods and algoritms\\Assignment\\instance07.slo", "C:\\Users\\angij\\Desktop\\Polito magistrale 1 anno\\Optimization methods and algoritms\\Assignment\\instance07.stu", "C:\\Users\\angij\\Desktop\\Polito magistrale 1 anno\\Optimization methods and algoritms\\Assignment\\instance07.exm");
		//t.Initialize("C:\\Users\\angij\\Desktop\\Polito magistrale 1 anno\\Optimization methods and algoritms\\Assignment\\instance06.slo", "C:\\Users\\angij\\Desktop\\Polito magistrale 1 anno\\Optimization methods and algoritms\\Assignment\\instance06.stu", "C:\\Users\\angij\\Desktop\\Polito magistrale 1 anno\\Optimization methods and algoritms\\Assignment\\instance06.exm");
		//t.Initialize("C:\\Users\\angij\\Desktop\\Polito magistrale 1 anno\\Optimization methods and algoritms\\Assignment\\instance05.slo", "C:\\Users\\angij\\Desktop\\Polito magistrale 1 anno\\Optimization methods and algoritms\\Assignment\\instance05.stu", "C:\\Users\\angij\\Desktop\\Polito magistrale 1 anno\\Optimization methods and algoritms\\Assignment\\instance05.exm");
		//t.Initialize("C:\\Users\\angij\\Desktop\\Polito magistrale 1 anno\\Optimization methods and algoritms\\Assignment\\instance04.slo", "C:\\Users\\angij\\Desktop\\Polito magistrale 1 anno\\Optimization methods and algoritms\\Assignment\\instance04.stu", "C:\\Users\\angij\\Desktop\\Polito magistrale 1 anno\\Optimization methods and algoritms\\Assignment\\instance04.exm");
		//t.Initialize("C:\\Users\\angij\\Desktop\\Polito magistrale 1 anno\\Optimization methods and algoritms\\Assignment\\instance03.slo", "C:\\Users\\angij\\Desktop\\Polito magistrale 1 anno\\Optimization methods and algoritms\\Assignment\\instance03.stu", "C:\\Users\\angij\\Desktop\\Polito magistrale 1 anno\\Optimization methods and algoritms\\Assignment\\instance03.exm");
		//t.Initialize("C:\\Users\\angij\\Desktop\\Polito magistrale 1 anno\\Optimization methods and algoritms\\Assignment\\instance02.slo", "C:\\Users\\angij\\Desktop\\Polito magistrale 1 anno\\Optimization methods and algoritms\\Assignment\\instance02.stu", "C:\\Users\\angij\\Desktop\\Polito magistrale 1 anno\\Optimization methods and algoritms\\Assignment\\instance02.exm");
		//t.Initialize("C:\\Users\\angij\\Desktop\\Polito magistrale 1 anno\\Optimization methods and algoritms\\Assignment\\instance01.slo", "C:\\Users\\angij\\Desktop\\Polito magistrale 1 anno\\Optimization methods and algoritms\\Assignment\\instance01.stu", "C:\\Users\\angij\\Desktop\\Polito magistrale 1 anno\\Optimization methods and algoritms\\Assignment\\instance01.exm");

	//	t.Initialize("C:\\Users\\angij\\Desktop\\Polito magistrale 1 anno\\Optimization methods and algoritms\\Assignment\\test.slo","C:\\Users\\angij\\Desktop\\Polito magistrale 1 anno\\Optimization methods and algoritms\\Assignment\\test.stu","C:\\Users\\angij\\Desktop\\Polito magistrale 1 anno\\Optimization methods and algoritms\\Assignment\\test.exm");
				t.Solve();
	}

}
