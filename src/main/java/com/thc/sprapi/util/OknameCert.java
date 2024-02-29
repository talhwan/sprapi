package com.thc.sprapi.util;

import java.util.Map;

import com.thc.sprapi.dto.TbuserDto;
import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kcb.org.json.JSONObject;
/*
 * 1. 객체생성 (HttpServletRequest request, String siteName, String siteURL, String returnURL)
 * 
 * 		OknameCert oc = new OknameCert(request, "아이센스", "http://localhost:8080", "http://"+request.getServerName()+":8080/popup3");
 * 
 * 2. 토큰 발급받아서 view페이지로.(oc.getToken 호출 하면 map에 같이 들어오는 CP_CD, POPUP_URL도 함께)
 *   
 * 		Map<String, Object> map = oc.getToken();
 *  
 * 3. view페이지에서  request()함수 호출하면 본인인증 페이지로 넘어감
 * 
 * 		<form name="form1">
 *			<input type="hidden" name="tc" value="kcb.oknm.online.safehscert.popup.cmd.P931_CertChoiceCmd"/>	<!-- 변경불가-->
 *			<input type="hidden" name="cp_cd" value="${CP_CD}">													<!-- 회원사코드 -->
 *			<input type="hidden" name="mdl_tkn" value="${MDL_TKN}">												<!-- 토큰 --> 
 *			<input type="hidden" name="target_id" value="">	 
 *		</form>
 *	
 *		function request(){
 *			window.open("", "auth_popup", "width=430,height=640,scrollbar=yes");
 *			var form1 = document.form1;   
 *			form1.target = "auth_popup";
 *			form1.action = "${popupURL}";
 *			form1.method = "post";
 *			form1.submit();
 *		}	
 *
 * 4. 본인인증이 끝나면 returnURL로 페이지 이동되는데 파라미터로 붙는 토큰 getResult 파라미터로 전달해서 result return받기.  
 * 
 */
public class OknameCert {

	private static final Logger logger = LoggerFactory.getLogger(OknameCert.class);
	
	public static String CP_CD     	  	= "V44390000000";
	public static String svcName_start  = "IDS_HS_POPUP_START";  
	public static String svcName_result = "IDS_HS_POPUP_RESULT";  
	public static String POPUP_URL 	  	= "https://safe.ok-name.co.kr/CommonSvl";
	public static String target       	= "PROD"; 	
	public static String RQST_CAUS_CD 	= "00";
	public static String license      	= "";  
	public static String SITE_NAME    	= "";
	public static String SITE_URL     	= "";
	public static String RETURN_URL   	= "";
	public static String resultStr    	= "";  
	
	
	public OknameCert(String rootpath, String siteName, String siteURL, String returnURL) throws Exception{
		SITE_NAME = siteName;
		SITE_URL = siteURL;
		RETURN_URL = returnURL;

		license = "C:/workspace/uploadfiles/"
				+ CP_CD + "_IDS_01_" + target + "_AES_license.dat";
		/*
		license = rootpath +
						 "resources/common/okcert/"
				+ CP_CD + "_IDS_01_" + target + "_AES_license.dat";

		license = request.getSession()
				 .getServletContext()
				 .getRealPath(
						 "/resources/common/okcert/"+ CP_CD + "_IDS_01_" + target + "_AES_license.dat"
				 );
		*/
	}
	
	public TbuserDto.TbuserOkcertTokenDto getToken() throws Exception{
		JSONObject reqJson = new JSONObject();
		reqJson.put("RETURN_URL", RETURN_URL);
		reqJson.put("SITE_NAME", SITE_NAME);
		reqJson.put("SITE_URL", SITE_URL);
		reqJson.put("RQST_CAUS_CD", RQST_CAUS_CD);

		logger.info("reqJson : " + reqJson);
		logger.info("license : " + license);

		String reqStr = reqJson.toString();
		kcb.module.v3.OkCert okcert = new kcb.module.v3.OkCert();
		logger.info("1. okcert : " + okcert);

		resultStr = okcert.callOkCert(target, CP_CD, svcName_start, license, reqStr);
		logger.info("2. resultStr : " + resultStr);

		JSONObject resJson = new JSONObject(resultStr);
		logger.info("3. resJson : " + resJson);

		TbuserDto.TbuserOkcertTokenDto result =
		new TbuserDto.TbuserOkcertTokenDto(
				resJson.getString("RSLT_CD")
				,resJson.getString("RSLT_MSG")
				,CP_CD
				,POPUP_URL
				,""
		);
		/*
		map.put("RSLT_CD", resJson.getString("RSLT_CD"));
		map.put("RSLT_MSG", resJson.getString("RSLT_MSG"));
		map.put("CP_CD", CP_CD);
		map.put("POPUP_URL", POPUP_URL);
		 */
	    if ("B000".equals(resJson.getString("RSLT_CD")) && resJson.has("MDL_TKN") ) {
	    	//map.put("MDL_TKN", resJson.getString("MDL_TKN"));
			result.setMDL_TKN(resJson.getString("MDL_TKN"));
	    }
	    logger.info("result : " + result);
	    return result;
	}
	
	public Map<String, Object> getResult(Map<String, Object> map) throws Exception{
		
		JSONObject reqJson = new JSONObject();
		
		String MDL_TKN = (String)map.get("mdl_tkn");    
		reqJson.put("MDL_TKN", MDL_TKN);
		
		String reqStr = reqJson.toString();
		
		kcb.module.v3.OkCert okcert = new kcb.module.v3.OkCert();
		
		String resultStr = okcert.callOkCert(target, CP_CD, svcName_result, license,  reqStr);
		JSONObject resJson = new JSONObject(resultStr);
		
		map.put("RSLT_CD", 	 resJson.getString("RSLT_CD"));
		map.put("RSLT_MSG",  resJson.getString("RSLT_MSG"));
		map.put("TX_SEQ_NO", resJson.getString("TX_SEQ_NO"));
		
		if(resJson.has("RETURN_MSG")) {
			map.put("RETURN_MSG", resJson.getString("RETURN_MSG"));
		}
		
		if ("B000".equals(resJson.getString("RSLT_CD"))){
			map.put("RSLT_NAME",        resJson.getString("RSLT_NAME"));
			map.put("RSLT_BIRTHDAY",    resJson.getString("RSLT_BIRTHDAY"));
			map.put("RSLT_SEX_CD",      resJson.getString("RSLT_SEX_CD"));
			map.put("RSLT_NTV_FRNR_CD", resJson.getString("RSLT_NTV_FRNR_CD"));
			map.put("DI", 				resJson.getString("DI"));
			map.put("CI", 				resJson.getString("CI"));
			map.put("CI_UPDATE", 		resJson.getString("CI_UPDATE"));
			map.put("TEL_COM_CD", 		resJson.getString("TEL_COM_CD"));
			map.put("TEL_NO", 			resJson.getString("TEL_NO"));
		}
		
		return map;
	}
}
