function func_ajax(_data) {
	$.ajax({
		url: _data.url,
		type: _data.type,
		beforeSend: function (xhr) {
			xhr.setRequestHeader("Content-type", "application/json");
			xhr.setRequestHeader("Authorization", localStorage.getItem("accessToken"));
		},
		contentType : 'application/json; charset=utf-8',
		data: JSON.stringify(_data.param),
		cache: false,
		success: (data, status, xhr)=>{
			// success
			if (xhr.status >= 200 && xhr.status < 300) {
				_data.success(data, status, xhr);
			} else {
				alert("error occured. try again");
			}
		},
		error: (data, status, xhr)=>{
			// error
			if(data.status == 401){
				if(_data.retry == null || _data.retry == false){
					//access token 만료되었을때 다시 시도
					alert("access token expired!");
					_data.retry = true;
					access_token(_data);
				} else {
					alert("login");
					location.replace("/tbuser/snslogin");
				}
			} else if(data.status == 403){
				//
				alert("no access auth.");
				location.replace("/tbuser/snslogin");
			} else {
				_data.error(data, status, xhr);
			}
		},
	});
}
function access_token(_data){
	$.ajax({
		url: "/api/auth/access-token",
		method: "POST",
		beforeSend: function (xhr) {
			xhr.setRequestHeader("Content-type", "application/json");
			xhr.setRequestHeader("RefreshToken", localStorage.getItem("refreshToken"));
		},
		cache: false,
		success: (data, status, xhr)=>{
			switch(xhr.status){
				case 200:
					let accessToken = xhr.getResponseHeader("Authorization");
					localStorage.setItem("accessToken", accessToken);
					func_ajax(_data);
					break;
				default:
					console.log("no matching status code");
			}
		},
		error: (data, status, xhr)=>{
			alert(JSON.stringify(data));
			switch(data.status){
				case 401:
					alert("expired refresh token. login please");
					break;
			}
		},
	});
}