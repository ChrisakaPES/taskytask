package edu.neumont.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import edu.neumont.dal.SlateDAL;
import edu.neumont.dal.SlateHandler;
import edu.neumont.models.DashboardViewModel;
import edu.neumont.models.Slate;

/**
 * Servlet implementation class SlateServlet
 */
@WebServlet("/dashboard/*")
public class SlateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static SlateDAL sh;
	
	public static Pattern DASHBOARD_PATTERN = Pattern.compile("/(\\d+)");
	public static Pattern CREATE_SLATE_PATTERN = Pattern.compile("/create");
	public static final Logger logger = (Logger) LoggerFactory.getLogger(SlateServlet.class);
    private static String contextPath;
	@Override
	public void init()
	{
		sh = new SlateHandler();
		sh.createSlate("Test Slate 1","This is the description for the first slate item.",LocalDateTime.now().plusHours(24L));
		sh.createSlate("Test Slate 2","This is the description for the second slate item.",LocalDateTime.now().plusHours(48L));
		contextPath = this.getServletContext().getContextPath();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = request.getPathInfo();
		try{
			Matcher m = DASHBOARD_PATTERN.matcher(url);
			if(m.matches())
			{
				DashboardViewModel dvm = new DashboardViewModel();
				//============start dummy data code================
				//This is creation of Dummy Data later on data will need to be queried from map (database once it is finished)
				
				dvm.addSlate(sh.retrieveSlate(1));
				dvm.addSlate(sh.retrieveSlate(2));
				dvm.addSlate(sh.retrieveSlate(3));
				dvm.addSlate(sh.retrieveSlate(4));
				//=============end dummy data code=================
				request.setAttribute("model",dvm);
				request.setAttribute("context", contextPath);
				request.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request,response);
			}
		}catch(Exception e)
		{
			SlateServlet.logger.debug(e.getMessage());
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = request.getPathInfo();
		try{
			Matcher m = CREATE_SLATE_PATTERN.matcher(url);
			if(m.matches())
			{
				String newSlateName = request.getParameter("name");
				String newSlateDescription = request.getParameter("description");
				String newSlateDueDate = request.getParameter("dueDate");
				//setting the date to posting time for new slate eventually we will implement a date 
				//picker type thing.
				if(!sh.createSlate(newSlateName,newSlateDescription,LocalDateTime.now()))
				{
					//Being here means the Slate may not be valid more of an issue later.
					//Don't just throw an exception here only doing so in order to have something here
					throw new Exception("Not Implemented Exception");
				}
				else
				{
					//the id here should probably change.
					response.sendRedirect(contextPath+"/dashboard/1");
				}
				
			}
			
		}catch(Exception e)
		{
			SlateServlet.logger.debug(e.getMessage());
		}
		
	}

}
