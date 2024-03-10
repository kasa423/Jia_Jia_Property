package com.fetch.common.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author jiang chen
 * @version 1.0
 * @description: TODO
 * @date 2024/3/10 21:08
 */
public class WebUtils {

    public static void renderString(HttpServletResponse response, String template) {
        try {
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(template);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
