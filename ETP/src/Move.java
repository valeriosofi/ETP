
public class Move {
	private Exam e;
	private int from,to;
	
	
	
	public Exam getE() {
		return e;
	}



	public int getFrom() {
		return from;
	}



	public int getTo() {
		return to;
	}



	public Move(Exam e, int from, int to) {
		this.e = e;
		this.from = from;
		this.to = to;
	}
	public boolean Equals(Move m)
	{
		return e.getId()==m.getE().getId() && from==m.getFrom() && to==m.getTo();
	}

}
