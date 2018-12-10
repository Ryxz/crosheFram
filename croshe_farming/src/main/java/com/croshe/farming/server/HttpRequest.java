package com.croshe.farming.server;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.croshe.crosheandroidbase.util.ImageUtils;
import com.croshe.farming.AppContext;
import com.croshe.farming.Entity.BuyCarInfo;
import com.croshe.farming.Login.RegisteredActivity;
import com.croshe.farming.activity.OrdeDetailActivity;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 接口
 */

public class HttpRequest {


    /**
     * 刷新用戶信息
     * @param context
     * @param serverResultListener
     */
    public static void refreshUser(Context context,ServerResultListener serverResultListener){
        Map<String,Object> map=new HashMap<>();
        map.put("userId",AppContext.getInstance().getUserInfo().getUserId());
        ServerRequest.requestHttp(context, ServerUrl.refreshUser, map, "",  serverResultListener );
    }
//    /**
//     * 刷新购物车
//     */
//    public static void refShopCar(Context context,ServerResultListener serverResultListener){
//        Map<String,Object> map=new HashMap<>();
//        ServerRequest.requestHttp(context, ServerUrl.showUserCar, map, "获取中",  serverResultListener );
//    }
    /*
    * 添加收藏
    * */
    public static void addCollection(Context context,int targetType,int targetId,ServerResultListener serverResultListener){
        Map<String,Object> map=new HashMap<>();
        map.put("userId",AppContext.getInstance().getUserInfo().getUserId());
        map.put("targetType",targetType);
        map.put("targetId",targetId);
        ServerRequest.requestHttp(context, ServerUrl.addCollection, map, "获取中",  serverResultListener);
    }
    /**
     * 取消收藏
     */
    public static void delCollection(Context context,int targetType,int targetId,ServerResultListener serverResultListener){
        Map<String,Object> map=new HashMap<>();
        map.put("userId",AppContext.getInstance().getUserInfo().getUserId());
        map.put("targetType",targetType);
        map.put("targetId",targetId);
        ServerRequest.requestHttp(context, ServerUrl.delCollection, map, "获取中",  serverResultListener);
    }
    /**
     * 检测是否收藏
     */
    public static void checkCollection(Context context,int targetType,int targetId,ServerResultListener serverResultListener){
        Map<String,Object> map=new HashMap<>();
        map.put("userId",AppContext.getInstance().getUserInfo().getUserId());
        map.put("targetType",targetType);
        map.put("targetId",targetId);
        ServerRequest.requestHttp(context, ServerUrl.checkCollection, map, "获取中",  serverResultListener);
    }
    /**
     * 我的收藏
     */
    public static void collection(Context context,int userId,int page,ServerResultListener serverResultListener){
        Map<String,Object> map=new HashMap<>();
        map.put("userId",userId);
        map.put("page",page);
        ServerRequest.requestHttp(context, ServerUrl.collection, map, "获取中",  serverResultListener);
    }
    /*
    商品详情
     */
    public static void getProductDetails(Context context,int productId,ServerResultListener serverResultListener){
        Map<String,Object> map=new HashMap<>();
        map.put("productId",productId);
        ServerRequest.requestHttp(context, ServerUrl.getProductDetails, map, "获取中",  serverResultListener);
    }
    /*
    集市商品列表
     */
    public static void showProduct(Context context,int productId,ServerResultListener serverResultListener){
        Map<String,Object> map=new HashMap<>();
        map.put("productId",productId);
        ServerRequest.requestHttp(context, ServerUrl.showProduct, map, "获取中",  serverResultListener);
    }
    /*
    加入菜篮子
     */
    public static void joinUserFoodCar(Context context,String userId,int productId,String detailsId,ServerResultListener serverResultListener){
        Map<String,Object> map=new HashMap<>();
        map.put("userId",userId);
        map.put("productId",productId);
        map.put("detailsId",detailsId);
        ServerRequest.requestHttp(context, ServerUrl.joinUserFoodCar, map, "获取中",  serverResultListener);
    }
    /*
    获取农药和施肥列表
     */
    public static void getTypeUserStore(Context context,String userId,int typeClass,ServerResultListener serverResultListener){
        Map<String,Object> map=new HashMap<>();
        map.put("userId",userId);
        map.put("typeClass",typeClass);
        ServerRequest.requestHttp(context, ServerUrl.getTypeUserStore, map, "",  serverResultListener);
    }
    /**
     * 我的去种植去养殖列表
     */
    public static void getList(Context context,String userId,int page,int type,ServerResultListener serverResultListener){
        Map<String,Object> map=new HashMap<>();
        map.put("userId",userId);
        map.put("page",page);
        map.put("type",type);
        ServerRequest.requestHttp(context, ServerUrl.getList, map, "",  serverResultListener);
    }
    /**
     * 用药，施肥，加料列表
     */
    public static void shoUserFarmAction(Context context,String userId,String detailsId,int type,ServerResultListener serverResultListener){
        Map<String,Object> map=new HashMap<>();
        map.put("type",type);
        map.put("detailsId",detailsId);
        map.put("userId",userId);
        ServerRequest.requestHttp(context, ServerUrl.shoUserFarmAction, map, "",  serverResultListener);
    }
    /**
     *  用户》用药，施肥，加料  操作
     */
    public static void actionFarm(Context context,int userId,int actionId,int type,ServerResultListener serverResultListener){
        Map<String,Object> map=new HashMap<>();
        map.put("type",type);
        map.put("actionId",actionId);
        map.put("userId",userId);
        ServerRequest.requestHttp(context, ServerUrl.actionFarm, map, "",  serverResultListener);
    }
    /**
     *农场成本详情
     */
    public static void showCostList(Context context,int page,int costType,String detailsId,ServerResultListener serverResultListener){
        Map<String,Object> map=new HashMap<>();
        map.put("page",page);
        map.put("detailsId",detailsId);
        map.put("costType",costType);
        ServerRequest.requestHttp(context, ServerUrl.showCostList, map, "",  serverResultListener);
    }
    /**
     * 计算单个篮子的成本
     */
    public static void calculationHarvestCost(Context context,int foodCarId ,String harvestArea,int harvestType,ServerResultListener serverResultListener){
        Map<String,Object> map=new HashMap<>();
        map.put("foodCarId",foodCarId );
        map.put("harvestArea",harvestArea);
        map.put("harvestType",harvestType);
        ServerRequest.requestHttp(context, ServerUrl.calculationHarvestCost, map, "",  serverResultListener);
    }
    /**
     * 计算单个篮子的数量
     */
    public static void HarvestCost(Context context,int foodCarId ,int harvestNumber,int harvestType,ServerResultListener serverResultListener){
        Map<String,Object> map=new HashMap<>();
        map.put("foodCarId",foodCarId );
        map.put("harvestNumber",harvestNumber);
        map.put("harvestType",harvestType);
        ServerRequest.requestHttp(context, ServerUrl.calculationHarvestCost, map, "",  serverResultListener);
    }
    /**
     * 我的农场最新一条养殖日志
     */
    public static void showUserFarmNewLog(Context context,String detailsId,ServerResultListener serverResultListener){
        Map<String,Object> map=new HashMap<>();
        map.put("detailsId",detailsId);
        ServerRequest.requestHttp(context, ServerUrl.showUserFarmNewLog, map, "",  serverResultListener);
    }
    /**
     * 微信支付
     */
    public static void doWxPay(Context context,String orderCode ,double orderMoney,String orderTitle,ServerResultListener serverResultListener){
        Map<String,Object> map=new HashMap<>();
        map.put("orderCode",orderCode );
        map.put("orderMoney",orderMoney);
        map.put("orderTitle",orderTitle);
        ServerRequest.requestHttp(context, ServerUrl.doWxPay, map, "",  serverResultListener);
    }

    /**
     * 发起支付宝手机APP支付
     * @param context
     * @param orderCode
     * @param orderMoney
     * @param orderTitle
     * @param serverResultListener
     */
    public static void doAliPay(Context context,String orderCode ,String orderMoney,String orderTitle,ServerResultListener serverResultListener){
        Map<String,Object> map=new HashMap<>();
        map.put("orderCode",orderCode );
        map.put("orderMoney",orderMoney);
        map.put("orderTitle",orderTitle);
        ServerRequest.requestHttp(context, ServerUrl.doAliPay, map, "打开支付宝中",  serverResultListener);
    }

    /**
     * 充值接口
     * @param context
     * @param orderPrice
     * @param serverResultListener
     */
    public static void recharge(Context context,double orderPrice,ServerResultListener serverResultListener){
        Map<String,Object> map=new HashMap<>();
        map.put("userId",AppContext.getInstance().getUserInfo().getUserId());
        map.put("orderPrice",orderPrice );
        ServerRequest.requestHttp(context, ServerUrl.recharge, map, "",  serverResultListener);
    }
    /**
     * 签到
     * @param context
     * @param serverResultListener
     */
    public static void sign(Context context,ServerResultListener serverResultListener){
        Map<String,Object> map=new HashMap<>();
        map.put("userId", AppContext.getInstance().getUserInfo().getUserId());
        ServerRequest.requestHttp(context, ServerUrl.sign, map, "",  serverResultListener);
    }

    /**
     * 用户签到列表
     * @param context
     * @param serverResultListener
     */
    public static void userSignList(Context context,ServerResultListener serverResultListener){
        Map<String,Object> map=new HashMap<>();
        map.put("userId", AppContext.getInstance().getUserInfo().getUserId());
        ServerRequest.requestHttp(context, ServerUrl.userSignList, map, "",  serverResultListener);
    }
    /**
     * 清空收藏
     */
    public static void delAllUserProductCollection(Context context,ServerResultListener serverResultListener){
        Map<String,Object> map=new HashMap<>();
        map.put("userId", AppContext.getInstance().getUserInfo().getUserId());
        ServerRequest.requestHttp(context, ServerUrl.delAllUserProductCollection, map, "",  serverResultListener);
    }

    /**
     * 农场订单
     * @param context
     * @param landId
     * @param serverResultListener
     */
    public static void addLandOrder(Context context,String landId ,ServerResultListener serverResultListener){
        Map<String,Object> map=new HashMap<>();
        map.put("userId", AppContext.getInstance().getUserInfo().getUserId());
        map.put("landId",landId );
        ServerRequest.requestHttp(context, ServerUrl.addLandOrder, map, "",  serverResultListener);
    }

    /**
     * 计算购物车的价格
     * @param context
     * @param list
     * @param serverResultListener
     */
    public static void computePrice(Context context, List<String> list, ServerResultListener serverResultListener){
        Map<String,Object> map=new HashMap<>();
        map.put("userId", AppContext.getInstance().getUserInfo().getUserId());
        for (int i = 0;i<list.size();i++){
            map.put("carId["+i+"]",list.get(i));
        }
        ServerRequest.requestHttp(context, ServerUrl.computePrice, map, "",  serverResultListener);
    }

    public static void addCar(Context context,String packageId,String productPrice,int productCount, ServerResultListener serverResultListener){
        Map<String,Object> map=new HashMap<>();
        map.put("userId", AppContext.getInstance().getUserInfo().getUserId());
        map.put("packageId", packageId);
        map.put("productPrice", productPrice);
        map.put("productCount",productCount);
        ServerRequest.requestHttp(context, ServerUrl.addCar, map, "",  serverResultListener);
    }
    /**
     *
     * @param context
     * @param serverResultListener
     */

    public static void updateShopCar(Context context, String carId, int productCount, ServerResultListener serverResultListener){
        Map<String,Object> map=new HashMap<>();
        map.put("carId",carId);
        map.put("productCount",productCount);
        ServerRequest.requestHttp(context, ServerUrl.updateShopCar, map, "",  serverResultListener);
    }
    /**
     * 设置支付密码
     */
    public static void modifyPayPassword(Context context, String payPassword,String userPhone,String code, ServerResultListener serverResultListener){
        Map<String,Object> map=new HashMap<>();
        map.put("userId",AppContext.getInstance().getUserInfo().getUserId());
        map.put("payPassword",payPassword);
        map.put("userPhone",userPhone);
        map.put("code",code);
        ServerRequest.requestHttp(context, ServerUrl.modifyPayPassword, map, "",  serverResultListener);
    }
    /**
     * 验证码发送短信
     */
    public static void sendSMS(Context context, String phone, int type, ServerResultListener serverResultListener){
        Map<String,Object> map = new HashMap<>();
        map.put("phone",phone);
        map.put("smsType",type);
        ServerRequest.requestHttp(context, ServerUrl.sendSMS, map, "", serverResultListener);
    }

    /**
     * 添加收成订单
     */
    public static void addPeisongOrder(Context context,List<String> storeIds, List<Integer> numbers, String addressId, int addressType, ServerResultListener serverResultListener){
        Map<String,Object> map = new HashMap<>();
        map.put("userId",AppContext.getInstance().getUserInfo().getUserId());
        for (int i = 0;i<storeIds.size();i++){
            map.put("store["+i+"].storeId",storeIds.get(i));
        }
        for (int i = 0;i<numbers.size();i++){
            map.put("store["+i+"].number",numbers.get(i));
        }
        map.put("addressId",addressId);
        map.put("addressType",addressType);
        ServerRequest.requestHttp(context, ServerUrl.addPeisongOrder, map, "", serverResultListener);
    }

    /**
     * 我的订单列表统计
     */
    public static void showMyOrdersCount(Context context, ServerResultListener serverResultListener){
        Map<String,Object> map = new HashMap<>();
        map.put("userId",AppContext.getInstance().getUserInfo().getUserId());
        ServerRequest.requestHttp(context, ServerUrl.showMyOrdersCount, map, "", serverResultListener);
    }
    /**
     * 订单详情
     */
    public static void showOrderDetails(Context context,String orderCode,ServerResultListener serverResultListener){
        Map<String,Object> map = new HashMap<>();
        map.put("orderCode",orderCode);
        ServerRequest.requestHttp(context, ServerUrl.showOrderDetails, map, "", serverResultListener);
    }
    /**
     * 回复评论
     */
    public static void addComment(Context context,String commentContent,List<String> listImg,int orderId,int targetType,String orderCode,int targetId,
                                  float commentLevel,float fuwu,float peisong,ServerResultListener serverResultListener){
        Map<String,Object> map = new HashMap<>();
        map.put("userId",AppContext.getInstance().getUserInfo().getUserId());
        map.put("commentContent",commentContent );
        for (int i = 0; i < listImg.size(); i++) {
            map.put("adviceImage[" + i + "]", new File(listImg.get(i).replace("file://", "")));
            ImageUtils.doCompressImage(listImg.get(i).replace("file://", ""), listImg.get(i).replace("file://", ""));
        }
        map.put("orderId",orderId);
        map.put("orderCode",orderCode);
        map.put("targetType",targetType);
        map.put("targetId",targetId);
        map.put("commentLevel",commentLevel);
        map.put("fuwu",fuwu);
        map.put("peisong",peisong);
//        map.put("parentId",parentId);
//        map.put("toUserId",toUserId);
        ServerRequest.requestHttp(context, ServerUrl.addComment, map, "", serverResultListener);
    }
    /**
     * 科学搭配
     */
    public static void showPackageDetails(Context context,String detailsProductId,ServerResultListener serverResultListener){
        Map<String,Object> map = new HashMap<>();
        map.put("detailsProductId",detailsProductId);
        ServerRequest.requestHttp(context, ServerUrl.showPackageDetails, map, "", serverResultListener);
    }
    /**
     * 提醒配送
     */
    public static void addPsMessage(Context context,int orderId,String orderCode,ServerResultListener serverResultListener){
        Map<String,Object> map = new HashMap<>();
        map.put("userId",AppContext.getInstance().getUserInfo().getUserId());
        map.put("userPhone",AppContext.getInstance().getUserInfo().getUserPhone());
        map.put("orderId",orderId);
        map.put("orderCode",orderCode);
        ServerRequest.requestHttp(context, ServerUrl.addPsMessage, map, "", serverResultListener);
    }
    /**
     * 首页搜索
     */
    public static void search(Context context,String productName,ServerResultListener serverResultListener){
        Map<String,Object> map = new HashMap<>();
        map.put("productName",productName);
        ServerRequest.requestHttp(context, ServerUrl.search, map, "", serverResultListener);
    }
    /**
     * 商品详情的查看全部评论
     */
    public static void showAllComment(Context context,String productId,int page,ServerResultListener serverResultListener){
        Map<String,Object> map = new HashMap<>();
        map.put("productId",productId);
        map.put("page",page);
        ServerRequest.requestHttp(context, ServerUrl.showAllComment, map, "", serverResultListener);
    }
    /**
     * 修改订单状态[{"id":0,"text":"待付款"},{"id":1,"text":"已付款"},{"id":2,"text":"待配送"},{"id":3,"text":"已配送"},{"id":4,"text":"已签收"},{"id":5,"text":"待评价"},{"id":6,"text":"已评价"}]
     */
    public static void setOrderState(Context context,int orderId,int orderState,ServerResultListener serverResultListener){
        Map<String,Object> map = new HashMap<>();
        map.put("orderId",orderId);
        map.put("orderState",orderState);
        ServerRequest.requestHttp(context, ServerUrl.setOrderState, map, "", serverResultListener);
    }

    /**
     * 全局配置[{"id":0,"text":"待付款"},{"id":1,"text":"已付款"},{"id":2,"text":"待配送"},{"id":3,"text":"已配送"},{"id":4,"text":"已签收"},{"id":5,"text":"待评价"},{"id":6,"text":"已评价"}]
     * @param context
     * @param configType
     * @param serverResultListener
     */
    public static void showGlobal(Context context,int configType,ServerResultListener serverResultListener){
        Map<String,Object> map = new HashMap<>();
        map.put("configType",configType);
        ServerRequest.requestHttp(context, ServerUrl.showGlobal, map, "", serverResultListener);
    }

    /**
     *套餐购买
     * @param context
     * @param serverResultListener
     */
    public static void buyPackage(Context context,String orderPrice,String packageId,int count,ServerResultListener serverResultListener){
        Map<String,Object> map = new HashMap<>();
        map.put("userId",AppContext.getInstance().getUserInfo().getUserId());
        map.put("orderPrice",orderPrice);
        map.put("packageId",packageId);
        map.put("count",count);
        ServerRequest.requestHttp(context, ServerUrl.buyPackage, map, "", serverResultListener);
    }

    public static void deleteOrder(Context context,int orderId,ServerResultListener serverResultListener){
        Map<String,Object> map = new HashMap<>();
        map.put("orderId",orderId);
        ServerRequest.requestHttp(context,ServerUrl.deleteOrder,map,"",serverResultListener);
    }
    /**
     * 全部评论
     */
    public static void getNewsComment(Context context,String targetId,int page,int targetType,ServerResultListener serverResultListener){
        Map<String,Object> map = new HashMap<>();
        map.put("targetId",targetId);
        map.put("page",page);
        map.put("targetType",targetType);
        ServerRequest.requestHttp(context,ServerUrl.getNewsComment,map,"",serverResultListener);
    }

    /**
     * 修改用户
     * @param context
     * @param userNickName
     * @param userSex
     * @param userHeadImg
     * @param serverResultListener
     */
    public static void modifyUser(Context context,String userNickName,String userSex,String userHeadImg ,ServerResultListener serverResultListener){
        Map<String,Object> map = new HashMap<>();
        map.put("userId",AppContext.getInstance().getUserInfo().getUserId());
        map.put("userNickName",userNickName);
        map.put("userSex",userSex);
        map.put("userHeadImg",new File(userHeadImg.replace("file://", "")));
        ServerRequest.requestHttp(context, ServerUrl.modifyUser, map, "", serverResultListener);
    }

    /**
     *
     * @param context
     * @param serverResultListener
     */
    public static void getCropList2(Context context,ServerResultListener serverResultListener){
        Map<String,Object> map = new HashMap<>();
        map.put("userId",AppContext.getInstance().getUserInfo().getUserId());
        ServerRequest.requestHttp(context, ServerUrl.getCropList2, map, "", serverResultListener);
    }

    /**
     * 用药，施肥，加料 提示数量
     * @param context
     * @param detailsId
     * @param serverResultListener
     */
    public static void countFormAction(Context context,String detailsId,ServerResultListener serverResultListener){
        Map<String,Object> map = new HashMap<>();
        map.put("detailsId",detailsId);
        ServerRequest.requestHttp(context, ServerUrl.countFormAction, map, "", serverResultListener);
    }
}
