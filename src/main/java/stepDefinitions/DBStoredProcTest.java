package stepDefinitions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DBStoredProcTest {
	public String url="34.134.229.85";
	public String user="user";
	public String password="user1234";
	Connection con;
	Statement st;
	ResultSet rs;
	String query;
	@Given("^a database with stored procedures$")
	public void db_sp() throws SQLException {
		
		con=DriverManager.getConnection(url,user,password);
	}
	@When("^making a request against a stored procedure$")
	public void request_SP() throws SQLException {
		st=con.prepareCall("{CALL GetOfficeByCountry('USA'}");
		query="select * from customer where country='USA'";
		rs=st.executeQuery(query);
	}
	
	@Then("^I will get data back$")
	public void getData() throws SQLException {
		while(rs.next()) {
			String data=rs.getString(0);
			System.out.println(data);
		}
	}
}
