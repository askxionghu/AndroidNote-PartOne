package cn.bingoogol.ndk.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import cn.bingoogol.ndk.R;
import cn.bingoogol.ndk.jni.CInvokeJava;
import cn.bingoogol.ndk.jni.JavaInvokeC;

public class MainActivity extends BaseActivity {
	private CInvokeJava mCInvokeJava;
	private TextView mResultTv;

	static {
		System.loadLibrary("NDK");
	}

	public native void callCInvokeJavaPrintString();

	@Override
	protected void initView() {
		setContentView(R.layout.activity_main);
		mResultTv = (TextView) findViewById(R.id.tv_main_result);
	}

	@Override
	protected void setListener() {
	}

	@Override
	protected void afterViews(Bundle savedInstanceState) {
		mCInvokeJava = new CInvokeJava();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_main_JavaInvokeC:
			mResultTv.setText(JavaInvokeC.getFullName("王", "浩") + " --- " + JavaInvokeC.add(2, 3));
			break;
		case R.id.btn_main_CInvokeJava:
			mCInvokeJava.name = "呵呵";
			mCInvokeJava.callHelloFromJava();
			mCInvokeJava.callAdd();
			mCInvokeJava.callPrintStaticString();
			callCInvokeJavaPrintString();
			break;

		default:
			break;
		}
	}
}
