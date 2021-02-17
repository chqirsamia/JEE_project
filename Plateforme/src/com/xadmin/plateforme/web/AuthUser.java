package com.xadmin.plateforme.web;
import com.xadmin.plateforme.bean.User;
import com.xadmin.plateforme.dao.DaoFactory;
import com.xadmin.plateforme.dao.interfaces.UserDao;
import com.xadmin.plateforme.forms.UserAuthForm;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
@WebServlet("/authentification")
public class AuthUser extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private UserDao userDao=null;

    private static final String ATT_SESSION_USER = "userSession";
    private static final String ATT_SESSION_USERID = "userId";

    private static final String ATT_FORM = "form";

    private static final String VUE_AUTHENTIFICATION = "/WEB-INF/authentification.jsp";
    private static final String VUE_USER_ACCUEIL = "/user";
    private static final String VUE_ADMIN_ACCUEIL = "Admin_acceuil.jsp";

    @Override
    public void init() throws ServletException {
    	DaoFactory daoFactory = null;
		try {
			daoFactory = DaoFactory.getInstance();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        this.userDao = daoFactory.getUserDao();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(VUE_AUTHENTIFICATION).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserAuthForm form = new UserAuthForm(userDao);
        User user = form.authentification(req);

        req.setAttribute(ATT_FORM,form);

        if(form.getErreurs().isEmpty()){
            HttpSession session = req.getSession();
            session.setAttribute(ATT_SESSION_USERID,user.getId());
            session.setAttribute(ATT_SESSION_USER,user);
           
            if(user.getRole()=="client") resp.sendRedirect(VUE_USER_ACCUEIL);
            else resp.sendRedirect(VUE_ADMIN_ACCUEIL);
        } else {
            this.getServletContext().getRequestDispatcher(VUE_AUTHENTIFICATION).forward(req, resp);
        }
    }
}