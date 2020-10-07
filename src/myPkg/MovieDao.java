package myPkg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MovieDao {
		
	private static MovieDao dao = null;
	DataSource ds = null;
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	private MovieDao() {
		try {
			Context context = new InitialContext();
			ds = (DataSource)context.lookup("java:/comp/env/jdbc/OracleDB");
			conn = ds.getConnection();
			 
		} catch (NamingException e) {
			e.printStackTrace();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	} 
	
	public static MovieDao getInstance() {
		if(dao == null) {
			dao = new MovieDao();
		}
		
		return dao;
		
	} //getInstance
			
		public ArrayList<MovieBean> getAllMovie() { //ArrayList를 써도 되고 Vector를 써도 된다.
			
			String sql = "select * from movie order by num";
			ArrayList<MovieBean> lists = new ArrayList<MovieBean>();
			try {
				ps = conn.prepareStatement(sql);
				
				rs = ps.executeQuery();
				
				while(rs.next()) {
					int num = rs.getInt("num");
					String id = rs.getString("id");
					String name = rs.getString("name");
					int age = rs.getInt("age");
					String genre = rs.getString("genre");
					String time = rs.getString("time");
					int partner = rs.getInt("partner");
					String memo = rs.getString("memo");
					
					MovieBean mb = new MovieBean(num,id,name,age,genre,time,partner,memo);
					
					lists.add(mb);
				}			
				
			} catch (SQLException e) {
			
			}finally {
				try {
				
					if(ps != null) {
						ps.close();
					}
					if(rs != null) {
						rs.close();
					}
								
				}catch(SQLException e) {
					
				}
				
			}
			return lists;
		} //getAllMovie
		

		public void insertData(MovieBean mb) {
			
			String sql = "insert into movie values(mv_seq.nextval,?,?,?,?,?,?,?)";
			int cnt=-1;
			try {
				ps = conn.prepareStatement(sql);
				
				ps.setString(1, mb.getId());
				ps.setString(2, mb.getName());
				ps.setInt(3, mb.getAge());
				ps.setString(4, mb.getGenre());
				ps.setString(5, mb.getTime());
				ps.setInt(6, mb.getPartner());
				ps.setString(7, mb.getMemo());
				
				ps.executeUpdate();
						
			} catch (SQLException e) {
				
			}finally {
				try {
					if(ps != null) {
						ps.close();
					}
						
				}catch(SQLException e) {
					
				}
				
			}
			
		} //insertData
		
		public void deleteData(int num) {
			
			
			String sql = "delete from movie where num=?";
			
			try {
				ps = conn.prepareStatement(sql);
				
				ps.setInt(1, num);
				
				ps.executeUpdate();
				
				
			} catch (SQLException e) {
				
			}finally {
				try {
					if(ps != null) {
						ps.close();
					}
						
				}catch(SQLException e) {
					
				}
				
			}
			
		} //deleteData
		
		public void deleteCheckData(String[] str) {
			
			String sql = "delete from movie where num=?";
			for(int i=0;i<str.length-1;i++) {
				sql += " or num=?";
			}
			
			
			try {
				ps = conn.prepareStatement(sql);
				
				for(int i=1;i<=str.length;i++) {
					ps.setInt(i, Integer.parseInt(str[i-1]));
				}
				
				ps.executeUpdate();
				
			} catch (SQLException e) {
				
			}finally {
				try {
					if(ps != null) {
						ps.close();
					}
						
				}catch(SQLException e) {
					
				}
				
			}
			
		} //deleteCheckData
		
		public MovieBean getMovieByNum(int num) {
			
			
			String sql = "select * from movie where num=?";
			MovieBean mb = null;
			try {
				ps = conn.prepareStatement(sql);
				
				ps.setInt(1, num);
				
				rs = ps.executeQuery();
				
				if(rs.next()) {
					
					String id = rs.getString("id");
					String name = rs.getString("name");
					int age = rs.getInt("age");
					String genre = rs.getString("genre");
					String time = rs.getString("time");
					int partner = rs.getInt("partner");
					String memo = rs.getString("memo");
					
					mb = new MovieBean(num,id,name,age,genre,time,partner,memo);
			
				}
				
			} catch (SQLException e) {
				
			}finally {
				try {
			
					if(rs != null) {
						rs.close();
					}
					
					if(ps != null) {
						ps.close();
					}
				}catch(SQLException e) {
					
				}
				
			}
			return mb;	
		}//getMovieByNum
		
		public void updateData(MovieBean mb) {
			
			
			String sql = "update movie set name=?, age=?, genre=?, time=?, partner=?, memo=? where num=?";
			
			try {
				ps = conn.prepareStatement(sql);
				
				
				ps.setString(1, mb.getName());
				ps.setInt(2, mb.getAge());
				ps.setString(3, mb.getGenre());
				ps.setString(4, mb.getTime());
				ps.setInt(5, mb.getPartner());
				ps.setString(6, mb.getMemo());
				ps.setInt(7, mb.getNum());
				
				ps.executeUpdate();
				
			} catch (SQLException e) {
				
			}finally {
				try {
				
					if(ps != null) {
						ps.close();
					}
						
				}catch(SQLException e) {
					
				}
				
			}
			
		}//updateData
		
		public boolean searchId(String userid) {
			
			String sql = "select id from movie where id=?";
			boolean flag = false;
			try {
				ps = conn.prepareStatement(sql);
				
				ps.setString(1, userid);
				
				rs = ps.executeQuery();
				
				while(rs.next()) {
					flag = true; //id가 있는지 없는지만 확인하면 되니까 flag의 상태를 통해 확인한다.
				}
			
			} catch (SQLException e) {
				
			}finally {
				try {
					if(rs != null) {
						rs.close();
					}
					if(ps != null) {
						ps.close();
					}
								
				}catch(SQLException e) {
					
				}
				
			}
			return flag; //이미있으면 true 아직없으면 false   리턴한다.
		} //searchId	

	
		
}
