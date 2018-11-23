package org.cap.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.cap.model.LoginBean;
import org.cap.model.PassRequestForm;
import org.cap.model.Routetable;

public class LoginDAOImpl implements ILoginDAO{

	@Override
	public boolean checkUser(LoginBean loginBean) {

		String sql="select * from adminlogin where username=? and userpassword=?";

		try(PreparedStatement ps =getSQLConnection().prepareStatement(sql);
				)
		{

			ps.setString(1, loginBean.getUsername());
			ps.setString(2, loginBean.getPassword());
			ResultSet rs =ps.executeQuery();
			if(rs.next()) {
				return true;

			}

		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	private Connection getSQLConnection()
	{
		Connection con=null;
		try{


			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection
					("jdbc:mysql://localhost:3306/capdb","root","India@123");
			return con;
		}catch(SQLException e)
		{
			e.printStackTrace();
		}catch(Exception e1) {
			e1.printStackTrace();
		}

		return con;
	}

	@Override
	public PassRequestForm createRequest(PassRequestForm passRequestBean) {

		String sql="insert into BusPassRequest(EmployeeId,firstname,lastname,gender,address,email,dateofjoin,location,pickuploc,pickuptime,status,designation)values(?,?,?,?,?,?,?,?,?,?,?,?)";
		try(PreparedStatement pst = getSQLConnection().prepareStatement(sql);){
			pst.setString(1, passRequestBean.getEmployeeid());
			pst.setString(2, passRequestBean.getFirstname());
			pst.setString(3, passRequestBean.getLastname());
			pst.setString(4, passRequestBean.getGender());
			pst.setString(5, passRequestBean.getAddress());
			pst.setString(6, passRequestBean.getEmail());
			pst.setDate(7, Date.valueOf(passRequestBean.getDoj()));
			pst.setString(8, passRequestBean.getLocation());
			pst.setString(9, passRequestBean.getPickUpLoc());
			pst.setTime(10, Time.valueOf(passRequestBean.getPickUpTime()));

			pst.setString(11, passRequestBean.getStatus());
			pst.setString(12, passRequestBean.getDesignation());

			int count=pst.executeUpdate();
			if(count>0) {
				return passRequestBean;
			}

		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public LoginBean createRequest(LoginBean loginBean) {

		String sql="insert into adminlogin values(?,?)";
		try(PreparedStatement pst = getSQLConnection().prepareStatement(sql);){

			pst.setString(1, loginBean.getUsername());
			pst.setString(2, loginBean.getPassword());

			int n=pst.executeUpdate();
			if(n>0) {
				return loginBean;
			}	

		} 
		catch (SQLException e) {

			e.printStackTrace();
		}


		return null;
	}

	@Override
	public List<Routetable> listAllRoutes() {
		String sql="select * from route";
		int routeCount=0;
		try(

				Statement statement=getSQLConnection().createStatement();

				){

			ResultSet resultSet=statement.executeQuery(sql);
			List<Routetable> routeList=new ArrayList<>();

			while(resultSet.next()){

				routeCount++;
				Routetable route=new Routetable();
				populateRoute(route,resultSet);

				routeList.add(route);

			}
			if(routeCount>0){
				return routeList;
			}else{
				return null;
			}

		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}

	private void populateRoute(Routetable route, ResultSet resultSet) throws SQLException {

		route.setRoute_id(resultSet.getInt(1));
		route.setRoute_path(resultSet.getString(2));
		route.setNo_of_seats_occupied(resultSet.getInt(3));
		route.setTotal_seats(resultSet.getInt(4));
		route.setBus_no(resultSet.getString(5));
		route.setDriver_name(resultSet.getString(6));
		route.setTotal_km(resultSet.getDouble(7));


	}

	@Override
	public Routetable addRoute(Routetable newroute) {
		String sql="insert into route(route_path,no_of_seats,total_seats,bus_no,driver_name,total_km) values(?,?,?,?,?,?)";
		try(PreparedStatement pst = getSQLConnection().prepareStatement(sql);){
			pst.setString(1, newroute.getRoute_path());
			pst.setInt(2, newroute.getNo_of_seats_occupied());
			pst.setInt(3, newroute.getTotal_seats());
			pst.setString(4, newroute.getBus_no());
			pst.setString(5, newroute.getDriver_name());
			pst.setDouble(6, newroute.getTotal_km());

			int n=pst.executeUpdate();
			System.out.println(n);
			if(n>0) {
				System.out.println(n);
				return newroute;
			}
			else {
				return null;
			}



		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<LoginBean> listAdmin() {

		String sql="select * from adminlogin";
		int adminCount=0;
		try(

				Statement statement=getSQLConnection().createStatement();

				){
			ResultSet resultSet=statement.executeQuery(sql);
			List<LoginBean> adminList=new ArrayList<>();
			
			while(resultSet.next()) {
				
				adminCount++;
				LoginBean admin=new LoginBean();
				populateAdmin(admin,resultSet);

				adminList.add(admin);
			}
			if(adminCount!=0) {
				return adminList;
			}
			else {
				return null;
			}

		} 
		catch (SQLException e) {

			e.printStackTrace();
		}

		return null;
	}

	private void populateAdmin(LoginBean admin, ResultSet resultSet) throws SQLException {
		
		admin.setUsername(resultSet.getString(1));
		admin.setPassword(resultSet.getString(2));
	}

}
