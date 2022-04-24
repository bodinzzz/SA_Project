package ncu.im3069.demo.controller;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.*;

import ncu.im3069.demo.app.Member;
import ncu.im3069.demo.app.Order;
import ncu.im3069.demo.app.Product;
import ncu.im3069.demo.app.ProductHelper;
import ncu.im3069.demo.app.OrderHelper;
import ncu.im3069.tools.JsonReader;

import javax.servlet.annotation.WebServlet;

@WebServlet("/api/order.do")
public class OrderController extends HttpServlet {

    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

    /** ph，ProductHelper 之物件與 Product 相關之資料庫方法（Sigleton） */
    private ProductHelper ph =  ProductHelper.getHelper();

    /** oh，OrderHelper 之物件與 order 相關之資料庫方法（Sigleton） */
	private OrderHelper oh =  OrderHelper.getHelper();

    public OrderController() {
        super();
    }
    
    
    /**
     * 處理Http Method請求DELETE方法（刪除）
     *
     * @param request Servlet請求之HttpServletRequest之Request物件（前端到後端）
     * @param response Servlet回傳之HttpServletResponse之Response物件（後端到前端）
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void doDelete(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        /** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
        JsonReader jsr = new JsonReader(request);
        JSONObject jso = jsr.getObject();
        
        /** 取出經解析到JSONObject之Request參數 */
        int product_id = jso.getInt("product_id");

        /** 透過MemberHelper物件的deleteByID()方法至資料庫刪除該名會員，回傳之資料為JSONObject物件 */
        JSONObject query = oh.deleteByProductID(product_id);
        
        System.out.println("有執行完sql");
        
        /** 新建一個JSONObject用於將回傳之資料進行封裝 */
        JSONObject resp = new JSONObject();
        resp.put("status", "200");
        resp.put("message", "會員移除成功！");
        resp.put("response", query);

        /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
        jsr.response(resp, response);
    }
    
    /**
     * 處理Http Method請求POST方法（新增訂單）
     *
     * @param request Servlet請求之HttpServletRequest之Request物件（前端到後端）
     * @param response Servlet回傳之HttpServletResponse之Response物件（後端到前端）
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
	        /** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
	        JsonReader jsr = new JsonReader(request);
	        JSONObject jso = jsr.getObject();
	        
	        /** 取出經解析到JSONObject之Request參數 */
	        String user_id_string = jso.getString("user_id");
	        int user_id = Integer.parseInt(user_id_string);
	        String product_id_string = jso.getString("product_id");
	        int product_id = Integer.parseInt(product_id_string);

	        /** 建立一個新的Product物件 */
	        Order o = new Order(user_id, product_id);
	            
	        /** 透過MemberHelper物件的create()方法新建一個會員至資料庫 */
	        JSONObject data = oh.create(o);
	
	        /** 新建一個JSONObject用於將回傳之資料進行封裝 */
	        JSONObject resp = new JSONObject();
	        resp.put("status", "200");
	        resp.put("message", "成功! 建立訂單成功");
	        resp.put("response", data);
	        
	        ph.updateNumOfWait(product_id);
	        
	        
	        /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
	        jsr.response(resp, response);
	        System.out.println(resp);        
    	}
    

    /**
     * 處理 Http Method 請求 GET 方法（新增資料）
     *
     * @param request Servlet 請求之 HttpServletRequest 之 Request 物件（前端到後端）
     * @param response Servlet 回傳之 HttpServletResponse 之 Response 物件（後端到前端）
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /** 透過 JsonReader 類別將 Request 之 JSON 格式資料解析並取回 */
        JsonReader jsr = new JsonReader(request);
        JSONObject resp = new JSONObject();
        
        /** 取出經解析到 JsonReader 之 Request 參數 */
        String buyer_id = jsr.getParameter("buyer_id");
        String product_id = jsr.getParameter("product_id");
        
        if (!buyer_id.isEmpty()) {    	

            /** 透過MemberHelper物件的getByID()方法自資料庫取回該名會員之資料，回傳之資料為JSONObject物件 */
            JSONObject query = oh.getByBuyerID(buyer_id);
            
            /** 新建一個JSONObject用於將回傳之資料進行封裝 */
            resp.put("status", "200");
            resp.put("message", "用product_id取得商品資料成功");
            resp.put("response", query);
        	
        } else {

            /** 透過MemberHelper物件的getByID()方法自資料庫取回該名會員之資料，回傳之資料為JSONObject物件 */
            JSONObject query = oh.getByProductID(product_id);
            
            /** 新建一個JSONObject用於將回傳之資料進行封裝 */
            resp.put("status", "200");
            resp.put("message", "用product_id取得商品資料成功");
            resp.put("response", query);
        	
        }



        /** 透過 JsonReader 物件回傳到前端（以 JSONObject 方式） */
        jsr.response(resp, response);
	}
	
    /**
     * 處理Http Method請求PUT方法（更新）
     *
     * @param request Servlet請求之HttpServletRequest之Request物件（前端到後端）
     * @param response Servlet回傳之HttpServletResponse之Response物件（後端到前端）
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void doPut(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        /** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
        JsonReader jsr = new JsonReader(request);
        JSONObject jso = jsr.getObject();
        
        /** 取出經解析到JSONObject之Request參數 */
        int order_id = jso.getInt("order_id");
        String order_status = jso.getString("order_status");
        String product_id = jso.getString("product_id");
        String note = jso.getString("note");
        
        System.out.println("note4"+note);
        
        /** 新建一個JSONObject用於將回傳之資料進行封裝 */
        JSONObject resp = new JSONObject();
        
        if (note.equals("更新其他訂單狀態")) {
            JSONObject data = oh.updateOtherOrderStatus(product_id, order_id, order_status);
            
            System.out.println("進來了更新其他訂單狀態");
            
            resp.put("status", "200");
            resp.put("message", "成功! 更新會員資料...");
            resp.put("response", data);     
        	
        } else if (note.equals("更新訂單狀態(當商品刪除)")) {
            /** 透過Member物件的update()方法至資料庫更新該名會員資料，回傳之資料為JSONObject物件 */
            JSONObject data = oh.updateAllOrderStatus(product_id, order_status);
            
            System.out.println("更新訂單狀態(當商品刪除)");
            
            resp.put("status", "200");
            resp.put("message", "成功! 更新會員資料...");
            resp.put("response", data);        	
        } else {
            /** 透過Member物件的update()方法至資料庫更新該名會員資料，回傳之資料為JSONObject物件 */
            JSONObject data = oh.updateOrderStatus(order_id, order_status);
            
            System.out.println("進來了更新訂單狀態");
            
            resp.put("status", "200");
            resp.put("message", "成功! 更新會員資料...");
            resp.put("response", data);        	
        }
        
        /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
        jsr.response(resp, response);
    }


}
