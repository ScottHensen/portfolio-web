$.support.cors = true

$(document).ready(function() {
	
	//events
	$(document).on('click', '.deleteSymbolButton', function() {
		var $this = $(this)
		var params = $this.attr("param").split(",")
		if (params) {
			var postParams = {"clientId":params[0],"portfolioId":params[1],"symbol":params[2]}
		}
		
		//TODO: clean this up; replace it with $.post?
		$.ajax({
			 type:		"POST"
			,url:		"/deleteSymbolFromPortfolio"
			,headers: 	{
							'Accept': 		'application/json',
							'Content-Type': 'application/json'
						}
			,data:		JSON.stringify(postParams)
			,cache:		false
			,success:	function(data) {
							alert("yeah!")
						}
			,error:		function(data) {
							alert("boo!")
						}
		})
	})
})