package edu.nyu.TweetMap.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.nyu.TweetMap.Elasticsearch.*;

public class KeywordsSearchingServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String keyword = request.getParameter("keyword");
        String res = Elasticsearch.ElasticFetchByKeywords(keyword);        
        try (PrintWriter iter = response.getWriter()){
            iter.println(res);
        }
    }
}
