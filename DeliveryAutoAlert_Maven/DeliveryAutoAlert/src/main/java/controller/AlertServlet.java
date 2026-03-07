package controller;
import DTO.alert.*;
import service.AlertService;
import service.UserService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/AlertServlet")
public class AlertServlet extends HttpServlet {
    private final AlertService service = new AlertService();
    private final UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action"); if (action == null) action = "list";
        try {
            switch (action) {
                case "detail":
                    req.setAttribute("alert", service.getEventById(Integer.parseInt(req.getParameter("id"))));
                    req.getRequestDispatcher("/alert/alertDetail.jsp").forward(req, resp); break;
                case "rules":
                    req.setAttribute("rules", service.getAllRules());
                    req.getRequestDispatcher("/alert/alertRules.jsp").forward(req, resp); break;
                case "cases":
                    req.setAttribute("cases", service.getAllCases());
                    req.setAttribute("users", userService.getAll());
                    req.getRequestDispatcher("/alert/caseList.jsp").forward(req, resp); break;
                case "caseDetail":
                    int caseId = Integer.parseInt(req.getParameter("id"));
                    req.setAttribute("case", service.getCaseById(caseId));
                    req.setAttribute("actions", service.getActionsByCase(caseId));
                    req.setAttribute("users", userService.getAll());
                    req.getRequestDispatcher("/alert/caseDetail.jsp").forward(req, resp); break;
                default:
                    req.setAttribute("alerts", service.getAllEvents());
                    req.getRequestDispatcher("/alert/alertsList.jsp").forward(req, resp);
            }
        } catch (Exception e) { throw new ServletException(e); }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String type = req.getParameter("type");
        try {
            if ("case".equals(type)) {
                String caseIdStr = req.getParameter("caseId");
                int caseId = (caseIdStr != null && !caseIdStr.isEmpty()) ? Integer.parseInt(caseIdStr) : 0;
                ReconciliationCaseDTO c = new ReconciliationCaseDTO();
                c.setCaseId(caseId);
                c.setAlertId(Integer.parseInt(req.getParameter("alertId")));
                c.setCaseStatus(req.getParameter("caseStatus"));
                c.setAssignedTo(Integer.parseInt(req.getParameter("assignedTo")));
                c.setResolutionNote(req.getParameter("resolutionNote"));
                service.saveCase(c);
                resp.sendRedirect(req.getContextPath() + "/AlertServlet?action=cases");
            } else if ("action".equals(type)) {
                AlertActionDTO a = new AlertActionDTO();
                a.setCaseId(Integer.parseInt(req.getParameter("caseId")));
                HttpSession session = req.getSession();
                DTO.master.UserDTO user = (DTO.master.UserDTO) session.getAttribute("user");
                a.setActionBy(user != null ? user.getUserID() : 0);
                a.setActionNote(req.getParameter("actionNote"));
                service.addAction(a);
                resp.sendRedirect(req.getContextPath() + "/AlertServlet?action=caseDetail&id=" + req.getParameter("caseId"));
            } else {
                AlertRuleDTO r = new AlertRuleDTO();
                r.setRuleName(req.getParameter("ruleName"));
                r.setRuleType(req.getParameter("ruleType"));
                r.setThresholdValue(req.getParameter("thresholdValue") != null && !req.getParameter("thresholdValue").isEmpty() ? new java.math.BigDecimal(req.getParameter("thresholdValue")) : java.math.BigDecimal.ZERO);
                r.setSeverity(req.getParameter("severity"));
                service.saveRule(r);
                resp.sendRedirect(req.getContextPath() + "/AlertServlet?action=rules");
            }
        } catch (Exception e) { throw new ServletException(e); }
    }
}
