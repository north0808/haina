package com.haina.beluga.log.mvc;
	/**
	 * @author:付翔.
	 * @createDate:2007-7-17.
	 * @classInfo:
	 */
	public class LogCommand {
		
		private String startTime;
		private String endTime;
		private String startIp;
		private String endIp;
		private String user;
		private String remark;
		
		public String getEndIp() {
			return endIp;
		}
		public void setEndIp(String endIp) {
			this.endIp = endIp;
		}
		public String getEndTime() {
			return endTime;
		}
		public void setEndTime(String endTime) {
			this.endTime = endTime;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public String getStartIp() {
			return startIp;
		}
		public void setStartIp(String startIp) {
			this.startIp = startIp;
		}
		public String getStartTime() {
			return startTime;
		}
		public void setStartTime(String startTime) {
			this.startTime = startTime;
		}
		public String getUser() {
			return user;
		}
		public void setUser(String user) {
			this.user = user;
		}
	
	}
