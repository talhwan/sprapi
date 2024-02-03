/* ajax 관련 */
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
			if(data.status === 401){
				//access token 만료
				if(_data.retry == null || _data.retry == false){
					//access token 만료되었을때 다시 시도
					//alert("access token expired!");
					_data.retry = true;
					access_token(_data);
				} else {
					alert("login");
					location.replace("/tbuser/snslogin");
				}
			} else if(data.status === 403){
				// 권한이 없음.
				alert("no access auth.");
			} else if(data.status === 406){
				//refresh token 만료
				alert("please login");
				location.replace("/tbuser/snslogin");
			} else if(data.status === 409){
				alert("중복된 정보입니다. 다시 시도해주세요.");
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
let limit_each_file_size = 10;
function readURLFile(input, listener_after_upload) {
	let temp_id = $(input).attr("id") + "";
	if (input.files && input.files[0]) {
		let reader = new FileReader();
		reader.readAsDataURL(input.files[0]);
		reader.onload = function(e) {
			let temp_each_file_size = input.files[0].size / (1024 * 1024);
			if (temp_each_file_size > limit_each_file_size) {
				alert("파일 1개 당 " + limit_each_file_size + "mb 용량 제한 초과 입니다!");
				$(input).val("");
				return false;
			}
			let file_type = "image";
			if (!input.files[0].type.match('image.*')) {
				file_type = "file";
			} else {
				file_type = "image";
			}
			listener_upload_file(input.files[0], file_type, listener_after_upload);
		}
	}
}
function listener_upload_file(obj_file, file_type, listener_after_upload) {
	let fileData = new FormData();
	fileData.append("file", obj_file);

	$.ajax({
		url: "/api/default/uploadFile",
		type: "POST",
		data: fileData ,
		cache : false,
		contentType : false,
		processData : false,
		success: (data, status, xhr)=>{
			//alert(xhr.status);
			switch(xhr.status){
				case 201:
					//alert(data);
					listener_after_upload(file_type, data);
					break;
				default:
					console.log("no matching status code");
			}
		},
		error: (data)=>{
			alert("error")
		},
	});
}

/* list관련 기능 */
function listenerAfterList(){
	let font_order = $(".font_order");
	for (let t = 0; t < font_order.length; t++) {
		$(font_order[t]).text((t+1));
	}

	let select_search_keyword = $(".select_search_keyword");
	for (let t = 0; t < select_search_keyword.length; t++) {
		let select_temp_name = $(select_search_keyword[t]).attr("keyword");
		let option_temps = $(select_search_keyword[t]).find("option");
		for(let i=0;i<option_temps.length;i++){
			let a_html = $(option_temps[i]).html();
			let a_value = $(option_temps[i]).attr("value");
			$(".font_"+ select_temp_name +"_" + a_value).html(a_html);
		}
	}
}
/* js 추가 기능 */
function isNull(x) {
	let result_x = false;
	x = x + "";
	if(x == null || x == "null" || x == "" || x == "undefined"){
		result_x = true;
	} else {
	}
	return result_x;
}
function number2digit(x) {
	var return_val = x + "";
	if(Number(x) < 10){
		return_val = "0" + return_val;
	}
	return return_val;
}

function getNextDay(date_string, days) {
	let date = null;
	if(date_string == null){
		date = new Date();
	} else {
		date = new Date(date_string);
	}
	if(days == null){
		days = 1;
	}
	date.setDate(date.getDate() + days);
	let d = date.getDate();
	let m = date.getMonth();
	let y = date.getFullYear();
	let temp_today = y + "-" + number2digit(m+1) + "-" + number2digit(d) + "";
	return temp_today;
}
function dateToStringFormat(date) {
	if(date == null){
		date = new Date();
	}
	let d = date.getDate();
	let m = date.getMonth();
	let y = date.getFullYear();
	let temp_today = y + "-" + number2digit(m+1) + "-" + number2digit(d) + "";
	return temp_today;
}
function getIdFromUrl(obj){
	let temp_url_with_idx = obj;
	if(obj == null){
		temp_url_with_idx = window.location.href;
	}
	let split_slash_temp_idx = temp_url_with_idx.split('/');
	let temp_idx = split_slash_temp_idx[split_slash_temp_idx.length - 1];
	let split_question_temp_idx = temp_idx.split('?');
	if(split_question_temp_idx.length > 0){
		temp_idx = split_question_temp_idx[0];
	}
	return temp_idx;
}