package edu.nyu.TweetMap.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.nyu.TweetMap.Elasticsearch.*;
public class DistanceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String lat = request.getParameter("lat");
        String lon = request.getParameter("lon");
        //String distance = request.getParameter("distance");
        String res = Elasticsearch.ElasticFetchByDistance(lat, lon, "100");
        System.out.println("lat: " + lat);
        System.out.println("lon: " + lon);
        
        try (PrintWriter iter = response.getWriter()){
            iter.println(res);
        }
    }
}
