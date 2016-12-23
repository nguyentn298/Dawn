<head>
<%@ include file="/WEB-INF/decorators/include/taglibs.jsp"%>
	<style>
	div#main {
		padding: 50px;
	    text-align: center;
	    background-color: #e5eecc;
	    border: solid 1px #c3c3c3;
	}
	
	table#test-checkbox, 
	table#test-checkbox th, 
	table#test-checkbox td {
	    border: 1px solid black;
	    border-collapse: collapse;
		width:20%;
	}
	
	tr:hover {
		background-color: gray;
	}
	
	form {
		width: 40%;
		text-align: left;
		
	}
	
	label {
		width:90px;
		display: inline-block;
	}
	
	label.error {
		float:none !important;
		width: 150px !important;
		padding-right: 5px;
		color: red;
	}
	
	
	.items {
		margin-top: 5px;
	}
	
	</style>
	<script>
	$(document).ready(function(){
		
		// =============================================================================
		// Event: remove tr
		$(".bt1").click(function(){
		 
			var checkLenght = $("#test-checkbox tr td input[type=checkbox]:checked").length;
			if(checkLenght == 0) {
				alert("Please select row to remove.");
				return;
			}
			
		
			$(".tb1 tr:not(:first)").each(function(){
				var $currentRow = $(this);
				var $chk = $currentRow.find(":checkbox");
				if ($chk.is(":checked")) {
						$currentRow.remove();
				}
			})
			$(".tb1 input[type=checkbox]:first").prop("checked", false);
			$(".check-text").html("No selected after remove!")
			
		});
		// =============================================================================
		// Event: select all check box
		$(".all-checkbox").change(function(){
			if($("#all-checkbox input[type=checkbox]").prop("checked")){
				$(".tb1 input[type=checkbox]:not(:first)").prop("checked", true);
				$(".check-text").html("All Selected");
			} else {
				$(".tb1 input[type=checkbox]:not(:first)").prop("checked", false);
				$(".check-text").html("No selected after select all")
			}
			
		})
		
		// =============================================================================
		// Event: Show text when click
		var countCheck = function() {
			var n = $(".tb1 tr input[type=checkbox]:not(:first):checked").length;
			if($(".tb1 tr input[type=checkbox]:not(:first), .tb1 tr").is(":checked") && n > 0) {
				
				$(".check-text").html(n + (n == 1 ? " row is " : " rows are ") + "checked by click checkbox");
			} else (
				$(".check-text").html("Have fun")
			)
		}
		
		
		$(".tb1 tr input[type=checkbox]:not(:first)").change(countCheck);
	
		
		// =============================================================================
		// Event: Checked when click tr
		/*	$(".tb1 tr:not(:first)").click(function(){
	
				if($(this).find(":checkbox").prop("checked")) {
					$(this).find(":checkbox").prop("checked", false);
				} else {
					$(this).find(":checkbox").prop("checked", true);
				}		 
			}) */
	
		
	
	
		
		/*$("div.test:not(:first)").hide();
		or:
	
		$("div.test:not(:eq(0))").hide();
		or:
	
		$("div.test").not(":eq(0)").hide();
		or:
	
		$("div.test:gt(0)").hide();
		or:
	
		$("div.test").gt(0).hide();
		or: 
	
		$("div.test").slice(1).hide();*/
		
		// =============================================================================
		// Event: attr (remember preventDefault)
		$(".show-hidden").click(function(e){
			e.preventDefault();
			$("input[type=hidden]").attr("type", "text").focus();
		})
		
			// =============================================================================
		// Event: validation form
		$(".form-valid").click(function(e){
		
			var checkValid = $("#myForm").valid();
			if($("#myForm").valid()) {
				alert("Status: " + checkValid);
			} else {
				$("input.error:first").focus();
				 
			}
			
			var errorLog = $("#error-message").show().html("").css("color", "red");
			var listError = [];
			
			//var validString = /^[a-zA-z0-9_-]+@[a-zA-z0-9]+.[a-zA-Z]{2,4}$/;
			var validString = /^[a-z]+$/;
			var $name = $(".name").val();
			if(!validString.test($(".name").val())) {
				//listError.push("<b>Your name must be string!!!</b>");
				alert("wrong name!!");
			}
			
			var validDigit= /\d+$/;
			var $age = $(".age").val();
			if(!validDigit.test($age) && $age.length > 0) {
				listError.push("<br><b>Your age must be digit!!!</b>");	
				alert("wrong age!!");
			} 
			
			/*var rege = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
			var $email = $(".email").val();
			if(!rege.test($email)){ 
				alert("Your email is invald");
			 } */
			
			errorLog.append(listError);
		});

		// =============================================================================
		// Event: Select option
		$(".alert-selected").click(function(){
			var multiLength = $("#multi_country option:selected").length;
			var valueOfMulti = $("#multi_country");
			
			alert("Single select: " + $("#country option:selected").val() 
					+ "<br> multi select: " 
					+ ((multiLength > 0) ? valueOfMulti.val() : 0));

			
// 			for(i = 0; i < multiLength; i++) {
// 				valueOfMulti.eq(i);
// 				valueOfMulti.eq(i).val();
// 				valueOfMulti[i].value;
// 				$(".value-multi").append("index " + i + " = " + valueOfMulti.eq(i).val() + "<br>");
// 			}
			$(".value-single").append($("#country option:selected").val());
			$("#multi_country :selected").each(function(i, el) {
				$(".value-multi").append("i = " + i + " ==> " + $(el).val() + "<br>");
			});
			
		})
	
		
	});
	
	
	</script>
</head>
<body>
	<div>
		<h2>Table</h2>
		<div id="main">
			<table id="test-checkbox" class="tb1">
				<tbody>
					<tr id="all-checkbox">
						<th>Id</th>
						<th>Name</th>
						<th>Role</th>
						<th><input class="all-checkbox" type="checkbox" /></th>
					</tr>
					
					<tr>
						<td>1</td>
						<td>Lucian</td>
						<td>Bot</td>
						<td><input id="lucian" type="checkbox" /></td>
					</tr>
					<tr>
						<td>2</td>
						<td>Jayce</td>
						<td>Mid</td>
						<td><input type="checkbox" /></td>
					</tr>
					<tr>
						<td>3</td>
						<td>Garen</td>
						<td>Top</td>
						<td><input type="checkbox" /></td>
					</tr>
					<tr>
						<td>4</td>
						<td>Jarvan</td>
						<td>Jung</td>
						<td><input type="checkbox" checked /></td>
					</tr>
					
				</tbody>
			</table>
			<div>
				<p class="check-text" style="color: red; position: relative; width: 100%; text-align: left">Click check box</p>
	  			<button class="bt1" style="color: blue;float:left">Remove</button>
	  		</div>
			
		</div>
		<div id="main">
			<h2>Original Form</h2>
			<form id="myForm">
				<fieldset>
					<legend>Detail</legend>
					<div class="items" id="error-message" hidden></div>
					<div class="items">
						<label>Drop down list single select: </label>
						<select>
							<option>Zed</option>
							<option>Talon</option>
							<option>Akali</option>
							<option>Leblanc</option>
						</select><br>
					<div>
					<div class="items">
						<label>Drop down list multi select: </label>
						<select multiple>
							<option>Zed</option>
							<option>Talon</option>
							<option>Akali</option>
							<option>Leblanc</option>
						</select><br>
					<div>
					<div class="items">
						<label>Drop down list multi select and checkbox: </label>
						<select multiple>
							<option>Zed</option>
							<option>Talon</option>
							<option>Akali</option>
							<option>Leblanc</option>
						</select><br>
					<div>
					<div class="items">
						<label>Your Name: </label>
						<input type="text" placeholder="Just string" name="name" required class="name"></input><br><br>
						<label>Your age: </label>
						<input type="text" placeholder="Just number" name="age" class="age" required></input><br><br>
						<label>Your email: </label>
						<input type="text" placeholder="Email" name="email" class="email" required></input><br><br>
						<label>Text field 4: </label>
						<input type="text" placeholder="Not required" name="tele"></input><br><br>
					</div>
					<div class="items">	
						<label>Checkbox 1: </label>
						<input type="checkbox" name="difference"></input><br>
						<label>Checkbox 2: </label>
						<input type="checkbox" name="difference"></input><br>
						<label>Checkbox 3: </label>
						<input type="checkbox" name="difference"></input><br>
					</div>
					<div class="items">
						<label>Radio 1: </label>
						<input type="radio" name="same"></input><br>
						<label>Radio 2: </label>
						<input type="radio" name="same"></input><br>
						<label>Radio 3: </label>
						<input type="radio" name="same"></input><br>
					</div>
					<div class="items">
						<label>Text area field: </label>
						<input type="textarea"></input><br>
					</div>
					<div class="items">
						<label>Hidden field: </label>
						<input type="hidden" value="this is visible input"></input>
					</div><br>
					<button class="show-hidden">show hidden</button>
					<button class="form-valid" type="button">Validate!</button>
				</fieldset>
			</form>
		</div>
		<div id="main">
			<h2>Form with Spring</h2>
			<form id="myForm">
				<fieldset>
					<legend>Drop down list single select:</legend>
					<div class="items" id="error-message" hidden></div>
					<div class="items">
						<label style="width:250px;">Drop down list single select: </label>
						<select id="country" name="country" items="${countryList}">
							<c:forEach var="entry" items="${countryList}">
								<option value="${entry.key}">${entry.value}</option>
							</c:forEach>
						</select>
					<div>
					
					<div class="items">
						<label style="width:250px;">Drop down list multi select: </label>
						<select id="multi_country" name="country" items="${countryList}" multiple>
							<c:forEach var="entry" items="${countryList}">
								<option value="${entry.key}">${entry.value}</option>
							</c:forEach>
						</select><br>
					<div>

					<div class="items">
						<label style="width:250px;">Drop down list multi select and checkbox: </label>
						<select multiple>
							<option>Zed</option>
							<option>Talon</option>
							<option>Akali</option>
							<option>Leblanc</option>
						</select><br>
					<div>
					
					<div class="items">
						<label style="width:250px;">value of single selected: </label><br>
						<p class="value-single"></p>
					</div>
					<div class="items">
						<label style="width:250px;">value of multi selected: </label><br>
						<p class="value-multi"></p>
					</div>
					<div class="items">
						<label style="width:250px;">value of multi selected: </label><br>
						<p class="value-multi-add"></p>
					</div>
					<div class="items">
						<label> </label>
						<button class="alert-selected">Alert selected</button>
					</div>
					<div class="items">
						<label>Your Name: </label>
						<input type="text" placeholder="Just string" name="name" required class="name"></input><br><br>
						<label>Your age: </label>
						<input type="text" placeholder="Just number" name="age" class="age" required></input><br><br>
						<label>Your email: </label>
						<input type="text" placeholder="Email" name="email" class="email" required></input><br><br>
						<label>Text field 4: </label>
						<input type="text" placeholder="Not required" name="tele"></input><br><br>
					</div>
					<div class="items">	
						<label>Checkbox 1: </label>
						<input type="checkbox" name="difference"></input><br>
						<label>Checkbox 2: </label>
						<input type="checkbox" name="difference"></input><br>
						<label>Checkbox 3: </label>
						<input type="checkbox" name="difference"></input><br>
					</div>
					<div class="items">
						<label>Radio 1: </label>
						<input type="radio" name="same"></input><br>
						<label>Radio 2: </label>
						<input type="radio" name="same"></input><br>
						<label>Radio 3: </label>
						<input type="radio" name="same"></input><br>
					</div>
					<div class="items">
						<label>Text area field: </label>
						<input type="textarea"></input><br>
					</div>
					<div class="items">
						<label>Hidden field: </label>
						<input type="hidden" value="this is visible input"></input>
					</div><br>
					<button class="show-hidden">show hidden</button>
					<button class="form-valid" type="button">Validate!</button>
				</fieldset>
			</form>
		</div>
		<div id="main">
			<h2>Test JSTL</h2>
			<c:forEach var="entry" items="${countryList}">
				Key: <c:out value="${entry.key}"/>
				Value: <c:out value="${entry.value}"/>
			</c:forEach>
			<h2>Test JSTL 222</h2>
			<c:forEach items="${countryList}" var="entry">
				${entry.key} = 
				<c:forEach items="${entry.value}" var="value">
					${value}, 
				</c:forEach>
  					<br/>
			</c:forEach>
		</div>
	</div>
</body>