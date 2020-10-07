package myPkg;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MovieIdCheckCommand implements MovieCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		PrintWriter pw = null;
		try {
			 pw = response.getWriter();  //여기서는 브라우저에 출력이 아니라 MovieIdCheckCommand를 요청한 ajax로 값을 넘긴다
			  
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		String userid = request.getParameter("userid");
		System.out.println("IdCheckCommand userid : " + userid);
		
		MovieDao dao = MovieDao.getInstance();
		boolean isCheck = dao.searchId(userid);
		String str = "";
		
		if(isCheck) {   //이미 사용중
			str = "NO";
			pw.write(str);
			
		}
		
		else {     //사용 가능
			str = "YES";
			pw.write(str);
			
			
		}
		
	}

}
