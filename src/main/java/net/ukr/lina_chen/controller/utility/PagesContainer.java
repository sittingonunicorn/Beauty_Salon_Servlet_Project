package net.ukr.lina_chen.controller.utility;

public interface PagesContainer {
    String ADMIN_MAIN = "/admin/adminmain.jsp";
    String REDIRECT_ADMIN = "redirect:/admin";
    String USER_MAIN = "/user/usermain.jsp";
    String REDIRECT_USER="redirect:/user";
    String MASTER_MAIN ="/master/mastermain.jsp";
    String REDIRECT_MASTER="redirect:/master";
    String INDEX_PAGE="/index.jsp";
    String LOGIN_PAGE="/login.jsp";
    String REDIRECT_LOGIN="redirect:/login";
    String REGISTRATION_PAGE="/registration.jsp";
    String ERROR_PAGE="/error.jsp";
    String SERVICETYPES_PAGE = "/user/servicetypes.jsp";
    String MASTER_LIST_PAGE = "/user/masters.jsp";
    String TIME_PAGE = "/user/time.jsp";
    String SAVE_PAGE = "/user/save.jsp";
    String ARCHIVE_APPOINTMENTS_PAGE = "/user/archive.jsp";
    String USER_COMMENT_PAGE = "/user/comment.jsp";
    String REDIRECT_ARCHIVE_APPOINTMENTS = "redirect:/user/archive";
    String MASTER_APPOINTMENTS_PAGE="/master/appointments.jsp";
    String REDIRECT_MASTER_APPOINTMENTS="redirect:/master/appointments";
    String REDIRECT_ADMIN_APPOINTMENTS="redirect:/admin/appointments";
    String ADMIN_APPOINTMENTS_PAGE = "/admin/appointments.jsp";
    String ADMIN_COMMENTS_PAGE = "/admin/comments.jsp";
}
