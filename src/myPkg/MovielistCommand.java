package myPkg;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MovielistCommand implements MovieCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		MovieDao dao = MovieDao.getInstance();
		ArrayList<MovieBean> lists = dao.getAllMovie();
		
		request.setAttribute("lists", lists);
		
	}

}
