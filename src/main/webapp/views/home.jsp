<%
    request.setAttribute("contentPage", "/views/homeContent.jsp");
    request.getRequestDispatcher("/views/layout/readerLayout.jsp").forward(request, response);
%>
