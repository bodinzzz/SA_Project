
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

public class Member {
    
    /* id */
    private int userID;
    
    /* 姓名 */
    private String userName;
    
    /* 學號 */
    private String stuID;
    
    /* 信箱 */
    private String email;   
    
    /* 會員密碼 */
    private String password;
    
    /* 電話 */
    private String userPhone;
    
    /* 建立時間 注:本來寫String*/
    private String created;
    
    /* 更新時間 注:本來寫String*/
    private String modified;
    
    /** status，會員是否停權*/
    private String userStatus;
    
    
    /** login_times，更新時間的分鐘數??? */
    private int loginTimes;
    
    /** mh，MemberHelper之物件與Member相關之資料庫方法（Sigleton） */
    private MemberHelper mh =  MemberHelper.getHelper();
    
    /**
     * 實例化（Instantiates）一個新的（new）Member物件<br>
     * 採用多載（overload）方法進行，此建構子用於建立會員資料時，產生一名新的會員
     *
     * @param email 會員電子信箱
     * @param password 會員密碼
     * @param name 會員姓名
     */ 
    public Member(String user_name, String stu_id, String email, String password, String user_phone) {
        this.userName = user_name;
        this.stuID = stu_id;
        this.email = email;
        this.password = password;
        this.userPhone = user_phone;
        update();
    }
    
    /**
     * 實例化（Instantiates）一個新的（new）Member物件<br>
     * 採用多載（overload）方法進行，此建構子用於修改會員資料時，產生一名新的會員
     *
     * @param email 會員電子信箱
     * @param password 會員密碼
     * @param name 會員姓名
     */ 
    public Member(int user_id, String user_name, String stu_id, String email, String password, String user_phone) {
    	this.userID =user_id;
        this.userName = user_name;
        this.stuID = stu_id;
        this.email = email;
        this.password = password;
        this.userPhone = user_phone;
        update();
    }

    /**
     * 實例化（Instantiates）一個新的（new）Member物件<br>
     * 採用多載（overload）方法進行，此建構子用於更新會員資料時，產生一名會員同時需要去資料庫檢索原有更新時間分鐘數與會員組別
     * 
     * @param id 會員編號
     * @param email 會員電子信箱
     * @param password 會員密碼
     * @param name 會員姓名
     */
    public Member(int id, String email, String password, String name) {
        this.userID = id;
        this.email = email;
        this.password = password;
        this.userName = name;
        /** 取回原有資料庫內該名會員之更新時間分鐘數與組別 */
        getLoginTimesStatus();
    }
    
    /**
     * 實例化（Instantiates）一個新的（new）Member物件<br>
     * 採用多載（overload）方法進行，此建構子用於查詢會員資料時，將每一筆資料新增為一個會員物件
     *
     * @param id 會員編號
     * @param email 會員電子信箱
     * @param password 會員密碼
     * @param name 會員姓名
     * @param login_times 更新時間的分鐘數
     * @param status the 會員之組別
     */
    public Member(int user_id, String user_name, String stu_id, String email, String password, String user_phone, Timestamp created, Timestamp modified, String user_status) {
        this.userID = user_id;
        this.userName = user_name;
        this.stuID = stu_id;
        this.email = email;
        this.password = password;
        this.userPhone = user_phone;
        this.created = created.toString();
        this.modified = modified.toString();
        this.userStatus = user_status;
    }
    
    
    /**
     * 更新會員資料
     *
     * @return the JSON object 回傳SQL更新之結果與相關封裝之資料
     */
    public JSONObject update() {
        /** 新建一個JSONObject用以儲存更新後之資料 */
        JSONObject data = new JSONObject();
        /** 取得更新資料時間（即現在之時間）之分鐘數 */
        Calendar calendar = Calendar.getInstance();
       // this.modified = calendar.get(Calendar.MINUTE);
        
        /** 檢查該名會員是否已經在資料庫 */
        if(this.userID != 0) {
            /** 若有則將目前更新後之資料更新至資料庫中 */
            mh.updateLoginTimes(this);
            /** 透過MemberHelper物件，更新目前之會員資料置資料庫中 */
            data = mh.updateByUserID(this);
        }
        
        
        return data;
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
        jso.put("stu_id", getStuID());
        jso.put("email", getEmail());
        jso.put("password", getPassword());
        jso.put("user_phone", getUserPhone());
        jso.put("created", getCreated());
        jso.put("modified", getModified());
        jso.put("user_status", getUserStatus());
        
        return jso;
    }
    
    /**
     * 取得資料庫內之更新資料時間分鐘數與會員組別
     *
     */
    private void getLoginTimesStatus() {
        /** 透過MemberHelper物件，取得儲存於資料庫的更新時間分鐘數與會員組別 */
        JSONObject data = mh.getLoginTimesStatus(this);
        /** 將資料庫所儲存該名會員之相關資料指派至Member物件之屬性 */
        this.modified = data.getString("modified");
        this.userStatus = data.getString("user_status");
    }
    
    
    /**Get 方法**/

	public int getUserID() {
		return this.userID;
	}

	public String getUserName() {
		return this.userName;
	}

	public String getStuID() {
		return this.stuID;
	}

	public String getEmail() {
		return this.email;
	}

	public String getPassword() {
		return this.password;
	}

	public String getUserPhone() {
		return this.userPhone;
	}

	public String getCreated() {
		return this.created;
	}

	public String getModified() {
		return this.modified;
	}

	public String getUserStatus() {
		return this.userStatus;
	}

	public int getLoginTimes() {
		return this.loginTimes;
	}
    
}