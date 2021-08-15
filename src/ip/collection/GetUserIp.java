package ip.collection;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 내부망 아이피 확인 프로그램
 */
@WebServlet("/testAPI")
public class GetUserIp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUserIp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("---------------------------------------");
		System.out.println("init 메서드 호출");
		System.out.println("IP 획득 API가 작동합니다.");
		System.out.println("---------------------------------------");
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("---------------------------------------");
		System.out.println("Get 방식으로 요청받았습니다.");
		System.out.println("doHandler 에게 위임합니다.");
		System.out.println("---------------------------------------");
		doHandler(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("---------------------------------------");
		System.out.println("Post 방식으로 요청받았습니다.");
		System.out.println("doHandler 에게 위임합니다.");
		System.out.println("---------------------------------------");
		doHandler(request, response);
	}
	
	protected static void doHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//클라이언트 아이피를 쿠키로 저장해봤다,잘된다
		//Cookie ip = new Cookie("ip" , request.getRemoteAddr());
		//ip.setMaxAge(24 * 60 * 60); // 쿠키 유효시간 설정 , 24시간
		//response.addCookie(ip);
		
		response.setContentType("text/html;charset=utf-8");
		
		String ip = (String)request.getRemoteAddr();
		
		FileOutputStream fos = new FileOutputStream("이 영역에 로컬에 저장할 경로 작성",true);
		
		BufferedOutputStream buffer = new BufferedOutputStream(fos);
		
		Date date = new Date();
		
		SimpleDateFormat docuDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String time = docuDate.format(date);
		
		buffer.write("클라이언트의 아이피".getBytes());
		buffer.write(ip.getBytes());
		buffer.write("클라이언트의 요청시간".getBytes());
		buffer.write(time.getBytes());
		buffer.write(" ".getBytes());
		buffer.write("\r".getBytes());
		
		buffer.close();
		
		PrintWriter out = response.getWriter();
		
		out.print("<html>");
		out.print("<body>");
		out.print("<hr>");
		out.print("<div align='center';>");
		out.print("안녕하세여 나태쿤의 테스트 API이에여");
		out.print("<br>");
		out.print("<br>");
		out.print("클라이언트의 IP를 얻어서 , 화면에 보내주고");
		out.print("<br>");
		out.print("서버관리자는 그사람의 아이피/시간 을 파일에다 기록해요");
		out.print("<hr>");
		out.print("당신의 아이피 : " + ip);
		out.print("<br>");
		out.print("당신의 요청시간 : " + time);
		out.print("<hr>");
		out.print("<img src='https://pbs.twimg.com/profile_images/419173455011930112/_Aa0jfm-_400x400.jpeg'/>");
		out.print("당신의 아이피는 내꺼야!!");
		out.print("<hr>");
		out.print("<div>");
		out.print("</body>");
		out.print("</html>");
		
		
		
	}

}
