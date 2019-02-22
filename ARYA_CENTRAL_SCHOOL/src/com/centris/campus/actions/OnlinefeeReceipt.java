package com.centris.campus.actions;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import com.centris.campus.daoImpl.OnlineFeePaymentDaoImpl;
import com.centris.campus.delegate.ParentExamdetailsBD;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.FeeCollectionVo;
import com.centris.campus.vo.ParentVO;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.UserDetailVO;
import com.centris.campus.vo.feeReportVO;

public class OnlinefeeReceipt extends Action {
	private static final Logger logger = Logger.getLogger(OnlinefeeReceipt.class);
	public ActionForward execute(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response)
	        throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenu : onlinefeetransactionId Starting");
		
		UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
		String txnstatus="";
		String userType=userDetailVO.getUserNametype();
		String userCode=userDetailVO.getUserId();
		String BankRefNo="";
		String tranID=(String) request.getSession(false).getAttribute("transactionId");
		
		request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_PARENT_ONLINE);
		request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_TEACHERS);
		
		try{
			ArrayList<feeReportVO> feereceipt=new ArrayList<feeReportVO>();
			ArrayList<feeReportVO> tfeereceipt=new ArrayList<feeReportVO>();
			
			
			String studentId=(String) request.getSession(false).getAttribute("hstudentId");
			String feeCode=(String) request.getSession(false).getAttribute("hfeeCode");
			String termcode=(String) request.getSession(false).getAttribute("htermcode");
			String bank=(String) request.getSession(false).getAttribute("hbank");
			String grandtotal=(String) request.getSession(false).getAttribute("hgrandtotal");
			String fineAmt=(String) request.getSession(false).getAttribute("hfineAmt");
			
			String  bankcode=(String) request.getSession(false).getAttribute("hbankcode");
			
			
	if(bankcode.equalsIgnoreCase("KVB")) {
		
		BankRefNo=request.getParameter("BankRefNo");
		 String ClientCode=request.getParameter("ClientCode");
		 String MerchantCode=request.getParameter("MerchantCode");
		 String TxnCurrency=request.getParameter("TxnCurrency");
		String TxnAmount=request.getParameter("TxnAmount");
		 String TxnScAmount=request.getParameter("TxnScAmount");
		
		 String MerchRefNo=request.getParameter("MerchRefNo");
		String AckStaticFlag=request.getParameter("AckStaticFlag");
		String ResponseStaticFlag=request.getParameter("ResponseStaticFlag");
		 String date=request.getParameter("Date");
		 
		System.out.println("BankRefNo=="+BankRefNo);
		String inserrt = new OnlineFeePaymentDaoImpl().onlinefeetransactionId(studentId,feeCode,termcode,bank,tranID,grandtotal,BankRefNo,fineAmt);
	
		
		String POST_PARAMS="fldTxnId=VRF&fldClientCode="+ClientCode+"&fldClientAccount=&fldMerchCode="+MerchantCode+"&fldTxnCurr="+TxnCurrency+"&fldTxnAmt="+TxnAmount+"&fldTxnScAmt="+TxnScAmount+"&fldMerchRefNbr="+MerchRefNo+"&fldSucStatFlg="+AckStaticFlag+"&fldFailStatFlg="+ResponseStaticFlag+"&fldDatTimeTxn="+date+"&fldOrgDatTimeTxn="+date+"&fldBankRefNo="+BankRefNo;
		URL obj = new URL("http://14.142.112.23/GeneralMerchant/GeneralMRequest");
		HttpURLConnection  con = (HttpURLConnection) obj.openConnection();
		con.setReadTimeout(300000);
		con.setConnectTimeout(300000);
		con.setDoOutput(true);
		con.setRequestMethod("POST");
		
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(POST_PARAMS.toString());
		wr.flush();
		wr.close();
		
		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) { //success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			
			
			String inputLine;
			StringBuffer kresponse = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				kresponse.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(kresponse.toString().replaceAll("&lt;", "<").replaceAll("&gt;", ">"));
			
			
			
			try {
	            JSONObject xmlJSONObj = XML.toJSONObject(kresponse.toString().replaceAll("&lt;", "<").replaceAll("&gt;", ">"));
	            JSONObject fmal = (JSONObject) xmlJSONObj.get("faml");
	            JSONObject REDIRECTURL=(JSONObject) fmal.get("REDIRECTURL");
	            JSONObject VerifyOutput=(JSONObject) REDIRECTURL.get("VerifyOutput");
	            JSONObject Status=(JSONObject) VerifyOutput.get("Status");
	            if(Status.getString("VerifyStatus").equalsIgnoreCase("0")) {
	            	 txnstatus="SUCCESS";
	            			 
	            }
	            else {
	            	txnstatus="FAILURE";
	            }
	           
	        } catch (JSONException je) {
	            System.out.println(je.toString());
	        }
			
		} else {
			System.out.println("POST request not worked");
		}
	}		
			
	else if(bankcode.equalsIgnoreCase("FDB")) {
		
		String Tran_status=request.getParameter("Tran_status");
		String 	hash_value=request.getParameter("hash_value");
		String inserrt = new OnlineFeePaymentDaoImpl().onlinefeetransactionId(studentId,feeCode,termcode,bank,tranID,grandtotal,hash_value,fineAmt);
		BankRefNo=hash_value;
	if(Tran_status.equalsIgnoreCase("S")) {
		txnstatus="SUCCESS";
	}
	else {
		txnstatus="FAILURE";
	}
	}		
	    	
		
			
			String tokenid=new OnlineFeePaymentDaoImpl().getonlinefeetokenId(tranID);
			if(tokenid.equalsIgnoreCase("false")) {
				txnstatus="RELOAD";
			}
			else {
					
				String update=new OnlineFeePaymentDaoImpl().updateOnlineTable(BankRefNo,txnstatus,BankRefNo);
			
			if(txnstatus.equalsIgnoreCase("SUCCESS")) {
				List<FeeCollectionVo> status = new OnlineFeePaymentDaoImpl().saveonlinefeetransactionId(tranID,userCode);
				ParentVO stovo = new ParentVO();
				stovo.setParentID(userCode);
				stovo.setAccyear(HelperClass.getCurrentYearID());
				
				ArrayList<ParentVO> student = new ParentExamdetailsBD().getStudentlist(stovo);
				
				String studentid = student.get(0).getStdAdmisiionNo();
				stovo.setStudentid(studentid);
				
				StudentRegistrationVo studentinfo = new ParentExamdetailsBD().getStudentInfoBD(stovo);
				System.out.println("STUDENT="+studentinfo.getStudentFirstName());
				request.setAttribute("studentInfo", studentinfo);
				for(int i=0;i<status.size();i++) {
					
					if(status.get(i).getStatus().equalsIgnoreCase("schoolfee")) {
						feeReportVO  vo=new feeReportVO();
						vo.setTermname(HelperClass.gettermName(status.get(i).getTermid()));
						vo.setFeeNameVo(new OnlineFeePaymentDaoImpl().getFeePaidDetails(status.get(i).getStudentid(),status.get(i).getTermid()));
						vo.setFine(status.get(i).getFineAmount());
						feereceipt.add(vo);
					}
					if(status.get(i).getStatus().equalsIgnoreCase("transport")) {
						feeReportVO  vo=new feeReportVO();
						vo.setTermname(HelperClass.getTransporttermName(status.get(i).getTermid()));
						vo.setTotalAmount(Double.parseDouble(new OnlineFeePaymentDaoImpl().transportFeepaid(status.get(i).getRefrecieptNo())));
						vo.setSno(1);
						vo.setStream("Transport Fee");
						tfeereceipt.add(vo);
					}
				}
			request.setAttribute("totalPaid",status.get(0).getTot_actual_amt());
			if(feereceipt.size()>0) {
				System.out.println("I Will Generate Receipt");
				request.setAttribute("SchoolFee",feereceipt);	
			}	
			if(tfeereceipt.size()>0) {
				request.setAttribute("TransportFee",tfeereceipt);	
			}	
				
			}
			
		}
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in ParentMenu : onlinefeetransactionId Ending");
		return mapping.findForward(MessageConstants.ONLINE_FEE_RECEIPT);
	}
}
