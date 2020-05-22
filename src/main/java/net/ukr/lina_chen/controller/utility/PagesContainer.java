package net.ukr.lina_chen.controller.utility;

public interface PagesContainer {
    String ADMIN_MAIN = "/WEB-INF/admin/adminmain.jsp";
    String REDIRECT_ADMIN = "redirect:/admin";
    String USER_MAIN = "/WEB-INF/user/usermain.jsp";
    String REDIRECT_USER="redirect:/user";
    String MASTER_MAIN ="/WEB-INF/master/mastermain.jsp";
    String REDIRECT_MASTER="redirect:/master";
    String INDEX_PAGE="/WEB-INF/index.jsp";
    String LOGIN_PAGE="/WEB-INF/login.jsp";
    String REDIRECT_LOGIN="redirect:/login";
    String REGISTRATION_PAGE="/WEB-INF/registration.jsp";
    String ERROR_PAGE="/WEB-INF/error.jsp";
    String SERVICETYPES_PAGE = "/WEB-INF/user/servicetypes.jsp";
    String BEAUTYSERVICES_PAGE = "/WEB-INF/user/beautyservices.jsp";
    String MASTER_LIST_PAGE = "/WEB-INF/user/masters.jsp";
    String TIME_PAGE = "/WEB-INF/user/time.jsp";
    String SAVE_PAGE = "/WEB-INF/user/save.jsp";
    String ARCHIVE_APPOINTMENTS_PAGE = "/WEB-INF/user/archive.jsp";
    String USER_COMMENT_PAGE = "/WEB-INF/user/comment.jsp";
    String REDIRECT_ARCHIVE_APPOINTMENTS = "redirect:/user/archive";
    String MASTER_APPOINTMENTS_PAGE="/WEB-INF/master/appointments.jsp";
    String REDIRECT_MASTER_APPOINTMENTS="redirect:/master/appointments";
    String ADMIN_APPOINTMENTS_PAGE = "/WEB-INF/admin/appointments.jsp";
    String ADMIN_COMMENTS_PAGE = "/WEB-INF/admin/comments.jsp";
}
