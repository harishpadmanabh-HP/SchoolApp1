package com.schoolmanapp.ktct_school.school.teacher.model_class;

import java.util.List;
import java.io.Serializable;

public class ExamsDTO implements Serializable {
	private int examId;
	private int schoolId;
	private int userId;
	private int classId;
	private int divisionId;
	private String examName;
	private String examDate;
	private boolean isActive;
	private String timeStamp;
	private List<ExamsSubjectsListsDTO> examsSubjectsLists;

	public void setExamId(int examId){
		this.examId = examId;
	}

	public int getExamId(){
		return examId;
	}

	public void setSchoolId(int schoolId){
		this.schoolId = schoolId;
	}

	public int getSchoolId(){
		return schoolId;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	public void setClassId(int classId){
		this.classId = classId;
	}

	public int getClassId(){
		return classId;
	}

	public void setDivisionId(int divisionId){
		this.divisionId = divisionId;
	}

	public int getDivisionId(){
		return divisionId;
	}

	public void setExamName(String examName){
		this.examName = examName;
	}

	public String getExamName(){
		return examName;
	}

	public void setExamDate(String examDate){
		this.examDate = examDate;
	}

	public String getExamDate(){
		return examDate;
	}

	public void setIsActive(boolean isActive){
		this.isActive = isActive;
	}

	public boolean isIsActive(){
		return isActive;
	}

	public void setTimeStamp(String timeStamp){
		this.timeStamp = timeStamp;
	}

	public String getTimeStamp(){
		return timeStamp;
	}

	public void setExamsSubjectsLists(List<ExamsSubjectsListsDTO> examsSubjectsLists){
		this.examsSubjectsLists = examsSubjectsLists;
	}

	public List<ExamsSubjectsListsDTO> getExamsSubjectsLists(){
		return examsSubjectsLists;
	}

	@Override
 	public String toString(){
		return 
			"ExamsDTO{" + 
			"examId = '" + examId + '\'' + 
			",schoolId = '" + schoolId + '\'' + 
			",userId = '" + userId + '\'' + 
			",classId = '" + classId + '\'' + 
			",divisionId = '" + divisionId + '\'' + 
			",examName = '" + examName + '\'' + 
			",examDate = '" + examDate + '\'' + 
			",isActive = '" + isActive + '\'' + 
			",timeStamp = '" + timeStamp + '\'' + 
			",examsSubjectsLists = '" + examsSubjectsLists + '\'' + 
			"}";
		}
}