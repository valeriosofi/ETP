import java.util.*; 
public class Tester {
private TimeTable t;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 String exm,slo,stu;
		 long limit;

		TimeTable t= new TimeTable();
		slo=args[0];
		stu= args[1];
		exm=args[2];
		//limit=Long.parseLong(args[3]);
		
		
		
		//t.Initialize(slot,st,ex);
		
		//t.Initialize("C:\\Users\\angij\\Desktop\\Polito magistrale 1 anno\\Optimization methods and algoritms\\Assignment\\instance07.slo", "C:\\Users\\angij\\Desktop\\Polito magistrale 1 anno\\Optimization methods and algoritms\\Assignment\\instance07.stu", "C:\\Users\\angij\\Desktop\\Polito magistrale 1 anno\\Optimization methods and algoritms\\Assignment\\instance07.exm");
		t.Initialize("C:\\Users\\angij\\Desktop\\Polito magistrale 1 anno\\Optimization methods and algoritms\\Assignment\\instance06.slo", "C:\\Users\\angij\\Desktop\\Polito magistrale 1 anno\\Optimization methods and algoritms\\Assignment\\instance06.stu", "C:\\Users\\angij\\Desktop\\Polito magistrale 1 anno\\Optimization methods and algoritms\\Assignment\\instance06.exm");
		//t.Initialize("C:\\Users\\angij\\Desktop\\Polito magistrale 1 anno\\Optimization methods and algoritms\\Assignment\\instance05.slo", "C:\\Users\\angij\\Desktop\\Polito magistrale 1 anno\\Optimization methods and algoritms\\Assignment\\instance05.stu", "C:\\Users\\angij\\Desktop\\Polito magistrale 1 anno\\Optimization methods and algoritms\\Assignment\\instance05.exm");
		//t.Initialize("C:\\Users\\angij\\Desktop\\Polito magistrale 1 anno\\Optimization methods and algoritms\\Assignment\\instance04.slo", "C:\\Users\\angij\\Desktop\\Polito magistrale 1 anno\\Optimization methods and algoritms\\Assignment\\instance04.stu", "C:\\Users\\angij\\Desktop\\Polito magistrale 1 anno\\Optimization methods and algoritms\\Assignment\\instance04.exm");
		//t.Initialize("C:\\Users\\angij\\Desktop\\Polito magistrale 1 anno\\Optimization methods and algoritms\\Assignment\\instance03.slo", "C:\\Users\\angij\\Desktop\\Polito magistrale 1 anno\\Optimization methods and algoritms\\Assignment\\instance03.stu", "C:\\Users\\angij\\Desktop\\Polito magistrale 1 anno\\Optimization methods and algoritms\\Assignment\\instance03.exm");
		//t.Initialize("C:\\Users\\angij\\Desktop\\Polito magistrale 1 anno\\Optimization methods and algoritms\\Assignment\\instance02.slo", "C:\\Users\\angij\\Desktop\\Polito magistrale 1 anno\\Optimization methods and algoritms\\Assignment\\instance02.stu", "C:\\Users\\angij\\Desktop\\Polito magistrale 1 anno\\Optimization methods and algoritms\\Assignment\\instance02.exm");
		//t.Initialize("C:\\Users\\angij\\Desktop\\Polito magistrale 1 anno\\Optimization methods and algoritms\\Assignment\\instance01.slo", "C:\\Users\\angij\\Desktop\\Polito magistrale 1 anno\\Optimization methods and algoritms\\Assignment\\instance01.stu", "C:\\Users\\angij\\Desktop\\Polito magistrale 1 anno\\Optimization methods and algoritms\\Assignment\\instance01.exm");
		
		//limit=System.currentTimeMillis();
		
		//t.Initialize("C:\\Users\\angij\\Desktop\\Polito magistrale 1 anno\\Optimization methods and algoritms\\Assignment\\test.slo","C:\\Users\\angij\\Desktop\\Polito magistrale 1 anno\\Optimization methods and algoritms\\Assignment\\test.stu","C:\\Users\\angij\\Desktop\\Polito magistrale 1 anno\\Optimization methods and algoritms\\Assignment\\test.exm");
				t.Solve();
	}

}
