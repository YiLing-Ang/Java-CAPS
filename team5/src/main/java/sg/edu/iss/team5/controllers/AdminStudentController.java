package sg.edu.iss.team5.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import sg.edu.iss.team5.exception.StudentNotFound;
import sg.edu.iss.team5.model.Student;
import sg.edu.iss.team5.services.StudentService;


@Controller
@RequestMapping(value="/admin/students")
//@SessionAttributes(value = {"usession"}, types = {UserSession.class})
public class AdminStudentController {

	@Autowired
	private StudentService sService;
//	
//	@Autowired
//	private UserValidator uValidator;
//
//	@InitBinder("user")
//	private void initUserBinder(WebDataBinder binder) {
//		binder.addValidators(uValidator);
	

	/**
	 * COURSE CRUD OPERATIONS
	 * 
	 * @return
	 */

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView newStudentPage() {
		ModelAndView mav = new ModelAndView("student-new", "student", new Student());
		ArrayList<Student> sList = sService.findAllStudents();
		mav.addObject("slist", sList);
		return mav;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ModelAndView createNewStudent(@ModelAttribute @Valid Student student, BindingResult result) {

		if (result.hasErrors())
			return new ModelAndView("student-new");

		ModelAndView mav = new ModelAndView();
		String message = "New student " + student.getStudentID() + " was successfully created.";
		System.out.println(message);
		sService.createStudent(student);
		mav.setViewName("forward:/admin/students/list");
		return mav;
	}

	@RequestMapping(value = "/list")
	public ModelAndView listStudentPage() {
		ModelAndView mav = new ModelAndView("student-list");
		ArrayList<Student> sList = sService.findAllStudents();
		mav.addObject("slist", sList);
		return mav;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editStudentPage(@PathVariable String id) {
		ModelAndView mav = new ModelAndView("student-edit");
		Student student = sService.findStudent(id);
		mav.addObject("student", student);
		ArrayList<Student> sList = sService.findAllStudents();
		mav.addObject("slist", sList);
		return mav;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public ModelAndView editEmployee(@ModelAttribute @Valid Student student, BindingResult result,
			@PathVariable String id) throws StudentNotFound {

		if (result.hasErrors())
			return new ModelAndView("student-edit");

		ModelAndView mav = new ModelAndView("forward:/admin/students/list");
		String message = "Student was successfully updated.";
		System.out.println(message);
		sService.changeStudent(student);
		return mav;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteStudent(@PathVariable String id)
			throws StudentNotFound {

		ModelAndView mav = new ModelAndView("forward:/admin/students/list");
		Student student = sService.findStudent(id);
		sService.removeStudent(student);
		String message = "The student " + student.getStudentID() + " was successfully deleted.";
		System.out.println(message);
		return mav;
	}
}
