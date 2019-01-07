package alazif.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alazif.business.CreatureBusiness;
import alazif.javabean.CreatureName;

/**
 * Servlet implementation class WriterList
 */
@WebServlet("/CreatureList")
public class CreatureList extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public CreatureList() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CreatureBusiness cBusi = new CreatureBusiness();
		List<CreatureName> lCre = cBusi.getAllCreatureNames();
		request.setAttribute("list", lCre);
		getServletContext().getRequestDispatcher("/views\\listCreatures.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
