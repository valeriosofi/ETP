import java.util.ArrayList;
import java.util.List;

public class Exam {
	private int id;
	private int tot_stud;
	private Integer time_slot;
	private List<String> students;
	
	public Exam(int id,int tot_stud)
	{
		this.id=id;
		this.tot_stud=tot_stud;
		time_slot=-1;
		students=new ArrayList<>();
	}
//x

	public List<String> getStudents() {
		return students;
	}


	public int getId() {
		return id;
	}


	public int getTot_stud() {
		return tot_stud;
	}


	public Integer getTime_slot() {
		return time_slot;
	}
	public void addStudent(String s)
	{
		students.add(s);
		return;
	}
}
