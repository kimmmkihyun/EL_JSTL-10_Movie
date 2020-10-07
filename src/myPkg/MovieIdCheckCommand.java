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
			 pw = response.getWriter();  //���⼭�� �������� ����� �ƴ϶� MovieIdCheckCommand�� ��û�� ajax�� ���� �ѱ��
			  
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		String userid = request.getParameter("userid");
		System.out.println("IdCheckCommand userid : " + userid);
		
		MovieDao dao = MovieDao.getInstance();
		boolean isCheck = dao.searchId(userid);
		String str = "";
		
		if(isCheck) {   //�̹� �����
			str = "NO";
			pw.write(str);
			
		}
		
		else {     //��� ����
			str = "YES";
			pw.write(str);
			
			
		}
		
	}

}
