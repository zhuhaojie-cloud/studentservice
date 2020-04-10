package swu.zhj.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.catalina.core.ApplicationPart;

import swu.zhj.service.UserService;

/**
 * Servlet implementation class Tou
 */
@WebServlet("/Tou")
@MultipartConfig()     //����Ҫ�����
public class Tou extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Tou() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//����name������img
				response.setContentType("text/html;charset=utf-8");
				request.setCharacterEncoding("utf-8");
				response.setCharacterEncoding("utf-8");
				HttpSession session=request.getSession();
				int id=(Integer)session.getAttribute("id");
				//String img=request.getParameter("uploadImg");
				session.setAttribute("id",id);
				//session.setAttribute("img",img);
				String path = this.getServletContext().getRealPath("/");	//��ȡ��������ַ
				Part p = request.getPart("uploadImg");//��ȡ�û�ѡ����ϴ��ļ�
				String fname2="";
				if (p.getContentType().contains("image")) {					// �������ϴ���ͼ���ļ�
					ApplicationPart ap = (ApplicationPart) p;
					//��ȡ�ϴ��ļ���
					String fname1 = ap.getSubmittedFileName();
					System.out.println(ap.getSubmittedFileName());
					System.out.println(ap.getClass());
					System.out.println(ap.getInputStream());
					
					
					//���´���ȡ���ļ��ĺ�׺��
					int dot=fname1.lastIndexOf(".");
					String extentname=fname1.substring(dot+1);
					//���´���ȡ�õ�ǰ����
					Date now = new Date();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");//���Է�����޸����ڸ�ʽ
					String firstname = dateFormat.format( now ); 
					//���������
					Random rd=new Random();
					String xxxx="";
					for(int i=1;i<=4;i++){
						xxxx=xxxx+(rd.nextInt(10)+1);
					}
					//ƴ���ļ���
					firstname=firstname+xxxx;
					fname2=firstname+"."+extentname;
					// д�� web ��Ŀ��·���µ�upload�ļ�����
					p.write(path + "/upload/" + fname2);
					System.out.print(path + "/upload/" + fname2);
				}
				else{
		
					  System.out.println("aa");
				}
				String pic_url="/project1/upload/" + fname2;
				System.out.println(pic_url+"mm");
				//System.out.println(img+"qq");
				boolean flag=UserService.AdduserimgByName(id,pic_url);
				System.out.println(pic_url+"mm");
                  if(flag) {
                    	response.sendRedirect("Student_User");
				}else {
					System.out.println("ʧ��");
				}
				
	}

}