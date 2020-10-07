package myPkg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MovieDeleteAllCommand implements MovieCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String[] str = request.getParameterValues("rowcheck");
		for(int i=0;i<str.length;i++) {
			System.out.println(str[i] + " ");
		}
		
		MovieDao dao = MovieDao.getInstance();
		dao.deleteCheckData(str);
		
		
		
	}

}
