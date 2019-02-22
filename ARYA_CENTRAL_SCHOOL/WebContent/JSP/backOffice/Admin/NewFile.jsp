public ActionForward saveVehicleDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in TransportAction : saveVehicleDetails Starting");
		
		
		String args = "Vehicle Details";
		request.setAttribute("vehicledetails", args);
		String path = null;
		int i = 0; 
		
		File fileURL = null;
		FileOutputStream outputStream = null;
		TransportForm vehicliform =(TransportForm)form;
		VehicleDetailsVO vehiclivo = new VehicleDetailsVO();
		IDGenerator id = new IDGenerator();
		try {

				String createuser = HelperClass.getCurrentUserID(request);
				String vehiclecode = id.getPrimaryKeyID("transport_vehicle");
				vehicliform.setVehiclecode(vehiclecode);
				vehicliform.setCreateuser(createuser);

				try {
					System.out.println("Rcfile text box Value: "+vehicliform.getRcfile());
					System.out.println("Rcfile text box Value: size:  "+vehicliform.getRcfile().getFileSize());
					if(vehicliform.getRcfile() == null || vehicliform.getRcfile().getFileSize()==0){
						vehiclivo.setRcfile(vehicliform.getHrcfileid()); 
						System.out.println("Rcfile1 action:===> hidden : "+vehicliform.getHrcfileid());
						
					}
					else{


						String extension=null;

						int j = vehicliform.getRcfile().getFileName().lastIndexOf('.');

						if (j >= 0) {
							extension = vehicliform.getRcfile().getFileName().substring(i+1);
						}


						String rcfilepath = "FIles/TRANSPORT/"+vehiclecode+"."+extension;

						String filePath = request.getRealPath("/") + "FIles/TRANSPORT/" + vehiclecode+ "." +extension;

						//System.out.println("Action Class--> RC filePath: "+filePath);

						if (vehicliform.getRcfile().getFileSize() > 0) {

							byte[] btDataFile = vehicliform.getRcfile().getFileData();
							File of = new File(filePath);
							FileOutputStream osf = new FileOutputStream(of);
							osf.write(btDataFile);
							osf.flush();

						}
						else{

							rcfilepath = ""; 

						}

						/* vehicliform.setRcfile1(rcfilepath);*/
						
						vehiclivo.setRcfile(rcfilepath); 
						System.out.println("Rcfile1 action:===> "+rcfilepath);

					
						
					}


				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				}finally {
					if (outputStream != null) {
						outputStream.close();
					}
				}</html>