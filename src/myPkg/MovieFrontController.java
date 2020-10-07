package myPkg;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MovieFrontController
 */
//@WebServlet("*.mv")
public class MovieFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
		ServletContext context = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieFrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		context = config.getServletContext();
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	
		doProcess(request, response);
	}
	
	public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("doProcess()");
		
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		int len = contextPath.length();
		
		String command = uri.substring(len);
		
		MovieCommand mcommand = null; // MemberCommand의 자식클래스를 관리하기 위해서 변수를 만들어 놓고 자식 객체를 관리한다.
		
		String viewPage = "";
		String flag = (String)context.getAttribute("flag");
		
		if(command.equals("/insertProc.mv")) {
			System.out.println("insertProc 요청");
						
			if(flag.equals("false")) {
				mcommand = new MovieInsertCommand();
				mcommand.execute(request, response);
				
				context.setAttribute("flag", "true");
				
				viewPage = "/list.mv";
			}
			else {
				viewPage = "/list.mv";
			}
			
			
		}
		
		
		else if(command.equals("/list.mv")) {
			System.out.println("list 요청");
			
			mcommand = new MovielistCommand();
			mcommand.execute(request, response);
			
			viewPage = "list.jsp";
			
		}
		
		
		else if(command.equals("/updateForm.mv")) {
			System.out.println("updateForm 요청");
			
			mcommand = new MovieUpdateFormCommand();
			mcommand.execute(request, response);
			
			viewPage = "updateForm.jsp";
			
		}
		
		
		else if(command.equals("/updateProc.mv")) {
			System.out.println("updateProc 요청");
			
			mcommand = new MovieUpdateCommand();
			mcommand.execute(request, response);
			
			viewPage = "/list.mv";
		}
		
		
		else if(command.equals("/delete.mv")) {
			System.out.println("delete 요청");
			
			mcommand = new MovieDeleteCommand();
			mcommand.execute(request, response);
			
			viewPage = "/list.mv";
		}
		else if(command.equals("/deleteAll.mv")) {
			System.out.println("deleteAll.mv 요청");
			
			mcommand = new MovieDeleteAllCommand();
			mcommand.execute(request, response);
			
			viewPage = "/list.mv";
		}
		else if(command.equals("/id_check_proc.mv")) {
			System.out.println("id_check_proc.mv 요청");
			
			mcommand = new MovieIdCheckCommand();
			mcommand.execute(request, response);
			
			return;
			
		}
		
		
		
		RequestDispatcher dis = request.getRequestDispatcher(viewPage);
		dis.forward(request, response);
	}
	
	
	
	
	

}
