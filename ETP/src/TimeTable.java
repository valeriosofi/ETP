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
import static java.util.Comparator.*; 
public class TimeTable {
	private Integer tmax;
	private List<Exam> esamiPeggiori;
	private SortedMap<Integer,Exam> exams;
	private SortedMap<String,List<Exam>> students;  //per ogni matricola la lista degli esami a cui si � iscritto
	private int[][] n;
	private int E,iteration,S,count, worst,num;
	private double current_obj,best_obj;
	private boolean trovato = false, compatibile=true, continua=true;
	private SortedMap<Integer,List<Exam>> initialSolution;
	private SortedMap<Integer,List<Exam>> current_solution;
	private SortedMap<Integer,List<Exam>> best_solution;
	private Move[] tabu;
//	private List<Move> moves;
	private List<Neighbor> neighborhood;
	
	
	public TimeTable()
	{
		exams=new TreeMap<>();
		students=new TreeMap<>();
		current_solution=new TreeMap<>();
		tabu=new Move[4];
		
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
						if(distance<=5) //devo considerare la penalit�
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
			best_solution.values().stream().flatMap(l->l.stream()).sorted(comparing(e->e.getId())).forEach(e->out.format("%d %d\n",e.getId(),e.getTime_slot()));
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
			continua=true;
			
			while(!esamiDaAssegnare.isEmpty() && continua) {	
				Exam esameScelto;
				worst=tmax;													
				esamiPeggiori = new ArrayList<>();
				Iterator<Exam> iter = esamiDaAssegnare.values().iterator();
				
				while(iter.hasNext() && continua) {
					Exam e = iter.next();
					List<Integer> timeSlotsDisponibili = new ArrayList<>();
					count=0;
					for(int i=1; i<=tmax; i++){
						if(checkTimeslot(i, e.getId())){
							count++;
							timeSlotsDisponibili.add(i);
						}
					}
					if(!timeSlotsDisponibili.isEmpty()) {
						e.setTimeSlotsDisponibili(timeSlotsDisponibili);
						if(count<worst || esamiPeggiori.isEmpty()){
							esamiPeggiori = new ArrayList<>();
							worst=count;
							esamiPeggiori.add(e);
						}
						else if(count==worst){
							esamiPeggiori.add(e);
						}
					}
					else
						continua = false;
				}
				
				if(continua) {
					esameScelto = esamiPeggiori.get(new Random().nextInt(esamiPeggiori.size()));
					int r = esameScelto.getTimeSlotsDisponibili().get(new Random().nextInt(esameScelto.getTimeSlotsDisponibili().size()));
					initialSolution.get(r).add(esameScelto);
					esameScelto.setTime_slot(r);
					esamiDaAssegnare.remove(esameScelto.getId());
				}
			}
			
			if(esamiDaAssegnare.isEmpty())
				trovato=true;
		}
		
		initialSolution.values().stream().forEach(t->sum(t.size()));
		System.out.println("Esami assegnati: " + num);
		return initialSolution;
	}
	

	private List<Neighbor> Generate_Neighborhood() {
		List<Neighbor> res=new ArrayList<>();
		//List<Move> m=new ArrayList<>();

		for(int i=1;i<=tmax;i++)
		{
		
		 for(int j=i+1;j<=tmax;j++)
		 { 	
			 //dopo aver generato il clone faccio modifica al ts ->genero tutti gli swap
			SortedMap<Integer,List<Exam>> neighbor=Clone_solution();
			List<Exam> first=neighbor.remove(i);
   
			for(int k=0;k<first.size();k++)  //coerenza tra mappa e timeslot in ogni esame
		    	first.get(k).setTime_slot(j);

			List<Exam> second=neighbor.remove(j);
			
			for(int k=0;k<second.size();k++)      
				second.get(k).setTime_slot(i);
			neighbor.put(i,second);
			neighbor.put(j, first);
			//res.add(neighbor);
			Move move;
			if(first.size()!=0)
			   move=new Move(first.get(0),j,i);//il primo esame della lista rappresenta lo spostamento dell'intera lista
			else
			   move=new Move(null,j,i);//Se sposto da i->j mossa inversa proibita j->i
												// Move (null,j,i) se lista vuota
			//m.add(move); //Ricavo la mossa usando come indice l'indice del neighbor scelto nel neighborhood
		    Neighbor n=new Neighbor(neighbor,move);
		    res.add(n);

		  
		 }
		}
	//	moves=m;
		//System.out.println(res.size());
		return res;
	}
	
	
	
	
	private SortedMap<Integer, List<Exam>> Clone_solution() {

		SortedMap<Integer,List<Exam>> clone=initializeInitialSolution();
		for(int i=1;i<=tmax;i++)
		{
			List<Exam> l=current_solution.get(i);
			List<Exam> l_new=clone.get(i);
			l.stream().forEach(e->{
				Exam e_new=(Exam)e.clone();
				l_new.add(e_new);
			});
		}
		
		
		return clone;
	}

	private Neighbor best_In_Neighborhood() {

			Neighbor best=null;
		  double best_obj_in_neighborhood=-1;
			Iterator<Neighbor> iter=neighborhood.iterator();
			while(iter.hasNext())
			{
				Neighbor neighbor=iter.next();
				double obj=Evaluate(neighbor.getSolution());
	
					if(obj<best_obj_in_neighborhood|| best_obj_in_neighborhood==-1)//se migliore delle altre
					{
						//int index=neighborhood.indexOf(neighbor);
						Move m=neighbor.getM();
						boolean bad=false;
						for(int i=0;i<tabu.length;i++)
						{
							if( tabu[i]!=null &&tabu[i].Equals(m))
								if(obj<best_obj)//aspiration
								{
									best=neighbor;
									best_obj_in_neighborhood=obj;
									//la mossa � gi� in tabu list e non la tocco
								}
								else
									bad=true;

						}
						if(!bad)
						{
						 best_obj_in_neighborhood=obj;
						 best=neighbor;
					//	int  index=iteration%tabu.length;//continuo a sovrascrivere fino a quando non trovo il migliore nel neighborhood
						// tabu[index]=m;
						}
					}
				
			}
		return best;
	}

	
	
	public void Solve()
	{

		current_solution=Generate_Initial_Solution();
		best_solution=current_solution;
		System.out.println(current_solution);
		Print();
		current_obj=Evaluate(current_solution);
		best_obj=current_obj;
		System.out.println("obj di partenza:"+current_obj);
	//Start Tabu search
		iteration=0;
		while(iteration<2000)//sostituire con il limite di tempo
		{
			neighborhood=Generate_Neighborhood();
			Neighbor best=best_In_Neighborhood();
			
			current_solution=best.getSolution();
			current_obj=Evaluate(current_solution);
			 
			if(current_obj<best_obj)
			{
				best_obj=current_obj;
				best_solution=current_solution;
			}

			int  index=iteration%tabu.length;
			 tabu[index]=best.getM();

			iteration++;
			if(iteration==500)
				{
				Move[] tmp=new Move[15];
				for(int i=0;i<tabu.length;i++)
					tmp[i]=tabu[i];
				tabu=tmp;
				}
			
		}
		Print();

		System.out.println(current_solution);
		System.out.println(best_solution);
		System.out.println("obj di arrivo: "+best_obj);
		return  ;
	}


}
