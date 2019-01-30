package org.maddress.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.maddress.config.MybatisManager;
import org.maddress.model.MyAddressDTO;

/**
 * Servlet implementation class ViewController
 */
@WebServlet("/maddr/view.my")
public class ViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		SqlSessionFactory sqlMapper = MybatisManager.getSqlSession();
		SqlSession sqlSess = sqlMapper.openSession(ExecutorType.REUSE);
		int num = Integer.parseInt(request.getParameter("num"));
		MyAddressDTO mdto = sqlSess.selectOne("mViewData", num);
		request.setAttribute("lists", mdto);
		RequestDispatcher rd = request.getRequestDispatcher("mView.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
