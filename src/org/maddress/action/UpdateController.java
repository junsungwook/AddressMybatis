package org.maddress.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.maddress.config.MybatisManager;
import org.maddress.model.MyAddressDTO;

/**
 * Servlet implementation class UpdateController
 */
@WebServlet("/maddr/update.my")
public class UpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		SqlSessionFactory sqlMapper = MybatisManager.getSqlSession();
		SqlSession sqlSess = sqlMapper.openSession();
		MyAddressDTO mdto = new MyAddressDTO();
		mdto.setNum(Integer.parseInt(request.getParameter("num")));
		mdto.setAddr(request.getParameter("addr"));
		mdto.setName(request.getParameter("name"));
		mdto.setTel(request.getParameter("tel"));
		mdto.setZipcode(request.getParameter("zipcode"));
		
		sqlSess.update("mUpdateData", mdto);
		sqlSess.commit();
		response.sendRedirect("list.my");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
