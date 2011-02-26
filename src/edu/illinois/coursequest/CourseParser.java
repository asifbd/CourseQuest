package edu.illinois.coursequest;

//This class contains functions for string manipulation for CourseQuest...
//3 public functions toCourseList, toCourse, and safeStrings

public class CourseParser {
    public static DaySlot toDaySlot(String s){
	if(s == null || s.length() != 5)
	    return  new DaySlot(false,false,false,false,false);
	boolean[] days = new boolean[5];
	for(int i  = 0; i < 5; i++){
	    if(s.charAt(i) != ' '){
		days[i] = true;
	    }
	    
	}
	return new DaySlot(days[0],days[1],days[2],days[3],days[4]);
    }
    public static boolean safeString(String s){
	return !(s==null ||  s.contains("|"));
    }
}