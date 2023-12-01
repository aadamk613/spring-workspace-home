package com.kh.spring.member.controller;

import java.text.DecimalFormat;
import java.text.Format;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.vo.CertVO;
import com.kh.spring.member.model.vo.Member;

@Controller
public class MemberController { // RequestMapping타입의 애노테이션을 붙임으로서 HandlerMapping을 등록
	
	/*
	 * 기존 객체 생성 방식
	 * 서비스가 동시에 매우 많은 횟수가 요청 될 경우 그만큼 객체 생성됨!!!
	 * 객체간의 결합도가 높아짐!!!!!!(B클래스의 수정이 일어날 경우 B클래스를 의존하고 있던 A클래스가 영향을 받게 됨!!!)
	 * 
	 * 
	 */
	
	// Spring의 D.I(Dependency Injection)을 이용한 방식
	// Field Injection
	// @Autowired가 달린 애노테이션을 찾아서 필드와 일치하는 타입을 bean객체중에 검색해서 필드로 주입시켜줌!
	// 객체간의 결합도를 낮출 수 있음
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	@Autowired
	private JavaMailSender sender;
	
	/*
	@RequestMapping(value="login.me")
	public void loginMember() {
		System.out.println("하나만 ㅜㅜ");
	}
	*/
	
	/*
	 * Spring에서 요청시 전달값(Parameter)을 받는 방법
	 * 
	 * 1. HttpServletRequest을 이용해서 전달받기(기존의 JSP/Servlet방식)
	 * 
	 * 해당 메소드의 매개변수로 HttpServletRequest타입을 작성해두면
	 * DispatcherServlet이 해당 메소드를 호출할 때 request객체를 전달해서 매개변수로 주입을 해줌
	 */

	/*
	@RequestMapping(value="login.me")
	public String loginMember(HttpServletRequest request) {
		
		String userId = request.getParameter("id");
		String userPwd = request.getParameter("pwd");
		
		System.out.println("userId " + userId);
		System.out.println("userPwd " + userPwd);
		
		return "main";
		
	}
	*/
	
	/*
	 * 2. @RequestParam 애노테이션을 이용하는 방법
	 * request.getParameter("키")로 밸류를 뽑아오는 역할을 대신해주는 애노테이션
	 * value속성의 값으로 jsp에서 작성했던name속성값을 적으면 알아서 해당 매개변수로 주입을 해줌
	 * 만약, 넘어온값이 비어있는 형태라면 defaultValue속성으로 기본값을 지정할 수 있음
	 */

	/*
	@RequestMapping(value="login.me")
	public String loginMember(@RequestParam(value="id", defaultValue="aaa") String userId,
							  @RequestParam(value="pwd") String userPwd) {
		
		System.out.println(userId + userPwd);
		return "main";
	}
	*/
	
	/*
	 * 3. RequestParam 애노테이션을 생략하는 방법
	 * 단, 매개변수명을 jsp의 name속성값(요청 시 전달하는 값의 키값)과 동일하게 세팅해둬야 자동으로 값이 주입
	 * 단점이라고 한다면 위엣 사용했던 defaultValue속성을 사용할 수 없음
	 */
	/*
	@RequestMapping(value="login.me")
	public String loginMember(String id, String pwd) {
		System.out.println("userId " + id);
		System.out.println("userPwd " + pwd);
		
		Member m = new Member();
		m.setUserId(id);
		m.setUserPwd(pwd);
		
		return "main";
	}
	*/
	
	/*
	 * 4. 커맨드 객체 방식
	 * 
	 * *** 반드시 name속성값과 담고자하는 필드명이 동일해야함!!!!!!!!!!!!!!!!!! + 기본생성자가 꼭 있어야함!!!!!!! + setter가 꼭 있어야 함!!!!!!!!!!!!
	 * 
	 * 해당 메서드의 매개변수로 
	 * 요청 시 전달값을 담고자하는 클래스의 타입을 세팅 후
	 * 요청 시 전달값의 키값(jsp의 name속성값)을 클래스의 담고자하는 필드명으로 작성
	 * 
	 * 스프링 컨테이너가 해당 객체를 기본생성자로 생성한 후 내부적으로 setter메소드를 찾아서 요청시 전달값을 해당 필드에 담아줌(setter injection)
	 * 
	 */
	/*
	@RequestMapping("login.me")
	public String loginMember(Member m) { // 먼저 객체 만들 때 기본생성자로 만들어준다
		
		System.out.println("userId " + m.getUserId());
		System.out.println("userPwd " + m.getUserPwd());
		
		Member loginMember = memberService.loginMember(m);
		
		if(loginMember == null) {
			System.out.println("로그인 실패");
		} else {
			System.out.println("로그인 성공");
		}
		// new MemberServiceImpl().loginMember(m); -> 이제는 내가 직접 서비스로 호출하지 않음
		
		return "main";
	}
	*/
	
	/*
	 * 요청 처리 후 응답데이터를 담고 응답페이지로 포워딩 또는 url재요청 하는 방법
	 * 
	 * 1. 스프링에서 제공하는 Model객체를 사용하는 방법
	 * 포워딩할 응답 뷰로 전달하고자 하는 데이터를 맵형식(key-value)으로 담을 수 있는 영역
	 * Model객체는 requestScope
	 * 단, setAttribute가 아닌 addAttribute메소드를 호출해야함
	 */
	
	/*
	// return "main";  WEB-INF/views/main.jsp 경로를 찾아감
	@RequestMapping("login.me")
	public String loginMember(Member m // 원시 자료형이 아니라면 @ModelAttribute Member m인데 앞이 생략 되었음
							 , Model model
							 , HttpSession session) {
		Member loginUser = memberService.loginMember(m);
		
		// Member의 m의 userId필드 : 사용자가 입력한 아이디
		// Member의 loginuser의 userId필드 : 조회된 아이디
		
		// Member의 m의 userPwd필드 : 사용자가 입력한 비밀번호(평문)
		// Member의 loginUser의 userPwd필드 : DB에 기록된 암호환된 비밀번호
		
		// BCryptPasswordEncoder객체 matches()
		// matcher(평문, 암호문)
		// 암호문에 포함되어있는 Salt값을 판단해서 평문에 Salt값을 더해서 암호화를 진행하고 두 값이 같은지 비교!!
		// 일치하면 true / 일치하지 않으면 false
		
		if(loginUser == null && !(bcryptPasswordEncoder.matches(m.getUserPwd(), loginUser.getUserPwd()))) { // 로그인 실패! => 에러문구를 requestScope에 담아서 에러페이지로 포워딩
			model.addAttribute("errorMsg", "로그인에 실패");

			/*
			 * 포워딩(파일 경로를 포함한 파일명을 제시)
			 * 
			 * return "abc";
			 * 
			 * servlet-context.xml <- ViewResolver
			 * 
			 * - prefix : /WEB/INF/views
			 * 
			 * - 중간 : abc(파일경로를 포함한 파일명)
			 * 
			 * -suffix : .jsp
			 * 
			 * /WEB-INF/views     common/errorPage      .jsp
			 */
	
	/*
			return "common/errorPage";
		} else { // 로그인 성공 => loginUser를 sessionScope에 담고 메인페이지로 url로 재요청
			session.setAttribute("loginUser", loginUser);
			
			// sendRedirect(url경로를 제시)
			// redirect:요청할url
			return "redirect:/";
			// localhost:8001/spring
		}
		
	}
	
	*/
	
	/*
	 * 2. 스프링에서 제공하는 ModelAndView타입을 사용하는 방법
	 * Model은 데이터를 key-value세트로 담을 수 있는 공간이라고 한다면
	 * view는 응답 뷰에 대한 정보를 담을 수 있는 공간
	 * 
	 * Model객체와 View가 결합된 형태의 객체
	 * 
	 */
	@RequestMapping("login.me")
	public ModelAndView loginMember(Member m,
									HttpSession session,
									ModelAndView mv) {
		Member loginUser = memberService.loginMember(m);
		
		if(loginUser != null && bcryptPasswordEncoder.matches(m.getUserPwd(), loginUser.getUserPwd())) {
			session.setAttribute("loginUser", loginUser);
			mv.setViewName("redirect:/");
		} else {
			// model.addAttribute
			mv.addObject("errorMsg", "로그인 실패");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	@RequestMapping("logout.me")
	public String logoutMember(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping("enrollForm.me")
	public String enrollForm() {
		return "member/memberEnrollForm";
	}
	
	@RequestMapping("insert.me")
	public String enrollMember(Member m, Model model) {
//		System.out.println(m);
//		return "main";
		// 1. 한글 문자가 인코딩 때문에 깨짐!! => web.xml에 스프링에서 제공하는 인코딩 필터 등록
		// 2. 나이를 입력하지 않았을 경우 int 자료형에 빈 문자열이 넘어오기 때문에 자료형이 맞지 않은 문제 발생(400 Bad request)
		// => Lombok을 이용해서 Member의 클래스의 age필드를 int형 =>String형으로 변경
		// 3. 비밀번호가 사용자가 입력한 있는 그대로의 평문(plain text)
		// Bcrypte방식을 이용해서 => 스프링 시큐리티 모듈에서 제공(pom.xml로 이동)
		// BcryptPasswordEncoder클래스를 .xml파일에 bean으로 등록
		// => web.xml에서 spring-security.xml파일을 로딩할 수 있도록 작성
	//	System.out.println("평문 " + m.getUserPwd());
		// 암호화 작업(암호문을 만들어내는 과정)
		String encPwd = bcryptPasswordEncoder.encode(m.getUserPwd());
		System.out.println("암호문 " + encPwd);
		
		m.setUserPwd(encPwd); // Member객체에 userPwd필드에 평문이 아닌 암호문을 담아서 DB로 보내기!
		
		if(memberService.insertMember(m) > 0) {
			return "redirect:/";
		} else {
			model.addAttribute("errorMsg", "회원가입 실패");
			return "common/errorPage";
		}
	}
	
	@RequestMapping("myPage.me")
	public String myPage() {
		return "member/myPage";
	}
	
	@RequestMapping("update.me")
	public String updateMember(/*@MedelAttribute*/Member m,
							   	Model model,
							   	HttpSession session) {
		// model.addAttribute("member", m);
		if(memberService.updateMember(m) > 0) {
			// DB로부터 수정된 회원정보를 다시 조회해서
			// sessionScope에 loginUser라는 키값으로 덮어씌워줄것!
			session.setAttribute("loginUser", memberService.loginMember(m));
			// session에 일회성 알람문구 띄워주기
			session.setAttribute("alertMsg", "정보 수정에 성공했습니다!");
			
			// 마이페이지화면이 띄워지도록~~
			return "redirect:myPage.me";
			
		} else { // 수정 실패 => 에러문구를 담아서 에러페이지로 포워딩
			model.addAttribute("errorMsg", "정보 수정에 실패하였습니다.");
			return "common/errorPage";
		}
		
	}
	
	@RequestMapping("delete.me")
	public String deleteMember(String userPwd, HttpSession session) {
		// userPwd : 회원 탈퇴 요청 시 사용자가 입력한 비밀번호 평문
		// session의 loginUser Member객체의 userPwd필드: DB에 기록된 암호화된 비밀번호
		
		Member loginUser = (Member)session.getAttribute("loginUser");
		
		String encPwd = loginUser.getUserPwd();
		// 비밀번호가 사용자가 입력한 평문으로 만든 암호문일 경우
		if(bcryptPasswordEncoder.matches(userPwd, encPwd)) {
			
			String userId = loginUser.getUserId();
			if(memberService.deleteMember(userId) > 0) {
				// 탈퇴처리 성공 => session에서 loginUser지움, alert문구 담기 => 메인페이지로 잘가
				session.removeAttribute("loginUser");
				session.setAttribute("alertMsg", "탈퇴 성공~");
				return "redirect:/";
			} else {
				session.setAttribute("alertMsg", "탈퇴 실패");
				return "common/errorPage";
			}
		} else {
			session.setAttribute("alertMsg", "비밀번호가 일치하지 않습니다.");
			return "redirect:myPage.me";
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value="idCheck.me")
	public String idCheck(String checkId) {
		//System.out.println(checkId);
		int count = memberService.idCheck(checkId);
		
		if(count > 0) { // 이미 존재하는 아이디 => 사용불가능(NNNNN)
			return "NNNNN";
		} else { // 사용 가능(NNNNY)
			return "NNNNY";
		}
		
		// return memberService.idCheck(checkId) > 0 ? "NNNNN" : "NNNNY";
	}
	
	@GetMapping("inputmail")
	public String inputMail() {
		return "member/input";
	}
	
	@PostMapping("mail")
	public String mail(String email, HttpServletRequest request) throws MessagingException {
		
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		
		// 지금 요청을 보낸 ip주소
		String ip = request.getRemoteAddr();
		
		Random r = new Random();
		int i = r.nextInt(100000);
		Format f = new DecimalFormat("000000");
		String secret = f.format(i);
		
		CertVO certVo = CertVO.builder()
							  .who(ip)
							  .secret(secret)
							  .build();
		memberService.sendMail(certVo);
		
		// 생성자를 호출하지 않고도 원하는 필드에 원하는 값을 넣을 수 있음
		helper.setTo(email);
		helper.setSubject("인증번호 보내드립니다.");
		helper.setText("인증번호 : " + secret);
		
		sender.send(message);
		
		return "redirect:checkPage";
	}
	
	@RequestMapping("checkPage")
	public String checkPage() {
		return "member/check";
	}
	
	@ResponseBody
	@PostMapping("check")
	public String checkCode(String secret, HttpServletRequest request) {
		CertVO certVo = CertVO.builder()
							  .who(request.getRemoteAddr())
							  .secret(secret)
							  .build();
		System.out.println(secret);
		boolean result = memberService.validate(certVo);
		
		return "result:" + result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
