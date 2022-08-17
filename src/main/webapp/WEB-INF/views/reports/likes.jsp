<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="actRep" value="${ForwardConst.ACT_REP.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commShow" value="${ForwardConst.CMD_SHOW.getValue()}" />
<c:set var="commNew" value="${ForwardConst.CMD_NEW.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <h2>いいね　一覧</h2>
                <table id="like_list">
    <tbody>
                <tr>
                    <th class="like_name">氏名</th>
                    <th class="like_date">日付</th>
                </tr>
                <c:forEach var="like" items="${likes}" varStatus="status">

                    <tr class="row${status.count % 2}">
                        <td class="like_name"><c:out value="${like.employee.name}" /></td>

                        <fmt:parseDate value="${like.createdAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="createDay" type="date" />
                        <td class="like_date">
                        <fmt:formatDate value="${createDay}" pattern="yyyy-MM-dd HH:mm:ss" />
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id="pagination">
            （全 ${like_Count} 件）<br />
            <c:forEach var="i" begin="1" end="${((like_Count - 1) / maxRow) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='?action=${actRep}&command=likeIndex&id=${ID}&page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p>
            <a href="<c:url value='?action=Report&command=index' />">一覧に戻る</a>
        </p>

    </c:param>
</c:import>