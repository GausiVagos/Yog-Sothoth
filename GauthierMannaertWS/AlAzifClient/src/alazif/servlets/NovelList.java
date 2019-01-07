package alazif.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alazif.business.NovelBusiness;
import alazif.javabean.Novel;

/**
 * Servlet implementation class WriterList
 */
@WebServlet("/NovelList")
public class NovelList extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public NovelList() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NovelBusiness nBusi = new NovelBusiness();
		List<Novel> lNov = nBusi.getListNovel();
		request.setAttribute("list", lNov);
		getServletContext().getRequestDispatcher("/views\\listNovels.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
