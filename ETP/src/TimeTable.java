import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;
import java.io.*;
import java.lang.Math;
import static java.util.stream.Collectors.*;
public class TimeTable {
	private Integer tmax;
	private List<Exam> esamiPeggiori;
	private SortedMap<Integer,Exam> exams;
	private SortedMap<String,List<Exam>> students;  //per ogni matricola la lista degli esami a cui si è iscritto
	private int[][] n;
	private int E,counter,S,infeasibility,count, worst,num;
	private double current_obj;
	private boolean trovato = false, compatibile=true;
	private SortedMap<Integer,List<Exam>> initialSolution;
	private SortedMap<Integer,List<Exam>> current_solution;
	private Move[] tabu;
	private List<SortedMap<Integer,List<Exam>>> neighborhood;
	
	
	public TimeTable()
	{
		exams=new TreeMap<>();
		students=new TreeMap<>();
		current_solution=new TreeMap<>();
		tabu=new Move[3];
		neighborhood=new ArrayList<>();
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
					// e.addStudent(student);
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
			 S=students.keySet().size();
			 //popolo n_ij
			 for (int i=0; i<S;i++)
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
			 System.out.println(Arrays.deepToString(n)); 

		 }  catch (IOException e) {};
		 
		 
	}

	
	private double Evaluate(SortedMap<Integer,List<Exam>> solution)
	{
		double obj=0;
		for (int i=1;i<=tmax-1;i++) 
		{
			if(solution.containsKey(i))
			{
				List<Exam>l=solution.get(i);

			Iterator<Exam> iter=l.iterator();
			while(iter.hasNext())//per ogni esame di ogni time_slot
			{
				Exam e=iter.next();
				for(int j=i+1;j<=tmax;j++) //Considero gli esami nei time_slot successivi
				{
					if(solution.containsKey(j))
					{
					List<Exam> l_int=solution.get(j);
					Iterator<Exam> iter_int=l_int.iterator();
					while(iter_int.hasNext())
					{
						Exam e_int=iter_int.next();
						int distance=Math.abs(e.getTime_slot()-e_int.getTime_slot());
						if(distance<=5) //devo considerare la penalità
						{
							int row=e.getId()-1;
							int col=e_int.getId()-1;
							double po=Math.pow(2,5-distance);
							
							obj+=po*n[row][col];
						}
					    
					}
				}
			}
		}
		}
		
	}
		return obj/S;
	}
	
	
	
	private void Print()
	{
		try(PrintWriter out=new PrintWriter(new FileWriter("C:\\\\Users\\\\angij\\\\Desktop\\\\Polito magistrale 1 anno\\\\Optimization methods and algoritms\\\\Assignment\\instancename_OMAAL_group04.sol")))
		{
			
				Iterator<Exam> iter=exams.values().iterator();
		while(iter.hasNext())
		{
			Exam e=iter.next();
			out.format("%d %d\n",e.getId(),e.getTime_slot());
		}
	 }catch (IOException e) {};
	}
	
	private SortedMap<Integer,List<Exam>> initializeInitialSolution() {
		SortedMap<Integer,List<Exam>> solution = new TreeMap<>();
		for(int i=1; i<=tmax; i++){
			solution.put(i, new ArrayList<>());
		}
		return solution;
	}

	private SortedMap<Integer,Exam> copiaEsami() {
		SortedMap<Integer,Exam> esami = new TreeMap<>();
		exams.entrySet().stream().forEach(e-> esami.put(e.getKey(), e.getValue()));
		return esami;
	}

	private Object sum(int size) {
		return num=num+size;
	}

	public boolean checkTimeslot(int j, int i) {
		final int idx = i;
		compatibile = true;
		if(initialSolution.get(j).size() != 0){
			List<Exam> listOfExams = initialSolution.get(j);
			listOfExams.stream().forEach(e -> {
				if(n[e.getId()-1][idx-1] != 0)
					compatibile = false;
			});
		}
		return compatibile;
	}
	

	public SortedMap <Integer,List<Exam>> Generate_Initial_Solution() {
		while(!trovato){
			initialSolution = initializeInitialSolution();
			SortedMap<Integer,Exam> esamiDaAssegnare = copiaEsami();
			while(!esamiDaAssegnare.isEmpty()){
				Exam esameScelto;
				worst=tmax;
				esamiPeggiori = new ArrayList<>();
				esamiDaAssegnare.values().stream().forEach(e-> {
					List<Integer> timeSlotsDisponibili = new ArrayList<>();
					count=0;
					for(int i=1; i<=tmax; i++){
						if(checkTimeslot(i, e.getId())){
							count++;
							timeSlotsDisponibili.add(i);
						}
					}
					
					e.setTimeSlotsDisponibili(timeSlotsDisponibili);
					if(count<worst || esamiPeggiori.isEmpty()){
						esamiPeggiori = new ArrayList<>();
						worst=count;
						esamiPeggiori.add(e);
					}
					else if(count==worst){
						esamiPeggiori.add(e);
					}
				});
				if(esamiPeggiori.size()>0){
					if(esamiPeggiori.size()>1){
						esameScelto = esamiPeggiori.get(new Random().nextInt(esamiPeggiori.size()));
					}
					else
						esameScelto = esamiPeggiori.get(0);
					if(esameScelto.getTimeSlotsDisponibili().size()==0) {
						break;
					}
					int r = esameScelto.getTimeSlotsDisponibili().get(new Random().nextInt(esameScelto.getTimeSlotsDisponibili().size()));
					initialSolution.get(r).add(esameScelto);
					esamiDaAssegnare.remove(esameScelto.getId());
				}
				
			}
			if(esamiDaAssegnare.isEmpty())
				trovato=true;
			else
				System.out.println("ho fatto più cicli");
			
		}
	//	System.out.println(initialSolution);
		initialSolution.values().stream().forEach(t->sum(t.size()));
		System.out.println("Esami assegnati: " + num);
		//Ricopio i ts negli esami di exams serve per stampa
	for(int i=1;i<=tmax;i++)
	{
		List<Exam>l=initialSolution.get(i);
		Iterator<Exam> iter=l.iterator();
		while(iter.hasNext())
		{
			Exam e=iter.next();
			Exam e1=exams.get(e.getId());
			e1.setTime_slot(i);
		}
	}
		return initialSolution;
		
		
	}
	public void Solve()
	{

		current_solution=Generate_Initial_Solution();
		System.out.println(current_solution);
		Print();
		current_obj=Evaluate(current_solution);
		return ;
	}
}
