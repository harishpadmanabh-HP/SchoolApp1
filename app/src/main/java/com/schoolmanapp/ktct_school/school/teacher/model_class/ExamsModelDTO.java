package com.schoolmanapp.ktct_school.school.teacher.model_class;

import java.util.List;
import java.io.Serializable;

public class ExamsModelDTO implements Serializable {
	private boolean status;
	private String msg;
	private List<ExamsDTO> exams;

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setExams(List<ExamsDTO> exams){
		this.exams = exams;
	}

	public List<ExamsDTO> getExams(){
		return exams;
	}

	@Override
 	public String toString(){
		return 
			"ExamsModelDTO{" + 
			"status = '" + status + '\'' + 
			",msg = '" + msg + '\'' + 
			",exams = '" + exams + '\'' + 
			"}";
		}
}