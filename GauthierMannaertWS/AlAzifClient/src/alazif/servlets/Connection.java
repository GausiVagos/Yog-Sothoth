package alazif.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import alazif.business.UserBusiness;

@WebServlet("/Connection")
public class Connection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Connection() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null)
			getServletContext().getRequestDispatcher("/views\\connection.jsp").forward(request, response);
		else
			getServletContext().getRequestDispatcher("/views\\home.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserBusiness uBusi = new UserBusiness();
		uBusi.Connexion(request.getParameter("identifiant"), request.getParameter("motdepasse"));
		if(uBusi.getErreur().equals("")) {
			HttpSession sess = request.getSession();
			sess.setAttribute("user", uBusi.getU());
			getServletContext().getRequestDispatcher("/views\\home.jsp").forward(request, response);
		}
		else {
			request.setAttribute("erreur", uBusi.getErreur());
			getServletContext().getRequestDispatcher("/views\\connection.jsp").forward(request, response);
		}
		
	}

}
