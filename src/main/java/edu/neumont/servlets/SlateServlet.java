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

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import edu.neumont.dal.SlateService;
import edu.neumont.dal.SlateServiceImpl;
import edu.neumont.models.DashboardViewModel;
import edu.neumont.models.Slate;

/**
 * Servlet implementation class SlateServlet
 */
@WebServlet("/dashboard/*")
public class SlateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static SlateService sh;
	
	public static Pattern DASHBOARD_PATTERN = Pattern.compile("/(\\d+)");
	public static Pattern CREATE_SLATE_PATTERN = Pattern.compile("/create");
	public static Pattern DELETE_SLATE_PATTERN = Pattern.compile("/delete");
	public static Pattern UPDATE_SLATE_PATTERN = Pattern.compile("/update");

	public static final Logger logger = (Logger) LoggerFactory.getLogger(SlateServlet.class);
    private static String contextPath;
	@Override
	public void init()
	{
		sh = new SlateServiceImpl();
//		sh.createSlate("Test Slate 1","This is the description for the first slate item.",LocalDateTime.now().plusHours(24L));
//		sh.createSlate("Test Slate 2","This is the description for the second slate item.",LocalDateTime.now().plusHours(48L));
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
				int counter = 0;
				List<Slate> slatesToAdd = sh.retrieveUserSlates(1l);
//				for(int i = 1; i <= 100; i++)
//				{
//					Slate toAdd = sh.retrieveSlate(i);
//					if(counter > 4)
//					{
//						break;
//					}
//					if(toAdd != null)
//					{
//						dvm.addSlate(toAdd);
//						counter++;
//						
//					}
//				}
				dvm.addSlates(slatesToAdd);
				//=============end dummy data code=================
				request.setAttribute("model",dvm);
				request.setAttribute("context", contextPath);
				request.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request,response);
			}else if((m = UPDATE_SLATE_PATTERN.matcher(url)).matches())
			{
				// This get may not be necessary anymore
				int toBeEditedId = Integer.parseInt(m.group(1));
				Slate toBeEdited = sh.retrieveSlate(toBeEditedId);
				
				request.setAttribute("model", toBeEdited);
				request.setAttribute("context",contextPath);
				request.getRequestDispatcher("/WEB-INF/edit-slate.jsp").forward(request, response);
			}
		}catch(NullPointerException e)
		{
			SlateServlet.logger.debug(e.getMessage() + "asldkfjsldjf");
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
				
			}else if((m = UPDATE_SLATE_PATTERN.matcher(url)).matches())
			{
				int updatingSlateId = Integer.parseInt(request.getParameter("slateId"));//possibly add hidden input to form to eliminate need for id in url
				Slate toBeUpdated = sh.retrieveSlate(updatingSlateId);
				
				//Implement more checking here for now it's pretty simple. Also Date need to be implemented
				toBeUpdated.setDescription(request.getParameter("description"));
				toBeUpdated.setDueDate(LocalDateTime.now());
				toBeUpdated.setName(request.getParameter("name"));
				
				sh.updateSlate(updatingSlateId, toBeUpdated);
				
				response.sendRedirect(contextPath+"/dashboard/1");
				
				
			}else if((m = DELETE_SLATE_PATTERN.matcher(url)).matches())
			{
				int deletingSlateId = Integer.parseInt(request.getParameter("toBeDeletedId"));
				
				sh.deleteSlate(deletingSlateId);
				
				response.sendRedirect(contextPath+"/dashboard/1");				
			}
			
		}catch(Exception e)
		{
			SlateServlet.logger.debug(e.getMessage());
		}
		
	}

}
