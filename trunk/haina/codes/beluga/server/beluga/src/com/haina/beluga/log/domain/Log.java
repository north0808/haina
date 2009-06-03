package com.haina.beluga.log.domain;

import org.springframework.stereotype.Component;

import com.haina.beluga.core.model.VersionalModel;


/**
 * 只取角色名称不关联角色表，日志实时性考虑。
*
 * @hibernate.class table="LOG"
 */
 @Component
public class Log extends VersionalModel{

	 // Fields    
	private static final long serialVersionUID = 712763106703674149L;
	
   // private Account account;
    private String ip;
    private String roleName;
    private String logTime;
    private String handle;
    private String infoClass;
    private String remark;

    
	 /**
	 * @hibernate.property column="HANDLE" length="20"
	 * @return String
	 */
	public String getHandle() {
		return handle;
	}
	
	public void setHandle(String handle) {
		this.handle = handle;
	}
	 /**
	 * @hibernate.property column="INFOCLASS" length="50"
	 * @return String
	 */
	public String getInfoClass() {
		return infoClass;
	}
	
	public void setInfoClass(String infoClass) {
		this.infoClass = infoClass;
	}
	 /**
	 * @hibernate.property column="IP" length="20"
	 * @return String
	 */
	public String getIp() {
		return ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	 /**
	 * @hibernate.property column="LOGTIME" length="20"
	 * @return String
	 */
	public String getLogTime() {
		return logTime;
	}
	
	public void setLogTime(String logTime) {
		this.logTime = logTime;
	}
	 /**
	 * @hibernate.property column="REMARK" length="200"
	 * @return String
	 */
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @hibernate.property column="ROLENAME" length="20"
	 * @return String
	 */
	public String getRoleName() {
		return roleName;
	}
	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getVersion() {
		// TODO Auto-generated method stub
		return version;
	}
	@Override
	public boolean equals(Object object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	 /**
	 * @hibernate.id column="ID" generator-class="uuid.hex"  unsaved-value="null"
	 * @return String
	 */
	public String getId() {
		// TODO Auto-generated method stub
		return this.id;
	}
	
	   
}
