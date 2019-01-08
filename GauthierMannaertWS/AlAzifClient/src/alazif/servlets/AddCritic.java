package alazif.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import alazif.business.CriticBusiness;
import alazif.javabean.Novel;
import alazif.javabean.User;

@WebServlet("/AddCritic")
public class AddCritic extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	String novelString;
    public AddCritic() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		novelString=request.getParameter("id");
		getServletContext().getRequestDispatcher("/views\\critic.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sess = request.getSession();
		CriticBusiness cBusi = new CriticBusiness();
		cBusi.setU((User)sess.getAttribute("user"));
		//novelString=request.getParameter("id");
		String commentary = request.getParameter("commentary");
		float rating = Float.parseFloat(request.getParameter("rating"));
		
		int novelIndex;
		try
		{
			novelIndex=Integer.parseInt(novelString);
		}
		catch(NumberFormatException e) {novelIndex=1;}
		
		if(cBusi.addCritic(cBusi.getU().getUserId(), novelIndex, commentary, rating)) {
			response.sendRedirect("/AlAzifClient/novel?id="+novelIndex);
		}
		else {
			request.setAttribute("erreur", cBusi.getErreur());
			getServletContext().getRequestDispatcher("/views\\critic.jsp").forward(request, response);
		}
	}

}
