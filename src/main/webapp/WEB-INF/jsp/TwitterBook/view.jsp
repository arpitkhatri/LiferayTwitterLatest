<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<portlet:defineObjects />
<table class="table table-hover">
<tr><td colspan="3" align="right"><img src='${profileImageUrl}' class="img-thumbnail"></td></tr>
<tr><td>Profile Name :</td><td>${name}</td> <td></td></tr>
<tr><td>Twitter Id :</td><td>@${screenName}</td> <td></td></tr>
<tr><td>Latest Tweet : </td><td>${latestTweet}</td> <td></td></tr>
<tr><td>Tweeted on :</td><td>${latestTweetDate}</td> <td></td></tr>
</table>

<%-- <c:out escapeXml="true" value="${releaseInfo}" />. --%>
