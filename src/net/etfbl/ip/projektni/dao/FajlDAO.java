package net.etfbl.ip.projektni.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.etfbl.ip.projektni.dto.Fajl;
import net.etfbl.ip.projektni.dto.User;


public class FajlDAO {
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_INSERT = "INSERT INTO fajl (opis,User_idUser,Putanja) VALUES (?,?,?)";
	private static final String SQL_SELECT_ALL = "SELECT * FROM fajl";
	
	public static boolean insert(Fajl fajl) {
		boolean result = false;
		Connection connection = null;
		Object values[] = {fajl.getOpis(), fajl.getUserID(), fajl.getPutanja() };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_INSERT, true, values);
			pstmt.executeUpdate();
			if(pstmt.getUpdateCount()>0) {
				result = true;
			}

			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}
	
	public static ArrayList<Fajl> selectAll(){
		ArrayList<Fajl> retVal = new ArrayList<Fajl>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,
					SQL_SELECT_ALL, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()){
				retVal.add(new Fajl(rs.getInt("User_idUser"),rs.getString("opis"),rs.getString("Putanja")));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return retVal;
	}

}
