<%--
  Created by IntelliJ IDEA.
  User: mac
  Date: 2019/3/19
  Time: 19:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="title" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib uri="/tags" prefix="date"%>

<link rel="stylesheet" href="/static/css/css8.1.css">
<main>
    <div class="container">
        <div class="row occupation">

                <a href="/user/stu">首页</a> &gt;
                <span> 职业</span>
            </p>
        </div>
        <div class="row learning">
            <p>方向：</p>
            <span>全部</span>
            <span>前端开发</span>
            <span>后端开发</span>
            <span>移动开发</span>
            <span>整站开发</span>
            <span>运营维护</span>

        </div>


        <div class="row front-end-det-dir">
            <p>前端开发方向</p>
        </div>
        <div class="row">

            <c:forEach var="job" items="${jobList}">
                <c:if test="${job.direction == '前端开发方向' }">

                    <div class=" col-xs-12 col-md-6 col-lg-4 now">
                        <div class="wrapper">
                            <div class="box gird-one">
                                <img src="${job.picture}" height="139" width="139"/>
                            </div>
                            <div class="box gird-two">
                                <p class="ios-box">${job.profession}</p>
                                <p>${job.introduce}
                                </p>
                            </div>

                            <div class="box gird-three">
                                门槛&nbsp;&nbsp;&nbsp;&nbsp;
                                    <%-- 循环图片个数 起始于1 step为每次循环添加数量 结束于需要的数量--%>
                                <c:forEach var="i" begin="1" end="${job.threshold}" step="1">
                                    <img src="/static/img/xx8.png" height="15" width="16"/>
                                </c:forEach>
                            </div>

                            <div class="box gird-four">
                                难易程度&nbsp;&nbsp;&nbsp;&nbsp;
                                <c:forEach var="i" begin="1" end="${job.difficulty}" step="1">
                                    <img src="/static/img/xx8.png" height="15" width="16"/>
                                </c:forEach>
                            </div>

                            <div class="box gird-five">
                                成长周期&nbsp;&nbsp;&nbsp;&nbsp;<span class="number-n">${job.growthCycle}</span>年
                            </div>
                            <div class="box gird-six">
                                稀缺程度&nbsp;&nbsp;&nbsp;&nbsp;<span class="number-n">${job.scarcityLevel}</span><span class="time">家公司需要</span>
                            </div>
                            <div class="box gird-seven">
                            </div>
                            <div class="box gird-eight">
                                <p class="time"> 0-1年 <span class="digit">5k-10k/月</span></p>
                            </div>
                            <div class="box gird-nine">
                                薪资待遇
                            </div>
                            <div class="box gird-ten">
                                <p class="time">1-3年 <span class="digit">10k-20k/月</span></p>
                            </div>
                            <div class="box gird-eleven">
                                <p class="time">0-1年 <span  class="digit">20k-50k/月</span></p>
                            </div>
                            <div class="box gird-twelve">
                            </div>
                            <div class="box gird-thirteen">
                                <p class="ios-box">有<span class="number-n">${job.peopleCounting}</span>人在学</p>
                                <p>创建时间:<date:date value = "${job.createdAt} "/></p>
                                <p>更新时间:<date:date value="${job.updatedAt}" /></p>
                            </div>


                            <div class="box gird-fourteen">
                            </div>
                            <div class="box gird-fifteen">


                                提示:在你学习之前你应该已经掌握XXXX、XXXX等语言基础
                            </div>

                            <div class="engineer">
                                <p>iOS工程师</p>
                                <p>iOS是由苹果公司开发的移动操作系统，iOS与苹果的Mac OS X操作系统一样，也是以Darwin为基础的，因此同样属于类Unix的商业操作系统。国内iOS开发起步相对较晚，人才培养机制更是远远跟不上市场发展速度。有限的iOS开发人才成了国内企业必争的资源。国内iOS开发起步相对较晚，人才培养机制更是远远跟不上市场发展速度。有限的iOS开发人才成了国内企业必争的资源。
                                </p>
                            </div>

                        </div>
                    </div>

                </c:if>
            </c:forEach>

        </div>

        <div class="row front-end-det-dir">
            <p>后端开发方向</p>
        </div>
        <div class="row">

            <c:forEach var="job" items="${jobList}">
                <c:if test="${job.direction == '后端开发方向' }">

                    <div class=" col-xs-12 col-md-6 col-lg-4 now">
                        <div class="wrapper">
                            <div class="box gird-one">
                                <img src="${job.picture}" height="139" width="139"/>
                            </div>
                            <div class="box gird-two">
                                <p class="ios-box">${job.profession}</p>
                                <p>${job.introduce}
                                </p>
                            </div>

                            <div class="box gird-three">
                                门槛&nbsp;&nbsp;&nbsp;&nbsp;
                                    <%-- 循环图片个数 起始于1 step为每次循环添加数量 结束于需要的数量--%>
                                <c:forEach var="i" begin="1" end="${job.threshold}" step="1">
                                    <img src="/static/img/xx8.png" height="15" width="16"/>
                                </c:forEach>
                            </div>

                            <div class="box gird-four">
                                难易程度&nbsp;&nbsp;&nbsp;&nbsp;
                                <c:forEach var="i" begin="1" end="${job.difficulty}" step="1">
                                    <img src="/static/img/xx8.png" height="15" width="16"/>
                                </c:forEach>
                            </div>

                            <div class="box gird-five">
                                成长周期&nbsp;&nbsp;&nbsp;&nbsp;<span class="number-n">${job.growthCycle}</span>年
                            </div>
                            <div class="box gird-six">
                                稀缺程度&nbsp;&nbsp;&nbsp;&nbsp;<span class="number-n">${job.scarcityLevel}</span><span class="time">家公司需要</span>
                            </div>
                            <div class="box gird-seven">
                            </div>
                            <div class="box gird-eight">
                                <p class="time"> 0-1年 <span class="digit">5k-10k/月</span></p>
                            </div>
                            <div class="box gird-nine">
                                薪资待遇
                            </div>
                            <div class="box gird-ten">
                                <p class="time">1-3年 <span class="digit">10k-20k/月</span></p>
                            </div>
                            <div class="box gird-eleven">
                                <p class="time">0-1年 <span  class="digit">20k-50k/月</span></p>
                            </div>
                            <div class="box gird-twelve">
                            </div>
                            <div class="box gird-thirteen">
                                <p class="ios-box">有<span class="number-n">${job.peopleCounting}</span>人在学</p>
                                <p>创建时间:<date:date value = "${job.createdAt} "/></p>
                                <p>更新时间:<date:date value="${job.updatedAt}" /></p>
                            </div>
                            <div class="box gird-fourteen">
                            </div>
                            <div class="box gird-fifteen">

                                      提示:在你学习之前你应该已经掌握XXXX、XXXX等语言基础
                            </div>

                            <div class="engineer">
                                <p>iOS工程师</p>
                                <p>iOS是由苹果公司开发的移动操作系统，iOS与苹果的Mac OS X操作系统一样，也是以Darwin为基础的，因此同样属于类Unix的商业操作系统。国内iOS开发起步相对较晚，人才培养机制更是远远跟不上市场发展速度。有限的iOS开发人才成了国内企业必争的资源。国内iOS开发起步相对较晚，人才培养机制更是远远跟不上市场发展速度。有限的iOS开发人才成了国内企业必争的资源。
                                </p>
                            </div>

                        </div>
                    </div>

                </c:if>
            </c:forEach>

        </div>



        <div class="row front-end-det-dir">
            <p>运维方向</p>
        </div>
        <div class="row">
            <c:forEach var="job" items="${jobList}">

                <c:if test="${job.direction == '运维方向' }">

                <div class=" col-xs-12 col-md-6 col-lg-4 now">
                <div class="wrapper">
                    <div class="box gird-one">
                        <img src="${job.picture}" height="139" width="139"/>
                    </div>
                    <div class="box gird-two">
                        <p class="ios-box">${job.profession}</p>
                        <p>${job.introduce}
                        </p>
                    </div>
                    <div class="box gird-three">
                        门槛&nbsp;&nbsp;&nbsp;&nbsp;
                            <%-- 循环图片个数 起始于1 step为每次循环添加数量 结束于需要的数量--%>
                        <c:forEach var="i" begin="1" end="${job.threshold}" step="1">
                            <img src="/static/img/xx8.png" height="15" width="16"/>
                        </c:forEach>
                    </div>

                    <div class="box gird-four">
                        难易程度&nbsp;&nbsp;&nbsp;&nbsp;
                        <c:forEach var="i" begin="1" end="${job.difficulty}" step="1">
                            <img src="/static/img/xx8.png" height="15" width="16"/>
                        </c:forEach>
                    </div>

                    <div class="box gird-six">
                        稀缺程度&nbsp;&nbsp;&nbsp;&nbsp;<span class="number-n">${job.scarcityLevel}</span><span class="time">家公司需要</span>
                    </div>
                    <div class="box gird-seven">
                    </div>
                    <div class="box gird-eight">
                        <p class="time"> 0-1年 <span class="digit">5k-10k/月</span></p>
                    </div>
                    <div class="box gird-nine">
                        薪资待遇
                    </div>
                    <div class="box gird-ten">
                        <p class="time">1-3年 <span class="digit">10k-20k/月</span></p>
                    </div>
                    <div class="box gird-eleven">
                        <p class="time">0-1年 <span  class="digit">20k-50k/月</span></p>
                    </div>
                    <div class="box gird-twelve">
                    </div>
                    <div class="box gird-thirteen">
                        <p class="ios-box">有<span class="number-n">${job.peopleCounting}</span>人在学</p>
                        <p>创建时间:<date:date value = "${job.createdAt} "/></p>
                        <p>更新时间:<date:date value="${job.updatedAt}" /></p>
                    </div>
                    <div class="box gird-fourteen">
                    </div>
                    <div class="box gird-fifteen">
                        提示:在你学习之前你应该已经掌握XXXX、XXXX等语言基础--%>
                    </div>

                    <div class="engineer">
                        <p>iOS工程师</p>
                        <p>iOS是由苹果公司开发的移动操作系统，iOS与苹果的Mac OS X操作系统一样，也是以Darwin为基础的，因此同样属于类Unix的商业操作系统。国内iOS开发起步相对较晚，人才培养机制更是远远跟不上市场发展速度。有限的iOS开发人才成了国内企业必争的资源。国内iOS开发起步相对较晚，人才培养机制更是远远跟不上市场发展速度。有限的iOS开发人才成了国内企业必争的资源。
                        </p>
                      </div>
                     </div>
               </div>
                </c:if>
            </c:forEach>
        </div>

        <div class="row front-end-det-dir">
            <p>运营方向</p>
        </div>
        <div class="row">
     <c:forEach var="job" items="${jobList}">

       <c:if test="${job.direction == '运营方向' }">

            <div class=" col-xs-12 col-md-6 col-lg-4 now">
                <div class="wrapper">
                    <div class="box gird-one">
                        <img src="${job.picture}" height="139" width="139"/>
                    </div>
                    <div class="box gird-two">
                        <p class="ios-box">${job.profession}</p>
                        <p>${job.introduce}
                        </p>
                    </div>
                    <div class="box gird-three">
                        门槛&nbsp;&nbsp;&nbsp;&nbsp;
                            <%-- 循环图片个数 起始于1 step为每次循环添加数量 结束于需要的数量--%>
                        <c:forEach var="i" begin="1" end="${job.threshold}" step="1">
                            <img src="/static/img/xx8.png" height="15" width="16"/>
                        </c:forEach>
                    </div>

                    <div class="box gird-four">
                        难易程度&nbsp;&nbsp;&nbsp;&nbsp;
                        <c:forEach var="i" begin="1" end="${job.difficulty}" step="1">
                        <img src="/static/img/xx8.png" height="15" width="16"/>
                    </c:forEach>
                    </div>

                    <div class="box gird-five">
                        成长周期&nbsp;&nbsp;&nbsp;&nbsp;<span class="number-n">${job.growthCycle}</span>年
                    </div>
                    <div class="box gird-six">
                        稀缺程度&nbsp;&nbsp;&nbsp;&nbsp;<span class="number-n">${job.scarcityLevel}</span><span class="time">家公司需要</span>
                    </div>
                    <div class="box gird-seven">
                    </div>
                    <div class="box gird-eight">
                        <p class="time"> 0-1年 <span class="digit">5k-10k/月</span></p>
                    </div>
                    <div class="box gird-nine">
                        薪资待遇
                    </div>
                    <div class="box gird-ten">
                        <p class="time">1-3年 <span class="digit">10k-20k/月</span></p>
                    </div>
                    <div class="box gird-eleven">
                        <p class="time">0-1年 <span  class="digit">20k-50k/月</span></p>
                    </div>
                    <div class="box gird-twelve">
                    </div>
                    <div class="box gird-thirteen">
                        <p class="ios-box">有<span class="number-n">${job.peopleCounting}</span>人在学</p>
                        <p>创建时间:<date:date value = "${job.createdAt} "/></p>
                        <p>更新时间:<date:date value="${job.updatedAt}" /></p>
                    </div>
                    <div class="box gird-fourteen">
                    </div>
                    <div class="box gird-fifteen">

                        提示:在你学习之前你应该已经掌握XXXX、XXXX等语言基础--%>
                    </div>

                    <div class="engineer">
                        <p>iOS工程师</p>
                        <p>iOS是由苹果公司开发的移动操作系统，iOS与苹果的Mac OS X操作系统一样，也是以Darwin为基础的，因此同样属于类Unix的商业操作系统。国内iOS开发起步相对较晚，人才培养机制更是远远跟不上市场发展速度。有限的iOS开发人才成了国内企业必争的资源。国内iOS开发起步相对较晚，人才培养机制更是远远跟不上市场发展速度。有限的iOS开发人才成了国内企业必争的资源。
                        </p>
                    </div>
                </div>
            </div>

                </div>
            </div>
    </c:if>
    </c:forEach>
        </div>
</main>

