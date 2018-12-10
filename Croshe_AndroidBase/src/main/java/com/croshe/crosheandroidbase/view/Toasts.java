package com.croshe.crosheandroidbase.view;

import android.content.Context;
import android.os.Build;


/**
 * 
 * 
 * @autor:Bin
 * @version:1.0
 * @created:2014-8-2下午3:15:20
 **/
public class Toasts {
	private static TipsToast tipsToast;

	// /**
	// * 显示toast信息
	// *
	// * @param context
	// * @param msg
	// */
	// public static void showToast(Context context, String log) {
	// View toastRoot =
	// LayoutInflater.from(context).inflate(R.layout.common_brevity_toast,
	// null);
	// TextView msg = (TextView)
	// toastRoot.findViewById(R.id.common_toast_message);
	// msg.setText(log);
	// Toast toast = new Toast(context);
	// toast.setGravity(Gravity.BOTTOM, 0, 80);
	// toast.setDuration(Toast.LENGTH_SHORT);
	// toast.setView(toastRoot);
	// toast.show();
	// }

	/**
	 * 自定义taost
	 * 
	 * @param iconResId
	 *            图片
	 *            提示文字
	 */
	public static void showTips(Context context, int iconResId, String tips) {
		if (tipsToast != null) {
			if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
				tipsToast.cancel();
			}
		} else {
			tipsToast = TipsToast.makeText(context, tips,
					TipsToast.LENGTH_SHORT);
		}
		tipsToast.show();
		tipsToast.setIcon(iconResId);
		tipsToast.setText(tips);
	}

}
