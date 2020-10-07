package myPkg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MovieUpdateCommand implements MovieCommand{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		MovieDao dao = MovieDao.getInstance();
		
		int num =Integer.parseInt(request.getParameter("num"));
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String[] genre_imsi = request.getParameterValues("genre");
		String genre="";
		if(genre_imsi == null) {
			genre = "선택한 장르가 없습니다.";
		}
		else {
			for(int i=0;i<genre_imsi.length;i++) {
				genre += genre_imsi[i];
					if(i != genre_imsi.length-1) {
						genre += ",";
					}
			}
		}
		
		String time = request.getParameter("time");
		int partner = Integer.parseInt(request.getParameter("partner"));
		String memo = request.getParameter("memo");
		
		
		MovieBean mb = new MovieBean(num,id,name,age,genre,time,partner,memo);
		dao.updateData(mb);
		
	}

}
