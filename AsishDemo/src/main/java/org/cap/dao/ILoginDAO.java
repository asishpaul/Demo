package org.cap.dao;

import java.util.List;

import org.cap.model.LoginBean;
import org.cap.model.PassRequestForm;
import org.cap.model.Routetable;

public interface ILoginDAO {

	boolean checkUser(LoginBean loginBean);

		PassRequestForm createRequest(PassRequestForm busBean);

		LoginBean createRequest(LoginBean loginBean);

		List<Routetable> listAllRoutes();

		Routetable addRoute(Routetable routeBean);

		List<LoginBean> listAdmin();

}
