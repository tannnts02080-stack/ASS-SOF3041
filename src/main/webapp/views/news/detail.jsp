<%
    request.setAttribute("contentPage", "/views/news/detailContent.jsp");
    request.getRequestDispatcher("/views/layout/readerLayout.jsp").forward(request, response);
%>
