package Imple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;





import Bean.cidbean;
import Bean.lawerbean;
import Bean.subpolicebean;
import Dbcon.dbcon;
import Inter.inter;

public class imple implements inter {
	Connection con;
	@Override
	public int adet(cidbean ck) {
		// TODO Auto-generated method stub
		int reg=0;
		 
		 con=dbcon.create();
		 
		 		try {
		 			
		 			PreparedStatement ps=con.prepareStatement("INSERT INTO `forensic`.`criminaldetails` VALUES(id,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		 			ps.setString(1,ck.getName());
		 			ps.setString(2,ck.getDate());
		 			ps.setString(3,ck.getAge());
		 			ps.setString(4,ck.getHeight());
		 			ps.setString(5,ck.getWeight());
		 			ps.setString(6,ck.getGender());
		 			ps.setString(7,ck.getZone());
		 			ps.setString(8,ck.getCaseno());
		 			ps.setString(9,ck.getSection());
		 			ps.setString(10,ck.getCasedetails());
		 			ps.setString(11, ck.getFilename());
		 			ps.setString(12, ck.getFilesize());
		 			ps.setString(13, ck.getFiletype());
		 			ps.setString(14, ck.getFilecontent());
		 			ps.setString(15, ck.getEncrypt());
		 			ps.setString(16, ck.getDecrypt());
		 			ps.setString(17, "Upload");
		 		
		 			reg=ps.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 		
		return reg;
		
		
	}
	@Override
	public int spr(subpolicebean pr) {
		// TODO Auto-generated method stub
		
		
		
		int reg=0;
		 
		 con=dbcon.create();
		 
		 		try {
		 			
		 			PreparedStatement ps=con.prepareStatement("INSERT INTO `forensic`.`subpolice` VALUES(id,?,?,?,?,?,?,?,?)");
		 		
		 			ps.setString(1,pr.getName());
		 			ps.setString(2,pr.getZone());
		 			ps.setString(3,pr.getGender());
		 			ps.setString(4,pr.getNumber());
		 			ps.setString(5,pr.getJoiningdate());
		 			ps.setString(6,pr.getPass());
		 			ps.setString(7,pr.getPic());
		 			ps.setString(8, "Not Activate");
		 			
		 			reg=ps.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 		
		return reg;
	}
	@Override
	public boolean spl(subpolicebean pr) {
		// TODO Auto-generated method stub
		
		boolean log=false;
		 con=dbcon.create();
		
		 try {
				PreparedStatement ps=con.prepareStatement("SELECT * FROM `forensic`.`subpolice` where  zone=? and pass=? and status='Activate' ");
				ps.setString(1, pr.getZone());
				ps.setString(2, pr.getPass());
				
				ResultSet rs=ps.executeQuery();
				log=rs.next();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return log;
	}
	@Override
	public int law(lawerbean pr) {
		int reg=0;
		 
		 con=dbcon.create();
		 
		 		try {
		 			
		 			PreparedStatement ps=con.prepareStatement("INSERT INTO `forensic`.`lawerbean` VALUES(id,?,?,?,?,?,?,?,?)");
		 			ps.setString(1,pr.getName());
		 			ps.setString(2,pr.getEmail());
		 			ps.setString(3,pr.getAddress());
		 			ps.setString(4,pr.getRegid());
		 			ps.setString(5,pr.getAdvocatecode());
		 			ps.setString(6,pr.getPassword());
		 			ps.setString(7,pr.getImage());
		 		
		 			ps.setString(8, "Not Verified");
		 			
		  		
		 			reg=ps.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 		
		return reg;
	}
	@Override
	public boolean log(lawerbean lb) {
		boolean log=false;
		 con=dbcon.create();
		
		 try {
				PreparedStatement ps=con.prepareStatement("SELECT * FROM `forensic`.`lawerbean` where  email=? and pass=? and status=? ");
				ps.setString(1, lb.getEmail());
				ps.setString(2, lb.getPassword());
				ps.setString(3, lb.getStatus());

				ResultSet rs=ps.executeQuery();
				log=rs.next();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return log;
	}
}
