package utilities;

import beans.*;

public class PointSystem {
	
	private SetBean set;
	private ExerciseBean excercise;
	private int point;
	
	public SetBean getSet() {
		return set;
	}
	public void setSet(SetBean set) {
		this.set = set;
	}
	public ExerciseBean getExcercise() {
		return excercise;
	}
	public void setExcercise(ExerciseBean excercise) {
		this.excercise = excercise;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	
//	public int setPointsforExcercise(TrainingBean training) 
//	{
//		int points = 0;
//		for(ExerciseBean e : training.getExercise()) 
//		{
//			for(SetBean s : e.getSets()) 
//			{
//				points += s.getPoints();
//			}
//			points += e.getPoint();
//		}
//		return points; 
//	}
	

}
