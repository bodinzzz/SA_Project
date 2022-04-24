package ncu.im3069.demo.controller;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.*;

import ncu.im3069.demo.app.Member;
import ncu.im3069.demo.app.Product;
import ncu.im3069.demo.app.ProductHelper;
import ncu.im3069.tools.JsonReader;

@WebServlet("/api/product.do")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductHelper ph =  ProductHelper.getHelper();

    public ProductController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
        JsonReader jsr = new JsonReader(request);
        /** 若直接透過前端AJAX之data以key=value之字串方式進行傳遞參數，可以直接由此方法取回資料 */
        String product_type = jsr.getParameter("product_type");
        String product_id = jsr.getParameter("product_id");
        String user_id = jsr.getParameter("user_id");
        
        System.out.println("有近來GET");

        JSONObject resp = new JSONObject();
        /** 判斷product_type是否存在 存在表示依照type找商品*/
        if (!product_type.isEmpty()) {
            JSONObject query = ph.getByProductType(product_type);
            resp.put("status", "200");
            resp.put("message", "用product_type取得商品資料成功");
            resp.put("response", query);
            
        } else if (!product_id.isEmpty()) {
        	
        	System.out.println("有進來else if");
        	
            /** 透過MemberHelper物件的getByID()方法自資料庫取回該名會員之資料，回傳之資料為JSONObject物件 */
            JSONObject query = ph.getByProductID(product_id);
            
            /** 新建一個JSONObject用於將回傳之資料進行封裝 */
            resp.put("status", "200");
            resp.put("message", "用product_id取得商品資料成功");
            resp.put("response", query);
            
            System.out.println("有跑到else iffffffffffffffff");
    
       } else if (!user_id.isEmpty()) {
        	
            /** 透過MemberHelper物件的getByID()方法自資料庫取回該名會員之資料，回傳之資料為JSONObject物件 */
            JSONObject query = ph.getByUserID(user_id);
            
            /** 新建一個JSONObject用於將回傳之資料進行封裝 */
            resp.put("status", "200");
            resp.put("message", "用product_id取得商品資料成功");
            resp.put("response", query);
    
        } else {
          JSONObject query = ph.getAll();

          resp.put("status", "200");
          resp.put("message", "所有商品資料取得成功");
          resp.put("response", query);
        }

        System.out.println(resp);
        /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
        jsr.response(resp, response);
	}

    /**
     * 處理Http Method請求POST方法（新增商品）
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
	        String user_id = jso.getString("user_id");
	        int id = Integer.parseInt(user_id);
	        String product_name = jso.getString("product_name");
	        String product_type = jso.getString("product_type");
	        int price = jso.getInt("price");
	        String product_state = jso.getString("product_state");
	        String content = jso.getString("content");
	        String place = jso.getString("place");
	        String image = "statics/asset/img/textbook1.png";

	        /** 建立一個新的Product物件 */
	        Product p = new Product(id, product_name, product_type, price, product_state, content, place, image);
	            
	        /** 透過MemberHelper物件的create()方法新建一個會員至資料庫 */
	        JSONObject data = ph.create(p);
	
	        /** 新建一個JSONObject用於將回傳之資料進行封裝 */
	        JSONObject resp = new JSONObject();
	        resp.put("status", "200");
	        resp.put("message", "成功! 建立商品...");
	        resp.put("response", data);
	        
	        /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
	        jsr.response(resp, response);
	        System.out.println(resp);        
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
        JSONObject query = ph.deleteByID(product_id);
        
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
        int product_id = jso.getInt("product_id");
        String status = jso.getString("status");

        
        /** 透過Member物件的update()方法至資料庫更新該名會員資料，回傳之資料為JSONObject物件 */
        JSONObject data = ph.updateProductStatus(product_id, status);
        
        /** 新建一個JSONObject用於將回傳之資料進行封裝 */
        JSONObject resp = new JSONObject();
        
        resp.put("status", "200");
        resp.put("message", "成功! 更新會員資料...");
        resp.put("response", data);
        
        /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
        jsr.response(resp, response);
    }
  }
