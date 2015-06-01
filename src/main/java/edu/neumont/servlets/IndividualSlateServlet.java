package edu.neumont.servlets;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.neumont.dal.TaskDAL;
import edu.neumont.models.SlateViewModel;
import edu.neumont.models.Task;

/**
 * Servlet implementation class IndividualSlateServlet
 */
@WebServlet("/slate/*")
public class IndividualSlateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static TaskDAL taskDAL;
	
	public static Pattern CREATE_TASK_PATTERN = Pattern.compile("/create/task");
	public static Pattern DELETE_TASK_PATTERN = Pattern.compile("/delete/task");
	public static Pattern UPDATE_TASK_PATTERN = Pattern.compile("/update/task/(\\d+)");
       
	@Override
	public void init()
	{
		taskDAL = null;
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = request.getPathInfo();
		Matcher m = SlateServlet.DASHBOARD_PATTERN.matcher(url);
		if(m.matches())
		{
			long slateId = Long.parseLong(m.group(1));
			List<Task> tasksInSlate = taskDAL.retrieveTasks(slateId);
			SlateViewModel svm = new SlateViewModel(tasksInSlate);
			
			request.setAttribute("model", svm);
			request.getRequestDispatcher("WEB-INF/viewSlate.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = request.getPathInfo();
		Matcher m = CREATE_TASK_PATTERN.matcher(url);
		if(m.matches())
		{
			
		}else if((m = DELETE_TASK_PATTERN.matcher(url)).matches())
		{
			
		}else if((m = UPDATE_TASK_PATTERN.matcher(url)).matches())
		{
			
		}
	}

}
