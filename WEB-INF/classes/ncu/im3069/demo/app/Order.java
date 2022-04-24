package ncu.im3069.demo.app;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

import org.json.*;

public class Order {

	/* 訂單ID */
	private int orderID;
	
	/* 賣家ID */
	private int sellerID;
	
	/* 買家ID */
	private int buyerID;
	
	/*商品ID*/
	private int productID;
	
	/*商品名稱*/
	private String productName;
	
	/*訂單進行狀態*/
	private String orderStatus;
	
	/*訂單建立時間*/
	private String created;
	
	/*訂單完成時間*/
	private String modified;

    /** Order 相關之資料庫方法（Sigleton） */
    private OrderHelper oh = OrderHelper.getHelper();

    /**
     * 實例化（Instantiates）一個新的（new）Order 物件<br>
     * 採用多載（overload）方法進行，此建構子用於建立訂單資料時，產生一個新的訂單
     *
     */
    public Order(int buyer_id, int product_id) {
        this.buyerID = buyer_id;
        this.productID = product_id;
    }
    
    public Order(int order_id, int seller_id, int buyer_id, int product_id, String product_name, String order_status, Timestamp created, Timestamp modified) {
    	this.orderID = order_id;
    	this.sellerID = seller_id;
    	this.buyerID = buyer_id;
    	this.productID = product_id;
    	this.productName = product_name;
    	this.orderStatus = order_status;
        this.created = created.toString();
        this.modified = modified.toString();
    }
    
    /**
     * 取得訂單資訊
     *
     * @return JSONObject 回傳產品資訊
     */
	public JSONObject getData() {
        /** 透過JSONObject將該項產品所需之資料全部進行封裝*/
        JSONObject jso = new JSONObject();
        jso.put("order_id", getOrderID());
        jso.put("seller_id", getSellerID());
        jso.put("buyer_id", getBuyerID());
        jso.put("product_id", getProductID());
        jso.put("product_name", getProductName());
        jso.put("order_status", getOrderStatus());
        jso.put("created", getCreated());
        jso.put("modified", getModified());
        System.out.println(jso);
        return jso;
    }
    
    
	/* Get 方法 */
	public int getOrderID() {
		return this.orderID;
	}
	public int getSellerID() {
		return this.sellerID;
	}
	public int getBuyerID() {
		return this.buyerID;
	}
	public int getProductID() {
		return this.productID;
	}
	public String getProductName() {
		return this.productName;
	}
	public String getOrderStatus() {
		return this.orderStatus;
	}
	public String getCreated() {
		return this.created;
	}
	public String getModified() {
		return this.modified;
	}

}
