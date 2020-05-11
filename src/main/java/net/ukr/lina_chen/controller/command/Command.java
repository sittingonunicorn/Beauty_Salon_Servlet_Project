
package net.ukr.lina_chen.controller.command;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    String execute(HttpServletRequest request);
}
