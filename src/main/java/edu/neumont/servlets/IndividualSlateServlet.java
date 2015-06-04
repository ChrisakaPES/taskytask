package edu.neumont.servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.neumont.dal.TaskService;
import edu.neumont.dal.TaskServiceImpl;
import edu.neumont.models.SlateViewModel;
import edu.neumont.models.Task;

/**
 * Servlet implementation class IndividualSlateServlet
 */
@WebServlet("/slate/*")
public class IndividualSlateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static TaskService taskDAL;
	private String contextPath;
	
	public static Pattern CREATE_TASK_PATTERN = Pattern.compile("/(\\d+)/create/task");
	public static Pattern DELETE_TASK_PATTERN = Pattern.compile("/(\\d+)/delete/task");
	public static Pattern UPDATE_TASK_PATTERN = Pattern.compile("/(\\d+)/update/task");
       
	@Override
	public void init()
	{
		taskDAL = new TaskServiceImpl();
		contextPath = this.getServletContext().getContextPath();
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
			SlateServlet.logger.debug(tasksInSlate.size()+"tasks for Slate ID: " + slateId);
			
			request.setAttribute("model", svm);
			request.setAttribute("slateId", slateId);
			request.getRequestDispatcher("/WEB-INF/viewSlate.jsp").forward(request, response);
		}else {
			SlateServlet.logger.debug("It didn't work here is the path info: "+ url);
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
			String name = request.getParameter("name");
			LocalDateTime dueDate = LocalDateTime.parse(request.getParameter("dueDate"));
			String description = request.getParameter("description");
			long slateId = Long.parseLong(request.getParameter("slateId"));
			
			taskDAL.create(slateId, name, description, dueDate);
			
			response.sendRedirect(contextPath + "/slate/" + slateId);

			
		}else if((m = DELETE_TASK_PATTERN.matcher(url)).matches())
		{
			long toDeleteId = Long.parseLong(request.getParameter("toBeDeletedId"));
			long slateId = Long.parseLong(m.group(1));
			taskDAL.delete(toDeleteId);
			
			response.sendRedirect(contextPath + "/slate/" + slateId);
		}else if((m = UPDATE_TASK_PATTERN.matcher(url)).matches())
		{
			long slateId = Long.parseLong(request.getParameter("slateId"));
			long taskToUpdateId = Long.parseLong(request.getParameter("taskId"));
			String updatedName = request.getParameter("name");
			LocalDateTime updatedDate = LocalDateTime.parse(request.getParameter("dueDate"));
			String updatedDescription = request.getParameter("description");
			
			Task toBeUpdated = taskDAL.retrieveTask(taskToUpdateId);
			
			
			
		}
	}

}
