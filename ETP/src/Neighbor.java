import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class Neighbor {
	private SortedMap<Integer,List<Exam>> solution=new TreeMap<>();
	private Move m;
	
	
	public Neighbor(SortedMap<Integer, List<Exam>> solution, Move m) {
		this.solution = solution;
		this.m = m;
	}


	public SortedMap<Integer, List<Exam>> getSolution() {
		return solution;
	}


	public Move getM() {
		return m;
	}
}
