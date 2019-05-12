package net.etfbl.ip.projektni.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import net.etfbl.ip.projektni.beans.BlogBean;
import net.etfbl.ip.projektni.beans.FajlBean;
import net.etfbl.ip.projektni.beans.FakultetBean;
import net.etfbl.ip.projektni.beans.ObjavaBean;
import net.etfbl.ip.projektni.beans.UserBean;
import net.etfbl.ip.projektni.beans.VezaBean;
import net.etfbl.ip.projektni.dto.Blog;
import net.etfbl.ip.projektni.dto.Fajl;
import net.etfbl.ip.projektni.dto.Fakultet;
import net.etfbl.ip.projektni.dto.Komentar;
import net.etfbl.ip.projektni.dto.ObjavaKorisnik;
import net.etfbl.ip.projektni.dto.User;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String address = "/WEB-INF/pages/login.jsp";
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		

		session.setAttribute("notification", "");

		if ((action == null || action.equals(""))&& session.getAttribute("userBean")==null) {
			address = "/WEB-INF/pages/login.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(address);
			dispatcher.forward(request, response);
		} 
		else if ((action == null || action.equals(""))&& session.getAttribute("userBean")!=null) {
			address = "/WEB-INF/pages/home.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(address);
			dispatcher.forward(request, response);}
		else if (action.equals("logout")) {
			UserBean userBean = (UserBean) session.getAttribute("userBean");
			if(userBean!=null)
			session.invalidate();
			address = "/WEB-INF/pages/login.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(address);
			dispatcher.forward(request, response);
		} else if (action.equals("login")) {
			if(session.getAttribute("userBean")!=null) {
				address = "/WEB-INF/pages/home.jsp";
			}
			else {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			System.out.println(username + " " + password);
			UserBean userBean = new UserBean();
			if (userBean.login(username, password)) {
				session.invalidate();
				session=request.getSession();
				session.setAttribute("userBean", userBean);
				if(userBean.getUser().getStatus()==1) {
				ObjavaBean objavaBean = new ObjavaBean();
				session.setAttribute("objavaBean", objavaBean);
				FakultetBean fakultetBean = new FakultetBean();
				fakultetBean.setFakultet(userBean.getUser().getFakultet());
				session.setAttribute("fakultetBean", fakultetBean);
				FajlBean fajlBean = new FajlBean();
				session.setAttribute("fajlBean", fajlBean);
				BlogBean blogBean = new BlogBean();
				session.setAttribute("blogBean", blogBean);
				address = "/WEB-INF/pages/home.jsp";}
				else {
					ObjavaBean objavaBean = new ObjavaBean();
				session.setAttribute("objavaBean", objavaBean);
				FakultetBean fakultetBean = new FakultetBean();
				session.setAttribute("fakultetBean", fakultetBean);
				FajlBean fajlBean = new FajlBean();
				session.setAttribute("fajlBean", fajlBean);
				BlogBean blogBean = new BlogBean();
				session.setAttribute("blogBean", blogBean);
				address = "/WEB-INF/pages/profil.jsp";
					
				}
			} else {
				session.setAttribute("notification", "Pogresni parametri za pristup");
			}
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher(address);
			dispatcher.forward(request, response);
		} else if (action.equals("registration")) {
			String username = request.getParameter("username");
			String password = request.getParameter("pass1");
			String password2 = request.getParameter("pass2");
			String email = request.getParameter("email");
			String ime=request.getParameter("firstName");
			String prezime=request.getParameter("lastName");
			UserBean userBean = new UserBean();
			try {
				if (username != null && email!=null && password.equals(password2)) {
					if (userBean.isUserNameAllowed(username) && userBean.isEmailAllowed(email)) {
						User user = new User(0, username, password,ime,
								prezime, email);
						if (userBean.add(user)) {
							if (userBean.login(username, password)) 
								session.invalidate();
								session=request.getSession();
								session.setAttribute("userBean", userBean);
								ObjavaBean objavaBean = new ObjavaBean();
								session.setAttribute("objavaBean", objavaBean);
								FakultetBean fakultetBean = new FakultetBean();
								session.setAttribute("fakultetBean", fakultetBean);
								FajlBean fajlBean = new FajlBean();
								session.setAttribute("fajlBean", fajlBean);
								BlogBean blogBean = new BlogBean();
								session.setAttribute("blogBean", blogBean);
								address = "/WEB-INF/pages/profil.jsp";
							
						}
					} else {
						session.setAttribute("notification", "Username je zauzet");
						address = "/WEB-INF/pages/registration.jsp";
					}
				} else {
					address = "/WEB-INF/pages/registration.jsp";
				}
			} catch (Exception e) {
				session.setAttribute("notification", "ERROR: " + e.getMessage());
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher(address);
			dispatcher.forward(request, response);
		} else if (action.equals("checkRequests")) {
			UserBean userBean = (UserBean) session.getAttribute("userBean");
			if (userBean != null && userBean.isLoggedIn()) {
				int i = userBean.getRequests().size();
				//System.out.println("Velicina" + i);
				try {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter printWriter = response.getWriter();
					printWriter.println(i);
					printWriter.close();

				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("error", "ERROR: " + e.getMessage());
				}
			}
		}
		else if (action.equals("download")) {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			String gurufile = request.getParameter("fajlPutanja");
			String gurupath = getServletContext().getRealPath(File.separator) + File.separator+"files"+ File.separator;
			

			System.out.println( getServletContext().getRealPath(File.separator));
			response.setContentType("APPLICATION/OCTET-STREAM");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ gurufile + "\"");
			FileInputStream fileInputStream = new FileInputStream(gurupath
					+ gurufile);
	 
			int i;
			while ((i = fileInputStream.read()) != -1) {
				out.write(i);
			}
			fileInputStream.close();
			out.close();
			
			
	    }
		else if (action.equals("changePassword")) {
			String old = request.getParameter("old");
			String pwd = request.getParameter("pwd");
			String con = request.getParameter("con");
			String res = "Password nije promijenjen";
			UserBean userBean = (UserBean) session.getAttribute("userBean");
			if (userBean != null && userBean.isLoggedIn()) {
				System.out.println(old + " " + pwd + " " + con);
				if (old != null && pwd != null && con != null && !old.equals(pwd) && pwd.equals(con)
						&& userBean.getUser().getPassword().equals(old)) {
					User updatedUser = userBean.getUser();
					updatedUser.setPassword(pwd);
					if (userBean.update(updatedUser)) {
						System.out.println("Prosao update");
						userBean.setProfileOfUser(updatedUser);
						userBean.setUser(updatedUser);
						session.setAttribute("userBean", userBean);
						res = "Password je promijenjen";
					}
				}
				try {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter printWriter = response.getWriter();
					printWriter.println(res);
					printWriter.close();

				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("error", "ERROR: " + e.getMessage());
				}
			}
		} else {
			UserBean userBean = (UserBean) session.getAttribute("userBean");
			if (userBean == null || !userBean.isLoggedIn())
				address = "/WEB-INF/pages/login.jsp";
			else {
				if (action.equals("home")) {

					address = "/WEB-INF/pages/home.jsp";
				} else if (action.equals("users")) {
					address = "/WEB-INF/pages/users.jsp";
				} else if (action.equals("requests")) {
					address = "/WEB-INF/pages/requests.jsp";
				}
				else if (action.equals("reloadObjave")) {
					address = "/WEB-INF/pages/objRld.jsp";
				}else if (action.equals("profile")) {
					int id = Integer.parseInt(request.getParameter("id"));
					userBean.setProfileOfUser(userBean.getByID(id));
					session.setAttribute("userBean", userBean);
					address = "/WEB-INF/pages/profil.jsp";
				} else if (action.equals("like")) {
					System.out.println(action);
					int id = Integer.parseInt(request.getParameter("id"));
					int userID = Integer.parseInt(request.getParameter("userID"));
					ObjavaBean objavaBean = (ObjavaBean) session.getAttribute("objavaBean");
					if (request.getParameter("id") != null && request.getParameter("userID") != null) {
						try {
							objavaBean.like(id, userID);
							address = "/WEB-INF/pages/home.jsp";
						} catch (Exception e) {
							request.setAttribute("error", "ERROR: " + e.getMessage());
						}
					}

				} else if (action.equals("undislike")) {
					System.out.println(action);
					int id = Integer.parseInt(request.getParameter("id"));
					int userID = Integer.parseInt(request.getParameter("userID"));
					ObjavaBean objavaBean = (ObjavaBean) session.getAttribute("objavaBean");
					if (request.getParameter("id") != null && request.getParameter("userID") != null) {
						try {
							objavaBean.undislike(id, userID);
							address = "/WEB-INF/pages/home.jsp";
						} catch (Exception e) {
							request.setAttribute("error", "ERROR: " + e.getMessage());
						}
					}

				} else if (action.equals("dislike")) {
					System.out.println(action);
					int id = Integer.parseInt(request.getParameter("id"));
					int userID = Integer.parseInt(request.getParameter("userID"));
					ObjavaBean objavaBean = (ObjavaBean) session.getAttribute("objavaBean");
					if (request.getParameter("id") != null && request.getParameter("userID") != null) {
						try {
							objavaBean.dislike(id, userID);
							address = "/WEB-INF/pages/home.jsp";
						} catch (Exception e) {
							request.setAttribute("error", "ERROR: " + e.getMessage());
						}
					}

				} else if (action.equals("unlike")) {
					System.out.println(action);
					int id = Integer.parseInt(request.getParameter("id"));
					int userID = Integer.parseInt(request.getParameter("userID"));
					ObjavaBean objavaBean = (ObjavaBean) session.getAttribute("objavaBean");
					if (request.getParameter("id") != null && request.getParameter("userID") != null) {
						try {
							objavaBean.unlike(id, userID);
							address = "/WEB-INF/pages/home.jsp";
						} catch (Exception e) {
							request.setAttribute("error", "ERROR: " + e.getMessage());
						}
					}

				}

				else if (action.equals("upload")) {
					String name = "";
					String opis = request.getParameter("opis");
					int userID = Integer.parseInt(request.getParameter("userID"));

					if (ServletFileUpload.isMultipartContent(request)) {
						try {
							List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory())
									.parseRequest(request);
							for (FileItem item : multiparts) {
								if (!item.isFormField()) {
									name = new File(item.getName()).getName();
									item.write(new File(getServletContext().getRealPath(File.separator) + File.separator
											+ "files" + File.separator + name));
								}
							}
							FajlBean fajlBean = (FajlBean) session.getAttribute("fajlBean");
							fajlBean.insert(new Fajl(userID, opis, name));
							// File uploaded successfully
							request.setAttribute("gurumessage", "File Uploaded Successfully");
						} catch (Exception ex) {
							request.setAttribute("gurumessage",
									"File Upload Failed due to " + ex.getClass().getSimpleName());
						}

					} else {

						request.setAttribute("gurumessage", "No File found");
					}
					address = "/WEB-INF/pages/home.jsp";
				} else if (action.equals("uploadData")) {
					System.out.println("Stigo zahtijev");
					String name = userBean.getUser().getSlika();
					String username = request.getParameter("username");
					String ime = request.getParameter("ime");
					String prezime = request.getParameter("prezime");
					String email = request.getParameter("email");
					String studprogram = request.getParameter("studprogram");
					int godina = Integer.parseInt(request.getParameter("godina"));
					String interesovanje = request.getParameter("interesovanje");
					String fakultet = request.getParameter("fakultet");

					User updatedUser = new User();

					if (!username.equals("") && !email.equals("") && email.matches("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$") && !ime.equals("") && !prezime.equals("") && !studprogram.equals("")
							&& interesovanje != null && !fakultet.equals("")) {

						if ((username.equals(userBean.getUser().getUsername()) || (!username.equals(userBean.getUser().getUsername()) && userBean.isUserNameAllowed(username)))
								&&(email.equals(userBean.getUser().getEmail()) || (!email.equals(userBean.getUser().getEmail()) && userBean.isEmailAllowed(email)))) {
							if (ServletFileUpload.isMultipartContent(request)) {
								try {
									List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory())
											.parseRequest(request);
									for (FileItem item : multiparts) {
										if (!item.isFormField() && !item.getName().equals("")) {
											name = new File(item.getName()).getName();
											name = userBean.getUser().getUsername() + "_" + name;
											File f = new File(getServletContext().getRealPath(File.separator)
													+ File.separator + "images" + File.separator + name);
											if (f.exists())
												f.delete();

											item.write(new File(getServletContext().getRealPath(File.separator)
													+ File.separator + "images" + File.separator + name));
											
											File fileSource=new File(getServletContext().getRealPath(File.separator)
													+ File.separator + "images" + File.separator + name);
											
											String adminProjectPath=getServletContext().getRealPath(File.separator).replaceAll("ProjektniGA", "ProjektniAdmin")+File.separator+"resources"+ File.separator + "images" + File.separator + name;
											File fileTarget=new File(adminProjectPath);
											Files.copy(fileSource.toPath(), fileTarget.toPath(),StandardCopyOption.REPLACE_EXISTING);
										}
									}
									// File uploaded successfully
									//request.setAttribute("gurumessage", "File Uploaded Successfully");
								} catch (Exception ex) {
									request.setAttribute("gurumessage",
											"File Upload Failed due to " + ex.getClass().getSimpleName());
								}

							} else {

								request.setAttribute("gurumessage", "No File found");
							}

							FakultetBean fb = new FakultetBean();
							updatedUser = new User(userBean.getUser().getId(), username,
									userBean.getUser().getPassword(), ime, prezime, email, studprogram, godina, name,
									interesovanje, fb.getByName(fakultet), 1);
							if (userBean.update(updatedUser)) {
								System.out.println("Prosao update");
								userBean.setProfileOfUser(updatedUser);
								userBean.setUser(updatedUser);
								session.setAttribute("userBean", userBean);
								FakultetBean fakultetBean = new FakultetBean();
								fakultetBean.setFakultet(userBean.getUser().getFakultet());
								session.setAttribute("fakultetBean", fakultetBean);
								request.setAttribute("gurumessage", "Promijenje su izvrsene");
							}
						} else {
							request.setAttribute("gurumessage", "Korisnicko ime ili email nisu u redu");
						}
					}
					else {
						if(email.matches("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$"))
						request.setAttribute("gurumessage", "Svi podaci nisu unijeti");
						else {
							request.setAttribute("gurumessage", "E-mail nije ogovarajuci");
						}
					}
					address = "/WEB-INF/pages/profil.jsp";
				} else if (action.equals("updateBlog")) {
					String komentar = request.getParameter("komentar");
					String blogID = request.getParameter("blogID");
					int userID = Integer.parseInt(request.getParameter("userID"));

					BlogBean blogBean = (BlogBean) session.getAttribute("blogBean");

					try {
						blogBean.updateBlog(blogID, new Komentar(userID, komentar));
						address = "/WEB-INF/pages/home.jsp";
					} catch (Exception e) {
						request.setAttribute("error", "ERROR: " + e.getMessage());
					}
				}

				else if (action.equals("insertTema")) {
					String tema = request.getParameter("tema");
					int userID = Integer.parseInt(request.getParameter("userID"));

					BlogBean blogBean = (BlogBean) session.getAttribute("blogBean");

					try {
						Blog bl = new Blog();
						bl.setUserID(userID);
						bl.setTema(tema);
						bl.setVrijemeKreiranja(new Date());
						blogBean.insertBlog(bl);

						address = "/WEB-INF/pages/home.jsp";
					} catch (Exception e) {
						request.setAttribute("error", "ERROR: " + e.getMessage());
					}
				} else if (action.equals("checkURL")) {
					String url = request.getParameter("url");
					int i = 404;
					try {
						HttpURLConnection.setFollowRedirects(false);
						// note : you may also need
						HttpURLConnection con;
						if (url.startsWith("http://") || url.startsWith("https://")) {
							con = (HttpURLConnection) new URL(url).openConnection();
						} else {
							con = (HttpURLConnection) new URL("https://" + url).openConnection();
						}
						con.setRequestMethod("HEAD");
						i = con.getResponseCode();
					} catch (Exception e) {

						e.printStackTrace();
					}
					System.out.println("STATUS" + i);

					response.setStatus(i);
					address = "/WEB-INF/pages/home.jsp";

				} else if (action.equals("insertObjava")) {
					String sadrzaj = request.getParameter("sadrzaj");
					int userID = Integer.parseInt(request.getParameter("userID"));
					int tip = Integer.parseInt(request.getParameter("tip"));
					ObjavaBean objavaBean = (ObjavaBean) session.getAttribute("objavaBean");
					try {
						if (tip == 1 || tip == 3) {
							ObjavaKorisnik objavaKorisnik = new ObjavaKorisnik();
							objavaKorisnik.setSadrzaj(sadrzaj);
							objavaKorisnik.setUser(userBean.getByID(userID));
							objavaKorisnik.setTip(tip);
							objavaBean.insertObjavaKorisnik(objavaKorisnik);
						} else {
							HttpURLConnection.setFollowRedirects(false);
							// note : you may also need
							HttpURLConnection con;
							if (sadrzaj.startsWith("http://") || sadrzaj.startsWith("https://")) {
								con = (HttpURLConnection) new URL(sadrzaj).openConnection();
							} else {
								con = (HttpURLConnection) new URL("https://" + sadrzaj).openConnection();
							}
							con.setRequestMethod("HEAD");
							int rc = con.getResponseCode();
							if (rc == 200) {
								ObjavaKorisnik objavaKorisnik = new ObjavaKorisnik();
								if (sadrzaj.startsWith("http://") || sadrzaj.startsWith("https://"))
									objavaKorisnik.setSadrzaj(sadrzaj);
								else
									objavaKorisnik.setSadrzaj("https://" + sadrzaj);
								objavaKorisnik.setUser(userBean.getByID(userID));
								objavaKorisnik.setTip(tip);
								objavaBean.insertObjavaKorisnik(objavaKorisnik);
							} else {
								ObjavaKorisnik objavaKorisnik = new ObjavaKorisnik();
								objavaKorisnik.setSadrzaj(sadrzaj);
								objavaKorisnik.setUser(userBean.getByID(userID));
								objavaKorisnik.setTip(3);
								objavaBean.insertObjavaKorisnik(objavaKorisnik);
							}
						}
						address = "/WEB-INF/pages/home.jsp";
					} catch (Exception e) {
						request.setAttribute("error", "ERROR: " + e.getMessage());
					}
				} else if (action.equals("deleteFreind")) {
					int freindID = Integer.parseInt(request.getParameter("freindID"));
					VezaBean vezaBean = new VezaBean();
					try {
						vezaBean.deleteFreind(userBean.getUser().getId(), freindID);
					} catch (Exception e) {
						request.setAttribute("error", "ERROR: " + e.getMessage());
					}

				} else if (action.equals("addFreind")) {
					int freindID = Integer.parseInt(request.getParameter("freindID"));
					VezaBean vezaBean = new VezaBean();
					try {
						vezaBean.addFreind(userBean.getUser().getId(), freindID);
					} catch (Exception e) {
						request.setAttribute("error", "ERROR: " + e.getMessage());
					}

				} else if (action.equals("acceptFreind")) {
					int freindID = Integer.parseInt(request.getParameter("freindID"));
					VezaBean vezaBean = new VezaBean();
					try {
						vezaBean.acceptFreind(userBean.getUser().getId(), freindID);
					} catch (Exception e) {
						e.printStackTrace();
						request.setAttribute("error", "ERROR: " + e.getMessage());
					}

				} else if (action.equals("rejectFreind")) {
					int freindID = Integer.parseInt(request.getParameter("freindID"));
					VezaBean vezaBean = new VezaBean();
					try {
						vezaBean.rejectFreind(userBean.getUser().getId(), freindID);
					} catch (Exception e) {
						e.printStackTrace();
						request.setAttribute("error", "ERROR: " + e.getMessage());
					}

				} else if (action.equals("fakultet")) {
					String fakultetNaziv = request.getParameter("fakultetNaziv");
					System.out.println(fakultetNaziv);
					FakultetBean fakultetBean = (FakultetBean) session.getAttribute("fakultetBean");
					try {
						ArrayList<Fakultet> fakulteti = fakultetBean.getAllFakulteti();
						for (Fakultet f : fakulteti) {
							if (f.getNaziv().equals(fakultetNaziv)) {
								fakultetBean.setFakultet(f);
								session.setAttribute("fakultetBean", fakultetBean);
							}
						}
						address = "/WEB-INF/pages/users.jsp";
					} catch (Exception e) {
						request.setAttribute("error", "ERROR: " + e.getMessage());
					}

				}
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher(address);
			dispatcher.forward(request, response);
		}
		// request.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request,
		// response);

		/*
		 * else { UserBean userBean = (UserBean) session.getAttribute("userBean"); if
		 * (userBean == null || !userBean.isLoggedIn()) { address =
		 * "/WEB-INF/pages/login.jsp"; } else { if (action.equals("messages")) { address
		 * = "/WEB-INF/pages/messages.jsp"; } else if (action.equals("newMessage")) {
		 * address = "/WEB-INF/pages/new_message.jsp"; MessageBean messageBean =
		 * (MessageBean) session.getAttribute("messageBean"); if
		 * (request.getParameter("submit") != null && request.getParameter("text") !=
		 * null) { try { String userInfo = userBean.getUser().getLastName() + " " +
		 * userBean.getUser().getFirstName() + " (" + userBean.getUser().getUsername() +
		 * ")"; String date = new SimpleDateFormat("dd.MM.yyyy. HH:mm").format(new
		 * Date()); Message book = new Message(request.getParameter("text"), userInfo,
		 * date, request.getRemoteAddr(), 0); if (messageBean.add(book)) address =
		 * "/WEB-INF/pages/messages.jsp"; } catch (Exception e) {
		 * session.setAttribute("notification", "ERROR: " + e.getMessage()); } }
		 * 
		 * } else { address = "/WEB-INF/pages/404.jsp"; } }
		 * 
		 * }
		 */

		/*
		 * RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		 * dispatcher.forward(request, response);
		 */
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
