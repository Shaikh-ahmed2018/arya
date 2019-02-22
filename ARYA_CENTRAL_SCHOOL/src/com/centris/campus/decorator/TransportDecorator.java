package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;

import com.centris.campus.vo.TransportVo;

public class TransportDecorator extends TableDecorator {

	String stop;

	String check;

	public String getCheck() {

		String chkbx = "";

		TransportVo detils = (TransportVo) getCurrentRowObject();
		chkbx = "<input type=\"checkbox\" class=\"Checkbox_Class\"  id=\"CheckBox_"
				+ detils.getRouteCode()
				+ ","
				+ detils.getRouteName()
				+ ","
				+ "\" />";

		return chkbx;

	}

	public String getStop() {
		TransportVo detils = (TransportVo) getCurrentRowObject();
		String totalStop = detils.getTotalStops();
		String routeCode = detils.getRouteCode();
		String halttime = detils.getHalttime();
		

		/*stop = "<input type='button' class='btn btn-success'  value=Update  id='updateStop' onclick='insertStop(\""
				+ totalStop + "\"," + "\"" + routeCode + "\")' />";
		
		
		stop = "<a href ='adminMenu.html?method=routeMasterSettings&stopdetails='"+ totalStop +","+ routeCode","">click</a>";*/

		stop = "<a  href=\"transport.html?method=stopDetailsScreen&stopdetails="
				+ totalStop+","+routeCode+","+halttime + "\"><b>Update Stops</b></>";
		
		return stop;

	}
}
