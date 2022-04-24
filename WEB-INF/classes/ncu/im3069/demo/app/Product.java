package ncu.im3069.demo.app;

import java.sql.Timestamp;
import java.util.Calendar;

import org.json.*;

import ncu.im3069.demo.controller.Product;

public class Product {

	/* 商品編號 */
	private int productID;
	
	/* 哪個會員建立 */	
	private int userID;

	/* 商品名稱 */
	private String productName;

	/* 商品類別 */
	private String productType;

	/* 價格 */
	private int price;

	/* 商品狀況 */
	private String productState;

	/* 內容描述 */
	private String content;

	/* 商品交易地點 */
	private String place;

	/* 圖片 */
	private String image;

	/* 商品進行狀態 */
	private String status;

	/* 等待人數 */
	private int numOfWait;

	/* 建立時間 */
	private String created;

	/* 更新時間 */
	private String modified;
	
    /** login_times，更新時間的分鐘數??? */
    private int loginTimes;
    
    /** mh，MemberHelper之物件與Member相關之資料庫方法（Sigleton） */
    private ProductHelper ph =  ProductHelper.getHelper();
	
	
	//******建構子區*******//
	
    /**
     * 實例化（Instantiates）一個新的（new）Product 物件<br>
     * 採用多載（overload）方法進行，此建構子用於新增產品時
     *
     * @param id 產品編號
     * @param name 產品名稱
     * @param price 產品價格
     * @param image 產品圖片
     * @param describe 產品敘述
     */
	public Product(int user_id, String product_name, String product_type, int price, String product_state, String content, String place, String image) {
		this.userID = user_id;
		this.productName = product_name;
		this.productType = product_type;
		this.price = price;
		this.productState = product_state;
		this.content = content;
		this.place = place;
		this.image = image;
		update(); //新增商品
	}

	
    /**
     * 實例化（Instantiates）一個新的（new）Product 物件<br>
     * 採用多載（overload）方法進行，此建構子用於拿取商品資訊
     *
     * @param id 產品編號
     * @param name 產品名稱
     * @param price 產品價格
     * @param image 產品圖片
     * @param describe 產品敘述
     */
	public Product(int product_id, int user_id, String product_name, int price, String product_type, String product_state, String content, String place, String image, String status, int num_of_wait, Timestamp created, Timestamp modified) {
		this.productID = product_id;
		this.userID = user_id;
		this.productName = product_name;
		this.price = price;
		this.productType = product_type;
		this.productState = product_state;
		this.content = content;
		this.place = place;
		this.image = image;
		this.status = status;
		this.numOfWait = num_of_wait;
        this.created = created.toString();
        this.modified = modified.toString();
        System.out.println("有近來建構子");
	}
	
	
	
	
	
	
	
	   /**
     * 更新商品資料(新增OR修改)
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
        if(this.productID != 0) {
            /** 若有則將目前更新後之資料更新至資料庫中 */
           //ph.updateLoginTimes(this);
           /** 透過MemberHelper物件，更新目前之會員資料置資料庫中 */
           //data = ph.updateByProductID(this);
        }
        
        
        return data;
    }

    /**
     * 取得產品資訊
     *
     * @return JSONObject 回傳產品資訊
     */
	public JSONObject getData() {
        /** 透過JSONObject將該項產品所需之資料全部進行封裝*/
        JSONObject jso = new JSONObject();
        jso.put("product_id", getProductID());
        jso.put("user_id", getUserID());
        jso.put("product_name", getProductName());
        jso.put("price", getPrice());
        jso.put("product_type", getProductType());
        jso.put("product_state", getProductState());
        jso.put("content", getContent());
        jso.put("place", getPlace());
        jso.put("image", getImage());
        jso.put("status", getStatus());
        jso.put("num_of_wait", getNumOfWait());
        jso.put("created", getCreated());
        jso.put("modified", getModified());
        System.out.println(jso);
        return jso;
    }

	
	/**** GETTER ****/
	public int getProductID() {
		return this.productID;
	}

	public int getUserID() {
		return this.userID;
	}

	public String getProductName() {
		return this.productName;
	}

	public String getProductType() {
		return this.productType;
	}

	public int getPrice() {
		return this.price;
	}

	public String getProductState() {
		return this.productState;
	}

	public String getContent() {
		return this.content;
	}

	public String getPlace() {
		return this.place;
	}

	public String getImage() {
		return this.image;
	}

	public String getStatus() {
		return this.status;
	}

	public int getNumOfWait() {
		return this.numOfWait;
	}

	public String getCreated() {
		return created;
	}

	public String getModified() {
		return modified;
	}
}
