package org.maddress.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.maddress.config.MybatisManager;
import org.maddress.model.ZipcodeDTO;


/**
 * Servlet implementation class ZipcodeAction
 */
@WebServlet("/maddr/mZip.my")
public class ZipcodeAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ZipcodeAction() {
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
		String dong = request.getParameter("dong");
		JSONArray jarr = new JSONArray();
		List<ZipcodeDTO> arr = sqlSess.selectList("mZipData", dong);
		for(ZipcodeDTO z : arr){
			JSONObject obj = new JSONObject();
			obj.put("zipcode",z.getZipcode());
			obj.put("sido",z.getSido());
			obj.put("gugun",z.getGugun());
			obj.put("dong",z.getDong());
			obj.put("bunji",z.getBunji());
			jarr.add(obj);
		}
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(jarr.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
