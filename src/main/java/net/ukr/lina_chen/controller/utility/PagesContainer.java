package net.ukr.lina_chen.controller.utility;

public interface PagesContainer {
    String ADMIN_MAIN = "/admin/adminmain.jsp";
    String REDIRECT = "redirect:/";
    String ERROR_ALREADY_LOGGED = "error?already_logged=true";
    String LOGOUT_PARAM = "?logout=true";
    String USER_MAIN = "/user/usermain.jsp";
    String MASTER_MAIN = "/master/mastermain.jsp";
    String REDIRECT_MASTER = "redirect:/master";
    String INDEX_PAGE = "/index.jsp";
    String LOGIN_PAGE = "/login.jsp";
    String REDIRECT_LOGIN = "redirect:/login";
    String REDIRECT_LOGIN_PATH = "/app/login";
    String REGISTRATION_PAGE = "/registration.jsp";
    String ERROR_PAGE = "/error.jsp";
    String REDIRECT_ERROR_DIRECT = "/app/error";
    String SERVICETYPES_PAGE = "/user/servicetypes.jsp";
    String REDIRECT_SERVICETYPES = "redirect:/servicetypes";
    String MASTER_LIST_PAGE = "/user/masters.jsp";
    String TIME_PAGE = "/user/time.jsp";
    String REDIRECT_TIME = "redirect:/user/time";
    String SAVE_PAGE = "/user/save.jsp";
    String ARCHIVE_APPOINTMENTS_PAGE = "/user/archive.jsp";
    String USER_COMMENT_PAGE = "/user/comment.jsp";
    String REDIRECT_ARCHIVE_APPOINTMENTS = "redirect:/user/archive";
    String MASTER_APPOINTMENTS_PAGE = "/master/appointments.jsp";
    String REDIRECT_MASTER_APPOINTMENTS = "redirect:/master/appointments";
    String REDIRECT_ADMIN_APPOINTMENTS = "redirect:/admin/appointments";
    String ADMIN_APPOINTMENTS_PAGE = "/admin/appointments.jsp";
    String USER_APPOINTMENTS_PAGE = "/user/appointments.jsp";
    String ADMIN_COMMENTS_PAGE = "/admin/comments.jsp";
    String MASTER_COMMENTS_PAGE = "/master/comments.jsp";
    String ERROR_PARAM = "?error=true";
}
