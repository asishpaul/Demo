package org.cap.service;

import java.util.ArrayList;
import java.util.List;

import org.cap.dao.ILoginDAO;
import org.cap.dao.LoginDAOImpl;
import org.cap.model.LoginBean;
import org.cap.model.PassRequestForm;
import org.cap.model.Routetable;

public class LoginServiceImpl implements ILoginService{

	ILoginDAO loginDAO=new LoginDAOImpl();
	
	@Override
	public boolean checkUser(LoginBean loginBean) {
		if(loginDAO.checkUser(loginBean)) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public PassRequestForm createRequest(PassRequestForm busBean) {
		if(loginDAO.createRequest(busBean) != null)
			return busBean;
      return null;
	}

	@Override
	public LoginBean signupRequest(LoginBean loginBean) {
		if(loginDAO.createRequest(loginBean) != null)
			return loginBean;
      return null;
	}

	@Override
	public List<Routetable> listAllRoutes() {
		List<Routetable> routeList=new ArrayList<>();
		routeList = loginDAO.listAllRoutes();
		return routeList;
	}

	@Override
	public Routetable addRoute(Routetable routeBean) {
		if(loginDAO.addRoute(routeBean) != null)
			return routeBean;
      return null;
	}

	@Override
	public List<LoginBean> listAdmin() {
		
		List<LoginBean> adminList=new ArrayList<>();
		adminList=loginDAO.listAdmin();
		return adminList;
	}

}
