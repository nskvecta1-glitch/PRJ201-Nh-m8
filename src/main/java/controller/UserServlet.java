package controller;
import DTO.master.UserDTO;
import service.UserService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
    private final UserService service = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action"); if (action == null) action = "list";
        try {
            req.setAttribute("roles", service.getAllRoles());
            switch (action) {
                case "edit":
                    req.setAttribute("userEdit", service.getById(Integer.parseInt(req.getParameter("id"))));
                    req.getRequestDispatcher("/auth/userForm.jsp").forward(req, resp); break;
                case "delete":
                    service.delete(Integer.parseInt(req.getParameter("id")));
                    resp.sendRedirect(req.getContextPath() + "/UserServlet"); break;
                case "new":
                    req.getRequestDispatcher("/auth/userForm.jsp").forward(req, resp); break;
                default:
                    req.setAttribute("users", service.getAll());
                    req.getRequestDispatcher("/auth/userList.jsp").forward(req, resp);
            }
        } catch (Exception e) { throw new ServletException(e); }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("userId");
        int id = (idStr != null && !idStr.isEmpty()) ? Integer.parseInt(idStr) : 0;
        UserDTO u = new UserDTO(id, req.getParameter("username"), req.getParameter("password"),
            req.getParameter("fullname"), req.getParameter("email"),
            Integer.parseInt(req.getParameter("roleId")), true, LocalDateTime.now());
        try { service.save(u); resp.sendRedirect(req.getContextPath() + "/UserServlet");
        } catch (Exception e) { throw new ServletException(e); }
    }
}
