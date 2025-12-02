<%
    request.setAttribute("contentPage", "/views/categoryContent.jsp");
    request.getRequestDispatcher("/views/layout/readerLayout.jsp").forward(request, response);
%>
