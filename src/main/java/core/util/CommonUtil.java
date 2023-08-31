package core.util;

import static core.util.Constants.GSON;
import static core.util.Constants.JSON_MIME_TYPE;

import java.io.BufferedReader;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class CommonUtil {
	private static final Logger logger = LogManager.getLogger(CommonUtil.class);
	
//	public static Connection getConnection() throws NamingException, SQLException {
//		if (DATASOURCE == null) {
//			DATASOURCE = (DataSource) new InitialContext().lookup("java:/comp/env/jdbc/javaFramework");
//		}
//		return DATASOURCE.getConnection();
//	}

	public static <P> P json2Pojo(HttpServletRequest request, Class<P> classOfPojo) {
		try (BufferedReader br = request.getReader()) {
			return GSON.fromJson(br, classOfPojo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
//			e.printStackTrace();
		}
		return null;
	}

	public static <P> void writePojo2Json(HttpServletResponse response, P pojo) {
		response.setContentType(JSON_MIME_TYPE);
		try (PrintWriter pw = response.getWriter()) {
			pw.print(GSON.toJson(pojo));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
//			e.printStackTrace();
		}
	}
	
	public static <T> T getBean(ServletContext sc, Class<T> clazz) {
        ApplicationContext context = 
            WebApplicationContextUtils.getWebApplicationContext(sc);
        return context.getBean(clazz);
    }
}
