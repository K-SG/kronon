
//package jp.co.benesse.filter;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
///**
// * [機 能] ログインフィルター<br>
// * [説 明] ログイン画面以降、ログインしていないユーザーの不正アクセスを弾く<br>
// * [備 考] なし<br>
// * [環 境] OpenJDK 11 <br>
// *
// * @author [作 成] 2020/07/16 山崎和樹 [修 正]
// */
//@WebFilter("/user/*")
//public class LoginFilter implements Filter {
//
//	public LoginFilter() {
//	}
//
//	public void destroy() {
//	}
//
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		// セッションが存在しない場合NULLを返す
//		HttpSession session = ((HttpServletRequest) request).getSession(false);
//
//		if (session.getAttribute("userBean") != null) {
//			// セッションがNULLでなければ、通常どおりの遷移
//			chain.doFilter(request, response);
//		} else {
//			// セッションがNullならば、ログイン画面へ飛ばす
//			((HttpServletResponse) response).sendRedirect("/login");
//		}
//	}
//
//	public void init(FilterConfig fConfig) throws ServletException {
//
//	}
//
//}

/*package jp.co.benesse.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

*//**
 * [機 能] ログインフィルター<br>
 * [説 明] ログイン画面以降、ログインしていないユーザーの不正アクセスを弾く<br>
 * [備 考] なし<br>
 * [環 境] OpenJDK 11 <br>
 *
 * @author [作 成] 2020/07/16 山崎和樹 [修 正]
 *//*
@WebFilter("/user/*")
public class LoginFilter implements Filter {

	public LoginFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// セッションが存在しない場合NULLを返す
		HttpSession session = ((HttpServletRequest) request).getSession(false);

		if (session.getAttribute("userBean") != null) {
			// セッションがNULLでなければ、通常どおりの遷移
			chain.doFilter(request, response);
		} else {
			// セッションがNullならば、ログイン画面へ飛ばす
			((HttpServletResponse) response).sendRedirect("/login");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
*/
