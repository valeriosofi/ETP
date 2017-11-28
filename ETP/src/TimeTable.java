import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;
import java.io.*;
public class TimeTable {
	private Integer tmax;
	private SortedMap<Integer,Exam> exams;
	private SortedMap<String,List<Exam>> students;  //per ogni matricola la lista degli esami a cui si è iscritto
	private int[][] n;
	private int E,counter;
	private SortedMap<Integer,List<Exam>> current_solution;
	private Move[] tabu;
	
	
	public TimeTable()
	{
		exams=new TreeMap<>();
		students=new TreeMap<>();
		current_solution=new TreeMap<>();
		tabu=new Move[3];
	}
	
	public void Initialize (String slo,String stu, String exm)
	{
		 try(BufferedReader in=new BufferedReader(new FileReader(slo))) //leggo tmax
		 {   
			 String line;
			 Scanner s;
			 while((line=in.readLine())!=null)
			 {
				 s=new Scanner(line);
				 tmax=s.nextInt();
				 s.close();
			 }
			
		 }  catch (IOException e) {};
		 
		 
		 try(BufferedReader in=new BufferedReader(new FileReader(exm))) //leggo esami
		 {   
			 String line;
			 Scanner s;
			 while((line=in.readLine())!=null)
			 {
				 s=new Scanner(line);
				 Exam e=new Exam(s.nextInt(),s.nextInt());
				 exams.put(e.getId(),e);
				 s.close();
			 }
			 E=exams.keySet().size();
			 n=new int[E][E];
			 //System.out.println(exams.keySet().size());
			 
		/*	 for (int i = 0; i < exams.keySet().size(); i++) {        
				 n[i] = new int[exams.keySet().size()]; 
			 }
*/
		 }  catch (IOException e) {};
		 
		 try(BufferedReader in=new BufferedReader(new FileReader(stu));
			 PrintWriter out=new PrintWriter(new FileWriter("prova.txt")))
		 {   
			 
			 String line;
			 Scanner s;
			 while((line=in.readLine())!=null)
			 {
				 s=new Scanner(line);
				 String student=s.next();
				 
				 List<Exam>l=students.get(student);
				 if(l==null)
				 {
					 l=new ArrayList<>();
					 Exam e=exams.get(s.nextInt());
					 e.addStudent(student);
					 l.add(e);
					 students.put(student, l);
				 }
				 else
				 {
					 
					 Exam e=exams.get(s.nextInt());
					 e.addStudent(student);
					 l.add(e);
				 }
				 s.close();

			 }
			
			 //System.out.println(students.keySet().size());
			 
			 //popolo n_ij
			 for (int i=0; i<students.keySet().size();i++)
			 {
				 String st="s"+(i+1);
				 
				List<Exam> l=students.get(st);
				 for(int j=0;j<l.size();j++)
				 {
					 for(int k=0; k<l.size() ;k++) //anche k =j -> evito permutazioni
					 {
						if(j!=k)
						{
							int row=l.get(j).getId();
							int col=l.get(k).getId();
							 n[row-1][col-1]++;//esami partono da 1
							
						}
					 }
					 
				 }
			 }
		/*	 for(int i=0;i<E;i++)
			 {
				 for(int j=0;j<E;j++)
				 {
					 
					 out.print(n[i][j]);
					 out.print(" ");
				 }
				 out.println();
			 }*/
			 System.out.println(Arrays.deepToString(n)); 

		 }  catch (IOException e) {};
		 
		 
	}

	public SortedMap <Integer,List<Exam>> Generate_Initial_Solution()
	{
		return null;
		
		
	}
	
	public void Solve()
	{
		return ;
	}
	
	public boolean checkFeasibility(Exam e, SortedMap<Integer,List<Exam>> solution)
	{
		Integer t=e.getTime_slot();
		List<Exam> l=solution.get(t);
		
		for(int i=0;i<l.size();i++)
		{
			if(n[e.getId()-1][(l.get(i).getId())-1]>0)
			 return false;
		}
		
		return true;
	}
}
