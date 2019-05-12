package net.etfbl.ip.projektni.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import net.etfbl.ip.projektni.dto.User;

import net.etfbl.ip.projektni.dto.Fakultet;
import net.etfbl.ip.projektni.dto.ObjavaKorisnik;

public class UserDAO {
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT_BY_USERNAME_AND_PASSWORD = "SELECT * FROM user join fakultet on user.idFakultet=fakultet.idFakultet where BINARY username=? AND BINARY password=?";
	private static final String SQL_SELECT_BY_USERNAME_AND_PASSWORD_SIMPLE = "SELECT * FROM user where BINARY username=? AND BINARY password=?";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM user join fakultet on user.idFakultet=fakultet.idFakultet where user.idUser=?";
	private static final String SQL_IS_USERNAME_USED = "SELECT * FROM user WHERE BINARY username = ?";
	private static final String SQL_IS_EMAIL_USED = "SELECT * FROM user WHERE BINARY email = ?";
	private static final String SQL_INSERT = "INSERT INTO user (username, password, Ime, Prezime, email) VALUES (?,?,?,?,?)";
	private static final String SQL_GET_FREINDS1 = "Select * from (SELECT idUser1 FROM projektni.veza join user on veza.idUser=user.idUser where veza.idUser=? && veza.Status=1) as  usID join user on user.idUser=usID.idUser1 join fakultet on user.idFakultet=fakultet.idFakultet";
	private static final String SQL_GET_FREINDS2 = "Select * from (SELECT veza.idUser FROM projektni.veza join user on veza.idUser1=user.idUser where veza.idUser1=? && veza.Status=1) as  usID join user on user.idUser=usID.idUser join fakultet on user.idFakultet=fakultet.idFakultet";
	private static final String SQL_GET_FREINDS3 = "Select * from (SELECT idUser1 FROM projektni.veza join user on veza.idUser=user.idUser where veza.idUser=? && (veza.Status=1|| veza.Status=2)) as  usID join user on user.idUser=usID.idUser1 join fakultet on user.idFakultet=fakultet.idFakultet";
	private static final String SQL_GET_FREINDS4 = "Select * from (SELECT veza.idUser FROM projektni.veza join user on veza.idUser1=user.idUser where veza.idUser1=? && (veza.Status=1|| veza.Status=2)) as  usID join user on user.idUser=usID.idUser join fakultet on user.idFakultet=fakultet.idFakultet";
	private static final String SQL_GET_USERS = "SELECT * FROM user join fakultet on user.idFakultet=fakultet.idFakultet where Status=1";
	private static final String SQL_GET_REQUESTS = "Select * from (SELECT veza.idUser FROM projektni.veza join user on veza.idUser1=user.idUser where veza.idUser1=? && veza.Status=2) as  usID join user on user.idUser=usID.idUser join fakultet on user.idFakultet=fakultet.idFakultet";
	private static final String SQL_UPDATE_USER = "UPDATE user set username=?,password=?,email=?,Ime=?,Prezime=?,StudijskiProgram=?,GodinaStudija=?,Slika=?,Interesovanje=?,idFakultet=?,Status=? WHERE idUser=?";
	private static final String SQL_LOG_USER="INSERT into usersperhour (Time,User_idUser) VALUES (now(),?)";
	public static User selectByUsernameAndPassword(String username, String password) {
		User user = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = { username, password };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_BY_USERNAME_AND_PASSWORD, false,
					values);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new User(rs.getInt("idUser"), rs.getString("username"), rs.getString("password"),
						rs.getString("Ime"), rs.getString("Prezime"), rs.getString("email"),
						rs.getString("StudijskiProgram"), rs.getInt("GodinaStudija"), rs.getString("Slika"),
						rs.getString("Interesovanje"), new Fakultet(rs.getInt("idFakultet"), rs.getString("Naziv")),
						rs.getInt("Status"));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return user;
	}
	
	public static User selectByUsernameAndPasswordSimple(String username, String password) {
		User user = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = { username, password };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_BY_USERNAME_AND_PASSWORD_SIMPLE, false,
					values);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new User(rs.getInt("idUser"), rs.getString("username"), rs.getString("password"),
						rs.getString("Ime"), rs.getString("Prezime"), rs.getString("email"));
						user.setSlika(rs.getString("Slika"));
						user.setStatus(rs.getInt("Status"));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return user;
	}

	public static User selectById(int id) {
		User user = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = { id };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_BY_ID, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = new User(rs.getInt("idUser"), rs.getString("username"), rs.getString("password"),
						rs.getString("Ime"), rs.getString("Prezime"), rs.getString("email"),
						rs.getString("StudijskiProgram"), rs.getInt("GodinaStudija"), rs.getString("Slika"),
						rs.getString("Interesovanje"), new Fakultet(rs.getInt("idFakultet"), rs.getString("Naziv")),
						rs.getInt("Status"));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return user;
	}

	public static boolean isUserNameUsed(String username) {
		boolean result = true;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = { username };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_IS_USERNAME_USED, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = false;
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}
	public static boolean isEmailUsed(String email) {
		boolean result = true;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = { email };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,  SQL_IS_EMAIL_USED, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = false;
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}

	public static boolean insert(User user) {
		boolean result = false;
		Connection connection = null;
		ResultSet generatedKeys = null;
		Object values[] = { user.getUsername(), user.getPassword(), user.getIme(), user.getPrezime(),user.getEmail() };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_INSERT, true, values);
			pstmt.executeUpdate();
			generatedKeys = pstmt.getGeneratedKeys();
			if (pstmt.getUpdateCount() > 0) {
				result = true;
			}
			if (generatedKeys.next())
				user.setId(generatedKeys.getInt(1));
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return result;
	}
	
	public static boolean logUser(int idUser) {
		boolean result = false;
		Connection connection = null;
		
		Object values[] = {idUser};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_LOG_USER, true, values);
			pstmt.executeUpdate();
			if (pstmt.getUpdateCount() > 0) {
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


	public static ArrayList<User> getFreinds(int id) {
		ArrayList<User> retVal = new ArrayList<>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = { id };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_GET_FREINDS1, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				retVal.add(new User(rs.getInt("idUser"), rs.getString("username"), rs.getString("password"),
						rs.getString("Ime"), rs.getString("Prezime"), rs.getString("email"),
						rs.getString("StudijskiProgram"), rs.getInt("GodinaStudija"), rs.getString("Slika"),
						rs.getString("Interesovanje"),new Fakultet(rs.getInt("idFakultet"), rs.getString("Naziv")), rs.getInt("Status")));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_GET_FREINDS2, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				retVal.add(new User(rs.getInt("idUser"), rs.getString("username"), rs.getString("password"),
						rs.getString("Ime"), rs.getString("Prezime"), rs.getString("email"),
						rs.getString("StudijskiProgram"), rs.getInt("GodinaStudija"), rs.getString("Slika"),
						rs.getString("Interesovanje"), new Fakultet(rs.getInt("idFakultet"), rs.getString("Naziv")), rs.getInt("Status")));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return retVal;

	}

	public static ArrayList<User> getFreindsAndRequests(int id) {
		ArrayList<User> retVal = new ArrayList<>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = { id };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_GET_FREINDS3, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				retVal.add(new User(rs.getInt("idUser"), rs.getString("username"), rs.getString("password"),
						rs.getString("Ime"), rs.getString("Prezime"), rs.getString("email"),
						rs.getString("StudijskiProgram"), rs.getInt("GodinaStudija"), rs.getString("Slika"),
						rs.getString("Interesovanje"), new Fakultet(rs.getInt("idFakultet"), rs.getString("Naziv")), rs.getInt("Status")));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_GET_FREINDS4, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				retVal.add(new User(rs.getInt("idUser"), rs.getString("username"), rs.getString("password"),
						rs.getString("Ime"), rs.getString("Prezime"), rs.getString("email"),
						rs.getString("StudijskiProgram"), rs.getInt("GodinaStudija"), rs.getString("Slika"),
						rs.getString("Interesovanje"), new Fakultet(rs.getInt("idFakultet"), rs.getString("Naziv")), rs.getInt("Status")));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return retVal;

	}

	public static ArrayList<User> getAllUsers() {
		ArrayList<User> retVal = new ArrayList<>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_GET_USERS, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				retVal.add(new User(rs.getInt("idUser"), rs.getString("username"), rs.getString("password"),
						rs.getString("Ime"), rs.getString("Prezime"), rs.getString("email"),
						rs.getString("StudijskiProgram"), rs.getInt("GodinaStudija"), rs.getString("Slika"),
						rs.getString("Interesovanje"), new Fakultet(rs.getInt("idFakultet"), rs.getString("Naziv")), rs.getInt("Status")));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}

		return retVal;

	}

	public static ArrayList<User> getRequests(int id) {
		ArrayList<User> retVal = new ArrayList<>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {id};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,  SQL_GET_REQUESTS, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				retVal.add(new User(rs.getInt("idUser"), rs.getString("username"), rs.getString("password"),
						rs.getString("Ime"), rs.getString("Prezime"), rs.getString("email"),
						rs.getString("StudijskiProgram"), rs.getInt("GodinaStudija"), rs.getString("Slika"),
						rs.getString("Interesovanje"), new Fakultet(rs.getInt("idFakultet"), rs.getString("Naziv")), rs.getInt("Status")));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}

		return retVal;


	}
	
	public static ArrayList<User> getUnconnectedUsers(int id) {
		ArrayList<User> users = getAllUsers();
		ArrayList<User> connUsers = getFreindsAndRequests(id);

		users.removeAll(connUsers);
		users.remove(selectById(id));
		return users;

	}
	
	public static boolean updateUser(User user) {
		boolean retVal = false;
		Connection connection = null;
		Object values[] = {user.getUsername(),user.getPassword(),user.getEmail(),user.getIme(),user.getPrezime(),user.getStudijskiProgram(),
							user.getGodinaStudija(),user.getSlika(),user.getInteresovaanje(),user.getFakultet().getId(),user.getStatus(),user.getId()};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_UPDATE_USER, false,
					values);
			int affectedRows = pstmt.executeUpdate();
			if (affectedRows == 0)
				retVal = false;
			else
				retVal = true;
			pstmt.close();
		} catch (SQLException e) {
			retVal = false;
		} finally {
			connectionPool.checkIn(connection);
		}
		return retVal;
	}
	
	

}
