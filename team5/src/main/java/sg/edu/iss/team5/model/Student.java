package sg.edu.iss.team5.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;
import sg.edu.iss.team5.helper.status;

@Entity
@Table(name="student")
@Data
@NoArgsConstructor

public class Student {
	
	@Id
	private String studentID;
	private String name;
	private String email;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate startDate;
	@Column(name = "status_type", columnDefinition = "ENUM('INPROGRESS','SUBMITTED', 'APPROVED', 'WITHDRAWN', 'UPDATED', 'REJECTED','GRUADATEDs')")
	@Enumerated(EnumType.STRING)
	private status eventType;
	private double gpa;
	@OneToMany(mappedBy="studentID")
	private List<Student_Course> studyList;

	
	public Student (String id, String name, String email)
	{
		this.studentID = id;
		this.name = name;
		this.email = email;
	}
}
