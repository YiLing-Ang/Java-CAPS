package sg.edu.iss.team5.services;

import java.util.ArrayList;

import sg.edu.iss.team5.model.Student_Course;

public interface EnrolmentService {

	ArrayList<Student_Course> findAllEnrolment();
	
	Student_Course findEnrolment(String id);

	Student_Course createEnrolment(Student_Course course);

	Student_Course changeEnrolment(Student_Course course);

	void removeEnrolment(Student_Course course);	

}