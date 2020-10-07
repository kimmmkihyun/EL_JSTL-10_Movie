package myPkg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MovieDeleteCommand implements MovieCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		MovieDao dao = MovieDao.getInstance();
		
		int num= Integer.parseInt(request.getParameter("num"));
		
		dao.deleteData(num);
	}

}
