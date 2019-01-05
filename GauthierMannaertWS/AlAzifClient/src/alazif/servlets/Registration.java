package alazif.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alazif.business.UserBusiness;

@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Registration() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/views\\registration.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserBusiness uBusi = new UserBusiness();
		uBusi.Inscription(request.getParameter("identifiant"), request.getParameter("motdepasse"), request.getParameter("confmdp"));
		if(uBusi.getErreur().equals("")) {
			getServletContext().getRequestDispatcher("/connection").forward(request, response);
		}
		else {
			request.setAttribute("erreur", uBusi.getErreur());
			getServletContext().getRequestDispatcher("/views\\registration.jsp").forward(request, response);
		}
	}

}
