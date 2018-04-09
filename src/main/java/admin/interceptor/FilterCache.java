package admin.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class FilterCache implements Filter {
	private String encoding;

	private boolean forceEncoding = false;
	
	
	
    public String getEncoding() {
		return encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	public boolean isForceEncoding() {
		return forceEncoding;
	}
	public void setForceEncoding(boolean forceEncoding) {
		this.forceEncoding = forceEncoding;
	}
	
	
	@Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
            HttpServletResponse res = (HttpServletResponse) response;  
            //res.setDateHeader("expries", new Date().getTime()+60*60*24*1000); //- 设置一天失效，经测试Chrome下不生效
           // res.setHeader("Cache-Control", "max-age=10");                   //- 这里的单位为秒，10代表第一次请求10s后过期
            

    		if (this.encoding != null && (this.forceEncoding || request.getCharacterEncoding() == null)) {
    			request.setCharacterEncoding(this.encoding);
    			if (this.forceEncoding) {
    				response.setCharacterEncoding(this.encoding);
    			}
    		}
    		chain.doFilter(request, response);
    		System.out.println(this.encoding);
            
    }
    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }
}
