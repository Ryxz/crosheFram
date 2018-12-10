package com.croshe.farming.server;

/**
 * Created by Administrator on 2017/5/5.
 */

public class ServerUrl {
//http://192.168.0.8:8888/Croshe_Farming/interface.html
//  public static String mainUrl="http://yyxs.naucu.com/Croshe_Farming/";//
 public static String mainUrl="http://120.77.251.197/Croshe_Farming/";//
//
//    public static String mainUrl="http://192.168.0.11:8888/Croshe_Farming/";//

//public static String mainUrl="http://192.168.0.26:8888/Croshe_Farming/";//W
//  public static String mainUrl="http://192.168.0.8:8888/Croshe_Farming/";//C
//public static String mainUrl="http://192.168.0.7:8088/Croshe_Farming/";//S


  //
//登录
  public static String login=mainUrl+"mobile/user/login";
  /**
   * 刷新用戶消息
   */
  public static String refreshUser=mainUrl+"mobile/user/refreshUser";
  //发送验证码通用
  public static String sendSMS=mainUrl+"mobile/common/sendSMS";
  //注册
  public static String register=mainUrl+"mobile/user/register";
  //忘记密码1
  public static String forgetPwdOne=mainUrl+"mobile/user/forgetPwdOne";
  //忘记密码2
  public static String forgetPwdThree=mainUrl+"mobile/user/forgetPwdThree";
  //忘记密码3
  public static String forgetPwdFour=mainUrl+"mobile/user/forgetPwdFour";
  //首页滚动图
  public static String getIndexActivity=mainUrl+"mobile/common/getIndexActivity";
  //农场头条
  public static String getNewsTop=mainUrl+"mobile/news/getNewsTop";

  //商品推荐
  public static String getPublishProduct=mainUrl+"mobile/product/getPublishProduct";
  //查看农场
  public static String showFarm=mainUrl+"mobile/farm/showFarm";
  //查看农场详情
  public static String showFarmdetails=mainUrl+"mobile/farm/showFarmdetails";
  //查看农场土地详情
  public static String showLand=mainUrl+"mobile/land/showLand";
  //查看我的农场
  public static String showUserFarm=mainUrl+"mobile/userFarm/showUserFarm";
  //查看我的农场详情
  public static String showFarmDetails=mainUrl+"mobile/userFarm/showFarmDetails";
  //我的农场去养殖和去种植的列表
  public static String getList=mainUrl+"mobile/userFarm/getList";
  /**
   *计算购物车的价格
   */
  public static String computePrice=mainUrl+"mobile/shopcar/computePrice";

  public static String farmingLand=mainUrl+"mobile/userFarm/farmingLand";
  //土地详情
  public static String showLandDetails=mainUrl+"mobile/land/showLandDetails";
  //套餐推荐
  public static String showPackageList=mainUrl+"mobile/package/showPackageList";
  //套餐推荐详情
  public static String showPackageDetials=mainUrl+"mobile/package/showPackageDetials";
  /**
   * 套餐加入购物车
   */
  public static String addCar=mainUrl+"mobile/package/addCar";
  /**
   * 套餐购买
   */
  public static String buyPackage=mainUrl+"mobile/package/buyPackage";
  //集市分类
  public static String getProductType=mainUrl+"mobile/productType/getProductType";

  //集市商品列表
  public static String showProduct=mainUrl+"mobile/product/showProduct";
  /*
  * 商品详情
  * */
  public static String getProductDetails=mainUrl+"mobile/product/getProductDetails";
  /*
   科学搭配
   */
  public static String showPackageDetails=mainUrl+"mobile/packageDetails/showPackageDetails";

  //购物车列表
  public static String showUserCar=mainUrl+"mobile/shopcar/showUserCar";
  //菜篮子
  public static String showUserFoodCar=mainUrl+"mobile/foodCar/showUserFoodCar";
  //修改购物车数量
  public static String updateShopCar=mainUrl+"mobile/shopcar/updateShopCar";
  //删除购物车
  public static String deleteShopCar=mainUrl+"mobile/shopcar/deleteShopCar";
  //收成
  public static String TypeTypeEnum=mainUrl+"mobile/common/showEnums?enumName=TypeTypeEnum";
  //农资
  public static String TypeClassEnum=mainUrl+"mobile/common/showEnums?enumName=TypeClassEnum";
//  我的仓库列表农资
  public static String getList1=mainUrl+"mobile/userStore/getList";
  //  我的仓库列表收成
  public static String getCropList=mainUrl+"mobile/userStore/getCropList";
  //  我的签到
  public static String sign=mainUrl+"mobile/sign/userSign";
  /**
   * 用户签到列表
   */
  public static String userSignList=mainUrl+"mobile/sign/userSignList";
  /*
  * 通知消息提醒
  * */
  public static String userSet=mainUrl+"mobile/user/userSet";
  /*
  * 重设密码
  * */
  public static String modifyPwd=mainUrl+"mobile/user/modifyPwd";
  /*
  * 意见反馈
  * */
  public static String addAdvice=mainUrl+"mobile/user/addAdvice";
  /*
  * 添加收货地址
  * */
  public static String addUserAdress=mainUrl+"mobile/userAddress/addUserAdress";
  /*
  * 用户收货地址管理
  * */
  public static String showUserAddress=mainUrl+"mobile/userAddress/showUserAddress";
  /*
  * 删除地址
  * */
  public static String deleteUserAddress=mainUrl+"mobile/userAddress/deleteUserAddress";
  /*
  * 设置用户默认收货地址
  * */
  public static String setDefaultUserAddress=mainUrl+"mobile/userAddress/setDefaultUserAddress";
  /*
  * 绑定手机号码
  * */
  public static String bindUserPhone=mainUrl+"mobile/user/bindUserPhone";
  /*
  * 余额
  * */
  public static String showUserBalance=mainUrl+"mobile/recordMoney/showUserBalance";
  /*
  * 明细列表
  * */
  public static String showUserRecordMoney=mainUrl+"mobile/recordMoney/showUserRecordMoney";
  /*
  * 账户安全：第一步：安全检测
  * */
  public static String modifyOne=mainUrl+"mobile/user/modifyOne";
  /*
  * 账户安全：第二步：修改
  * */
  public static String modifyTwo=mainUrl+"mobile/user/modifyTwo";
  /*
  * 我的订单列表
  * */
  public static String showMyOrders=mainUrl+"mobile/order/showMyOrders";
  /**
   * 获得订单详情
   */
  public static String showOrderDetails=mainUrl+"mobile/order/showOrderDetails";
  /*
  *  我的订单列表统计
   */
  public static String showMyOrdersCount=mainUrl+"mobile/order/showMyOrdersCount";
  /*
  *商品推荐
   */
  public static String getNews=mainUrl+"mobile/news/getNews";
  /*
  商品添加到购物车
   */
  public static String addShopCar=mainUrl+"mobile/shopcar/addShopCar";
  /*
  商品详情的查看全部评论
   */
  public static String showAllComment=mainUrl+"mobile/product/showAllComment";
  /*
  立即购买商品
   */
  public static String buyProduct=mainUrl+"mobile/product/buyProduct";
  /**
   * 设置支付密码
   */
  public static String modifyPayPassword=mainUrl+"mobile/user/modifyPayPassword";
  /*
  余额支付
   */
  public static String balancePay=mainUrl+"mobile/balance/balancePay";
  /**
   * 发起微信手机APP支付
   */
    public static String doWxPay=mainUrl+"mobile/pay/doWxPay";
    /**
     *发起支付宝手机APP支付
     * */
    public static String doAliPay=mainUrl+"mobile/pay/doAliPay";

    /**
   * 充值接口
   */
   public static String recharge=mainUrl+"mobile/balance/recharge";
  /*
   购物车添加订单
   */
  public static String addShopCarOrder=mainUrl+"mobile/order/addShopCarOrder";
  /*
   商品收藏
   */
  public static String addCollection=mainUrl+"mobile/collection/addCollection";
  /*
  *取消收藏
   */
  public static String delCollection=mainUrl+"mobile/collection/delCollection";
  /**
   * 检测是否收藏
   */
  public static String checkCollection=mainUrl+"mobile/collection/checkCollection";
  /**
   * 我的收藏
   */
  public static String collection=mainUrl+"mobile/user/collection";
  /*
  加入菜篮子
   */
  public static String joinUserFoodCar=mainUrl+"mobile/foodCar/joinUserFoodCar";
  /*

  获取施肥和农药列表
   */
  public static String getTypeUserStore=mainUrl+"mobile/userStore/getTypeUserStore";
  /**
   *用药，施肥，加料列表
   */
  public static String shoUserFarmAction=mainUrl+"mobile/userFarm/shoUserFarmAction";
  /**
   * 用户》用药，施肥，加料  操作
   */
  public static String actionFarm=mainUrl+"mobile/userFarm/actionFarm";
  /**
   *农场成本详情
   */
  public static String showCostList=mainUrl+"mobile/cost/showCostList";
  /**
   * 计算单个篮子的成本
   */
  public static String calculationHarvestCost=mainUrl+"mobile/foodCar/calculationHarvestCost";

  /**
   * 我的农场最新一条养殖日志
   */
  public static String showUserFarmNewLog=mainUrl+"mobile/userFarmLog/showUserFarmNewLog";
  /**
   * 添加菜篮子收获订单
   */
  public static String payOrder=mainUrl+"mobile/order/payHarvestOrder";
  /**
   * 清空商品收藏
   */
  public static String delAllUserProductCollection=mainUrl+"mobile/collection/delAllUserProductCollection";
  /**
   * 土地租用订单
   */
  public static String addLandOrder=mainUrl+"mobile/order/addLandOrder";
  /**
   * 配送订单
   */
  public static String addPeisongOrder=mainUrl+"mobile/order/addPeisongOrder";
  /**
   * 回复评论
   */
  public static String addComment=mainUrl+"mobile/comment/addComment";
  /**
   * 全局搜索
   */
  public static String search=mainUrl+"mobile/product/search";
  /**
   * 提醒配送
   */
  public static String addPsMessage=mainUrl+"mobile/systemMessage/addPsMessage";
  /**
   * 修改订单状态
   */
  public static String setOrderState=mainUrl+"mobile/order/setOrderState";
  /**
   * 获得全局配置
   */
  public static String showGlobal=mainUrl+"mobile/global/showGlobal";
  /**
   * 删除订单
   */
  public static String deleteOrder=mainUrl+"mobile/order/deleteOrder";
  /**
   * 全部评论
   */
  public static String getNewsComment=mainUrl+"mobile/comment/getNewsComment";
  /**
   * 修改用户
   */
  public static String modifyUser=mainUrl+"mobile/user/modifyUser";
  /**
   * 我的仓库收成列表全部
   */
  public static String getCropList2=mainUrl+"mobile/userStore/getCropList2";
  /**
   * 用药，施肥，加料 提示数量
   */
  public static String countFormAction=mainUrl+"mobile/userFarm/countFormAction";
}

