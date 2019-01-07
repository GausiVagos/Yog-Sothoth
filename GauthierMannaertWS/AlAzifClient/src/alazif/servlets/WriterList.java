package alazif.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alazif.business.WriterBusiness;
import alazif.javabean.Writer;

/**
 * Servlet implementation class WriterList
 */
@WebServlet("/WriterList")
public class WriterList extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public WriterList() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WriterBusiness wBusi = new WriterBusiness();
		List<Writer> lWri = wBusi.getListWriter();
		request.setAttribute("list", lWri);
		getServletContext().getRequestDispatcher("/views\\listWriters.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
