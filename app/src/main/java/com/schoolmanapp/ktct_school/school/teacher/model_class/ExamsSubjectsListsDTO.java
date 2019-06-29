package com.schoolmanapp.ktct_school.school.teacher.model_class;

import java.io.Serializable;

public class ExamsSubjectsListsDTO implements Serializable {
	private int subId;
	private int examId;
	private String subject;
	private int mark;
	private boolean isActive;
	private String timeStamp;
	private int internalMarks;
	private int externalMark;
	private String examDate;
	private int subjectId;

	public void setSubId(int subId){
		this.subId = subId;
	}

	public int getSubId(){
		return subId;
	}

	public void setExamId(int examId){
		this.examId = examId;
	}

	public int getExamId(){
		return examId;
	}

	public void setSubject(String subject){
		this.subject = subject;
	}

	public String getSubject(){
		return subject;
	}

	public void setMark(int mark){
		this.mark = mark;
	}

	public int getMark(){
		return mark;
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

	public void setInternalMarks(int internalMarks){
		this.internalMarks = internalMarks;
	}

	public int getInternalMarks(){
		return internalMarks;
	}

	public void setExternalMark(int externalMark){
		this.externalMark = externalMark;
	}

	public int getExternalMark(){
		return externalMark;
	}

	public void setExamDate(String examDate){
		this.examDate = examDate;
	}

	public String getExamDate(){
		return examDate;
	}

	public void setSubjectId(int subjectId){
		this.subjectId = subjectId;
	}

	public int getSubjectId(){
		return subjectId;
	}

	@Override
 	public String toString(){
		return 
			"ExamsSubjectsListsDTO{" + 
			"subId = '" + subId + '\'' + 
			",examId = '" + examId + '\'' + 
			",subject = '" + subject + '\'' + 
			",mark = '" + mark + '\'' + 
			",isActive = '" + isActive + '\'' + 
			",timeStamp = '" + timeStamp + '\'' + 
			",internalMarks = '" + internalMarks + '\'' + 
			",externalMark = '" + externalMark + '\'' + 
			",examDate = '" + examDate + '\'' + 
			",subjectId = '" + subjectId + '\'' + 
			"}";
		}
}