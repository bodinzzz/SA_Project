
package ncu.im3069.demo.app;

import org.json.*;

import java.sql.Timestamp;
import java.util.Calendar;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * The Class Member
 * Member類別（class）具有會員所需要之屬性與方法，並且儲存與會員相關之商業判斷邏輯<br>
 * </p>
 * 
 * @author IPLab
 * @version 1.0.0
 * @since 1.0.0
 */

public class Manager {
    
    /* id */
    private int userID;
    
    /* 姓名 */
    private String userName;
    
    /* 帳號 */
    private String account;
    
    /* 管理員密碼 */
    private String password;
    
    /* 建立時間 注:本來寫String*/
    private String created;
    
    /* 更新時間 注:本來寫String*/
    private String modified;
    
    
    
    /** login_times，更新時間的分鐘數??? */
    private int loginTimes;
    
    /** mh，MemberHelper之物件與Member相關之資料庫方法（Sigleton） */
    private ManagerHelper mgh =  ManagerHelper.getHelper();

    
    /**
     * 實例化（Instantiates）一個新的（new）Manager物件<br>
     * 採用多載（overload）方法進行，此建構子用於修改會員資料時，產生一名新的會員
     *
     * @param email 會員電子信箱
     * @param password 會員密碼
     * @param name 會員姓名
     */ 
    public Manager(int user_id, String account, String user_name, String password) {
    	this.userID =user_id;
        this.account = account;
        this.userName = user_name;
        this.password = password;
    }
    
    
    
 
    
    /**
     * 取得該名會員所有資料
     *
     * @return the data 取得該名會員之所有資料並封裝於JSONObject物件內
     */
    public JSONObject getData() {
        /** 透過JSONObject將該名會員所需之資料全部進行封裝*/ 
        JSONObject jso = new JSONObject();
        jso.put("user_id", getUserID());
        jso.put("user_name", getUserName());
        jso.put("account", getAccount());
        jso.put("password", getPassword());
        return jso;
    }
    
    
    
    /**Get 方法**/

	public int getUserID() {
		return this.userID;
	}

	public String getUserName() {
		return this.userName;
	}

	public String getAccount() {
		return this.account;
	}


	public String getPassword() {
		return this.password;
	}
}