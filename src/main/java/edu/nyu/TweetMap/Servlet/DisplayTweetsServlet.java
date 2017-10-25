package edu.nyu.TweetMap.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.nyu.TweetMap.Elasticsearch.Elasticsearch;
import edu.nyu.TweetMap.stream.TwitterStream;

/**
 * Servlet implementation class displayTweetsServlet
 */
public class DisplayTweetsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void init() throws ServletException
	{
	      TwitterStream stream = new TwitterStream();
	      Thread tweetStream = new Thread(stream);
	      tweetStream.start();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    response.setContentType("text/html; charset=UTF-8");
        String keyword = request.getParameter("init");
	    String res = "";
	    System.out.println("request: " + keyword);
	    res =  Elasticsearch.ElasticFetchAll();
	    try (PrintWriter iter = response.getWriter()){
            iter.println(res);
        }
	    //System.out.println(res);	    
	}

}
