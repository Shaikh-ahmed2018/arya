<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>

<link href="CSS/newUI/custome.css" rel="stylesheet">
<title>eCampus Pro</title>
<body>
	<div class="col-md-2 leftmenu">

		<div class="panel panel-primary"
			style="border: none; background-color: transparent; margin: 1px 0px; box-shadow: none;">
			<div class="panel-heading" style="background-color: #07AAB9;">
				<a data-toggle="collapse" data-parent="#accordion2"
					href="#menucollapseOne" style="color: #fff;"><h3
						class="panel-title active">
						Student Management<i class="glyphicon glyphicon-triangle-bottom"
							style="float: right;"></i>
					</h3></a>
			</div>
			<div id="menucollapseOne" class="accordion-body collapse in">
				<div class="panel-body">
					<ul class="nav nav-pills nav-stacked">

						<li><a href="TestAction.html?method=studentList">Student
												Registration</a></li>
									
					</ul>

				</div>
				<br />
			</div>
		</div>


	</div>
</body>
</html>