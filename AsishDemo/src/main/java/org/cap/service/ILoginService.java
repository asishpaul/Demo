package org.cap.service;

import java.util.List;

import org.cap.model.LoginBean;
import org.cap.model.PassRequestForm;
import org.cap.model.Routetable;

public interface ILoginService {

	boolean checkUser(LoginBean loginBean);

	PassRequestForm createRequest(PassRequestForm busBean);

	LoginBean signupRequest(LoginBean loginBean);

	List<Routetable> listAllRoutes();

	Routetable addRoute(Routetable routeBean);

	List<LoginBean> listAdmin();

}
