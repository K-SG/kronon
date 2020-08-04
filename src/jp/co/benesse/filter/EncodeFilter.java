package jp.co.benesse.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * [機 能] 日本語の文字化けを防ぐフィルター<br>
 * [説 明] データの受け渡しの際の変換を行う<br>
 * [備 考] なし<br>
 * [環 境] OpenJDK 11 <br>
 *
 * @author [作 成] 2020/07/16 山崎和樹 [修 正]
 */

@WebFilter("/*")
public class EncodeFilter implements Filter {

	public EncodeFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
