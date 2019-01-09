package alazif.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alazif.business.WriterBusiness;

@WebServlet("/AddWriter")
public class AddWriter extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String erreur;
       
    public AddWriter() {
        super();
        erreur="";
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/views\\newWriter.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		erreur="";
		WriterBusiness wBusi=new WriterBusiness();
		String f=request.getParameter("firstname");
		String n=request.getParameter("name");
		String b=request.getParameter("bio");
		
		if(f == null) {
			erreur += "Le prénom est null ,";
		}
		else if(f.isEmpty()) {
			erreur += "Le prénom est vide, ";
		}
		
		if(n == null) {
			erreur += "Le nom est null, ";
		}
		else if(n.isEmpty()) {
			erreur += "Le nom est vide, ";
		}
		
		if(b == null) {
			erreur += "La bio est null, ";
		}
		else if(b.isEmpty()) {
			erreur += "La bio est vide, ";
		}
		
		if(erreur.equals("")&&wBusi.addWriter(f, n, b))
			response.sendRedirect("/AlAzifClient/writerlist");
		else
		{
			request.setAttribute("erreur", erreur);
			getServletContext().getRequestDispatcher("/views\\newWriter.jsp").forward(request, response);
		}
	}

}
